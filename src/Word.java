import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.plaf.*;

public class Word extends JFrame implements ActionListener {

    int SETTING_FONT_SIZE_DEFAULT = 20;
    String SETTING_FONT_FAMILY_DEFAULT = "Arial";
    int SETTING_FONT_STYLE_DEFAULT = Font.PLAIN;

    JTextArea textArea;
    JScrollPane scrollPane;
    JSpinner jSpinner;
    JLabel jLabel;
    JButton jButtonColor;
    JComboBox<String> jComboBox;
    JLabel jLabelCentered;
    JLabel jLabelBold;
    JLabel jLabelItalic;
    JCheckBox jCheckBoxCentered;
    JCheckBox jCheckBoxBold;
    JCheckBox jCheckBoxItalic;

    Word() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Bro text Editor");
        this.setSize(500, 500);
        this.setLayout(new FlowLayout());
        // this.setLocationRelativeTo(null);

        textArea = new JTextArea("Let's write some text in this field textarea!!!");
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFont(new Font(SETTING_FONT_FAMILY_DEFAULT, SETTING_FONT_STYLE_DEFAULT, SETTING_FONT_SIZE_DEFAULT));

        scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new DimensionUIResource(450, 450));
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jLabel = new JLabel("Font: ");

        jSpinner = new JSpinner();
        jSpinner.setPreferredSize(new DimensionUIResource(50, 25));
        jSpinner.setValue(SETTING_FONT_SIZE_DEFAULT);
        jSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                System.out.println("what happen");
                textArea.setFont(
                        new FontUIResource(textArea.getFont().getFamily(), SETTING_FONT_STYLE_DEFAULT,
                                (int) jSpinner.getValue()));
            }
        });

        jButtonColor = new JButton("Color");
        jButtonColor.addActionListener(this);

        String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        jComboBox = new JComboBox<>(fonts);
        jComboBox.addActionListener(this);
        jComboBox.setSelectedItem(SETTING_FONT_FAMILY_DEFAULT);

        jLabelCentered = new JLabel("Centered: ");
        jCheckBoxCentered = new JCheckBox();
        jCheckBoxCentered.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (jCheckBoxCentered.isSelected()) {
                    System.out.println(textArea.getFont().getFontName());
                    textArea.setFont(new Font((String) jComboBox.getSelectedItem(), Font.CENTER_BASELINE,
                            textArea.getFont().getSize()));
                } else {
                    textArea.setFont(
                            new Font((String) jComboBox.getSelectedItem(), Font.CENTER_BASELINE | Font.ITALIC,
                                    textArea.getFont().getSize()));
                }
                ;
            }
        });
        jLabelBold = new JLabel("Bold: ");
        jCheckBoxBold = new JCheckBox();
        jCheckBoxBold.addActionListener(this);
        jCheckBoxBold.setSelected(false);

        jLabelItalic = new JLabel("Italic: ");
        jCheckBoxItalic = new JCheckBox();

        this.add(jLabel);
        this.add(jSpinner);
        this.add(jButtonColor);
        this.add(jComboBox);
        this.add(jLabelCentered);
        this.add(jCheckBoxCentered);
        this.add(jLabelBold);
        this.add(jCheckBoxBold);
        this.add(jLabelItalic);
        this.add(jCheckBoxItalic);
        this.add(scrollPane);
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jButtonColor) {
            Color color = JColorChooser.showDialog(null, "Choose a color", Color.BLACK);
            textArea.setForeground(color);
        }

        if (e.getSource() == jComboBox) {
            textArea.setFont(new Font((String) jComboBox.getSelectedItem(), SETTING_FONT_STYLE_DEFAULT,
                    textArea.getFont().getSize()));
        }

        if (e.getSource() == jCheckBoxBold) {
            if (jCheckBoxBold.isSelected()) {
                textArea.setFont(new Font((String) jComboBox.getSelectedItem(), Font.BOLD,
                        textArea.getFont().getSize()));
            } else {
                textArea.setFont(new Font((String) jComboBox.getSelectedItem(), SETTING_FONT_STYLE_DEFAULT,
                        textArea.getFont().getSize()));
            }
            ;

        }
    }

    public void changeAction(ComponentUI c) {

    }

    public boolean checkFont(int font) {

        if (textArea.getFont().getFontName().indexOf(font) != -1) {
            return true;
        }
        return false;
    }

}
