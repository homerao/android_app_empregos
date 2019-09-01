package br.com.databasehelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import br.com.model.Candidato;

public class SQLiteDAO {

    private SQLiteDatabase db;


    public SQLiteDAO(Context ctx){
        SQLiteConnectionHelper helper = new SQLiteConnectionHelper(ctx);
        db = helper.getWritableDatabase();
    }


    public void inserirUsuario(Candidato user){
        ContentValues values = new ContentValues();


       db.insert("usuario", null,  values);

    }


    public void excluirUsuario(int userId){
        String [] args = {""+ userId};

      db.delete("usuario", "_id = ?",args);
    }

    public String updateToken(int userId, String token){

        ContentValues values = new ContentValues();
          values.put("token", token);
        db.update("usuario", values, "_id = ?", new String[] {""+userId} );

        return "1";
    }


}
