package org.hackathon.views;

import org.hackathon.model.Historico;
import org.hackathon.service.HistoricoService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.util.List;

public class HistoricoForm extends JFrame {
    private HistoricoService historicoService;
    private JComboBox<Integer> comboIdosoId, comboVacinaId;
    private JTextField txtDataVacinacao;
    private JButton btnSalvar, btnBuscar, btnAtualizar, btnDeletar, btnListarTodos;

    public HistoricoForm(Connection connection) {
        historicoService = new HistoricoService(connection);
        createMenuBar();
        initUI();
    }

    private void initUI() {
        setTitle("Histórico de Vacinação");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel titleLabel = new JLabel("Histórico de Vacinação");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(titleLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridy++;
        panel.add(new JLabel("ID do Idoso:"), gbc);
        comboIdosoId = new JComboBox<>(historicoService.buscarTodosIdosos());
        gbc.gridx = 1;
        panel.add(comboIdosoId, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("ID da Vacina:"), gbc);
        comboVacinaId = new JComboBox<>(historicoService.buscarTodasVacinas());
        gbc.gridx = 1;
        panel.add(comboVacinaId, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Data da Vacinação (yyyy-mm-dd):"), gbc);
        txtDataVacinacao = new JTextField();
        gbc.gridx = 1;
        panel.add(txtDataVacinacao, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        btnSalvar = new JButton("Salvar");
        btnSalvar.setBackground(new Color(60, 179, 113));
        btnSalvar.setForeground(Color.WHITE);
        btnSalvar.setFocusPainted(false);
        btnSalvar.setFont(new Font("Arial", Font.BOLD, 12));
        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Historico historico = new Historico(
                        0,
                        (Integer) comboIdosoId.getSelectedItem(),
                        (Integer) comboVacinaId.getSelectedItem(),
                        Date.valueOf(txtDataVacinacao.getText())
                );
                historicoService.salvarHistorico(historico);
                JOptionPane.showMessageDialog(null, "Histórico salvo com sucesso!");
            }
        });
        panel.add(btnSalvar, gbc);


        gbc.gridx = 1;
        btnBuscar = new JButton("Buscar");
        btnBuscar.setBackground(new Color(30, 144, 255));
        btnBuscar.setForeground(Color.WHITE);
        btnBuscar.setFocusPainted(false);
        btnBuscar.setFont(new Font("Arial", Font.BOLD, 12));
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do histórico de vacinação:"));
                Historico historico = historicoService.buscarHistoricoPorId(id);
                if (historico != null) {
                    comboIdosoId.setSelectedItem(historico.getIdosoId());
                    comboVacinaId.setSelectedItem(historico.getVacinaId());
                    txtDataVacinacao.setText(historico.getDataVacinacao().toString());
                } else {
                    JOptionPane.showMessageDialog(null, "Histórico não encontrado!");
                }
            }
        });
        panel.add(btnBuscar, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        btnAtualizar = new JButton("Atualizar");
        btnAtualizar.setBackground(new Color(255, 165, 0));
        btnAtualizar.setForeground(Color.WHITE);
        btnAtualizar.setFocusPainted(false);
        btnAtualizar.setFont(new Font("Arial", Font.BOLD, 12));
        btnAtualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do histórico de vacinação:"));
                Historico historico = new Historico(
                        id,
                        (Integer) comboIdosoId.getSelectedItem(),
                        (Integer) comboVacinaId.getSelectedItem(),
                        Date.valueOf(txtDataVacinacao.getText())
                );
                historicoService.atualizarHistorico(historico);
                JOptionPane.showMessageDialog(null, "Histórico atualizado com sucesso!");
            }
        });
        panel.add(btnAtualizar, gbc);

        gbc.gridx = 1;
        btnDeletar = new JButton("Deletar");
        btnDeletar.setBackground(new Color(220, 20, 60));
        btnDeletar.setForeground(Color.WHITE);
        btnDeletar.setFocusPainted(false);
        btnDeletar.setFont(new Font("Arial", Font.BOLD, 12));
        btnDeletar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do histórico de vacinação:"));
                historicoService.deletarHistorico(id);
                JOptionPane.showMessageDialog(null, "Histórico deletado com sucesso!");
            }
        });
        panel.add(btnDeletar, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        btnListarTodos = new JButton("Listar Todos");
        btnListarTodos.setBackground(new Color(100, 149, 237));
        btnListarTodos.setForeground(Color.WHITE);
        btnListarTodos.setFocusPainted(false);
        btnListarTodos.setFont(new Font("Arial", Font.BOLD, 12));
        btnListarTodos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Historico> historicos = historicoService.listarTodos();
                StringBuilder sb = new StringBuilder();
                for (Historico historico : historicos) {
                    sb.append("ID: ").append(historico.getId()).append(", Idoso ID: ").append(historico.getIdosoId())
                            .append(", Vacina ID: ").append(historico.getVacinaId()).append(", Data: ")
                            .append(historico.getDataVacinacao()).append("\n");
                }
                JTextArea textArea = new JTextArea(sb.toString());
                textArea.setEditable(false);
                JScrollPane scrollPane = new JScrollPane(textArea);
                scrollPane.setPreferredSize(new Dimension(380, 150));
                JOptionPane.showMessageDialog(null, scrollPane, "Históricos de Vacinação", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        panel.add(btnListarTodos, gbc);

        add(panel);
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
