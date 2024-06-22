package org.hackathon.model;

import java.sql.Time;
import java.util.Date;

public class Agendamento {
    private Integer id;
    private String nome;
    private Date dataVisita;
    private Time horaVisita;

    public Agendamento(String nome, Date dataVisita, Time horaVisita) {
        this.nome = nome;
        this.dataVisita = dataVisita;
        this.horaVisita = horaVisita;

    }

    public Agendamento(Integer id, String nome, Date dataVisita, Time horaVisita) {
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

    public void setHoraVisita(Time horaVisita) {
        this.horaVisita = horaVisita;
    }

    @Override
    public String toString() {
        return "Id: " + id + " Nome: " + nome + " Data Visita: " + dataVisita + " Hora Visita: "
                + horaVisita;
    }
}