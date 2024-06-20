package org.hackathon.model;

public class Idoso {
    private int id;
    private String nome;
    private int idade;
    private String endereco;
    private String telefone;
    private String historicoMedico;
    private String alergias;
    private String condicoesPreexistentes;
    private String observacoes;

    public Idoso(String nome, int idade, String endereco, String telefone, String historicoMedico, String alergias, String condicoesPreexistentes, String observacoes) {
        this.nome = nome;
        this.idade = idade;
        this.endereco = endereco;
        this.telefone = telefone;
        this.historicoMedico = historicoMedico;
        this.alergias = alergias;
        this.condicoesPreexistentes = condicoesPreexistentes;
        this.observacoes = observacoes;
    }

    public Idoso(int id, String nome, int idade, String endereco, String telefone, String historicoMedico, String alergias, String condicoesPreexistentes, String observacoes) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.endereco = endereco;
        this.telefone = telefone;
        this.historicoMedico = historicoMedico;
        this.alergias = alergias;
        this.condicoesPreexistentes = condicoesPreexistentes;
        this.observacoes = observacoes;
    }

    public Idoso() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setHistoricoMedico(String historicoMedico) {
        this.historicoMedico = historicoMedico;
    }

    public void setAlergias(String alergias) {
        this.alergias = alergias;
    }

    public void setCondicoesPreexistentes(String condicoesPreexistentes) {
        this.condicoesPreexistentes = condicoesPreexistentes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getHistoricoMedico() {
        return historicoMedico;
    }

    public String getAlergias() {
        return alergias;
    }

    public String getCondicoesPreexistentes() {
        return condicoesPreexistentes;
    }

    public String getObservacoes() {
        return observacoes;
    }

    @Override
    public String toString() {
        return STR."Idoso{id=\{id}, nome='\{nome}\{'\''}, idade=\{idade}, endereco='\{endereco}\{'\''}, telefone='\{telefone}\{'\''}, historicoMedico='\{historicoMedico}\{'\''}, alergias='\{alergias}\{'\''}, condicoesPreexistentes='\{condicoesPreexistentes}\{'\''}, observacoes='\{observacoes}\{'\''}\{'}'}";
    }
}
