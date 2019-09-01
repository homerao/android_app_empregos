package br.com.empregos.enumarators;

public enum Sexo {

    MASCULINO("M"), FEMININO("F"),TRANS("T");

    private String valor;


    Sexo(String valor){
        this.valor = valor;
    }

    public String getValor(){
        return  this.valor;
    }


}
