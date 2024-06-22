package org.hackathon.model;

public class Idoso {
    private Integer id;
    private String nome;
    private int idade;
    private int cpf;
    private int telefone;
    private String Alergia;

    public Idoso(String nome, int idade, int cpf, int telefone, String Alergia) {
        this.nome = nome;
        this.idade = idade;
        this.cpf = cpf;
        this.telefone = telefone;
        this.Alergia = Alergia;
    }

    public Idoso(Integer id, String nome, int idade, int cpf, int telefone, String Alergia) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.cpf = cpf;
        this.telefone = telefone;
        this.Alergia = Alergia;
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

    public String getAlergia() {
        return Alergia;
    }

    public void setAlergia(String alergia) {
        this.Alergia = alergia;
    }

    @Override
    public String toString() {
        return "Id: " + id + " Nome: " + nome + " CPF: " + cpf + " Telefone: "
                + telefone + " medicamento: " + Alergia;
    }
}