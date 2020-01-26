package br.com.empregos.partial;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import br.com.empregos.R;
import br.com.empregos.dao.ContatoDAO;
import br.com.model.Contato;

public class ContatoEditSaveActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText edtEmail;
    private EditText edtCelular;
    private EditText edtCelularRecado;
    private EditText edtFalarcom;
    private EditText edtTelefoneFixo;
    private Button btnSalvar;
    private Contato contato;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private FirebaseDatabase database;
    private ContatoDAO  dao;







    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contato_edit_save);
        setupToolBar();
        setup();
        getDataFromIntent();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void setupToolBar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Contato");
        setActionBar(toolbar);
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setup(){
        edtEmail = findViewById(R.id.txRGView);
        edtCelular = findViewById(R.id.txCpfView);
        edtCelularRecado = findViewById(R.id.txTituloEleView);
        edtFalarcom = findViewById(R.id.edtFalarcomSave);
        edtTelefoneFixo = findViewById(R.id.edtTelefoneFixo);
        btnSalvar = findViewById(R.id.btnSalvar);
        btnSalvar.setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();
        mUser =  mAuth.getCurrentUser();
    }

    @Override
    public void onClick(View v) {

        if (v == btnSalvar) {
            if (validate()) {
                createOrUpdate();
            }
        }
    }

    private boolean validate(){
     boolean result = true;

        if(edtCelular.getText().toString().trim().equals("")){
            edtCelular.setError("Favor preencher o campo Celular");
            result = false;
        }

     return result;
    }

    private void createOrUpdate(){
        dao = new ContatoDAO(Contato.class, "Contatos");
        contato.setCelular(edtCelular.getText().toString().trim().toUpperCase());
        contato.setCelularRecado(edtCelularRecado.getText().toString().trim().toUpperCase());
        contato.setEmail(edtEmail.getText().toString().trim().toUpperCase());
        contato.setFalarCom(edtFalarcom.getText().toString().trim().toUpperCase());
        contato.setTelefoneFixo(edtTelefoneFixo.getText().toString().trim().toUpperCase());
        contato.setUpdated_At(new Date());
        dao.saveOrUpdate(contato, mUser.getUid());
        Toast.makeText(this, "Contato salvo!!", Toast.LENGTH_SHORT).show();
    }

    private void getDataFromIntent(){
        Intent it = getIntent();
        Bundle bd = new Bundle();
        bd = it.getBundleExtra("contatoBundle");
        contato = bd.getParcelable("Contato");


        Handler hand = new Handler();
        if(contato == null){
            contato = new Contato();
            contato.fillContato();
            hand.postDelayed(new Runnable() {
                @Override
                public void run() {
                    edtEmail.setText(contato.getEmail());
                    edtCelular.setText(contato.getCelular());
                    edtCelularRecado.setText(contato.getCelularRecado());
                    edtFalarcom.setText(contato.getFalarCom());
                    edtTelefoneFixo.setText(contato.getTelefoneFixo());
                }
            }, 500);
        }else {
            hand.postDelayed(new Runnable() {
                @Override
                public void run() {
                    edtEmail.setText(contato.getEmail());
                    edtCelular.setText(contato.getCelular());
                    edtCelularRecado.setText(contato.getCelularRecado());
                    edtFalarcom.setText(contato.getFalarCom());
                    edtTelefoneFixo.setText(contato.getTelefoneFixo());
                }
            }, 500);
        }
    }
}
