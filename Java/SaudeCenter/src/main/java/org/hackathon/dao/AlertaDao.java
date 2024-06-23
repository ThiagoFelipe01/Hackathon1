package org.hackathon.dao;

import org.hackathon.model.Alerta;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlertaDao {
    private Connection connection;

    public AlertaDao() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/saudecenter?useTimezone=true&serverTimezone=UTC", "root", "");
        } catch (Exception e) {
            // throw new SQLException(e.getMessage());
            throw new SQLException("Driver JDBC do MySQL não encontrado.", e);
        }
    }

    public Connection getConnection() {
        return connection;
    }
    public void adicionarAlerta(Alerta alerta) throws SQLException {
        String sql = "INSERT INTO alertas (idoso_id, mensagem, data_alerta, horario_alerta) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, alerta.getIdosoId());
            stmt.setString(2, alerta.getMensagem());
            stmt.setObject(3, alerta.getDataAlerta());
            stmt.setTime(4, alerta.getHorarioAlerta());
            stmt.executeUpdate();
        }
    }

    public void atualizarAlerta(Alerta alerta) throws SQLException {
        String sql = "UPDATE alertas SET idoso_id = ?, mensagem = ?, data_alerta = ?, horario_alerta = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, alerta.getIdosoId());
            stmt.setString(2, alerta.getMensagem());
            stmt.setObject(3, alerta.getDataAlerta());
            stmt.setTime(4, alerta.getHorarioAlerta());
            stmt.setInt(5, alerta.getId());
            stmt.executeUpdate();
        }
    }

    public void deletarAlerta(int id) throws SQLException {
        String sql = "DELETE FROM alertas WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public Alerta obterAlertaPorId(int id) throws SQLException {
        String sql = "SELECT * FROM alertas WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Alerta alerta = new Alerta();
                    alerta.setId(rs.getInt("id"));
                    alerta.setIdosoId(rs.getInt("idoso_id"));
                    alerta.setMensagem(rs.getString("mensagem"));
                    alerta.setDataAlerta(rs.getDate("data_alerta"));
                    alerta.setHorarioAlerta(rs.getTime("horario_alerta"));
                    return alerta;
                }
            }
        }
        return null;
    }

    public List<Alerta> listarAlertas() throws SQLException {
        List<Alerta> alertas = new ArrayList<>();
        String sql = "SELECT * FROM alertas";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Alerta alerta = new Alerta();
                alerta.setId(rs.getInt("id"));
                alerta.setIdosoId(rs.getInt("idoso_id"));
                alerta.setMensagem(rs.getString("mensagem"));
                alerta.setDataAlerta(rs.getDate("data_alerta"));
                alerta.setHorarioAlerta(rs.getTime("horario_alerta"));
                alertas.add(alerta);
            }
        }
        return alertas;
    }
}
