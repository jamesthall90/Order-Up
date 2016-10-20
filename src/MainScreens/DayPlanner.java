package MainScreens;

import Utility.ToolClass;

import javax.swing.*;
import java.awt.*;

/**
 * Created by TylerHall on 10/20/16.
 */
public class DayPlanner extends JFrame {

    JPanel dayPlannerPanel;
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

    public DayPlanner() {

        super("Today's Meal Plan");

        dayPlannerPanel = new JPanel();
        setSize(750, 640); //sets the size of the frame
        setLocation(500, 280); //sets the location of the frame on the screen
        dayPlannerPanel.setLayout(null);
        dayPlannerPanel.setBackground(ToolClass.fgcuGreen);
        getContentPane().add(dayPlannerPanel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        breakfastLabel = new JLabel("Breakfast");
        breakfastLabel.setForeground(Color.white);
        breakfastLabel.setFont(ToolClass.largerBoldHeadingFont);

        breakfastRestaurants = new JComboBox();
        breakfastRestaurants.setVisible(true);
        breakfastRestaurants.setBackground(Color.WHITE);

        breakfastLabel.setBounds(100, 90, 100, 80);
        breakfastRestaurants.setBounds(100, 185, 120, 50);
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

        dayPlannerPanel.add(breakfastLabel);
        dayPlannerPanel.add(breakfastRestaurants);
        dayPlannerPanel.add(breakfastFoodItems);
        dayPlannerPanel.add(breakfastDrinkItems);
        dayPlannerPanel.add(lunchLabel);
        dayPlannerPanel.add(lunchRestaurants);
        dayPlannerPanel.add(lunchFoodItems);
        dayPlannerPanel.add(lunchDrinkItems);
        dayPlannerPanel.add(dinnerLabel);
        dayPlannerPanel.add(dinnerRestaurants);
        dayPlannerPanel.add(dinnerFoodItems);
        dayPlannerPanel.add(dinnerDrinkItems);
        dayPlannerPanel.add(snackLabel);
        dayPlannerPanel.add(snackRestaurants);
        dayPlannerPanel.add(snackItems);

    }

}
