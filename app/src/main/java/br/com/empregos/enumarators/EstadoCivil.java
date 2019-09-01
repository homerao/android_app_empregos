package br.com.empregos.enumarators;

public enum EstadoCivil {

    CASADO("casado(a)"), SOLTEIRO("solteiro(a)"), AMAZIADO("amasiado(a)"), SEPARADO("separado(a)"), VIUVO("viúvo(a)");

    private String valor;
    EstadoCivil(String estadoCivil){
        this.valor = estadoCivil;
    }

    public String getValor(){
        return  this.valor;
    }
}
