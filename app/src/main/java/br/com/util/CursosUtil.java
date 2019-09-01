package br.com.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.model.Cursos;

public  class CursosUtil{

    public List<Cursos> getCursos(){
        List<Cursos> lstCursos = new ArrayList<>();
        Cursos c1 = new Cursos();
        Cursos c2 = new Cursos();
        Cursos c3 = new Cursos();
        c1.setQtdHoras(30);
        c1.setDataInicio(new Date());
        c1.setNomeCurso("Empilhadeira");
        c1.setDataTermino(new Date());
        c1.setDescriçãoCurso("Operação de empilhadeira seu manuseio e cuidados");
        c1.setEmenta("Normas regulamentadoras e operação da empilhadeira de forma segura");

        c2.setQtdHoras(50);
        c2.setDataInicio(new Date());
        c2.setNomeCurso("Informática");
        c2.setDataTermino(new Date());
        c2.setDescriçãoCurso("Operação de computadores e seus programas");
        c2.setEmenta("Utilização do computador e seus componentes para uso em escritório");

        c3.setQtdHoras(80);
        c3.setDataInicio(new Date());
        c3.setNomeCurso("Eletricista");
        c3.setDataTermino(new Date());
        c3.setDescriçãoCurso("Instalação de circuitos elétricos residenciais");
        c3.setEmenta("Implantação de circuitos elétricos de forma segura");

        lstCursos.add(c1);
        lstCursos.add(c2);
        lstCursos.add(c3);


        return lstCursos;
    }
}

