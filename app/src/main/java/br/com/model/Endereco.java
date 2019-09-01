package br.com.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Endereco implements Parcelable {

    private String idEndereco;
    private String idCandidato;
    private String rua;
    private String cidade;
    private String estado;
    private String bairro;
    private String cep;
    private String numero;
    private String referencia;

    public Endereco(){

    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(String idEndereco) {
        this.idEndereco = idEndereco;
    }

    public String getIdCandidato() {
        return idCandidato;
    }

    public void setIdCandidato(String idCandidato) {
        this.idCandidato = idCandidato;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }


    @Override
    public String toString() {
        return "Endereco{" +
                "idEndereco='" + idEndereco + '\'' +
                ", idCandidato='" + idCandidato + '\'' +
                ", rua='" + rua + '\'' +
                ", cidade='" + cidade + '\'' +
                ", estado='" + estado + '\'' +
                ", bairro='" + bairro + '\'' +
                ", cep='" + cep + '\'' +
                ", numero='" + numero + '\'' +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.idEndereco);
        dest.writeString(this.idCandidato);
        dest.writeString(this.rua);
        dest.writeString(this.cidade);
        dest.writeString(this.estado);
        dest.writeString(this.bairro);
        dest.writeString(this.cep);
        dest.writeString(this.numero);
        dest.writeString(this.referencia);
    }

    protected Endereco(Parcel in) {
        this.idEndereco = in.readString();
        this.idCandidato = in.readString();
        this.rua = in.readString();
        this.cidade = in.readString();
        this.estado = in.readString();
        this.bairro = in.readString();
        this.cep = in.readString();
        this.numero = in.readString();
        this.referencia = in.readString();
    }

    public static final Creator<Endereco> CREATOR = new Creator<Endereco>() {
        @Override
        public Endereco createFromParcel(Parcel source) {
            return new Endereco(source);
        }

        @Override
        public Endereco[] newArray(int size) {
            return new Endereco[size];
        }
    };
}
