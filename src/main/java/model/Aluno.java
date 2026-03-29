package model;

public class Aluno {
    private String nome, sexo;
    private int ra, idade;
    private double media;
    private String resultado;

    public Aluno() {
    }

    public Aluno(String nome, String sexo, int ra, int idade, double media) {
        this.nome = nome;
        this.sexo = sexo;
        this.ra = ra;
        this.idade = idade;
        this.setMedia(media);
    }

    // Este é o metodo que verifica se a media do aluno é 6 e da o RESULTADO: aprovado ou reprovado
    public void setMedia(double media){
        this.media = media;
        if (this.media >= 6.0){
            this.resultado = "Aprovado";
        } else{
            this.resultado = "Reprovado";
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int getRa() {
        return ra;
    }

    public void setRa(int ra) {
        this.ra = ra;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public double getMedia() {
        return media;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    @Override
    public String toString() {
        return "Aluno{" +
                "nome='" + nome + '\'' +
                ", sexo='" + sexo + '\'' +
                ", ra=" + ra +
                ", idade=" + idade +
                ", media=" + media +
                ", resultado='" + resultado + '\'' +
                '}';
    }
}

