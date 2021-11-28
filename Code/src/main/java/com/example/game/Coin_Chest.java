import java.io.Serializable;

public class Coin_Chest extends Chests implements Serializable {
    int coins;
    public Coin_Chest(Position position, int speed, int coins) {
        super(position, speed);
        this.coins = coins;
    }

    public int getCoins() {
        return coins;
    }

    @Override
    public void collide(Player player) {

    }
}
