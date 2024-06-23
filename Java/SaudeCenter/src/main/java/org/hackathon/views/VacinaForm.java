package org.hackathon.views;

import org.hackathon.model.Vacina;
import org.hackathon.service.VacinaService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class VacinaForm extends JFrame {
    private JTextField nomeTextField;
    private JTextField descricaoTextField;
    private JTextField intervaloRecomendadoTextField;
    private JButton adicionarButton;
    private JButton atualizarButton;
    private JButton deletarButton;
    private JTextArea outputTextArea;
    private VacinaService vacinaService;

    public VacinaForm(Connection connection) {
        this.vacinaService = new VacinaService(connection);
        setTitle("Gerenciamento de Vacinas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);

        initComponents();
        createMenuBar();
    }

    private void initComponents() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Gerenciamento de Vacinas");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(titleLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridy++;
        panel.add(new JLabel("Nome da Vacina:"), gbc);
        gbc.gridx = 1;
        nomeTextField = new JTextField();
        panel.add(nomeTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Descrição:"), gbc);
        gbc.gridx = 1;
        descricaoTextField = new JTextField();
        panel.add(descricaoTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Intervalo Recomendado (meses):"), gbc);
        gbc.gridx = 1;
        intervaloRecomendadoTextField = new JTextField();
        panel.add(intervaloRecomendadoTextField, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        adicionarButton = new JButton("Adicionar");
        adicionarButton.setBackground(new Color(60, 179, 113));
        adicionarButton.setForeground(Color.WHITE);
        adicionarButton.setFocusPainted(false);
        adicionarButton.setFont(new Font("Arial", Font.BOLD, 12));
        adicionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarVacina();
            }
        });
        panel.add(adicionarButton, gbc);

        gbc.gridx = 1;
        atualizarButton = new JButton("Atualizar");
        atualizarButton.setBackground(new Color(255, 165, 0));
        atualizarButton.setForeground(Color.WHITE);
        atualizarButton.setFocusPainted(false);
        atualizarButton.setFont(new Font("Arial", Font.BOLD, 12));
        atualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarVacina();
            }
        });
        panel.add(atualizarButton, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        deletarButton = new JButton("Deletar");
        deletarButton.setBackground(new Color(220, 20, 60));
        deletarButton.setForeground(Color.WHITE);
        deletarButton.setFocusPainted(false);
        deletarButton.setFont(new Font("Arial", Font.BOLD, 12));
        deletarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deletarVacina();
            }
        });
        panel.add(deletarButton, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        outputTextArea = new JTextArea(10, 40);
        outputTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputTextArea);
        panel.add(scrollPane, gbc);

        add(panel);
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");

        JMenuItem listarItem = new JMenuItem("Listar Vacinas");
        listarItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarVacinas();
            }
        });

        JMenuItem exitItem = new JMenuItem("Sair");
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        menu.add(listarItem);
        menu.add(exitItem);
        menuBar.add(menu);
        setJMenuBar(menuBar);
    }

    private void adicionarVacina() {
        try {
            String nome = nomeTextField.getText();
            String descricao = descricaoTextField.getText();
            int intervaloRecomendado = Integer.parseInt(intervaloRecomendadoTextField.getText());
            vacinaService.adicionarVacina(nome, descricao, intervaloRecomendado);
            outputTextArea.setText("Vacina adicionada com sucesso!\n");
        } catch (SQLException e) {
            outputTextArea.setText("Erro ao adicionar vacina: " + e.getMessage() + "\n");
        }
    }

    private void atualizarVacina() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID da vacina a ser atualizada:"));
            String nome = nomeTextField.getText();
            String descricao = descricaoTextField.getText();
            int intervaloRecomendado = Integer.parseInt(intervaloRecomendadoTextField.getText());
            vacinaService.atualizarVacina(id, nome, descricao, intervaloRecomendado);
            outputTextArea.setText("Vacina atualizada com sucesso!\n");
        } catch (SQLException e) {
            outputTextArea.setText("Erro ao atualizar vacina: " + e.getMessage() + "\n");
        }
    }

    private void deletarVacina() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID da vacina a ser deletada:"));
            vacinaService.deletarVacina(id);
            outputTextArea.setText("Vacina deletada com sucesso!\n");
        } catch (SQLException e) {
            outputTextArea.setText("Erro ao deletar vacina: " + e.getMessage() + "\n");
        }
    }

    private void listarVacinas() {
        try {
            List<Vacina> vacinas = vacinaService.listarVacinas();
            StringBuilder sb = new StringBuilder();
            for (Vacina vacina : vacinas) {
                sb.append(vacina.toString()).append("\n");
            }
            outputTextArea.setText(sb.toString());
        } catch (SQLException e) {
            outputTextArea.setText("Erro ao listar vacinas: " + e.getMessage() + "\n");
        }
    }

    public static void main(String[] args) {
        Connection connection = null;
        SwingUtilities.invokeLater(() -> new VacinaForm(connection).setVisible(true));
    }
}
