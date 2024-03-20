package CALab;

import java.awt.*;
import java.util.*;
import java.io.*;
import mvc.*;

public abstract class Grid extends Model {
    static private int time = 0;
    protected int dim = 20;
    protected Cell[][] cells;

    public int getDim() { return dim; }
    public int getTime() { return time; }
    public Cell getCell(int row, int col) { return cells[row][col]; }
    public abstract Cell makeCell(boolean uniform);


    public Grid(int dim) {
        this.dim = dim;
        cells = new Cell[dim][dim];
        populate();
    }
    public Grid() { this(20); }

    protected void populate() {
        // 1. use makeCell to fill in cells
        // 2. use getNeighbors to set the neighbors field of each cell
        for (int i = 0; i < cells.length; i ++){
            for (int j = 0; j < cells.length; j ++){
                Cell cell = this.makeCell(true);
                cells[i][j] = cell;
                cell.myGrid = this;
                cell.row = i;
                cell.col = j;
            }
        }

        for (Cell[] cellRow: cells){
            for (Cell cell : cellRow){
                cell.neighbors = this.getNeighbors(cell, 1);
            }
        }


    }

    // called when Populate button is clicked
//    public void repopulate(boolean randomly) {
//        if (randomly) {
//            // randomly set the status of each cell
//            for (Cell[] cellRow : cells) {
//                for (Cell cell : cellRow){
//                    cell = this.makeCell(randomly);
//                }
//            }
//        } else {
//            // set the status of each cell to 0 (dead)
//        }
//        // notify subscribers
//        notifySubscribers();
//    }
    public abstract void repopulate(boolean randomly);


    public Set<Cell> getNeighbors(Cell asker, int radius) {
        /*
        return the set of all cells that can be reached from the asker in radius steps.
        If radius = 1 this is just the 8 cells touching the asker.
        Tricky part: cells in row/col 0 or dim - 1.
        The asker is not a neighbor of itself.
        */
        Set<Cell> neighbor = new HashSet<>();
        int cRow = asker.row;
        int cCol = asker.col;

        //topleft cell
        cRow = (cRow - radius) >= 0 ? cRow - radius : cRow - radius + this.getDim();
        cCol = (cCol - radius) >= 0 ? cCol - radius : cCol - radius + this.getDim();
        neighbor.add(cells[cRow][cCol]);

        //leftBar
        int i = cRow;
        int n = 0;
        while(n <= 2 * radius)
        {
            if (i >= this.getDim()) i -= this.getDim();
            neighbor.add(cells[i][cCol]);
            i++;
            n++;
        }

        //topBar
        i = cCol;
        n = 0;
        while(n <= 2 * radius)
        {
            if (i >= this.getDim()) i -= this.getDim();
            neighbor.add(cells[cRow][i]);
            i++;
            n++;
        }

        //resetOGCell
        cRow = asker.row;
        cCol = asker.col;

        //bottomRight cell
        cRow = (cRow + radius) < this.getDim() ? cRow + radius : cRow + radius - this.getDim();
        cCol = (cCol + radius) < this.getDim() ? cCol + radius : cCol + radius - this.getDim();
        neighbor.add(cells[cRow][cCol]);

        //rightBar
        i = cRow;
        n = 0;
        while(n <= 2 * radius)
        {
            if (i < 0) i += this.getDim();
            neighbor.add(cells[i][cCol]);
            i--;
            n++;
        }

        //bottomBar
        i = cCol;
        n = 0;
        while(n <= 2 * radius)
        {
            if (i < 0) i += this.getDim();
            neighbor.add(cells[cRow][i]);
            i--;
            n++;
        }

//        for (int i = cRow; i <= cRow + 2 * radius + 1; i++) {
//            for (int j = cCol; j <= cCol + 2 * radius + 1; j++) {
//                if (i == asker.row && j == asker.col) continue; // Skip the asker itself
//                int row = i % this.getDim(); // Wrap around for rows
//                int col = j % this.getDim(); // Wrap around for columns
//                if (Math.abs(row - cRow) == radius || Math.abs(col - cCol) == radius) {
//                    neighbor.add(cells[row][col]);
//                }
//            }
//        }
        return neighbor;
    }


    // cell phases:

    public void observe() {
        // call each cell's observe method and notify subscribers
        for (Cell[] cellRow : cells) {
            for (Cell cell : cellRow){
                cell.observe();
                cell.notifySubscribers();
            }
        }
    }

    public void interact() {
        // ???
    }

    public void update() {
        for (Cell[] cellRow : cells) {
            for (Cell cell : cellRow){
                cell.update();
            }
        }
    }

    public void updateLoop(int cycles) {
        observe();
        for(int cycle = 0; cycle < cycles; cycle++) {
            interact();
            update();
            observe();
            time++;
            System.out.println("time = " + time);
        }
    }
}
