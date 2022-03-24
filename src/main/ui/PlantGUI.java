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
import java.awt.event.KeyEvent;
import java.io.IOException;


public class PlantGUI extends JPanel implements ListSelectionListener {

    private JList list;
    private DefaultListModel<String> listModel;
    private JFrame frame;

    private Plants plantList;

    private static final String JSON_FILE = "./data/plantList.json";
    private PlantJsonReader jsonReader;
    private PlantJsonWriter jsonWriter;

    private JSplitPane splitPane;
    private JTextArea area;
    private JTextField name;
    private JTextField type;
    private JTextField status;
    private JTextField waterAmount;
    private JTextField watered;

    private JButton addButton;
    private JButton removeButton;

    private AddPlantListener addPlantListener;
    private RemovePlantListener removePlantListener;
    private SaveListener saveListener;

    private JMenuBar menuBar;
    private JMenu menu;


    // EFFECTS: creates all the components of the GUI
    public PlantGUI(JFrame frame) {
        super(new BorderLayout());

        jsonReader = new PlantJsonReader(JSON_FILE);
        jsonWriter = new PlantJsonWriter(JSON_FILE);

        listModel = new DefaultListModel<>();
        plantList = new Plants();
        this.frame = frame;

        menuBar = new JMenuBar();
        menu = new JMenu("Menu");
        JMenuItem menuItem = new JMenuItem("Save");
        saveListener = new SaveListener();
        menuItem.setActionCommand("Save");
        menuItem.addActionListener(saveListener);
        menu.add(menuItem);
        JMenuItem menuItem1 = new JMenuItem("Load");
        menu.add(menuItem1);
        menuBar.add(menu);


        frame.setJMenuBar(menuBar);

        createIcon();
        createSplitPlane();

        initializeRemoveButton();
        initializeAddButton();
        initializeJTextFields(addPlantListener);
        createPanel(splitPane, addButton);

    }

    private void createSplitPlane() {
        JScrollPane scrollPane = createList();
        area = new JTextArea(10, 30);
        JScrollPane areaScrollPane = new JScrollPane(area);
        area.setEditable(true);
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scrollPane, areaScrollPane);
    }

    public void createIcon() {
        ImageIcon img = new ImageIcon("./data/succulent.jpg");
        frame.setIconImage(img.getImage());
    }

    private void createPanel(JSplitPane splitPane, JButton button) {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));

        JPanel sideButtonPanel = createSideButtonPanel();
        buttonPanel.add(sideButtonPanel);
        buttonPanel.add(Box.createHorizontalStrut(10));
        buttonPanel.add(Box.createHorizontalStrut(10));

        JPanel panel = constructLabels();

        buttonPanel.add(panel);
        buttonPanel.add(Box.createHorizontalStrut(10));
        buttonPanel.add(addButton);
        buttonPanel.add(Box.createHorizontalStrut(10));
        buttonPanel.add(removeButton);
        buttonPanel.add(Box.createHorizontalStrut(10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10,10));

        add(splitPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.PAGE_START);
    }

    private JPanel createSideButtonPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.add(removeButton);
        return panel;
    }

    private JPanel constructLabels() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel nameLabel = new JLabel("Plant Name");
        JLabel typeLabel = new JLabel("Plant Type (fern, cactus, etc");
        JLabel statusLabel = new JLabel("Status (healthy, dead, ok)");
        JLabel waterAmountLabel = new JLabel("Water Amount (# of days per week)");
        JLabel wateredLabel = new JLabel("Watered? (true or false)");

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



    private void initializeJTextFields(AddPlantListener plantListener) {
        initializeNameJTextField();
        initializeTypeJTextField();
        initializeStatusJTextField();
        initializeWaterAmountJTextField();
        initializeWateredJTextField();

    }

    private void initializeNameJTextField() {
        name = new JTextField(20);
        name.addActionListener(addPlantListener);
        name.getDocument().addDocumentListener(addPlantListener);
    }

    private void initializeTypeJTextField() {
        type = new JTextField(20);
        type.addActionListener(addPlantListener);
        type.getDocument().addDocumentListener(addPlantListener);
    }

    private void initializeStatusJTextField() {
        status = new JTextField(20);
        status.addActionListener(addPlantListener);
        status.getDocument().addDocumentListener(addPlantListener);
    }

    private void initializeWaterAmountJTextField() {
        waterAmount = new JTextField(20);
        waterAmount.addActionListener(addPlantListener);
        waterAmount.getDocument().addDocumentListener(addPlantListener);
    }

    private void initializeWateredJTextField() {
        watered = new JTextField(20);
        watered.addActionListener(addPlantListener);
        watered.getDocument().addDocumentListener(addPlantListener);
    }

    private void initializeRemoveButton() {
        removeButton = new JButton("Remove Plant");
        removePlantListener = new RemovePlantListener();
        removeButton.setActionCommand("Remove Plant");
        removeButton.addActionListener(removePlantListener);
        removeButton.setEnabled(false);
    }


    private void initializeAddButton() {
        addButton = new JButton("Add Plant");
        addPlantListener = new AddPlantListener(addButton);
        addButton.setActionCommand("Add Plant");
        addButton.addActionListener(addPlantListener);
        addButton.setEnabled(false);
    }

    private JScrollPane createList() {
        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.addListSelectionListener(this);
        list.setVisibleRowCount(10);
        return new JScrollPane(list);
    }




    class AddPlantListener implements ActionListener, DocumentListener {

        private boolean alreadyEnabled = false;
        private JButton button;

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
        public void insertUpdate(DocumentEvent e) {
            enableButton();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            handleEmptyTextField(e);
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            if (!handleEmptyTextField(e)) {
                enableButton();
            }
        }

        private void enableButton() {
            if (!alreadyEnabled) {
                button.setEnabled(true);
            }
        }

        private boolean handleEmptyTextField(DocumentEvent e) {
            if (e.getDocument().getLength() <= 0) {
                button.setEnabled(false);
                alreadyEnabled = false;
                return true;
            }
            return false;
        }
    }

    class RemovePlantListener implements  ActionListener {
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
            }
        }
    }

    class SaveListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                jsonWriter.open();
                jsonWriter.write(plantList);
                jsonWriter.close();
                JOptionPane.showMessageDialog(frame, "Plant List Saved");
            } catch (IOException i) {
                System.out.println("Unable to save " + JSON_FILE + " Json file");
            }
        }
    }

    class LoadListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                plantList = jsonReader.read();
            } catch (IOException i) {
                System.out.println("Unable to load " + JSON_FILE);
            }
        }
    }


    @Override
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

    private void updatePlantInfo(String plantName) {
        for (Plant p: plantList.getPlants()) {
            if (p.getPlantName().equals(plantName)) {
                JTextArea plantInfo = new JTextArea("\nPlant Name: " + p.getPlantName()
                        + "\nPlant Type: " + p.getPlantType()
                        + "\nPlant Status: " + p.getPlantStatus()
                        + "\nWater Amount: " + p.getPlantWater()
                        + "\nWatered Today? " + p.getWatered());
                splitPane.setRightComponent(plantInfo);

            }
        }
    }



}
