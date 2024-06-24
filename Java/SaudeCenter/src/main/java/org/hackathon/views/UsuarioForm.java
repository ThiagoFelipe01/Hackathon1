package org.hackathon.views;

import org.hackathon.dao.UsuarioDao;
import org.hackathon.model.Usuario;
import org.hackathon.service.UsuarioService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class UsuarioForm extends JFrame {
    private UsuarioService usuarioService;
    private JTable table;
    private DefaultTableModel tableModel;

    public UsuarioForm() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/saudecenter", "root", "password");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            usuarioService = new UsuarioService(new UsuarioDao(connection));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        setTitle("Gestão de Usuários");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        createMenuBar();
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        tableModel = new DefaultTableModel(new String[]{"ID", "Nome", "Email"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel labelNome = new JLabel("Nome:");
        JTextField textNome = new JTextField(20);
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(labelNome, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        formPanel.add(textNome, gbc);

        JLabel labelEmail = new JLabel("Email:");
        JTextField textEmail = new JTextField(20);
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(labelEmail, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        formPanel.add(textEmail, gbc);

        JLabel labelSenha = new JLabel("Senha:");
        JPasswordField textSenha = new JPasswordField(20);
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(labelSenha, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        formPanel.add(textSenha, gbc);

        JButton btnAdicionar = new JButton("Adicionar");
        JButton btnAtualizar = new JButton("Atualizar");
        JButton btnExcluir = new JButton("Excluir");

        btnAdicionar.setBackground(new Color(60, 179, 113));
        btnAdicionar.setForeground(Color.WHITE);
        btnAdicionar.setFocusPainted(false);
        btnAdicionar.setFont(new Font("Arial", Font.BOLD, 12));

        btnAtualizar.setBackground(new Color(30, 144, 255));
        btnAtualizar.setForeground(Color.WHITE);
        btnAtualizar.setFocusPainted(false);
        btnAtualizar.setFont(new Font("Arial", Font.BOLD, 12));

        btnExcluir.setBackground(new Color(220, 20, 60));
        btnExcluir.setForeground(Color.WHITE);
        btnExcluir.setFocusPainted(false);
        btnExcluir.setFont(new Font("Arial", Font.BOLD, 12));

        btnAdicionar.setMinimumSize(new Dimension(100, 30));
        btnAtualizar.setMinimumSize(new Dimension(100, 30));
        btnExcluir.setMinimumSize(new Dimension(100, 30));

        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(btnAdicionar, gbc);

        gbc.gridx = 1;
        formPanel.add(btnAtualizar, gbc);

        gbc.gridx = 2;
        formPanel.add(btnExcluir, gbc);

        panel.add(formPanel, BorderLayout.SOUTH);

        add(panel);

        btnAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarUsuario(textNome, textEmail, textSenha);
            }
        });

        btnAtualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarUsuario(textNome, textEmail, textSenha);
            }
        });

        btnExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluirUsuario();
            }
        });

        loadUsuarios();
    }

    private void adicionarUsuario(JTextField textNome, JTextField textEmail, JPasswordField textSenha) {
        try {
            String nome = textNome.getText().trim();
            String email = textEmail.getText().trim();
            String senha = new String(textSenha.getPassword()).trim();

            if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos os campos são obrigatórios.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")) {
                JOptionPane.showMessageDialog(this, "Email inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Usuario usuario = new Usuario(0, nome, email, senha);
            usuarioService.addUsuario(usuario);
            loadUsuarios();
            limparCampos(textNome, textEmail, textSenha);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao adicionar usuário: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void atualizarUsuario(JTextField textNome, JTextField textEmail, JPasswordField textSenha) {
        try {
            int selectedRow = table.getSelectedRow();
            if (selectedRow < 0) {
                JOptionPane.showMessageDialog(this, "Selecione um usuário para atualizar.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int id = Integer.parseInt(tableModel.getValueAt(selectedRow, 0).toString());
            String nome = textNome.getText().trim();
            String email = textEmail.getText().trim();
            String senha = new String(textSenha.getPassword()).trim();

            if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos os campos são obrigatórios.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")) {
                JOptionPane.showMessageDialog(this, "Email inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Usuario usuario = new Usuario(id, nome, email, senha);
            usuarioService.updateUsuario(usuario);
            loadUsuarios();
            limparCampos(textNome, textEmail, textSenha);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar usuário: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void excluirUsuario() {
        try {
            int selectedRow = table.getSelectedRow();
            if (selectedRow < 0) {
                JOptionPane.showMessageDialog(this, "Selecione um usuário para excluir.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int id = Integer.parseInt(tableModel.getValueAt(selectedRow, 0).toString());
            usuarioService.deleteUsuario(id);
            loadUsuarios();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao excluir usuário: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limparCampos(JTextField textNome, JTextField textEmail, JPasswordField textSenha) {
        textNome.setText("");
        textEmail.setText("");
        textSenha.setText("");
    }

    private void loadUsuarios() {
        try {
            List<Usuario> usuarios = usuarioService.getAllUsuarios();
            tableModel.setRowCount(0);
            for (Usuario usuario : usuarios) {
                tableModel.addRow(new Object[]{usuario.getId(), usuario.getNome(), usuario.getEmail()});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void voltar() {
        MenuForm menuForm = new MenuForm();
        menuForm.setVisible(true);
        dispose();
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu menu = new JMenu("Menu");
        menuBar.add(menu);

        JMenuItem listarMenuItem = new JMenuItem("Voltar");
        listarMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                voltar();
            }
        });
        menu.add(listarMenuItem);

        JMenuItem sairMenuItem = new JMenuItem("Sair");
        sairMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        menu.add(sairMenuItem);

        setJMenuBar(menuBar);
    }
}
