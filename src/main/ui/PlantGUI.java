package ui;

import model.Plant;
import model.Plants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class PlantGUI extends JFrame  {

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

    private JPanel plantInfo;
    private JLabel plantInfoLabel;
    private JTextField nameText;
    private JTextField typeText;
    private JTextField statusText;
    private JTextField waterAmountText;
    private JLabel name;
    private JLabel type;
    private JLabel status;
    private JLabel waterAmount;

    private JPanel plantsPanel;
    private JLabel plantsLabel;

    public PlantGUI() {
        super("PlantApp");
        frame = new JFrame("Rooted");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension dimension = new Dimension(800, 400);
        frame.setPreferredSize(dimension);

        createIcon();
        initializeMainMenu();
        welcomeMessage();

        initializeButtons();
        addButtons(b1, b2, b3, b4, b5, b6);

        frame.pack();
        frame.setVisible(true);

    }


    public void welcomeMessage() {
        JLabel welcome = new JLabel("Welcome to Rooted", SwingConstants.CENTER);
        addMainLabel(welcome);

    }

    private void addMainLabel(JLabel welcome) {
        Font font = new Font("Arial", Font.BOLD, 40);
        welcome.setFont(font);
        mainMenu.add(welcome);
    }

    public void createIcon() {
        ImageIcon img = new ImageIcon("./data/succulent.jpg");
        frame.setIconImage(img.getImage());
    }

    private void initializeMainMenu() {
        mainMenu = new JPanel();
        frame.add(mainMenu);
        mainMenu.setVisible(true);
    }

    public void initializeButtons() {
        b1 = new JButton("View your plant list");
        b2 = new JButton("Add a plant");
        b3 = new JButton("Remove a plant");
        b4 = new JButton("Save plant list");
        b5 = new JButton("Load plant list");
        b6 = new JButton("Exit application");

    }

    public void addButton(JButton button, JPanel panel) {
        Font font = new Font("Ariel", Font.PLAIN, 20);
        button.setFont(font);
        panel.add(button);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        frame.pack();
        setVisible(true);

    }

    public void addButtons(JButton button1, JButton button2, JButton button3,
                           JButton button4, JButton button5, JButton button6) {
        addButton(button1, mainMenu);
        addButton(button2, mainMenu);
        addButton(button3, mainMenu);
        addButton(button4, mainMenu);
        addButton(button5, mainMenu);
        addButton(button6, mainMenu);
    }

    //public void performAction(ActionEvent actionEvent) {
    //    if (actionEvent.getActionCommand())
    //}

    public void initializePlantsPanel() {
        frame.add(plantsPanel);
        plantsPanel.setVisible(true);
        mainMenu.setVisible(false);
    }


}
