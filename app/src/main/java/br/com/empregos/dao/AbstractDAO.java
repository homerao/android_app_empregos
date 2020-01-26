package br.com.empregos.dao;

import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import androidx.annotation.NonNull;
import br.com.empregos.exception.RegistroInexistenteException;
import br.com.model.Candidato;

public abstract class AbstractDAO<T> {

    protected Class<T>  tClass;
    protected FirebaseDatabase database;
    protected FirebaseStorage storage;
    protected DatabaseReference ref;
    protected String storageCollection;
    protected String databaseCollection;
    protected FirebaseAuth mAuth;
    protected FirebaseUser mUser;
    protected String result;
    protected static DataSnapshot snapshot;
    protected static String resultado;

    public AbstractDAO(Class<T> tClass, String dbCollection, String storeCollection) {
        this.tClass = tClass;
        database = FirebaseDatabase.getInstance();

        storage = FirebaseStorage.getInstance();
        databaseCollection = dbCollection;
        storageCollection = storeCollection;
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        ref = database.getReference(databaseCollection+"/").child(this.tClass.getSimpleName() + "-" + mUser.getUid());
    }

    public AbstractDAO(Class<T> tClass, String databaseCollection) {
        this.tClass = tClass;
        this.databaseCollection = databaseCollection;
        this.database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        this.ref = database.getReference(databaseCollection).child(this.tClass.getSimpleName() + "-" +mUser.getUid());
    }

    public String saveOrUpdate(final T obj, String pk) {


        ref.setValue(obj).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d("######SUCCESS########", "onDataChange: ");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("######FAILURE#######", "onDataChange: " );
            }
        });



        return result;
    }





    public T buscarUm( String pkValue) throws RegistroInexistenteException {
        T obj;

        ref.keepSynced(true);

          ref.addValueEventListener(new ValueEventListener() {
              @Override
              public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               snapshot =  dataSnapshot;
              }

              @Override
              public void onCancelled(@NonNull DatabaseError databaseError) {
                  Log.d("ERRO_BUSCAR_UM", "onCancelled: " + databaseError.getMessage());
              }
          });

          if(snapshot == null){
              throw  new RegistroInexistenteException("Não existem dados");
          } else {
              obj = (T) snapshot.getValue(this.tClass);

          }


       return obj ;



    }



    public T buscarPorPK( String pkValue,  String registryName) throws RegistroInexistenteException {
        T obj;
      Query query  = ref.child(registryName + "-" + pkValue);

        query.keepSynced(true);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                snapshot = dataSnapshot;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        if(snapshot == null){
            Log.d("impossível", "buscarPorPK: objeto não existe ");
            throw  new RegistroInexistenteException("Dados inexistentes");

        } else {
            Log.d("success", "buscarPorPK: objeto setado ");
            obj = (T) snapshot.getValue(tClass);
        }


        return obj;
    }
}
