package org.hackathon.dao;

import org.hackathon.model.Historico;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HistoricoDao {
    private Connection connection;

    public HistoricoDao() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/saudecenter?useTimezone=true&serverTimezone=UTC", "root", "");
        } catch (Exception e) {
            throw new SQLException("Driver JDBC do MySQL não encontrado.", e);
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void salvar(Historico historicoVacinacao) {
        String sql = "INSERT INTO historico_vacinacao (idoso_id, vacina_id, data_vacinacao) VALUES (?, ?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, historicoVacinacao.getIdosoId());
            stmt.setInt(2, historicoVacinacao.getVacinaId());
            stmt.setDate(3, new java.sql.Date(historicoVacinacao.getDataVacinacao().getTime()));
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Historico buscarPorId(int id) {
        String sql = "SELECT * FROM historico_vacinacao WHERE id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Historico historicoVacinacao = new Historico(
                        rs.getInt("id"),
                        rs.getInt("idoso_id"),
                        rs.getInt("vacina_id"),
                        rs.getDate("data_vacinacao")
                );
                rs.close();
                stmt.close();
                return historicoVacinacao;
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public List<Historico> listarTodos() {
        String sql = "SELECT * FROM historico_vacinacao";
        try {
            List<Historico> historicos = new ArrayList<>();
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Historico historicoVacinacao = new Historico(
                        rs.getInt("id"),
                        rs.getInt("idoso_id"),
                        rs.getInt("vacina_id"),
                        rs.getDate("data_vacinacao")
                );
                historicos.add(historicoVacinacao);
            }
            rs.close();
            stmt.close();
            return historicos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void atualizar(Historico historico) {
        String sql = "UPDATE historico_vacinacao SET idoso_id = ?, vacina_id = ?, data_vacinacao = ? WHERE id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, historico.getIdosoId());
            stmt.setInt(2, historico.getVacinaId());
            stmt.setDate(3, new java.sql.Date(historico.getDataVacinacao().getTime()));
            stmt.setInt(4, historico.getId());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM historico_vacinacao WHERE id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
