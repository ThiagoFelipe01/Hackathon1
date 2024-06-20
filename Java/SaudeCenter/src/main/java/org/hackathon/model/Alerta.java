package org.hackathon.model;

import java.util.Date;

public class Alerta {
    private int id;
    private int idosoId;
    private String mensagem;
    private Date dataAlerta;
    private Date horarioAlerta;

    public Alerta(int idosoId, String mensagem, Date dataAlerta, Date horarioAlerta) {
        this.idosoId = idosoId;
        this.mensagem = mensagem;
        this.dataAlerta = dataAlerta;
        this.horarioAlerta = horarioAlerta;
    }

    public Alerta(int id, int idosoId, String mensagem, Date dataAlerta, Date horarioAlerta) {
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

    public void setHorarioAlerta(Date horarioAlerta) {
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

    public Date getHorarioAlerta() {
        return horarioAlerta;
    }

    @Override
    public String toString() {
        return STR."Alerta{id=\{id}, idosoId=\{idosoId}, mensagem='\{mensagem}\{'\''}, dataAlerta=\{dataAlerta}, horarioAlerta=\{horarioAlerta}\{'}'}";
    }
}
