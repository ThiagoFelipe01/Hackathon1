package org.hackathon.model;

public class Idoso {
    private Integer id;
    private String nome;
    private int idade;
    private String cpf;
    private String endereco;
    private int telefone;
    private String historicoMedico;
    private String alergia;
    private String condicaoPreExistente;
    private String observacoes;

    public Idoso(String nome, int idade, String cpf, String endereco, int telefone,
                 String historicoMedico, String alergia, String condicaoPreExistente, String observacoes) {
        this.nome = nome;
        this.idade = idade;
        this.cpf = cpf;
        this.endereco = endereco;
        this.telefone = telefone;
        this.historicoMedico = historicoMedico;
        this.alergia = alergia;
        this.condicaoPreExistente = condicaoPreExistente;
        this.observacoes = observacoes;
    }

    public Idoso(Integer id, String nome, int idade, String cpf, String endereco, int telefone,
                 String historicoMedico, String alergia, String condicaoPreExistente, String observacoes) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.cpf = cpf;
        this.endereco = endereco;
        this.telefone = telefone;
        this.historicoMedico = historicoMedico;
        this.alergia = alergia;
        this.condicaoPreExistente = condicaoPreExistente;
        this.observacoes = observacoes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    public String getHistoricoMedico() {
        return historicoMedico;
    }

    public void setHistoricoMedico(String historicoMedico) {
        this.historicoMedico = historicoMedico;
    }

    public String getAlergia() {
        return alergia;
    }

    public void setAlergia(String alergia) {
        this.alergia = alergia;
    }

    public String getCondicaoPreExistente() {
        return condicaoPreExistente;
    }

    public void setCondicaoPreExistente(String condicaoPreExistente) {
        this.condicaoPreExistente = condicaoPreExistente;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    @Override
    public String toString() {
        return  "Id: " + id + " Nome: " + nome +  " Idade: " + idade + " CPF: " + cpf + " Endereço: " + endereco +
                " Telefone: " + telefone + " Historico Medico: " + historicoMedico + " Alergia: " + alergia +
                " Condição Pré Existente: " + condicaoPreExistente + " Observações: ";
    }
}