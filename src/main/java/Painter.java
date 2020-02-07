import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Painter {
    static TextColor skyBlue = new TextColor.RGB(122,199,220);
    static TextColor brightWhite = new TextColor.RGB(255,255,255);
    static TextColor brickRed = TextColor.ANSI.RED;
    static TextColor grout = TextColor.ANSI.WHITE;
    static TextColor black = TextColor.ANSI.BLACK;
    static char brick = '\u25A1';
    static char heart = '\u2665';
    static int leftWallEnd = 5;
    static int rightWallStart = 65;
    static int terminalHeight = 24;
    static int terminalWidth = 80;

    public static void printStartScreen(Terminal terminal, KeyStroke keyStroke) throws IOException, InterruptedException {
        terminal.setBackgroundColor(TextColor.ANSI.RED);
        terminal.setForegroundColor(TextColor.ANSI.WHITE);
        for(int i = 0; i < 80; i++) {
            for (int j = 0; j < 65; j++) {
                terminal.setCursorPosition(i, j);
                terminal.putCharacter('\u25A1');
            }
        }

        List<String> welcome = new ArrayList<>();
        welcome.add(0, "   ___             __      ___  _     __             ___  ___  ___  ___ ");
        welcome.add( 1, "  / _ | ___  ___  / /__   / _ \\(_)___/ /_____ ____  |_  |/ _ \\/ _ \\/ _ \\");
        welcome.add( 2, " / __ |/ _ \\/ _ \\/ / -_) / ___/ / __/  '_/ -_) __/ / __// // / // / // /");
        welcome.add( 3, "/_/ |_/ .__/ .__/_/\\__/ /_/  /_/\\__/_/\\_\\\\__/_/   /____/\\___/\\___/\\___/ ");
        welcome.add( 4, "     /_/  /_/                                                           ");
        welcome.add( 5, "                                                                        ");
        welcome.add( 6, "                 ,--./,-.      ,--./,-.      ,--./,-.                   ");
        welcome.add( 7, "                /,-._.--~\\    /,-._.--~\\    /,-._.--~\\                  ");
        welcome.add( 8, "                 __}  {        __}  {        __}  {                     ");
        welcome.add( 9, "                \\`-._,-`-,    \\`-._,-`-,    \\`-._,-`-,                  ");
        welcome.add( 10, "                 `._,._,'      `._,._,'      `._,._,'                   ");
        welcome.add( 11, "                                                                        ");
        welcome.add( 12, "                           Hit any key to start                         ");

        int startScreenX = 4;
        int backToStart = startScreenX;
        int startScreenY = 6;
        terminal.setBackgroundColor(new TextColor.RGB(255,255,255));
        terminal.setForegroundColor(TextColor.ANSI.GREEN);
        for (String i : welcome) {
            for (char c : i.toCharArray()) {
                terminal.setCursorPosition(startScreenX, startScreenY);
                terminal.putCharacter(c);
                startScreenX++;
            }
            startScreenX = backToStart;
            startScreenY++;
        }
        terminal.flush();
        do {
            Thread.sleep(5); // might throw InterruptedException
            keyStroke = terminal.pollInput();
        } while (keyStroke == null);

    }

    public static void paintBackground (Terminal terminal) throws IOException {
        terminal.clearScreen();
        terminal.setForegroundColor(grout);

        for(int i = 0; i < leftWallEnd ; i++) {
            for (int j = 0; j < rightWallStart; j++) {
                terminal.setCursorPosition(i, j);
                terminal.setBackgroundColor(brickRed);
                terminal.putCharacter(brick);
            }
        }
        for(int i = leftWallEnd; i < rightWallStart; i++) {
            for (int j = 0; j < terminalHeight; j++) {
                terminal.setCursorPosition(i, j);
                terminal.setBackgroundColor(skyBlue);
                terminal.putCharacter(' ');
            }
        }
        for(int i = rightWallStart; i < terminalWidth; i++) {
            for(int j = 0; j < terminalHeight; j++) {
                terminal.setCursorPosition(i, j);
                terminal.setBackgroundColor(brickRed);
                terminal.putCharacter(brick);
            }
            terminal.flush();
        }

    }

    public static void printLives(Terminal terminal) throws Exception {
        String printLives = "Lives: ";
        terminal.setCursorPosition(68, 14);
        terminal.setBackgroundColor(brightWhite);
        terminal.setForegroundColor(black);

        for(int i = 0; i < printLives.length(); i++) {
            terminal.putCharacter(printLives.charAt(i));
        }

        for(int i = 0; i < 3; i++){
            terminal.putCharacter(' ');
        }
        terminal.setCursorPosition(75, 14);

        for(int i = 0; i < Main.lives; i++) {
            terminal.putCharacter(heart);
        }

    }

    public static void printScore(Terminal terminal) throws Exception {
        String printScore = "Score: " + (Main.score-1);
        terminal.setCursorPosition(68, 12);
        terminal.setBackgroundColor(new TextColor.RGB(255,255,255));
        terminal.setForegroundColor(TextColor.ANSI.BLACK);

        for(int i = 0; i < printScore.length(); i++) {
            terminal.putCharacter(printScore.charAt(i));
        }
    }

    public static void printGameOver (Terminal terminal) throws Exception {
        terminal.setForegroundColor(new TextColor.RGB(255,255,255));
        List<String> gameOver = new ArrayList<>();
        gameOver.add(0, "  ____    _    __  __ _____    _____     _______ ____  ");
        gameOver.add(1, " / ___|  / \\  |  \\/  | ____|  / _ \\ \\   / / ____|  _ \\ ");
        gameOver.add( 2,"| |  _  / _ \\ | |\\/| |  _|   | | | \\ \\ / /|  _| | |_) |");
        gameOver.add( 3, "| |_| |/ ___ \\| |  | | |___  | |_| |\\ V / | |___|  _ < ");
        gameOver.add( 4, " \\____/_/   \\_\\_|  |_|_____|  \\___/  \\_/  |_____|_| \\_\\");

        int gameOverX = 7;
        int back = gameOverX;
        int gameOverY = 6;

        for (String i : gameOver) {
            for (char c : i.toCharArray()) {
                terminal.setCursorPosition(gameOverX, gameOverY);
                terminal.putCharacter(c);
                gameOverX++;
            }
            gameOverX = back;
            gameOverY++;
        }
        terminal.flush();
    }

}
