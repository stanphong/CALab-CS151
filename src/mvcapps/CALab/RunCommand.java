package mvcapps.CALab;

import mvcapps.mvc.*;

public class RunCommand extends Command {

    private int cycles;

    public RunCommand(Model model, int cycles){
        super(model);
        this.cycles = cycles;
    }

    @Override
    public void execute() throws Exception {
            Grid grid = (Grid) model;
            grid.updateLoop(cycles);
    }
}
