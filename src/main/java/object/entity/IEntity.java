package object.entity;

import java.awt.*;

public interface IEntity {

    void move();

    void draw(Graphics2D g2);

    void setAction();

    void interactCoin(int i);

    void interactAggressiveCreature(int i);
    void interactNeutralCreature(int i);
    void interactSmallCreature(int i);

    void interactPlayer(boolean c);

}
