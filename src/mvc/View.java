package mvc;

import javax.swing.*;

public class View extends JPanel implements Subscriber{
    protected Model model;

    public View(Model model){
        this.model = model;
        model.subscribe(this);
    }
    @Override
    public void update() {
        repaint();
    }
    public void setModel(Model m){
        this.model = m;
        m.subscribe(this);
    }
}
