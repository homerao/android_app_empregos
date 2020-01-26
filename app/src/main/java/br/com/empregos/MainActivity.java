package br.com.empregos;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;

import com.facebook.login.widget.LoginButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONException;
import org.json.JSONObject;

import androidx.annotation.Nullable;
import br.com.databasehelper.ConexaoFirebaseHelper;
import br.com.firebase.login.FirebaseLoginEmailPasswordActivity;
import br.com.firebase.register.FirebaseRegisterEmailPasswordActivity;


public class MainActivity extends Activity implements View.OnClickListener{

    private EditText txEmail;
    private EditText txSenha;
    private Button btnLogin;
    private ImageView avatar;
    private Button btnRegistro;
    private LoginButton btnLoginFacebook;
    private ImageButton btnLoginGoogle;
    private Bitmap bmp;
    private CallbackManager callback;
    private String nome;
    private FirebaseAuth auth;
    private FirebaseUser fireUser;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        auth = ConexaoFirebaseHelper.getFirebaseAuth();
        fireUser = auth.getCurrentUser();
        if(fireUser != null){
          carregarMenu();
        }

        setContentView(R.layout.activity_main);


        btnLogin = findViewById(R.id.btnFireLogin);
        callback = CallbackManager.Factory.create();



        btnRegistro = findViewById(R.id.btnRegistrar);





        btnLogin.setOnClickListener(this);
        btnRegistro.setOnClickListener(this);

















    }



    public void carregarMenu(){

        Intent it = new Intent(getApplicationContext(),MenuUsuarioActivity.class);
        finish();
        startActivity(it);

    }


    public void onClick(View v) {


        if(v == btnLogin){
         commomLogin();
        } else if (v == btnRegistro){
            registrarClick();
        }







    }
    private void checkLoginStatus(){
        if(AccessToken.getCurrentAccessToken() != null){
            carregarDadosdoUsuarioFacebook(AccessToken.getCurrentAccessToken());

        }
    }

    public void btnLoginFacebookClick(Bundle params){
        Intent itMenu = new Intent(getApplicationContext(), MenuUsuarioActivity.class);
        itMenu.putExtras(params);
        Toast.makeText(getApplicationContext(),"o Botão do facebook foi clicado", Toast.LENGTH_SHORT).show();
        startActivity(itMenu);
    }

    public void btnLoginGoogleClick(){
        Toast.makeText(getApplicationContext(),"o Botão do Google foi clicado", Toast.LENGTH_SHORT).show();
    }

    public void commomLogin(){

    Intent it = new Intent(getApplicationContext(), FirebaseLoginEmailPasswordActivity.class);

    startActivity(it);


    }

    public void registrarClick(){

    Intent it = new Intent(getApplicationContext(), FirebaseRegisterEmailPasswordActivity.class);
    startActivity(it);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
             carregarMenu();

    }


    private void carregarDadosdoUsuarioFacebook(AccessToken token){

        GraphRequest req = GraphRequest.newMeRequest(token, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {

                try {
                    String firstName = object.getString("first_name");
                    String lastName = object.getString("last_name");
                    String email = object.getString("email");
                    String id = object.getString("id");
                    String img_url = "https://graph.facebook.com/"+ id +"/picture?type=normal";
                    txEmail.setText(email);
                    nome = firstName;
                    Bundle bundle = new Bundle();
                    bundle.putString("nome", nome);
                    bundle.putString("email", txEmail.getText().toString());
                    Glide.with(MainActivity.this).load(img_url).into(avatar);
                    btnLoginFacebookClick(bundle);


                }catch(JSONException e){
                     e.printStackTrace();
                    Toast.makeText(MainActivity.this, "Erro no login", Toast.LENGTH_SHORT).show();
                }

            }
        });

        Bundle parameters = new Bundle();
        parameters.putString("fields", "first_name,last_name,email,id");
        req.setParameters(parameters);
        req.executeAsync();
    }
}
