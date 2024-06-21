package org.hackathon.views;

import org.hackathon.model.Idoso;
import org.hackathon.service.IdosoService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

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
    private JLabel labelTelefone;
    private JTextField campoTelefone;
    private JLabel labelSimNao;
    private JLabel labelMedicamento;
    private JTextField campoMedicamento;
    private JButton botaoCadastrar;
    private JButton botaoCancelar;
    private JButton botaoVoltar;
    private JRadioButton radioSim;
    private JRadioButton radioNao;

    public JPanel painel() {
        setTitle("Tela de Cadastro");
        JPanel painelEntrada = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        painelEntrada.setPreferredSize(new Dimension(370, 300));
        painelEntrada.revalidate();
        constraints.insets = new Insets(5, 5, 5, 5);

        /*labelId = new JLabel("ID");
        constraints.gridx = 0;
        constraints.gridy = 0;
        labelId.setVisible(false);
        painelEntrada.add(labelId, constraints);

        campoId = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 0;
        labelId.setVisible(false);
        painelEntrada.add(campoId, constraints);*/

        botaoVoltar = new JButton("Voltar");
        botaoVoltar.addActionListener(e -> voltar());
        constraints.gridx = 0;
        constraints.gridy = 0;
        painelEntrada.add(botaoVoltar, constraints);

        labelNome = new JLabel("Digite seu nome");
        constraints.gridx = 0;
        constraints.gridy = 1;
        painelEntrada.add(labelNome, constraints);

        campoNome = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 1;
        painelEntrada.add(campoNome, constraints);

        labelIdade = new JLabel("Digite sua idade");
        constraints.gridx = 0;
        constraints.gridy = 2;
        painelEntrada.add(labelIdade, constraints);

        campoIdade = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 2;
        painelEntrada.add(campoIdade, constraints);

        labelCpf = new JLabel("Digite seu CPF");
        constraints.gridx = 0;
        constraints.gridy = 3;
        painelEntrada.add(labelCpf, constraints);

        campoCpf = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 3;
        painelEntrada.add(campoCpf, constraints);

        labelTelefone = new JLabel("Digite seu telefone");
        constraints.gridx = 0;
        constraints.gridy = 4;
        painelEntrada.add(labelTelefone, constraints);

        campoTelefone = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 4;
        painelEntrada.add(campoTelefone, constraints);

        labelSimNao = new JLabel("Voce Usa algum Medicamento?");
        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        painelEntrada.add(labelSimNao, constraints);

        radioSim = new JRadioButton("Sim");
        radioSim.addActionListener(this::acaoBotaoSim);
        constraints.gridx = 0;
        constraints.gridy = 6;
        painelEntrada.add(radioSim, constraints);

        radioNao = new JRadioButton("Não");
        radioNao.addActionListener(this::acaoBotaoNao);
        constraints.gridx = 1;
        constraints.gridy = 6;
        painelEntrada.add(radioNao, constraints);

        ButtonGroup grupoRadio = new ButtonGroup();
        grupoRadio.add(radioSim);
        grupoRadio.add(radioNao);

        constraints.gridx = 0;
        constraints.gridy = 6;


        labelMedicamento = new JLabel("Nome Medicamento");
        constraints.gridx = 0;
        constraints.gridy = 7;
        constraints.anchor = GridBagConstraints.WEST;
        labelMedicamento.setVisible(false);
        painelEntrada.add(labelMedicamento, constraints);

        campoMedicamento = new JTextField(18);
        constraints.gridx = 1;
        constraints.gridy = 7;
        constraints.anchor = GridBagConstraints.EAST;
        campoMedicamento.setVisible(false);
        painelEntrada.add(campoMedicamento, constraints);


        botaoCadastrar = new JButton("Cadatrar");
        botaoCadastrar.addActionListener(e -> salvar());
        constraints.gridx = 1;
        constraints.gridy = 8;
        constraints.anchor = GridBagConstraints.EAST;
        painelEntrada.add(botaoCadastrar, constraints);

        botaoCancelar = new JButton("Cancelar");
        botaoCancelar.addActionListener(e -> limpaCampos());
        constraints.gridx = 0;
        constraints.gridy = 8;
        constraints.anchor = GridBagConstraints.WEST;
        painelEntrada.add(botaoCancelar, constraints);

        return painelEntrada;
    }

    private void acaoBotaoSim(ActionEvent e) {
        if (radioSim.isSelected()) {
            labelMedicamento.setVisible(true);
            campoMedicamento.setVisible(true);
        }
    }

    private void acaoBotaoNao(ActionEvent e) {
        if (radioNao.isSelected()) {
            labelMedicamento.setVisible(false);
            campoMedicamento.setVisible(false);
        }
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

    private void salvar() {
        try {
            validarCampos();
            service.salvar(construirIdoso());
            limpaCampos();
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro de Validação", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpaCampos() {
        campoNome.setText("");
        campoIdade.setText("");
        campoCpf.setText("");
        campoTelefone.setText("");
    }

    public void  voltar() {
        MenuForm menuForm = new MenuForm();
        menuForm.setVisible(true);
        dispose();
    }

    private Idoso construirIdoso() {
        return campoId.getText().isEmpty()
                ? new Idoso(campoNome.getText(), parseInt(campoCpf.getText()), parseInt(campoIdade.getText()),
                parseInt(campoTelefone.getText()), campoMedicamento.getText())
                : new Idoso(
                parseInt(campoId.getText()),
                campoNome.getText(),
                parseInt(campoIdade.getText()),
                parseInt(campoCpf.getText()),
                parseInt(campoTelefone.getText()),
                campoMedicamento.getText()
        );
    }

    public static class Main {
        public static void main(String[] args) {
            SwingUtilities.invokeLater(() -> {
                JFrame frame = new JFrame("Cadastro de Idoso");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                IdosoCadastroForm cadastroForm = new IdosoCadastroForm();
                frame.getContentPane().add(cadastroForm.painel());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            });
        }
    }
}
