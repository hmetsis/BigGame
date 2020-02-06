import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.terminal.Terminal;
import java.util.concurrent.ThreadLocalRandom;

public class Treats extends Block {

    protected Position treatPosition;
    protected final char treatChar = '\u25cf';
    protected final char livesChar = '\u2665';
    protected KindOfTreat kindOfTreat;

    public Treats() {
        do {
            int x = ThreadLocalRandom.current().nextInt(6, 64);
            int y = 0;

            treatPosition = new Position(x, y);

            int i = ThreadLocalRandom.current().nextInt(1, 15);
            if (i == 9) {
                kindOfTreat = KindOfTreat.EXTRA_LIVES;
            } else {
                kindOfTreat = KindOfTreat.POINT;
            }
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
        treatPosition.setY(treatPosition.getY() + 1);

        terminal.setCursorPosition(treatPosition.getX(), treatPosition.getY());

        if (kindOfTreat.equals(KindOfTreat.EXTRA_LIVES)) {
            terminal.setForegroundColor(TextColor.ANSI.RED);
            terminal.putCharacter(livesChar);
        } else {
            terminal.setForegroundColor(TextColor.ANSI.GREEN);
            terminal.putCharacter(treatChar);
        }

        terminal.setCursorPosition(treatPosition.getX(), treatPosition.getOldY());
        terminal.putCharacter(' ');
    }
}



enum KindOfTreat {
    EXTRA_LIVES, POINT
}
