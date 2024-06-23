package org.hackathon.views;

import org.hackathon.model.Alerta;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Time;

public class AlertaForm extends JFrame {
    private JTextField idosoIdTextField;
    private JTextField mensagemTextField;
    private JTextField dataAlertaTextField;
    private JTextField horarioAlertaTextField;
    private JButton adicionarButton;
    private JButton atualizarButton;
    private JButton deletarButton;
    private JButton listarButton;
    private JTextArea outputTextArea;
    private Connection connection;

    public AlertaForm(Connection connection) {
        this.connection = connection;
        setTitle("Gerenciamento de Alertas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        initComponents();
        createMenuBar();
    }

    private void initComponents() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("ID do Idoso:"), gbc);
        gbc.gridx = 1;
        idosoIdTextField = new JTextField(15);
        panel.add(idosoIdTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Mensagem:"), gbc);
        gbc.gridx = 1;
        mensagemTextField = new JTextField(15);
        panel.add(mensagemTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Data do Alerta (YYYY-MM-DD):"), gbc);
        gbc.gridx = 1;
        dataAlertaTextField = new JTextField(15);
        panel.add(dataAlertaTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Horário do Alerta (HH:MM:SS):"), gbc);
        gbc.gridx = 1;
        horarioAlertaTextField = new JTextField(15);
        panel.add(horarioAlertaTextField, gbc);

        adicionarButton = new JButton("Adicionar");
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(adicionarButton, gbc);

        atualizarButton = new JButton("Atualizar");
        gbc.gridx = 1;
        panel.add(atualizarButton, gbc);

        deletarButton = new JButton("Deletar");
        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(deletarButton, gbc);

        listarButton = new JButton("Listar");
        gbc.gridx = 1;
        panel.add(listarButton, gbc);

        outputTextArea = new JTextArea(10, 30);
        outputTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputTextArea);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(panel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        adicionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarAlerta();
            }
        });

        atualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarAlerta();
            }
        });

        deletarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deletarAlerta();
            }
        });

        listarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarAlertas();
            }
        });

        getContentPane().add(mainPanel);
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu menu = new JMenu("Menu");
        menuBar.add(menu);

        JMenuItem listarMenuItem = new JMenuItem("Listar");
        listarMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarAlertas();
            }
        });
        menu.add(listarMenuItem);

        JMenuItem obterPorIdMenuItem = new JMenuItem("Obter por ID");
        obterPorIdMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                obterAlertaPorId();
            }
        });
        menu.add(obterPorIdMenuItem);

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

    private void adicionarAlerta() {
        try {
            int idosoId = Integer.parseInt(idosoIdTextField.getText());
            String mensagem = mensagemTextField.getText();
            Date dataAlerta = Date.valueOf(dataAlertaTextField.getText());
            Time horarioAlerta = Time.valueOf(horarioAlertaTextField.getText());

            Alerta alerta = new Alerta(0, idosoId, mensagem, dataAlerta, horarioAlerta);
            outputTextArea.append("Alerta a ser adicionado: " + alerta + "\n");
        } catch (Exception ex) {
            outputTextArea.append("Erro ao adicionar alerta: " + ex.getMessage() + "\n");
        }
    }

    private void atualizarAlerta() {
        try {
            int idosoId = Integer.parseInt(idosoIdTextField.getText());
            String mensagem = mensagemTextField.getText();
            Date dataAlerta = Date.valueOf(dataAlertaTextField.getText());
            Time horarioAlerta = Time.valueOf(horarioAlertaTextField.getText());

            Alerta alerta = new Alerta(0, idosoId, mensagem, dataAlerta, horarioAlerta);
            outputTextArea.append("Alerta a ser atualizado: " + alerta + "\n");
        } catch (Exception ex) {
            outputTextArea.append("Erro ao atualizar alerta: " + ex.getMessage() + "\n");
        }
    }

    private void deletarAlerta() {
        try {
            int idosoId = Integer.parseInt(idosoIdTextField.getText());
            outputTextArea.append("Alerta a ser deletado para o idoso ID = " + idosoId + "\n");
        } catch (Exception ex) {
            outputTextArea.append("Erro ao deletar alerta: " + ex.getMessage() + "\n");
        }
    }

    private void listarAlertas() {
        outputTextArea.append("Listando todos os alertas...\n");
    }

    private void obterAlertaPorId() {
        String id = JOptionPane.showInputDialog(this, "Digite o ID do Alerta:", "Obter Alerta por ID", JOptionPane.PLAIN_MESSAGE);
        if (id != null) {
            try {
                int alertaId = Integer.parseInt(id);
                outputTextArea.append("Obtendo alerta com ID: " + alertaId + "\n");
            } catch (NumberFormatException ex) {
                outputTextArea.append("ID inválido: " + id + "\n");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AlertaForm(null).setVisible(true);
            }
        });
    }
}
