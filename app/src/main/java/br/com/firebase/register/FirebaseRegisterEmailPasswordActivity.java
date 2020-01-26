package br.com.firebase.register;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import br.com.databasehelper.ConexaoFirebaseHelper;
import br.com.empregos.MenuUsuarioActivity;
import br.com.empregos.R;
import br.com.interfaces.BackPressed;


public class FirebaseRegisterEmailPasswordActivity extends Activity implements BackPressed, View.OnClickListener{

    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private final static String TAG = "FIRE";
    private EditText edtEmail;
    private EditText edtSenha;
    private Button btnRegister;
    private EditText edtRepetirSenha;
    private Button btnVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_register_email_password);
        setUpToolbar();
        setup();






    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    protected void onStart() {
        super.onStart();
        mAuth = ConexaoFirebaseHelper.getFirebaseAuth();
        user = mAuth.getCurrentUser();
        if(user != null){
          carregarMenu();
        }

    }


    public void carregarMenu(){

          edtEmail.setText("");
          edtSenha.setText("");
          Intent it = new Intent(getApplicationContext(), MenuUsuarioActivity.class);


          startActivity(it);
          finish();
    }

    public void criarConta(String email, String password, View v){
        if(edtEmail.getText().toString().equals("")){
            edtEmail.setError("O e-mail não pode ser vazio");
        } else if (edtSenha.getText().toString().equals("")){
            edtSenha.setError("Você precisa digitar uma senha");
        } else if (edtRepetirSenha.getText().toString().equals("")){
            edtRepetirSenha.setError("Repita a senha!");
        }

        if(!edtSenha.getText().toString().equals(edtRepetirSenha.getText().toString())){

            Snackbar.make(v, "As duas senhas devem ser iguais", Snackbar.LENGTH_SHORT).show();
            edtRepetirSenha.setError("Você não digitou as duas senhas iguais");
        } else {

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "createUserWithEmail:success");
                                user = mAuth.getCurrentUser();
                                user.sendEmailVerification();



                                finish();

                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                Toast.makeText(getApplicationContext(), "Falha ao criar um usuário." + task.getException().getMessage(),
                                        Toast.LENGTH_SHORT).show();

                            }

                            // ...
                        }
                    });

        }

    }


    @Override
    public void onClick(View v) {
     /*aqui vai algum método de validação*/
       if(v == btnRegister){
           if(validarCadastro(v)){
               criarConta(edtEmail.getText().toString().trim(), edtSenha.getText().toString().trim(), v);
           }
       } else if(v == btnVoltar ){
              backPressed();
       }

    }


    public boolean validarCadastro(View v){
        boolean resultado = true;
        ConstraintLayout layout = findViewById(R.id.registerLayout);
        if(edtEmail.getText().toString().equals("") || edtRepetirSenha.getText().toString().equals("") || edtRepetirSenha.getText().toString().equals("")){

            Snackbar.make(layout,"Os campos e-mail, senha e repetir senha são obrigatórios", Snackbar.LENGTH_SHORT).setActionTextColor(Color.RED).show();
            resultado = false;
        }

        return resultado;
    }

    public boolean validarEmail(){

        return true;
    }


    public void enviaConfirmacaoDeEmail(){

    }

    public void setUpToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setActionBar(toolbar);
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void backPressed() {
        finish();
    }

    private void setup(){
        mAuth = FirebaseAuth.getInstance();
        edtRepetirSenha = findViewById(R.id.edtRepetirSenha);
        edtEmail = findViewById(R.id.txZonaView);
        edtSenha = findViewById(R.id.edtSenha);
        btnRegister = findViewById(R.id.btnFireLogin);
        btnVoltar = findViewById(R.id.btnRegisterVoltar);
        btnVoltar.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
    }

}
