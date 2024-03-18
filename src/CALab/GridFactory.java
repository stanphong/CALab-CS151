package CALab;

import Life.Society;
import mvc.AppFactory;
import mvc.Command;
import mvc.Model;

import mvc.*;

public class GridFactory implements AppFactory {

    @Override
    public Model makeModel() {
        return new Society();
    }

    @Override
    public View makeView(Model model) {
        // Ensure that the model is indeed an instance of Grid before casting.
        return new GridView((Grid)model);
    }


    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public String getHelp() {
        return null;
    }

    @Override
    public String about() {
        return null;
    }

    @Override
    public String[] getEditCommands() {
        return new String[]{"Run1" , "Run50" , "Populate" , "Clear"};
    }

    @Override
    public Command makeEditComand(Model m, String type, Object source) {
        if (type == "Run1"){
            return new RunCommand(m, 1);
        } else if (type == "Run50") {
            return new RunCommand(m, 50);
        } else if (type == "Populate") {

            return new PopulateCommand();
        } else {
            return new ClearCommand();
        }
    }
}
