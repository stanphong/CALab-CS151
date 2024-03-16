package mvcapps.mvc;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import mvcapps.tools.*;
import javax.swing.*;

public class AppPanel extends JPanel implements ActionListener, Subscriber{
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

    public void setModel(Model newModel){
        this.model.unsubscribe(this);
        this.model = newModel;
        this.model.subscribe(this);
        view.setModel(this.model);
    }

    protected JMenuBar createMenuBar() {
        JMenuBar result = new JMenuBar();
        JMenu fileMenu = Utilities.makeMenu("File", new String[]{"New", "Save", "Open", "Quit"}, this);
        result.add(fileMenu);
        JMenu editMenu = Utilities.makeMenu("Edit", new String[]{factory.getEditCommand()}, this);
        result.add(editMenu);
        JMenu helpMenu = Utilities.makeMenu("Help", new String[]{"About", "Help"}, this);
        result.add(helpMenu);
        return result;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        try {
            String commandName = e.getActionCommand();
            Command command = factory.makeEditCommand(commandName);

            if (factory.getEditCommand().equals(commandName)) {
                command.execute();
            } else if ("Save".equals(commandName)) {
                String fName = Utilities.getFileName((String) null, false);
                ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fName));
                os.writeObject(model);
                os.close();
            } else if ("Open".equals(commandName)) {
                if (Utilities.confirm("Are you sure? Unsaved changes will be lost!")) {
                    String fName = Utilities.getFileName((String) null, true);
                    ObjectInputStream is = new ObjectInputStream(new FileInputStream(fName));
                    model = (Model) is.readObject();
                    view.setModel(model);
                    is.close();
                }
            } else if ("New".equals(commandName)) {
                model = new Model();
                view.setModel(model);
            } else if ("Quit".equals(commandName)) {
                System.exit(0);
            } else if ("About".equals(commandName)) {
                factory.about();
            } else if ("Help".equals(commandName)) {
                Utilities.inform(factory.getHelp());
            } else {
                throw new Exception("Unrecognized command: " + commandName);
            }
        } catch (Exception ex) {
            Utilities.error(ex);
        }

    }

    @Override
    public void update() throws Exception {

    }

}
