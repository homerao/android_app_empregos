package br.com.empregos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import br.com.empregos.dao.DocumentoDAO;
import br.com.empregos.exception.RegistroInexistenteException;
import br.com.empregos.partial.DocumentoEditSaveActivity;
import br.com.model.Documento;
import br.com.util.DateConverterUtil;

public class DocumentoViewActivity extends Activity implements View.OnClickListener{

    private TextView txRG;
    private TextView txDataExpRG;
    private TextView txOrgExped;
    private TextView txCPF;
    private TextView txTituloEleitoral;
    private TextView txZonaSecao;
    private TextView txCarteiraMilitar;
    private TextView txCNH;
    private TextView txCatCNH;
    private TextView txPIS;
    private TextView txCarteiraProfissional;
    private TextView txSerieCartProfissional;
    private FirebaseDatabase mDatabase;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser firebaseUser;
    private DatabaseReference reference;
    private Documento documento;
    private DocumentoDAO dao;
    private Button btnEdit;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_documentos);
        setupToolBar();
        setup();
        fillDocumentoData();
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
        toolbar.setTitle("Documentos");
        setActionBar(toolbar);
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setup(){
        documento = new Documento();
        dao = new DocumentoDAO(Documento.class,"Documentos");
        documento.fillDocumento();
        txRG = findViewById(R.id.txRGView);
        txDataExpRG = findViewById(R.id.txRGDataExpView);
        txOrgExped = findViewById(R.id.txOrgExpedView);
        txCPF = findViewById(R.id.txCpfView);
        txTituloEleitoral = findViewById(R.id.txTituloEleView);
        txZonaSecao = findViewById(R.id.txZonaView);
        txCarteiraMilitar = findViewById(R.id.txCartMilitarView);
        txCNH = findViewById(R.id.txCnHView);
        txCatCNH = findViewById(R.id.edtRuaView);
        txPIS = findViewById(R.id.txPisView);
        txCarteiraProfissional = findViewById(R.id.txCarteiraProfiView);
        txSerieCartProfissional = findViewById(R.id.txSerieCartProfView);
        btnEdit = findViewById(R.id.btnOpenDocEdit);
        btnEdit.setOnClickListener(this);
    }




    public  void fillDocumentoData(){
        mFirebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = mFirebaseAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance();
        DatabaseReference ref = mDatabase.getReference("Documentos/"+"Documento-"+firebaseUser.getUid());

        documento = new Documento();
        documento.fillDocumento();
        ref.child("Documento-"+firebaseUser.getUid());
        ref.addValueEventListener(new ValueEventListener() {
            private static final String TAG = "filldocumentData";

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d("DATASNAPSHOT", "onDataChange: FILLDOCUMENTO" + dataSnapshot.toString());
                documento = dataSnapshot.getValue(Documento.class);



                Handler hand = new Handler();
                hand.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            Log.d(TAG, "onDataChange: " + documento.toString());
                           if(documento == null){
                               txRG.setText("");
                               txDataExpRG.setText("");
                               txOrgExped.setText("");
                               txCPF.setText("");
                               txTituloEleitoral.setText("");
                               txZonaSecao.setText("");
                               txCarteiraMilitar.setText("");
                               txCNH.setText("");
                               txCatCNH.setText("");
                               txPIS.setText("");
                               txCarteiraProfissional.setText("");
                               txSerieCartProfissional.setText("");

                           }else {
                               txRG.setText(documento.getRg());
                               txDataExpRG.setText(documento.getDataExpedicaoRG() == null ?  "":DateConverterUtil.dateToString(documento.getDataExpedicaoRG()));
                               txOrgExped.setText(documento.getOrgaoExpRG());
                               txCPF.setText(documento.getCpf());
                               txTituloEleitoral.setText(documento.getTituloEleitor());
                               txZonaSecao.setText(documento.getZonaSecao());
                               txCarteiraMilitar.setText(documento.getCarteiraMilitar());
                               txCNH.setText(documento.getHabilitacao());
                               txCatCNH.setText(documento.getCategoriaHabilitacao());
                               txPIS.setText(documento.getPis());
                               txCarteiraProfissional.setText(documento.getCarteiraProfissional());
                               txSerieCartProfissional.setText(documento.getSerieCarteiraProfissional());
                           }

                        }catch(NullPointerException e){
                            Log.e(TAG, "run: ",e );
                        }

                    }
                }, 100);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
    private Documento getDocumentoData(){
        mFirebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = mFirebaseAuth.getCurrentUser();
        Documento d = null;
        try {
            d = dao.buscarUm(firebaseUser.getUid());

        } catch (RegistroInexistenteException e) {
            e.printStackTrace();
        }
     return d;
    }

    private void createOrUpdate(){
        mFirebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = mFirebaseAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance();
        reference = mDatabase.getReference("Documentos");
        reference.child("Documento-"+firebaseUser.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                documento = dataSnapshot.getValue(Documento.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(DocumentoViewActivity.this, "Não foi possível buscar o documento", Toast.LENGTH_SHORT).show();
            }
        });

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


    @Override
    public void onClick(View v) {

       if(v == btnEdit){
           if(documento != null){
               Bundle b = new Bundle();
               Bundle bd = new Bundle();
               bd.putParcelable("Documento", documento);
               b.putBundle("documentoBundle",bd);
               openIntent(DocumentoEditSaveActivity.class, b);
           } else {
               documento = new Documento();
               Bundle b = new Bundle();
               Bundle bd = new Bundle();
               bd.putParcelable("Documento", documento);
               b.putBundle("documentoBundle",bd);
               openIntent(DocumentoEditSaveActivity.class, b);
           }
       }
    }


    private void openIntent(Class c, Bundle extras){
        Intent it = new Intent(getApplicationContext(),c);
        it.putExtras(extras);
        startActivity(it);
    }
}
