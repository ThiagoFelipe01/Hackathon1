package org.hackathon.dao;

import org.hackathon.model.Vacina;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VacinaDao {
    private Connection conn;

    public VacinaDao(Connection conn) {
        this.conn = conn;
    }

    public List<Vacina> getAllVacinas() throws SQLException {
        List<Vacina> vacinas = new ArrayList<>();
        String query = "SELECT * FROM vacinas";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Vacina vacina = new Vacina();
                vacina.setId(rs.getInt("id"));
                vacina.setNome(rs.getString("nome"));
                vacina.setDescricao(rs.getString("descricao"));
                vacina.setIntervaloRecomendado(rs.getInt("intervalo_recomendado"));
                vacinas.add(vacina);
            }
        }
        return vacinas;
    }

    public void inserirVacina(Vacina vacina) throws SQLException {
        String query = "INSERT INTO vacinas (nome, descricao, intervalo_recomendado) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, vacina.getNome());
            stmt.setString(2, vacina.getDescricao());
            stmt.setInt(3, vacina.getIntervaloRecomendado());
            stmt.executeUpdate();
        }
    }

    public void atualizarVacina(Vacina vacina) throws SQLException {
        String query = "UPDATE vacinas SET nome=?, descricao=?, intervalo_recomendado=? WHERE id=?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, vacina.getNome());
            stmt.setString(2, vacina.getDescricao());
            stmt.setInt(3, vacina.getIntervaloRecomendado());
            stmt.setInt(4, vacina.getId());
            stmt.executeUpdate();
        }
    }

    public void excluirVacina(int idVacina) throws SQLException {
        String query = "DELETE FROM vacinas WHERE id=?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, idVacina);
            stmt.executeUpdate();
        }
    }
}
