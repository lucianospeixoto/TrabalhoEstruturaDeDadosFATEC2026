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
}
