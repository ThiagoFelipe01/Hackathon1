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
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Erro ao conectar ao banco de dados. Verifique a conexão.");
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
        String sql = "SELECT a.id, a.idoso_id, a.data_agendamento, a.horario, i.nome " +
                "FROM agendamentos a " +
                "JOIN idosos i ON a.idoso_id = i.id " +
                "ORDER BY a.id"; // Ordena por ID do agendamento
        try (ResultSet rs = connection.prepareStatement(sql).executeQuery()) {
            while (rs.next()) {
                agendamentos.add(new Agendamento(
                        rs.getInt("id"),
                        rs.getInt("idoso_id"),
                        rs.getDate("data_agendamento"),
                        rs.getTime("horario"),
                        rs.getString("nome")));
            }
        }
        return agendamentos;
    }


    public void atualizar(Agendamento agendamento) throws SQLException {
        String sql = "update agendamentos set idoso_id = ?, data_agendamento = ?, horario = ? where id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, agendamento.getIdIdoso());
        ps.setDate(2, (Date) agendamento.getDataAgendamento());
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

    public List<Object[]> listarIdosos() throws SQLException {
        List<Object[]> idosos = new ArrayList<>();
        String sql = "select id, nome from idosos";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                idosos.add(new Object[]{rs.getInt("id"), rs.getString("nome")});
            }
        }
        return idosos;
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