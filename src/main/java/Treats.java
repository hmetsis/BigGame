import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.terminal.Terminal;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Treats extends Block {

    protected Position treatPosition;
    protected final char treatChar = 'O';

    public Treats() {
        do {
            int x = ThreadLocalRandom.current().nextInt(11, 59);
            int y = 0;

            treatPosition = new Position(x, y);

        } while (checkIfBlock());
    }

    public boolean checkIfBlock() {
        boolean crashing = false;

        for (Block oneBlock : Main.allBlocks) {
            for (Position p : oneBlock.getOneBlock()) {
                if (p.getY() == treatPosition.getY() && p.getX() == treatPosition.getX()) {
                    crashing = true;
                } else {
                    crashing = false;
                }
            }
        }
        return crashing;
    }

    public void moveTreat(Terminal terminal) throws Exception {
            treatPosition.setOldY(treatPosition.getY());
            treatPosition.setY(treatPosition.getY()+1);

            terminal.setCursorPosition(treatPosition.getX(), treatPosition.getY());
            terminal.setForegroundColor(TextColor.ANSI.GREEN);
            terminal.putCharacter(blockChar);

            terminal.setCursorPosition(treatPosition.getX(), treatPosition.getOldY());
            terminal.putCharacter(' ');
    }
}
