package mvcapps.CALab;

import mvcapps.mvc.Command;
import mvcapps.mvc.Model;

public class PopulateCommand extends Command {



    public PopulateCommand(Model model){
        super(model);
    }

    @Override
    public void execute() {
        Grid grid = (Grid)model;
        grid.repopulate(true);
        grid.observe();
    }
}