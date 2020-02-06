import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.terminal.Terminal;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Block2by4 extends Block{
    protected Position position;
    protected List<Position> oneBlock = new ArrayList<>();
    protected final char blockChar = '\u25A1';

    public Block3by3 () {
        int x = ThreadLocalRandom.current().nextInt(11, 59);
        int backToX = x;
        int y = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Position position = new Position(x, y);
                oneBlock.add(position);
                x++;}
            x = backToX;
            y++;
        }
    }

    @Override
    public void moveBlock (Terminal terminal) throws Exception{
        for(Position b : oneBlock) {
            b.setOldY(b.getY());
            b.setY(b.getY()+3);
            terminal.setCursorPosition(b.getX(), b.getY());
            terminal.setBackgroundColor(TextColor.ANSI.RED);
            terminal.setForegroundColor(TextColor.ANSI.WHITE);
            terminal.putCharacter(blockChar);

            terminal.setCursorPosition(b.getX(), b.getOldY());
            terminal.putCharacter(' ');
        }
    }

    @Override
    public void printBlock (Terminal terminal) throws Exception {
        for (Position position : oneBlock) {
            terminal.setCursorPosition(position.getX(), position.getY());
            terminal.setBackgroundColor(TextColor.ANSI.RED);
            terminal.setForegroundColor(TextColor.ANSI.WHITE);
            terminal.putCharacter('\u25A1');
        }
    }



    public List<Position> getOneBlock() {
        return oneBlock;
    }
}

