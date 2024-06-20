package org.hackathon.model;

public class Idoso {
    private Integer id;
    private String nome;
    private int idade;
    private int cpf;
    private int telefone;
    private String medicamento;

    public Idoso(String nome, int idade, int cpf, int telefone, String medicamento) {
        this.nome = nome;
        this.idade = idade;
        this.cpf = cpf;
        this.telefone = telefone;
        this.medicamento = medicamento;
    }

    public Idoso(Integer id, String nome, int idade, int cpf, int telefone, String medicamento) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.cpf = cpf;
        this.telefone = telefone;
        this.medicamento = medicamento;
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

    public int getCpf() {
        return cpf;
    }

    public void setCpf(int cpf) {
        this.cpf = cpf;
    }

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(String medicamento) {
        this.medicamento = medicamento;
    }

    @Override
    public String toString() {
        return "Id: " + id + " Nome: " + nome + " CPF: " + cpf + " Telefone: " + telefone;
    }
}
