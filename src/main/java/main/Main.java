package main;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class Main {

    private static GamePanel gamePanel;
    public static void main(String[] args) throws IOException {


        // tworzenie okna gry
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                try {
                    gamePanel.writeToFile();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                window.dispose();
            }
        });
        window.setResizable(false);
        window.setTitle("Gra");

        // mechanika gry
        gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.setupGame();
        gamePanel.startGameThread();
    }


}
