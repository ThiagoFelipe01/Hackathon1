package org.hackathon.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AgendamentoDao {
    private Connection connection;

    public AgendamentoDao() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/saudecenter?useTimezone=true&serverTimezone=UTC", "root", "");
        } catch (Exception e) {
            throw new SQLException(e.getMessage());
        }
    }
}
