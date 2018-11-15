package javmos2.components;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseListener;
import java.util.HashSet;
import java.util.LinkedList;
import javax.swing.JPanel;
import javmos2.JavmosGUI;
import javmos2.components.functions.Function;
import javmos2.listeners.PointClickListener;

public class JavmosPanel extends JPanel {

    public final JavmosGUI gui;
    public final java.util.LinkedList<JavmosComponent> components;

    public JavmosPanel(JavmosGUI gui) {
        this.gui = gui;
        components = new LinkedList<>();
    }

    public Function getFunction() {
        for (JavmosComponent j : components) {
            if (j instanceof Function) {
                return (Function) j;
            }
        }
        return null;
    }

    public void setPlane(CartesianPlane plane) {
        components.add(plane);
    }

    public void setFunction(Function function) {
        components.clear();
        components.add(function);
        components.addAll(function.getXIntercepts());
        components.addAll(function.getCriticalPoints());
        components.addAll(function.getInflectionPoints());
    }

    @Override
    //Paints the plane and polynomial on the panel
    public void paintComponent(Graphics graphics) {
    	try {
    		
        super.paintComponent(graphics);
        setPlane(new CartesianPlane(gui));
        for (JavmosComponent p : components) {
            if (p instanceof CartesianPlane) {
                p.draw((Graphics2D) graphics);
            }
        }
        PointClickListener m = (PointClickListener) getListeners(MouseListener.class)[0];
        for (JavmosComponent j : components) {
            if (j instanceof Function) {
                Function function = (Function) j;
                function.draw((Graphics2D) graphics);
            } else if (j instanceof Point) {
                Point point = (Point) j;
                point.draw((Graphics2D) graphics);
                m.setPoint(point);
            }
        }
    }
    catch(Exception e) {
    	
    }
    }
}
