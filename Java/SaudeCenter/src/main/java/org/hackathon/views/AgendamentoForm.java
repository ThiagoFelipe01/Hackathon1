package org.hackathon.views;

import org.hackathon.model.Agendamento;
import org.hackathon.service.AgendamentoService;
import org.hackathon.service.IdosoService;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.Time;

import static java.lang.Integer.parseInt;

public class AgendamentoForm extends JFrame {

    private AgendamentoService service;
    private JLabel labelId;
    private JTextField campoId;
    private JLabel labelNome;
    private JTextField campoNome;
    private JLabel labelData;
    private JTextField campoDataVisita;
    private JLabel labelHora;
    private JTextField campoHoraVisita;
    private JButton botaoSalvar;
    private JButton botaoLimpar;
    private JButton botaoDeletar;
    private JTable tabela;


    public AgendamentoForm() {
        service = new AgendamentoService();

        setTitle("Agendamento de Visitas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 450);

        getContentPane().add(montarPainelEntrada(), BorderLayout.NORTH);
        getContentPane().add(montarPainelSaida(), BorderLayout.CENTER);

        setLocationRelativeTo(null);

        service = new AgendamentoService();
        createMenuBar();
        setContentPane(montarPainelEntrada());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel montarPainelSaida() {
        JPanel painelSaida = new JPanel(new BorderLayout());

        tabela = new JTable();
        tabela.setDefaultEditor(Object.class, null);
        tabela.getTableHeader().setReorderingAllowed(false);
        tabela.setModel(carregarDadosAgendamentos());
        tabela.getSelectionModel().addListSelectionListener(this::selecionarAgendamento);

        JScrollPane scrollPane = new JScrollPane(tabela);
        painelSaida.add(scrollPane, BorderLayout.CENTER);
        return painelSaida;
    }

    private JPanel montarPainelEntrada() {
        JPanel painelEntrada = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);

        labelId = new JLabel("ID:");
        constraints.gridx = 0;
        constraints.gridy = 0;
        painelEntrada.add(labelId, constraints);

        campoId = new JTextField(20);
        campoId.setEnabled(false);
        constraints.gridx = 1;
        constraints.gridy = 0;
        painelEntrada.add(campoId, constraints);

        labelNome = new JLabel("Nome:");
        constraints.gridx = 0;
        constraints.gridy = 1;
        painelEntrada.add(labelNome, constraints);

        campoNome = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 1;
        painelEntrada.add(campoNome, constraints);

        labelData = new JLabel("Data:");
        constraints.gridx = 0;
        constraints.gridy = 2;
        painelEntrada.add(labelData, constraints);

        campoDataVisita = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 2;
        painelEntrada.add(campoDataVisita, constraints);

        labelHora = new JLabel("Hora:");
        constraints.gridx = 0;
        constraints.gridy = 3;
        painelEntrada.add(labelHora, constraints);

        campoHoraVisita = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 3;
        painelEntrada.add(campoHoraVisita, constraints);

        botaoSalvar = new JButton("Salvar/Editar");
        botaoSalvar.addActionListener(e -> salvar());
        constraints.gridx = 0;
        constraints.gridy = 4;
        painelEntrada.add(botaoSalvar, constraints);

        botaoLimpar = new JButton("Limpar Campos");
        botaoLimpar.addActionListener(e -> limparCampos());
        constraints.gridx = 1;
        constraints.gridy = 4;
        painelEntrada.add(botaoLimpar, constraints);

        botaoDeletar = new JButton("Deletar");
        botaoDeletar.addActionListener(e -> deletar());
        constraints.gridx = 2;
        constraints.gridy = 4;
        painelEntrada.add(botaoDeletar, constraints);

        return painelEntrada;
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
                        agendamento.getNome(),
                        agendamento.getDataVisita(),
                        agendamento.getHoraVisita()}));
        return model;
    }

    private void validarCampos() {
        if (campoNome.getText().trim().isEmpty()) {
            throw new IllegalArgumentException("O Campo Nome Não pode ser vazio");
        }

        if (!campoNome.getText().matches("[a-zA-Z\\s]+")) {
            throw new IllegalArgumentException("O nome deve conter apenas letras.");
        }

        if (campoDataVisita.getText().trim().isEmpty()) {
            throw new IllegalArgumentException("O Campo Data Não pode ser vazio");
        }

        try {
            Date.valueOf(campoDataVisita.getText());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("A data deve estar no formato aaaa-mm-dd.");
        }

        if (campoHoraVisita.getText().trim().isEmpty()) {
            throw new IllegalArgumentException("O Campo Hora Não pode ser vazio");
        }

        if (!campoHoraVisita.getText().matches("\\d{2}:\\d{2}")) {
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
        campoNome.setText("");
        campoDataVisita.setText("");
        campoHoraVisita.setText("");
        tabela.clearSelection();
    }

    public void deletar() {
        if (!agendamentoSelecionado()) {
            JOptionPane.showMessageDialog(null, "Selecione um diretor para poder deletar!", "Erro de Validação", JOptionPane.ERROR_MESSAGE);
        } else {
            service.deletar(construirAgendamento());
            limparCampos();
            tabela.setModel(carregarDadosAgendamentos());
        }
    }

    private Agendamento construirAgendamento() {
        return campoId.getText().isEmpty()
                ? new Agendamento(campoNome.getText(), Date.valueOf(campoDataVisita.getText()),
                Time.valueOf(campoHoraVisita.getText()))
                : new Agendamento(
                parseInt(campoId.getText()),
                campoNome.getText(),
                Date.valueOf(campoDataVisita.getText()),
                Time.valueOf(campoHoraVisita.getText()));
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
                campoNome.setText(nome);
                campoDataVisita.setText(String.valueOf(dataVisita));
                campoHoraVisita.setText(horaVisita);
            }
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

    public void voltar() {
        MenuForm menuForm = new MenuForm();
        menuForm.setVisible(true);
        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AgendamentoForm form = new AgendamentoForm();
            form.setVisible(true);
        });
    }
}
