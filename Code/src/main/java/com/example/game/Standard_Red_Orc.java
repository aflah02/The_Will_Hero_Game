import java.io.Serializable;

public class Standard_Red_Orc extends Orc implements Serializable {

    public Standard_Red_Orc(Position position, int speed, int hitPoints, int damage, int coins) {
        super(position, speed, hitPoints, damage, coins);
    }

    @Override
    public void collide(Player player) {

    }
}
