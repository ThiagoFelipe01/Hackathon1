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
        String sql = "insert into agendamentos(idoso_id,data_agendamento,horario) values(?,?,?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, agendamento.getIdIdoso());
        ps.setDate(2, (Date) agendamento.getDataAgendamento());
        ps.setTime(3, agendamento.getHorario());
        ps.execute();
    }

    public List<Agendamento> listarTodos() throws SQLException {
        List<Agendamento> agendamentos = new ArrayList<>();
        ResultSet rs = connection.prepareStatement("select * from agendamentos").executeQuery();
        while (rs.next()) {
            agendamentos.add(new Agendamento(
                    rs.getInt("id"),
                    rs.getInt("idIdoso"),
                    rs.getDate("data_agendamento"),
                    rs.getTime("horario")));
        }
        rs.close();
        return agendamentos;
    }

    public void atualizar(Agendamento agendamento) throws SQLException {
        String sql = "update agendamentos set idoso_id = ?, data_agendamento = ?, horario = ? where id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, agendamento.getIdIdoso());
        ps.setDate(4, (Date) agendamento.getDataAgendamento());
        ps.setTime(3, agendamento.getHorario());
        ps.setInt(4, agendamento.getId());
        ps.execute();
    }

    public void deletar(int id) throws SQLException {
        String sql = "delete from agendamentos where id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        ps.execute();
    }

    public List<String> listarIdosos() throws SQLException {
        List<String> nomes = new ArrayList<>();
        String sql = "select nome from idosos";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                nomes.add(rs.getString("nome"));
            }
        }
        return nomes;
    }

    public int obterIdIdosoPorNome(String nome) throws SQLException {
        String sql = "select id from idosos where nome = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, nome);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            } else {
                throw new SQLException("Idoso não encontrado com o nome: " + nome);
            }
        }
    }
}