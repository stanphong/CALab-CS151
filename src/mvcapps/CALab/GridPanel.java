package mvcapps.CALab;
import mvcapps.mvc.*;

import javax.swing.*;

public class GridPanel extends AppPanel{

    private JButton run1, run50, populate, clear;

    public GridPanel(AppFactory factory) {
        super(factory);
        run1 = new JButton("RUN1");
        run1.addActionListener(this);
        ControlPanel.add(run1);

        run50 = new JButton("RUN50");
        run50.addActionListener(this);
        ControlPanel.add(run50);

        populate = new JButton("POPULATE");
        populate.addActionListener(this);
        ControlPanel.add(populate);

        clear = new JButton("CLEAR");
        clear.addActionListener(this);
        ControlPanel.add(clear);
    }

    /*
    private JButton createAndAddButton(String label) {
        JButton button = new JButton(label);
        button.addActionListener(this);
        ControlPanel.add(button);
        return button;
    }

     */

    public static void main(String[] args) {
        AppFactory factory = new GridFactory();
        AppPanel panel = new GridPanel(factory);
        panel.display();
    }
}
