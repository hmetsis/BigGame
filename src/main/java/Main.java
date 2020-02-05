import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Main {
    static DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
    static Terminal terminal;

    static {
        try {
            terminal = terminalFactory.createTerminal();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main (String [] args) throws Exception {
        terminal.setCursorVisible(false);
        paintBackground();
        Block block = new Block();
        block.printBlock(terminal);
        Wall walls = new Wall();
        walls.printWall(terminal);
        Player player = new Player(20, 20);
        player.printPlayer(terminal);

        terminal.setBackgroundColor(TextColor.ANSI.CYAN);

        KeyType type = KeyType.ArrowUp;
        KeyStroke keyStroke = null;
        terminal.flush();

        boolean continueReadingInput = true;
        while (continueReadingInput) {
            Thread.sleep(400);

            block.moveBlock(terminal);


            keyStroke = terminal.pollInput();

            if(keyStroke!=null) {



                type = keyStroke.getKeyType();


            }
            player.playerMove(type);
            player.checkIfWall(walls, terminal);

            //Ligga sist i loopen
//            terminal.setCursorPosition(x, y);
//            terminal.putCharacter(player);
//            terminal.setCursorPosition(oldX, oldY);
//            terminal.putCharacter(' ');
            terminal.flush();

        }
    }

    public static void paintBackground () throws IOException {
        terminal.clearScreen();

        for(int i = 11; i < 60; i++) {
            for(int j = 0; j < 60; j++) {
                terminal.setCursorPosition(i, j);
                terminal.setBackgroundColor(TextColor.ANSI.CYAN);
                terminal.putCharacter(' ');
            }
            terminal.flush();
        }

    }
}
