import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Wall {

    private List<Position> wall = new ArrayList<>();
    final char blockChar = '\u2588';

    public Wall () {
        for(int i = 0; i < 24; i++) {
            wall.add(new Position(10, i));
            wall.add(new Position(60, i));
        }
    }

    public void printWall (Terminal terminal) throws IOException {
        for(Position p : wall) {
            terminal.setCursorPosition(p.getX(), p.getY());
            terminal.putCharacter(blockChar);
        }
    }

    public List<Position> getWall() {
        return wall;
    }
}
