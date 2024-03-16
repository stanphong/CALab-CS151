package CALab;

import java.awt.*;
import java.util.*;
import java.io.*;
import mvc.*;

public abstract class Cell extends Publisher implements Serializable {

    protected int row = 0, col = 0;
    protected Set<Cell> neighbors = new HashSet<Cell>();
    protected Grid myGrid = null;
    protected Cell partner = null;

    // choose a random neighbor as a partner
    public void choosePartner() {
        if (partner == null && neighbors != null) {
            /*
             * Set partner to null
             * Convert neighbors set to a local array
             * Starting at a random position in the array search for a neighbor without a
             * partner
             * Make the first such neighbor (if any) the partner and set its partner field
             * to this
             */
            Cell[] neighborsArray = neighbors.toArray(new Cell[neighbors.size()]);
            Random random = new Random();
            int startIndex = random.nextInt(neighborsArray.length);
            for (int i = 0; i < neighborsArray.length; i++) {
                int index = (startIndex + i) % neighborsArray.length;
                Cell potentialPartner = neighborsArray[index];
                if (potentialPartner.partner == null) {
                    // Make the first such neighbor (if any) the partner
                    partner = potentialPartner;
                    // Set its partner field to this
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

    // override these
    public abstract int getStatus();

    public abstract Color getColor();

    // observer neighbors' states
    public abstract void observe();

    // interact with a random neighbor
    public abstract void interact();

    // update my state
    public abstract void update();

    // set status to status + 1 mod whatever
    public abstract void nextState();

    // set status to a random or initial value
    public abstract void reset(boolean randomly);
}