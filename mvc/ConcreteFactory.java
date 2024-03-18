package mvc;

public class mvcFactory implements AppFactory{
    Model model;
    View view;
    String title;



    @Override
    public Model makeModel() {
        this.model = new Model();
        return model;
    }

    @Override
    public View makeView(Model model) {
        this.view = new View(model);
        return view;
    }

    @Override
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

        return new String[]{"Change", "edit"};
    }

    @Override
    public Command makeEditComand(Model m, String type, Object source) {
        if (type == "Change") {
            return new ChangeCommand(model);

        } else {
            return null;
        }
    }

}
