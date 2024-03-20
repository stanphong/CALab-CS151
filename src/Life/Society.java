package Life;

import CALab.Grid;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Society extends Grid {
    public static Set<Integer> rebirth = new HashSet<Integer>();
    public static Set<Integer> death = new HashSet<Integer>();
    public static int percentAlive = 50;

    static {
        rebirth.add(3);
        death.add(0);
        death.add(1);
        death.add(4);
        death.add(5);
        death.add(6);
        death.add(7);
        death.add(8);
    }

    public Agent makeCell(boolean randomly){
        return new Agent();
    }

    @Override
    public void repopulate(boolean randomly) {
        if (randomly) {
            // randomly set the status of each cell
            Random random = new Random();
            int count = cells.length * cells.length * 100 % percentAlive;
            for (int i = 0; i < this.getDim(); i ++) {
                for (int j = 0; j < this.getDim(); j ++){
                    int randomBinary = random.nextInt(2);
                    if (randomBinary == 1){
                        cells[i][j].nextState();
                        count--;
                    }
                }
            }
        } else {
            // set the status of each cell to 0 (dead)
            for (int i = 0; i < this.getDim(); i ++) {
                for (int j = 0; j < this.getDim(); j ++){
                    cells[i][j].reset(true);
                }
            }
        }
        // notify subscribers
        notifySubscribers();
    }

}
