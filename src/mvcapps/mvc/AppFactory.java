package mvcapps.mvc;

public interface AppFactory {

    public Model makeModel();

    public View makeView(Model model);

    public Command makeEditCommand(Model model, String type, Object source);

    public String[] getEditCommands();

    public String getTitle();

    public String[] getHelp();

    public String about();
}
