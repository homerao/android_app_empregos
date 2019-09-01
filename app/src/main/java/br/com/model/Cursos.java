package br.com.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class Cursos implements Parcelable {
    private String nomeCurso;
    private String descriçãoCurso;
    private String ementa;
    private int qtdHoras;
    private Date dataInicio;
    private Date dataTermino;

    protected Cursos(Parcel in) {
        nomeCurso = in.readString();
        descriçãoCurso = in.readString();
        ementa = in.readString();
        qtdHoras = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nomeCurso);
        dest.writeString(descriçãoCurso);
        dest.writeString(ementa);
        dest.writeInt(qtdHoras);
    }

    public static final Creator<Cursos> CREATOR = new Creator<Cursos>() {
        @Override
        public Cursos createFromParcel(Parcel in) {
            return new Cursos(in);
        }

        @Override
        public Cursos[] newArray(int size) {
            return new Cursos[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    public Cursos() {

    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    public String getDescriçãoCurso() {
        return descriçãoCurso;
    }

    public void setDescriçãoCurso(String descriçãoCurso) {
        this.descriçãoCurso = descriçãoCurso;
    }

    public String getEmenta() {
        return ementa;
    }

    public void setEmenta(String ementa) {
        this.ementa = ementa;
    }

    public int getQtdHoras() {
        return qtdHoras;
    }

    public void setQtdHoras(int qtdHoras) {
        this.qtdHoras = qtdHoras;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(Date dataTermino) {
        this.dataTermino = dataTermino;
    }

    @Override
    public String toString() {
        return "Cursos{" +
                "nomeCurso='" + nomeCurso + '\'' +
                ", descriçãoCurso='" + descriçãoCurso + '\'' +
                ", ementa='" + ementa + '\'' +
                ", qtdHoras=" + qtdHoras +
                ", dataInicio=" + dataInicio +
                ", dataTermino=" + dataTermino +
                '}';
    }


}
