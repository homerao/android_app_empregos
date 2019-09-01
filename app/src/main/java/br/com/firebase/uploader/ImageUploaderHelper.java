package br.com.firebase.uploader;

public class ImageUploaderHelper {
    private String imgName;
    private String imageUri;

    ImageUploaderHelper(String name, String uri){
        if(name.trim().equals("")){
           name = "sem nome";
        }
        this.imgName = name;
        this.imageUri = uri;
    }


    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }


}
