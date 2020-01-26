package br.com.firebase.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
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
import br.com.firebase.recovery.PasswordRecoveryActivity;
import br.com.interfaces.BackPressed;
import br.com.loader.LoaderActivity;


public class FirebaseLoginEmailPasswordActivity extends Activity implements BackPressed, View.OnClickListener{

    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private final static String TAG = "FIRE";
    private EditText edtEmail;
    private EditText edtSenha;
    private Button btnLogin;
    private ProgressBar bar;
    private Intent loadingIntent;
    private Button btnPassRecovery;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_firebase_login_email_password);

        setupToolbar();
        setup();






    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        carregarMenu();
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

    private void setup(){
        mAuth = FirebaseAuth.getInstance();
        edtEmail = findViewById(R.id.txZonaView);
        edtSenha = findViewById(R.id.edtSenha);
        btnLogin = findViewById(R.id.btnFireLogin);
        btnPassRecovery = findViewById(R.id.btnRecuperarSenha);
        btnLogin.setOnClickListener(this);
        btnPassRecovery.setOnClickListener(this);
    }



    public void fireBaseLogin(String email, String password){
        /*Aqui vai alguma validação necessária*/
       loadingIntent = new Intent(this, LoaderActivity.class);
       startActivity(loadingIntent);

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");


                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(FirebaseLoginEmailPasswordActivity.this, "Autenticação falhou, verifique seu e-mail e senha.",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });
        finish();
    }


    @Override
    public void onClick(View v) {

    if(v == btnLogin){
        if(validarLogin()){
            fireBaseLogin(edtEmail.getText().toString().trim(), edtSenha.getText().toString().trim());
        }
    } else if (v == btnPassRecovery){
        openIntent(PasswordRecoveryActivity.class);
    }

    }




    public boolean validarLogin(){
      boolean accepted = true;
        ConstraintLayout layout = findViewById(R.id.loginFirebaseLayout);
      if(edtEmail.getText().toString().equals("") || edtSenha.getText().toString().equals("")){
          accepted = false;
          Snackbar.make(layout,"Os campos E-mail e senha são obrigatórios", Snackbar.LENGTH_SHORT).show();

      }
        return accepted;
    }


    @Override
    public void backPressed() {
        finish();
    }

    private void openIntent(Class c){
        Intent it = new Intent(getApplicationContext(), c);
        startActivity(it);
    }

    private void setupToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Log-in");
        setActionBar(toolbar);
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }


}
