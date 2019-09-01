package br.com.model;

import android.provider.ContactsContract;

import java.util.Date;
import java.util.List;

public class ExperienciaProfissional {


    private String idExperiencia;
    private String idCandidato;
    private String nomeEmpresa;
    private String telefoneEmpresa;
    private String gestor;
    private Date dataInicio;
    private Date dataFim;
    private String motivoSaida;
    private List<String> cargosOcupados;
    private List<String> atividadesDesempenhadas;

    public ExperienciaProfissional() {

    }

    public String getIdExperiencia() {
        return idExperiencia;
    }

    public void setIdExperiencia(String idExperiencia) {
        this.idExperiencia = idExperiencia;
    }

    public String getIdCandidato() {
        return idCandidato;
    }

    public void setIdCandidato(String idCandidato) {
        this.idCandidato = idCandidato;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public String getTelefoneEmpresa() {
        return telefoneEmpresa;
    }

    public void setTelefoneEmpresa(String telefoneEmpresa) {
        this.telefoneEmpresa = telefoneEmpresa;
    }

    public String getGestor() {
        return gestor;
    }

    public void setGestor(String gestor) {
        this.gestor = gestor;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public String getMotivoSaida() {
        return motivoSaida;
    }

    public void setMotivoSaida(String motivoSaida) {
        this.motivoSaida = motivoSaida;
    }

    public List<String> getCargosOcupados() {
        return cargosOcupados;
    }

    public void setCargosOcupados(List<String> cargosOcupados) {
        this.cargosOcupados = cargosOcupados;
    }

    public List<String> getAtividadesDesempenhadas() {
        return atividadesDesempenhadas;
    }

    public void setAtividadesDesempenhadas(List<String> atividadesDesempenhadas) {
        this.atividadesDesempenhadas = atividadesDesempenhadas;
    }
}
