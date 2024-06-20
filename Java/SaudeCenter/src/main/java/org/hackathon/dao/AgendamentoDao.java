package org.hackathon.dao;

import org.hackathon.model.Agendamento;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AgendamentoDao {
    private Connection connection;

    public AgendamentoDao() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/saudecenter?useTimezone=true&serverTimezone=UTC", "root", "");
        } catch (ClassNotFoundException | SQLException e) {
            throw new SQLException("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }

    public void salvar(Agendamento agendamento) {
        String sql = "INSERT INTO agendamentos (idoso_id, data_agendamento, horario) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, agendamento.getIdosoId());
            stmt.setDate(2, new java.sql.Date(agendamento.getDataAgendamento().getTime()));
            stmt.setTime(3, new java.sql.Time(agendamento.getHorario().getTime()));
            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                agendamento.setId(generatedKeys.getInt(1));
            } else {
                throw new SQLException("Falha ao obter o ID gerado para o agendamento.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Agendamento> listar() {
        List<Agendamento> agendamentos = new ArrayList<>();
        String sql = "SELECT * FROM agendamentos";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Agendamento agendamento = new Agendamento();
                agendamento.setId(rs.getInt("id"));
                agendamento.setIdosoId(rs.getInt("idoso_id"));
                agendamento.setDataAgendamento(rs.getDate("data_agendamento"));
                agendamento.setHorario(rs.getTime("horario"));
                agendamentos.add(agendamento);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return agendamentos;
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
