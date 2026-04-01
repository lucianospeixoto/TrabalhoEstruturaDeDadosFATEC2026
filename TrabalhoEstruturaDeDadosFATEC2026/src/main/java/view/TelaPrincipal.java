package main.java.view;

import main.java.model.Aluno;
import main.java.service.AlunoService;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class TelaPrincipal extends JFrame {
    private AlunoService service = new AlunoService();

    private JTextField txtNome = new JTextField();
    private JTextField txtRA = new JTextField();
    private JTextField txtMedia = new JTextField();
    private JTextField txtIdade = new JTextField();
    private JComboBox<String> cbSexo = new JComboBox<>(new String[]{"M", "F"});
    private JTextArea areaRelatorio = new JTextArea(15, 90);

    public TelaPrincipal() {
        setTitle("Sistema Acadêmico - FATEC Franca");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // --- PAINEL DE ENTRADA (Adicionado Idade e Sexo) ---
        JPanel pnlInputs = new JPanel(new GridLayout(5, 2, 5, 5));
        pnlInputs.setBorder(BorderFactory.createTitledBorder("Dados do Aluno"));
        pnlInputs.add(new JLabel(" Nome Completo:")); pnlInputs.add(txtNome);
        pnlInputs.add(new JLabel(" Registro Acadêmico (RA):")); pnlInputs.add(txtRA);
        pnlInputs.add(new JLabel(" Idade:")); pnlInputs.add(txtIdade);
        pnlInputs.add(new JLabel(" Sexo:")); pnlInputs.add(cbSexo);
        pnlInputs.add(new JLabel(" Média Semestral:")); pnlInputs.add(txtMedia);

        // --- PAINEL DE BOTÕES ---
        JPanel pnlBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JButton btnCadastrar = criarBotao("1. Cadastrar", new Color(0, 120, 215));
        JButton btnNomeAz = criarBotao("2. Nome (A-Z)", Color.DARK_GRAY);
        JButton btnRaDesc = criarBotao("3. RA (Decresc.)", Color.DARK_GRAY);
        JButton btnAprovados = criarBotao("4. Aprovados", new Color(34, 139, 34));
        JButton btnGerarTeste = criarBotao("Gerar Teste (40)", new Color(255, 140, 0));
        JButton btnSair = criarBotao("5. Sair", new Color(178, 34, 34));

        pnlBotoes.add(btnCadastrar);
        pnlBotoes.add(btnNomeAz);
        pnlBotoes.add(btnRaDesc);
        pnlBotoes.add(btnAprovados);
        pnlBotoes.add(btnGerarTeste);
        pnlBotoes.add(btnSair);

        // --- PAINEL DO RELATÓRIO (Fonte Monospaced para alinhar colunas) ---
        areaRelatorio.setFont(new Font("Monospaced", Font.BOLD, 13));
        areaRelatorio.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaRelatorio);
        scroll.setBorder(BorderFactory.createTitledBorder("Relatórios Gerados"));

        JPanel pnlSuperior = new JPanel(new BorderLayout());
        pnlSuperior.add(pnlInputs, BorderLayout.NORTH);
        pnlSuperior.add(pnlBotoes, BorderLayout.CENTER);
        pnlSuperior.setBorder(new EmptyBorder(10, 10, 10, 10));

        add(pnlSuperior, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);

        // --- EVENTOS ---
        btnCadastrar.addActionListener(e -> cadastrar());
        btnNomeAz.addActionListener(e -> { service.ordenarPorNomeCrescente(); atualizar(service.listarTodos(), "NOMES (A-Z)"); });
        btnRaDesc.addActionListener(e -> { service.ordenarPorRADecrescente(); atualizar(service.listarTodos(), "RA (DECRESCENTE)"); });
        btnAprovados.addActionListener(e -> atualizar(service.filtrarAprovadosOrdenados(), "APENAS APROVADOS (A-Z)"));
        btnGerarTeste.addActionListener(e -> gerarAlunosAutomaticos());
        btnSair.addActionListener(e -> System.exit(0));
    }

    private JButton criarBotao(String texto, Color corBorda) {
        JButton b = new JButton(texto);
        b.setPreferredSize(new Dimension(160, 40));
        b.setBackground(Color.WHITE);
        b.setForeground(Color.BLACK);
        b.setFocusPainted(false);
        b.setBorder(BorderFactory.createLineBorder(corBorda, 2));
        return b;
    }

    private void cadastrar() {
        try {
            service.adicionar(new Aluno(
                    txtNome.getText(),
                    cbSexo.getSelectedItem().toString(),
                    Integer.parseInt(txtRA.getText()),
                    Integer.parseInt(txtIdade.getText()),
                    Double.parseDouble(txtMedia.getText())
            ));
            limparCampos();
            atualizar(service.listarTodos(), "LISTA GERAL DE ALUNOS");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro: preencha todos os campos corretamente!");
        }
    }

    private void atualizar(List<Aluno> lista, String titulo) {
        StringBuilder sb = new StringBuilder();
        sb.append("=== ").append(titulo).append(" ===\n\n");
        // Cabeçalho formatado com todas as colunas solicitadas
        sb.append(String.format("%-30s | %-10s | %-5s | %-5s | %-7s | %s\n",
                "NOME DO ALUNO", "RA", "IDADE", "SEXO", "MÉDIA", "RESULTADO"));
        sb.append("-".repeat(95)).append("\n");

        for (Aluno a : lista) {
            sb.append(String.format("%-30s | %-10d | %-5d | %-5s | %-7.1f | %s\n",
                    a.getNome().toUpperCase(),
                    a.getRa(),
                    a.getIdade(),
                    a.getSexo(),
                    a.getMedia(),
                    a.getResultado()));
        }
        areaRelatorio.setText(sb.toString());
    }

    private void limparCampos() {
        txtNome.setText(""); txtRA.setText(""); txtMedia.setText(""); txtIdade.setText("");
        txtNome.requestFocus();
    }

    private void gerarAlunosAutomaticos() {
        String[] nomes = {"Luciano", "Miguel", "Ana", "Pedro", "Maria", "Joao", "Beatriz", "Carlos", "Fernanda", "Ricardo"};
        String[] sobrenomes = {"Souza", "Arantes", "Silva", "Santos", "Oliveira", "Pereira", "Ferreira", "Alves", "Peixoto", "Costa"};
        String[] sexos = {"M", "F"};

        java.util.Random random = new java.util.Random();
        for (int i = 0; i < 40; i++) {
            String nomeC = nomes[random.nextInt(nomes.length)] + " " + sobrenomes[random.nextInt(sobrenomes.length)];
            int ra = 10000 + random.nextInt(90000);
            int idade = 17 + random.nextInt(10);
            double med = random.nextDouble() * 10.0;
            String sex = sexos[random.nextInt(2)];

            service.adicionar(new Aluno(nomeC, sex, ra, idade, med));
        }
        atualizar(service.listarTodos(), "40 ALUNOS GERADOS PARA TESTE");
        JOptionPane.showMessageDialog(this, "Massa de dados gerada!");
    }

    public static void main(String[] args) {
        String aviso = "<html><body style='width: 350px; text-align: justify; font-family: sans-serif;'>" +
                "<h2 style='color: #004080;'>Aviso de Desenvolvimento</h2>" +
                "A interface visual deste projeto foi concebida com o auxílio de Inteligência Artificial " +
                "em colaboração estratégica com os desenvolvedores.<br><br>" +
                "Contudo, toda a <b>lógica de negócio, implementação de estruturas de dados, " +
                "algoritmos de ordenação e fluxos do sistema</b> foram desenvolvidos integralmente " +
                "pelos alunos do 3º Semestre de ADS (2026):<br><br>" +
                "• <b>Luciano Souza Peixoto</b><br>" +
                "• <b>Miguel de Paula Arantes</b>" +
                "</body></html>";

        JOptionPane.showMessageDialog(null, aviso, "Sobre o Projeto", JOptionPane.INFORMATION_MESSAGE);
        SwingUtilities.invokeLater(() -> new TelaPrincipal().setVisible(true));
    }
}