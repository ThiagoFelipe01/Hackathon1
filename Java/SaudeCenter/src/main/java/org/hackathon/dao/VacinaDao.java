package org.hackathon.dao;

import org.hackathon.model.Vacina;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VacinaDao {
    private Connection connection;

    public VacinaDao(Connection connection) {
        this.connection = connection;
    }

    public void adicionarVacina(Vacina vacina) throws SQLException {
        String sql = "INSERT INTO vacinas (nome, descricao, intervalo_recomendado) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, vacina.getNome());
            stmt.setString(2, vacina.getDescricao());
            stmt.setInt(3, vacina.getIntervaloRecomendado());
            stmt.executeUpdate();
        }
    }

    public void atualizarVacina(Vacina vacina) throws SQLException {
        String sql = "UPDATE vacinas SET nome = ?, descricao = ?, intervalo_recomendado = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, vacina.getNome());
            stmt.setString(2, vacina.getDescricao());
            stmt.setInt(3, vacina.getIntervaloRecomendado());
            stmt.setInt(4, vacina.getId());
            stmt.executeUpdate();
        }
    }

    public void deletarVacina(int id) throws SQLException {
        String sql = "DELETE FROM vacinas WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public Vacina obterVacinaPorId(int id) throws SQLException {
        String sql = "SELECT * FROM vacinas WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Vacina(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getString("descricao"),
                            rs.getInt("intervalo_recomendado")
                    );
                }
            }
        }
        return null;
    }

    public List<Vacina> listarVacinas() throws SQLException {
        List<Vacina> vacinas = new ArrayList<>();
        String sql = "SELECT * FROM vacinas";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Vacina vacina = new Vacina(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("descricao"),
                        rs.getInt("intervalo_recomendado")
                );
                vacinas.add(vacina);
            }
        }
        return vacinas;
    }
}
