package main.java.view;

import main.java.model.Aluno;
import main.java.service.AlunoService;

import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        // Criando o Scanner para ler do teclado 
        Scanner leitor = new Scanner(System.in);
        AlunoService service = new AlunoService();

        int opcao = 0;

        // Menu Interativo conforme o PDF 
        do {
            System.out.println("\n--- SISTEMA ACADEMICO FATEC ---");
            System.out.println("1. Cadastrar Alunos");
            System.out.println("2. Relatorio por Nome (A-Z)");
            System.out.println("3. Relatorio por RA (Decrescente)");
            System.out.println("4. Relatorio de Aprovados");
            System.out.println("5. Encerrar");
            System.out.print("Escolha uma opcao: ");

            opcao = leitor.nextInt();
            leitor.nextLine(); // Limpa o buffer do teclado

            switch (opcao) {
                case 1:
                    // REQUISITO 1: Cadastrar Alunos [cite: 27]
                    System.out.print("Digite o Nome: ");
                    String nome = leitor.nextLine();

                    System.out.print("Digite o RA: ");
                    int ra = leitor.nextInt();

                    System.out.print("Digite a Idade: ");
                    int idade = leitor.nextInt();

                    System.out.print("Digite o Sexo (M/F): ");
                    String sexo = leitor.next();

                    System.out.print("Digite a Media: ");
                    double media = leitor.nextDouble();

                    // Criando o objeto aluno com os dados lidos [cite: 19]
                    Aluno novo = new Aluno(nome, sexo, ra, idade, media);
                    service.adicionar(novo);

                    System.out.println("Aluno cadastrado com sucesso!");
                    break;

                case 2:
                    // REQUISITO 2: Ordenar A-Z [cite: 28]
                    service.ordenarPorNomeCrescente();
                    System.out.println("--- Relatorio por Nome ---");
                    service.listarTodos();
                    break;

                case 3:
                    // REQUISITO 3: RA Decrescente [cite: 29]
                    service.ordenarPorRADecrescente();
                    System.out.println("--- Relatorio por RA (Maior para Menor) ---");
                    service.listarTodos();
                    break;

                case 4:
                    // REQUISITO 4: Aprovados [cite: 30]
                    System.out.println("--- Lista de Aprovados ---");
                    service.filtrarAprovadosOrdenados();
                    break;

                case 5:
                    // REQUISITO 5: Encerrar [cite: 32]
                    System.out.println("Encerrando o programa...");
                    break;

                default:
                    System.out.println("Opcao invalida!");
            }

        } while (opcao != 5);

        leitor.close();
    }
}