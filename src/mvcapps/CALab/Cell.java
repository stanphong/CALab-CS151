package mvcapps.CALab;

import java.util.*;
import java.io.*;
import mvcapps.mvc.*;
import java.awt.Color;

public abstract class Cell extends Publisher implements Serializable {

    protected int row = 0, col = 0;
    protected Set<Cell> neighbors = new HashSet<Cell>();
    protected Grid myGrid = null;
    protected Cell partner = null;


    public void choosePartner() {
        if (partner == null && neighbors != null) {
            Cell[] neighborsArray = neighbors.toArray(new Cell[neighbors.size()]);
            Random random = new Random();
            int startIndex = random.nextInt(neighborsArray.length);
            for (int i = 0; i < neighborsArray.length; i++) {
                int index = (startIndex + i) % neighborsArray.length;
                Cell potentialPartner = neighborsArray[index];
                if (potentialPartner.partner == null) {

                    partner = potentialPartner;

                    partner.partner = this;
                    break;
                }
            }
        }

    }

    public void unpartner() {
        if (partner != null) {
            if (partner.partner != null) {
                partner.partner = null;
            }
            partner = null;
        }
    }

    public abstract void observe();
    public abstract void interact();
    public abstract void update();
    public abstract void nextState();
    public abstract void reset(boolean randomly);


    public abstract Color getColor();

    public abstract int getStatus();
}
