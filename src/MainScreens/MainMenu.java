package MainScreens;

import Calendar.CalendarDemo;
import Utility.ToolClass;

import javax.swing.border.Border;
import javax.swing.*;
import java.awt.*;

/**
 * Created by TylerHall on 10/14/16.
 */

public class MainMenu extends JFrame {

    JPanel mainMenuPanel;
    String studentName = "John Doe"; // <----- Needs to store the student's name from userPass
    JLabel studentNameLabel;
    JLabel pointsLabel;
    JLabel totalPoints;
    JLabel totalPointsLabel;
    JLabel remainingPointsLabel;
    JLabel remainingPoints;
    JLabel pointsUsed;
    JLabel pointsUsedLabel;
    JButton myMealPlan;
    Border compound, fgcuGreenLine, raisedBevel, loweredBevel;
    Font studentNameFont, largerBoldHeadingFont, totalPointsFont, totalPointsLabelFont,
            remainingPointsFont, remainingPointsLabelFont;
    //Logo should be added to shorter URL for code convention purposes
    String logoURL = "https://f9149b6c-a-62cb3a1a-s-sites.googlegroups.com/site/outstandingprogramming/documents/OrderUpLogo%20small.png";
    ImageIcon orderUpLogoSmall;
    JLabel smallLogoholderLabel;

    public MainMenu() {
        super("Main Menu");

        //Objects created for use with compound border
        raisedBevel = BorderFactory.createRaisedBevelBorder();
        loweredBevel = BorderFactory.createLoweredBevelBorder();
        fgcuGreenLine = BorderFactory.createLineBorder(ToolClass.fgcuGreen);
        compound = BorderFactory.createCompoundBorder(raisedBevel, loweredBevel);
        compound = BorderFactory.createCompoundBorder(fgcuGreenLine, compound);

        //Font objects for various labels

        totalPointsLabelFont = new Font(Font.SANS_SERIF, Font.ITALIC, 15);
        remainingPointsLabelFont = new Font(Font.SANS_SERIF, Font.ITALIC, 15);


        mainMenuPanel = new JPanel();

        setSize(750, 640); //sets the size of the frame
        setLocation(500, 280); //sets the location of the frame on the screen
        mainMenuPanel.setLayout(null);
        mainMenuPanel.setBackground(ToolClass.fgcuBlue);


        //Creates a ToolClass object and then calls
        //createImageIcon to add logo ImageIcon to orderUpLogoSmall

        orderUpLogoSmall = ToolClass.createImageIcon(logoURL, "Order-Up Logo");

        smallLogoholderLabel = new JLabel(orderUpLogoSmall);

        // Initialization and settings for student Name Label
        studentNameLabel = new JLabel(studentName);
        studentNameLabel.setFont(ToolClass.smallBoldHeadingFont);
        studentNameLabel.setForeground(ToolClass.fgcuBlue);
        studentNameLabel.setBackground(Color.white);
        studentNameLabel.setOpaque(true);
        studentNameLabel.setBorder(fgcuGreenLine);
        studentNameLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Initialization and settings for points Label
        pointsLabel = new JLabel("Points");
        pointsLabel.setForeground(Color.white);
        pointsLabel.setFont(ToolClass.largerBoldHeadingFont);

        // Initialization and settings for total Points Label
        totalPointsLabel = new JLabel("Total ");
        totalPointsLabel.setFont(ToolClass.smallItalicHeadingFont);
        totalPointsLabel.setForeground(Color.white);

        // Initialization and settings for total Points output
        totalPoints = new JLabel("500"); // <----- Needs to display total points (probably an int)
        totalPoints.setFont(ToolClass.smallBoldHeadingFont);
        totalPoints.setForeground(ToolClass.fgcuGreen);
        totalPoints.setBackground(Color.white);
        totalPoints.setOpaque(true);
        totalPoints.setBorder(fgcuGreenLine);
        totalPoints.setHorizontalAlignment(SwingConstants.CENTER);

        // Initialization and settings for remaining Points Label
        remainingPointsLabel = new JLabel("Remaining ");
        remainingPointsLabel.setFont(ToolClass.smallItalicHeadingFont);
        remainingPointsLabel.setForeground(Color.white);

        // Initialization and settings for remaining Points output
        remainingPoints = new JLabel("256"); // <----- Needs to display remaining points (probably an int)
        remainingPoints.setFont(ToolClass.smallBoldHeadingFont);
        remainingPoints.setForeground(ToolClass.fgcuGreen);
        remainingPoints.setBackground(Color.yellow);
        remainingPoints.setOpaque(true);
        remainingPoints.setBorder(fgcuGreenLine);
        remainingPoints.setHorizontalAlignment(SwingConstants.CENTER);

        // Initialization and settings for my Meal Plan button
        myMealPlan = new JButton("My Meal Plan");
        myMealPlan.setForeground(ToolClass.fgcuGreen);

        // Bounds / placement settings for all objects in mainMenuPanel
        smallLogoholderLabel.setBounds(49, 10, 100, 87);
        studentNameLabel.setBounds(20, 100, 160, 40);
        pointsLabel.setBounds(650, 10, 100, 50);
        totalPoints.setBounds(685, 55, 40, 30);
        totalPointsLabel.setBounds(636, 47, 65, 40);
        remainingPoints.setBounds(685, 84, 40, 30);
        remainingPointsLabel.setBounds(597, 75, 100, 40);
        myMealPlan.setBounds(585, 153, 100, 40);


        mainMenuPanel.add(smallLogoholderLabel);
        mainMenuPanel.add(studentNameLabel);
        mainMenuPanel.add(pointsLabel);
        mainMenuPanel.add(totalPoints);
        mainMenuPanel.add(totalPointsLabel);
        mainMenuPanel.add(remainingPoints);
        mainMenuPanel.add(remainingPointsLabel);
        //mainMenuPanel.add(myMealPlan);

        /*TESTING*/
        CalendarDemo cal = new CalendarDemo();
        mainMenuPanel.add(cal);
        cal.setBounds(78, 200, 600, 400);
        cal.setVisible(true);

        /*END TESTING*/

        getContentPane().add(mainMenuPanel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

}
