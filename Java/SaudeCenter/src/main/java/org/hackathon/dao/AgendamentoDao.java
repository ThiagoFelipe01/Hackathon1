package org.hackathon.dao;

import org.hackathon.model.Agendamento;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
        String sql = "insert into agendamentos(nome,dataVisita,horaVisita) values(?,?,?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, agendamento.getNome());
        ps.setDate(2, agendamento.getDataVisita());
        ps.setTime(3, agendamento.getHoraVisita());
        ps.execute();
    }

    public List<Agendamento> listarTodos() throws SQLException {
        List<Agendamento> agendamentos = new ArrayList<Agendamento>();

        ResultSet rs = connection.prepareStatement("select * from agendamentos").executeQuery();
        while (rs.next()) {
            agendamentos.add(new Agendamento(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getDate("dataVisita"),
                    rs.getTime("horaVisita")));
        }
        rs.close();
        return agendamentos;
    }

    public void atualizar(Agendamento agendamento) throws SQLException {
        String sql = "update agendamentos set nome = ?, dataVisita = ?, horaVisita = ?, where id = ?";
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

