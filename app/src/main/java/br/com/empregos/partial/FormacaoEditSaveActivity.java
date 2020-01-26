package br.com.empregos.partial;

import android.os.Bundle;
import android.widget.Toolbar;


import androidx.appcompat.app.AppCompatActivity;
import br.com.empregos.R;

public class FormacaoEditSaveActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formacao_edit_save);
        setupToolBar();
    }









    private void setupToolBar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Cadastro de Formação");
        setActionBar(toolbar);
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
