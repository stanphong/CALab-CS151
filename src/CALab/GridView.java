package CALab;

import javax.swing.*;

import mvc.*;
import java.awt.*;

public class GridView  extends View {

    private CellView cellViews[][];
    public GridView(Grid grid) {
        /*
        Cell cell = new CellView(((Grid)model).getCell(row, col)
        cellViews[row][col] = cell
        set cell.row and cell.col here
        */
        super(grid);
        int dim = grid.getDim();
        cellViews = new CellView[dim][dim];
        setLayout(new GridLayout(dim, dim, 0, 0));
        for (int row = 0; row < grid.getDim(); row ++) {
            for (int col = 0; col < grid.getDim(); col ++){
                cellViews[row][col] = new CellView(grid.getCell(row, col));
                cellViews[row][col].update();
                add(cellViews[row][col]);
            }
        }
    }

    @Override
    public void update() {
        // call update method of each CellView
        for (int row = 0; row < ((Grid)model).getDim(); row ++) {
            for (int col = 0; col < ((Grid)model).getDim(); col ++){
                cellViews[row][col].update();
            }
        }
    }

}