import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Treats {

    protected Position treatPosition;

    public Treats()  {
        do {
            int x = ThreadLocalRandom.current().nextInt(11, 59);
            int y = 0;

            treatPosition = new Position(x, y);

        } while(checkIfBlock());
    }

    public boolean checkIfBlock() {
        for (Block b : //Blocklist) {
            for(Position p : Block.allBlocks) {
                if (b.p.getY == treatPosition.getY() &&  b.p.getX == treatPosition.getX()) {
                    return true;
                } else {
                    return false;
                }
        }
    }
}
