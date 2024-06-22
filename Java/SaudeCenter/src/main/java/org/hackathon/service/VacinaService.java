package org.hackathon.service;

import org.hackathon.dao.VacinaDao;
import org.hackathon.model.Vacina;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class VacinaService {
    private VacinaDao vacinaDao;

    public VacinaService(Connection connection) {
        this.vacinaDao = new VacinaDao(connection);
    }

    public void adicionarVacina(String nome, String descricao, int intervaloRecomendado) throws SQLException {
        Vacina vacina = new Vacina(nome, descricao, intervaloRecomendado);
        vacinaDao.adicionarVacina(vacina);
    }

    public void atualizarVacina(int id, String nome, String descricao, int intervaloRecomendado) throws SQLException {
        Vacina vacina = new Vacina(id, nome, descricao, intervaloRecomendado);
        vacinaDao.atualizarVacina(vacina);
    }

    public void deletarVacina(int id) throws SQLException {
        vacinaDao.deletarVacina(id);
    }

    public Vacina obterVacinaPorId(int id) throws SQLException {
        return vacinaDao.obterVacinaPorId(id);
    }

    public List<Vacina> listarVacinas() throws SQLException {
        return vacinaDao.listarVacinas();
    }
}
