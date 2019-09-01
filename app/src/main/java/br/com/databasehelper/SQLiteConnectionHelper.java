package br.com.databasehelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteConnectionHelper extends SQLiteOpenHelper {

    private static final String NOME_BD = "USUARIO";
    private static final int VERSAO_BD = 1;


    public SQLiteConnectionHelper(Context ctx){
        super(ctx, NOME_BD,null, VERSAO_BD);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
     db.execSQL("CREATE TABLE USUARIO (_id INTEGER PRIMARY KEY autoincrement, NOME VARCHAR(50), EMAIL VARCHAR(100), TOKEN TEXT NOT NULL, ACTIVE INT ) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
