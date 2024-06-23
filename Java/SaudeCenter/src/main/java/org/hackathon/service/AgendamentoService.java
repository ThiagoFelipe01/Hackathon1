package org.hackathon.service;

import org.hackathon.dao.AgendamentoDao;
import org.hackathon.model.Agendamento;

import java.util.Collections;
import java.util.List;

public class AgendamentoService {

    public void salvar(Agendamento agendamento) {
        try {
            var dao = new AgendamentoDao();
            if (agendamento.getId() == null) {
                dao.inserir(agendamento);
            } else {
                dao.atualizar(agendamento);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Agendamento> listaAgendamento() {
        try {
            var dao = new AgendamentoDao();
            return dao.listarTodos();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Collections.emptyList();
        }
    }

    public void deletar(Agendamento agendamento) {
        try {
            var delete = new AgendamentoDao();
            if (agendamento.getId() != null) {
                delete.deletar(agendamento.getId());
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
