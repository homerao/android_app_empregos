package br.com.util;

import java.util.HashMap;
import java.util.Map;

import br.com.model.Candidato;

public class ConvertModelToMap {


    private static Map<String, Object> candidatoMap;


    public static Map<String, Object> candidatoToMap(Candidato c){

                candidatoMap = new HashMap<>();
                candidatoMap.put("candidato", c);
                return  candidatoMap;

    }
}
