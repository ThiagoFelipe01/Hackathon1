package org.hackathon.model;

import java.util.Date;

public class Historico {
    private int id;
    private int idosoId;
    private int vacinaId;
    private Date dataVacinacao;

    public Historico(int id, int idosoId, int vacinaId, Date dataVacinacao) {
        this.id = id;
        this.idosoId = idosoId;
        this.vacinaId = vacinaId;
        this.dataVacinacao = dataVacinacao;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getIdosoId() { return idosoId; }
    public void setIdosoId(int idosoId) { this.idosoId = idosoId; }

    public int getVacinaId() { return vacinaId; }
    public void setVacinaId(int vacinaId) { this.vacinaId = vacinaId; }

    public Date getDataVacinacao() { return dataVacinacao; }
    public void setDataVacinacao(Date dataVacinacao) { this.dataVacinacao = dataVacinacao; }
}
