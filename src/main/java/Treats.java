/*import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Treats extends Block {

    protected Position treatPosition;

    public Treats()  {
        do {
            int x = ThreadLocalRandom.current().nextInt(11, 59);
            int y = 0;

            treatPosition = new Position(x, y);

        } while(checkIfBlock());
    }

    public boolean checkIfBlock() {
        for (List<Position> oneBlock : allBlocks) {
            for(Position p : oneBlock) {
                if (p.getY() == treatPosition.getY() &&  p.getX() == treatPosition.getX()) {
                    return true;
                } else {
                    return false;
                }
        }
    }
}*/
