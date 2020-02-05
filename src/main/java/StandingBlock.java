import com.googlecode.lanterna.terminal.Terminal;
import java.util.concurrent.ThreadLocalRandom;

    public class StandingBlock extends Block {

        public StandingBlock () {
            int x = ThreadLocalRandom.current().nextInt(11, 59);
            int y = 0;
            int blockLength = ThreadLocalRandom.current().nextInt(2, 3);

            for (int i = 0; i < blockLength; i++) {
                Position position = new Position(x, y-blockLength);
                oneBlock.add(position);
                y++;
            }
        }

        @Override
        public void moveBlock(Terminal terminal) throws Exception {
            int y = oneBlock.get(oneBlock.size()-1).getY();
            int x = oneBlock.get(oneBlock.size()-1).getX();

            oneBlock.add(new Position(x, y+1));
            terminal.setCursorPosition(x, y+1);
            terminal.putCharacter(blockChar);

            terminal.setCursorPosition(oneBlock.get(0).getX(), oneBlock.get(0).getY());
            terminal.putCharacter(' ');
            oneBlock.remove(0);
        }

        @Override
        public void printBlock (Terminal terminal) throws Exception {
            for (Position position : oneBlock) {
                terminal.setCursorPosition(position.getX(), position.getY());
                terminal.putCharacter(blockChar);
            }
        }
    }
