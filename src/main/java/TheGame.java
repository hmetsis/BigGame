import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TheGame {

    static DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
    static Terminal terminal;

    public static boolean continueReadingInput = true;
    public static List<Block> allBlocks = new ArrayList<>();
    public static List<Treats> allTreats = new ArrayList<>();
    static int score = 1;
    public static int lives = 3;

    public static int moveSpeed = 40;
    public static int oldMoveSpeed = 40;
    static int createBlockSpeed = 180;
    static int extraBlockSpeed = 40;
    static int oldBlockSpeed = 180;
    static int createTreatSpeed = 150;
    static int extraTreatSpeed = 30;
    static int oldTreatSpeed = 150;
    static boolean isNotIncreasingSpeed = false;
    static Position startPosition = new Position(20, 21);
    static String fileAppleBite = "src/Apple_Bite.wav";
    static String fileBackMusic = "src/BackMusic.wav";
    static String fileBlockInHead = "src/BlockInHead";
    static String fileGameOver = "src/nsmb_game_over.wav";
    static String fileWooho = "src/Wooho.wav.wav";
    static String fileIntro = "src/Intro.wav.wav";
    static SoundClass eatAppleObject = new SoundClass();
    static SoundClass otherSoundsObject = new SoundClass();
    static KeyType type;
    static KeyStroke keyStroke;
    static Player player;
    static int moveBlockSpeed = 0;
    static Wall walls;

    static {
        try {
            terminal = terminalFactory.createTerminal();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main (String [] args) throws Exception {

        startUpSequence();

        while (continueReadingInput) {
            Thread.sleep(30);
            keyStroke = null;

            takeInput();
            interactions();
            objectSpeeds();
            checkLives();
            Block.removeBlock();
            Painter.printScore(terminal);
            Painter.printLives(terminal);
            terminal.flush();
        }
    }

    public static void startUpSequence() throws IOException, InterruptedException {
        type = KeyType.ArrowUp;
        keyStroke = null;

        terminal.setCursorVisible(false);
        otherSoundsObject.playLoopedMusic(fileIntro);
        Painter.printStartScreen(terminal, keyStroke);
        otherSoundsObject.stopLoopedMusic();
        otherSoundsObject.playLoopedMusic(fileBackMusic);
        Painter.paintBackground(terminal);

        walls = new Wall();
        walls.printWall(terminal);

        Treats firstTreat = new Treats();
        allTreats.add(firstTreat);

        Block.blockCreator();

        player = new Player(startPosition);
        player.printPlayer(terminal);

        terminal.setBackgroundColor(new TextColor.RGB(122,199,220));

        terminal.flush();

    }

    public static void takeInput() throws IOException {
        keyStroke = terminal.pollInput();

            if (keyStroke != null) {
                type = keyStroke.getKeyType();
                player.playerMove(type);
            }

            if (!type.equals(KeyType.ArrowDown)){
                moveSpeed = oldMoveSpeed;
            }
    }

    public static void interactions() throws Exception {
        player.checkIfWall(walls, terminal);

        if (!player.hitBlock) {
            player.hitBlock(terminal);
            if (player.hitBlock) {
                otherSoundsObject.playMusic(fileBlockInHead);
            }
        }

        if (allTreats.get(0).treatPosition.getY() == 24) {
            terminal.setCursorPosition(allTreats.get(0).treatPosition.getX(), allTreats.get(0).treatPosition.getY());
            terminal.putCharacter(' ');
            allTreats.remove(0);
        }

        if (player.hitTreat(allTreats.get(0))) {
            if (allTreats.get(0).kindOfTreat.equals(KindOfTreat.EXTRA_LIVES) && TheGame.lives < 3) {
                TheGame.lives++;
                SoundClass eatExtraLife = new SoundClass();
                eatExtraLife.playMusic(fileWooho);
            }
            allTreats.remove(0);
            score++;
            eatAppleObject.playMusic(fileAppleBite);
        }
    }

    public static void checkLives() throws Exception {
        if(lives == 0) {
            gameOver();
            otherSoundsObject.stopLoopedMusic();
            otherSoundsObject.playMusic(fileBlockInHead);
            otherSoundsObject.playGameOver(fileGameOver);
            continueReadingInput = false;
        }
    }

    public static void objectSpeeds() throws Exception {
        if ((moveBlockSpeed % moveSpeed) == 0) {
            player.hitBlock = false;
            for (int i = 0; i < allTreats.size(); i++) {
                allTreats.get(i).moveTreat(terminal);
            }

            for (int i = 0; i < allBlocks.size(); i++) {
                allBlocks.get(i).moveBlock(terminal);
                allBlocks.get(i).printBlock(terminal);
            }
        }
        if(moveSpeed==player.extraSpeed){
            createBlockSpeed = extraBlockSpeed;
            createTreatSpeed = extraTreatSpeed;
        }
        else{
            createBlockSpeed = oldBlockSpeed;
            createTreatSpeed = oldTreatSpeed;
        }
        //change for speeding up the game
        if ((moveBlockSpeed % createBlockSpeed) == 0) {
            Block.blockCreator();
        }

        if ((moveBlockSpeed % createTreatSpeed) == 0) {
            Treats treat = new Treats();
            allTreats.add(treat);
        }

        if(moveBlockSpeed == 200000) {
            moveBlockSpeed = 0;
        }

        moveBlockSpeed++;
        if(score % 5 == 0 && !isNotIncreasingSpeed && score < 21) {
            if(score > 15){
                createTreatSpeed = createTreatSpeed - 12;
                oldTreatSpeed = createTreatSpeed - 12;
                oldBlockSpeed = createBlockSpeed - 12;
                createBlockSpeed = createBlockSpeed - 12;
                oldMoveSpeed = oldMoveSpeed - 4;
                isNotIncreasingSpeed = true;
            } else {
                createTreatSpeed = createTreatSpeed - 24;
                createBlockSpeed = createBlockSpeed - 24;
                oldMoveSpeed = oldMoveSpeed - 8;
                isNotIncreasingSpeed = true;
            }
        } else if (score%2 == 0) {
            isNotIncreasingSpeed = false;
        }
        if (score%2 == 0 && score%5 == 0){
            isNotIncreasingSpeed = true;
        }
    }

    public static void gameOver() throws Exception {
        continueReadingInput = false;
        Painter.printGameOver(terminal);
    }
}
