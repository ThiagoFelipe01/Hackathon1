package org.hackathon.views;

import org.hackathon.service.AlertaService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class AlertaForm extends JFrame {
    private JTextField idosoIdTextField;
    private JTextField mensagemTextField;
    private JButton enviarButton;
    private JButton listarButton;
    private JButton limparButton;
    private JTextArea outputTextArea;
    private AlertaService alertaService;

    public AlertaForm(Connection connection) {
        this.alertaService = new AlertaService(connection);
        setTitle("Gerenciamento de Alertas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createMenuBar();
        setSize(500, 400);
        setLocationRelativeTo(null);

        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Gerenciamento de Alertas");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(titleLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridy++;
        panel.add(new JLabel("ID do Idoso:"), gbc);
        gbc.gridx = 1;
        idosoIdTextField = new JTextField();
        panel.add(idosoIdTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Mensagem:"), gbc);
        gbc.gridx = 1;
        mensagemTextField = new JTextField();
        panel.add(mensagemTextField, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        enviarButton = new JButton("Enviar");
        enviarButton.setBackground(new Color(60, 179, 113));
        enviarButton.setForeground(Color.WHITE);
        enviarButton.setFocusPainted(false);
        enviarButton.setFont(new Font("Arial", Font.BOLD, 12));
        enviarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enviarAlerta();
            }
        });
        panel.add(enviarButton, gbc);

        gbc.gridx = 1;
        listarButton = new JButton("Listar");
        listarButton.setBackground(new Color(30, 144, 255));
        listarButton.setForeground(Color.WHITE);
        listarButton.setFocusPainted(false);
        listarButton.setFont(new Font("Arial", Font.BOLD, 12));
        listarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarAlertas();
            }
        });
        panel.add(listarButton, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        limparButton = new JButton("Limpar");
        limparButton.setBackground(new Color(220, 20, 60));
        limparButton.setForeground(Color.WHITE);
        limparButton.setFocusPainted(false);
        limparButton.setFont(new Font("Arial", Font.BOLD, 12));
        limparButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limparAlertas();
            }
        });
        panel.add(limparButton, gbc);

        gbc.gridy++;
        gbc.gridwidth = 2;
        outputTextArea = new JTextArea(10, 40);
        outputTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputTextArea);
        panel.add(scrollPane, gbc);

        add(panel);
    }

    public void voltar() {
        MenuForm menuForm = new MenuForm();
        menuForm.setVisible(true);
        dispose();
    }

    private void enviarAlerta() {
        try {
            int idosoId = Integer.parseInt(idosoIdTextField.getText().trim());
            String mensagem = mensagemTextField.getText().trim();
            if (mensagem.isEmpty()) {
                throw new IllegalArgumentException("Mensagem não pode ser vazia.");
            }
            alertaService.enviarAlerta(idosoId, mensagem);
            outputTextArea.setText("Alerta enviado com sucesso!\n");
        } catch (NumberFormatException e) {
            outputTextArea.setText("Erro: ID do Idoso deve ser um número inteiro.\n");
        } catch (IllegalArgumentException e) {
            outputTextArea.setText("Erro: " + e.getMessage() + "\n");
        } catch (SQLException e) {
            outputTextArea.setText("Erro ao enviar alerta: " + e.getMessage() + "\n");
        }
    }

    private void listarAlertas() {
        try {
            int idosoId = Integer.parseInt(idosoIdTextField.getText().trim());
            List<String> alertas = alertaService.listarAlertas(idosoId);
            StringBuilder sb = new StringBuilder();
            for (String alerta : alertas) {
                sb.append(alerta).append("\n");
            }
            outputTextArea.setText(sb.toString());
        } catch (NumberFormatException e) {
            outputTextArea.setText("Erro: ID do Idoso deve ser um número inteiro.\n");
        } catch (SQLException e) {
            outputTextArea.setText("Erro ao listar alertas: " + e.getMessage() + "\n");
        }
    }

    private void limparAlertas() {
        try {
            int idosoId = Integer.parseInt(idosoIdTextField.getText().trim());
            alertaService.limparAlertas(idosoId);
            outputTextArea.setText("Alertas limpos com sucesso!\n");
        } catch (NumberFormatException e) {
            outputTextArea.setText("Erro: ID do Idoso deve ser um número inteiro.\n");
        } catch (SQLException e) {
            outputTextArea.setText("Erro ao limpar alertas: " + e.getMessage() + "\n");
        }
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
