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
    public static List<Treats> allTreats = new ArrayList<>();
    static int score = 0;

    static {
        try {
            terminal = terminalFactory.createTerminal();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main (String [] args) throws Exception {

        String filepath = "src/Apple_Bite.wav";
        String filepath2 = "src/BackMusic.wav";
        String filepath3 = "src/BlockInHead";
        String filepath4 = "src/nsmb_game_over.wav";

        MusicStuff eatAppleObject = new MusicStuff();
        MusicStuff otherSoundsObject = new MusicStuff();

        otherSoundsObject.playBackgroundMusic(filepath2);




        terminal.setCursorVisible(false);
        paintBackground();

        blockCreator();

        Treats firstTreat = new Treats();
        allTreats.add(firstTreat);

        Wall walls = new Wall();
        walls.printWall(terminal);
        Player player = new Player(20, 20);
        player.printPlayer(terminal);

        terminal.setBackgroundColor(new TextColor.RGB(122,199,220));

        KeyType type;
        KeyStroke keyStroke;
        terminal.flush();
        int moveBlockSpeed = 0;

        while (continueReadingInput) {
//            terminal.setBackgroundColor(new TextColor.RGB(122,199,220);
            Thread.sleep(50);
            keyStroke = null;

            keyStroke = terminal.pollInput();

            if (keyStroke != null) {
                type = keyStroke.getKeyType();
                player.playerMove(type);
            }

            player.checkIfWall(walls, terminal);
            player.hitBlock(terminal);
            if(player.hitBlock==true){
                otherSoundsObject.stopBackgroundMusic(filepath2);
                otherSoundsObject.playMusic(filepath3);
                otherSoundsObject.playGameOver(filepath4);
            }

            if(allTreats.get(0).treatPosition.getY() == 21) {
                terminal.setCursorPosition(allTreats.get(0).treatPosition.getX(), allTreats.get(0).treatPosition.getY());
                terminal.putCharacter(' ');
                allTreats.remove(0);
            }

            if(player.hitTreat(allTreats.get(0))) {
                allTreats.remove(0);
                score++;
                eatAppleObject.playMusic(filepath);
            }

            if (moveBlockSpeed % 30 == 0) {
                for (int i = 0; i < allBlocks.size(); i++) {
                    allBlocks.get(i).moveBlock(terminal);
                }

                for (int i = 0; i < allTreats.size(); i++) {
                    allTreats.get(i).moveTreat(terminal);
                }
            }


            if (moveBlockSpeed%70 == 0) {
                blockCreator();

                Treats treat = new Treats();
                allTreats.add(treat);
            }

            moveBlockSpeed++;

            //Ligga sist i loopen
//            terminal.setCursorPosition(x, y);
//            terminal.putCharacter(player);
//            terminal.setCursorPosition(oldX, oldY);
//            terminal.putCharacter(' ');
            printScore();
            printLives();
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
                terminal.setBackgroundColor(new TextColor.RGB(122,199,220));
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

    public static void printScore() throws Exception {
        String printScore = "Score: " + score;
        terminal.setCursorPosition(63, 10);
        terminal.setBackgroundColor(new TextColor.RGB(255,255,255));
        terminal.setForegroundColor(TextColor.ANSI.BLACK);

        for(int i = 0; i < printScore.length(); i++) {
            terminal.putCharacter(printScore.charAt(i));
        }
    }

    public static void printLives() throws Exception {
        String printLives = "Lives: ";
        terminal.setCursorPosition(63, 12);
        terminal.setBackgroundColor(new TextColor.RGB(255,255,255));
        terminal.setForegroundColor(TextColor.ANSI.BLACK);

        for(int i = 0; i < printLives.length(); i++) {
            terminal.putCharacter(printLives.charAt(i));
        }

        for(int i = 0; i < 3; i++) {
            terminal.putCharacter('\u2665');
            terminal.putCharacter(' ');
        }

    }

    public static void blockCreator() {
        Block block = null;
            for(int i = 0; i < 2; i++) {
                int b = ThreadLocalRandom.current().nextInt(1, 3);
                switch (b) {
                    case 1:
                        block = new Block3by3();
                        break;
                    case 2:
                        block = new Block4by2();
                        break;
                    case 3:
                        block = new Block8by2();
                        break;
                }
                allBlocks.add(block);
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
