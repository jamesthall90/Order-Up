import javax.swing.border.Border;
import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by TylerHall on 10/14/16.
 */
public class MainMenu extends JFrame {

    JPanel mainMenuPanel;
    String studentName = "Test Name"; // <----- Needs to store the student's name from userPass
    JLabel studentNameLabel;
    JLabel pointsLabel;
    JLabel totalPoints;
    JLabel totalPointsLabel;
    JLabel remainingPointsLabel;
    JLabel remainingPoints;
    JLabel pointsUsed;
    JLabel pointsUsedLabel;
    JButton myMealPlan;
    Color fgcuBlue;
    Color fgcuGreen;
    Border compound, fgcuGreenLine, raisedBevel, loweredBevel;
    Font studentNameFont, pointsLabelFont, totalPointsFont, totalPointsLabelFont,
            remainingPointsFont, remainingPointsLabelFont;
    String logoURL = "https://f9149b6c-a-62cb3a1a-s-sites.googlegroups.com/site/outstandingprogramming/documents/OrderUpLogo%20small.png?attachauth=ANoY7cqnPAfwcX7KptsKARQ4lduoL8wFCNEf5EjWKX1uc_Gde2kDkChiXRo43bP6gTEWQ42K_d38AmMGn2X9allt7u7rdTw8OneQC2qM8yuc1ef4i2hf2o--zoPN0IZf6OFBl4r4MkWYcj4Bl-eciN2sTYv331OurpjuG0YWyxpzoL637O7s4qVvseXBXsOFZpn_oUS6-0GuVyGZK2ctPi2Ee65KaHAIbFCwoEguIpZdpTXCZl2clF25ihz1kI_gXxCdaPB3pDXC&attredirects=0";
    ImageIcon orderUpLogoSmall;
    JLabel smallLogoholderLabel;

    public MainMenu() {
        super("Main Menu");

        fgcuBlue = new Color(9, 40, 105);
        fgcuGreen = new Color(1, 121, 76);

        //Objects created for use with compound border
        raisedBevel = BorderFactory.createRaisedBevelBorder();
        loweredBevel = BorderFactory.createLoweredBevelBorder();
        fgcuGreenLine = BorderFactory.createLineBorder(fgcuGreen);
        compound = BorderFactory.createCompoundBorder(raisedBevel, loweredBevel);
        compound = BorderFactory.createCompoundBorder(fgcuGreenLine, compound);

        //Font objects for various labels
        studentNameFont = new Font(Font.SANS_SERIF, Font.BOLD, 15);
        pointsLabelFont = new Font(Font.SANS_SERIF, Font.BOLD, 15);
        totalPointsFont = new Font(Font.SANS_SERIF, Font.BOLD, 15);
        totalPointsLabelFont = new Font(Font.SANS_SERIF, Font.BOLD, 15);
        remainingPointsFont = new Font(Font.SANS_SERIF, Font.BOLD, 15);
        remainingPointsLabelFont = new Font(Font.SANS_SERIF, Font.BOLD, 15);


        mainMenuPanel = new JPanel();

        setSize(500, 400); //sets the size of the frame
        setLocation(500, 280); //sets the location of the frame on the screen
        mainMenuPanel.setLayout(null);
        mainMenuPanel.setBackground(fgcuBlue);

        orderUpLogoSmall = createImageIcon(logoURL, "Order-Up Logo");

        smallLogoholderLabel = new JLabel(orderUpLogoSmall);

        studentNameLabel = new JLabel(studentName);
        studentNameLabel.setFont(studentNameFont);
        studentNameLabel.setForeground(fgcuBlue);
        studentNameLabel.setBackground(Color.white);
        studentNameLabel.setOpaque(true);
        studentNameLabel.setBorder(fgcuGreenLine);
        studentNameLabel.setHorizontalAlignment(SwingConstants.CENTER);

        pointsLabel = new JLabel("Points");
        pointsLabel.setForeground(Color.white);
        pointsLabel.setFont(pointsLabelFont);

        totalPointsLabel = new JLabel("Total ");
        totalPointsLabel.setFont(totalPointsLabelFont);
        totalPointsLabel.setForeground(Color.white);

        totalPoints = new JLabel("500"); // <----- Needs to display total points (probably an int)
        totalPoints.setFont(totalPointsFont);
        totalPoints.setForeground(fgcuGreen);
        totalPoints.setBackground(Color.white);
        totalPoints.setOpaque(true);
        totalPoints.setBorder(fgcuGreenLine);
        totalPoints.setHorizontalAlignment(SwingConstants.CENTER);

        remainingPointsLabel = new JLabel("Remaining ");
        remainingPointsLabel.setFont(remainingPointsLabelFont);
        remainingPointsLabel.setForeground(Color.white);

        remainingPoints = new JLabel("256"); // <----- Needs to display remaining points (probably an int)
        remainingPoints.setFont(remainingPointsFont);
        remainingPoints.setForeground(fgcuGreen);
        remainingPoints.setBackground(Color.yellow);
        remainingPoints.setOpaque(true);
        remainingPoints.setBorder(fgcuGreenLine);
        remainingPoints.setHorizontalAlignment(SwingConstants.CENTER);

        myMealPlan = new JButton("My Meal Plan");
        myMealPlan.setForeground(fgcuGreen);

        smallLogoholderLabel.setBounds(38, 10, 100, 87);
        studentNameLabel.setBounds(20, 100, 140, 40);
        pointsLabel.setBounds(400, 10, 100, 50);
        totalPoints.setBounds(435, 55, 40, 30);
        totalPointsLabel.setBounds(373, 47, 65, 40);
        remainingPoints.setBounds(435, 84, 40, 30);
        remainingPointsLabel.setBounds(331, 75, 100, 40);
        myMealPlan.setBounds(385, 153, 100, 40);

        mainMenuPanel.add(smallLogoholderLabel);
        mainMenuPanel.add(studentNameLabel);
        mainMenuPanel.add(pointsLabel);
        mainMenuPanel.add(totalPoints);
        mainMenuPanel.add(totalPointsLabel);
        mainMenuPanel.add(remainingPoints);
        mainMenuPanel.add(remainingPointsLabel);
        mainMenuPanel.add(myMealPlan);

        getContentPane().add(mainMenuPanel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    protected ImageIcon createImageIcon(String path, String description) {
//        java.net.URL imgURL = getClass().getResource(path);
        URL imgURL = null;

        try {
            imgURL = new URL(path);

        } catch (MalformedURLException e) { // catches an invalid url exception
            e.printStackTrace();
        }

        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }

    }
}
