package org.hackathon.dao;

import org.hackathon.model.Agendamento;

import java.sql.*;

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

    public void inserir(Agendamento agendamento) throws SQLException {
        String sql = "insert into agendamento(nome,dataVisita,horaVisita) values(?,?,?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, agendamento.getNome());
        ps.setDate(2, (Date) agendamento.getDataVisita());
        ps.setTime(3, agendamento.getHoraVisita());
        ps.execute();
    }


    public void atualizar(Agendamento agendamento) throws SQLException {
        String sql = "update agendamento set nome = ?, dataVisita = ?, horaVisita = ?, where id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, agendamento.getNome());
        ps.setDate(2, agendamento.getDataVisita());
        ps.setTime(3, agendamento.getHoraVisita());
        ps.setInt(5, agendamento.getId());
        ps.execute();
    }

    public void deletar(int id) throws SQLException {
        String sql = "delete from agendamento where id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        ps.execute();
    }
}