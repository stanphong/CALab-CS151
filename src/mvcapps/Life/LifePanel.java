package mvcapps.Life;

import mvcapps.CALab.GridPanel;
import mvcapps.mvc.AppFactory;
import mvcapps.mvc.AppPanel;

public class LifePanel extends GridPanel {
    public LifePanel(AppFactory factory){
        super(factory);
    }

    public static void main(String[] args) {
        AppFactory factory = new LifeFactory();
        AppPanel panel = new LifePanel(factory);
        panel.display();
    }
}
