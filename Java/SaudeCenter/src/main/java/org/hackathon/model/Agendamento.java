package org.hackathon.model;

import java.sql.Time;
import java.util.Date;
import java.util.Timer;

public class Agendamento {
    private Integer id;
    private String nome;
    private Date dataVisita;
    private Timer horaVisita;

    public Agendamento(Date dataVisita, Timer horaVisita, String nome) {
        this.dataVisita = dataVisita;
        this.horaVisita = horaVisita;
        this.nome = nome;
    }

    public Agendamento(Integer id, String nome, Date dataVisita, Timer horaVisita) {
        this.id = id;
        this.nome = nome;
        this.dataVisita = dataVisita;
        this.horaVisita = horaVisita;
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

    public java.sql.Date getDataVisita() {
        return (java.sql.Date) dataVisita;
    }

    public void setDataVisita(Date dataVisita) {
        this.dataVisita = dataVisita;
    }

    public Time getHoraVisita() {
        return (Time) dataVisita;
    }

    public void setHoraVisita(Timer horaVisita) {
        this.horaVisita = horaVisita;
    }
}
