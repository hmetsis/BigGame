import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Player {
    private int x;
    private int y;
    private int oldX;
    private int oldY;
    final char playerChar = 'X';

    public Player () {
        this.x = 20;
        this.y = 20;
    }

    public void playerMove(KeyType type) {

        oldX = x;
        oldY = y;

        switch (type) {
//            case ArrowDown:
//                y++;
//                break;
            case ArrowRight:
                x++;
                break;
//            case ArrowUp:
//                y--;
//                break;
            case ArrowLeft:
                x--;
                break;
        }
    }

    public void checkIfWall (Wall walls, Terminal terminal) throws IOException {
        boolean crashIntoWall = false;

        for (Position p : walls.getWall()) {
            if (p.getX() == x && p.getY() == y) {
                crashIntoWall = true;
            }
        }

        if (crashIntoWall) {
            x = oldX;
            y = oldY;
        } else {
            printPlayer(terminal);
        }
    }

    public void printPlayer(Terminal terminal) throws IOException {
        terminal.setCursorPosition(oldX, oldY);
        terminal.putCharacter(' ');
        terminal.setCursorPosition(x, y);
        terminal.putCharacter(playerChar);
    }
}
