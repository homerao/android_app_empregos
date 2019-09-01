package br.com.util;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

public class DownloadHelper {


    private FirebaseStorage storage;
    private StorageReference reference;
    private static final String TAG= "DownloadHelper";

    public FirebaseStorage getStorage() {
        return storage;
    }

    public void setStorage(FirebaseStorage storage) {
        this.storage = storage;
    }

    public StorageReference getReference() {
        return reference;
    }

    public void setReference(StorageReference reference) {
        this.reference = reference;
    }

    public DownloadHelper() {
    }

    public File donwloadPicture(String pictureName, String folderName){
        storage = FirebaseStorage.getInstance();
        reference = storage.getReference(folderName);
        File file = null;
          try {
              File.createTempFile("imagens",pictureName);
              reference.getFile(file).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                  @Override
                  public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                      Log.d(TAG, "onSuccess: ");
                  }
              }).addOnProgressListener(new OnProgressListener<FileDownloadTask.TaskSnapshot>() {
                  @Override
                  public void onProgress(FileDownloadTask.TaskSnapshot taskSnapshot) {
                      Log.d(TAG, "onProgress: ");
                  }
              }).addOnFailureListener(new OnFailureListener() {
                  @Override
                  public void onFailure(@NonNull Exception e) {
                      Log.d(TAG, "onFailure: " + e.getMessage());
                  }
              });

          }catch (IOException e){

          }
          return file;
    }
}
