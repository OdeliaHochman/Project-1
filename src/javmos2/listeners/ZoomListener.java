package javmos2.listeners;

import java.awt.event.ActionEvent;
<<<<<<< HEAD

import java.awt.event.ActionListener;
import javmos2.GraphGUI;
import javmos2_components.JavmosPanel;

public class ZoomListener implements ActionListener {
    
    private final int ZOOM_FACTOR = 10;
    private final GraphGUI gui;
    private final JavmosPanel panel;

    public ZoomListener(GraphGUI gui, JavmosPanel panel) {
=======
import java.awt.event.ActionListener;
import javmos2.JavmosGUI;
import javmos2.components.JavmosPanel;

public class ZoomListener implements ActionListener {
    
    private final int ZOOM_FACTOR = 10;
    private final JavmosGUI gui;
    private final JavmosPanel panel;

    public ZoomListener(JavmosGUI gui, JavmosPanel panel) {
>>>>>>> branch 'master' of https://github.com/OdeliaHochman/Project-1.git
        this.gui = gui;
        this.panel = panel;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        String command = event.getActionCommand();

        if (command.equals("+")) {
            gui.setZoom(gui.getZoom() + ZOOM_FACTOR);
        } else if (gui.getZoom() - ZOOM_FACTOR > 0) {
            gui.setZoom(gui.getZoom() - ZOOM_FACTOR);
        }
        panel.repaint();
    }
}
