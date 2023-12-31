package main;

import java.awt.Image;


import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;
import javax.swing.JFrame;

public class EventAnvil {

    private final GamePanel gp;
    private final JFrame anvilWindow;
    private final JButton upgradeStaffButton;
    private final JButton upgradeRobeButton;
    private final JButton exitButton;

    /**
     * Konstruktor
     */
    public EventAnvil(GamePanel gp) {
        this.gp = gp;

        gp.gameState = gp.pauseState;

        // okno
        anvilWindow = new JFrame("Kowadło");
        anvilWindow.setResizable(false);
        anvilWindow.setSize(600, 400);
        anvilWindow.setLocationRelativeTo(null);
        anvilWindow.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        // nazwa przedmiotu
        Font font1 = new Font("Verdana", Font.BOLD,20);
        JLabel nameLabel1 = new JLabel("Różdżka");
        nameLabel1.setFont(font1);
        nameLabel1.setBounds(140,20,200,20);
        anvilWindow.add(nameLabel1);

        // nazwa przedmiotu
        JLabel nameLabel2 = new JLabel("Szata");
        nameLabel2.setFont(font1);
        nameLabel2.setBounds(355,20,200,20);
        anvilWindow.add(nameLabel2);

        // przycisk Ulepsz różdżkę
        upgradeStaffButton = new JButton("Ulepsz");
        upgradeStaffButton.setBounds(110, 230, 150, 30);
        upgradeStaffButton.addActionListener(e -> clickStaffUpgrade());
        upgradeStaffButton.setEnabled(true);
        anvilWindow.add(upgradeStaffButton);

        // przycisk Ulepsz szatę
        upgradeRobeButton = new JButton("Ulepsz");
        upgradeRobeButton.setBounds(310, 230, 150, 30);
        upgradeRobeButton.addActionListener(e -> clickRobeUpgrade());
        upgradeRobeButton.setEnabled(true);
        anvilWindow.add(upgradeRobeButton);

        // Przycisk Wyjdź
        exitButton = new JButton("Wyjdź");
        exitButton.setBounds(20, 310, 150, 30);
        exitButton.addActionListener(e -> exit());
        exitButton.setEnabled(true);
        anvilWindow.add(exitButton);

        // tekst z poziomem szaty
        JLabel armorLVLLabel = new JLabel("Poziom szaty: " + gp.armor.level);
        armorLVLLabel.setBounds(340, 120, 150, 30);
        anvilWindow.add(armorLVLLabel);

        // tekst z poziomem różdżki
        JLabel weaponLVLLabel = new JLabel("Poziom różdżki: " + gp.weapon.level);
        weaponLVLLabel.setBounds(140, 120, 150, 30);
        anvilWindow.add(weaponLVLLabel);

        // tekst z pancerzem szaty
        JLabel armorHPLabel = new JLabel("Pancerz szaty: " + gp.armor.health);
        armorHPLabel.setBounds(335, 150, 150, 30);
        anvilWindow.add(armorHPLabel);

        // tekst z obrażeniami różdżki
        JLabel weaponDMGLabel = new JLabel("Obrażenia różdżki: " + gp.weapon.damage);
        weaponDMGLabel.setBounds(130, 150, 150, 30);
        anvilWindow.add(weaponDMGLabel);

        // tekst z kosztem ulepszenia różdżki
        JLabel upgradeStaffCostLabel = new JLabel("Koszt ulepszenia różdżki: " + gp.weapon.upgradeCost);
        upgradeStaffCostLabel.setBounds(110, 180, 170, 30);
        anvilWindow.add(upgradeStaffCostLabel);

        // tekst z kosztem ulepszenia szaty
        JLabel upgradeRobeCostLabel = new JLabel("Koszt ulepszenia szaty: " + gp.armor.upgradeCost);
        upgradeRobeCostLabel.setBounds(310, 180, 170, 30);
        anvilWindow.add(upgradeRobeCostLabel);

        // tekst informujący
        JLabel infoLabel = new JLabel();
        infoLabel.setForeground(Color.darkGray);
        infoLabel.setBounds(50,300,500,20);
        anvilWindow.add(infoLabel);
        anvilWindow.setLayout(null);
        anvilWindow.setVisible(true);

        // dodanie ikony różdżki
        Image staffImage;
        try {
            staffImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/staff.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        JLabel staffPhoto = new JLabel("");
        staffPhoto.setIcon(new ImageIcon(staffImage));
        staffPhoto.setBounds(170, 70, 100, 30);
        anvilWindow.getContentPane().add(staffPhoto);

        // dodanie ikony szaty
        Image robeImage;
        try {
            robeImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/robe.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        JLabel robePhoto = new JLabel("");
        robePhoto.setIcon(new ImageIcon(robeImage));
        robePhoto.setBounds(370, 70, 100, 30);
        anvilWindow.getContentPane().add(robePhoto);
    }

    /**
     * Kliknięcie przycisku ulepszenia róźdżki
     */
    private void clickStaffUpgrade(){
        upgradeStaffButton.setEnabled(true);
        exitButton.setEnabled(true);
        Timer timer = new Timer(1, e -> {
            checkStaffUpgrade();
        });
        timer.setRepeats(false);
        timer.start();
    }

    /**
     * Kliknięcie przycisku ulepszenia szaty
     */
    private void clickRobeUpgrade(){
        upgradeRobeButton.setEnabled(true);
        exitButton.setEnabled(true);
        Timer timer = new Timer(1, e -> {
            checkRobeUpgrade();
        });
        timer.setRepeats(false);
        timer.start();
    }

    /**
     * sprawdzenie czy ulepszenie różdżki się powiodło
     */
    private void checkStaffUpgrade() {
        if (gp.player.playerCoins >= gp.weapon.upgradeCost) {
            JOptionPane.showMessageDialog(null, "Udało się ulepszyć różdżkę",
                    "Ulepszenie pomyślne", JOptionPane.INFORMATION_MESSAGE);

            gp.player.playerCoins-=gp.weapon.upgradeCost;
            gp.weapon.upgrade();
            exit();
        }
        else {
            JOptionPane.showMessageDialog(null, "Nie udało się ulepszyć różdżki",
                    "Ulepszenie niewykonane", JOptionPane.INFORMATION_MESSAGE);
            exit();
        }
    }

    /**
     * sprawdzenie czy ulepszenie szaty się powiodło
     */
    private void checkRobeUpgrade() {
        if (gp.player.playerCoins >= gp.armor.upgradeCost) {
            JOptionPane.showMessageDialog(null,"Udało się ulepszyć szatę",
                    "Ulepszenie pomyślne",JOptionPane.INFORMATION_MESSAGE);

            gp.player.playerCoins-=gp.armor.upgradeCost;
            gp.armor.upgrade();

            exit();
        }
        else{
            JOptionPane.showMessageDialog(null, "Nie udało się ulepszyć szaty",
                    "Nie wystarczającej ilości monet", JOptionPane.INFORMATION_MESSAGE);
            exit();
        }
    }

    /**
     * wyjście z okna kowadła
     */
    private void exit(){
        gp.gameState = gp.playState;
        anvilWindow.dispose();
    }
}
