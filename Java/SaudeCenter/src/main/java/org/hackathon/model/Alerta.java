package org.hackathon.model;

import java.sql.Time;
import java.util.Date;

public class Alerta {
    private int id;
    private int idosoId;
    private String mensagem;
    private Date dataAlerta;
    private Time horarioAlerta;

    public Alerta(int idosoId, String mensagem, Date dataAlerta, Time horarioAlerta) {
        this.idosoId = idosoId;
        this.mensagem = mensagem;
        this.dataAlerta = dataAlerta;
        this.horarioAlerta = horarioAlerta;
    }

    public Alerta(int id, int idosoId, String mensagem, Date dataAlerta, Time horarioAlerta) {
        this.id = id;
        this.idosoId = idosoId;
        this.mensagem = mensagem;
        this.dataAlerta = dataAlerta;
        this.horarioAlerta = horarioAlerta;
    }

    public Alerta() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdosoId(int idosoId) {
        this.idosoId = idosoId;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public void setDataAlerta(Date dataAlerta) {
        this.dataAlerta = dataAlerta;
    }

    public void setHorarioAlerta(Time horarioAlerta) {
        this.horarioAlerta = horarioAlerta;
    }

    public int getIdosoId() {
        return idosoId;
    }

    public String getMensagem() {
        return mensagem;
    }

    public Date getDataAlerta() {
        return dataAlerta;
    }

    public Time getHorarioAlerta() {
        return horarioAlerta;
    }

    @Override
    public String toString() {
        return "Id: " + id + " Idoso: " + idosoId + " Messagem: " + mensagem + " Data Alerta: " + dataAlerta + " Horario Alerta: " + horarioAlerta;
    }
}
