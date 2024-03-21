package mvcapps.CALab;

import java.awt.*;
import java.util.*;
import java.io.*;
import mvcapps.mvc.*;

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

        for (int row = 0; row < dim; row++) {
            for (int col = 0; col < dim; col++) {
                Cell cell = this.makeCell(true);
                cells[row][col] = cell;
                cell.myGrid = this;
                cell.row = row;
                cell.col = col;
            }
        }

        for(Cell[] cellRow : cells){
            for(Cell cell : cellRow){
                cell.neighbors = this.getNeighbors(cell, 1);
            }
        }
    }

    public void repopulate(boolean randomly){
        if (randomly) {
            Random random = new Random();

            for (int i = 0; i < this.getDim(); i ++) {
                for (int j = 0; j < this.getDim(); j ++){
                    int randomBinary = random.nextInt(2);
                    if (randomBinary == 1){
                        cells[i][j].nextState();
                    }
                }
            }
        } else {
            for (int i = 0; i < this.getDim(); i ++) {
                for (int j = 0; j < this.getDim(); j ++){
                    cells[i][j].reset(true);
                }
            }
        }
        notifySubscribers();
    }


    public Set<Cell> getNeighbors(Cell asker, int radius) {
        Set<Cell> neighborhood = new HashSet<Cell>();
        int dim = this.getDim();
        int centerRow = asker.row;
        int centerCol = asker.col;

        for (int dist = 1; dist <= radius; dist++) {
            for (int row = centerRow - dist; row <= centerRow + dist; row++) {
                for (int col = centerCol - dist; col <= centerCol + dist; col++) {
                    if (row == centerRow && col == centerCol) continue;

                    int actualRow = (row + dim) % dim;
                    int actualCol = (col + dim) % dim;

                    neighborhood.add(cells[actualRow][actualCol]);
                }
            }
        }
        return neighborhood;
    }




    public void observe() {
        for(Cell[] cellRow : cells){
            for(Cell cell : cellRow){
                cell.observe();
                cell.notifySubscribers();
            }
        }
    }

    public void interact() {
    }

    public void update() {
        for(Cell[] cellRow : cells){
            for(Cell cell : cellRow){
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
