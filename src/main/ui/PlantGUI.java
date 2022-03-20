package ui;

import model.Plant;
import model.Plants;

import javax.swing.*;
import java.awt.*;


public class PlantGUI extends JFrame {

    private Plant plant;
    private Plants plants;

    private static final String JSON_FILE = "./data/plantList.json";

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
        super("PlantApp");
        Dimension dimension = new Dimension(500, 500);
        setSize(dimension);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        initializeMainMenu();

        welcomeMessage();

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
    }

}
