package br.com.empregos;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;

import br.com.empregos.dao.CandidatoDAO;
import br.com.empregos.partial.ContatoEditSaveActivity;
import br.com.empregos.partial.DadosPessoaisEditSaveActivity;
import br.com.interfaces.SetupControlls;
import br.com.interfaces.SetupToolbar;
import br.com.model.Candidato;
import br.com.model.Contato;
import br.com.util.DateConverterUtil;
import br.com.util.DownloadHelper;
import de.hdodenhof.circleimageview.CircleImageView;

public class DadosPessoaisViewActivity extends AbstractActivity implements View.OnClickListener, SetupControlls{


    private static final String TAG = "DadosPessoaisVie";
    private CandidatoDAO dao;
    private Candidato candidato = new Candidato();
    private Contato contato = new Contato();
    private DatabaseReference candidatoRef;
    private Button btnVoltar;

    private Uri mImageURI;
    private CircleImageView mProfileImage;
    private Button mBtnFoto;
    private Button mBtnInfoPessoal;
    private StorageReference mStorage;
    private DatabaseReference mDatabase;
    private FirebaseUser muser;
    private FirebaseAuth mAuth;
    private FirebaseDatabase fdatabase;

    private TextView edtNome;
    private TextView edtDataNascto;
    private TextView edtSexo;
    private TextView edtNaturalidade;
    private TextView edtNacionalidade;
    private TextView edtEstadoCivil;
    private Button btnContato;

    private TextView edtEmail;
    private TextView edtCelular;
    private TextView edtTeleFixo;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_details);


        setUpFirebaseOnce();



        setup();
        setupToolbar("Dados Pessoais");
        fillUserData(candidato.getProfilePicture());











    }

    @Override
    protected void onStart() {
        super.onStart();


    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("\n\n TESTE ON PAUSE", "onPause: ");
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.d("\n\nTESTE ON RESUME", "onResume: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        candidato = null;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }



    @Override
    public void onClick(View v) {
        if(v == mBtnFoto){
            Bundle bd = new Bundle();

            bd.putParcelable("candidato", candidato);
            openIntent(DadosPessoaisEditSaveActivity.class, bd);

        } else if (v == btnVoltar){
            finish();
        } else if (v == mBtnInfoPessoal){
            Bundle bd = new Bundle();
            if(candidato == null){
                candidato = new Candidato();
                candidato.fillCandidato();
            }
            bd.putParcelable("candidato", candidato);
              openIntent(DadosPessoaisEditSaveActivity.class, bd);
        } else if (v == btnContato){
            Bundle bd = new Bundle();
            Bundle b = new Bundle();
            bd.putParcelable("Contato", contato);
            b.putBundle("contatoBundle", bd);
            openIntent(ContatoEditSaveActivity.class, b);
        }
    }




    private void downloadPictureProfile(String pictureUrl){
     try {

         StorageReference ref = FirebaseStorage.getInstance().getReference("profile_img/");
         Log.d("###DOWNLOAD_PICTURE###", "downloadPictureProfile: " + candidato.getProfilePicture());

         ref.child(pictureUrl).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
             @Override
             public void onSuccess(Uri uri) {
                 final Uri novaUri = uri;
                 Handler hand = new Handler();
                 hand.postDelayed(new Runnable() {
                     @Override
                     public void run() {
                         Log.d("###DOWNLOAD_PICTURE###", "uri da foto: " + novaUri);
                         Glide.with(getBaseContext()).load(novaUri).into(mProfileImage);
                         Log.d("###DOWNLOAD_PICTURE###", "uri da foto: " + novaUri);
                     }
                 }, 100);

             }
         });
     }catch(Exception e){
         Log.e("###DOWNLOAD_PICTURE###", "downloadPictureProfile: ", e);
     }
    }

    private void openIntent(Class c, Bundle extras){
        Intent it = new Intent(getApplicationContext(),c);
        it.putExtras(extras);
        startActivity(it);
    }

    @Override
    public void setup() {

        mProfileImage = findViewById(R.id.imgPerfil);
        mBtnFoto = findViewById(R.id.btnAddImagem);
        mBtnInfoPessoal = findViewById(R.id.btnInfoPessoal);
        btnContato = findViewById(R.id.btnOpenContatoEdit);
        edtNome = findViewById(R.id.txRGView);
        edtDataNascto = findViewById(R.id.edtNascimentoView);
        edtSexo = findViewById(R.id.txOrgExpedView);
        edtNaturalidade = findViewById(R.id.txCpfView);
        edtNacionalidade = findViewById(R.id.txTituloEleView);

        edtEmail = findViewById(R.id.txZonaView);
        edtCelular = findViewById(R.id.txCartMilitarView);
        edtTeleFixo = findViewById(R.id.edtTelefoneView);

        mBtnInfoPessoal.setOnClickListener(this);
        btnContato.setOnClickListener(this);



    }

    private void downloadFile(String fileName){
        download = new DownloadHelper();
        try {
            FileInputStream f = new FileInputStream(fileName);
            File file = new File("/sdcard:");

        }catch (FileNotFoundException e){}



    }



    public void setUpFirebaseOnce(){
        mAuth = FirebaseAuth.getInstance();
        muser = mAuth.getCurrentUser();

        fdatabase = FirebaseDatabase.getInstance();
        candidatoRef = fdatabase.getReference("Candidatos/"+"Candidato-"+muser.getUid());
        mStorage = FirebaseStorage.getInstance().getReference("profile_img");
        mDatabase = fdatabase.getReference("Candidatos/"+"Candidato-"+muser.getUid());


    }
    public  void fillUserData(final String pictureProfile){
        mAuth = FirebaseAuth.getInstance();
        muser = mAuth.getCurrentUser();
        candidato = new Candidato();


        mDatabase.child("Candidato-"+muser.getUid());
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d("DATASNAPSHOT", "onDataChange: " + dataSnapshot.toString());
                candidato = dataSnapshot.getValue(Candidato.class);

                if(candidato == null){
                    candidato = new Candidato();
                }
                if(candidato.getUpdated_At() == null){
                    candidato.setUpdated_At(new Date());
                }
                if(candidato.getCreated_At() == null){
                    candidato.setCreated_At(new Date());
                }
                Handler hand = new Handler();
                hand.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                   try{
                       edtNome.setText(candidato.getInformacoes().getNome().toUpperCase());
                       edtDataNascto.setText(DateConverterUtil.dateToString(candidato.getInformacoes().getDataNascimento()));
                       edtSexo.setText(candidato.getInformacoes().getSexo().toUpperCase());
                       edtNaturalidade.setText(candidato.getInformacoes().getNaturalidade().toUpperCase());
                       edtNacionalidade.setText(candidato.getInformacoes().getNacionalidade().toUpperCase());
                       Log.d("", "run: ");
                       downloadPictureProfile(candidato.getProfilePicture());
                       Log.d("PICTURE", "CANDIDATO PROFILEPICTURE: "+ candidato.getProfilePicture());
                       loadContato();

                   }catch(NullPointerException e){

                   }

                    }
                }, 100);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    public void loadContato(){
        FirebaseDatabase db;
        DatabaseReference re;
        db = mDatabase.getDatabase();
        re = db.getReference("Contatos");

        re.child("Contato-"+candidato.getIdCandidato())
        .addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                contato =  dataSnapshot.getValue(Contato.class);
                Handler h = new Handler();

                    if(contato == null){
                        h.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                edtEmail.setText("");
                                edtCelular.setText("");
                                edtTeleFixo.setText("");


                            }
                        }, 100);
                    } else {
                        h.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                edtEmail.setText(contato.getEmail().toUpperCase());
                                edtCelular.setText(contato.getCelular().toUpperCase());
                                edtTeleFixo.setText(contato.getTelefoneFixo().toUpperCase());


                            }
                        }, 100);
                    }


                Log.d("\n\n\n LOADCONTATO", "onDataChange: "+ dataSnapshot.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    public void loadProfilePicture(final String url){
        StorageReference r = FirebaseStorage.getInstance().getReference("profile_img");
        final File foto;
        try {
            foto = File.createTempFile("picture", url.substring(url.length() -4));
            Log.d("XXX DOWNLOAD IMAGEM XXX", "loadProfilePicture: "+ url.substring(url.length() - 4));
            r.child(url).getFile(foto).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {

                    Handler hand = new Handler();
                    hand.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Glide.with(getApplicationContext()).load(foto.toURI()).into(mProfileImage);

                        }
                    }, 3000);
                }
            });
        } catch (IOException| IndexOutOfBoundsException e) {
            e.printStackTrace();
        }



    }

    public void openExplorer(String intentType, String action, String conteudo){
        Intent it = new Intent();
        it.setType(intentType);
        it.putExtra(Intent.EXTRA_TEXT, conteudo);
        it.setAction(action);
        startActivity(it);

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
}
