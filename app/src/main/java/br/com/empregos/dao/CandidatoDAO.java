package br.com.empregos.dao;

import br.com.model.Candidato;

public class CandidatoDAO extends AbstractDAO<Candidato>{





    public CandidatoDAO(Class<Candidato> candidatoClass, String dbCollection, String storeCollection) {
        super(candidatoClass, dbCollection, storeCollection);
    }


}
