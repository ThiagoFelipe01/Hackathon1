package org.hackathon.model;

public class Vacina {
    private int id;
    private String nome;
    private String descricao;
    private int intervaloRecomendado;

    public Vacina(int id, String nome, String descricao, int intervaloRecomendado) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.intervaloRecomendado = intervaloRecomendado;
    }

    public Vacina(String nome, String descricao, int intervaloRecomendado) {
        this.nome = nome;
        this.descricao = descricao;
        this.intervaloRecomendado = intervaloRecomendado;
    }

    public Vacina() {
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getIntervaloRecomendado() {
        return intervaloRecomendado;
    }

    public void setIntervaloRecomendado(int intervaloRecomendado) {
        this.intervaloRecomendado = intervaloRecomendado;
    }

    @Override
    public String toString() {
        return "Vacina{" + "id=" + id + ", nome='" + nome + '\'' + ", descricao='" + descricao + '\'' + ", intervaloRecomendado=" + intervaloRecomendado + '}';
    }
}
