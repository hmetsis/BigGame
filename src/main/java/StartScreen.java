import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StartScreen {
    public static void printStartScreen(Terminal terminal) throws IOException {

        List<String> welcome = new ArrayList<>();
        welcome.add(1, "   ___             __      ___  _     __             ___  ___  ___  ___ ");
        welcome.add( 2, "  / _ | ___  ___  / /__   / _ \\(_)___/ /_____ ____  |_  |/ _ \\/ _ \\/ _ \\");
        welcome.add( 3, " / __ |/ _ \\/ _ \\/ / -_) / ___/ / __/  '_/ -_) __/ / __// // / // / // /");
        welcome.add( 4, "/_/ |_/ .__/ .__/_/\\__/ /_/  /_/\\__/_/\\_\\\\__/_/   /____/\\___/\\___/\\___/ ");
        welcome.add( 5, "     /_/  /_/                                                           ");
        welcome.add( 6, "                                                                        ");
        welcome.add( 7, "                 ,--./,-.      ,--./,-.      ,--./,-.                   ");
        welcome.add( 8, "                /,-._.--~\\    /,-._.--~\\    /,-._.--~\\                  ");
        welcome.add( 9, "                 __}  {        __}  {        __}  {                     ");
        welcome.add( 10, "                \\`-._,-`-,    \\`-._,-`-,    \\`-._,-`-,                  ");
        welcome.add( 11, "                 `._,._,'      `._,._,'      `._,._,'                   ");

        int startScreenX = 6;
        int backToStart = startScreenX;
        int startScreenY = 6;
        terminal.setForegroundColor(new TextColor.RGB(255,255,255));
        for (String i : welcome) {
            for (char c : i.toCharArray()) {
                terminal.setCursorPosition(startScreenX, startScreenY);
                terminal.putCharacter(c);
                startScreenX++;
            }
            startScreenX = backToStart;
            startScreenY++;
        }
    }
}
