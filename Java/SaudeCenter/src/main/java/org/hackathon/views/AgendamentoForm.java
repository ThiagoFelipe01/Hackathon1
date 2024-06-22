package org.hackathon.views;

import org.hackathon.service.AgendamentoService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

public class AgendamentoForm extends JFrame {
    private AgendamentoService service;
    private JLabel labelNome;
    private JTextField campoNome;
    private JLabel labelDiaVisita;
    private JComboBox<Integer> comboDiaVisita;
    private JLabel labelMesVisita;
    private JComboBox<String> comboMesVisita;
    private JLabel labelAnoVisita;
    private JComboBox<Integer> comboAnoVisita;
    private JLabel labelHoraVisita;
    private JComboBox<String> comboHoraVisita;
    private JButton botaoAgendar;
    private JButton botaoVoltar;
    private JLabel labelCadastro;

    public JPanel painel() {
        setTitle("Tela de Agendamento");
        JPanel painelEntrada = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        painelEntrada.setPreferredSize(new Dimension(400, 300));
        painelEntrada.revalidate();
        constraints.insets = new Insets(5, 5, 5, 5);

        botaoVoltar = new JButton("Voltar");
        botaoVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                voltar();
                dispose();
            }
        });
        constraints.gridx = 0;
        constraints.gridy = 0;
        painelEntrada.add(botaoVoltar, constraints);

        labelNome = new JLabel("Nome:");
        constraints.gridx = 0;
        constraints.gridy = 1;
        painelEntrada.add(labelNome, constraints);

        campoNome = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 1;
        painelEntrada.add(campoNome, constraints);

        labelDiaVisita = new JLabel("Dia da Visita:");
        constraints.gridx = 0;
        constraints.gridy = 2;
        painelEntrada.add(labelDiaVisita, constraints);

        comboDiaVisita = new JComboBox<>();
        for (int i = 1; i <= 30; i++) {
            comboDiaVisita.addItem(i);
        }
        constraints.gridx = 1;
        constraints.gridy = 2;
        painelEntrada.add(comboDiaVisita, constraints);

        labelMesVisita = new JLabel("Mês da Visita:");
        constraints.gridx = 0;
        constraints.gridy = 3;
        painelEntrada.add(labelMesVisita, constraints);

        comboMesVisita = new JComboBox<>(new String[]{"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho",
                "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"});
        constraints.gridx = 1;
        constraints.gridy = 3;
        painelEntrada.add(comboMesVisita, constraints);

        labelAnoVisita = new JLabel("Ano da Visita:");
        constraints.gridx = 0;
        constraints.gridy = 4;
        painelEntrada.add(labelAnoVisita, constraints);

        comboAnoVisita = new JComboBox<>();
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        comboAnoVisita.addItem(currentYear);
        comboAnoVisita.addItem(currentYear + 1);
        constraints.gridx = 1;
        constraints.gridy = 4;
        painelEntrada.add(comboAnoVisita, constraints);

        labelHoraVisita = new JLabel("Hora da Visita:");
        constraints.gridx = 0;
        constraints.gridy = 5;
        painelEntrada.add(labelHoraVisita, constraints);

        comboHoraVisita = new JComboBox<>();
        for (int hora = 8; hora <= 18; hora++) {
            String horaFormatada = String.format("%02d:00", hora);
            comboHoraVisita.addItem(horaFormatada);
        }
        constraints.gridx = 1;
        constraints.gridy = 5;
        painelEntrada.add(comboHoraVisita, constraints);

        botaoAgendar = new JButton("Agendar Visita");
        botaoAgendar.addActionListener(e -> salvar());
        constraints.gridx = 1;
        constraints.gridy = 7;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        painelEntrada.add(botaoAgendar, constraints);

        return painelEntrada;
    }

    public void validarCampos() {

    }

    private void salvar() {
        try {
            validarCampos();
            //service.salvar(construirIdoso());
            limpaCampos();
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro de Validação", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpaCampos() {
        campoNome.setText("");
    }

    public void voltar() {
        MenuForm menuForm = new MenuForm();
        menuForm.setVisible(true);
    }


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