package object.entity;

import java.awt.*;

public interface IEntity {

    /**
     * zaktualizowanie pozycji postaci
     */
    void move();

    /**
     * wyświetlenie postaci na ekranie
     * @param g2 obiekt klasy Graphics2D
     */
    void draw(Graphics2D g2);

    /**
     * wylosowanie kierunku
     */
    void setAction();

    /**
     * ustawienie domyślnych wartości
     */
    void setDefaultValues();

    /**
     * ustawienie domyślnych wartości
     * @param level poziom trudności
     */
    void setDefaultValues(int level);

    /**
     * interakcja przy spotkaniu z monetą
     * @param i liczba wskazująca czy nastąpiła kolizja
     */
    void interactCoin(int i);

    /**
     * interakcja przy spotkaniu z orkiem
     * @param i liczba wskazująca czy nastąpiła kolizja
     */
    void interactOrc(int i);
    /**
     * interakcja przy spotkaniu ze świnią
     * @param i liczba wskazująca czy nastąpiła kolizja
     */
    void interactPig(int i);
    /**
     * interakcja przy spotkaniu ze szczurem
     * @param i liczba wskazująca czy nastąpiła kolizja
     */
    void interactRat(int i);
    /**
     * interakcja przy spotkaniu z kowadłem
     * @param i liczba wskazująca czy nastąpiła kolizja
     */
    void interactAnvil(int i);

    /**
     * interakcja przy spotkaniu z graczem
     * @param c informacja czy nastąpiła kozlizja
     */
    void interactPlayer(boolean c);

}
