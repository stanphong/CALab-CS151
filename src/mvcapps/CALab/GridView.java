package mvcapps.CALab;
import mvcapps.mvc.*;

import javax.swing.*;
import java.awt.*;

public class GridView  extends View {

    private CellView cellViews[][];

    public GridView(Model model) {
        super(model);
        Grid grid = (Grid)model;
        setLayout(new GridLayout(grid.getDim(),grid.getDim(),0,0));
        cellViews = new CellView[grid.getDim()][grid.getDim()];
        for (int row = 0; row < grid.getDim(); row++) {
            for (int col = 0; col < grid.getDim(); col++) {
                cellViews[row][col] = new CellView(grid.getCell(row, col));
                cellViews[row][col].update();
                add(cellViews[row][col]);
            }
        }
    }

    public void update() {
        for (int row = 0; row < cellViews.length; row++) {
            for (int col = 0; col < cellViews[row].length; col++) {
                cellViews[row][col].update();
            }
        }
    }

}