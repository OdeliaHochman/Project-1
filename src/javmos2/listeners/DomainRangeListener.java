package javmos2.listeners;

import java.awt.event.ActionEvent;
<<<<<<< HEAD

import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javmos2_components.JavmosPanel;
=======
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javmos2.components.JavmosPanel;
>>>>>>> branch 'master' of https://github.com/OdeliaHochman/Project-1.git

public class DomainRangeListener implements ActionListener {

    private final JavmosPanel panel;

    public DomainRangeListener(JavmosPanel panel) {
        this.panel = panel;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        try {
            Double.parseDouble(((JTextField) event.getSource()).getText());

        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, ((JTextField) event.getSource()).getText() + " is an invalid domain or range value", "Domain/Range Error", JOptionPane.ERROR_MESSAGE);
        }
        panel.repaint();
    }
}
