import java.io.Serializable;

public class Boss_Orc extends Orc implements Serializable {


    public Boss_Orc(Position position, int speed, int hitPoints, int damage, int coins) {
        super(position, speed, hitPoints, damage, coins);
    }

    @Override
    public void collide(Player player) {

    }
}
