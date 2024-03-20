package CALab;

import mvc.AppFactory;
import mvc.AppPanel;

import javax.swing.*;
import java.awt.*;

public class GridPanel extends AppPanel {

    private JButton run1;
    private JButton run50;
    private JButton populate;
    private JButton clear;

    public GridPanel(AppFactory factory) {
        super(factory);
        JPanel run1Panel = new JPanel();
        run1 = new JButton("RUN1");
        run1Panel.add(run1);

        JPanel run50Panel = new JPanel();
        run50 = new JButton("RUN50");
        run50Panel.add(run50);

        JPanel populatePanel = new JPanel();
        populate = new JButton("POPULATE");
        populatePanel.add(populate);

        JPanel clearPanel = new JPanel();
        clear = new JButton("CLEAR");
        clearPanel.add(clear);

        run1.addActionListener(this);
        run50.addActionListener(this);
        populate.addActionListener(this);
        clear.addActionListener(this);

        ControlPanel.setLayout(new GridLayout(2, 2));
        ControlPanel.add(run1Panel);
        ControlPanel.add(run50Panel);
        ControlPanel.add(populatePanel);
        ControlPanel.add(clearPanel);
    }

    public static void main(String[] args) {
        AppFactory factory = new GridFactory();
        AppPanel panel = new GridPanel(factory);
        panel.display();
    }


}
