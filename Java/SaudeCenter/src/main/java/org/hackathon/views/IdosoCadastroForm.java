package org.hackathon.views;

import org.hackathon.model.Idoso;
import org.hackathon.service.IdosoService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.Integer.parseInt;

public class IdosoCadastroForm extends JFrame {
    private IdosoService service;
    private JLabel labelId;
    private JTextField campoId;
    private JLabel labelNome;
    private JTextField campoNome;
    private JLabel labelIdade;
    private JTextField campoIdade;
    private JLabel labelCpf;
    private JTextField campoCpf;
    private JLabel labelEndereco;
    private JTextField campoEndereco;
    private JLabel labelTelefone;
    private JTextField campoTelefone;
    private JLabel labelTemHistMedico;
    private JLabel labelHistoricoMedico;
    private JTextField campoHistoricoMedico;
    private JLabel labelAlergia;
    private JTextField campoAlergia;
    private JLabel labelTemAlergia;
    private JLabel labelTemCondPreExistente;
    private JLabel labelCondicoesPreExistente;
    private JTextField campoCondicoesPreExistente;
    private JLabel labelObservacao;
    private JTextField campoObservacao;
    private JButton botaoCadastrar;
    private JButton botaoCancelar;
    private JRadioButton radioHistSim;
    private JRadioButton radioHistNao;
    private JRadioButton radioAlergiaSim;
    private JRadioButton radioAlergiaNao;
    private JRadioButton radioCondSim;
    private JRadioButton radioCondNao;

    public IdosoCadastroForm() {
        service = new IdosoService();
        createMenuBar();
        setContentPane(painel());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public JPanel painel() {
        setTitle("Tela de Cadastro");
        JPanel painelEntrada = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        painelEntrada.setPreferredSize(new Dimension(450, 520));
        painelEntrada.revalidate();
        constraints.insets = new Insets(10, 5, 5, 5);

        labelId = new JLabel("ID:");
        constraints.gridx = 0;
        constraints.gridy = 0;
        painelEntrada.add(labelId, constraints);

        campoId = new JTextField(20);
        campoId.setEnabled(false);
        constraints.gridx = 1;
        constraints.gridy = 0;
        painelEntrada.add(campoId, constraints);

        labelNome = new JLabel("Digite seu nome:");
        constraints.gridx = 0;
        constraints.gridy = 1;
        painelEntrada.add(labelNome, constraints);

        campoNome = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 1;
        painelEntrada.add(campoNome, constraints);

        labelIdade = new JLabel("Digite sua idade:");
        constraints.gridx = 0;
        constraints.gridy = 2;
        painelEntrada.add(labelIdade, constraints);

        campoIdade = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 2;
        painelEntrada.add(campoIdade, constraints);

        labelCpf = new JLabel("Digite seu CPF:");
        constraints.gridx = 0;
        constraints.gridy = 3;
        painelEntrada.add(labelCpf, constraints);

        campoCpf = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 3;
        painelEntrada.add(campoCpf, constraints);

        labelEndereco = new JLabel("Digite seu Endereço:");
        constraints.gridx = 0;
        constraints.gridy = 4;
        painelEntrada.add(labelEndereco, constraints);

        campoEndereco = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 4;
        painelEntrada.add(campoEndereco, constraints);

        labelTelefone = new JLabel("Digite seu telefone:");
        constraints.gridx = 0;
        constraints.gridy = 5;
        painelEntrada.add(labelTelefone, constraints);

        campoTelefone = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 5;
        painelEntrada.add(campoTelefone, constraints);

        labelTemHistMedico = new JLabel("Tem Histórico médico?");
        constraints.gridx = 0;
        constraints.gridy = 6;
        painelEntrada.add(labelTemHistMedico, constraints);

        JPanel painelRadioHist = new JPanel(new FlowLayout(FlowLayout.LEFT, 30, 0));
        radioHistSim = new JRadioButton("Sim");
        radioHistSim.addActionListener(e -> BtnSimNaoHistMedico(true));
        painelRadioHist.add(radioHistSim);

        radioHistNao = new JRadioButton("Não");
        radioHistNao.addActionListener(e -> BtnSimNaoHistMedico(false));
        painelRadioHist.add(radioHistNao);

        ButtonGroup grupoRadioHist = new ButtonGroup();
        grupoRadioHist.add(radioHistSim);
        grupoRadioHist.add(radioHistNao);

        constraints.gridx = 1;
        constraints.gridy = 6;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.WEST;
        painelEntrada.add(painelRadioHist, constraints);

        labelHistoricoMedico = new JLabel("Histórico:");
        constraints.gridx = 0;
        constraints.gridy = 7;
        labelHistoricoMedico.setVisible(false);
        painelEntrada.add(labelHistoricoMedico, constraints);

        campoHistoricoMedico = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 7;
        campoHistoricoMedico.setVisible(false);
        painelEntrada.add(campoHistoricoMedico, constraints);

        labelTemAlergia = new JLabel("Possui Alergia?");
        constraints.gridx = 0;
        constraints.gridy = 8;
        painelEntrada.add(labelTemAlergia, constraints);

        JPanel painelRadioAlergia = new JPanel(new FlowLayout(FlowLayout.LEFT, 30, 0));
        radioAlergiaSim = new JRadioButton("Sim");
        radioAlergiaSim.addActionListener(e -> BtnSimNaoAlergia(true));
        painelRadioAlergia.add(radioAlergiaSim);

        radioAlergiaNao = new JRadioButton("Não");
        radioAlergiaNao.addActionListener(e -> BtnSimNaoAlergia(false));
        painelRadioAlergia.add(radioAlergiaNao);

        ButtonGroup grupoRadioAlergia = new ButtonGroup();
        grupoRadioAlergia.add(radioAlergiaSim);
        grupoRadioAlergia.add(radioAlergiaNao);

        constraints.gridx = 1;
        constraints.gridy = 8;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.WEST;
        painelEntrada.add(painelRadioAlergia, constraints);

        labelAlergia = new JLabel("Alergia do que?");
        constraints.gridx = 0;
        constraints.gridy = 9;
        labelAlergia.setVisible(false);
        painelEntrada.add(labelAlergia, constraints);

        campoAlergia = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 9;
        campoAlergia.setVisible(false);
        painelEntrada.add(campoAlergia, constraints);

        labelTemCondPreExistente = new JLabel("Condições pré-existentes?");
        constraints.gridx = 0;
        constraints.gridy = 10;
        painelEntrada.add(labelTemCondPreExistente, constraints);

        JPanel painelRadioCond = new JPanel(new FlowLayout(FlowLayout.LEFT, 30, 0));
        radioCondSim = new JRadioButton("Sim");
        radioCondSim.addActionListener(e -> BtnSimNaoCondPreExistente(true));
        painelRadioCond.add(radioCondSim);

        radioCondNao = new JRadioButton("Não");
        radioCondNao.addActionListener(e -> BtnSimNaoCondPreExistente(false));
        painelRadioCond.add(radioCondNao);

        ButtonGroup grupoRadioCond = new ButtonGroup();
        grupoRadioCond.add(radioCondSim);
        grupoRadioCond.add(radioCondNao);

        constraints.gridx = 1;
        constraints.gridy = 10;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.WEST;
        painelEntrada.add(painelRadioCond, constraints);

        labelCondicoesPreExistente = new JLabel("Condição pré-existente:");
        constraints.gridx = 0;
        constraints.gridy = 11;
        labelCondicoesPreExistente.setVisible(false);
        painelEntrada.add(labelCondicoesPreExistente, constraints);

        campoCondicoesPreExistente = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 11;
        campoCondicoesPreExistente.setVisible(false);
        painelEntrada.add(campoCondicoesPreExistente, constraints);

        labelObservacao = new JLabel("Observações:");
        constraints.gridx = 0;
        constraints.gridy = 12;
        painelEntrada.add(labelObservacao, constraints);

        campoObservacao = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 12;
        painelEntrada.add(campoObservacao, constraints);

        botaoCadastrar = new JButton("Cadastrar");
        botaoCadastrar.addActionListener(e -> salvar());
        constraints.gridx = 1;
        constraints.gridy = 13;
        constraints.anchor = GridBagConstraints.EAST;
        painelEntrada.add(botaoCadastrar, constraints);

        botaoCancelar = new JButton("Cancelar");
        botaoCancelar.addActionListener(e -> limpaCampos());
        constraints.gridx = 0;
        constraints.gridy = 13;
        constraints.anchor = GridBagConstraints.WEST;
        painelEntrada.add(botaoCancelar, constraints);

        return painelEntrada;
    }

    private void BtnSimNaoHistMedico(boolean visible) {
        labelHistoricoMedico.setVisible(visible);
        campoHistoricoMedico.setVisible(visible);
    }

    private void BtnSimNaoAlergia(boolean visible) {
        labelAlergia.setVisible(visible);
        campoAlergia.setVisible(visible);
    }

    private void BtnSimNaoCondPreExistente(boolean visible) {
        labelCondicoesPreExistente.setVisible(visible);
        campoCondicoesPreExistente.setVisible(visible);
    }

    public void validarCampos() {
        if (campoNome.getText().trim().isEmpty()) {
            throw new IllegalArgumentException("O campo nome não pode ser vazio");
        }

        if (!campoNome.getText().matches("[a-zA-Z\\s]+")) {
            throw new IllegalArgumentException("O campo nome deve conter apenas letras");
        }

        if (campoIdade.getText().trim().isEmpty()) {
            throw new IllegalArgumentException("O campo Idade Não pode ser vazio");
        }

        if (!campoIdade.getText().matches("\\d+")) {
            throw new IllegalArgumentException("O campo Idade deve conter apenas um número inteiro");
        }

        if (campoCpf.getText().trim().isEmpty()) {
            throw new IllegalArgumentException("O campo CPF Não pode ser vazio");
        }

        if (!campoCpf.getText().matches("\\d+")) {
            throw new IllegalArgumentException("O campo CPF deve conter apenas números");
        }

        if (campoCpf.getText().length() != 11) {
            throw new IllegalArgumentException("O campo CPF deve conter exatamente 11 dígitos");
        }

        if (campoTelefone.getText().trim().isEmpty()) {
            throw new IllegalArgumentException("O campo Telefone Não pode ser vazio");
        }

        if (!campoTelefone.getText().matches("\\d+")) {
            throw new IllegalArgumentException("O campo Telefone deve conter apenas números");
        }

        if (campoTelefone.getText().length() != 11) {
            throw new IllegalArgumentException("O campo Telefone deve conter exatamente 11 dígitos");
        }

    }

    public void voltar() {
        MenuForm menuForm = new MenuForm();
        menuForm.setVisible(true);
        dispose();
    }

    private void salvar() {
        try {
            validarCampos();
            service.salvar(construirIdoso());
            limpaCampos();
            JOptionPane.showMessageDialog(this, "Cadastrado com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro de Validação", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar dados: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void limpaCampos() {
        campoNome.setText("");
        campoIdade.setText("");
        campoCpf.setText("");
        campoEndereco.setText("");
        campoTelefone.setText("");
        campoHistoricoMedico.setText("");
        campoAlergia.setText("");
        campoCondicoesPreExistente.setText("");
        campoObservacao.setText("");

    }

    private Idoso construirIdoso() {
        return campoId.getText().isEmpty()
                ? new Idoso(campoNome.getText(), parseInt(campoIdade.getText()), campoCpf.getText(), campoEndereco.getText(),
                campoTelefone.getText(), campoHistoricoMedico.getText(),
                campoAlergia.getText(), campoCondicoesPreExistente.getText(), campoObservacao.getText())
                : new Idoso(
                parseInt(campoId.getText()),
                campoNome.getText(),
                parseInt(campoIdade.getText()),
                campoCpf.getText(),
                campoEndereco.getText(),
                campoTelefone.getText(),
                campoHistoricoMedico.getText(),
                campoAlergia.getText(),
                campoCondicoesPreExistente.getText(),
                campoObservacao.getText());
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

    public static class Main {
        public static void main(String[] args) {
            SwingUtilities.invokeLater(IdosoCadastroForm::new);
        }
    }
}
