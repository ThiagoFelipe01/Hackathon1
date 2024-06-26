
package org.hackathon.model;

import java.sql.Time;
import java.util.Date;

public class Agendamento {
    private Integer id;
    private Integer idIdoso;
    private Date dataAgendamento;
    private Time horario;
    private String nomeIdoso;

    public Agendamento(Integer idIdoso, Date dataAgendamento, Time horario, String nomeIdoso) {
        this.idIdoso = idIdoso;
        this.dataAgendamento = dataAgendamento;
        this.horario = horario;
        this.nomeIdoso = nomeIdoso;
    }

    public Agendamento(Integer id, Integer idIdoso, Date dataAgendamento, Time horario, String nomeIdoso) {
        this.id = id;
        this.idIdoso = idIdoso;
        this.dataAgendamento = dataAgendamento;
        this.horario = horario;
        this.nomeIdoso = nomeIdoso;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdIdoso() {
        return idIdoso;
    }

    public void setIdIdoso(Integer idIdoso) {
        this.idIdoso = idIdoso;
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

    public String getNomeIdoso() {
        return nomeIdoso;
    }

    public void setNomeIdoso(String nomeIdoso) {
        this.nomeIdoso = nomeIdoso;
    }

    @Override
    public String toString() {
        return "Id: " + id + " Nome: " + nomeIdoso + " Data Visita: " + dataAgendamento + " Hora Visita: " + horario;
    }
}
