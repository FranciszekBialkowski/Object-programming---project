package main;

import object.entity.Entity;

import javax.swing.*;
import java.awt.*;


public class EventFight {

    GamePanel gp;
    Entity monster;
    JFrame fightWindow;
    JButton attackButton;
    JButton abilityButton;
    JButton runButton;
    JLabel playerHPLabel;
    JLabel monsterHPLabel;
    JLabel infoLabel;
    boolean shield = false;
    boolean freeze = false;


    public EventFight(GamePanel gp, Entity monster) {
        this.gp = gp;
        this.monster = monster;
        gp.gameState = gp.pauseState;

        // okno
        fightWindow = new JFrame("Walka");
        fightWindow.setResizable(false);
        fightWindow.setSize(600, 400);
        fightWindow.setLocationRelativeTo(null);
        fightWindow.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        // nazwa przeciwnika
        Font font1 = new Font("Verdana", Font.BOLD,20);
        JLabel nameLabel = new JLabel("Ork, lvl " + monster.level);
        nameLabel.setFont(font1);
        nameLabel.setBounds(220,10,200,20);
        fightWindow.add(nameLabel);

        // przycisk Atakuj
        attackButton = new JButton("Atakuj");
        attackButton.setBounds(50, 100, 150, 30);
        attackButton.addActionListener(e -> clickAttack());
        attackButton.setEnabled(false);
        fightWindow.add(attackButton);

        // Przycisk Umiejętność
        abilityButton = new JButton("Umiejętność");
        abilityButton.setBounds(50, 150, 150, 30);
        abilityButton.addActionListener(e-> clickAbility());
        abilityButton.setEnabled(false);
        fightWindow.add(abilityButton);

        // Przycisk Uciekaj
        runButton = new JButton("Uciekaj");
        runButton.setBounds(50, 200, 150, 30);
        runButton.addActionListener(e -> exit());
        runButton.setEnabled(false);
        fightWindow.add(runButton);

        // tekst ze zdrowiem gracza
        playerHPLabel = new JLabel("Twoje zdrowie: " + gp.player.health);
        playerHPLabel.setBounds(250, 100, 150, 30);
        fightWindow.add(playerHPLabel);

        // tekst ze zdrowiem przeciwnika
        monsterHPLabel = new JLabel("Zdrowie przeciwnika: " + monster.health);
        monsterHPLabel.setBounds(250, 150, 150, 30);
        fightWindow.add(monsterHPLabel);

        // tekst informujacy co sie dzieje w danej chwili
        infoLabel = new JLabel();
        infoLabel.setForeground(Color.darkGray);
        infoLabel.setBounds(50,300,500,20);
        fightWindow.add(infoLabel);

        fightWindow.setLayout(null);
        fightWindow.setVisible(true);

        playerTurn();
    }

    // wciśnięcie przycisku Atakuj
    public void clickAttack() {
        attackButton.setEnabled(false);
        abilityButton.setEnabled(false);
        runButton.setEnabled(false);
        infoLabel.setText("Zadajesz przeciwnikowi " + gp.player.attackDamage + " obrażeń");
        monster.health -= gp.player.attackDamage;
        playerHPLabel.setText("Twoje zdrowie: " + gp.player.health);
        monsterHPLabel.setText("Zdrowie przeciwnika: " + monster.health);
        Timer timer = new Timer(2000, e -> {
            checkWin();
            monsterTurn();
        });
        timer.setRepeats(false);
        timer.start();

    }

    // wciśnięcie przycisku Umiejętność
    public void clickAbility(){
        attackButton.setEnabled(false);
        abilityButton.setEnabled(false);
        runButton.setEnabled(false);
        infoLabel.setText("Używasz umiejętności");
        Timer timer = new Timer(2000, e -> {
            if (gp.player.mage.equals("Fire Mage")){
                infoLabel.setText("Zadajesz przeciwnikowi " + (gp.player.attackDamage*2) + " obrażeń");
                monster.health -= gp.player.attackDamage*2;
                playerHPLabel.setText("Twoje zdrowie: " + gp.player.health);
                monsterHPLabel.setText("Zdrowie przeciwnika: " + monster.health);
                Timer time1 = new Timer(2000,e1 -> checkWin());
                time1.setRepeats(false);
                time1.start();

            } else if (gp.player.mage.equals("Ice Mage")){
                infoLabel.setText("Zamrażasz przeciwnika");
                freeze = true;

            }else {
                infoLabel.setText("Otrzymujesz tarcze");
                shield = true;
            }
            Timer timer2 = new Timer(2000,e1-> monsterTurn());
            timer2.setRepeats(false);
            timer2.start();

        });
        timer.setRepeats(false);
        timer.start();

    }

    // Tura gracza
    public void playerTurn() {
        infoLabel.setText("Twoja tura");
        attackButton.setEnabled(true);
        abilityButton.setEnabled(true);
        runButton.setEnabled(true);
    }

    // tura przeciwnika
    public void monsterTurn() {

        infoLabel.setText("Tura przeciwnika");
        Timer timer = new Timer(1000, e -> {
            if (shield){
                infoLabel.setText("Twoja tarcza pochłania obrażenia od przeciwnika");
            } else if (freeze){
                infoLabel.setText("Przeciwnik nie może sie ruszyc");
            } else {
                infoLabel.setText("Przeciwnik zadaje ci " + monster.attackDamage + " obrażeń");
                gp.player.health -= monster.attackDamage;
                playerHPLabel.setText("Twoje zdrowie: " + gp.player.health);
                monsterHPLabel.setText("Zdrowie przeciwnika: " + monster.health);
                Timer timer1 = new Timer(2000,e1->checkWin());
                timer1.setRepeats(false);
                timer1.start();
            }
            Timer timer2 = new Timer(2000,e2 -> playerTurn());
            timer2.setRepeats(false);
            timer2.start();
        });
        timer.start();
        timer.setRepeats(false);

    }

    // sprawdzenie czy walka dobiegła końca
    public void checkWin() {
        if (gp.player.health <= 0) {
            JOptionPane.showMessageDialog(null,"Przegrałeś walke i straciłeś życie",
                    "Przegrana",JOptionPane.INFORMATION_MESSAGE);
            gp.player.life--;
            gp.player.health = gp.player.maxHealth;
            monster.health = monster.maxHealth;
            gp.checkLose();
            exit();
            
        } else if (monster.health <= 0) {
            int reward = monster.gold;
            JOptionPane.showMessageDialog(null,"Wygrałeś walke i zdobyłeś " + reward + " monet",
                                            "Wygrana",JOptionPane.INFORMATION_MESSAGE);
            gp.player.playerCoins += reward;
            for (int i = 0; i<gp.orcs.length; i++){
                if (gp.orcs[i] == monster){
                    gp.orcs[i] = null;
                    gp.orcCounter--;
                }
            }
            gp.player.health = gp.player.maxHealth;
            exit();
        }
    }

    // wyjście z okna walki
    public void exit(){
        gp.gameState = gp.playState;
        gp.player.health = gp.player.maxHealth;
        monster.health = monster.maxHealth;
        fightWindow.dispose();
    }
}
