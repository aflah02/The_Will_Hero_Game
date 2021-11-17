import java.io.Serializable;

public abstract class TNT extends Game_Objects implements Serializable {
    public TNT(Position position, int speed) {
        super(position, speed);
    }
}
