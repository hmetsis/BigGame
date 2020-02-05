import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.sun.media.jfxmedia.events.PlayerEvent;

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
        Player player = new Player();
        player.printPlayer(terminal);

        terminal.setBackgroundColor(TextColor.ANSI.CYAN);

        terminal.flush();

        boolean continueReadingInput = true;
        while (continueReadingInput) {
//            Thread.sleep(300);
//            //keyStroke = terminal.pollInput();
            block.moveBlock(terminal);

            KeyStroke keyStroke = null;

            do {
                Thread.sleep(5); // might throw InterruptedException
                keyStroke = terminal.pollInput();
            } while (keyStroke == null);

            KeyType type = keyStroke.getKeyType();

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
