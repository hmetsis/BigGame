
public class Position {

    private int x;
    private int y;
    private int oldX;
    private int oldY;

    public Position (int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX () {
        return x;
    }

    public int getY () {

        return y;
    }

    public void setX(int x) {

        this.x = x;
    }

    public void setY(int y) {

        this.y = y;
    }

    public int getOldX() {

        return oldX;
    }

    public void setOldX(int oldX) {

        this.oldX = oldX;
    }

    public int getOldY() {

        return oldY;
    }

    public void setOldY(int oldY) {

        this.oldY = oldY;
    }
}
