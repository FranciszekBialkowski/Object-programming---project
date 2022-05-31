package extra;

public class Weapon extends Item{

    int damage;

    public Weapon(int damage){
        name = "Staff";
        requiredLevel = 1;
        upgradeCost = 8;
        this.damage = damage;
    }

    public void upgrade(){
    }
}
