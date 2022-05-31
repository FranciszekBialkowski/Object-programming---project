package extra;

public class Armor extends Item{

    int health;

    public Armor(int health){
        name = "Robe";
        requiredLevel = 1;
        upgradeCost = 10;
        this.health = health;
    }

    public void upgrade(){
    }
}
