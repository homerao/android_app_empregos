package br.com.loader;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Timer;
import java.util.TimerTask;

import br.com.databasehelper.ConexaoFirebaseHelper;
import br.com.empregos.MainActivity;
import br.com.empregos.R;
import br.com.firebase.login.FirebaseLoginEmailPasswordActivity;

public class LoaderActivity extends AppCompatActivity {

    ProgressBar progressBar;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    int counter = 0;
    TextView txmsg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loader);
        progressBar = findViewById(R.id.progressFireLogin);
        mAuth = ConexaoFirebaseHelper.getFirebaseAuth();



        updateProgress();
    }



    public void updateProgress() {
       final  Intent it =  new Intent(this, MainActivity.class);
       final Timer t = new Timer();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {


                    counter++;
                    progressBar.setProgress(counter);
                    user = mAuth.getCurrentUser();

                    if(counter == 100){
                        counter = 100;
                    }
                    if(user != null ){

                        progressBar.setProgress(100);

                        startActivityForResult(it, 1);
                        t.cancel();
                        finish();


                    }




            }
        };
        t.schedule(task,6,100);


    }


}
