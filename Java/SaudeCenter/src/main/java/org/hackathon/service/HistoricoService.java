package org.hackathon.service;

import org.hackathon.model.Historico;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HistoricoService {
    private Connection connection;

    public HistoricoService(Connection connection) {
        this.connection = connection;
    }

    public void salvarHistorico(Historico historico) {
        String sql = "INSERT INTO historicos (idoso_id, vacina_id, data_vacinacao) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, historico.getIdosoId());
            pstmt.setInt(2, historico.getVacinaId());
            pstmt.setDate(3, (Date) historico.getDataVacinacao());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Historico buscarHistoricoPorId(int id) {
        String sql = "SELECT * FROM historicos WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Historico(
                            rs.getInt("id"),
                            rs.getInt("idoso_id"),
                            rs.getInt("vacina_id"),
                            rs.getDate("data_vacinacao")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void atualizarHistorico(Historico historico) {
        String sql = "UPDATE historicos SET idoso_id = ?, vacina_id = ?, data_vacinacao = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, historico.getIdosoId());
            pstmt.setInt(2, historico.getVacinaId());
            pstmt.setDate(3, (Date) historico.getDataVacinacao());
            pstmt.setInt(4, historico.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletarHistorico(int id) {
        String sql = "DELETE FROM historicos WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Historico> listarTodos() {
        List<Historico> historicos = new ArrayList<>();
        String sql = "SELECT * FROM historicos";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                historicos.add(new Historico(
                        rs.getInt("id"),
                        rs.getInt("idoso_id"),
                        rs.getInt("vacina_id"),
                        rs.getDate("data_vacinacao")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return historicos;
    }

    public Integer[] buscarTodosIdosos() {
        List<Integer> ids = new ArrayList<>();
        String sql = "SELECT id FROM idosos";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                ids.add(rs.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ids.toArray(new Integer[0]);
    }

    public Integer[] buscarTodasVacinas() {
        List<Integer> ids = new ArrayList<>();
        String sql = "SELECT id FROM vacinas";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                ids.add(rs.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ids.toArray(new Integer[0]);
    }
}
