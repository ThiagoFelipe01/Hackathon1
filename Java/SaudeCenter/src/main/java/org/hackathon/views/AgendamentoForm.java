package org.hackathon.views;

import org.hackathon.model.Agendamento;
import org.hackathon.service.AgendamentoService;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

import static java.lang.Integer.parseInt;

public class AgendamentoForm extends JFrame {

    private AgendamentoService service;
    private JLabel labelId;
    private JTextField campoId;
    private JLabel labelNome;
    private JComboBox<String> comboBoxNomes;
    private JLabel labelDataAgendamento;
    private JTextField campoDataAgendamento;
    private JLabel labelHorario;
    private JTextField campoHorario;
    private JButton botaoSalvar;
    private JButton botaoLimpar;
    private JButton botaoDeletar;
    private JTable tabela;

    public AgendamentoForm() {
        service = new AgendamentoService();

        setTitle("Agendamento de Visitas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createMenuBar();
        initComponents();

        setSize(500, 500);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initComponents() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Agendamento de Visitas");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridwidth = 3;
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(titleLabel, gbc);

        labelId = new JLabel("ID:");
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(labelId, gbc);

        campoId = new JTextField(20);
        campoId.setEnabled(false);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(campoId, gbc);

        labelNome = new JLabel("Nome:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(labelNome, gbc);

        List<String> nomesIdosos = service.listarNomesIdosos();
        comboBoxNomes = new JComboBox<>(nomesIdosos.toArray(new String[0]));
        comboBoxNomes.setSelectedItem(null);
        comboBoxNomes.setPreferredSize(new Dimension(200, comboBoxNomes.getPreferredSize().height));
        comboBoxNomes.setEditable(false);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(comboBoxNomes, gbc);

        labelDataAgendamento = new JLabel("Data Visita:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(labelDataAgendamento, gbc);

        campoDataAgendamento = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(campoDataAgendamento, gbc);

        labelHorario = new JLabel("Hora Visita:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(labelHorario, gbc);

        campoHorario = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 4;
        panel.add(campoHorario, gbc);

        botaoSalvar = new JButton("Salvar / Editar");
        botaoSalvar.setBackground(new Color(60, 179, 113)); // Green color
        botaoSalvar.setForeground(Color.WHITE);
        botaoSalvar.setFocusPainted(false);
        botaoSalvar.setFont(new Font("Arial", Font.BOLD, 12));
        botaoSalvar.addActionListener(e -> salvar());
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(botaoSalvar, gbc);

        botaoLimpar = new JButton("Limpar Campos");
        botaoLimpar.setBackground(new Color(220, 20, 60)); // Red color
        botaoLimpar.setForeground(Color.WHITE);
        botaoLimpar.setFocusPainted(false);
        botaoLimpar.setFont(new Font("Arial", Font.BOLD, 12));
        botaoLimpar.addActionListener(e -> limparCampos());
        gbc.gridx = 1;
        gbc.gridy = 5;
        panel.add(botaoLimpar, gbc);

        botaoDeletar = new JButton("Cancelar");
        botaoDeletar.setBackground(new Color(30, 144, 255)); // Blue color
        botaoDeletar.setForeground(Color.WHITE);
        botaoDeletar.setFocusPainted(false);
        botaoDeletar.setFont(new Font("Arial", Font.BOLD, 12));
        botaoDeletar.addActionListener(e -> deletar());
        gbc.gridx = 2;
        gbc.gridy = 5;
        panel.add(botaoDeletar, gbc);

        tabela = new JTable();
        tabela.setDefaultEditor(Object.class, null);
        tabela.getTableHeader().setReorderingAllowed(false);
        tabela.setModel(carregarDadosAgendamentos());
        tabela.getSelectionModel().addListSelectionListener(this::selecionarAgendamento);

        JScrollPane scrollPane = new JScrollPane(tabela);
        gbc.gridwidth = 3;
        gbc.gridx = 0;
        gbc.gridy = 6;
        panel.add(scrollPane, gbc);

        getContentPane().add(panel);
    }

    private DefaultTableModel carregarDadosAgendamentos() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nome");
        model.addColumn("Data Visita");
        model.addColumn("Hora Visita");

        service.listaAgendamento().forEach(agendamento -> model.addRow(
                new Object[]{
                        agendamento.getId(),
                        agendamento.getIdIdoso(),
                        agendamento.getDataAgendamento(),
                        agendamento.getHorario()}));
        return model;
    }

    private void validarCampos() {
        if (campoDataAgendamento.getText().trim().isEmpty()) {
            throw new IllegalArgumentException("O Campo Data Não pode ser vazio");
        }

        try {
            Date.valueOf(campoDataAgendamento.getText());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("A data deve estar no formato aaaa-mm-dd.");
        }

        if (campoHorario.getText().trim().isEmpty()) {
            throw new IllegalArgumentException("O Campo Hora Não pode ser vazio");
        }

        if (!campoHorario.getText().matches("\\d{2}:\\d{2}")) {
            throw new IllegalArgumentException("A hora deve estar no formato hh:mm.");
        }
    }

    private boolean agendamentoSelecionado() {
        int selectedRow = tabela.getSelectedRow();
        return selectedRow != -1;
    }

    private void salvar() {
        try {
            validarCampos();
            service.salvar(construirAgendamento());
            limparCampos();
            tabela.setModel(carregarDadosAgendamentos());
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro de Validação", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limparCampos() {
        campoId.setText("");
        campoDataAgendamento.setText("");
        campoHorario.setText("");
        tabela.clearSelection();
    }

    public void deletar() {
        if (!agendamentoSelecionado()) {
            JOptionPane.showMessageDialog(null, "Selecione um Agendamento para poder Cancelar!", "Erro de Validação", JOptionPane.ERROR_MESSAGE);
        } else {
            service.deletar(construirAgendamento());
            limparCampos();
            tabela.setModel(carregarDadosAgendamentos());
        }
    }

    private Agendamento construirAgendamento() {
        String nomeSelecionado = (String) comboBoxNomes.getSelectedItem();
        int idIdoso = service.obterIdIdosoPorNome(nomeSelecionado);

        return campoId.getText().isEmpty()
                ? new Agendamento(idIdoso, Date.valueOf(campoDataAgendamento.getText()),
                Time.valueOf(campoHorario.getText()))
                : new Agendamento(
                parseInt(campoId.getText()),
                idIdoso,
                Date.valueOf(campoDataAgendamento.getText()),
                Time.valueOf(campoHorario.getText()));
    }

    private void selecionarAgendamento(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            int selectedRow = tabela.getSelectedRow();
            if (selectedRow != -1) {
                var id = (Integer) tabela.getValueAt(selectedRow, 0);
                var nome = (String) tabela.getValueAt(selectedRow, 1);
                var dataVisita = (Integer) tabela.getValueAt(selectedRow, 2);
                var horaVisita = (String) tabela.getValueAt(selectedRow, 3);

                campoId.setText(id.toString());
                comboBoxNomes.setSelectedItem(nome);
                campoDataAgendamento.setText(String.valueOf(dataVisita));
                campoHorario.setText(horaVisita);
            }
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
