package MainScreens;

import Utility.ToolClass;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Scanner;

///////////////////////////////////
/*
 * File: LogInScreen.java
 * -----------------------
 * This program uses the GUI layout mechanism to create a login
 * page. Students will enter their school email address and password. These
 * two variables will be checked against the database.
 */
///////////////////////////////

public class LogInScreen extends JFrame {

    //variables for for connecting to database
    public static String HOST;
    public static Connection studentInfoCon;

    //components for log in screen gui
    JButton btnLogIn, btnCancel;
    JPanel logInPanel;
    JTextField txtFieldUser, txtFieldPassword;
    JLabel logoHolderLabel, schoolChoiceLabel, usernameLabel, passwordLabel;
    JComboBox schoolChoiceBox;
    String logoURL = "http://i.imgur.com/hPN6Qz7.png";
    ImageIcon orderUpLogo;

    //variables used to for holding student information
    static int universityID;
    static String firstName = "", lastName = "";
    static int userPointTotal;
    static int userPointRemaining;


    //for schoolChoiceBox
    private static final String[] schoolNames = {"Florida Gulf Coast" +
            "University"};

    public LogInScreen() throws FileNotFoundException {
        //title
        super("Login Screen");

        //grabs the path from where the databases are being stored
        //path is in database_path.txt
        Scanner file = new Scanner(new File("database_path.txt"));
        HOST = file.nextLine();
        file.close();

        //puts schools names into comboBox rows
        schoolChoiceBox = new JComboBox(schoolNames);

        btnLogIn = new JButton("Login");
        btnCancel = new JButton("Cancel");
        logInPanel = new JPanel();
//        txtFieldUIN = new JTextField();
        txtFieldUser = new JTextField(15);
        txtFieldPassword = new JPasswordField(15);

        //Creates a ToolClass object and then calls
        //createImageIcon to add logo ImageIcon to orderUpLogo
        orderUpLogo = ToolClass.createImageIcon(logoURL, "Order-up Logo");

        //adds created logo icon to JLabel
        logoHolderLabel = new JLabel(orderUpLogo);

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

        //sets max rows to number of schools in schoolNames array
        schoolChoiceBox.setMaximumRowCount(schoolNames.length);

        //ctrl + hover over a function for info (IntelliJ)
        //sets all of the specified location of each of the JObjects
        //if setSize is changed, these will be affected

        //setting location          x,y,w,h
        logoHolderLabel.setBounds(155, 10, 200, 174);
        txtFieldUser.setBounds(175, 230, 150, 20);
        txtFieldPassword.setBounds(175, 265, 150, 20);
        btnLogIn.setBounds(210, 300, 80, 20);
        btnCancel.setBounds(210, 335, 80, 20);
        schoolChoiceBox.setBounds(175, 200, 150, 20);
        schoolChoiceLabel.setBounds(112, 193, 80, 20);
        usernameLabel.setBounds(135, 228, 80, 20);
        passwordLabel.setBounds(110, 263, 80, 20);

        //adding components
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

        //I think this can just be 'add(logInPanel);'
        getContentPane().add(logInPanel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        //compares user login and password with database on log in button click
        btnLogIn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {

                    String uname, upaswd;
                    String usertxt = " ";
                    String passtxt = " ";

                    //grabs user entered info
                    uname = txtFieldUser.getText();
                    upaswd = txtFieldPassword.getText();
                    String host = HOST;

                    //starts connection to database
                    studentInfoCon = DriverManager.getConnection(host);

                    //executes a sql query
                    Statement state = studentInfoCon.createStatement();

                    //grabs student uin from table student based on email
                    // address entered.
                    String uid = String.format("SELECT uin FROM student WHERE student_email= '%s'", uname);
                    ResultSet uidSet = state.executeQuery(uid);
                    universityID = Integer.parseInt(uidSet.getString("uin"));

                    //grabs student's meal bucks from table student based on
                    // email address entered
                    String uPoint = String.format("SELECT meal_bucks FROM student WHERE student_email= '%s'", uname);
                    ResultSet uPointSet = state.executeQuery(uPoint);
                    userPointTotal = Integer.parseInt(uidSet.getString("meal_bucks"));

                    //grabs student's first name
                    String fName = String.format("SELECT first_name FROM student WHERE student_email= '%s'", uname);
                    ResultSet fNameSet = state.executeQuery(fName);
                    firstName = fNameSet.getString("first_name");

                    //grabs students last name
                    String lName = String.format("SELECT last_name FROM student WHERE student_email= '%s'", uname);
                    ResultSet lNameSet = state.executeQuery(lName);
                    lastName = lNameSet.getString("last_name");

                    //grabs students password to be compared with entered one
                    String query = String.format("SELECT student_password FROM student WHERE student_email= '%s'", uname);
                    ResultSet rs = state.executeQuery(query);

                    usertxt = txtFieldUser.getText();
                    passtxt = rs.getString("student_password");

                     /*
                      * testing sysouts
                      * 
                      * System.out.println(uname + " " + upaswd);
                      * System.out.println(usertxt + " " + upaswd);
                      */
                    //compares entered student info with database info
                    if (uname.equals(usertxt) && upaswd.equals(passtxt)) {
                        MainMenu menu = new MainMenu();
                        dispose();

                        //if no info is entered, display error to enter it
                    } else if (uname.equals("") && upaswd.equals("")) {
                        JOptionPane.showMessageDialog(null, "Please insert Username and Password");

                    } else {
                        JOptionPane.showMessageDialog(null, "Wrong Username / Password");
                        txtFieldUser.setText("");
                        txtFieldPassword.setText("");
                        txtFieldUser.requestFocus();
                    }

                    // displays error if wrong info is entered
                } catch (SQLException e1) {
                    JOptionPane.showMessageDialog(null, "Invalid credentials entered! Please try again.",
                            "Input Error", JOptionPane.ERROR_MESSAGE);
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
            }
        });

        // if cancel button is clicked, closes screen
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}