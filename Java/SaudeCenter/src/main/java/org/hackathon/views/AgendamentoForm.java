package org.hackathon.views;

import javax.swing.*;
import java.awt.*;


public class AgendamentoForm extends JFrame {
    private JLabel labelNome;
    private JTextField campoNome;
    private JLabel labelDataVisita;
    private JComboBox<String> comboDataVisita;
    private JLabel labelHoraVisita;
    private JComboBox<String> comboHoraVisita;
    private JLabel labelTipoVisita;
    private JTextField campoTipoVisita;
    private JButton botaoAgendar;

    public JPanel painel() {
        setTitle("Tela de Agendamento");
        JPanel painelEntrada = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        painelEntrada.setPreferredSize(new Dimension(400, 300));
        painelEntrada.revalidate();
        constraints.insets = new Insets(5, 5, 5, 5);

        labelNome = new JLabel("Nome Completo:");
        constraints.gridx = 0;
        constraints.gridy = 0;
        painelEntrada.add(labelNome, constraints);

        campoNome = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 0;
        painelEntrada.add(campoNome, constraints);

        labelDataVisita = new JLabel("Data da Visita:");
        constraints.gridx = 0;
        constraints.gridy = 1;
        painelEntrada.add(labelDataVisita, constraints);

        comboDataVisita = new JComboBox<>(new String[]{"10/07/2024", "11/07/2024"});
        constraints.gridx = 1;
        constraints.gridy = 1;
        painelEntrada.add(comboDataVisita, constraints);

        labelHoraVisita = new JLabel("Hora da Visita:");
        constraints.gridx = 0;
        constraints.gridy = 2;
        painelEntrada.add(labelHoraVisita, constraints);

        comboHoraVisita = new JComboBox<>(new String[]{"08:00", "09:00"});
        constraints.gridx = 1;
        constraints.gridy = 2;
        painelEntrada.add(comboHoraVisita, constraints);

        labelTipoVisita = new JLabel("Tipo de Visita:");
        constraints.gridx = 0;
        constraints.gridy = 3;
        painelEntrada.add(labelTipoVisita, constraints);

        campoTipoVisita = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 3;
        painelEntrada.add(campoTipoVisita, constraints);

        botaoAgendar = new JButton("Agendar Visita");
        //botaoAgendar.addActionListener(e -> agendar());
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        painelEntrada.add(botaoAgendar, constraints);

        return painelEntrada;
    }



    public static class Main {
        public static void main(String[] args) {
            SwingUtilities.invokeLater(() -> {
                JFrame frame = new JFrame("Agendamento de Visita");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                AgendamentoForm agendamentoForm = new AgendamentoForm();
                frame.getContentPane().add(agendamentoForm.painel());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            });
        }
    }
}

