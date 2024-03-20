package CALab;

import mvc.*;

public class ClearCommand extends Command {
    public ClearCommand(Model model){
        super(model);
    }

    @Override
    public void execute() throws Exception {
        ((Grid)model).repopulate(false);
        ((Grid)model).observe();
    }
}
