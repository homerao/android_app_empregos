package br.com.empregos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import br.com.adapters.AdapterCursos;
import br.com.model.Cursos;
import br.com.util.CursosUtil;
import br.com.util.MenuResourceUtil;

public class CursoViewActivity extends Activity {
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
        setActionBar(toolbar);
        getActionBar().setDisplayHomeAsUpEnabled(true);
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
