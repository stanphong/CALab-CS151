package mvcapps.Life;

import mvcapps.CALab.*;
import mvcapps.mvc.Model;
import mvcapps.mvc.View;

public class LifeFactory extends GridFactory {

    public LifeFactory(){
        super();
    }

    @Override
    public Model makeModel() {
        return new Society();
    }

    @Override
    public View makeView(Model model) {
        return new GridView((Society)model);
    }


}
