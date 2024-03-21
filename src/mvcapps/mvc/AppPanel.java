package mvcapps.mvc;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class AppPanel extends JPanel implements Subscriber, ActionListener{
    protected Model model;
    protected View view;
    protected JPanel ControlPanel;
    protected AppFactory factory;
    private JFrame frame;

    public static int frameWidth = 500;
    public static int frameHeight = 300;

    public AppPanel(AppFactory factory) {
        this.factory = factory;
        this.model = factory.makeModel();
        this.view = factory.makeView(model);
        view.setBackground((Color.GRAY));
        ControlPanel = new JPanel();
        ControlPanel.setBackground((Color.PINK));
        this.setLayout(new GridLayout(1,2));

        add(ControlPanel);
        add(view);
        model.subscribe(this);

        frame = new SafeFrame();
        Container cp = frame.getContentPane();
        cp.add(this);
        frame.setJMenuBar(createMenuBar());
        frame.setTitle(factory.getTitle());
        frame.setSize(frameWidth,frameHeight);
    }

    public void display() {
        frame.setVisible(true);
    }

    public void setModel(Model newModel) throws Exception {
        this.model.unsubscribe(this);
        this.model = newModel;
        this.model.subscribe(this);
        view.setModel(this.model);
        model.changed();
    }


    public Model getModel() { return model; }

    protected JMenuBar createMenuBar() {
        JMenuBar result = new JMenuBar();
        JMenu fileMenu = Utilities.makeMenu("File", new String[]{"New", "Save", "Open", "Quit"}, this);
        result.add(fileMenu);
        JMenu editMenu = Utilities.makeMenu("Edit", factory.getEditCommands(), this);
        result.add(editMenu);
        JMenu helpMenu = Utilities.makeMenu("Help", new String[]{"About", "Help"}, this);
        result.add(helpMenu);
        return result;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String cmmd = e.getActionCommand();
            Command command = factory.makeEditCommand(model,cmmd,this);

            if (cmmd.equals("Save")) {
                Utilities.save(model, false);
            } else if (cmmd.equals("SaveAs")) {
                Utilities.save(model, true);
            } else if (cmmd.equals("Open")) {
                Model newModel = Utilities.open(model);
                if (newModel != null) setModel(newModel);
            } else if (cmmd.equals("New")) {
                Utilities.saveChanges(model);
                setModel(factory.makeModel());
                model.setUnsavedChanges(false);
            } else if (cmmd.equals("Quit")) {
                Utilities.saveChanges(model);
                System.exit(0);
            } else if (cmmd.equals("About")) {
                Utilities.inform(factory.about());
            } else if (cmmd.equals("Help")) {
                Utilities.inform(factory.getHelp());
            } else {
                if (command != null) {
                    command.execute();
                } else {
                    Utilities.error("Command not recognized: " + cmmd);
                }
            }
        } catch (Exception ex) {
            handleException(ex);
        }
    }

    protected void handleException(Exception e) {
        Utilities.error(e);
    }

    @Override
    public void update() {
    }

}
