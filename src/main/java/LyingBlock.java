import com.googlecode.lanterna.terminal.Terminal;
import java.util.concurrent.ThreadLocalRandom;

public class LyingBlock extends Block {

    public LyingBlock () {
        int x = ThreadLocalRandom.current().nextInt(11, 59);
        int y = 0;
        int blockWidth = ThreadLocalRandom.current().nextInt(3, 10);

        for (int i = 0; i < blockWidth; i++) {
            Position position = new Position(x, y);
            oneBlock.add(position);
            x++;
        }
    }

    public void moveBlock (Terminal terminal) throws Exception{

        for(Position b : oneBlock) {
            b.setOldY(b.getY());
            b.setY(b.getY()+1);
            terminal.setCursorPosition(b.getX(), b.getY());
            terminal.putCharacter(blockChar);

            terminal.setCursorPosition(b.getX(), b.getOldY());
            terminal.putCharacter(' ');
        }
    }

    public void printBlock (Terminal terminal) throws Exception {
        for (Position position : oneBlock) {
            terminal.setCursorPosition(position.getX(), position.getY());
            terminal.putCharacter(blockChar);
        }
    }
}
