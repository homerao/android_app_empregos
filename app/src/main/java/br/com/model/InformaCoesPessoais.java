package br.com.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.ParseException;
import java.util.Date;

import br.com.util.DateConverterUtil;

public class InformaCoesPessoais implements Parcelable {


    private String nome;
    private String sexo;
    private Date dataNascimento;
    private String naturalidade;
    private String nacionalidade;
    private String estadoCivil;


    public InformaCoesPessoais(){

    }

    protected InformaCoesPessoais(Parcel in) {
        nome = in.readString();
        sexo = in.readString();
        naturalidade = in.readString();
        nacionalidade = in.readString();
        estadoCivil = in.readString();
        dataNascimento = new Date(in.readLong());

    }

    public static final Creator<InformaCoesPessoais> CREATOR = new Creator<InformaCoesPessoais>() {
        @Override
        public InformaCoesPessoais createFromParcel(Parcel in) {
            return new InformaCoesPessoais(in);
        }

        @Override
        public InformaCoesPessoais[] newArray(int size) {
            return new InformaCoesPessoais[size];
        }
    };

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getNaturalidade() {
        return naturalidade;
    }

    public void setNaturalidade(String naturalidade) {
        this.naturalidade = naturalidade;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nome);
        dest.writeString(sexo);
        dest.writeString(naturalidade);
        dest.writeString(nacionalidade);
        dest.writeString(estadoCivil);
        dest.writeLong(dataNascimento.getTime());
    }

    @Override
    public String toString() {
        return "InformaCoesPessoais{" +
                "nome='" + nome + '\'' +
                ", sexo='" + sexo + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", naturalidade='" + naturalidade + '\'' +
                ", nacionalidade='" + nacionalidade + '\'' +
                ", estadoCivil='" + estadoCivil + '\'' +
                '}';
    }
}
