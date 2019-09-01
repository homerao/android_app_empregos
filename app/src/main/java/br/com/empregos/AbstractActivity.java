package br.com.empregos;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.google.firebase.database.DatabaseReference;

import java.util.List;

import br.com.databasehelper.ConexaoFirebaseHelper;
import br.com.model.AbstractEntity;
import br.com.util.DownloadHelper;
import br.com.util.UploadHelper;

public abstract class AbstractActivity extends AppCompatActivity {

    protected List<View> lstComponents;
    protected AbstractEntity entity;
    protected Intent intent;
    protected DatabaseReference ref;
    protected UploadHelper upload;
    protected DownloadHelper download;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected  AbstractEntity getDataFromIntent(){

        if(getIntent().getExtras() != null){
            intent = getIntent();
            entity = intent.getExtras().getParcelable("entity");
        }
        return  entity;
    }

    protected void setupToolbar(String titulo){
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(titulo);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    protected  void setupControls(){

    }
    protected  void setUserData(int layout){

    }

    protected void setReference(String collection){
        ref = ConexaoFirebaseHelper.getDatabaseReference(collection);
    }

    protected  void setReference(String collection, String child){
        ref = ConexaoFirebaseHelper.getDatabaseReference(collection, child);
    }
}
