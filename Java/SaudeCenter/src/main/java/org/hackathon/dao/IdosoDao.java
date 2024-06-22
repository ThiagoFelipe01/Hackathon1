package org.hackathon.dao;

import org.hackathon.model.Idoso;

import java.sql.*;

public class IdosoDao {
    private Connection connection;

    public IdosoDao() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/saudecenter?useTimezone=true&serverTimezone=UTC", "root", "");
        } catch (Exception e) {
            throw new SQLException(e.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void inserir (Idoso idoso) throws  SQLException {
        String sql = "insert into idosos(nome,idade,cpf,endereco,telefone,historicoMedico,alergia,CondicoesPreexistentes) values(?,?,?,?,?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, idoso.getNome());
        ps.setInt(2, idoso.getIdade());
        ps.setString(3, idoso.getCpf());
        ps.setString(4, idoso.getEndereco());
        ps.setInt(5, idoso.getTelefone());
        ps.setString(6, idoso.getHistoricoMedico());
        ps.setString(7,idoso.getAlergia());
        ps.setString(8, idoso.getCondicaoPreExistente());
        ps.execute();
    }
}