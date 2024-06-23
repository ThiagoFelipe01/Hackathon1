package org.hackathon.dao;

import org.hackathon.model.Historico;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HistoricoDao {
    private Connection connection;

    public HistoricoDao() throws SQLException {
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
}
