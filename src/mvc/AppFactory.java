package mvc;

import CALab.Grid;
import CALab.GridView;

public interface AppFactory {
    Model makeModel();
    View makeView(Model model);
    String getTitle();
    String getHelp();
    String about();
    String[] getEditCommands();
    Command makeEditComand(Model m, String type, Object source);


}
