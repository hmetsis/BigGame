import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class  Block {

    protected Position position;
    protected List<Position> oneBlock = new ArrayList<>();
    protected static List<List<Position>> allBlocks = new ArrayList<>();
    protected final char blockChar = '\u2588';

    public Block () {
        int x = ThreadLocalRandom.current().nextInt(11, 59);
        int y = 0;
        int blockLength = ThreadLocalRandom.current().nextInt(3, 10);

        for (int i = 0; i < blockLength; i++) {
            Position position = new Position(x, y-blockLength);
            oneBlock.add(position);
            y++;
        }
    }

    public void moveBlock (Terminal terminal) throws Exception {

    }

    public void printBlock (Terminal terminal) throws Exception {

    }
}
