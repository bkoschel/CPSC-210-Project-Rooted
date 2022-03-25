package ui;

import model.Plant;
import model.Plants;
import persistence.PlantJsonReader;
import persistence.PlantJsonWriter;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.ListSelectionModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

// referenced ListDemo/MenuDemo/ButtonMenu/IconDemo/TextFieldDemo/TextAreaDemo/SplitPaneDemo
// from https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html
// constructs a GUI for the Plant App
public class PlantGUI extends JPanel implements ListSelectionListener {

    private JList list;
    private DefaultListModel<String> listModel;
    private JFrame frame;

    private Plants plantList;

    private static final String JSON_FILE = "./data/plantList.json";
    private PlantJsonReader jsonReader;
    private PlantJsonWriter jsonWriter;

    private JSplitPane splitPane;
    private JTextField name;
    private JTextField type;
    private JTextField status;
    private JTextField waterAmount;
    private JTextField watered;

    private JButton addButton;
    private JButton removeButton;

    private AddPlantListener addPlantListener;


    // EFFECTS: creates all the components of the GUI
    public PlantGUI(JFrame frame) {
        super(new BorderLayout());

        jsonReader = new PlantJsonReader(JSON_FILE);
        jsonWriter = new PlantJsonWriter(JSON_FILE);

        listModel = new DefaultListModel<>();
        plantList = new Plants();
        this.frame = frame;

        createMenuBar();

        createIcon();
        createSplitPlane();

        initializeRemoveButton();
        initializeAddButton();
        initializeJTextFields();
        createPanel(splitPane);

    }

    // MODIFIES: this
    // EFFECTS: creates the menu bar and menu items and initiates the save and load
    //          listeners
    private void createMenuBar() {
        Font menuFont = new Font("Georgia", Font.PLAIN, 24);
        Font menuItemFont = new Font("Georgia", Font.PLAIN, 20);
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        menu.setFont(menuFont);
        JMenuItem menuItem = new JMenuItem("Save");
        menuItem.setFont(menuItemFont);
        SaveListener saveListener = new SaveListener();
        menuItem.setActionCommand("Save");
        menuItem.addActionListener(saveListener);
        menu.add(menuItem);

        JMenuItem menuItem1 = new JMenuItem("Load");
        menuItem1.setFont(menuItemFont);
        LoadListener loadListener = new LoadListener();
        menuItem1.setActionCommand("Load");
        menuItem1.addActionListener(loadListener);
        menu.add(menuItem1);

        menuBar.add(menu);
        frame.setJMenuBar(menuBar);
    }

    // MODIFIES: this
    // EFFECTS: creates a split pane with scroll pane and JTextArea
    private void createSplitPlane() {
        JScrollPane scrollPane = createList();
        scrollPane.setPreferredSize(new Dimension(100, 100));
        JTextArea area = new JTextArea(6, 30);
        JScrollPane areaScrollPane = new JScrollPane(area);
        area.setEditable(false);
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scrollPane, areaScrollPane);
    }

    // MODIFIES: this
    // EFFECTS: creates an icon image for the GUI frame
    public void createIcon() {
        ImageIcon img = new ImageIcon("./data/succulent.jpg");
        frame.setIconImage(img.getImage());
    }

    // MODIFIES: this, splitPane and addPlant button
    // EFFECTS: constructs a button panel and adds the buttons
    private void createPanel(JSplitPane splitPane) {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        buttonPanel.add(Box.createHorizontalStrut(0));
        buttonPanel.add(Box.createHorizontalStrut(0));

        JPanel panel = constructPlantLabels();

        buttonPanel.add(panel);
        buttonPanel.add(Box.createHorizontalStrut(10));
        buttonPanel.add(addButton);
        buttonPanel.add(Box.createHorizontalStrut(10));
        buttonPanel.add(removeButton);
        buttonPanel.add(Box.createHorizontalStrut(10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20,20));

        add(splitPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.PAGE_START);
    }


    // EFFECTS: constructs the JTextFields of the button panel
    @SuppressWarnings("methodlength")
    private JPanel constructPlantLabels() {
        Font labelFont = new Font("Georgia", Font.BOLD, 20);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel nameLabel = new JLabel("Plant Name");
        nameLabel.setFont(labelFont);
        JLabel typeLabel = new JLabel("Plant Type (fern, cactus, etc");
        typeLabel.setFont(labelFont);
        JLabel statusLabel = new JLabel("Status (healthy, dead, ok)");
        statusLabel.setFont(labelFont);
        JLabel waterAmountLabel = new JLabel("Water Amount (# of days per week)");
        waterAmountLabel.setFont(labelFont);
        JLabel wateredLabel = new JLabel("Watered? (true or false)");
        wateredLabel.setFont(labelFont);

        panel.add(nameLabel);
        panel.add(this.name);
        panel.add(typeLabel);
        panel.add(this.type);
        panel.add(statusLabel);
        panel.add(this.status);
        panel.add(waterAmountLabel);
        panel.add(this.waterAmount);
        panel.add(wateredLabel);
        panel.add(this.watered);

        return panel;
    }

    // MODIFIES: this
    // EFFECTS: constructs the name, type, status, watered amount and watered boolean
    //          calls on each plant method
    private void initializeJTextFields() {
        initializeNameJTextField();
        initializeTypeJTextField();
        initializeStatusJTextField();
        initializeWaterAmountJTextField();
        initializeWateredJTextField();

    }

    // MODIFIES: this
    // EFFECTS: constructs the name JTextField
    private void initializeNameJTextField() {
        Font textFieldFont = new Font("Georgia", Font.PLAIN, 18);
        Dimension textFieldDimension = new Dimension(10, 30);
        name = new JTextField(10);
        name.setFont(textFieldFont);
        name.setPreferredSize(textFieldDimension);
        name.addActionListener(addPlantListener);
        name.getDocument().addDocumentListener(addPlantListener);
    }

    // MODIFIES: this
    // EFFECTS: constructs the type JTextField
    private void initializeTypeJTextField() {
        Font textFieldFont = new Font("Georgia", Font.PLAIN, 18);
        Dimension textFieldDimension = new Dimension(12, 30);
        type = new JTextField(20);
        type.setFont(textFieldFont);
        type.setPreferredSize(textFieldDimension);
        type.addActionListener(addPlantListener);
        type.getDocument().addDocumentListener(addPlantListener);
    }

    // MODIFIES: this
    // EFFECTS: constructs the status JTextField
    private void initializeStatusJTextField() {
        Font textFieldFont = new Font("Georgia", Font.PLAIN, 18);
        Dimension textFieldDimension = new Dimension(12, 30);
        status = new JTextField(20);
        status.setFont(textFieldFont);
        status.setPreferredSize(textFieldDimension);
        status.addActionListener(addPlantListener);
        status.getDocument().addDocumentListener(addPlantListener);
    }

    // MODIFIES: this
    // EFFECTS: constructs the water amount JTextField
    private void initializeWaterAmountJTextField() {
        Font textFieldFont = new Font("Georgia", Font.PLAIN, 18);
        Dimension textFieldDimension = new Dimension(12, 30);
        waterAmount = new JTextField(20);
        waterAmount.setFont(textFieldFont);
        waterAmount.setPreferredSize(textFieldDimension);
        waterAmount.addActionListener(addPlantListener);
        waterAmount.getDocument().addDocumentListener(addPlantListener);
    }

    // MODIFIES: this
    // EFFECTS: constructs the watered JTextField
    private void initializeWateredJTextField() {
        Font textFieldFont = new Font("Georgia", Font.PLAIN, 18);
        Dimension textFieldDimension = new Dimension(12, 30);
        watered = new JTextField(20);
        watered.setFont(textFieldFont);
        watered.setPreferredSize(textFieldDimension);
        watered.addActionListener(addPlantListener);
        watered.getDocument().addDocumentListener(addPlantListener);
    }

    // MODIFIES: this
    // EFFECTS: initializes the remove plant button and listener
    private void initializeRemoveButton() {
        removeButton = new JButton("Remove Plant");
        RemovePlantListener removePlantListener = new RemovePlantListener();
        removeButton.setActionCommand("Remove Plant");
        removeButton.addActionListener(removePlantListener);
        removeButton.setEnabled(false);
    }

    // MODIFIES: this
    // EFFECTS: initializes the add plant button and add listener
    private void initializeAddButton() {
        addButton = new JButton("Add Plant");
        addPlantListener = new AddPlantListener(addButton);
        addButton.setActionCommand("Add Plant");
        addButton.addActionListener(addPlantListener);
        addButton.setEnabled(false);
    }

    // MODIFIES: this
    // EFFECTS: creates a plant list and assigns it to the panel
    private JScrollPane createList() {
        Font listFont = new Font("Georgia", Font.PLAIN, 20);
        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.addListSelectionListener(this);
        list.setVisibleRowCount(10);
        list.setFont(listFont);

        return new JScrollPane(list);
    }



    // a listener for the addPlant button
    class AddPlantListener implements ActionListener, DocumentListener {
        private boolean alreadyEnabled = false;
        private JButton button;

        // EFFECTS: initiates the addButton
        public AddPlantListener(JButton button) {
            this.button = button;
        }

        // MODIFIES: this
        // EFFECTS: adds plants to the plant list; clears the textField,
        //         inserts plants into the listModel; makes new plan visible
        public void actionPerformed(ActionEvent ae) {
            Plant plant = new Plant(name.getText(), type.getText(), status.getText(),
                    Integer.parseInt(waterAmount.getText()), Boolean.parseBoolean(watered.getText()));

            plantList.addPlant(plant);

            int index = list.getSelectedIndex();
            if (index == -1) {
                index = 0;
            } else {
                index++;
            }

            listModel.insertElementAt(name.getText(), index);

            PlantGUI.this.name.requestFocusInWindow();
            PlantGUI.this.name.setText("");
            PlantGUI.this.type.requestFocusInWindow();
            PlantGUI.this.type.setText("");
            PlantGUI.this.status.requestFocusInWindow();
            PlantGUI.this.status.setText("");
            PlantGUI.this.waterAmount.requestFocusInWindow();
            PlantGUI.this.waterAmount.setText("");
            PlantGUI.this.watered.requestFocusInWindow();
            PlantGUI.this.watered.setText("");

            list.setSelectedIndex(index);
            list.ensureIndexIsVisible(index);
        }


        @Override
        // MODIFIES: this
        // EFFECTS: creates a notification that an attribute was inserted into the document
        public void insertUpdate(DocumentEvent e) {
            enableButton();
        }

        @Override
        // MODIFIES: thus
        // EFFECTS: creates a notification that a part of the document has been removed
        public void removeUpdate(DocumentEvent e) {
            handleEmptyTextField(e);
        }

        @Override
        // MODIFIES: this
        // EFFECTS: creates notification when an attribute is changed
        public void changedUpdate(DocumentEvent e) {
            if (!handleEmptyTextField(e)) {
                enableButton();
            }
        }

        // helper function for addPlantButton
        // MODIFIES: this
        // EFFECTS: enables button
        private void enableButton() {
            if (!alreadyEnabled) {
                button.setEnabled(true);
            }
        }

        // helper function addPlant button
        // MODIFIES: this
        // EFFECTS: if the text field is empty it does not enable to button and returns false
        //          otherwise enables button and returns true
        private boolean handleEmptyTextField(DocumentEvent e) {
            if (e.getDocument().getLength() <= 0) {
                button.setEnabled(false);
                alreadyEnabled = false;
                return true;
            }
            return false;
        }
    }

    // remove plant listener
    class RemovePlantListener implements  ActionListener {
        // MODIFIES: this
        // EFFECTS: removes a selected item from the list and the panel
        public void actionPerformed(ActionEvent e) {
            int index = list.getSelectedIndex();
            listModel.remove(index);

            int size = listModel.getSize();

            if (size == 0) {
                removeButton.setEnabled(false);
                splitPane.setRightComponent(new JTextArea("Empty"));
                splitPane.setVisible(true);
            } else {
                if (index == listModel.getSize()) {
                    index--;
                }
                list.setSelectedIndex(index);
                list.ensureIndexIsVisible(index);
                plantList.removePlant(index);
            }
        }
    }


    // save button listener
    class SaveListener implements ActionListener {
        // MODIFIES: this
        // EFFECTS: when the save button is pressed opens, and writes to a JSON file of the current plant list
        public void actionPerformed(ActionEvent e) {
            try {
                jsonWriter.open();
                jsonWriter.write(plantList);
                jsonWriter.close();
                createAnimation();
            } catch (IOException i) {
                System.out.println("Unable to save " + JSON_FILE + " Json file");
            }
        }
    }

    // load button listener
    class LoadListener implements ActionListener {
        // MODIFIES: this
        // EFFECTS: when the load button is pressed reads from a JSON file and loads the plant list
        //          to the splitPane panel
        public void actionPerformed(ActionEvent e) {
            try {
                plantList = jsonReader.read();
                for (Plant p: plantList.getPlants()) {
                    listModel.insertElementAt(p.getPlantName(), 0);
                }
            } catch (IOException i) {
                System.out.println("Unable to load " + JSON_FILE);
            }
        }
    }


    @Override
    // MODIFIES: this
    // EFFECTS: prints out the right component of the scroll pane; enables removePlant Button
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {
            if (list.getSelectedIndex() == -1) {
                removeButton.setEnabled(false);
            } else {
                removeButton.setEnabled(true);
                updatePlantInfo(listModel.get(list.getSelectedIndex()));
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: prints out plant info on the right side of the scroll pane
    private void updatePlantInfo(String plantName) {
        Font listFont = new Font("Georgia", Font.PLAIN, 20);
        for (Plant p: plantList.getPlants()) {
            if (p.getPlantName().equals(plantName)) {
                JTextArea plantInfo = new JTextArea("\nPlant Name: " + p.getPlantName()
                        + "\nPlant Type: " + p.getPlantType()
                        + "\nPlant Status: " + p.getPlantStatus()
                        + "\nWater Amount: " + p.getPlantWater()
                        + "\nWatered Today? " + p.getWatered());
                plantInfo.setFont(listFont);
                splitPane.setRightComponent(plantInfo);

            }
        }
    }

    // MODIFIES: this
    // EFFECTS: creates a gift panel for the saved plant list
    private void createAnimation() {
        ImageIcon gif = new ImageIcon("./data/fallingleaves.png");
        JLabel gifLabel = new JLabel(gif);
        JFrame gifFrame = new JFrame();
        JPanel panel = new JPanel();

        LayoutManager overlay = new OverlayLayout(panel);
        panel.setLayout(overlay);

        JLabel label = new JLabel("Plant List Saved!");
        label.setFont(new Font("Georgia", Font.BOLD, 24));
        label.setAlignmentX(0.5f);
        label.setAlignmentY(0.5f);
        panel.add(label);
        gifLabel.setAlignmentX(0.5f);
        panel.add(gifLabel);

        gifFrame.add(panel);
        gifFrame.pack();
        gifFrame.setLocationRelativeTo(null);
        gifFrame.setVisible(true);
    }

}
