package MainScreens;

import Utility.Meals;
import Utility.ToolClass;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.*;
import java.util.*;

public class LogInScreen extends JFrame {
    JButton btnLogIn;
    JPanel logInPanel;
    JTextField txtFieldUser;
    JTextField txtFieldPassword;
    JButton btnCancel;
    JLabel logoHolderLabel;
    JLabel schoolChoiceLabel;
    JLabel usernameLabel;
    JLabel passwordLabel;
    JComboBox schoolChoiceBox;
    String logoURL = "http://i.imgur.com/hPN6Qz7.png";
    ImageIcon orderUpLogo;
    static int universityID;
    static String firstName = "";
    static String lastName = "";
    static int userPoint;

    //for schoolChoiceBox
    private static final String[] schoolNames = {" ", "Florida Gulf Coast " +
            "University"};

    public LogInScreen() {
        super("Login Screen");

        schoolChoiceBox = new JComboBox(schoolNames); //puts schools names into comboBox rows

        btnLogIn = new JButton("Login");
        btnCancel = new JButton("Cancel");
        logInPanel = new JPanel();
//        txtFieldUIN = new JTextField();
        txtFieldUser = new JTextField(15);
        txtFieldPassword = new JPasswordField(15);

        //Creates a ToolClass object and then calls
        //createImageIcon to add logo ImageIcon to orderUpLogo

        orderUpLogo = ToolClass.createImageIcon(logoURL, "Order-up Logo");

        logoHolderLabel = new JLabel(orderUpLogo); //adds created logo icon to JLabel


        schoolChoiceLabel = new JLabel("University ");
        schoolChoiceLabel.setForeground(Color.white);

        usernameLabel = new JLabel("Email ");
        usernameLabel.setForeground(Color.white);

        passwordLabel = new JLabel("Password ");
        passwordLabel.setForeground(Color.white);

        //Our setLocation be different on other screen resolutions

        setSize(500, 400); //sets the size of the frame
        setLocationRelativeTo(null); //sets the location of the frame on the screen
        logInPanel.setLayout(null);
        schoolChoiceBox.setMaximumRowCount(schoolNames.length); //sets max
        // rows to number of schools in schoolNames array

        //ctrl + hover over a function for info (IntelliJ)
        //sets all of the specified location of each of the JObjects
        //if setSize is changed, these will be affected

        logoHolderLabel.setBounds(155, 10, 200, 174);
        txtFieldUser.setBounds(175, 230, 150, 20);
        txtFieldPassword.setBounds(175, 265, 150, 20);
        btnLogIn.setBounds(210, 300, 80, 20);
        btnCancel.setBounds(210, 335, 80, 20);
        schoolChoiceBox.setBounds(175, 200, 150, 20);
        schoolChoiceLabel.setBounds(112, 193, 80, 20);
        usernameLabel.setBounds(135, 228, 80, 20);
        passwordLabel.setBounds(110, 263, 80, 20);


        logInPanel.add(logoHolderLabel);
        logInPanel.add(btnLogIn);
        logInPanel.add(txtFieldUser);
        logInPanel.add(txtFieldPassword);
        logInPanel.add(btnCancel);
        logInPanel.add(schoolChoiceBox);
        logInPanel.add(schoolChoiceLabel);
        logInPanel.add(usernameLabel);
        logInPanel.add(passwordLabel);
        logInPanel.setBackground(ToolClass.fgcuGreen);

        getContentPane().add(logInPanel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        btnLogIn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {

                    String uname, upaswd;
                    String usertxt = " ";
                    String passtxt = " ";

                    uname = txtFieldUser.getText();
                    upaswd = txtFieldPassword.getText();
                    String host = "jdbc:sqlite:/Users/iceman371/git/Order-Up/data/studentinfo.db";

                    Connection studentInfoCon = DriverManager.getConnection(host);

                    Statement state = studentInfoCon.createStatement();

                    String uid = String.format("SELECT uin FROM student WHERE student_email= '%s'", uname);
                    ResultSet uidSet = state.executeQuery(uid);
                    universityID = Integer.parseInt(uidSet.getString("uin"));

//                    String tableCheck = String.format("SELECT count(*) FROM studentinfo WHERE type='table' AND name='%s'", uid2);
//
//                    ResultSet tableSet = state.executeQuery(tableCheck);
//
//                    System.out.println(tableCheck);
//
//                    if (tableSet == 0) {
//
//                        String tableCreate = String.format("CREATE TABLE ['%s']" +
//                                "date INTEGER PRIMARY KEY," + "breakfast TEXT," + "lunch TEXT," +
//                                "dinner TEXT," + "snack TEXT," + "points_used INTEGER," +
//                                "total_calories INTEGER," + "total_fat_calories INTEGER," +
//                                "[total protein] INTEGER," + "total_carbs INTEGER," +
//                                "total_fat INTEGER", uid);
//                    }

                    String fName = String.format("SELECT first_name FROM student WHERE student_email= '%s'", uname);
                    ResultSet fNameSet = state.executeQuery(fName);
                    firstName = fNameSet.getString("first_name");

                    String lName = String.format("SELECT last_name FROM student WHERE student_email= '%s'", uname);
                    ResultSet lNameSet = state.executeQuery(lName);
                    lastName = lNameSet.getString("last_name");

                    String query = String.format("SELECT student_password FROM student WHERE student_email= '%s'", uname);
                    ResultSet rs = state.executeQuery(query);

                    usertxt = txtFieldUser.getText();
                    passtxt = rs.getString("student_password");

                    if (uname.equals(usertxt) && upaswd.equals(passtxt)) {
                        MainMenu menu = new MainMenu();
                        dispose();
                    } else if (uname.equals("") && upaswd.equals("")) {
                        JOptionPane.showMessageDialog(null, "Please insert Username and Password");
                    } else {

                        JOptionPane.showMessageDialog(null, "Wrong Username / Password");
                        txtFieldUser.setText("");
                        txtFieldPassword.setText("");
                        txtFieldUser.requestFocus();
                    }
                } catch (SQLException e1) {
                    JOptionPane.showMessageDialog(null, "Invalid credentials entered! Please try again.",
                            "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}