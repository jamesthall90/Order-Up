package MainScreens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CalorieCalculator extends JFrame {

    private JPanel mainPanel;
    private JTextField ageTextField;
    private JLabel ageLabel;
    private JRadioButton maleRadioButton;
    private JLabel maleLabel;
    private JRadioButton femaleRadioButton;
    private JLabel femaleLabel;
    private ButtonGroup radioGroup;
    private JTextField heightTextField;
    private JLabel heightLabel;
    private JTextField weightTextField;
    private JLabel weightLabel;
    private JComboBox activityComboBox;
    private JLabel activityLabel;
    private JButton calculateButton;
    private JLabel caloriesLabel;
    private JTextField gainTwoLbsTextField;
    private JLabel gainTwoLbsLabel;
    private JTextField gainOneLbsTextField;
    private JLabel gainOneLbsLabel;
    private JTextField maintainWeightTextField;
    private JLabel maintainWeightLabel;
    private JTextField loseOneLbsTextField;
    private JLabel loseOneLbsLabel;
    private JTextField loseTwoLbsTextField;
    private JLabel loseTwoLbsLabel;
    private JLabel screenTitle;
    private static final String[] activityLevelDescriptions = {
            "Little or no exercise", "Light exercise/sports 1-3 days/week",
            "Moderate exercise/sports 3-5 days/week",
            "Hard exercise/sports 6-7 days a week",
            "Very hard exercise/sports & physical job"};
    private double bmr;


    public CalorieCalculator() {

        super("Calorie Calculator");

        mainPanel = new JPanel(null);
        ageTextField = new JTextField(5);
        ageLabel = new JLabel("Age:");
        maleRadioButton = new JRadioButton();
        maleLabel = new JLabel("Male:");
        femaleRadioButton = new JRadioButton();
        femaleLabel = new JLabel("Female:");
        radioGroup = new ButtonGroup();
        heightTextField = new JTextField(5);
        heightLabel = new JLabel("Height(in):");
        weightTextField = new JTextField(5);
        weightLabel = new JLabel("Weight(lbs):");
        activityComboBox = new JComboBox(activityLevelDescriptions);
        activityLabel = new JLabel("Pick your activity level:");
        calculateButton = new JButton("Calculate");
        caloriesLabel = new JLabel("<HTML><I>Calories<HTML><I>");
        gainTwoLbsTextField = new JTextField(10);
        gainTwoLbsLabel = new JLabel("Gain 2 lbs/week:");
        gainOneLbsTextField = new JTextField(10);
        gainOneLbsLabel = new JLabel("Gain 1 lb/week:");
        maintainWeightTextField = new JTextField(10);
        maintainWeightLabel = new JLabel("Maintain weight:");
        loseOneLbsTextField = new JTextField(10);
        loseOneLbsLabel = new JLabel("Lose 1 lb/week:");
        loseTwoLbsTextField = new JTextField(10);
        loseTwoLbsLabel = new JLabel("Lose 2 lbs/week:");
        screenTitle = new JLabel("<HTML><U>Calorie Calculator Using The " +
                "Original Harris Benedict Equation<HTML><U>");

        mainPanel.setBackground(Color.WHITE);

        radioGroup.add(maleRadioButton);
        radioGroup.add(femaleRadioButton);

        ageTextField.setBounds(40, 60, 50, 20);
        ageLabel.setBounds(5, 54, 30, 30);
        maleRadioButton.setBounds(40, 110, 20, 20);
        maleLabel.setBounds(5, 110, 40, 20);
        femaleRadioButton.setBounds(120, 110, 20, 20);
        femaleLabel.setBounds(70, 110, 50, 20);
        heightTextField.setBounds(80, 160, 50, 20);
        heightLabel.setBounds(5, 160, 80, 20);
        weightTextField.setBounds(80, 210, 50, 20);
        weightLabel.setBounds(5, 210, 80, 20);
        activityComboBox.setBounds(5, 290, 250, 20);
        activityLabel.setBounds(5, 260, 150, 20);
        calculateButton.setBounds(140, 340, 100, 20);
        caloriesLabel.setBounds(340, 40, 50, 20);
        gainTwoLbsTextField.setBounds(340, 60, 65, 20);
        gainTwoLbsLabel.setBounds(220, 60, 100, 20);
        gainOneLbsTextField.setBounds(340, 110, 65, 20);
        gainOneLbsLabel.setBounds(225, 110, 100, 20);
        maintainWeightTextField.setBounds(340, 160, 65, 20);
        maintainWeightLabel.setBounds(220, 160, 100, 20);
        loseOneLbsTextField.setBounds(340, 210, 65, 20);
        loseOneLbsLabel.setBounds(225, 210, 100, 20);
        loseTwoLbsTextField.setBounds(340, 260, 65, 20);
        loseTwoLbsLabel.setBounds(220, 260, 100, 20);
        screenTitle.setBounds(20, 5, 360, 40);


        gainTwoLbsTextField.setEditable(false);
        gainOneLbsTextField.setEditable(false);
        maintainWeightTextField.setEditable(false);
        loseOneLbsTextField.setEditable(false);
        loseTwoLbsTextField.setEditable(false);

        MouseHandler mouseHandler = new MouseHandler();

        calculateButton.addMouseListener(mouseHandler);

        mainPanel.add(ageTextField);
        mainPanel.add(ageLabel);
        mainPanel.add(maleRadioButton);
        mainPanel.add(maleLabel);
        mainPanel.add(femaleRadioButton);
        mainPanel.add(femaleLabel);
        mainPanel.add(heightTextField);
        mainPanel.add(heightLabel);
        mainPanel.add(weightTextField);
        mainPanel.add(weightLabel);
        mainPanel.add(activityComboBox);
        mainPanel.add(activityLabel);
        mainPanel.add(calculateButton);
        mainPanel.add(caloriesLabel);
        mainPanel.add(gainTwoLbsTextField);
        mainPanel.add(gainTwoLbsLabel);
        mainPanel.add(gainOneLbsTextField);
        mainPanel.add(gainOneLbsLabel);
        mainPanel.add(maintainWeightTextField);
        mainPanel.add(maintainWeightLabel);
        mainPanel.add(loseOneLbsTextField);
        mainPanel.add(loseOneLbsLabel);
        mainPanel.add(loseTwoLbsTextField);
        mainPanel.add(loseTwoLbsLabel);
        mainPanel.add(screenTitle);

        add(mainPanel);
    }

    private class MouseHandler implements MouseListener {


        @Override
        public void mouseClicked(MouseEvent e) {
            double maintain;
            double addOne;
            double addTwo;
            double minusOne;
            double minusTwo;
            String Bmr;

//            System.out.println(Double.parseDouble(ageTextField.getText()));

            try {
                if (Double.parseDouble(ageTextField.getText()) < 1) {

                    JOptionPane.showMessageDialog(null, "Enter only numbers greater " +
                            "than zero", "Your input is wrong", JOptionPane
                            .ERROR_MESSAGE);
                } else if (Double.parseDouble(heightTextField.getText()) < 1) {

                    JOptionPane.showMessageDialog(null, "Enter only numbers greater " +
                            "than zero", "Your input is wrong", JOptionPane
                            .ERROR_MESSAGE);
                } else if (Double.parseDouble(weightTextField.getText()) < 1) {

                    JOptionPane.showMessageDialog(null, "Enter only numbers greater " +
                            "than zero", "Your input is wrong", JOptionPane
                            .ERROR_MESSAGE);
                } else if(!maleRadioButton.isSelected() &&
                        !femaleRadioButton.isSelected()){

                    JOptionPane.showMessageDialog(null, "Select a gender " +
                            "please", "Your input" +
                            " is wrong", JOptionPane
                            .ERROR_MESSAGE);
                } else {
                    maintain = BMR() * BmrMultiplier();
                    Bmr = Double.toString((int) maintain);
                    maintainWeightTextField.setText(Bmr);

                    addOne = maintain + 500;
                    Bmr = Double.toString((int) addOne);
                    gainOneLbsTextField.setText(Bmr);
                    addTwo = maintain + 1000;
                    Bmr = Double.toString((int) addTwo);
                    gainTwoLbsTextField.setText(Bmr);
                    minusOne = maintain - 500;
                    Bmr = Double.toString((int) minusOne);
                    loseOneLbsTextField.setText(Bmr);
                    minusTwo = maintain - 1000;
                    Bmr = Double.toString((int) minusTwo);
                    loseTwoLbsTextField.setText(Bmr);
                }
            } catch (NumberFormatException error){

                JOptionPane.showMessageDialog(null, "Enter only numbers that " +
                        "are also greater " +
                        "than zero", "Your input is wrong", JOptionPane
                        .ERROR_MESSAGE);
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    public double age() {

        return Double.parseDouble(ageTextField.getText());
    }

    //getHeight clashes with a method in awt
    public double height() {

        return Double.parseDouble(heightTextField.getText());
    }

    public double weight() {

        return Double.parseDouble(weightTextField.getText());
    }

    public double BMR() {
        if(gender() == true) {

            bmr = 66 + (6.2 * weight()) + (12.7 * height()) - (6.76
                    * age()); // for men need to do for women
        } else if (gender() == false){

            bmr = 655.1 + ( 4.35 * weight() ) + ( 4.7 * height())
                    - (4.7 * age());
        }
        return bmr;
    }

    public boolean gender(){

        return !femaleRadioButton.isSelected();
    }

    public double BmrMultiplier() {

        if (activityComboBox.getSelectedItem().equals
                ("Little or no exercise")) {

            return 1.2;
        } else if (activityComboBox.getSelectedItem().equals
                ("Light exercise/sports 1-3 days/week")) {

            return 1.375;
        } else if (activityComboBox.getSelectedItem().equals
                ("Moderate exercise/sports 3-5 days/week")) {

            return 1.55;
        } else if (activityComboBox.getSelectedItem().equals
                ("Hard exercise/sports 6-7 days a week")) {

            return 1.725;
        } else if (activityComboBox.getSelectedItem().equals
                ("Very hard exercise/sports & physical job")) {

            return 1.9;
        }
        return 1;
    }

//    //test
//    public static void main(String[] args) {
//        CalorieCalculator starter = new CalorieCalculator();
//        starter.setVisible(true);
//        starter.setSize(400, 400);
//        starter.setResizable(false);
//        starter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//    }
}
