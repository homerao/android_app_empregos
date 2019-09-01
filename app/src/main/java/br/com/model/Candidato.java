package br.com.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Candidato extends AbstractEntity implements Parcelable {

    private String idCandidato;
    private String profilePicture;
    private InformaCoesPessoais informacoes;
    private Endereco endereco;
    private Formacao formacao;
    private Documento documento;
    private List<FormacaoComplementar> listFormacaoComplementar;
    private List<ExperienciaProfissional> listExperienciaProfissional;
    private Curriculo curriculo;
    private List<Treinamento> listTreinamentos;
    private Date created_At;
    private Date updated_At;

    public Candidato(String idCandidato, String profilePicture, InformaCoesPessoais informacoes, Endereco endereco, Formacao formacao, Documento documento, List<FormacaoComplementar> listFormacaoComplementar, List<ExperienciaProfissional> listExperienciaProfissional, Curriculo curriculo, List<Treinamento> listTreinamentos, Date created_At, Date updated_At) {
        this.idCandidato = idCandidato;
        this.profilePicture = profilePicture;
        this.informacoes = informacoes;
        this.endereco = endereco;
        this.formacao = formacao;
        this.documento = documento;
        this.listFormacaoComplementar = listFormacaoComplementar;
        this.listExperienciaProfissional = listExperienciaProfissional;
        this.curriculo = curriculo;
        this.listTreinamentos = listTreinamentos;
        this.created_At = created_At;
        this.updated_At = updated_At;
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

    public Candidato(){

    }








    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public InformaCoesPessoais getInformacoes() {
        return informacoes;
    }

    public void setInformacoes(InformaCoesPessoais informacoes) {
        this.informacoes = informacoes;
    }


    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Formacao getFormacao() {
        return formacao;
    }

    public void setFormacao(Formacao formacao) {
        this.formacao = formacao;
    }

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    public List<FormacaoComplementar> getListFormacaoComplementar() {
        return listFormacaoComplementar;
    }

    public void setListFormacaoComplementar(List<FormacaoComplementar> listFormacaoComplementar) {
        this.listFormacaoComplementar = listFormacaoComplementar;
    }

    public List<ExperienciaProfissional> getListExperienciaProfissional() {
        return listExperienciaProfissional;
    }

    public void setListExperienciaProfissional(List<ExperienciaProfissional> listExperienciaProfissional) {
        this.listExperienciaProfissional = listExperienciaProfissional;
    }

    public Curriculo getCurriculo() {
        return curriculo;
    }

    public void setCurriculo(Curriculo curriculo) {
        this.curriculo = curriculo;
    }

    public List<Treinamento> getListTreinamentos() {
        return listTreinamentos;
    }

    public void setListTreinamentos(List<Treinamento> listTreinamentos) {
        this.listTreinamentos = listTreinamentos;
    }


    public String getIdCandidato() {
        return idCandidato;
    }

    public void setIdCandidato(String idCandidato) {
        this.idCandidato = idCandidato;
    }



    public void fillCandidato(){
        this.setProfilePicture("");
        this.setIdCandidato("");
        this.setCreated_At(new Date());
        this.setUpdated_At(new Date());
        InformaCoesPessoais p = new InformaCoesPessoais();
        p.setDataNascimento(null);
        p.setEstadoCivil("");
        p.setNacionalidade("");
        p.setNaturalidade("");
        p.setSexo("");
        p.setNome("");

        this.setInformacoes(p);
    }

    @Override
    public String toString() {
        return "Candidato{" +
                "idCandidato='" + idCandidato + '\'' +
                ", profilePicture='" + profilePicture + '\'' +
                ", informacoes=" + informacoes.toString() +
                ", endereco=" + endereco +
                ", formacao=" + formacao +
                ", documento=" + documento +
                ", listFormacaoComplementar=" + listFormacaoComplementar +
                ", listExperienciaProfissional=" + listExperienciaProfissional +
                ", curriculo=" + curriculo +
                ", listTreinamentos=" + listTreinamentos +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.idCandidato);
        dest.writeString(this.profilePicture);
        dest.writeParcelable(this.informacoes, flags);

        dest.writeList(this.listTreinamentos);
        dest.writeLong(this.created_At != null ? this.created_At.getTime() : -1);
        dest.writeLong(this.updated_At != null ? this.updated_At.getTime() : -1);
    }

    protected Candidato(Parcel in) {
        this.idCandidato = in.readString();
        this.profilePicture = in.readString();
        this.informacoes = in.readParcelable(InformaCoesPessoais.class.getClassLoader());
        long tmpCreated_At = in.readLong();
        this.created_At = tmpCreated_At == -1 ? null : new Date(tmpCreated_At);
        long tmpUpdated_At = in.readLong();
        this.updated_At = tmpUpdated_At == -1 ? null : new Date(tmpUpdated_At);
    }

    public static final Creator<Candidato> CREATOR = new Creator<Candidato>() {
        @Override
        public Candidato createFromParcel(Parcel source) {
            return new Candidato(source);
        }

        @Override
        public Candidato[] newArray(int size) {
            return new Candidato[size];
        }
    };
}
