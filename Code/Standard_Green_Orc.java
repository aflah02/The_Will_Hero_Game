import java.io.Serializable;

public class Standard_Green_Orc extends Orc implements Serializable {

    public Standard_Green_Orc(Position position, int speed, int hitPoints, int damage, int coins) {
        super(position, speed, hitPoints, damage, coins);
    }

    @Override
    public void collide(Player player) {

    }
}
