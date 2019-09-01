package br.com.empregos.enumarators;

public enum TYPE_CONTENT {
    PROFILE_IMG("profile_img/"), PRODUCT_IMG("products/");

    TYPE_CONTENT(String profile_img) {
    }


    public  String toString() {
        return PROFILE_IMG.toString();
    }

    TYPE_CONTENT() {
    }
}
