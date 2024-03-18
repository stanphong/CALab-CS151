package Life;

import CALab.Cell;

import java.awt.*;

public class Agent extends Cell {
    private int status;
    int ambience;

    public Agent() {
        status = 0;
        ambience = 8;
    }

    @Override
    public int getStatus() {
        return status;
    }

    @Override
    public Color getColor() {
        if (this.getStatus() == 0)
            return Color.RED;
        else
            return Color.GREEN;
    }

    @Override
    public void observe() {
        int livingNeigh = 0;
        for (Cell neigh : this.neighbors)
        {
            if (neigh.getStatus() == 1)
                livingNeigh++;
        }
        this.ambience = livingNeigh;
    }

    @Override
    public void interact() {
        // no-op
    }

    @Override
    public void update() {
        if (Society.rebirth.contains(this.ambience))
            this.status = 1;
        if (Society.death.contains(this.ambience))
            this.status = 0;
    }

    @Override
    public void nextState() {
       if (this.status == 0)
           this.status = 1;
        if (this.status == 1)
            this.status = 0;
    }

    @Override
    public void reset(boolean randomly) {
        this.status = 0;
    }
}
