package javmos2;

import javmos2.enums.RootType;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javmos2.listeners.DomainRangeListener;
import javmos2.listeners.DrawListener;
import javmos2.listeners.PointClickListener;
import javmos2.listeners.QuitListener;
import javmos2.listeners.ResetListener;
import javmos2.listeners.ZoomListener;
import javmos2.components.JavmosPanel;

public final class JavmosGUI {

    // Create GUI's constants
    private final int PLANE_PANEL_WIDTH = 700;
    private final int PLANE_PANEL_HEIGHT = 700;
    private final int CONTROL_PANEL_WIDTH = PLANE_PANEL_WIDTH / 2;
    private final int CONTROL_PANEL_HEIGHT = PLANE_PANEL_HEIGHT;
    private final int CONTROL_PANEL_ITEMS = 9;
    private final double MIN_DOMAIN = -PLANE_PANEL_WIDTH / 2;
    private final double MAX_DOMAIN = PLANE_PANEL_WIDTH / 2;
    private final double MIN_RANGE = -PLANE_PANEL_HEIGHT / 2;
    private final double MAX_RANGE = PLANE_PANEL_HEIGHT / 2;
    private final double DOMAIN_STEP = 1;
    private final double RANGE_STEP = 1;
    private final double ZOOM = 50;
    // Create GUI's container components
    private final JFrame frame = new JFrame("Graph");
    private final JPanel mainPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
    private final JavmosPanel javmosPanel = new JavmosPanel(this);
    private final JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
    private final JPanel domainStepPanel = new JPanel(new GridLayout(2, 1));
    private final JPanel rangeStepPanel = new JPanel(new GridLayout(2, 1));
    // Create GUI's control components
    public final JTextField equationField = new JTextField("Type function here and press ENTER key");
    private final JTextField domainStepField = new JTextField(Double.toString(DOMAIN_STEP));
    private final JTextField minDomainField = new JTextField(Double.toString(MIN_DOMAIN));
    private final JTextField maxDomainField = new JTextField(Double.toString(MAX_DOMAIN));
    private final JTextField rangeStepField = new JTextField(Double.toString(RANGE_STEP));
    private final JTextField minRangeField = new JTextField(Double.toString(MIN_RANGE));
    private final JTextField maxRangeField = new JTextField(Double.toString(MAX_RANGE));
    
    private final JLabel firstDerivativeLabel = new JLabel("First Derivative", SwingConstants.CENTER);
    private final JLabel secondDerivativeLabel = new JLabel("Second Derivative", SwingConstants.CENTER);
    private final JLabel pointLabel = new JLabel("Click a Point to Display", SwingConstants.CENTER);
    private final JLabel zoomLabel = new JLabel("x" + ZOOM, SwingConstants.CENTER);
    private final JLabel domainLabel = new JLabel("DOMAIN", SwingConstants.CENTER);
    private final JLabel rangeLabel = new JLabel("RANGE", SwingConstants.CENTER);
    private final JButton zoomOutButton = new JButton("ZOOM OUT");
    private final JButton zoomInButton = new JButton("ZOOM IN");
    private final JButton resetButton = new JButton("RESET");
    private final JButton quitButton = new JButton("QUIT");
    // Create GUI's listeners
    private final DrawListener drawListener = new DrawListener(this, javmosPanel);
    private final ZoomListener zoomListener = new ZoomListener(this, javmosPanel);
    private final DomainRangeListener domainRangeListener = new DomainRangeListener(javmosPanel);
    private final ResetListener resetListener = new ResetListener(this, javmosPanel);
    private final QuitListener quitListener = new QuitListener();
    // Create GUI's colors
    private final Color inputBackgroundColor = Color.LIGHT_GRAY;
    private final Color displayBackgroundColor = Color.BLACK;
    private final Color displayForegroundColor = Color.WHITE;

    // Class constructor
    public JavmosGUI(String s) {
        // Add the plane and control panels to the main panel
        mainPanel.add(createJavmosPanel());
        mainPanel.add(createControlPanel());
        // Configure the frame
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setContentPane(mainPanel);
        frame.pack();
        equationField.setText(s);
        drawListener.draw();
    }

    // Creates the GUI's Javmos panel
    private JPanel createJavmosPanel() {
        // Configure the Javmos panel
        javmosPanel.setPreferredSize(new Dimension(PLANE_PANEL_WIDTH, PLANE_PANEL_HEIGHT));
        javmosPanel.addMouseListener(new PointClickListener(this));
        // Return the configured panel
        return javmosPanel;
    }

    // Creates the GUI's control panel
    private JPanel createControlPanel() {
        // Configure the control panel
        controlPanel.setPreferredSize(new Dimension(CONTROL_PANEL_WIDTH, CONTROL_PANEL_HEIGHT));
        // Configure control panel's components
        configureComponent(equationField, CONTROL_PANEL_WIDTH, CONTROL_PANEL_HEIGHT / CONTROL_PANEL_ITEMS, Color.WHITE, 25, drawListener, "", Color.LIGHT_GRAY, Color.BLACK);
        configureComponent(firstDerivativeLabel, CONTROL_PANEL_WIDTH, CONTROL_PANEL_HEIGHT / CONTROL_PANEL_ITEMS, Color.WHITE, 25, null, "", displayBackgroundColor, Color.RED);
        configureComponent(secondDerivativeLabel, CONTROL_PANEL_WIDTH, CONTROL_PANEL_HEIGHT / CONTROL_PANEL_ITEMS, Color.WHITE, 25, null, "", displayBackgroundColor, Color.BLUE);
        configureComponent(zoomOutButton, CONTROL_PANEL_WIDTH / 3, CONTROL_PANEL_HEIGHT / CONTROL_PANEL_ITEMS, Color.WHITE, 20, zoomListener, "-", displayBackgroundColor, displayForegroundColor);
        configureComponent(zoomLabel, CONTROL_PANEL_WIDTH / 3, CONTROL_PANEL_HEIGHT / CONTROL_PANEL_ITEMS, Color.WHITE, 25, null, "", displayBackgroundColor, displayForegroundColor);
        configureComponent(zoomInButton, CONTROL_PANEL_WIDTH / 3, CONTROL_PANEL_HEIGHT / CONTROL_PANEL_ITEMS, Color.WHITE, 20, zoomListener, "+", displayBackgroundColor, displayForegroundColor);
        configureComponent(rangeStepField, CONTROL_PANEL_WIDTH / 3, (CONTROL_PANEL_HEIGHT / CONTROL_PANEL_ITEMS) / 2, Color.BLACK, 25, domainRangeListener, "RANGE_STEP", inputBackgroundColor, displayBackgroundColor);
        configureComponent(maxRangeField, CONTROL_PANEL_WIDTH / 3, CONTROL_PANEL_HEIGHT / CONTROL_PANEL_ITEMS, Color.BLACK, 30, domainRangeListener, "MAX_RANGE", inputBackgroundColor, displayBackgroundColor);
        configureComponent(pointLabel, CONTROL_PANEL_WIDTH, CONTROL_PANEL_HEIGHT / CONTROL_PANEL_ITEMS, Color.WHITE, 25, null, "", displayBackgroundColor, displayForegroundColor);
        configureComponent(resetButton, CONTROL_PANEL_WIDTH, CONTROL_PANEL_HEIGHT / CONTROL_PANEL_ITEMS, Color.WHITE, 45, resetListener, "", displayBackgroundColor, displayForegroundColor);
        configureComponent(quitButton, CONTROL_PANEL_WIDTH, CONTROL_PANEL_HEIGHT / CONTROL_PANEL_ITEMS, Color.WHITE, 25, quitListener, "", displayBackgroundColor, displayForegroundColor);
        // Add control panel components
        controlPanel.add(equationField);
        controlPanel.add(firstDerivativeLabel);
       // controlPanel.add(secondDerivativeLabel);
        controlPanel.add(zoomOutButton);
        controlPanel.add(zoomLabel);
        controlPanel.add(zoomInButton);
        controlPanel.add(quitButton);
        // Return the configured panel
        return controlPanel;
    }

    // Configures various properties of JComponents
    private void configureComponent(JComponent component, int width, int height, Color border, int fontSize, ActionListener listener, String actionCommand, Color background, Color foreground) {
        component.setPreferredSize(new Dimension(width, height));
        component.setBorder(new LineBorder(border, 1));
        component.setFont(new Font("miriam", Font.ITALIC, fontSize));
        component.setOpaque(true);
        component.setBackground(background);
        component.setForeground(foreground);
        if (component instanceof JButton) {
            ((JButton) component).addActionListener(listener);
            ((JButton) component).setActionCommand(actionCommand);
        } else if (component instanceof JTextField) {
            ((JTextField) component).setHorizontalAlignment(SwingConstants.CENTER);
            ((JTextField) component).addActionListener(listener);
            ((JTextField) component).setActionCommand(actionCommand);
        }
    }

    public int getPlaneWidth() {
        return PLANE_PANEL_WIDTH;
    }

    public int getPlaneHeight() {
        return PLANE_PANEL_HEIGHT;
    }

    public String getEquationField() {
        return equationField.getText();
    }

    public double getZoom() {
        return Double.parseDouble(zoomLabel.getText().substring(1));
    }

    public double getMinDomain() {
        return Double.parseDouble(minDomainField.getText());
    }

    public double getMaxDomain() {
        return Double.parseDouble(maxDomainField.getText());
    }

    public double getMinRange() {
        return Double.parseDouble(minRangeField.getText());
    }

    public double getMaxRange() {
        return Double.parseDouble(maxRangeField.getText());
    }

    public double getDomainStep() {
        return Double.parseDouble(domainStepField.getText());
    }

    public double getRangeStep() {
        return Double.parseDouble(rangeStepField.getText());
    }

    public void setFirstDerivativeLabel(String firstDerivative) {
        firstDerivativeLabel.setText(firstDerivative);
    }

    public void setSecondDerivativeLabel(String secondDerivative) {
        secondDerivativeLabel.setText(secondDerivative);
    }

    public void setZoom(double zoom) {
        this.zoomLabel.setText("x" + zoom);
    }

    public void setPointLabel(String pointLabel, RootType rootType) {
        this.pointLabel.setForeground(rootType.getPointColor());
        this.pointLabel.setText(pointLabel);
    }

    public void resetFields() {
        minDomainField.setText(Double.toString(MIN_DOMAIN));
        maxDomainField.setText(Double.toString(MAX_DOMAIN));
        minRangeField.setText(Double.toString(MIN_RANGE));
        maxRangeField.setText(Double.toString(MAX_RANGE));
        domainStepField.setText(Double.toString(DOMAIN_STEP));
        rangeStepField.setText(Double.toString(RANGE_STEP));
        zoomLabel.setText("x" + ZOOM);
    }


}