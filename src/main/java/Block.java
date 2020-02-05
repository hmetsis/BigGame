import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class  Block {

    private Position position;
    private Character [] blockArray;
    private List<Position> oneBlock = new ArrayList<>();
    private List<List<Position>> allBlocks = new ArrayList<>();
    final char blockChar = '\u2588';

    public Block () {
        int x = ThreadLocalRandom.current().nextInt(11, 59);
        int y = 0;
        int blockLength = ThreadLocalRandom.current().nextInt(3, 10);
        //blockArray = new Character[blockLength];

        for (int i = 0; i < blockLength; i++) {
            Position position = new Position(x, y-blockLength);
            oneBlock.add(position);
            y++;
        }
    }


    public void moveBlock(Terminal terminal) throws Exception {
        int y = oneBlock.get(oneBlock.size()-1).getY();
        int x = oneBlock.get(oneBlock.size()-1).getX();

        oneBlock.add(new Position(x, y+1));
        terminal.setCursorPosition(x, y+1);
        terminal.putCharacter(blockChar);

        terminal.setCursorPosition(oneBlock.get(0).getX(), oneBlock.get(0).getY());
        terminal.putCharacter(' ');
        oneBlock.remove(0);
    }

    public void printBlock (Terminal terminal) throws Exception {
        for (Position position : oneBlock) {
            terminal.setCursorPosition(position.getX(), position.getY());
            terminal.putCharacter(blockChar);
        }
    }
    public void lyingDown () {
        int x = ThreadLocalRandom.current().nextInt(11, 59);
        int y = 0;
        int blockWidth = ThreadLocalRandom.current().nextInt(3, 10);

        for (int i = 0; i < blockWidth; i++) {
            Position position = new Position(x, y);
            oneBlock.add(position);
            x++;
        }
    }

    public void moveLying (Terminal terminal) throws Exception{

        for(Position b : oneBlock) {
            b.setOldY(b.getY());
            b.setY(b.getY()+1);
            terminal.setCursorPosition(b.getX(), b.getY());
            terminal.putCharacter(blockChar);

            terminal.setCursorPosition(b.getX(), b.getOldY());
            terminal.putCharacter(' ');
        }
    }

}
