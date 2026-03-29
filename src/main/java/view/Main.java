package view;

import model.Aluno;
import service.AlunoService;
import java.util.Scanner;

public class Main {
    static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        AlunoService service = new AlunoService();
        int opcao;

        do {
            System.out.println("\n--- MENU FATEC ADS ---");
            System.out.println("1 - Adicionar Aluno");
            System.out.println("2 - Listar Alunos");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            opcao = Integer.parseInt(scan.nextLine());

            if (opcao == 1){
                System.out.print("Nome: ");
                String nome = scan.nextLine();
                System.out.print("RA: ");
                int ra = Integer.parseInt(scan.nextLine());
                System.out.print("Média: ");
                double media = Double.parseDouble(scan.nextLine());

                service.adicionar(new Aluno(nome, "M", ra, 18, media));
                System.out.println("Aluno salvo com sucesso!");

            } else if (opcao == 2) {
                System.out.println("\n--- LISTA DE ALUNOS ---");
                for (Aluno a : service.listarTodos()){
                    System.out.println(a);
                }

            }
        } while (opcao != 0);
        scan.close();
    }
}
