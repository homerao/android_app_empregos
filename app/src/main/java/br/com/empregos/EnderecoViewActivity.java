package br.com.empregos;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import br.com.empregos.dao.EnderecoDAO;
import br.com.empregos.partial.EnderecoEditSaveActivity;
import br.com.model.Endereco;

public class EnderecoViewActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView txCidade;
    private TextView txBairro;
    private TextView txRua;
    private TextView txReferencia;
    private TextView txCep;
    private Button btnEdit;
    private static Endereco  endereco;
    private EnderecoDAO dao;
    private FirebaseAuth auth;
    private FirebaseUser user;
    private FirebaseDatabase database;
    private DatabaseReference reference;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_endereco_view);
        setupToolBar();
        setup();
        getEnderecoData();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void setupToolBar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Endereço");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setup(){
        txCidade = findViewById(R.id.edtCidadeView);
        txBairro = findViewById(R.id.edtBairroView);
        txRua = findViewById(R.id.edtRuaView);
        txReferencia = findViewById(R.id.edtReferenciaView);
        txCep = findViewById(R.id.edtCepView);
        btnEdit = findViewById(R.id.btnOpenEnderecoEdit);
        btnEdit.setOnClickListener(this);
    }

    private DatabaseReference getDatabaseReference(String collection, String... childs){
             auth = FirebaseAuth.getInstance();
             user = auth.getCurrentUser();
             database = FirebaseDatabase.getInstance();
             reference = database.getReference(collection).child(childs[0]);
             return  reference;
    }

    private void getEnderecoData(){
        endereco = new Endereco();
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        DatabaseReference ref = getDatabaseReference("Enderecos", "Endereco-"+user.getUid());
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
             endereco = dataSnapshot.getValue(Endereco.class);
             try {
                 if(endereco != null){
                     txCidade.setText(endereco.getCidade());
                     txBairro.setText(endereco.getBairro());
                     txRua.setText(endereco.getRua());
                     txReferencia.setText(endereco.getReferencia());
                     txCep.setText(endereco.getCep());
                 }else {
                     txCidade.setText("");
                     txBairro.setText("");
                     txRua.setText("");
                     txReferencia.setText("");
                     txCep.setText("");
                     Log.d("TAG. ENDERECO", "onDataChange: dados não setados");
                 }
             }catch (NullPointerException e) {
                 Toast.makeText(EnderecoViewActivity.this, "Impossível", Toast.LENGTH_SHORT).show();
             }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    // criação de menu de opção
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_usuario, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_sincronizar:

                break;
            case R.id.action_configuracoes:

                break;
            case R.id.action_sair:

                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View v) {
        Intent it;
        Bundle bd = new Bundle();

        if(endereco != null){
          bd.putParcelable("enderecoParcel", endereco);

          it = new Intent(getApplicationContext(),EnderecoEditSaveActivity.class);
          it.putExtra("endereco",bd );
          startActivity(it);
        } else {
            endereco = new Endereco();
            bd.putParcelable("enderecoParcel", endereco);

            it = new Intent(getApplicationContext(),EnderecoEditSaveActivity.class);
            it.putExtra("endereco", bd);
            startActivity(it);
        }

    }
}
