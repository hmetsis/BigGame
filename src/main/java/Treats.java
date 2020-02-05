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

    public boolean checkIfBlock(List<List<Position>> allBlocks) {
        for (List<Position> oneBlock : allBlocks) {
            for(Position p : b.) {
                if (oneBlock.p.getY == treatPosition.getY() &&  b.p.getX == treatPosition.getX()) {
                    return true;
                } else {
                    return false;
                }
        }
    }
}
