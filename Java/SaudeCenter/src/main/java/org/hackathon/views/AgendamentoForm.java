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
import java.sql.SQLException;
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

        setSize(450, 450);

        getContentPane().add(montarPainelEntrada(), BorderLayout.NORTH);
        getContentPane().add(montarPainelSaida(), BorderLayout.CENTER);

        createMenuBar();

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

        List<String> nomesIdosos = service.listarNomesIdosos();

        comboBoxNomes = new JComboBox<>(nomesIdosos.toArray(new String[0])); ;
        comboBoxNomes.setSelectedItem(null);
        comboBoxNomes.setPreferredSize(new Dimension(200, comboBoxNomes.getPreferredSize().height));
        comboBoxNomes.setEditable(false);
        constraints.gridx = 1;
        constraints.gridy = 1;
        painelEntrada.add(comboBoxNomes, constraints);

        labelDataAgendamento = new JLabel("Data Visita:");
        constraints.gridx = 0;
        constraints.gridy = 2;
        painelEntrada.add(labelDataAgendamento, constraints);

        campoDataAgendamento = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 2;
        painelEntrada.add(campoDataAgendamento, constraints);

        labelHorario = new JLabel("Hora Visita:");
        constraints.gridx = 0;
        constraints.gridy = 3;
        painelEntrada.add(labelHorario, constraints);

        campoHorario = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 3;
        painelEntrada.add(campoHorario, constraints);

        botaoSalvar = new JButton("Salvar / Editar");
        botaoSalvar.addActionListener(e -> salvar());
        constraints.gridx = 0;
        constraints.gridy = 4;
        painelEntrada.add(botaoSalvar, constraints);

        botaoLimpar = new JButton("Limpar Campos");
        botaoLimpar.addActionListener(e -> limparCampos());
        constraints.gridx = 1;
        constraints.gridy = 4;
        painelEntrada.add(botaoLimpar, constraints);

        botaoDeletar = new JButton("Cancelar");
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
