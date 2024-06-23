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
        panel.add(new JLabel("Nome Da Vacina:"), gbc);
        gbc.gridx = 1;
        nomeTextField = new JTextField(15);
        panel.add(nomeTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Descrição:"), gbc);
        gbc.gridx = 1;
        descricaoTextField = new JTextField(15);
        panel.add(descricaoTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Intervalo Recomendado (meses):"), gbc);
        gbc.gridx = 1;
        intervaloRecomendadoTextField = new JTextField(15);
        panel.add(intervaloRecomendadoTextField, gbc);

        adicionarButton = new JButton("Adicionar");
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(adicionarButton, gbc);

        atualizarButton = new JButton("Atualizar");
        gbc.gridx = 1;
        panel.add(atualizarButton, gbc);

        deletarButton = new JButton("Deletar");
        gbc.gridx = 2;
        panel.add(deletarButton, gbc);

        outputTextArea = new JTextArea(10, 50);
        outputTextArea.setEditable(false);
        outputTextArea.setVisible(false);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 3;
        panel.add(new JScrollPane(outputTextArea), gbc);

        adicionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarVacina();
            }
        });

        atualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarVacina();
            }
        });

        deletarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deletarVacina();
            }
        });

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
            outputTextArea.setVisible(true);
        } catch (SQLException e) {
            outputTextArea.setText("Erro ao adicionar vacina: " + e.getMessage() + "\n");
            outputTextArea.setVisible(true);
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
            outputTextArea.setVisible(true);
        } catch (SQLException e) {
            outputTextArea.setText("Erro ao atualizar vacina: " + e.getMessage() + "\n");
            outputTextArea.setVisible(true);
        }
    }

    private void deletarVacina() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID da vacina a ser deletada:"));
            vacinaService.deletarVacina(id);
            outputTextArea.setText("Vacina deletada com sucesso!\n");
            outputTextArea.setVisible(true);
        } catch (SQLException e) {
            outputTextArea.setText("Erro ao deletar vacina: " + e.getMessage() + "\n");
            outputTextArea.setVisible(true);
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
            outputTextArea.setVisible(true);
        } catch (SQLException e) {
            outputTextArea.setText("Erro ao listar vacinas: " + e.getMessage() + "\n");
            outputTextArea.setVisible(true);
        }
    }

    public static void main(String[] args) {
        Connection connection = null;
        SwingUtilities.invokeLater(() -> new VacinaForm(connection).setVisible(true));
    }
}
