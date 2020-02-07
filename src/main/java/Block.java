import com.googlecode.lanterna.terminal.Terminal;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class  Block {

    protected Position position;
    protected List<Position> oneBlock = new ArrayList<>();
    protected final char blockChar = '\u2588';

    public Block () {
    }

    public void moveBlock (Terminal terminal) throws Exception {

    }

    public void printBlock (Terminal terminal) throws Exception {

    }

    public List<Position> getOneBlock() {
        return oneBlock;
    }



    public static void blockCreator() {
        Block block;
        int b = ThreadLocalRandom.current().nextInt(1, 4);
        switch (b) {
            case 1:
                block = new Block3by3();
                break;
            case 2:
                block = new Block4by2();
                break;
            case 3:
                block = new Block8by2();
                break;
            default:
                throw new IndexOutOfBoundsException(b);
        }
        TheGame.allBlocks.add(block);
    }

    public static void removeBlock(){
        if(TheGame.allBlocks.get(0).getOneBlock().get(0).getY() > 30) {
            TheGame.allBlocks.remove(0);
        }
    }
}
