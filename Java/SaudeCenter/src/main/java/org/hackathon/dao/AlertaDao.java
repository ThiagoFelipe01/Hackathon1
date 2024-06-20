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
        } catch (ClassNotFoundException | SQLException e) {
            throw new SQLException("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void salvar(Alerta alerta) {
        String sql = "INSERT INTO alertas (idoso_id, mensagem, data_alerta, horario_alerta) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, alerta.getIdosoId());
            stmt.setString(2, alerta.getMensagem());
            stmt.setDate(3, new java.sql.Date(alerta.getDataAlerta().getTime()));
            stmt.setTime(4, new java.sql.Time(alerta.getHorarioAlerta().getTime()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Alerta> listar() {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alertas;
    }
}
