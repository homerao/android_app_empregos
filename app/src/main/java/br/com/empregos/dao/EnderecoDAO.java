package br.com.empregos.dao;

import br.com.model.Endereco;

public class EnderecoDAO extends AbstractDAO<Endereco> {

    public EnderecoDAO(Class<Endereco> enderecoClass, String databaseCollection) {
        super(enderecoClass, databaseCollection);
    }
}
