package CALab;
import StoplightMVC.Stoplight;
import mvc.*;
public class RunCommand extends Command{

    private int cycle;

    public RunCommand(Model model, int loop) {
        super(model);
        this.cycle = loop;
    }

    public void execute() {
        ((Grid)model).updateLoop(cycle);
    }
}
