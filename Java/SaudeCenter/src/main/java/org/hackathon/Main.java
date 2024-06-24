
import org.hackathon.views.MenuForm;

import javax.swing.*;


public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
        MenuForm menuForm = new MenuForm();
        menuForm.setVisible(true);
    });
}