package br.com.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class Contato implements Parcelable {

    private String email;
    private String celular;
    private String telefoneFixo;
    private String celularRecado;
    private String falarCom;
    private Date   created_At;
    private Date   updated_At;

    public String getFalarCom() {
        return falarCom;
    }

    public void setFalarCom(String falarCom) {
        this.falarCom = falarCom;
    }

    public Date getCreated_At() {
        return created_At;
    }

    public void setCreated_At(Date created_At) {
        this.created_At = created_At;
    }

    public Date getUpdated_At() {
        return updated_At;
    }

    public void setUpdated_At(Date updated_At) {
        this.updated_At = updated_At;
    }

    public  Contato(){

    }




    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getTelefoneFixo() {
        return telefoneFixo;
    }

    public void setTelefoneFixo(String telefoneFixo) {
        this.telefoneFixo = telefoneFixo;
    }

    public String getCelularRecado() {
        return celularRecado;
    }

    public void setCelularRecado(String celularRecado) {
        this.celularRecado = celularRecado;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(email);
        dest.writeString(celular);
        dest.writeString(telefoneFixo);
        dest.writeString(celularRecado);
        dest.writeString(falarCom);
        dest.writeLong(created_At.getTime());
        dest.writeLong(updated_At.getTime());
    }


    protected Contato(Parcel in) {
        email = in.readString();
        celular = in.readString();
        telefoneFixo = in.readString();
        celularRecado = in.readString();
        falarCom = in.readString();
        created_At = new Date(in.readLong());
        updated_At = new Date(in.readLong());
    }

    public static final Creator<Contato> CREATOR = new Creator<Contato>() {
        @Override
        public Contato createFromParcel(Parcel in) {
            return new Contato(in);
        }

        @Override
        public Contato[] newArray(int size) {
            return new Contato[size];
        }
    };


    public  void fillContato(){
        this.updated_At = new Date();
        this.created_At = new Date();
        this.celular = "";
        this.celularRecado ="";
        this.telefoneFixo = "";
        this.email ="";
        this.telefoneFixo = "";
    }
}
