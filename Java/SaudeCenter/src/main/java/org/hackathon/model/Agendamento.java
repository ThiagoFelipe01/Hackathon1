package org.hackathon.model;

import java.util.Date;

public class Agendamento {
    private int id;
    private int idosoId;
    private Date dataAgendamento;
    private Date horario;

    public Agendamento(int idosoId, Date dataAgendamento, Date horario) {
        this.idosoId = idosoId;
        this.dataAgendamento = dataAgendamento;
        this.horario = horario;
    }

    public Agendamento(int id, int idosoId, Date dataAgendamento, Date horario) {
        this.id = id;
        this.idosoId = idosoId;
        this.dataAgendamento = dataAgendamento;
        this.horario = horario;
    }

    public Agendamento() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdosoId() {
        return idosoId;
    }

    public void setIdosoId(int idosoId) {
        this.idosoId = idosoId;
    }

    public Date getDataAgendamento() {
        return dataAgendamento;
    }

    public void setDataAgendamento(Date dataAgendamento) {
        this.dataAgendamento = dataAgendamento;
    }

    public Date getHorario() {
        return horario;
    }

    public void setHorario(Date horario) {
        this.horario = horario;
    }

    @Override
    public String toString() {
        return "Id: " + id + " Idoso: " + idosoId + " Data Agendamento: " + dataAgendamento + " Horario: " + horario;
    }
}
