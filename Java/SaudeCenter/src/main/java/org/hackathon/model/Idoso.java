package org.hackathon.model;

public class Idoso {
    private Integer id;
    private String nome;
    private int idade;
    private int cpf;
    private int telefone;

    public Idoso(String nome, int idade, int cpf, int telefone) {
        this.nome = nome;
        this.idade = idade;
        this.cpf = cpf;
        this.telefone = telefone;
    }

    public Idoso(Integer id, String nome, int idade, int cpf, int telefone) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.cpf = cpf;
        this.telefone = telefone;
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

    @Override
    public String toString() {
        return "Id: " + id + " Nome: " + nome + " CPF: " + cpf + " Telefone: " + telefone;
    }
}
