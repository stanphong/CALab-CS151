package CALab;

import Life.Society;
import StoplightMVC.ChangeCommand;
import mvc.AppFactory;
import mvc.Command;
import mvc.Model;
import mvc.View;

public class GridFactory implements AppFactory {

    @Override
    public Model makeModel() {
        return new Society();
    }

    @Override
    public View makeView(Model m) {
        return new GridView((Society)m);
    }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public String[] getHelp() {
        return new String[0];
    }

    @Override
    public String about() {
        return null;
    }

    @Override
    public String[] getEditCommands() {
        return new String[] {"RUN1", "RUN50", "POPULATE", "CLEAR"};
    }

    @Override
    public Command makeEditCommand(Model m, String name) {
        if (name == "RUN1")
            return new RunCommand((Grid)m, 1);
        else if (name == "RUN50")
            return new RunCommand((Grid)m, 50);
        else if (name == "POPULATE")
            return new PopulateCommand((Grid)m);
        else if (name == "CLEAR")
            return new ClearCommand((Grid)m);
        else
            return null;
    }
}
