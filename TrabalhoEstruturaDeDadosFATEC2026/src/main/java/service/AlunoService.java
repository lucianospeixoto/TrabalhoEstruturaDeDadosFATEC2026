package service;

import model.Aluno;

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
        // TODO: Implementar ordenação por nome crescente
    }

    public void ordenarPorRADecrescente() {
        // TODO: Implementar ordenação de RA de forma decrescente
    }

    public List<Aluno> filtrarAprovadosOrdenados() {
        List<Aluno> aprovados = new ArrayList<>();
        // TODO: 1. Filtrar quem tem status "Aprovado"
        // TODO: 2. Ordenar essa nova lista por nome
        return aprovados;
    }
}
