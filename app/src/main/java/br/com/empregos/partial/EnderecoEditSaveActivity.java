package br.com.empregos.partial;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import br.com.empregos.R;
import br.com.empregos.dao.EnderecoDAO;
import br.com.model.Endereco;

public class EnderecoEditSaveActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "EnderecoEdit";
    private EditText edtCidade;
    private EditText edtBairro;
    private EditText edtRua;
    private EditText edtNumero;
    private EditText edtCep;
    private EditText edtReferencia;
    private Button btnSalvar;
    private Endereco endereco;
    private EnderecoDAO dao;
    private FirebaseAuth auth;
    private FirebaseUser user;
    private Intent dataIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_endereco_edit_save);
        setupToolBar();
        setup();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    private void setupToolBar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    private void setup(){
        edtCidade = findViewById(R.id.edtCidadeSave);
        edtBairro = findViewById(R.id.edtBairroSave);
        edtRua = findViewById(R.id.edtRuaSave);
        edtCep = findViewById(R.id.edtCepSave);
        edtReferencia = findViewById(R.id.edtReferenciaSave);
        btnSalvar = findViewById(R.id.btnSalvarEndereco);
        btnSalvar.setOnClickListener(this);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        dataIntent = getIntent();
        try {
            endereco = (Endereco) dataIntent.getBundleExtra("endereco").getParcelable("enderecoParcel");
            if(endereco == null){
                endereco = new Endereco();
            } else {
             fillUserData();
            }
        }catch (NullPointerException e){
            Log.e(TAG, "setup: Não foi possível obter os dados do endereço",e );
        }
    }

    @Override
    public void onClick(View v) {
        saveOrUpdate();
    }

    private boolean validaForm(){

        return  true;
    }
    private void fillUserData(){
        try {
          edtCidade.setText(endereco.getCidade());
          edtBairro.setText(endereco.getBairro());
          edtRua.setText(endereco.getRua());
          edtReferencia.setText(endereco.getReferencia());
          edtCep.setText(endereco.getCep());
        }catch (NullPointerException e){
            Log.e(TAG, "fillUserData: ", e);
        }

    }


    private void saveOrUpdate(){
      endereco = new Endereco();
      dao = new EnderecoDAO(Endereco.class, "Enderecos");
      try {
          endereco.setCidade(edtCidade.getText().toString().trim().toUpperCase());
          endereco.setBairro(edtBairro.getText().toString().trim().toUpperCase());
          endereco.setRua(edtRua.getText().toString().trim().toUpperCase());
          endereco.setCep(edtCep.getText().toString().trim().toUpperCase());
          endereco.setReferencia(edtReferencia.getText().toString().trim().toUpperCase());
          dao.saveOrUpdate(endereco,user.getUid());
          Toast.makeText(this, "Endereço Salvo com Sucessso", Toast.LENGTH_SHORT).show();
          Handler h = new Handler();

          h.postDelayed(new Runnable() {
              @Override
              public void run() {
               finish();
              }
          }, 400);

      }catch (NullPointerException e){
          Toast.makeText(this, "Falha ao salvar..." + e.getMessage(), Toast.LENGTH_SHORT).show();
          Log.e(TAG, "saveOrUpdate:  salvar",e );
      }
    }
}
