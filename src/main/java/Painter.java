import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Painter {
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
        welcome.add( 11, "                           Hit any key to start                         ");

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
}
