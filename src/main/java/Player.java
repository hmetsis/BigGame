import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.util.List;

public class Player {

    final char playerChar = '█';

    String [] playerGraphic = new String[]{"█"," " ," " , " " ," " ," ","█"," ","█"," ", "█"," ", "█"," " ," " ," ", "█","█","█"," ", " ",};
    protected Position [] playerPosition = new Position[playerGraphic.length];
    boolean hitBlock;

    public Player (int x, int y) {
        for (int i = 0; i < 7; i++) {
            Position position = new Position(x++, y);
            playerPosition[i] = position;
        } int k = 0;
        for (int i = 7; i < 14; i++) {
            Position position = new Position(playerPosition[k].getX(), y);
            playerPosition[i] = position;
            k++;
        } int c = 0;
        for (int i = 14; i < playerGraphic.length; i++) {
            Position position = new Position(playerPosition[c].getX(), y);
            playerPosition[i] = position;
            c++;
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
    }
    }

    public void checkIfWall (Wall walls, Terminal terminal) throws IOException {
        for (int i = 0; i < 7 ; i++) {

        boolean crashIntoWall = false;

        for (Position p : walls.getWall()) {
           if(playerPosition[0].getX()>30){
               if (p.getX() == playerPosition[0].getX()+6) {
                   for (int j = 0; j < playerGraphic.length ; j++) {
                       playerPosition[j].setX(playerPosition[j].getOldX());
                       playerPosition[j].setY(playerPosition[j].getOldY());
                       crashIntoWall = true;
                   }
               }

           } else{
                 if (p.getX() == playerPosition[0].getX()) {
                    for (int k = 0; k < playerGraphic.length ; k++) {
                        playerPosition[k].setX(playerPosition[k].getOldX());
                        playerPosition[k].setY(playerPosition[k].getOldY());
                        crashIntoWall = true;
                    }
                 }
           }
        }
        if (crashIntoWall) {
        }
        else {
            printPlayer(terminal);
        }
    }
    }

    public void hitBlock(Terminal terminal) throws Exception {
        hitBlock = false;
        for (int i = 0; i < 7 ; i++) {
            for (Block block : Main.allBlocks) {
                for (Position p : block.getOneBlock()) {
                    if ((p.getY() == 21 || p.getY() == 22) && p.getX() == playerPosition[i].getX()) {
//                for (int j = 0; j < block.getOneBlock().size(); j++) {
//                    if (playerPosition[i] == block.getOneBlock().get(j)) {
                        hitBlock = true;
                        break;
                    }
                }
            }
        if (hitBlock) {
            MusicStuff musicObject3 = new MusicStuff();
            String filepath3 = "src/BlockInHead";
            musicObject3.playMusic(filepath3);
            Main.gameOver();
            System.out.println("Game over");
        } else {
            printPlayer(terminal);
        }
    }
    }

    public boolean hitTreat(Treats treat) throws Exception {
        boolean isHitting = false;


        for (int i = 0; i < 7 ; i++) {
                if (treat.treatPosition.getX() == playerPosition[i].getX() && (treat.treatPosition.getY() == 21 || treat.treatPosition.getY() == 22) ) {
                isHitting = true;
                break;}
        }
        return isHitting;
    }

    public void printPlayer(Terminal terminal) throws IOException {


        for (int i = 0; i < 7 ; i++) {
            terminal.setCursorPosition(playerPosition[i].getOldX(), playerPosition[i].getOldY());
            terminal.setBackgroundColor(new TextColor.RGB(122,199,220));
            terminal.putCharacter(' ');
        }

        for (int i = 0; i < 7 ; i++) {
            playerPosition[i].setY(21);
            terminal.setCursorPosition(playerPosition[i].getX(), playerPosition[i].getY());
            terminal.setForegroundColor(TextColor.ANSI.BLUE);
            terminal.putCharacter(playerGraphic[i].charAt(0));
        }

        for (int i = 7; i < 14 ; i++) {
            terminal.setCursorPosition(playerPosition[i].getOldX(), playerPosition[i].getOldY());
            terminal.setBackgroundColor(new TextColor.RGB(122,199,220));
            terminal.putCharacter(' ');
        }

            for (int q = 7; q < 14 ; q++) {
                playerPosition[q].setY(22);
                terminal.setCursorPosition(playerPosition[q].getX(), playerPosition[q].getY());
                terminal.setForegroundColor(TextColor.ANSI.BLUE);
                terminal.putCharacter(playerGraphic[q].charAt(0));
            }

        for (int i = 14; i < playerGraphic.length ; i++) {
            terminal.setCursorPosition(playerPosition[i].getOldX(), playerPosition[i].getOldY());
            terminal.setBackgroundColor(new TextColor.RGB(122,199,220));
            terminal.putCharacter(' ');
        }

                for (int m = 14; m < playerGraphic.length ; m++) {
                    playerPosition[m].setY(23);
                    terminal.setCursorPosition(playerPosition[m].getX(), playerPosition[m].getY());
                    terminal.setForegroundColor(TextColor.ANSI.BLUE);
                    terminal.putCharacter(playerGraphic[m].charAt(0));




    }
}
}