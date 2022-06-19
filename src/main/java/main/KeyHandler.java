package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import object.entity.Entity;


public class KeyHandler implements KeyListener {

    public boolean upClicked, downClicked, leftClicked, rightClicked;
    private final GamePanel gp;
    private int level;

    /**
     * Konstruktor
     */
    public KeyHandler(GamePanel gp){
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * kliknięcie przycisku do poruszania się
     * @param e the event to be processed
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if(gp.gameState == gp.menuState) {

                if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
                    gp.ui.commandNum--;
                }
                if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
                    gp.ui.commandNum++;
                }
                if (code == KeyEvent.VK_ENTER) {
                    if (gp.ui.commandNum == 0) {
                        gp.player.mage = "Fire Mage";
                        gp.player.setDefaultValues();
                        gp.gameState = gp.secondMenuState;
                    }
                    if (gp.ui.commandNum == 1) {
                        gp.player.mage = "Ice Mage";
                        gp.player.setDefaultValues();
                        gp.gameState = gp.secondMenuState;
                    }
                    if (gp.ui.commandNum == 2) {
                        gp.player.mage = "Lighting Mage";
                        gp.player.setDefaultValues();
                        gp.gameState = gp.secondMenuState;
                    }
                }
        }else if(gp.gameState == gp.secondMenuState){
            if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
                gp.ui.commandNum--;
            }
            if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
                gp.ui.commandNum++;
            }
            if (code == KeyEvent.VK_ENTER) {
                if (gp.ui.commandNum == 0) {
                    level = 1;
                }
                if (gp.ui.commandNum == 1) {
                    level = 3;
                }
                if (gp.ui.commandNum == 2) {
                    level = 5;
                }

                for(Entity orc: gp.orcs){
                    if (orc!=null) orc.setDefaultValues(level);
                }
                gp.gameState = gp.playState;
            }
        }

        if(gp.gameState == gp.playState) {

            if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
                upClicked = true;
            }
            if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
                leftClicked = true;
            }
            if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
                downClicked = true;
            }
            if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
                rightClicked = true;
            }
        }
    }

    /**
     * puszczenie przycisku do poruszania się
     * @param e the event to be processed
     */
    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            upClicked = false;
        }
        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
            leftClicked = false;
        }
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            downClicked = false;
        }
        if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
            rightClicked = false;
        }
    }
}
