package br.com.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class Documento implements Parcelable {

    private String idDocumento;
    private String idCandidato;
    private String rg;
    private Date dataExpedicaoRG;
    private String orgaoExpRG;
    private String cpf;
    private String tituloEleitor;
    private String zonaSecao;
    private String carteiraMilitar;
    private String pis;
    private String habilitacao;
    private String categoriaHabilitacao;
    private String carteiraProfissional;
    private String serieCarteiraProfissional;
    private Date dataValidadeHabilitacao;

    @Override
    public String toString() {
        return "Documento{" +
                "idDocumento='" + idDocumento + '\'' +
                ", idCandidato='" + idCandidato + '\'' +
                ", rg='" + rg + '\'' +
                ", dataExpedicaoRG=" + dataExpedicaoRG +
                ", orgaoExpRG='" + orgaoExpRG + '\'' +
                ", cpf='" + cpf + '\'' +
                ", tituloEleitor='" + tituloEleitor + '\'' +
                ", carteiraMilitar='" + carteiraMilitar + '\'' +
                ", pis='" + pis + '\'' +
                ", habilitacao='" + habilitacao + '\'' +
                ", categoriaHabilitacao='" + categoriaHabilitacao + '\'' +
                ", carteiraProfissional='" + carteiraProfissional + '\'' +
                ", serieCarteiraProfissional='" + serieCarteiraProfissional + '\'' +
                ", dataValidadeHabilitacao=" + dataValidadeHabilitacao +
                '}';
    }

    public String getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(String idDocumento) {
        this.idDocumento = idDocumento;
    }

    public String getIdCandidato() {
        return idCandidato;
    }

    public void setIdCandidato(String idCandidato) {
        this.idCandidato = idCandidato;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public Date getDataExpedicaoRG() {
        return dataExpedicaoRG;
    }

    public void setDataExpedicaoRG(Date dataExpedicaoRG) {
        this.dataExpedicaoRG = dataExpedicaoRG;
    }

    public String getOrgaoExpRG() {
        return orgaoExpRG;
    }

    public void setOrgaoExpRG(String orgaoExpRG) {
        this.orgaoExpRG = orgaoExpRG;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTituloEleitor() {
        return tituloEleitor;
    }

    public void setTituloEleitor(String tituloEleitor) {
        this.tituloEleitor = tituloEleitor;
    }

    public String getZonaSecao() {
        return zonaSecao;
    }

    public void setZonaSecao(String zonaSecao) {
        this.zonaSecao = zonaSecao;
    }

    public String getCarteiraMilitar() {
        return carteiraMilitar;
    }

    public void setCarteiraMilitar(String carteiraMilitar) {
        this.carteiraMilitar = carteiraMilitar;
    }

    public String getPis() {
        return pis;
    }

    public void setPis(String pis) {
        this.pis = pis;
    }

    public String getHabilitacao() {
        return habilitacao;
    }

    public void setHabilitacao(String habilitacao) {
        this.habilitacao = habilitacao;
    }

    public String getCategoriaHabilitacao() {
        return categoriaHabilitacao;
    }

    public void setCategoriaHabilitacao(String categoriaHabilitacao) {
        this.categoriaHabilitacao = categoriaHabilitacao;
    }

    public String getCarteiraProfissional() {
        return carteiraProfissional;
    }

    public void setCarteiraProfissional(String carteiraProfissional) {
        this.carteiraProfissional = carteiraProfissional;
    }

    public String getSerieCarteiraProfissional() {
        return serieCarteiraProfissional;
    }

    public void setSerieCarteiraProfissional(String serieCarteiraProfissional) {
        this.serieCarteiraProfissional = serieCarteiraProfissional;
    }

    public Date getDataValidadeHabilitacao() {
        return dataValidadeHabilitacao;
    }

    public void setDataValidadeHabilitacao(Date dataValidadeHabilitacao) {
        this.dataValidadeHabilitacao = dataValidadeHabilitacao;
    }

    public Documento() {

    }
    public void fillDocumento(){
        this.idDocumento = "";
        this.idCandidato = "";
        this.rg = "";
        this.dataExpedicaoRG =new Date();
        this.orgaoExpRG = "";
        this.cpf = "";
        this.tituloEleitor = "";
        this.carteiraMilitar = "";
        this.pis = "";
        this.habilitacao = "";
        this.categoriaHabilitacao = "";
        this.carteiraProfissional = "";
        this.serieCarteiraProfissional = "";
        this.dataValidadeHabilitacao =  new Date();
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.idDocumento);
        dest.writeString(this.idCandidato);
        dest.writeString(this.rg);
        dest.writeLong(this.dataExpedicaoRG != null ? this.dataExpedicaoRG.getTime() : -1);
        dest.writeString(this.orgaoExpRG);
        dest.writeString(this.cpf);
        dest.writeString(this.tituloEleitor);
        dest.writeString(this.zonaSecao);
        dest.writeString(this.carteiraMilitar);
        dest.writeString(this.pis);
        dest.writeString(this.habilitacao);
        dest.writeString(this.categoriaHabilitacao);
        dest.writeString(this.carteiraProfissional);
        dest.writeString(this.serieCarteiraProfissional);
        dest.writeLong(this.dataValidadeHabilitacao != null ? this.dataValidadeHabilitacao.getTime() : -1);
    }

    protected Documento(Parcel in) {
        this.idDocumento = in.readString();
        this.idCandidato = in.readString();
        this.rg = in.readString();
        long tmpDataExpedicaoRG = in.readLong();
        this.dataExpedicaoRG = tmpDataExpedicaoRG == -1 ? null : new Date(tmpDataExpedicaoRG);
        this.orgaoExpRG = in.readString();
        this.cpf = in.readString();
        this.tituloEleitor = in.readString();
        this.zonaSecao = in.readString();
        this.carteiraMilitar = in.readString();
        this.pis = in.readString();
        this.habilitacao = in.readString();
        this.categoriaHabilitacao = in.readString();
        this.carteiraProfissional = in.readString();
        this.serieCarteiraProfissional = in.readString();
        long tmpDataValidadeHabilitacao = in.readLong();
        this.dataValidadeHabilitacao = tmpDataValidadeHabilitacao == -1 ? null : new Date(tmpDataValidadeHabilitacao);
    }

    public static final Creator<Documento> CREATOR = new Creator<Documento>() {
        @Override
        public Documento createFromParcel(Parcel source) {
            return new Documento(source);
        }

        @Override
        public Documento[] newArray(int size) {
            return new Documento[size];
        }
    };
}
