package org.hackathon.model;

import java.util.Date;

public class Historico {
    private int id;
    private int idosoId;
    private int vacinaId;
    private Date dataVacinacao;

    public Historico(int idosoId, int vacinaId, Date dataVacinacao) {
        this.idosoId = idosoId;
        this.vacinaId = vacinaId;
        this.dataVacinacao = dataVacinacao;
    }

    public Historico(int id, int idosoId, int vacinaId, Date dataVacinacao) {
        this.id = id;
        this.idosoId = idosoId;
        this.vacinaId = vacinaId;
        this.dataVacinacao = dataVacinacao;
    }

    public int getId() {
        return id;
    }

    public int getIdosoId() {
        return idosoId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdosoId(int idosoId) {
        this.idosoId = idosoId;
    }

    public void setVacinaId(int vacinaId) {
        this.vacinaId = vacinaId;
    }

    public void setDataVacinacao(Date dataVacinacao) {
        this.dataVacinacao = dataVacinacao;
    }

    public int getVacinaId() {
        return vacinaId;
    }

    public Date getDataVacinacao() {
        return dataVacinacao;
    }

    @Override
    public String toString() {
        return "Id: " + id + " Idoso: " + idosoId + " Vacina: " + vacinaId + " Data Vacinação: " + dataVacinacao;
    }
}
