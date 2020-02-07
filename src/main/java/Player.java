import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.util.List;

public class Player {

    final char playerChar = '█';

    char [] playerGraphic = new char[]{
            'x' ,' ', ' ', ' ', 'x',
                '\\', 'O', '/',
                      '█',
    };
    protected Position [] playerPosition = new Position[playerGraphic.length];
    public boolean hitBlock = false;

    public Player (int x, int y) {
        int j = 0;
        for (int i = 0; i < 5; i++) {
            Position position = new Position(x+j, y);
            playerPosition[i] = position;
            j++;
        }
        int k = 1;
        for (int i = 5; i < 8; i++) {
            Position position = new Position(x+k, y+1);
            playerPosition[i] = position;
            k++;
        }
            Position position = new Position(x+2, y+2);
            playerPosition[8] = position;
    }

    public void playerMove(KeyType type) {

        for (int i = 0; i < playerGraphic.length; i++) {

            playerPosition[i].setOldX(playerPosition[i].getX());
            playerPosition[i].setOldY(playerPosition[i].getY());

            switch (type) {
                case ArrowDown:
                    Main.moveSpeed = 15;
                    break;
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
        }
    }

    public void checkIfWall (Wall walls, Terminal terminal) throws IOException {
        for (int i = 0; i < 7 ; i++) {

            boolean crashIntoWall = false;

            for (Position p : walls.getWall()) {
               if(playerPosition[0].getX()>30){
                   if (p.getX() == playerPosition[4].getX()) {
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
//            else {
//
//            }
        }
    }

    public void hitBlock(Terminal terminal) throws Exception {
        hitBlock = false;
        Block deleteBlock = null;

        for (int i = 0; i < 5 ; i++) {
            for (Block block : Main.allBlocks) {
                for (Position p : block.getOneBlock()) {
                    if ((p.getY() == 21 || p.getY() == 22) && p.getX() == playerPosition[i].getX()) {
//                for (int j = 0; j < block.getOneBlock().size(); j++) {
//                    if (playerPosition[i] == block.getOneBlock().get(j)) {
                        hitBlock = true;
                        deleteBlock = block;
                        break;
                    }
                }
            }
        }

        if (hitBlock) {
            MusicStuff musicObject3 = new MusicStuff();
            String filepath3 = "src/BlockInHead";
            musicObject3.playMusic(filepath3);
            //Main.gameOver();
            Main.lives = Main.lives-1;
            //System.out.println("Game over");
        } else {
            printPlayer(terminal);
        }

        if (hitBlock) {
            for (Position p : deleteBlock.getOneBlock()) {
                terminal.setCursorPosition(p.getX(), p.getY());
                terminal.putCharacter(' ');
            }
            Main.allBlocks.remove(deleteBlock);
        }
    }

    public boolean hitTreat(Treats treat) throws Exception {
        boolean isHitting = false;

        for (int i = 0; i < 5 ; i++) {
                if (treat.treatPosition.getX() == playerPosition[i].getX() && (treat.treatPosition.getY() == 21 || treat.treatPosition.getY() == 22) ) {
                isHitting = true;
                break;}
        }
        return isHitting;
    }

    public void printPlayer(Terminal terminal) throws IOException {

        terminal.setBackgroundColor(new TextColor.RGB(122,199,220));
        for (int i = 0; i < playerPosition.length; i++ ) {
            terminal.setCursorPosition(playerPosition[i].getOldX(), playerPosition[i].getOldY());
            terminal.putCharacter(' ');
        }

        terminal.setForegroundColor(TextColor.ANSI.BLUE);
        for (int i = 0; i < playerPosition.length; i++) {
            terminal.setCursorPosition(playerPosition[i].getX(), playerPosition[i].getY());
            terminal.putCharacter(playerGraphic[i]);
        }
    }
}