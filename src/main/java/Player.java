import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.util.List;

public class Player {
    private Position playerPosition;
    final char playerChar = 'X';

    public Player (int x, int y) {
        Position position = new Position(x, y);
        playerPosition = position;
    }

    public void playerMove(KeyType type) {

        playerPosition.setOldX(playerPosition.getX());
        playerPosition.setOldY(playerPosition.getY());

        switch (type) {
//            case ArrowDown:
//                y++;
//                break;
            case ArrowRight:
                playerPosition.setX(playerPosition.getX()+1);
                break;
//            case ArrowUp:
//                y--;
//                break;
            case ArrowLeft:
                playerPosition.setX(playerPosition.getX()-1);
                break;
        }
    }

    public void checkIfWall (Wall walls, Terminal terminal) throws IOException {
        boolean crashIntoWall = false;

        for (Position p : walls.getWall()) {
            if (p.getX() == playerPosition.getX() && p.getY() == playerPosition.getY()) {
                crashIntoWall = true;
            }
        }

        if (crashIntoWall) {
            playerPosition.setX(playerPosition.getOldX());
            playerPosition.setY(playerPosition.getOldY());
        } else {
            printPlayer(terminal);
        }
    }

    public void hitBlock(List<List<Position>> allBlocks, Terminal terminal) throws Exception {
        boolean hitBlock = false;
            for (List<Position> oneBlock : allBlocks) {
                for (Position p : oneBlock) {
                    if (p.getX() == playerPosition.getX() && p.getY() == playerPosition.getY()) {
                        hitBlock = true;
                    }
                }
            }

        if (hitBlock) {
            Main.gameOver();
        } else {
            printPlayer(terminal);
        }
    }

    public void printPlayer(Terminal terminal) throws IOException {
        terminal.setCursorPosition(playerPosition.getOldX(), playerPosition.getOldY());
        terminal.putCharacter(' ');
        terminal.setCursorPosition(playerPosition.getX(), playerPosition.getY());
        terminal.putCharacter(playerChar);
    }
}
