package br.com.empregos.partial;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;

import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import br.com.empregos.AbstractActivity;
import br.com.empregos.DadosPessoaisViewActivity;
import br.com.empregos.R;
import br.com.empregos.dao.CandidatoDAO;
import br.com.interfaces.SetupControlls;
import br.com.model.Candidato;
import br.com.model.InformaCoesPessoais;
import br.com.util.DateConverterUtil;
import br.com.util.UploadHelper;
import de.hdodenhof.circleimageview.CircleImageView;

public class DadosPessoaisEditSaveActivity extends AbstractActivity implements View.OnClickListener, SetupControlls{
    private EditText mEdtNome;

    private Spinner mSpnSexo;
    private EditText mEdtNaturalidade;
    private EditText mEdtNacionalidade;
    private EditText mEdtEstadoCivil;
    private EditText mEdtDataNascimento;
    private Button btnSalvar;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private Candidato candidato = new Candidato();
    private CandidatoDAO dao;
    private ArrayAdapter<String> spnAdapter;
    private List<String> lstSexo;
    private DateConverterUtil dateUtil;
    private CircleImageView imProfile;
    private Uri mImageURI;
    private static final int PICK_IMAGE_REQUEST = 1;

    private FirebaseStorage storage;
    private StorageReference storeReference;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_pessoais_edit_save);
        setupToolbar();


        setup();




    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
       final Context context = this.getApplicationContext();
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null){
            mImageURI = data.getData();

            Handler hand = new Handler();
            hand.post(new Runnable() {
                @Override
                public void run() {
                    Glide.with(context).load(mImageURI).into(imProfile);
                }
            });

        }
    }

    @Override
    public void onClick(View v) {
        if(v == btnSalvar){
        if(validateForm()){
            Intent it = new Intent(getApplicationContext(), DadosPessoaisViewActivity.class);
            it.putExtra("pictureUrl",candidato.getProfilePicture());

            insertUserData();

           startActivity(it);

           finish();
        } else {
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
        }
        }  else if (v == imProfile){
            openFile();
        }
    }


    @Override
    public void setup() {
        mEdtNome = findViewById(R.id.txRGView);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        storeReference = FirebaseStorage.getInstance().getReference("profile_img");

        lstSexo = new ArrayList<>();
        lstSexo.add("MASCULINO");
        lstSexo.add("FEMININO");
        lstSexo.add("TRANS");
        mSpnSexo = findViewById(R.id.spnSexo);
        mEdtNaturalidade = findViewById(R.id.txCpfView);
        mEdtNacionalidade = findViewById(R.id.txTituloEleView);
        mEdtEstadoCivil = findViewById(R.id.edtTelefoneFixo);
        mEdtDataNascimento = findViewById(R.id.edtNascto);
        imProfile = findViewById(R.id.imgPerfil);
        spnAdapter = new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item, lstSexo);
        mSpnSexo.setAdapter(spnAdapter);
        btnSalvar = findViewById(R.id.btnSalvar);


        btnSalvar.setOnClickListener(this);

        imProfile.setOnClickListener(this);

        fillUserData();
    }


    public void fillUserData(){

        candidato.fillCandidato();
        Intent it = getIntent();
        Bundle b = new Bundle();
        b = it.getExtras();
        candidato = b.getParcelable("candidato");

         if(candidato.getInformacoes() != null){
             mEdtNome.setText(candidato.getInformacoes().getNome());
             mEdtNaturalidade.setText(candidato.getInformacoes().getNaturalidade());
             mEdtNacionalidade.setText(candidato.getInformacoes().getNacionalidade());
             mEdtEstadoCivil.setText(candidato.getInformacoes().getEstadoCivil());
             mSpnSexo.setSelection(getPosition(candidato.getInformacoes().getSexo()));
             Log.d("\n\n/USUARIO BUNDLE", "fillUserData: "+candidato.toString());
             mEdtDataNascimento.setText(DateConverterUtil.dateToString(candidato.getInformacoes().getDataNascimento()));

         } else {
             mEdtNome.setText("");
             mEdtNaturalidade.setText("");
             mEdtNacionalidade.setText("");
             mEdtEstadoCivil.setText("");
             mSpnSexo.setSelection(getPosition("0"));

             mEdtDataNascimento.setText("");

         }
    }

    public void updateUserData(){
        candidato = new Candidato();
        InformaCoesPessoais info = new InformaCoesPessoais();
        dao = new CandidatoDAO(Candidato.class,"Candidatos","profile_img");
        candidato.setProfilePicture("");
        candidato.setIdCandidato(mUser.getUid());
        info.setNome(mEdtNome.getText().toString());
        info.setSexo(mSpnSexo.getSelectedItem().toString());
        info.setNaturalidade(mEdtNaturalidade.getText().toString());
        info.setNacionalidade(mEdtNacionalidade.getText().toString());
        info.setEstadoCivil(mEdtEstadoCivil.getText().toString());
        try {
            info.setDataNascimento(DateConverterUtil.stringToDate(mEdtDataNascimento.getText().toString()));
        }catch(ParseException e){
            Toast.makeText(this, "Formato da data errado, por favor utilize dd/mm/yyyy", Toast.LENGTH_SHORT).show();
        }

        candidato.setInformacoes(info);



    }

    public void insertUserData(){

        InformaCoesPessoais info = new InformaCoesPessoais();

        dao = new CandidatoDAO(Candidato.class,"Candidatos","profile_img");
        if(candidato == null){
            candidato = new Candidato();
            candidato.setProfilePicture("");
        }
        candidato.setIdCandidato(mUser.getUid());
        candidato.setUpdated_At(new Date());
        if(candidato.getCreated_At() == null){
            candidato.setCreated_At(new Date());
        }


        info.setNome(mEdtNome.getText().toString());
        info.setSexo(mSpnSexo.getSelectedItem().toString());
        info.setNaturalidade(mEdtNaturalidade.getText().toString());
        info.setNacionalidade(mEdtNacionalidade.getText().toString());
        info.setEstadoCivil(mEdtEstadoCivil.getText().toString());
        try {
            info.setDataNascimento(DateConverterUtil.stringToDate(mEdtDataNascimento.getText().toString()));
        }catch(ParseException e){
            Toast.makeText(this, "Formato da data errado, por favor utilize dd/mm/yyyy", Toast.LENGTH_SHORT).show();
        }

        candidato.setInformacoes(info);

        deleteOldPicture(candidato.getProfilePicture());
        candidato.setProfilePicture(candidato.getIdCandidato()+"_"+System.currentTimeMillis());
        uploadFile(candidato.getProfilePicture());
        dao.saveOrUpdate(candidato, candidato.getIdCandidato());

        Toast.makeText(this, "Dados salvos" , Toast.LENGTH_SHORT).show();
    }





    private void deleteOldPicture(String pictureUrl){
        StorageReference referencia = FirebaseStorage.getInstance().getReference("profile_img/"+pictureUrl);


        Log.d("DELETE", "deleteOldPicture: " + pictureUrl);
            referencia.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(DadosPessoaisEditSaveActivity.this, "Imagem antiga deletada", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d("DELETE", "deleteOldPicture: " + e.getMessage());
                    Toast.makeText(DadosPessoaisEditSaveActivity.this, "Falha em deletar a imagem + " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

    }
    private void uploadFile(String pictureUrl){
        upload = new UploadHelper();
        upload.uploadContent(getFileExtension(mImageURI),mImageURI,pictureUrl,"profile_img");
        candidato.setProfilePicture(pictureUrl + "."+getFileExtension(mImageURI));

    }



    private void openFile(){
        Intent it = new Intent();
        it.setType("image/*");
        it.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(it, PICK_IMAGE_REQUEST);


    }

    private String getFileExtension(Uri uri){
        ContentResolver resolver = getContentResolver();
        MimeTypeMap mtype = MimeTypeMap.getSingleton();
        return  mtype.getExtensionFromMimeType(resolver.getType(uri));
    }

    private Bitmap getFoto(){
        InputStream ip;
        StorageReference r = FirebaseStorage.getInstance().getReference();

       return  null;
    }
    private String getUrlPath(){
        String path = "";
        if(mImageURI != null){
         path = candidato.getProfilePicture()+"."+getFileExtension(mImageURI);
        }
        return path;
    }

    public boolean validateForm(){

        boolean validado = false;
        if(mEdtNome.getText().toString().equals("") || mEdtDataNascimento.getText().toString().equals("") ||
           mEdtEstadoCivil.getText().toString().equals("") || mEdtNacionalidade.getText().toString().equals("")||
           mEdtNaturalidade.getText().toString().equals("")){
            mEdtNome.setError("Por favor insira um nome");

        } else {
            validado = true;
        }

        return validado;
    }

    public int getPosition(String value){
        int position = 0;
        switch (value) {
            case "MASCULINO":  position = 0; break;
            case "FEMININO":  position = 1; break;
            case "TRANS":  position = 2; break;
        }
        return  position;
    }

    private void setupToolbar(){
        Toolbar tollbar = findViewById(R.id.toolbar);
        tollbar.setTitle("Edição de dados");
        setActionBar(tollbar);
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }
}

