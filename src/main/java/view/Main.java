package view;

import model.Aluno;
import service.AlunoService;

public class Main {
    static void main(String[] args) {

        AlunoService service = new AlunoService();

        Aluno aluno1 = new Aluno("Luciano Peixoto", "Masculino", 123, 18, 8.5);

        service.adicionar(aluno1);

        System.out.println("--- Teste de Cadastro (ArrayList) ---");
        for (Aluno a : service.listarTodos()) {
            System.out.println("Nome: " + a.getNome());
            System.out.println("RA: " + a.getRa());
            System.out.println("Status: " + a.getResultado()); // Deve imprimir "Aprovado"
            System.out.println("------------------------------------");
        }
    }
}
