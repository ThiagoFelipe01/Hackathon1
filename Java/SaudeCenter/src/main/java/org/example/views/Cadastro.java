package org.example.views;

import javax.swing.*;
import java.awt.*;


public class Cadastro {
    private JLabel labelNome;
    private JTextField campoNome;
    private JLabel labelIdade;
    private JTextField campoIdade;
    private JLabel labelCpf;
    private JTextField campoCpf;
    private JLabel labelTelefone;
    private JTextField campoTelefone;
    private JLabel labelMedicamento;
    private JTextField campoMedicamento;

    private JPanel painel() {
        JPanel painelEntrada = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);

        labelNome = new JLabel("Digite seu nome");
        constraints.gridx = 0;
        constraints.gridy = 0;
        painelEntrada.add(labelNome, constraints);

        campoNome = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 0;
        painelEntrada.add(campoNome, constraints);

        labelIdade = new JLabel("Digite sua idade");
        constraints.gridx = 0;
        constraints.gridy = 1;
        painelEntrada.add(labelIdade, constraints);

        campoIdade = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 1;
        painelEntrada.add(campoIdade, constraints);

        labelCpf = new JLabel("Digite seu CPF");
        constraints.gridx = 0;
        constraints.gridy = 2;
        painelEntrada.add(labelCpf, constraints);

        campoCpf = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 2;
        painelEntrada.add(campoCpf, constraints);

        labelTelefone = new JLabel("Digite seu telefone");
        constraints.gridx = 0;
        constraints.gridy = 3;
        painelEntrada.add(labelTelefone, constraints);

        campoTelefone = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 3;
        painelEntrada.add(campoTelefone, constraints);

        labelMedicamento = new JLabel("Usa algum Medicamento");
        constraints.gridx = 0;
        constraints.gridy = 4;
        painelEntrada.add(labelMedicamento, constraints);

        campoMedicamento = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 4;
        painelEntrada.add(campoMedicamento, constraints);

        return painel();
    }

}
