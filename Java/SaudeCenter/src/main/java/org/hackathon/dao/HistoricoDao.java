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
        } catch (ClassNotFoundException | SQLException e) {
            throw new SQLException("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void salvar(Historico historico) {
        String sql = "INSERT INTO historico_vacinacao (idoso_id, vacina_id, data_vacinacao) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, historico.getIdosoId());
            stmt.setInt(2, historico.getVacinaId());
            stmt.setDate(3, new java.sql.Date(historico.getDataVacinacao().getTime()));
            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                historico.setId(generatedKeys.getInt(1));
            } else {
                throw new SQLException("Falha ao obter o ID gerado para o histórico de vacinação.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Historico> listar() {
        List<Historico> historicoVacinacoes = new ArrayList<>();
        String sql = "SELECT * FROM historico_vacinacao";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Historico historico = new Historico();
                historico.setId(rs.getInt("id"));
                historico.setIdosoId(rs.getInt("idoso_id"));
                historico.setVacinaId(rs.getInt("vacina_id"));
                historico.setDataVacinacao(rs.getDate("data_vacinacao"));
                historicoVacinacoes.add(historico);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return historicoVacinacoes;
    }

    public void fechar() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
