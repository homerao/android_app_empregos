package br.com.empregos.dao;

import br.com.model.Documento;

public class DocumentoDAO extends AbstractDAO<Documento>{

    public DocumentoDAO(Class<Documento> documentoClass, String databaseCollection) {
        super(documentoClass, databaseCollection);
    }
}
