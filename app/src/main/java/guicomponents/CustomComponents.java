package guicomponents;

import javax.swing.*;
import java.awt.*;

public class CustomComponents {
    // Components
    public static class CustomButton extends JButton{
        public CustomButton(String _label){
            super(_label);
            // setPreferredSize(new java.awt.Dimension(250, 30));
            // setMinimumSize(new java.awt.Dimension(250, 30));
            setMaximumSize(new Dimension(Integer.MAX_VALUE, getMinimumSize().height));
            setBackground(new Color(65, 105, 151));
            setForeground(new Color(255, 255, 255));
            setFont(new java.awt.Font("Segoe UI", 0, 18));
        }
    }
    public enum eFontLevels{
        SMALL(-2),
        MEDIUM(0),
        LARGE(2);

        private final int value;
        eFontLevels(int _value){
            value = _value;
        }
        public int getValue(){
            return value;
        }
    }
    public static class CustomLabel extends JLabel {
        public CustomLabel(String _label, eFontLevels _fontLevel){
            super(_label);
            setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            setForeground(Color.WHITE);
            setFont(new java.awt.Font("Segoe UI", 0, 16 + _fontLevel.getValue()));        
        }

        public CustomLabel(String _label){
            super(_label);
            setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            setForeground(Color.WHITE);
            setFont(new java.awt.Font("Segoe UI", 0, 16));        
        }
        public CustomLabel(String _label, eFontLevels _fontLevel, int _opacity){
            super(_label);
            setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            setForeground(new Color(255 - (25 * _opacity), 255 - (25 * _opacity), 255 - (25 * _opacity)));
            setFont(new java.awt.Font("Segoe UI", 0, 16));            
        }
    }
    public static class ListPanel extends JPanel {
        private java.util.ArrayList<JCheckBox> checkBoxes = new java.util.ArrayList<>();
        private JCheckBox checkboxPlaceHolder;
        private String title;
        public ListPanel(String[] _items, String _title){
            super();
            title = _title;

            setPreferredSize(new Dimension(400, 600));
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            setBorder(BorderFactory.createEmptyBorder(10, 30, 20, 10));
            setBackground(new Color(58, 60, 64));
            // add(Box.createRigidArea(new Dimension(1, 10)));

            for (final String item : _items){
                checkBoxes.add(checkboxPlaceHolder = new JCheckBox(String.format("%s", item)));
                checkboxPlaceHolder.setFont(new Font("Segoe UI", Font.BOLD, 16));
                checkboxPlaceHolder.setHorizontalAlignment(SwingConstants.RIGHT);
                checkboxPlaceHolder.setForeground(Color.WHITE);
                checkboxPlaceHolder.setBackground(new Color(58, 60, 64));
                
                checkboxPlaceHolder.setAlignmentX(Component.LEFT_ALIGNMENT);

                add(checkboxPlaceHolder);
                // add(Box.createRigidArea(new Dimension(1, 10)));
            }
        } // super


        public long getChoiceBinary(){
            long result = 0;
            long currentBit = 1;
            for (final JCheckBox checkbox : checkBoxes){
                if (checkbox.isSelected()) result |= currentBit;
                currentBit *= 2;
            } // for
            misc.Utils.logAppend("List panel: " + title + "\nBinary: " + Long.toBinaryString(result), null);
            return result;
        }
    }
}
