package mvcapps.CALab;

import mvcapps.mvc.AppFactory;
import mvcapps.mvc.Command;
import mvcapps.mvc.Model;
import mvcapps.mvc.View;

public class GridFactory implements AppFactory {

    @Override
    public Model makeModel() {
        return null;
    }

    @Override
    public View makeView(Model model) {
        return null;
    }

    @Override
    public Command makeEditCommand(Model model, String type, Object source) {
        return null;
    }

    @Override
    public String[] getEditCommands() {
        return new String[0];
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
}
