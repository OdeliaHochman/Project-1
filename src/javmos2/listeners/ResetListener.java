package javmos2.listeners;

import java.awt.event.ActionEvent;
<<<<<<< HEAD

import java.awt.event.ActionListener;
import javmos2.GraphGUI;
import javmos2_components.JavmosPanel;

public class ResetListener implements ActionListener {

    private final GraphGUI gui;
    private final JavmosPanel panel;

    public ResetListener(GraphGUI gui, JavmosPanel panel) {
=======
import java.awt.event.ActionListener;
import javmos2.JavmosGUI;
import javmos2.components.JavmosPanel;

public class ResetListener implements ActionListener {

    private final JavmosGUI gui;
    private final JavmosPanel panel;

    public ResetListener(JavmosGUI gui, JavmosPanel panel) {
>>>>>>> branch 'master' of https://github.com/OdeliaHochman/Project-1.git
        this.gui = gui;
        this.panel = panel;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        gui.resetFields();
        panel.repaint();
    }
}
