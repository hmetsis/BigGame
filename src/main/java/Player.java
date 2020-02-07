import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Player {

    char [] playerGraphic = new char[]{
            'x' ,' ', ' ', ' ', 'x',
                '\\', '\u263A', '/',
                      '█',
    };
    protected Position [] playerPosition = new Position[playerGraphic.length];
    public boolean hitBlock = false;
    int playerWidth = 5;
    int extraSpeed = 10;
    int middle = 30;
    int playerHeight = 21;
    int playerHeight2 = 22;
    TextColor skyBlue = new TextColor.RGB(122,199,220);
    TextColor blue = TextColor.ANSI.BLUE;

    public Player (Position position) {
        int j = 0;
        for (int i = 0; i < playerWidth; i++) {
            Position p = new Position(position.getX()+j, position.getY());
            playerPosition[i] = p;
            j++;
        }
        int k = 1;
        for (int i = 5; i < playerPosition.length-1; i++) {
            Position p = new Position(position.getX()+k, position.getY()+1);
            playerPosition[i] = p;
            k++;
        }
            Position p = new Position( position.getX()+2,  position.getY()+2);
            playerPosition[playerPosition.length-1] = p;
    }

    public void playerMove(KeyType type) {
        for (int i = 0; i < playerGraphic.length; i++) {

            playerPosition[i].setOldX(playerPosition[i].getX());
            playerPosition[i].setOldY(playerPosition[i].getY());

            switch (type) {
                case ArrowDown:
                    TheGame.moveSpeed = extraSpeed;
                    break;
                case ArrowRight:
                    playerPosition[i].setX(playerPosition[i].getX()+1);
                    break;
                case ArrowLeft:
                    playerPosition[i].setX(playerPosition[i].getX()-1);
                    break;
            }
        }
    }

    public void checkIfWall (Wall walls, Terminal terminal) throws IOException {
        for (int i = 0; i < playerWidth; i++) {

            boolean crashIntoWall = false;

            for (Position p : walls.getWall()) {
                if(playerPosition[0].getX()>middle){
                    if (p.getX() == playerPosition[playerWidth -1].getX()) {
                        for (int j = 0; j < playerGraphic.length ; j++) {
                           playerPosition[j].setX(playerPosition[j].getOldX());
                           playerPosition[j].setY(playerPosition[j].getOldY());
                           crashIntoWall = true;
                        }
                    }

                } else {
                    if (p.getX() == playerPosition[0].getX()) {
                        for (int k = 0; k < playerGraphic.length ; k++) {
                        playerPosition[k].setX(playerPosition[k].getOldX());
                        playerPosition[k].setY(playerPosition[k].getOldY());
                        crashIntoWall = true;
                        }
                    }
                }
            }
            if (!crashIntoWall) {
                printPlayer(terminal);
            }
        }
    }

    public void hitBlock(Terminal terminal) throws Exception {
        hitBlock = false;
        Block deleteBlock = null;

        for (int i = 0; i < playerWidth; i++) {
            for (Block block : TheGame.allBlocks) {
                for (Position p : block.getOneBlock()) {
                    if ((p.getY() == playerHeight || p.getY() == playerHeight2) && p.getX() == playerPosition[i].getX()) {
                        hitBlock = true;
                        deleteBlock = block;
                        break;
                    }
                }
            }
        }

        if (hitBlock) {
            SoundClass musicObject3 = new SoundClass();
            String filepath3 = "src/BlockInHead";
            musicObject3.playMusic(filepath3);
            TheGame.lives = TheGame.lives-1;
        } else {
            printPlayer(terminal);
            }

        if (hitBlock) {
            for (Position p : deleteBlock.getOneBlock()) {
                terminal.setCursorPosition(p.getX(), p.getY());
                terminal.putCharacter(' ');
            }
            TheGame.allBlocks.remove(deleteBlock);
        }
    }

    public boolean hitTreat(Treats treat)  {
        boolean isHitting = false;

        for (int i = 0; i < playerWidth; i++) {
            if (treat.treatPosition.getX() == playerPosition[i].getX() && (treat.treatPosition.getY() == playerHeight || treat.treatPosition.getY() == playerHeight2) ) {
            isHitting = true;
            break;
            }
        }
        return isHitting;
    }

    public void printPlayer(Terminal terminal) throws IOException {

        terminal.setBackgroundColor(skyBlue);
        for (int i = 0; i < playerPosition.length; i++ ) {
            terminal.setCursorPosition(playerPosition[i].getOldX(), playerPosition[i].getOldY());
            terminal.putCharacter(' ');
        }

        terminal.setForegroundColor(blue);
        for (int i = 0; i < playerPosition.length; i++) {
            terminal.setCursorPosition(playerPosition[i].getX(), playerPosition[i].getY());
            terminal.putCharacter(playerGraphic[i]);
        }
        terminal.setCursorPosition(playerPosition[6].getX(), playerPosition[6].getY());
        terminal.putCharacter(playerGraphic[6]);
    }
}