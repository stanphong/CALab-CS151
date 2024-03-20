package CALab;
import mvc.*;
public class PopulateCommand extends Command{
    public PopulateCommand(Model model){
        super(model);
    }

    public void execute(){
        ((Grid)model).repopulate(true);
        ((Grid)model).observe();
    }
}
