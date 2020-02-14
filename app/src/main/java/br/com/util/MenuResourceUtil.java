package br.com.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;



import br.com.model.Cursos;

public class MenuResourceUtil {


    public static class PerfilResource{

        public List getPerfilIcons(){
            int [] perfilIcons = {
                    R.drawable.ic_person_blue_64dp,
                    R.drawable.documentos,
                    R.drawable.contato,
                    R.drawable.localidade,
                    R.drawable.curriculo,
                    R.drawable.cursos,
                    R.drawable.formacao,
                    R.drawable.experiencia


            };
           List icons = new ArrayList();
           for (int x = 0; x <= perfilIcons.length; x ++){
               icons.add(perfilIcons[x]);
           }
            return icons;
        }

        public List<String> getTitulosPerfil(){
            String[] titulos = {
                    "Meus Dados",
                    "Documentos",
                    "Contato",
                    "Endereço",
                    "Currículo",
                    "Cursos",
                    "Formação",
                    "Experiência profissional",};

            List lstTitulos = new ArrayList();

            for (int i = 0; i < titulos.length ; i++) {
                lstTitulos.add(titulos[i]);
            }
            return lstTitulos;
        }

    }

    public static class CursosResource{

        public  List<Cursos> getCursos(){
            List<Cursos> lstCursos = new ArrayList<>();
            Cursos c1 = new Cursos();
            Cursos c2 = new Cursos();
            Cursos c3 = new Cursos();
            Cursos c4 = new Cursos();
            Cursos c5 = new Cursos();
            Cursos c6 = new Cursos();
            Cursos c7 = new Cursos();
            Cursos c8 = new Cursos();

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

            c4.setQtdHoras(80);
            c4.setDataInicio(new Date());
            c4.setNomeCurso("Excel");
            c4.setDataTermino(new Date());
            c4.setDescriçãoCurso("Formatação e organização de dados em tabelas");
            c4.setEmenta("Implantação de circuitos elétricos de forma segura");

            c5.setQtdHoras(80);
            c5.setDataInicio(new Date());
            c5.setNomeCurso("Vigilante");
            c5.setDataTermino(new Date());
            c5.setDescriçãoCurso("Normas de segurança com arma de fogo");
            c5.setEmenta("Implantação de circuitos elétricos de forma segura");

            c6.setQtdHoras(80);
            c6.setDataInicio(new Date());
            c6.setNomeCurso("Torneiro Mecânico");
            c6.setDataTermino(new Date());
            c6.setDescriçãoCurso("Usinagem e processos produtivos com máquinas");
            c6.setEmenta("Implantação de circuitos elétricos de forma segura");

            c7.setQtdHoras(80);
            c7.setDataInicio(new Date());
            c7.setNomeCurso("Mecatrônica");
            c7.setDataTermino(new Date());
            c7.setDescriçãoCurso("Automação industrial");
            c7.setEmenta("Implantação de circuitos elétricos de forma segura");

            c8.setQtdHoras(80);
            c8.setDataInicio(new Date());
            c8.setNomeCurso("Injeção de plásticos");
            c8.setDataTermino(new Date());
            c8.setDescriçãoCurso("Operação de Máquina injetora");
            c8.setEmenta("Implantação de circuitos elétricos de forma segura");

            lstCursos.add(c1);
            lstCursos.add(c2);
            lstCursos.add(c3);
            lstCursos.add(c4);
            lstCursos.add(c5);
            lstCursos.add(c6);
            lstCursos.add(c7);
            lstCursos.add(c8);


            return lstCursos;
        }
    }


 }
