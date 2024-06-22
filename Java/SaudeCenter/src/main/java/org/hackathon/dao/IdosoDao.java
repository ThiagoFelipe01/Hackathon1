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

    public void inserir(Idoso idoso) throws SQLException {
        String sql = "insert into idoso(nome,idade,cpf,telefone,alergia) values(?,?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, idoso.getNome());
        ps.setInt(2, idoso.getIdade());
        ps.setInt(3, idoso.getCpf());
        ps.setInt(4, idoso.getTelefone());
        ps.setString(5, idoso.getAlergia());
        ps.execute();
    }


    public void atualizar(Idoso idoso) throws SQLException {
        String sql = "update idoso set nome = ?, idade = ?, cpf = ?, telefone = ?, alergia = ? where id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, idoso.getNome());
        ps.setInt(2, idoso.getIdade());
        ps.setInt(3, idoso.getCpf());
        ps.setInt(4, idoso.getTelefone());
        ps.setString(5, idoso.getAlergia());
        ps.setInt(6, idoso.getId());
        ps.execute();
    }
}