package ui;


import model.Event;
import model.EventLog;

import javax.swing.*;
import java.awt.*;

// referenced ListDemo project from https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html
// calls the Plant App GUI and creates a new PlantApp

public class Main {

    // EFFECTS: creates and shows GUI
    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Rooted");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setPreferredSize(new Dimension(500, 700));

        JComponent newContentpane = new PlantGUI(frame);
        newContentpane.setOpaque(true);
        frame.add(newContentpane);

        frame.pack();
        frame.setVisible(true);
    }


    public static void main(String[] args) {
        // creates a job for the event-dispatcher thread;
        // creates and shows PlantApp's GUI
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });

    }
}
