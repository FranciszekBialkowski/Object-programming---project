package main;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class EventFight {

    GamePanel gp;

    public EventFight(GamePanel gp){
        this.gp = gp;

        gp.gameState = gp.pauseState;
        JFrame fightWindow = new JFrame("Walka");
        fightWindow.setResizable(false);
        fightWindow.setSize(400,400);
        fightWindow.setVisible(true);
        fightWindow.setLocationRelativeTo(null);
        fightWindow.setLayout(null);

        JButton attackButton = new JButton("Attack");
        attackButton.setBounds(50,100,95,30);
        fightWindow.add(attackButton);

        JButton abilityButton = new JButton("Use Ability");
        JButton runButton = new JButton("Run");

        fightWindow.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                gp.gameState = gp.playState;
            }
        });
    }
}
