package mvcapps.CALab;
import mvcapps.mvc.*;

import javax.swing.*;
import java.awt.*;

public class GridPanel extends AppPanel{

    private JButton run1, run50, populate, clear;

    public GridPanel(AppFactory factory) {
        super(factory);

        JPanel run1Panel = new JPanel();
        JPanel run50Panel = new JPanel();
        JPanel populatePanel = new JPanel();
        JPanel clearPanel = new JPanel();

        run1 = new JButton("RUN1");
        run1.addActionListener(this);

        run50 = new JButton("RUN50");
        run50.addActionListener(this);

        populate = new JButton("POPULATE");
        populate.addActionListener(this);

        clear = new JButton("CLEAR");
        clear.addActionListener(this);

        run1Panel.add(run1);
        run50Panel.add(run50);
        populatePanel.add(populate);
        clearPanel.add(clear);

        run1Panel.setBackground(Color.PINK);
        run50Panel.setBackground(Color.PINK);
        populatePanel.setBackground(Color.PINK);
        clearPanel.setBackground(Color.PINK);

        ControlPanel.setLayout(new GridLayout(2,2));
        ControlPanel.add(run1Panel);
        ControlPanel.add(run50Panel);
        ControlPanel.add(populatePanel);
        ControlPanel.add(clearPanel);
    }

}
