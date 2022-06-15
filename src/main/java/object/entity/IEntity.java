package object.entity;

import java.awt.*;

public interface IEntity {

    void move();

    void draw(Graphics2D g2);

    void setAction();
    void setDefaultValues();

    void setDefaultValues(int level);

    void interactCoin(int i);

    void interactOrc(int i);
    void interactPig(int i);
    void interactRat(int i);
    void interactAnvil(int i);

    void interactPlayer(boolean c);

}
