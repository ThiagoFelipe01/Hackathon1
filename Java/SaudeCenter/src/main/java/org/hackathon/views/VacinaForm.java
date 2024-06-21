package org.hackathon.views;

import org.hackathon.model.Vacina;
import org.hackathon.service.VacinaService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class VacinaForm extends JFrame {
    private VacinaService vacinaService;
    private JTable tableVacinas;
    private DefaultTableModel model;

    public VacinaForm(Connection conn) {
        this.vacinaService = new VacinaService(conn);
        initComponents();
        loadVacinas();
    }

    private void initComponents() {
        setTitle("Cadastro de Vacinas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);

        JPanel panel = new JPanel(new BorderLayout());
        getContentPane().add(panel, BorderLayout.CENTER);

        model = new DefaultTableModel(new String[]{"ID", "Nome", "Descrição", "Intervalo Recomendado (meses)"}, 0);
        tableVacinas = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(tableVacinas);
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel panelButtons = new JPanel();
        panel.add(panelButtons, BorderLayout.SOUTH);

        JButton btnAdicionar = new JButton("Adicionar");
        btnAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirFormularioCadastro();
            }
        });
        panelButtons.add(btnAdicionar);

        JButton btnEditar = new JButton("Editar");
        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = tableVacinas.getSelectedRow();
                if (row != -1) {
                    int idVacina = (int) tableVacinas.getValueAt(row, 0);
                    abrirFormularioEdicao(idVacina);
                } else {
                    JOptionPane.showMessageDialog(VacinaForm.this, "Selecione uma vacina para editar.", "Editar Vacina", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        panelButtons.add(btnEditar);

        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = tableVacinas.getSelectedRow();
                if (row != -1) {
                    int idVacina = (int) tableVacinas.getValueAt(row, 0);
                    excluirVacina(idVacina);
                } else {
                    JOptionPane.showMessageDialog(VacinaForm.this, "Selecione uma vacina para excluir.", "Excluir Vacina", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        panelButtons.add(btnExcluir);

        setLocationRelativeTo(null);
    }

    private void loadVacinas() {
        model.setRowCount(0);
        try {
            List<Vacina> vacinas = vacinaService.getAllVacinas();
            for (Vacina vacina : vacinas) {
                Object[] row = {vacina.getId(), vacina.getNome(), vacina.getDescricao(), vacina.getIntervaloRecomendado()};
                model.addRow(row);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar vacinas: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void abrirFormularioCadastro() {
        VacinaDialog dialog = new VacinaDialog(this, "Cadastro de Vacina", true, null);
        dialog.setVisible(true);

        if (dialog.isConfirmed()) {
            Vacina vacina = dialog.getVacina();
            try {
                vacinaService.inserirVacina(vacina);
                JOptionPane.showMessageDialog(this, "Vacina cadastrada com sucesso!", "Cadastro de Vacina", JOptionPane.INFORMATION_MESSAGE);
                loadVacinas();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Erro ao cadastrar vacina: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void abrirFormularioEdicao(int idVacina) {
        try {
            Vacina vacina = vacinaService.getVacinaById(idVacina);
            VacinaDialog dialog = new VacinaDialog(this, "Edição de Vacina", true, vacina);
            dialog.setVisible(true);
            if (dialog.isConfirmed()) {
                vacina = dialog.getVacina();
                vacinaService.atualizarVacina(vacina);
                JOptionPane.showMessageDialog(this, "Vacina atualizada com sucesso!", "Edição de Vacina", JOptionPane.INFORMATION_MESSAGE);
                loadVacinas();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao editar vacina: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void excluirVacina(int idVacina) {
        try {
            int confirmacao = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja excluir esta vacina?", "Excluir Vacina", JOptionPane.YES_NO_OPTION);
            if (confirmacao == JOptionPane.YES_OPTION) {
                vacinaService.excluirVacina(idVacina);
                JOptionPane.showMessageDialog(this, "Vacina excluída com sucesso!", "Excluir Vacina", JOptionPane.INFORMATION_MESSAGE);
                loadVacinas();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao excluir vacina: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            Connection conn = null;
            VacinaForm form = new VacinaForm(conn);
            form.setVisible(true);
        });
    }
}
