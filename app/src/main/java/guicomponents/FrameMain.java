package guicomponents;

import javax.swing.*;

import java.awt.*;
import java.io.IOException;

import guicomponents.CustomComponents.*;

public class FrameMain extends JFrame{
    private PanelSwitcher panelSwitcher;
    private JPanel panelPersonal;
    private JPanel panelRecommend;
    private JPanel panelSettings;
    private CustomLabel labelPlaceHolder;
    // Personal
    private JTextField nameField;
    private JTextField ageField;
    private JTextField longitudeField;
    private JTextField latitudeField;
    private double perLong;
    private double perLat;
    // Hotel
    private ListPanel hotelAmenities;
    private ListPanel hotelFacilities;
    private JCheckBox hotelViewCheck;
    private ListPanel hotelTypes;
    private JSpinner hotelSpinnerRating;
    // Restaurant
    private ListPanel restaurantAmenities;
    private ListPanel restaurantDiningTime;
    private JCheckBox restaurantViewCheck;
    private JCheckBox restaurantHolidayCheck;
    private ListPanel restaurantTypes;
    private ListPanel restaurantGoodFor;
    private JSpinner restaurantSpinnerRating;
    private JSpinner restaurantSpinnerCapacity;
    // Miscs
    private java.util.ArrayList<misc.Pair> combos = new java.util.ArrayList<>();
    private java.util.ArrayList<components.HotelEntry> hotelList = new java.util.ArrayList<>();
    private java.util.ArrayList<components.RestaurantEntry> restaurantList = new java.util.ArrayList<>();
    // Recommend panel
    private JTextArea infoArea;
    // Main panels
    private class PanelSwitcher extends JPanel {
        CustomButton buttonPersonal;
        CustomButton buttonRecommend;
        CustomButton buttonSettings;
        public PanelSwitcher(){
            super();
            setPreferredSize(new Dimension(320, 240));
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            setBackground(new Color(32, 33, 35));
            
            CustomLabel label1 = new CustomLabel("Tabs");
            label1.setAlignmentX(0.5f);
            
            buttonPersonal = new CustomButton("Personal Information");
            buttonPersonal.setAlignmentX(Component.CENTER_ALIGNMENT);

            buttonRecommend = new CustomButton("Recommendations");
            buttonRecommend.setAlignmentX(Component.CENTER_ALIGNMENT);

            buttonSettings = new CustomButton("Settings");
            buttonSettings.setAlignmentX(Component.CENTER_ALIGNMENT);

            setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            add(label1);
            add(buttonPersonal);
            add(Box.createRigidArea(new Dimension(1, 10)));
            add(buttonRecommend);
            add(Box.createRigidArea(new Dimension(1, 10)));
            add(buttonSettings);
            add(Box.createRigidArea(new Dimension(1, 10)));    
        }
    }

    public FrameMain(){
        super("test");
        try {
            misc.ExcelParser.hotelParser(hotelList);
            misc.ExcelParser.restaurantParser(restaurantList);
        } catch (IOException excIO){
            JOptionPane.showMessageDialog(this, "IOException caught. Check Log.");
            return;
        } catch (IllegalStateException excST){
            JOptionPane.showMessageDialog(this, "IllegalState caught. Check Log.");
            return;
        } catch (NumberFormatException excNUM){
            JOptionPane.showMessageDialog(this, "NumberFormat caught. Check Log.");
            return;
        }

        getContentPane().setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        panelSwitcher = new PanelSwitcher();

        JPanel panel2 = new JPanel();
        panel2.setPreferredSize(new Dimension(320, 320));
        panel2.setBackground(new Color(32, 33, 35));

        JPanel panel3 = new JPanel();
        panel3.setPreferredSize(new Dimension(320, 1));
        panel3.setBackground(new Color(32, 33, 35));

        JPanel tabs = new JPanel();
        tabs.setPreferredSize(new Dimension(1120, 560));
        CardLayout cardLayout = new CardLayout();
        tabs.setLayout(cardLayout);
        // Info Pane
        panelPersonal = new JPanel();
        panelPersonal.setBackground(new Color(32, 33, 35));
        panelPersonal.setLayout(null);
        panelPersonal.add(labelPlaceHolder = new CustomLabel("Enter required information to find a hotel or a restaurant"));
        labelPlaceHolder.setBounds(310, 10, 450, 30);

        panelPersonal.add(labelPlaceHolder = new CustomLabel("Name: ", eFontLevels.MEDIUM, 3));
        labelPlaceHolder.setBounds(20, 50, 100, 30);

        panelPersonal.add(nameField = new JTextField(1));
        nameField.setFont(new Font("Segoe UI", 0, 16));
        nameField.setForeground(Color.WHITE);
        nameField.setBackground(new Color(58, 60, 64));
        nameField.setBounds(30, 90, 300, 30);

        panelPersonal.add(labelPlaceHolder = new CustomLabel("Age: ", eFontLevels.MEDIUM, 3));
        labelPlaceHolder.setBounds(20, 120, 100, 30);

        panelPersonal.add(ageField = new JTextField(1));
        ageField.setFont(new Font("Segoe UI", 0, 16));
        ageField.setForeground(Color.WHITE);
        ageField.setBackground(new Color(58, 60, 64));
        ageField.setBounds(30, 150, 300, 30);

        panelPersonal.add(labelPlaceHolder = new CustomLabel("Longtitude: ", eFontLevels.MEDIUM, 3));
        labelPlaceHolder.setBounds(400, 50, 130, 30);

        panelPersonal.add(longitudeField = new JTextField(1));
        longitudeField.setFont(new Font("Segoe UI", 0, 16));
        longitudeField.setForeground(Color.WHITE);
        longitudeField.setBackground(new Color(58, 60, 64));
        longitudeField.setBounds(410, 90, 300, 30);

        panelPersonal.add(labelPlaceHolder = new CustomLabel("Latitude: ", eFontLevels.MEDIUM, 3));
        labelPlaceHolder.setBounds(400, 120, 100, 30);

        panelPersonal.add(latitudeField = new JTextField(1));
        latitudeField.setFont(new Font("Segoe UI", 0, 16));
        latitudeField.setForeground(Color.WHITE);
        latitudeField.setBackground(new Color(58, 60, 64));
        latitudeField.setBounds(410, 150, 300, 30);

        panelPersonal.add(labelPlaceHolder = new CustomLabel("-".repeat(30) + "  Ideal Hotel  " + "-".repeat(30)));
        labelPlaceHolder.setBounds(290, 190, 550, 30);

        panelPersonal.add(labelPlaceHolder = new CustomLabel("Types: ", eFontLevels.MEDIUM, 3));
        labelPlaceHolder.setBounds(150, 220, 100, 30);

        panelPersonal.add(hotelTypes = new ListPanel(new String[]{"Hotel",
                                                                    "Hostel",
                                                                    "Capsule Hotel"}, "HotelTypes"));
        hotelTypes.setBounds(160, 250, 200, 150);

        panelPersonal.add(labelPlaceHolder = new CustomLabel("Rating: ", eFontLevels.MEDIUM, 3));
        labelPlaceHolder.setBounds(20,220, 100, 30);

        panelPersonal.add(hotelSpinnerRating = new JSpinner(new SpinnerNumberModel(3, 1, 5, 1)));
        hotelSpinnerRating.setFont(new Font("Segoe UI", 0, 20));
        hotelSpinnerRating.setBounds(30, 250, 50, 30);

        panelPersonal.add(hotelViewCheck = new JCheckBox("Allow View"));
        hotelViewCheck.setFont(new Font("Segoe UI", 0, 16));
        hotelViewCheck.setForeground(Color.WHITE);
        hotelViewCheck.setBackground(new Color(32, 33, 35));
        hotelViewCheck.setBounds(30, 350, 120, 30);

        panelPersonal.add(labelPlaceHolder = new CustomLabel("Amenities: ", eFontLevels.MEDIUM, 3));
        labelPlaceHolder.setBounds(380, 220, 100, 30);

        panelPersonal.add(hotelAmenities = new ListPanel(new String[]{"Safe",
                                                                    "Suit Press",
                                                                    "Heating"}, "HotelAm"));
        hotelAmenities.setBounds(390, 250, 200, 150);

        panelPersonal.add(labelPlaceHolder = new CustomLabel("Facilities: ", eFontLevels.MEDIUM, 3));
        labelPlaceHolder.setBounds(610, 220, 100, 30);

        panelPersonal.add(hotelFacilities = new ListPanel(new String[]{"Air Conditioning",
                                                                    "Airport Shuttle",
                                                                    "Beach",
                                                                    "Bar",
                                                                    "Family Rooms",
                                                                    "EV charging station",
                                                                    "Non Smoking Room",
                                                                    "Swimming Pool"}, "HotelFac"));
        hotelFacilities.setBounds(620, 250, 250, 260);

        // -------------------------------------------- Restaurants -----------------------------------------------------------

        panelPersonal.add(labelPlaceHolder = new CustomLabel("-".repeat(28) + "  Ideal Restaurant  " + "-".repeat(28)));
        labelPlaceHolder.setBounds(290, 510, 550, 30);

        panelPersonal.add(labelPlaceHolder = new CustomLabel("Rating: ", eFontLevels.MEDIUM, 3));
        labelPlaceHolder.setBounds(20, 540, 100, 30);

        panelPersonal.add(restaurantSpinnerRating = new JSpinner(new SpinnerNumberModel(3, 1.d, 5.d, .2d)));
        restaurantSpinnerRating.setFont(new Font("Segoe UI", 0, 20));
        restaurantSpinnerRating.setBounds(30, 570, 50, 30);

        panelPersonal.add(restaurantViewCheck = new JCheckBox("Allow View"));
        restaurantViewCheck.setFont(new Font("Segoe UI", 0, 16));
        restaurantViewCheck.setForeground(Color.WHITE);
        restaurantViewCheck.setBackground(new Color(32, 33, 35));
        restaurantViewCheck.setBounds(30, 630, 120, 30);

        panelPersonal.add(restaurantHolidayCheck = new JCheckBox("Holiday"));
        restaurantHolidayCheck.setFont(new Font("Segoe UI", 0, 16));
        restaurantHolidayCheck.setForeground(Color.WHITE);
        restaurantHolidayCheck.setBackground(new Color(32, 33, 35));
        restaurantHolidayCheck.setBounds(30, 670, 120, 30);

        panelPersonal.add(restaurantSpinnerCapacity = new JSpinner(new SpinnerNumberModel(10, 1, 1000, 5)));
        restaurantSpinnerCapacity.setFont(new Font("Segoe UI", 0, 20));
        restaurantSpinnerCapacity.setBounds(30, 710, 50, 30);

        panelPersonal.add(labelPlaceHolder = new CustomLabel("Types: ", eFontLevels.MEDIUM, 3));
        labelPlaceHolder.setBounds(150, 540, 100, 30);

        panelPersonal.add(restaurantTypes = new ListPanel(new String[]{"Bakery",
                                                                    "Foodcourt",
                                                                    "Restaurant",
                                                                    "Coffe - Dessert",
                                                                    "Sight - Landmark",
                                                                    "Online Shopping",
                                                                    "Street Food",
                                                                    "Shop - Store",
                                                                    "Wedding Convention"}, "RestType"));
        restaurantTypes.setBounds(160, 570, 250, 290);

        panelPersonal.add(labelPlaceHolder = new CustomLabel("Good for: ", eFontLevels.MEDIUM, 3));
        labelPlaceHolder.setBounds(430, 540, 100, 30);

        panelPersonal.add(restaurantGoodFor = new ListPanel(new String[]{"Snack",
                                                                    "Dating",
                                                                    "Meeting",
                                                                    "Relaxing",
                                                                    "Sightseeing",
                                                                    "FastFood",
                                                                    "Party",
                                                                    "Wedding Ceremony",
                                                                    "Convention",
                                                                    "Takeaway"}, "RestGood"));
        restaurantGoodFor.setBounds(440, 570, 250, 330);

        panelPersonal.add(labelPlaceHolder = new CustomLabel("Dinning Times: ", eFontLevels.MEDIUM, 3));
        labelPlaceHolder.setBounds(710,  540, 150, 30);

        panelPersonal.add(restaurantDiningTime = new ListPanel(new String[]{"Morning",
                                                                        "Noon",
                                                                        "Afternoon",
                                                                        "Evening"}, "RestDin"));
        restaurantDiningTime.setBounds(720, 570, 250, 140);

        panelPersonal.add(labelPlaceHolder = new CustomLabel("Amenities: ", eFontLevels.MEDIUM, 3));
        labelPlaceHolder.setBounds(710, 720, 100, 30);

        panelPersonal.add(restaurantAmenities = new ListPanel(new String[]{"Deliery Service",
                                                                        "Takeaway Service",
                                                                        "Free Bike Park",
                                                                        "Outdoor Seats"}, "RestAm"));
        restaurantAmenities.setBounds(720, 750, 250, 140);

        // Recommendation pane
        panelRecommend = new JPanel();
        panelRecommend.setBackground(new Color(32, 33, 35));
        panelRecommend.setLayout(null);
        // panelRecommend.add(infoArea = new JTextArea(100, 300));
        JScrollPane scrollPane = new JScrollPane(infoArea = new JTextArea(100, 300));
        infoArea.setBackground(new Color(58, 60, 64));
        infoArea.setForeground(Color.WHITE);
        infoArea.setFont(new Font("Segoe UI", 0, 16));
        infoArea.setLineWrap(true);
        infoArea.setWrapStyleWord(true);
        panelRecommend.add(scrollPane);
        scrollPane.setBounds(10, 320, 1100, 500);

        // Settings pane
        panelSettings = new JPanel();
        panelSettings.setBackground(new Color(32, 33, 35));
        panelSettings.setLayout(null);

        tabs.add(panelPersonal, "personal");
        tabs.add(panelRecommend, "recommend");
        tabs.add(panelSettings, "settings");

        panelSwitcher.buttonPersonal.addActionListener(e -> {
                cardLayout.show(tabs, "personal");
            }
        );
        panelSwitcher.buttonRecommend.addActionListener(e -> {
                if (nameField.getText().isBlank()
                    || ageField.getText().isBlank()
                    || longitudeField.getText().isBlank()
                    || latitudeField.getText().isBlank()) {
                        JOptionPane.showMessageDialog(this, "You did not enter all the fields in Personal.");
                        return;
                }
                try {
                    perLong = Double.parseDouble(longitudeField.getText());
                    perLat = Double.parseDouble(latitudeField.getText());
                } catch (NumberFormatException exc){
                    JOptionPane.showMessageDialog(this, "Invalid longtitude / latitude.");
                    return;
                }
                getRecommendation();
                cardLayout.show(tabs, "recommend");
            }
        );
        panelSwitcher.buttonSettings.addActionListener(e -> {
                cardLayout.show(tabs, "settings");
            }
        );


        add(panelSwitcher, gbc);
        gbc.gridy = 1;
        add(panel2, gbc);
        gbc.gridy = 2;
        gbc.weighty = 1;
        add(panel3, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 3;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        add(tabs, gbc);

        setBounds(100, 100, 1500, 960);
        getContentPane().setBackground(new Color(22, 23, 25));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    private void getRecommendation(){
        components.HotelEntry idealHotel = new components.HotelEntry();
        idealHotel.setFacilities(hotelFacilities.getChoiceBinary());
        idealHotel.setTypeAmenities(hotelTypes.getChoiceBinary() | hotelAmenities.getChoiceBinary());
        idealHotel.setRating((Integer) hotelSpinnerRating.getValue());
        idealHotel.setView(hotelViewCheck.isSelected());

        components.RestaurantEntry idealRestaurant = new components.RestaurantEntry();
        idealRestaurant.setCapacity((Integer) restaurantSpinnerCapacity.getValue());
        idealRestaurant.setHoliday(restaurantHolidayCheck.isSelected());
        idealRestaurant.setRating((Double) restaurantSpinnerRating.getValue());
        idealRestaurant.setDiningGood(restaurantDiningTime.getChoiceBinary() | restaurantGoodFor.getChoiceBinary());
        idealRestaurant.setTypeAmenities(restaurantAmenities.getChoiceBinary() | restaurantTypes.getChoiceBinary());

        for (final components.HotelEntry hotel : hotelList) for (final components.RestaurantEntry restaurant : restaurantList){
            hotel.getScore(idealHotel);
            restaurant.getScore(idealRestaurant);
            combos.add(new misc.Pair(hotel, restaurant));
            combos.get(combos.size() - 1).setScore(1, 1, new misc.DoublePair(perLong, perLat));
        }
        combos.sort((a, b) -> - Long.compare(a.getScore(), b.getScore()));
        JPanel choicePanels = new JPanel();
        choicePanels.setLayout(new BoxLayout(choicePanels, BoxLayout.Y_AXIS));
        int i;
        for (i = 0; i < combos.size(); i++){
            final int choiceIndex = i;
            CustomButton button = new CustomButton("Choice #" + (choiceIndex + 1));
            choicePanels.add(button);
            // add(Box.createRigidArea(new Dimension(1, 10)));
            button.addActionListener(e -> loadInfo(choiceIndex));
        }
        // panelRecommend.add(choicePanels);
        choicePanels.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JScrollPane scrollPane = new JScrollPane(choicePanels);
        panelRecommend.add(scrollPane);
        scrollPane.setBounds(10, 10, 300, 300);
    }

    private void loadInfo(int index){
        String text = "-".repeat(50) + " Hotel " + "-".repeat(50)
                    + combos.get(index).getHotel()
                    + "-".repeat(50) + " Restaurant " + "-".repeat(50)
                    + combos.get(index).getRest();
        infoArea.setText(text);
        misc.Utils.logAppend("Hotel score: " + combos.get(index).getHotel().getScore()
                            + "\n\tRestaurant score: " + combos.get(index).getRest().getScore()
                            + "\n\tTotal score: " + combos.get(index).getScore(), null);
    }
}
