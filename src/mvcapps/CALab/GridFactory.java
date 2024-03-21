package mvcapps.CALab;

import mvcapps.mvc.*;
import mvcapps.mvc.Command;
import mvcapps.mvc.Model;
import mvcapps.mvc.View;

public abstract class GridFactory implements AppFactory {

    @Override
    public abstract Model makeModel();

    @Override
    public abstract View makeView(Model model);


    @Override
    public String getTitle() {
        return "CA LAB";
    }

    @Override
    public String[] getHelp() {
        return new String[] {"RUN1: Runs The Program 1 Time", "RUN50: Runs The Program 50 Times", "POPULATE: Populates The Field", "CLEAR: Resets The Field"};
    }

    @Override
    public String about() {
        return "CA LAB version 4.0. Copyright 2024 by Group 1 (Edvin Rastoder, Minh Phong Do, Ken Rainier Alexopoulos)";
    }

    @Override
    public String[] getEditCommands() {
        return new String[]{"RUN1" , "RUN50" , "POPULATE" , "CLEAR"};
    }

    @Override
    public Command makeEditCommand(Model m, String type, Object source) {
        if (type == "RUN1"){
            return new RunCommand(m, 1);
        } else if (type == "RUN50") {
            return new RunCommand(m, 50);
        } else if (type == "POPULATE") {
            return new PopulateCommand(m);
        } else if(type == "CLEAR"){
            return new ClearCommand(m);
        }else{
            return null;
        }
    }
}