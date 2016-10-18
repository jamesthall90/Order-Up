import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
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
    Color fgcuGreen;
    String logoURL = "http://i.imgur.com/hPN6Qz7.png";
    ImageIcon orderUpLogo;

    //for schoolChoiceBox
    private static final String[] schoolNames = {" ", "Florida Gulf Coast " +
            "University"};

    public LogInScreen() {
        super("Login Screen");

        fgcuGreen = new Color(1, 121, 76); //Custom color for FGCU green

        schoolChoiceBox = new JComboBox(schoolNames); //puts schools names into comboBox rows

        btnLogIn = new JButton("Login");
        btnCancel = new JButton("Cancel");
        logInPanel = new JPanel();
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
        setLocation(500, 280); //sets the location of the frame on the screen
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
        logInPanel.setBackground(fgcuGreen);

        getContentPane().add(logInPanel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        Writer writer = null;
        File check = new File("userPass.txt");
        if (check.exists()) {

            //Checks if the file exists. will not add anything if the file does exist.
        } else {
            try {
                File texting = new File("userPass.txt");
                writer = new BufferedWriter(new FileWriter(texting));
                writer.write("message");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        btnLogIn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    File file = new File("userPass.txt");
                    Scanner scan = new Scanner(file);
                    ;
                    String line = null;
                    FileWriter filewrite = new FileWriter(file, true);

                    String usertxt = " ";
                    String passtxt = " ";
                    String puname = txtFieldUser.getText();
                    String ppaswd = txtFieldPassword.getText();


                    while (scan.hasNext()) {
                        usertxt = scan.nextLine();
                        passtxt = scan.nextLine();

                    }


                    if (puname.equals(usertxt) && ppaswd.equals(passtxt)) {
                        MainMenu menu = new MainMenu(); // We don't have a main menu yet
                        dispose();
                    } else if (puname.equals("") && ppaswd.equals("")) {
                        JOptionPane.showMessageDialog(null, "Please insert Username and Password");
                    } else {

                        JOptionPane.showMessageDialog(null, "Wrong Username / Password");
                        txtFieldUser.setText("");
                        txtFieldPassword.setText("");
                        txtFieldUser.requestFocus();
                    }
                } catch (IOException d) {
                    d.printStackTrace();
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