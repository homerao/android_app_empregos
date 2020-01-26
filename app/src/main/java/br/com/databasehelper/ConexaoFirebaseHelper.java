package br.com.databasehelper;



import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.NonNull;

public class ConexaoFirebaseHelper {

    private static FirebaseAuth auth;
    private static FirebaseUser fuser;
    private static FirebaseAuth.AuthStateListener listener;
    private static FirebaseDatabase  database;
    private static DatabaseReference reference;

    private ConexaoFirebaseHelper(){

    }


    public static FirebaseAuth getFirebaseAuth(){
          if(auth == null){
            inicializaFirebaseAuth();
          }
          return auth;
    }

    private static void inicializaFirebaseAuth(){
        auth = FirebaseAuth.getInstance();
        listener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
               FirebaseUser user = auth.getCurrentUser();
               if(user != null){
                   fuser = user;
               }
            }
        };
        auth.addAuthStateListener(listener);
    }

    public static FirebaseUser getFirebaseUser(){
           return getFirebaseAuth().getCurrentUser();
    }

    public static void logoutFirebase(){
          auth.signOut();
    }
    public static DatabaseReference getDatabaseReference(String collection){
           database = FirebaseDatabase.getInstance();
           reference = database.getReference(collection);
           return  reference;
    }

    public static DatabaseReference getDatabaseReference(String collection, String child){
        database = FirebaseDatabase.getInstance();
        reference = database.getReference(collection).child(child);
        return  reference;
    }
}
