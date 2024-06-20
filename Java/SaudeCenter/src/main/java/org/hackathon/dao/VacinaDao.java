package org.hackathon.dao;

import org.hackathon.model.Vacina;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VacinaDao {
    private Connection connection;

    public VacinaDao() throws SQLException {
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

    public void salvar(Vacina vacina) {
        String sql = "INSERT INTO vacinas (nome, descricao, intervalo_recomendado) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, vacina.getNome());
            stmt.setString(2, vacina.getDescricao());
            stmt.setInt(3, vacina.getIntervaloRecomendado());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Vacina> listar() {
        List<Vacina> vacinas = new ArrayList<>();
        String sql = "SELECT * FROM vacinas";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Vacina vacina = new Vacina();
                vacina.setId(rs.getInt("id"));
                vacina.setNome(rs.getString("nome"));
                vacina.setDescricao(rs.getString("descricao"));
                vacina.setIntervaloRecomendado(rs.getInt("intervalo_recomendado"));
                vacinas.add(vacina);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vacinas;
    }
}
