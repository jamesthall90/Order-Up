package MainScreens;

import Utility.ToolClass;

import javax.swing.*;
import java.awt.*;

/**
 * Created by TylerHall on 10/20/16.
 */
public class DayPlanner extends JFrame {

    JPanel dayPlannerPanel;
    JPanel breakfastPanel;
    JPanel lunchPanel;
    JPanel dinnerPanel;
    JPanel snackPanel;
    JLabel breakfastLabel;
    JComboBox breakfastRestaurants;
    JComboBox breakfastFoodItems;
    JComboBox breakfastDrinkItems;
    JLabel lunchLabel;
    JComboBox lunchRestaurants;
    JComboBox lunchFoodItems;
    JComboBox lunchDrinkItems;
    JLabel dinnerLabel;
    JComboBox dinnerRestaurants;
    JComboBox dinnerFoodItems;
    JComboBox dinnerDrinkItems;
    JLabel snackLabel;
    JComboBox snackRestaurants;
    JComboBox snackItems;
    Dimension innerPanelDimension = new Dimension(200, 200);

    public DayPlanner() {

        super("Today's Meal Plan");

//        dayPlannerPanel = new JPanel();
        setSize(800, 800); //sets the size of the frame
        setLocation(500, 500); //sets the location of the frame on the screen
        setLayout(new FlowLayout());
        setBackground(ToolClass.fgcuGreen);
        getContentPane();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        /* Breakfast Panel & Items */
        breakfastPanel = new JPanel();
        breakfastPanel.setLayout(new GridLayout(4, 1));
        breakfastPanel.setVisible(true);
        breakfastPanel.setPreferredSize(innerPanelDimension);
        breakfastPanel.setBackground(ToolClass.fgcuGreen);

        breakfastLabel = new JLabel("Breakfast");
        breakfastLabel.setForeground(Color.WHITE);
        breakfastLabel.setFont(ToolClass.largerBoldHeadingFont);
        breakfastLabel.setVisible(true);

        breakfastRestaurants = new JComboBox();
        breakfastRestaurants.setVisible(true);
        breakfastRestaurants.setBackground(Color.WHITE);
        breakfastRestaurants.setForeground(ToolClass.fgcuBlue);

        breakfastFoodItems = new JComboBox();
        breakfastFoodItems.setBackground(Color.WHITE);
        breakfastFoodItems.setForeground(ToolClass.fgcuGreen);

        breakfastDrinkItems = new JComboBox();
        breakfastDrinkItems.setBackground(Color.WHITE);
        breakfastDrinkItems.setForeground(ToolClass.fgcuBlue);

        /* Lunch Panel & Items */
        lunchPanel = new JPanel();
        lunchPanel.setLayout(new GridLayout(4, 1));
        lunchPanel.setVisible(true);
        lunchPanel.setPreferredSize(innerPanelDimension);
        lunchPanel.setBackground(ToolClass.fgcuGreen);

        lunchLabel = new JLabel("Lunch");
        lunchLabel.setForeground(Color.WHITE);
        lunchLabel.setFont(ToolClass.largerBoldHeadingFont);

        lunchRestaurants = new JComboBox();
        lunchRestaurants.setVisible(true);
        lunchRestaurants.setBackground(Color.WHITE);
        lunchRestaurants.setForeground(ToolClass.fgcuBlue);

        lunchFoodItems = new JComboBox();
        lunchFoodItems.setBackground(Color.WHITE);
        lunchFoodItems.setForeground(ToolClass.fgcuGreen);

        lunchDrinkItems = new JComboBox();
        lunchDrinkItems.setBackground(Color.WHITE);
        lunchDrinkItems.setForeground(ToolClass.fgcuBlue);

        /* Dinner Panel & Items */

        dinnerPanel = new JPanel();
        dinnerPanel.setLayout(new GridLayout(4, 1));
        dinnerPanel.setVisible(true);
        dinnerPanel.setPreferredSize(innerPanelDimension);
        dinnerPanel.setBackground(ToolClass.fgcuGreen);

        dinnerLabel = new JLabel("Dinner");
        dinnerLabel.setForeground(Color.WHITE);
        dinnerLabel.setFont(ToolClass.largerBoldHeadingFont);

        dinnerRestaurants = new JComboBox();
        dinnerRestaurants.setVisible(true);
        dinnerRestaurants.setBackground(Color.WHITE);
        dinnerRestaurants.setForeground(ToolClass.fgcuBlue);

        dinnerFoodItems = new JComboBox();
        dinnerFoodItems.setBackground(Color.WHITE);
        dinnerFoodItems.setForeground(ToolClass.fgcuGreen);

        dinnerDrinkItems = new JComboBox();
        dinnerDrinkItems.setBackground(Color.WHITE);
        dinnerDrinkItems.setForeground(ToolClass.fgcuBlue);

        /* Snack Panel & Items */

        snackPanel = new JPanel();
        snackPanel.setLayout(new GridLayout(3, 1));
        snackPanel.setVisible(true);
        snackPanel.setSize(innerPanelDimension);
        snackPanel.setBackground(ToolClass.fgcuGreen);

        snackLabel = new JLabel("Snack");
        snackLabel.setForeground(Color.WHITE);
        snackLabel.setFont(ToolClass.largerBoldHeadingFont);

        snackRestaurants = new JComboBox();
        snackRestaurants.setVisible(true);
        snackRestaurants.setBackground(Color.WHITE);
        snackRestaurants.setForeground(ToolClass.fgcuBlue);

        snackItems = new JComboBox();
        snackItems.setBackground(Color.WHITE);
        snackItems.setForeground(ToolClass.fgcuGreen);


//        breakfastLabel.setBounds(150, 90, 100, 80);
//        breakfastRestaurants.setBounds(150, 185, 120, 50);
//        breakfastFoodItems.setBounds();
//        breakfastDrinkItems.setBounds();
//        lunchLabel.setBounds();
//        lunchFoodItems.setBounds();
//        lunchDrinkItems.setBounds();
//        dinnerLabel.setBounds();
//        dinnerRestaurants.setBounds();
//        dinnerFoodItems.setBounds();
//        dinnerDrinkItems.setBounds();
//        snackLabel.setBounds();
//        snackRestaurants.setBounds();
//        snackItems.setBounds();

        add(breakfastPanel, FlowLayout.LEFT);
        breakfastPanel.add(breakfastLabel);
        breakfastPanel.add(breakfastRestaurants);
        breakfastPanel.add(breakfastFoodItems);
        breakfastPanel.add(breakfastDrinkItems);

        add(lunchPanel, FlowLayout.CENTER);
        lunchPanel.add(lunchLabel);
        lunchPanel.add(lunchRestaurants);
        lunchPanel.add(lunchFoodItems);
        lunchPanel.add(lunchDrinkItems);

        add(dinnerPanel, FlowLayout.RIGHT);
        dinnerPanel.add(dinnerLabel);
        dinnerPanel.add(dinnerRestaurants);
        dinnerPanel.add(dinnerFoodItems);
        dinnerPanel.add(dinnerDrinkItems);

        validate();
//        dayPlannerPanel.add(snackPanel, FlowLayout.CENTER);
//        snackPanel.add(snackLabel);
//        snackPanel.add(snackRestaurants);
//        snackPanel.add(snackItems);

//        dayPlannerPanel.add(lunchLabel);
//        dayPlannerPanel.add(lunchRestaurants);
//        dayPlannerPanel.add(lunchFoodItems);
//        dayPlannerPanel.add(lunchDrinkItems);
//        dayPlannerPanel.add(dinnerLabel);
//        dayPlannerPanel.add(dinnerRestaurants);
//        dayPlannerPanel.add(dinnerFoodItems);
//        dayPlannerPanel.add(dinnerDrinkItems);
//        dayPlannerPanel.add(snackLabel);
//        dayPlannerPanel.add(snackRestaurants);
//        dayPlannerPanel.add(snackItems);

    }

}
