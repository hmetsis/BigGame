import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Block {

    private Position position;
    private Character [] blockArray;
    private List<Position> block = new ArrayList<>();
    final char blockChar = '\u2588';

    public Block () {
        int x = ThreadLocalRandom.current().nextInt(11, 59);
        int y = 0;
        int blockLength = ThreadLocalRandom.current().nextInt(3, 10);
        //blockArray = new Character[blockLength];

        for (int i = 0; i < blockLength; i++) {
            Position position = new Position(x, y);
            block.add(position);
            y++;
        }
    }


    public void moveBlock(Terminal terminal) throws Exception {
        int y = block.get(block.size()-1).getY();
        int x = block.get(block.size()-1).getX();

        block.add(new Position(x, y+1));
        terminal.setCursorPosition(x, y+1);
        terminal.putCharacter(blockChar);

        terminal.setCursorPosition(block.get(0).getX(), block.get(0).getY());
        terminal.putCharacter(' ');
        block.remove(0);
    }

    public void printBlock (Terminal terminal) throws Exception {
        for (Position position : block) {
            terminal.setCursorPosition(position.getX(), position.getY());
            terminal.putCharacter(blockChar);
        }
    }
}
