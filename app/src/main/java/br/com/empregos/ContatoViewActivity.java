package br.com.empregos;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import br.com.empregos.partial.ContatoEditSaveActivity;
import br.com.model.Contato;

public class ContatoViewActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnOpenContatoSave;
    private FirebaseDatabase fa;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private DatabaseReference ref;
    private Contato contato = new Contato();
    private DataSnapshot snap;
    private FirebaseDatabase database;
    private TextView edtEmailview;
    private TextView edtCelularview;
    private TextView edtCelularRecadoview;
    private TextView edtFalarComview;
    private TextView edtTelefoneFixoview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_contatos);
         contato.fillContato();
         setup();
         setupToolBar();
         fillContatoData();
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
        toolbar.setTitle("Dados de contato");
        setActionBar(toolbar);
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setup(){
        edtEmailview = findViewById(R.id.txZonaView);
        edtCelularview = findViewById(R.id.txCartMilitarView);
        edtCelularRecadoview = findViewById(R.id.edtCelularRecado);
        edtFalarComview = findViewById(R.id.edtFalarCom);
        edtTelefoneFixoview = findViewById(R.id.edtTelefoneFixo);
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        database = FirebaseDatabase.getInstance();

        btnOpenContatoSave = findViewById(R.id.btnOpenEditContato);
        btnOpenContatoSave.setOnClickListener(this);
    }

    private void fillContatoData(){

        contato = new Contato();
        ref = database.getReference("Contatos");
        ref.child("Contato-"+user.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                contato = dataSnapshot.getValue(Contato.class);

                Handler hand = new Handler();
                try{
                    if(contato == null){
                        hand.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                edtEmailview.setText("");
                                edtCelularview.setText("");
                                edtCelularRecadoview.setText("");
                                edtFalarComview.setText("");
                                edtTelefoneFixoview.setText("");
                            }
                        }, 100);
                    }else{
                        hand.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                edtEmailview.setText(contato.getEmail().toUpperCase());
                                edtCelularview.setText(contato.getCelular().toUpperCase());
                                edtCelularRecadoview.setText(contato.getCelularRecado().toUpperCase());
                                edtFalarComview.setText(contato.getFalarCom().toUpperCase());
                                edtTelefoneFixoview.setText(contato.getTelefoneFixo().toUpperCase());
                            }
                        }, 100);
                    }

                }catch(NullPointerException e){

                }


                Log.d("\n\n\nSNAPSHOT", "fillContatoData: "+ dataSnapshot.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }


    @Override
    public void onClick(View v) {
        if(v == btnOpenContatoSave){
            Bundle bd = new Bundle();
          bd.putParcelable("Contato", contato);

        Intent it = new Intent(this, ContatoEditSaveActivity.class);
        it.putExtra("contatoBundle", bd);
        startActivity(it);
        }
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
}
