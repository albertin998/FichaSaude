package model;

public class FichaSaude {
    private int id;
    private String nome;
    private int idade;
    private float peso;
    private float altura;
    private String pressao;

    public FichaSaude(int id, String nome, int idade, float peso, float altura, String pressao) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.peso = peso;
        this.altura = altura;
        this.pressao = pressao;
    }

    public FichaSaude(String nome, int idade, float peso, float altura, String pressao) {
        this(-1, nome, idade, peso, altura, pressao);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    public String getPressao() {
        return pressao;
    }

    public void setPressao(String pressao) {
        this.pressao = pressao;
    }

    public float calcularIMC() {
        return peso / (altura * altura);
    }

    public String interpretarIMC() {
        float imc = calcularIMC();
        if (imc < 18.5) return "Abaixo do peso";
        else if (imc < 25) return "Normal";
        else if (imc < 30) return "Sobrepeso";
        else return "Obesidade";
    }
}
