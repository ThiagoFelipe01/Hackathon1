package org.hackathon.service;

import org.hackathon.dao.VacinaDao;
import org.hackathon.model.Vacina;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class VacinaService {
    private VacinaDao vacinaDao;

    public VacinaService(Connection conn) {
        this.vacinaDao = new VacinaDao(conn);
    }


    public List<Vacina> getAllVacinas() throws SQLException {
        return vacinaDao.getAllVacinas();
    }
}
