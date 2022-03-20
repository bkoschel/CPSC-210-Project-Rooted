package ui;

import model.Plant;
import model.Plants;

import javax.swing.*;
import java.awt.*;


public class PlantGUI extends JFrame {

    private Plant plant;
    private Plants plants;

    private static final String JSON_FILE = "./data/plantList.json";

    private JFrame frame;
    private JPanel mainMenu;
    private JButton b1;
    private JButton b2;
    private JButton b3;
    private JButton b4;
    private JButton b5;
    private JButton b6;
    private JButton b7;
    private JButton b8;



    public PlantGUI() {
        frame = new JFrame("Rooted");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension dimension = new Dimension(800, 700);
        frame.setPreferredSize(dimension);

        initializeMainMenu();

        welcomeMessage();

        frame.pack();
        frame.setVisible(true);

        mainMenu.setVisible(true);
    }

    private void initializeMainMenu() {
        mainMenu = new JPanel();
        mainMenu.setBackground(Color.LIGHT_GRAY);
        add(mainMenu);
    }

    private void welcomeMessage() {
        JLabel welcome = new JLabel("Welcome to Rooted");
        JLabel mainScreen = new JLabel();
        addLabel(welcome);
        addImageToLabel(mainScreen);
    }

    private void addLabel(JLabel welcome) {
        Font font = new Font("Arial", Font.BOLD, 40);
        welcome.setFont(font);
        mainMenu.add(welcome);
    }

    private void addImageToLabel(JLabel mainScreen) {
        ImageIcon icon = new ImageIcon("./data/succulent.jpg");
        mainScreen.setIcon(icon);
        Dimension dimension = new Dimension(20, 20);
        setSize(dimension);
        mainMenu.add(mainScreen);
    }

}
