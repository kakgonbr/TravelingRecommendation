package guicomponents;

import javax.swing.*;
import java.awt.*;
import guicomponents.CustomComponents.*;

public class FrameMain extends JFrame{
    PanelSwitcher panelSwitcher;
    JPanel panelPersonal;
    JPanel panelRecommend;
    JPanel panelSettings;
    CustomLabel labelPlaceHolder;
    // Personal
    JTextField nameField;
    JTextField ageField;
    JTextField longitudeField;
    JTextField latitudeField;
    // Hotel
    JTextField hotelRatingField;
    ListPanel hotelAmenities;
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
        tabs.setPreferredSize(new Dimension(1120, 500));
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
        nameField.setForeground(Color.WHITE);
        nameField.setBackground(new Color(68, 70, 74));
        nameField.setBounds(30, 90, 300, 30);

        panelPersonal.add(labelPlaceHolder = new CustomLabel("Age: ", eFontLevels.MEDIUM, 3));
        labelPlaceHolder.setBounds(20, 120, 100, 30);

        panelPersonal.add(ageField = new JTextField(1));
        ageField.setForeground(Color.WHITE);
        ageField.setBackground(new Color(68, 70, 74));
        ageField.setBounds(30, 150, 300, 30);

        panelPersonal.add(labelPlaceHolder = new CustomLabel("Longtitude: ", eFontLevels.MEDIUM, 3));
        labelPlaceHolder.setBounds(400, 50, 130, 30);

        panelPersonal.add(longitudeField = new JTextField(1));
        longitudeField.setForeground(Color.WHITE);
        longitudeField.setBackground(new Color(68, 70, 74));
        longitudeField.setBounds(410, 90, 300, 30);

        panelPersonal.add(labelPlaceHolder = new CustomLabel("Latitude: ", eFontLevels.MEDIUM, 3));
        labelPlaceHolder.setBounds(400, 120, 100, 30);

        panelPersonal.add(latitudeField = new JTextField(1));
        latitudeField.setForeground(Color.WHITE);
        latitudeField.setBackground(new Color(68, 70, 74));
        latitudeField.setBounds(410, 150, 300, 30);

        panelPersonal.add(labelPlaceHolder = new CustomLabel("-".repeat(30) + "  Ideal Hotel  " + "-".repeat(30)));
        labelPlaceHolder.setBounds(290, 190, 550, 30);

        panelPersonal.add(labelPlaceHolder = new CustomLabel("Types: ", eFontLevels.MEDIUM, 3));
        labelPlaceHolder.setBounds(20, 220, 100, 30);

        panelPersonal.add(hotelAmenities = new ListPanel(new String[]{"Safe",
                                                                    "Suit Press",
                                                                    "Heating"}, "Types"));
        hotelAmenities.setBounds(30, 250, 200, 150);

        // Recommendation pane
        panelRecommend = new JPanel();
        panelRecommend.setBackground(new Color(32, 33, 35));

        // Settings pane
        panelSettings = new JPanel();
        panelSettings.setBackground(new Color(32, 33, 35));

        tabs.add(panelPersonal, "personal");
        tabs.add(panelRecommend, "recommend");
        tabs.add(panelSettings, "settings");

        panelSwitcher.buttonPersonal.addActionListener(e -> {
                cardLayout.show(tabs, "personal");
            }
        );
        panelSwitcher.buttonRecommend.addActionListener(e -> {
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

        setBounds(100, 100, 1500, 900);
        getContentPane().setBackground(new Color(22, 23, 25));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }
}
