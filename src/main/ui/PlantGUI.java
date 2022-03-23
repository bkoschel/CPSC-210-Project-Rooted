package ui;

import model.Plant;
import model.Plants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class PlantGUI extends JFrame implements ActionListener {

    private Plant plant;
    private Plants plants;

    private static final String JSON_FILE = "./data/plantList.json";

    private JFrame frame;
    private JPanel mainMenu;
    private JButton addPlant;
    private JButton b1;
    private JButton b2;
    private JButton b3;
    private JButton b4;

    private JPanel plantInfo;
    private JLabel plantInfoLabel;
    private JTextField nameText;
    private JTextField typeText;
    private JTextField statusText;
    private JTextField waterAmountText;
    private JTextField wateredText;
    private JLabel name;
    private JLabel type;
    private JLabel status;
    private JLabel waterAmount;
    private JLabel watered;

    private JPanel plantsPanel;
    private JLabel plantsLabel;

    private JList plantList;
    private DefaultListModel<String> listModel;

    public PlantGUI() {
        super("PlantApp");
        frame = new JFrame("Rooted");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension dimension = new Dimension(800, 400);
        frame.setPreferredSize(dimension);
        listModel = new DefaultListModel<>();

        createIcon();
        initializeMainMenu();
        welcomeMessage();

        initializeButtons();
        addButtons(b1, b2, b3, b4);

        buttonAction();

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
        b4 = new JButton("Exit application");

    }

    public void addButton(JButton button, JPanel panel) {
        Font font = new Font("Ariel", Font.PLAIN, 20);
        button.setFont(font);
        panel.add(button);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        frame.pack();
        setVisible(true);

    }

    public void addButtons(JButton button1, JButton button2, JButton button3, JButton button6) {
        addButton(button1, mainMenu);
        addButton(button2, mainMenu);
        addButton(button3, mainMenu);
        addButton(button6, mainMenu);
    }

    public void buttonAction() {
        b1.addActionListener(this);
        b1.setActionCommand("View your plant list");
        b2.addActionListener(this);
        b2.setActionCommand("Add a plant");
        b3.addActionListener(this);
        b3.setActionCommand("Remove a plant");
        b4.addActionListener(this);
        b4.setActionCommand("Exit application");

    }

    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getActionCommand().equals("View your plant list")) {
            initializePlantsPanel();
        } else if (actionEvent.getActionCommand().equals("Add a plant")) {
            initializePlantPanel();
        } else if (actionEvent.getActionCommand().equals("Remove a plant")) {
            removePlant(plant);
        } else if (actionEvent.getActionCommand().equals("Return to main menu")) {
            backToMainMenu();
        } else if (actionEvent.getActionCommand().equals("Add Plant to list")) {
            addPlantToPlants();
        } else if (actionEvent.getActionCommand().equals("Exit application")) {
            System.exit(0);
        }
    }

    private void initializePlantPanel() {
        plantInfo = new JPanel();

        generatePlantPanel();
        addPlantLabels();
        frame.add(plantInfo);
        JButton mainMenuJButton = returnToMainMenuButton();
        addButton(mainMenuJButton, plantInfo);

        plantInfo.setVisible(true);
        mainMenu.setVisible(false);

    }

    public void generatePlantPanel() {

        addPlant = new JButton("Add Plant to list");
        addPlant.addActionListener(this);
        addPlant.setActionCommand("Add Plant to list");
        //addPlantToPlants();

        generatePlant();
        generatePlantTextField();

        plantInfoLabel = new JLabel();
        plantLabelStyle();

    }

    public void generatePlant() {
        name = new JLabel("Plant name: ");
        type = new JLabel("Plant type: ");
        status = new JLabel("Plant status: ");
        waterAmount = new JLabel("Amount watered: ");
        watered = new JLabel("Watered today? ");
    }

    public void generatePlantTextField() {
        nameText = new JTextField(10);
        typeText = new JTextField(10);
        statusText = new JTextField(10);
        waterAmountText = new JTextField(10);
        wateredText = new JTextField(10);
    }

    public void addPlantLabels() {
        plantInfo.add(name);
        plantInfo.add(nameText);
        plantInfo.add(type);
        plantInfo.add(typeText);
        plantInfo.add(status);
        plantInfo.add(statusText);
        plantInfo.add(waterAmount);
        plantInfo.add(waterAmountText);
        plantInfo.add(watered);
        plantInfo.add(wateredText);

        plantInfo.add(addPlant);
        plantInfo.add(plantInfoLabel);
    }

    public void plantLabelStyle() {
        Font addPlantFont = new Font("Arial", Font.PLAIN, 12);
        Font plantFont = new Font("Arial", Font.PLAIN, 24);

        addPlant.setFont(addPlantFont);
        name.setFont(plantFont);
        type.setFont(plantFont);
        status.setFont(plantFont);
        waterAmount.setFont(plantFont);
        watered.setFont(plantFont);

    }


    public void initializePlantsPanel() {
        plantsPanel = new JPanel();
        JButton mainMenuJButton = new JButton("Return to Main Menu");
        mainMenuJButton.setActionCommand("Return to main menu");
        mainMenuJButton.addActionListener(this);

        generatePlantsPanel();


        frame.add(plantsPanel);
        plantsPanel.setVisible(true);
        mainMenu.setVisible(false);
    }

    private void generatePlantsPanel() {
        plantsPanel = new JPanel();
        Font plantsFont = new Font("Arial", Font.PLAIN, 12);
        JScrollPane scrollPane = new JScrollPane(plantsLabel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        plantsPanel.setFont(plantsFont);
        plantsPanel.add(scrollPane);

        JButton mainMenuJButton = returnToMainMenuButton();
        addButton(mainMenuJButton, plantsPanel);
    }



    public JButton returnToMainMenuButton() {
        JButton mainMenuJButton = new JButton("Return to Main Menu");
        mainMenuJButton.setActionCommand("Return to main menu");
        mainMenuJButton.addActionListener(this);
        return mainMenuJButton;
    }

    public void backToMainMenu() {
        mainMenu.setVisible(true);
        plantsPanel.setVisible(false);
        plantInfo.setVisible(false);

    }

    public void addPlantToPlants() {
        plant = new Plant(nameText.getText(), typeText.getText(), statusText.getText(),
                Integer.parseInt(waterAmountText.getText()), Boolean.parseBoolean(wateredText.getText()));
        plants.addPlant(plant);
        plantsLabel.setText(plants.getListOfPlantNames());

    }


    public void removePlant(Plant plant) {

    }


}
