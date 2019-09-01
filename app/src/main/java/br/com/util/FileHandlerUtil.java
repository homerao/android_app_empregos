package br.com.util;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

public class FileHandlerUtil {

    private SharedPreferences preferences;

    public SharedPreferences getPreferences() {
        return preferences;
    }

    public void setPreferences(SharedPreferences preferences) {
        this.preferences = preferences;
    }

    public FileHandlerUtil(SharedPreferences pref) {
        this.preferences = pref;
    }

    public Bitmap getUserPicture(){
        Bitmap b = null;
        if(preferences.getString("userPicture","") != null){
            String userPicture = preferences.getString("userPicture","");
            b = BitmapFactory.decodeFile(userPicture);

        }

        return b;
    }


    public void saveUserPicture(Uri uri){

    }

    public boolean pictureExists(){
        return  true;
    }

    public boolean directoryExists(String directory){
        return true;
    }
}
