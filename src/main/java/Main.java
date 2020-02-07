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
    static int score = 1;
    public static int lives = 3;

    public static int moveSpeed = 40;
    public static int oldMoveSpeed = 40;
    static int createBlockSpeed = 150;
    static int createTreat = 150;
    static boolean isNotIncreasingSpeed = false;

    static {
        try {
            terminal = terminalFactory.createTerminal();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main (String [] args) throws Exception {

        String fileAppleBite = "src/Apple_Bite.wav";
        String fileBackMusic = "src/BackMusic.wav";
        String fileBlockInHead = "src/BlockInHead";
        String fileGameOver = "src/nsmb_game_over.wav";
        String fileWooho = "src/Wooho.wav.wav";
        String fileIntro = "src/Intro.wav.wav";

        SoundClass eatAppleObject = new SoundClass();
        SoundClass otherSoundsObject = new SoundClass();

        KeyType type = KeyType.ArrowUp;
        KeyStroke keyStroke = null;

        terminal.setCursorVisible(false);
        otherSoundsObject.playLoopedMusic(fileIntro);
        Painter.printStartScreen(terminal, keyStroke);
        otherSoundsObject.stopLoopedMusic(fileIntro);
        otherSoundsObject.playLoopedMusic(fileBackMusic);
        Painter.paintBackground(terminal);

        Wall walls = new Wall();
        walls.printWall(terminal);

        Treats firstTreat = new Treats();
        allTreats.add(firstTreat);

        blockCreator();

        Player player = new Player(20, 21);
        player.printPlayer(terminal);

        terminal.setBackgroundColor(new TextColor.RGB(122,199,220));

        terminal.flush();
        int moveBlockSpeed = 0;

        while (continueReadingInput) {
//            terminal.setBackgroundColor(new TextColor.RGB(122,199,220);
            Thread.sleep(30);
            keyStroke = null;

            keyStroke = terminal.pollInput();

            if (keyStroke != null) {
                type = keyStroke.getKeyType();
                player.playerMove(type);
            }

            if (!type.equals(KeyType.ArrowDown)){
                moveSpeed = oldMoveSpeed;
            }

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
                if (allTreats.get(0).kindOfTreat.equals(KindOfTreat.EXTRA_LIVES) && Main.lives < 3) {
                    Main.lives++;
                    SoundClass eatExtraLife = new SoundClass();
                    eatExtraLife.playMusic(fileWooho);
                }
                allTreats.remove(0);
                score++;
                eatAppleObject.playMusic(fileAppleBite);
            }

            //adjusted for shorter sleep
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
            if(moveBlockSpeed==Player.extraSpeed)
            //change for speeding up the game
            if ((moveBlockSpeed % createBlockSpeed) == 0) {
                blockCreator();
            }

            if ((moveBlockSpeed % createTreat) == 0) {
                Treats treat = new Treats();
                allTreats.add(treat);
            }

            if(moveBlockSpeed == 200000) {
                moveBlockSpeed = 0;
            }

            moveBlockSpeed++;
            if(score % 5 == 0 && !isNotIncreasingSpeed && score < 21) {
                if(score > 15){
                    createTreat = createTreat - 12;
                    createBlockSpeed = createBlockSpeed - 12;
                    oldMoveSpeed = oldMoveSpeed - 4;
                    isNotIncreasingSpeed = true;
                } else {
                    createTreat = createTreat - 24;
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

            if(lives == 0) {
                gameOver();
                otherSoundsObject.stopLoopedMusic(fileBackMusic);
                otherSoundsObject.playMusic(fileBlockInHead);
                otherSoundsObject.playGameOver(fileGameOver);
                continueReadingInput = false;
            }

            if(allBlocks.get(0).getOneBlock().get(0).getY() > 30) {
                allBlocks.remove(0);
            }

            Painter.printScore(terminal);
            Painter.printLives(terminal);
            terminal.flush();
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
        Painter.printGameOver(terminal);
    }


    }
