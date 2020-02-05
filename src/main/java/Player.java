import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.util.List;

public class Player {
    private Position [] playerPosition = new Position[4];
    final char playerChar = 'X';
    int [] playerGraphic = new int[4];

    public Player (int x, int y) {
        for (int i = 0; i < playerGraphic.length; i++) {
            Position position = new Position(x++, y);
            playerPosition[i] = position;
        }


    }

    public void playerMove(KeyType type) {

        for (int i = 0; i < playerGraphic.length; i++) {

        playerPosition[i].setOldX(playerPosition[i].getX());
        playerPosition[i].setOldY(playerPosition[i].getY());

        switch (type) {
//            case ArrowDown:
//                y++;
//                break;
            case ArrowRight:
                playerPosition[i].setX(playerPosition[i].getX()+1);

                break;
//            case ArrowUp:
//                y--;
//                break;
            case ArrowLeft:
                playerPosition[i].setX(playerPosition[i].getX()-1);

                break;
        }
    }}

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
        for (int i = 0; i < playerGraphic.length ; i++) {
            terminal.setCursorPosition(playerPosition[i].getOldX(), playerPosition[i].getOldY());
            terminal.setBackgroundColor(TextColor.ANSI.CYAN);
            terminal.putCharacter(' ');
        }

        for (int i = 0; i < playerGraphic.length ; i++) {
        terminal.setCursorPosition(playerPosition[i].getX(), playerPosition[i].getY());
        terminal.setForegroundColor(TextColor.ANSI.BLUE);
            if(i % 3 == 0){
      terminal.putCharacter(playerChar);}
            else{
                terminal.putCharacter('_');
                terminal.setForegroundColor(TextColor.ANSI.CYAN);
    }}}
}
