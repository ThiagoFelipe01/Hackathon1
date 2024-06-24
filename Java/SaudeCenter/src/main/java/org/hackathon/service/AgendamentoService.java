
package org.hackathon.service;

import org.hackathon.dao.AgendamentoDao;
import org.hackathon.model.Agendamento;

import java.sql.SQLException;
import java.util.List;

public class AgendamentoService {

    private AgendamentoDao dao;

    public AgendamentoService() {
        try {
            this.dao = new AgendamentoDao();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void salvar(Agendamento agendamento) {
        try {
            var dao = new AgendamentoDao();
            if (agendamento.getId() == null) {
                dao.inserir(agendamento);
            }else {
                dao.atualizar(agendamento);
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Agendamento> listaAgendamento() {
        try {
            return dao.listarTodos();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deletar(Agendamento agendamento) {
        try {
            dao.deletar(agendamento.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> listarNomesIdosos() {
        try {
            return dao.listarIdosos();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int obterIdIdosoPorNome(String nome) {
        try {
            return dao.obterIdIdosoPorNome(nome);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
