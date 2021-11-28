import java.io.Serializable;

public class TNT extends Game_Objects implements Serializable {
    int timeToBurst;
    int damage;
    Boolean isBurst;
    int radius;

    public int getTimeToBurst() {
        return timeToBurst;
    }

    public int getDamage() {
        return damage;
    }

    public Boolean getBurst() {
        return isBurst;
    }

    public void Burst(){
        this.isBurst = true;
    }

    public TNT(Position position, int speed, int timeToBurst, int damage, int radius) {
        super(position, speed);
        this.timeToBurst = timeToBurst;
        this.damage = damage;
        this.isBurst = false;
        this.radius = radius;
    }

    @Override
    public void collide(Player player) {

    }
}
