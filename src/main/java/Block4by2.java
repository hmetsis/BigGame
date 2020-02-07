import com.googlecode.lanterna.terminal.Terminal;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Block4by2 extends Block{

    public Block4by2() {
        int x = ThreadLocalRandom.current().nextInt(6, 60);
        int backToX = x;
        int y = 0;

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                Position position = new Position(x, y);
                oneBlock.add(position);
                x++;}
            x = backToX;
            y++;
        }
    }

    @Override
    public void moveBlock (Terminal terminal) {
        for(Position b : oneBlock) {
            b.setOldY(b.getY());
            b.setY(b.getY()+1);
        }
    }

    @Override
    public void printBlock (Terminal terminal) throws Exception {
        int i = 0;

        for(Position b : oneBlock) {
            terminal.setCursorPosition(b.getX(), b.getY());
            terminal.setBackgroundColor(brickRed);
            terminal.setForegroundColor(grout);
            terminal.putCharacter(blockChar);

        }

        for (Position b : oneBlock) {
            if (i < 4) {
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

