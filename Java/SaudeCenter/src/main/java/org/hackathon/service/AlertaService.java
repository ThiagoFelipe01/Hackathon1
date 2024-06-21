package org.hackathon.service;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class AlertaService {
    private Connection connection;

    public AlertaService(Connection connection) {
        this.connection = connection;
    }

    public void enviarAlerta(int idosoId, String mensagem) throws SQLException {
        String sql = "INSERT INTO alertas (idoso_id, mensagem, data_alerta, horario_alerta) VALUES (?, ?, ?, ?)";
        LocalDate dataAtual = LocalDate.now();
        LocalTime horarioAtual = LocalTime.now();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idosoId);
            stmt.setString(2, mensagem);
            stmt.setDate(3, Date.valueOf(dataAtual));
            stmt.setTime(4, Time.valueOf(horarioAtual));

            stmt.executeUpdate();
        }
    }

    public List<String> listarAlertas(int idosoId) throws SQLException {
        List<String> alertas = new ArrayList<>();
        String sql = "SELECT mensagem FROM alertas WHERE idoso_id = ? ORDER BY data_alerta DESC, horario_alerta DESC";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idosoId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String mensagem = rs.getString("mensagem");
                    alertas.add(mensagem);
                }
            }
        }

        return alertas;
    }

    public void limparAlertas(int idosoId) throws SQLException {
        String sql = "DELETE FROM alertas WHERE idoso_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idosoId);
            stmt.executeUpdate();
        }
    }
}
