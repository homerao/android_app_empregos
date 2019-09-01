package br.com.empregos;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import br.com.adapters.AdapterCursos;
import br.com.model.Cursos;
import br.com.util.CursosUtil;
import br.com.util.MenuResourceUtil;

public class CursoViewActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager manager;
    private List<Cursos> lstCursos = new ArrayList<>();

    private static final String TAG = "CursoViewActivity";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_cursos);
        setupToolBar();

        Log.d(TAG, "onCreate: CURSOS" + lstCursos.toString());
        setupRecyclerView();
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
        toolbar.setTitle("Cursos e treinamentos");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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

    public void setupRecyclerView(){
        CursosUtil u = new CursosUtil();
        lstCursos = new MenuResourceUtil.CursosResource().getCursos();
        Log.d(TAG, "setupRecyclerView: " + u.getCursos().toString());
        recyclerView = findViewById(R.id.recyclerCursos);
        recyclerView.setHasFixedSize(true);
        manager = new LinearLayoutManager(this);
        adapter = new AdapterCursos(lstCursos,this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        Log.d(TAG, "setupRecyclerView: RECYCLERVIEW SETADA");

    }


}
