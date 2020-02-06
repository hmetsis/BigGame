import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.terminal.Terminal;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Block8by2 extends Block{
    protected Position position;
    protected List<Position> oneBlock = new ArrayList<>();
    protected final char blockChar = '\u25A1';

    public Block8by2 () {
        int x = ThreadLocalRandom.current().nextInt(6, 54);
        int backToX = x;
        int y = 0;

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 8; j++) {
                Position position = new Position(x, y);
                oneBlock.add(position);
                x++;}
            x = backToX;
            y++;
        }
    }

    @Override
    public void moveBlock (Terminal terminal) throws Exception{
        int i = 0;

        for(Position b : oneBlock) {
            b.setOldY(b.getY());
            b.setY(b.getY()+1);
            terminal.setCursorPosition(b.getX(), b.getY());
            terminal.setBackgroundColor(TextColor.ANSI.RED);
            terminal.setForegroundColor(TextColor.ANSI.WHITE);
            terminal.putCharacter(blockChar);


        }

        for (Position b : oneBlock) {
            if (i < 8) {
                terminal.setCursorPosition(b.getX(), b.getOldY());
                terminal.setBackgroundColor(new TextColor.RGB(122,199,220));
                terminal.setForegroundColor(TextColor.ANSI.WHITE);
                terminal.putCharacter(' ');
            }
            i++;
        }
    }

//    @Override
//    public void printBlock (Terminal terminal) throws Exception {
//        for (Position position : oneBlock) {
//            terminal.setCursorPosition(position.getX(), position.getY());
//            terminal.setBackgroundColor(TextColor.ANSI.RED);
//            terminal.setForegroundColor(TextColor.ANSI.WHITE);
//            terminal.putCharacter('\u25A1');
//        }
//    }



    public List<Position> getOneBlock() {
        return oneBlock;
    }
}
