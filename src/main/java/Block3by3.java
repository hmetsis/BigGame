import com.googlecode.lanterna.terminal.Terminal;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Block3by3 extends Block{

    public Block3by3 () {
        int x = ThreadLocalRandom.current().nextInt(6, 61);
        int backToX = x;
        int y = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Position position = new Position(x, y);
                oneBlock.add(position);
                x++;
            }
            x = backToX;
            y++;
        }
    }

    @Override
    public void moveBlock (Terminal terminal) {
        for (Position b : oneBlock) {
            b.setOldY(b.getY());
            b.setY(b.getY() + 1);
        }
    }

    @Override
    public void printBlock (Terminal terminal) throws Exception {
        int i = 0;
        for (Position b : oneBlock) {
            terminal.setCursorPosition(b.getX(), b.getY());
            terminal.setBackgroundColor(brickRed);
            terminal.setForegroundColor(grout);
            terminal.putCharacter(blockChar);
        }

        for (Position b : oneBlock) {
            if (i < 3) {
                terminal.setCursorPosition(b.getX(), b.getOldY());
                terminal.setBackgroundColor(skyBlue);
                terminal.putCharacter(' ');
            }
            i++;
        }
    }



    public List<Position> getOneBlock() {
        return oneBlock;
    }
}
