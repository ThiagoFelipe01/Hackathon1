package org.hackathon.model;

public class Vacina {
    private int id;
    private String nome;
    private String descricao;
    private int intervaloRecomendado;

    public Vacina(String nome, String descricao, int intervaloRecomendado) {
        this.nome = nome;
        this.descricao = descricao;
        this.intervaloRecomendado = intervaloRecomendado;
    }

    public Vacina(int id, String nome, String descricao, int intervaloRecomendado) {
        this.id = id;
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

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setIntervaloRecomendado(int intervaloRecomendado) {
        this.intervaloRecomendado = intervaloRecomendado;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getIntervaloRecomendado() {
        return intervaloRecomendado;
    }

    @Override
    public String toString() {
        return STR."Vacina{id=\{id}, nome='\{nome}\{'\''}, descricao='\{descricao}\{'\''}, intervaloRecomendado=\{intervaloRecomendado}\{'}'}";
    }
}
