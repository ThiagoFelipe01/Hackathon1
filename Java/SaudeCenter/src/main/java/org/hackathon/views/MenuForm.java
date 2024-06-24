package org.hackathon.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MenuForm extends JFrame {

    public MenuForm() {
        setTitle("Menu Principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setLocationRelativeTo(null);

        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel(new GridLayout(5, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton btnIdosoCadastro = new JButton("Cadastro de Idoso");
        btnIdosoCadastro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirIdosoCadastroForm();
            }
        });
        panel.add(btnIdosoCadastro);

        JButton btnVacinaForm = new JButton("Gerenciamento de Vacinas");
        btnVacinaForm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirVacinaForm();
            }
        });
        panel.add(btnVacinaForm);

        JButton btnAgendamentoForm = new JButton("Agendamento");
        btnAgendamentoForm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirAgendamentoForm();
            }
        });
        panel.add(btnAgendamentoForm);

        JButton btnAlertaForm = new JButton("Alerta");
        btnAlertaForm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirAlertaForm();
            }
        });
        panel.add(btnAlertaForm);

        JButton btnHistoricoForm = new JButton("Histórico");
        btnHistoricoForm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirHistoricoForm();
            }
        });
        panel.add(btnHistoricoForm);

        JButton btnGestaoUsuarios = new JButton("Gestão de Usuários");
        btnGestaoUsuarios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirUsuarioForm();
            }
        });
        panel.add(btnGestaoUsuarios);

        add(panel);
    }

    private void abrirIdosoCadastroForm() {
        IdosoCadastroForm idosoCadastroForm = new IdosoCadastroForm();
        idosoCadastroForm.setVisible(true);
        dispose();
    }

    private void abrirVacinaForm() {
        Connection connection = null;
        VacinaForm vacinaForm = new VacinaForm(connection);
        vacinaForm.setVisible(true);
        dispose();
    }

    private void abrirAgendamentoForm() {
        AgendamentoForm agendamentoForm = new AgendamentoForm();
        agendamentoForm.setVisible(true);
        dispose();
    }

    private void abrirAlertaForm() {
        Connection connection = null;
        AlertaForm alertaForm = new AlertaForm(connection);
        alertaForm.setVisible(true);
        dispose();
    }

    private void abrirHistoricoForm() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/saudecenter", "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Falha na conexão com o banco de dados: " + e.getMessage());
            return;
        }
        HistoricoForm historicoForm = new HistoricoForm(connection);
        historicoForm.setVisible(true);
        dispose();
    }

    private void abrirUsuarioForm() {
        UsuarioForm usuarioForm = new UsuarioForm();
        usuarioForm.setVisible(true);
        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MenuForm menuForm = new MenuForm();
            menuForm.setVisible(true);
        });
    }
}
