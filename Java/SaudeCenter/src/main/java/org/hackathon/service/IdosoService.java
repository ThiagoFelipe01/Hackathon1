package org.hackathon.service;

import org.hackathon.dao.IdosoDao;
import org.hackathon.model.Idoso;

public class IdosoService {

    public void salvar(Idoso idoso) {
        try {
            var dao = new IdosoDao();
            if (idoso.getId() == null) {
                dao.inserir(idoso);
            } else {
                dao.atualizar(idoso);
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
