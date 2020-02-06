import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.terminal.Terminal;
import java.util.concurrent.ThreadLocalRandom;

    public class StandingBlock extends Block {

        public StandingBlock () {
            int x = ThreadLocalRandom.current().nextInt(11, 59);
            int y = 0;
            int blockLength = ThreadLocalRandom.current().nextInt(2, 5);

            for (int i = 0; i < blockLength; i++) {
                Position position = new Position(x, y-blockLength);
                oneBlock.add(position);
                y++;
            }
        }

        @Override
        public void moveBlock(Terminal terminal) throws Exception {

            for(Position b : oneBlock) {
                b.setOldY(b.getY());
                b.setY(b.getY()+1);
                terminal.setCursorPosition(b.getX(), b.getY());
                terminal.setForegroundColor(TextColor.ANSI.GREEN);
                terminal.putCharacter(blockChar);

                terminal.setCursorPosition(b.getX(), b.getOldY());
                terminal.putCharacter(' ');
            }
        }

        @Override
        public void printBlock (Terminal terminal) throws Exception {
            for (Position position : oneBlock) {
                terminal.setCursorPosition(position.getX(), position.getY());
                terminal.putCharacter(blockChar);
            }
        }
    }
