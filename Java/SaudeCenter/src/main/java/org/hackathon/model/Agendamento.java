package org.hackathon.model;

import java.sql.Time;
import java.util.Date;

public class Agendamento {
    private Integer id;
    private String nome;
    private Date dataAgendamento;
    private Time horario;

    public Agendamento(String nome, Date dataAgendamento, Time horario) {
        this.nome = nome;
        this.dataAgendamento = dataAgendamento;
        this.horario = horario;
    }

    public Agendamento(Integer id, String nome, Date dataAgendamento, Time horario) {
        this.id = id;
        this.nome = nome;
        this.dataAgendamento = dataAgendamento;
        this.horario = horario;
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

    public Date getDataAgendamento() {
        return dataAgendamento;
    }

    public void setDataAgendamento(Date dataAgendamento) {
        this.dataAgendamento = dataAgendamento;
    }

    public Time getHorario() {
        return horario;
    }

    public void setHorario(Time horario) {
        this.horario = horario;
    }

    @Override
    public String toString() {
        return "Id: " + id + " Nome: " + nome + " Data Visita: " + dataAgendamento + " Hora Visita: " + horario;
    }
}