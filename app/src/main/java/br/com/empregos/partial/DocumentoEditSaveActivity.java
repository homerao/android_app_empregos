package br.com.empregos.partial;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.util.Date;

import br.com.empregos.DocumentoViewActivity;
import br.com.empregos.R;
import br.com.empregos.dao.DocumentoDAO;
import br.com.interfaces.BackPressed;
import br.com.model.Documento;
import br.com.util.DateConverterUtil;
import br.com.util.MaskEditUtil;

public class DocumentoEditSaveActivity extends AppCompatActivity implements View.OnClickListener, BackPressed {


    private static final String TAG = "DocumentoEditsave";

/*    private TextView txRG;
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
    private TextView txSerieCartProfissional;*/

    // edittexts
    private EditText edtRG;
    private EditText edtDataExpRG;
    private EditText edtOrgExped;
    private EditText edtCPF;
    private EditText edtTituloEleitoral;
    private EditText edtZonaSecao;
    private EditText edtCarteiraMilitar;
    private EditText edtCNH;
    private EditText edtCatCNH;
    private EditText edtDataValidadeCNH;
    private EditText edtPIS;
    private EditText edtCarteiraProfissional;
    private EditText edtSerieCartProfissional;

    // firebase
    private FirebaseDatabase mDatabase;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser firebaseUser;
    private DatabaseReference reference;
    private Documento documento;
    private DocumentoDAO dao;
    private Button btnSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document_edit_save);
        setupToolBar();
        setup();
        fillUserData();
        validateDocumentos();


    }

    @Override
    public void onClick(View v) {

        if(v == btnSalvar){
            createOrUpdate();

        } else {
            backPressed();
        }

    }

    @Override
    public void backPressed() {
        finish();
    }

    private Documento getDataFromIntent(){
        Intent it = getIntent();
        Bundle b = it.getBundleExtra("documentoBundle");
        documento = b.getParcelable("Documento");
        return  documento;
    }


    private void setupToolBar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Inserir Documentos");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void setup(){


        //lincando recursos
        btnSalvar = findViewById(R.id.btnDocSave);
        edtRG = findViewById(R.id.edtRG);
        edtDataExpRG = findViewById(R.id.edtDataExp);
        edtOrgExped = findViewById(R.id.edtOrgExp);
        edtCPF = findViewById(R.id.edtCpf);
        edtTituloEleitoral = findViewById(R.id.edtTituloEleitoral);
        edtZonaSecao = findViewById(R.id.edtZonaSecao);
        edtCarteiraMilitar = findViewById(R.id.edtCartMilitar);
        edtCNH = findViewById(R.id.edtHabilitacao);
        edtCatCNH = findViewById(R.id.edtCatCNH);
        edtDataValidadeCNH = findViewById(R.id.edtValidadeCNH);
        edtPIS = findViewById(R.id.edtNumeroPis);
        edtCarteiraProfissional = findViewById(R.id.edtCartProfissional);
        edtSerieCartProfissional = findViewById(R.id.edtSerieCartProfi);
        documento = getDataFromIntent();
        dao = new DocumentoDAO(Documento.class,"Documentos");

        // listener do botão
        btnSalvar.setOnClickListener(this);
    }

    private void createOrUpdate(){
        mFirebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = mFirebaseAuth.getCurrentUser();
     try {

         documento.setIdCandidato(firebaseUser.getUid());
         documento.setDataValidadeHabilitacao(DateConverterUtil.stringToDate(edtDataValidadeCNH.getText().toString()));
         documento.setRg(edtRG.getText().toString().trim().toLowerCase());
         documento.setDataExpedicaoRG(DateConverterUtil.stringToDate(edtDataExpRG.getText().toString()));
         documento.setOrgaoExpRG(edtOrgExped.getText().toString().trim().toUpperCase());
         documento.setCpf(edtCPF.getText().toString().trim());
         documento.setTituloEleitor(edtTituloEleitoral.getText().toString().trim());
         documento.setZonaSecao(edtZonaSecao.getText().toString().trim().toUpperCase());
         documento.setCarteiraMilitar(edtCarteiraMilitar.getText().toString().trim().toUpperCase());
         documento.setHabilitacao(edtCNH.getText().toString().trim().toUpperCase());
         documento.setCategoriaHabilitacao(edtCatCNH.getText().toString().trim().toUpperCase());
         documento.setPis(edtPIS.getText().toString().trim().toUpperCase());
         documento.setCarteiraProfissional(edtCarteiraProfissional.getText().toString().trim().toUpperCase());
         documento.setSerieCarteiraProfissional(edtSerieCartProfissional.getText().toString().trim().toUpperCase());
         dao.saveOrUpdate(documento, firebaseUser.getUid());
         Toast.makeText(this, "Salvando", Toast.LENGTH_SHORT).show();
         Handler h = new Handler();
         h.postDelayed(new Runnable() {
             @Override
             public void run() {
                 finish();
             }
         }, 100);
     }catch(NullPointerException| ParseException e){
         Toast.makeText(this, "Falha ao salvar os dados, favor verifique o formulário", Toast.LENGTH_SHORT).show();
         Log.e(TAG, "createOrUpdate: ", e);
     }
    }

    private void fillUserData(){
        if(documento == null){
            edtRG.setText("");
            edtDataExpRG.setText("");
            edtOrgExped.setText("");
            edtCPF.setText("");
            edtTituloEleitoral.setText("");
            edtZonaSecao.setText("");
            edtCarteiraMilitar.setText("");
            edtCNH.setText("");
            edtCatCNH.setText("");
            edtDataValidadeCNH.setText("");
            edtPIS.setText("");
            edtCarteiraProfissional.setText("");
            edtSerieCartProfissional.setText("");

        } else {
            edtRG.setText(documento.getRg());
            edtDataExpRG.setText(documento.getDataExpedicaoRG() == null ? "": DateConverterUtil.dateToString(documento.getDataExpedicaoRG()));
            edtOrgExped.setText(documento.getOrgaoExpRG());
            edtCPF.setText(documento.getCpf());
            edtTituloEleitoral.setText(documento.getTituloEleitor());
            edtZonaSecao.setText(documento.getZonaSecao());
            edtCarteiraMilitar.setText(documento.getCarteiraMilitar());
            edtCNH.setText(documento.getHabilitacao());
            edtCatCNH.setText(documento.getCategoriaHabilitacao());
            edtDataValidadeCNH.setText(documento.getDataValidadeHabilitacao() == null ? "": DateConverterUtil.dateToString(documento.getDataValidadeHabilitacao()) );
            edtPIS.setText(documento.getPis());
            edtCarteiraProfissional.setText(documento.getCarteiraProfissional());
            edtSerieCartProfissional.setText(documento.getSerieCarteiraProfissional());
        }
    }

    private void validateDocumentos(){
        edtRG.addTextChangedListener(MaskEditUtil.mask(edtRG,MaskEditUtil.FORMAT_RG));
        edtCPF.addTextChangedListener(MaskEditUtil.mask(edtCPF, MaskEditUtil.FORMAT_CPF));
        edtDataValidadeCNH.addTextChangedListener(MaskEditUtil.mask(edtDataValidadeCNH,MaskEditUtil.FORMAT_DATE));
        edtDataExpRG.addTextChangedListener(MaskEditUtil.mask(edtDataExpRG,MaskEditUtil.FORMAT_DATE));
        edtZonaSecao.addTextChangedListener(MaskEditUtil.mask(edtZonaSecao,MaskEditUtil.ZONA_SECAO));


    }
}
