package org.hackathon.dao;

import org.hackathon.model.Idoso;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IdosoDao {
    private Connection connection;

    public IdosoDao() throws SQLException {
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

    public void salvar(Idoso idoso) {
        String sql = "INSERT INTO idosos (nome, idade, endereco, telefone, historico_medico, alergias, condicoes_preexistentes, observacoes) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, idoso.getNome());
            stmt.setInt(2, idoso.getIdade());
            stmt.setString(3, idoso.getEndereco());
            stmt.setString(4, idoso.getTelefone());
            stmt.setString(5, idoso.getHistoricoMedico());
            stmt.setString(6, idoso.getAlergias());
            stmt.setString(7, idoso.getCondicoesPreexistentes());
            stmt.setString(8, idoso.getObservacoes());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Idoso> listar() {
        List<Idoso> idosos = new ArrayList<>();
        String sql = "SELECT * FROM idosos";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Idoso idoso = new Idoso();
                idoso.setId(rs.getInt("id"));
                idoso.setNome(rs.getString("nome"));
                idoso.setIdade(rs.getInt("idade"));
                idoso.setEndereco(rs.getString("endereco"));
                idoso.setTelefone(rs.getString("telefone"));
                idoso.setHistoricoMedico(rs.getString("historico_medico"));
                idoso.setAlergias(rs.getString("alergias"));
                idoso.setCondicoesPreexistentes(rs.getString("condicoes_preexistentes"));
                idoso.setObservacoes(rs.getString("observacoes"));
                idosos.add(idoso);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idosos;
    }
}
