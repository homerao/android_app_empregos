package br.com.empregos.dao;

import br.com.model.Contato;

public class ContatoDAO extends AbstractDAO<Contato>{


    public ContatoDAO(Class<Contato> contatoClass, String dbCollection, String storeCollection) {
        super(contatoClass, dbCollection, storeCollection);
    }

    public ContatoDAO(Class<Contato> contatoClass, String databaseCollection) {
        super(contatoClass, databaseCollection);
    }
}
