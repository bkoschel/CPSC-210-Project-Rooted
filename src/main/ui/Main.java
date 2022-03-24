package ui;


import javax.swing.*;

public class Main {

    // referenced ListDemo project from https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html
    // EFFECTS: creates and shows GUI
    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Rooted");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JComponent newContentpane = new PlantGUI(frame);
        newContentpane.setOpaque(true);
        frame.setContentPane(newContentpane);

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
