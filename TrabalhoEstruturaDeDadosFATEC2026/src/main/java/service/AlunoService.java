package main.java.service;

import main.java.model.Aluno;

import java.util.ArrayList;
import java.util.List;

public class AlunoService {
    private List<Aluno> listaAlunos = new ArrayList<>();

    public void adicionar(Aluno aluno){
        listaAlunos.add(aluno);
    }

    public List<Aluno> listarTodos(){
        return listaAlunos;
    }

    public void ordenarPorNomeCrescente() {
        //bubble sort
        int n = listaAlunos.size();
        for (int i=0; i<n-1; i++) {
            for (int j= 0; j<n-i-1; j++) {
                if (listaAlunos.get(j).getNome().compareTo(listaAlunos.get(j+1).getNome()) > 0){
                        Aluno temp = listaAlunos.get(j);
                        listaAlunos.set(j, listaAlunos.get(j+1));
                        listaAlunos.set(j+1,temp);
                }
            }
        }
        for (Aluno aluno : listaAlunos) {
            System.out.println(aluno);
        }
    }

    public void ordenarPorRADecrescente() {
       //selection sort
        int n = listaAlunos.size();
        for (int i=0; i<n-1; i++) {
            int pos_maior = i;
            for (int j= i+1; j < n ; j++) {
                if (listaAlunos.get(j).getRa() > listaAlunos.get(pos_maior).getRa()){
                    pos_maior = j;
                }
            }
            Aluno temp = listaAlunos.get(i);
            listaAlunos.set(i,listaAlunos.get(pos_maior));
            listaAlunos.set(pos_maior,temp);
        }
        for (Aluno aluno : listaAlunos) {
            System.out.println(aluno);
        }
    }

    public List<Aluno> filtrarAprovadosOrdenados() {
        List<Aluno> aprovados = new ArrayList<>();
        for(Aluno aluno : listaAlunos){
            if (aluno.getResultado().equals("Aprovado")){
                aprovados.add(aluno);
            }
        }
        int n = aprovados.size();
        for (int i=0; i<n-1; i++) {
            for (int j= 0; j<n-i-1; j++) {
                if (aprovados.get(j).getNome().compareTo(aprovados.get(j+1).getNome()) > 0 ){
                    Aluno temp = aprovados.get(j);
                    aprovados.set(j, aprovados.get(j+1));
                    aprovados.set(j+1,temp);
                }
            }
        }
        for (Aluno aluno : aprovados) {
            System.out.println(aluno);
        }
        return aprovados;
    }

}
