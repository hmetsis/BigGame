import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    static DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
    static Terminal terminal;
    public static boolean continueReadingInput = true;
    public static List<Block> allBlocks = new ArrayList<>();

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

        Block block = null;

        for(int i = 0; i < 3; i++) {
            if(i%2 == 0) {
                block = new StandingBlock();
            } else {
                block = new LyingBlock();
            }
            allBlocks.add(block);
        }

        Wall walls = new Wall();
        walls.printWall(terminal);
        Player player = new Player(20, 20);
        player.printPlayer(terminal);

        terminal.setBackgroundColor(TextColor.ANSI.CYAN);

        KeyType type = KeyType.ArrowUp;
        KeyStroke keyStroke;
        terminal.flush();
        int moveBlockSpeed = 0;

        while (continueReadingInput) {
            Thread.sleep(50);

            if (moveBlockSpeed % 30 == 0) {
                for (int i = 0; i < allBlocks.size(); i++) {
                    allBlocks.get(i).moveBlock(terminal);
                }
            }

            moveBlockSpeed++;
            keyStroke = terminal.pollInput();

            if (keyStroke != null) {
                type = keyStroke.getKeyType();
            }

            player.playerMove(type);
            player.checkIfWall(walls, terminal);
            player.hitBlock(terminal);

            if (moveBlockSpeed%50 == 0) {
                int blockTypeChooser = ThreadLocalRandom.current().nextInt(1, 3);
                if (blockTypeChooser % 2 == 0) {
                    Block tempBlock = new StandingBlock();
                    allBlocks.add(tempBlock);
                } else {
                    Block tempBlock = new LyingBlock();
                    allBlocks.add(tempBlock);
                }
            }
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
        for(int i = 0; i < 10; i++) {
            for (int j = 0; j < 60; j++) {
                terminal.setCursorPosition(i, j);
                terminal.setBackgroundColor(TextColor.ANSI.RED);
                terminal.putCharacter('\u25A1');
            }
        }
        for(int i = 11; i < 60; i++) {
            for (int j = 0; j < 60; j++) {
                terminal.setCursorPosition(i, j);
                terminal.setBackgroundColor(TextColor.ANSI.CYAN);
                terminal.putCharacter(' ');
            }
        }
        for(int i = 60; i < 80; i++) {
            for(int j = 0; j < 60; j++) {
                terminal.setCursorPosition(i, j);
                terminal.setBackgroundColor(TextColor.ANSI.RED);
                terminal.putCharacter('\u25A1');
            }
            terminal.flush();
        }

    }

    public static void gameOver() throws Exception {
        continueReadingInput = false;
        printGameOver(terminal);
    }

    public static void printGameOver (Terminal terminal) throws Exception {
        String gameOver1 = "  ____    _    __  __ _____    _____     _______ ____  ";
        String gameOver2 = " / ___|  / \\  |  \\/  | ____|  / _ \\ \\   / / ____|  _ \\ ";
        String gameOver3 = "| |  _  / _ \\ | |\\/| |  _|   | | | \\ \\ / /|  _| | |_) |";
        String gameOver4 = "| |_| |/ ___ \\| |  | | |___  | |_| |\\ V / | |___|  _ < ";
        String gameOver5 = " \\____/_/   \\_\\_|  |_|_____|  \\___/  \\_/  |_____|_| \\_\\";

        int gameOverX = 11;
        int gameOverY = 6;
        for (char c : gameOver1.toCharArray()) {
            terminal.setCursorPosition(gameOverX, gameOverY);
            terminal.putCharacter(c);
            gameOverX++;
        }
        gameOverX = 11;
        gameOverY = 7;
        for (char c : gameOver2.toCharArray()) {
            terminal.setCursorPosition(gameOverX, gameOverY);
            terminal.putCharacter(c);
            gameOverX++;
        }
        gameOverX = 11;
        gameOverY = 8;
        for (char c : gameOver3.toCharArray()) {
            terminal.setCursorPosition(gameOverX, gameOverY);
            terminal.putCharacter(c);
            gameOverX++;
        }
        gameOverX = 11;
        gameOverY = 9;
        for (char c : gameOver4.toCharArray()) {
            terminal.setCursorPosition(gameOverX, gameOverY);
            terminal.putCharacter(c);
            gameOverX++;
        }
        gameOverX = 11;
        gameOverY = 10;
        for (char c : gameOver5.toCharArray()) {
            terminal.setCursorPosition(gameOverX, gameOverY);
            terminal.putCharacter(c);
            gameOverX++;
        }
        terminal.flush();
        }

    }
