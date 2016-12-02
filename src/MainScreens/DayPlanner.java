package MainScreens;

import MainScreens.MainMenu.CalendarDemo;
import Utility.ToolClass;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.*;

public class DayPlanner extends JFrame {

    JButton submitBtn;
    JPanel dayPlannerPanel, breakfastPanel, lunchPanel, dinnerPanel, snackPanel, breakfastNutrition, lunchNutrition,
            dinnerNutrition, snackNutrition, totalNutrition;

    JLabel breakfastCalories, breakfastFatCalories, breakfastProtein, breakfastCarbs, breakfastFat, lunchCalories,
            lunchFatCalories, lunchProtein, lunchCarbs, lunchFat, dinnerCalories, dinnerFatCalories, dinnerProtein,
            dinnerCarbs, dinnerFat, snackCalories, snackFatCalories, snackProtein, snackCarbs, snackFat, totalCalories,
            totalFatCalories, totalProtein, totalCarbs, totalFat, breakfastLabel, lunchLabel, dinnerLabel, snackLabel;

    JComboBox breakfastRestaurants, breakfastFoodItems, breakfastSideItems, breakfastDrinkItems, lunchRestaurants,
            lunchFoodItems, lunchSideItems, lunchDrinkItems, dinnerRestaurants, dinnerFoodItems, dinnerSideItems,
            dinnerDrinkItems, snackRestaurants, snackItems;

    Dimension innerPanelDimension = new Dimension(200, 200);
    String[] ent, side, drink = new String[3]; // temporary array to hold menu

    // items and load into combo-boxes
    String host;

    Connection bFoodItemsConnect, drink_itemConnect;

    Statement bFoodState, bSideState, bDrinkState;
    Statement lFoodState, lSideState, lDrinkState;
    Statement dFoodState, dSideState, dDrinkState;
    Statement sFoodState;
    
    
    ResultSet bFoodSet, bSideSet, bDrinkSet;
    ResultSet lFoodSet, lSideSet, lDrinkSet;
    ResultSet dFoodSet, dSideSet, dDrinkSet;
    ResultSet sFoodSet;
    
    

    String[] restaurants = {"Einstein Bros. Bagels", "Papa Johns", "Brahma Express", "Chick-Fil-A", "Jamba Juice",
            "Starbucks"};
    String[] sRestaurants = {"Jamba Juice", "Starbucks"};
    String[] einEnt = {"Plain Bagel", "Cheesy Bacon Club", "Santa Fe Wrap"}; //Entered into DB
    String[] einSide = {"Blueberry Muffin", "Brownie", "Cookie"}; //Entered into DB
    String[] einDrink = {"Coffee", "Orange Juice", "Chocolate Milk"}; //Entered into DB
    String[] papaEnt = {"Cheese Pizza", "Pepperoni Pizza", "Hot Wings"}; //Entered into DB
    String[] papaSide = {"Cookie Slice", "Brownie", "Breadsticks x2"}; //Entered into DB
    String[] papaDrink = {"Water", "Coke", "Sprite"}; //Entered into DB
    String[] brEnt = {"Teriyaki Chicken Bowl", "Dunk City Roll", "Spicy Tuna Roll"}; //Entered into DB
    String[] brSide = {"Egg Roll", "Miso Soup", "Seaweed Salad"}; //Entered into DB
    String[] brDrink = {"Water", "Coke", "Sprite"}; //Entered into DB
    String[] chickEnt = {"Chicken Sandwich", "8-Piece Chicken Nuggets", "Chicken Salad"};  //Entered into DB
    String[] chickSide = {"Waffle Fries", "Cookie", "Fruit Cup"}; //Entered into DB
    String[] chickDrink = {"Water", "Lemonade", "Iced Tea"}; //Entered into DB
    String[] jambaDrink = {"Mango Smoothie", "Strawberry Smoothie", "Chocolate Banana Smoothie"}; //Entered into DB
    String[] starDrink = {"Vanilla Bean Frappuccino", "Coffee", "Raspberry Iced Tea"}; //Entered into DB

    String breakfastRestaurantName, breakfastDrinkName, breakfastFoodName, breakfastSideName;
    int bFoodCal, bFoodFatCal, bFoodCarb, bFoodProtein, bFoodPoints, bSideCal, bSideFatCal, bSideCarb, bSideProtein, bSidePoints,
    lFoodCal, lFoodFatCal, lFoodCarb, lFoodProtein, lFoodPoints, dFoodCal, dFoodFatCal, dFoodCarb, dFoodProtein, dFoodPoints, 
    sFoodCal, sFoodFatCal, sFoodCarb, sFoodProtein, sFoodPoints;


    BoxHandler boxHandler = new BoxHandler();

    public DayPlanner(String dayText) throws SQLException { // dayText is the day number
        super("Meal Plan for " + CalendarDemo.capitalize(CalendarDemo.monthNames[CalendarDemo.month])+ ", " + dayText + " " + CalendarDemo.year);

        String databaseKey = CalendarDemo.datePrimaryKey;

        System.out.println(databaseKey);

        ent = einEnt;
        side = einSide;
        drink = einDrink;

        dayPlannerPanel = new JPanel();
        setSize(800, 800); // sets the size of the frame
        setLocationRelativeTo(null); // sets the location of the frame on the screen
        dayPlannerPanel.setLayout(null);

        dBConnect();
        breakfastItems();
        breakfastNutritionItems();
        lunchItems();
        lunchNutritionItems();
        dinnerItems();
        dinnerNutritionItems();
        snackItems();
        snackNutritionItems();
        totalNutritionItems();
        submit();

        submitBtn.setBounds(0,0,200,50);
        totalNutrition.setBounds(285, 70, 225, 70);
        breakfastPanel.setBounds(20, 120, 225, 225);
        breakfastNutrition.setBounds(20, 345, 225, 70);
        lunchPanel.setBounds(560, 120, 225, 225);
        lunchNutrition.setBounds(560, 345, 225, 70);
        snackPanel.setBounds(310, 120, 185, 170);
        snackNutrition.setBounds(310, 290, 185, 70);
        dinnerPanel.setBounds(300, 400, 225, 225);
        dinnerNutrition.setBounds(300, 625, 225, 70);
        // submitBtn.setBounds(350, 400, 60, 60);

        // Addition of contents to dayPlannerPanel
        getContentPane().add(dayPlannerPanel);

        dayPlannerPanel.add(submitBtn);

        dayPlannerPanel.add(totalNutrition);
        dayPlannerPanel.add(breakfastPanel);
        dayPlannerPanel.add(breakfastNutrition);
        dayPlannerPanel.add(lunchPanel);
        dayPlannerPanel.add(lunchNutrition);
        dayPlannerPanel.add(dinnerPanel);
        dayPlannerPanel.add(dinnerNutrition);
        dayPlannerPanel.add(snackPanel);
        dayPlannerPanel.add(snackNutrition);
        // dayPlannerPanel.add(submitBtn);

        dayPlannerPanel.setBackground(ToolClass.fgcuGreen);

        setVisible(true);

        // changed this to Dispose so it won't close the entire program
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        WindowListener OnCLose = new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {

            }

            @Override
            public void windowClosed(WindowEvent e) {
                MainMenu menu = new MainMenu();

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        };

        this.addWindowListener(OnCLose);

    }

    private class BoxHandler implements ActionListener {

        @Override
    public void actionPerformed(ActionEvent e) {
      if (e.getSource() == breakfastRestaurants) {

        JComboBox br = (JComboBox) e.getSource();
        String r = (String) br.getSelectedItem();
        // update breakfast combo boxes
        breakfastPanel.remove(breakfastFoodItems);
        breakfastPanel.remove(breakfastSideItems);
        breakfastPanel.remove(breakfastDrinkItems);

        if (r.equals(restaurants[0])) {
          ent = einEnt;
          side = einSide;
          drink = einDrink;
        } else if (r.equals(restaurants[1])) {
          ent = papaEnt;
          side = papaSide;
          drink = papaDrink;
        } else if (r.equals(restaurants[2])) {
          ent = brEnt;
          side = brSide;
          drink = brDrink;
        } else if (r.equals(restaurants[3])) {
          ent = chickEnt;
          side = chickSide;
          drink = chickDrink;
        } else if (r.equals(restaurants[4])) {
          ent = new String[0];
          side = new String[0];
          drink = jambaDrink;
        } else if (r.equals(restaurants[5])) {
          ent = new String[0];
          side = new String[0];
          drink = starDrink;
        }

        breakfastFoodItems = new JComboBox(ent);
        breakfastFoodItems.addActionListener(boxHandler);
        breakfastSideItems = new JComboBox(side);
        breakfastSideItems.addActionListener(boxHandler);
        breakfastDrinkItems = new JComboBox(drink);
        breakfastDrinkItems.addActionListener(boxHandler);
        breakfastPanel.add(breakfastFoodItems);
        breakfastPanel.add(breakfastSideItems);
        breakfastPanel.add(breakfastDrinkItems);
        
        bFoodSet = null;
        bSideSet = null;
        bDrinkSet = null;
        bFoodCal = 0;
        bFoodFatCal = 0;
        bFoodCarb = 0;
        bFoodProtein = 0;
        bFoodPoints = 0;
        breakfastCalories.setText("<HTML><U>Total Calories: </U>" + bFoodCal + "</HTML> ");
        breakfastFatCalories.setText("<HTML><U>Total Fat Calories: </U>" + bFoodFatCal + "</HTML>");
        breakfastCarbs.setText("<HTML><U>Total Carbs: </U>" + bFoodCarb + "</HTML>");
        breakfastProtein.setText("<HTML><U>Total Protein: </U>" + bFoodProtein + "</HTML>");
        updateTotalNutrition();

//        queryAss(breakfastRestaurantName, breakfastFoodName, bFoodState, bFoodItemsConnect, bFoodSet, bFoodCal,
//            bFoodFatCal, bFoodProtein, bFoodCarb, bFoodPoints);

      } else if (e.getSource() == lunchRestaurants) {
        JComboBox lr = (JComboBox) e.getSource();
        String r = (String) lr.getSelectedItem();
        // update lunch combo boxes
        lunchPanel.remove(lunchFoodItems);
        lunchPanel.remove(lunchSideItems);
        lunchPanel.remove(lunchDrinkItems);

        if (r.equals(restaurants[0])) {
          ent = einEnt;
          side = einSide;
          drink = einDrink;
        } else if (r.equals(restaurants[1])) {
          ent = papaEnt;
          side = papaSide;
          drink = papaDrink;
        } else if (r.equals(restaurants[2])) {
          ent = brEnt;
          side = brSide;
          drink = brDrink;
        } else if (r.equals(restaurants[3])) {
          ent = chickEnt;
          side = chickSide;
          drink = chickDrink;
        } else if (r.equals(restaurants[4])) {
          ent = new String[0];
          side = new String[0];
          drink = jambaDrink;
        } else if (r.equals(restaurants[5])) {
          ent = new String[0];
          side = new String[0];
          drink = starDrink;
        }
        lunchFoodItems = new JComboBox(ent);
        lunchFoodItems.addActionListener(boxHandler);
        lunchSideItems = new JComboBox(side);
        lunchSideItems.addActionListener(boxHandler);
        lunchDrinkItems = new JComboBox(drink);
        lunchDrinkItems.addActionListener(boxHandler);
        lunchPanel.add(lunchFoodItems);
        lunchPanel.add(lunchSideItems);
        lunchPanel.add(lunchDrinkItems);
        
        lFoodSet = null;
        lSideSet = null;
        lDrinkSet = null;
        lFoodCal = 0;
        lFoodFatCal = 0;
        lFoodCarb = 0;
        lFoodProtein = 0;
        lFoodPoints = 0;
        lunchCalories.setText("<HTML><U>Total Calories: </U>" + lFoodCal + "</HTML> ");
        lunchFatCalories.setText("<HTML><U>Total Fat Calories: </U>" + lFoodFatCal + "</HTML>");
        lunchCarbs.setText("<HTML><U>Total Carbs: </U>" + lFoodCarb + "</HTML>");
        lunchProtein.setText("<HTML><U>Total Protein: </U>" + lFoodProtein + "</HTML>");
        updateTotalNutrition();
        
      } else if (e.getSource() == dinnerRestaurants) {
        JComboBox dr = (JComboBox) e.getSource();
        String r = (String) dr.getSelectedItem();
        // update dinner combo boxes
        dinnerPanel.remove(dinnerFoodItems);
        dinnerPanel.remove(dinnerSideItems);
        dinnerPanel.remove(dinnerDrinkItems);

        if (r.equals(restaurants[0])) {
          ent = einEnt;
          side = einSide;
          drink = einDrink;
        } else if (r.equals(restaurants[1])) {
          ent = papaEnt;
          side = papaSide;
          drink = papaDrink;
        } else if (r.equals(restaurants[2])) {
          ent = brEnt;
          side = brSide;
          drink = brDrink;
        } else if (r.equals(restaurants[3])) {
          ent = chickEnt;
          side = chickSide;
          drink = chickDrink;
        } else if (r.equals(restaurants[4])) {
          ent = new String[0];
          side = new String[0];
          drink = jambaDrink;
        } else if (r.equals(restaurants[5])) {
          ent = new String[0];
          side = new String[0];
          drink = starDrink;
        }
        dinnerFoodItems = new JComboBox(ent);
        dinnerFoodItems.addActionListener(boxHandler);
        dinnerSideItems = new JComboBox(side);
        dinnerSideItems.addActionListener(boxHandler);
        dinnerDrinkItems = new JComboBox(drink);
        dinnerDrinkItems.addActionListener(boxHandler);
        dinnerPanel.add(dinnerFoodItems);
        dinnerPanel.add(dinnerSideItems);
        dinnerPanel.add(dinnerDrinkItems);
        
        dFoodSet = null;
        dSideSet = null;
        dDrinkSet = null;
        dFoodCal = 0;
        dFoodFatCal = 0;
        dFoodCarb = 0;
        dFoodProtein = 0;
        dFoodPoints = 0;
        dinnerCalories.setText("<HTML><U>Total Calories: </U>" + dFoodCal + "</HTML> ");
        dinnerFatCalories.setText("<HTML><U>Total Fat Calories: </U>" + dFoodFatCal + "</HTML>");
        dinnerCarbs.setText("<HTML><U>Total Carbs: </U>" + dFoodCarb + "</HTML>");
        dinnerProtein.setText("<HTML><U>Total Protein: </U>" + dFoodProtein + "</HTML>");
        updateTotalNutrition();
        
      } else if (e.getSource() == snackRestaurants) {
        JComboBox sr = (JComboBox) e.getSource();
        String r = (String) sr.getSelectedItem();
        // update snack combo boxes
        snackPanel.remove(snackItems);

        if (r.equals(sRestaurants[0])) {
          side = jambaDrink;
        } else if (r.equals(sRestaurants[1])) {
          side = starDrink;
        } 
        snackItems = new JComboBox(side);
        snackItems.addActionListener(boxHandler);
        snackPanel.add(snackItems);

        sFoodSet = null;
        sFoodCal = 0;
        sFoodFatCal = 0;
        sFoodCarb = 0;
        sFoodProtein = 0;
        sFoodPoints = 0;
        snackCalories.setText("<HTML><U>Total Calories: </U>" + sFoodCal + "</HTML> ");
        snackFatCalories.setText("<HTML><U>Total Fat Calories: </U>" + sFoodFatCal + "</HTML>");
        snackCarbs.setText("<HTML><U>Total Carbs: </U>" + sFoodCarb + "</HTML>");
        snackProtein.setText("<HTML><U>Total Protein: </U>" + sFoodProtein + "</HTML>");
        updateTotalNutrition();
        
      } else if (e.getSource().equals(breakfastFoodItems)) {
        try {
          bFoodCal -= bFoodSet.getInt("total_calories");
          bFoodFatCal -= bFoodSet.getInt("total_fat_cal");
          bFoodCarb -= bFoodSet.getInt("total_carbs");
          bFoodProtein -= bFoodSet.getInt("total_protein");
          bFoodPoints -= bFoodSet.getInt("points");
        } catch (NullPointerException noFoodSet) {
          System.err.println("No bFoodSet created");
        } catch (SQLException sqlE) {
          System.err.println("SQL No bFoodSet");
        }
        bFoodSet = stephensQuery(breakfastRestaurants.getSelectedItem().toString(),
            breakfastFoodItems.getSelectedItem().toString(), bFoodState, bFoodItemsConnect, bFoodSet);
        try {
          bFoodCal += bFoodSet.getInt("total_calories");
          bFoodFatCal += bFoodSet.getInt("total_fat_cal");
          bFoodCarb += bFoodSet.getInt("total_carbs");
          bFoodProtein += bFoodSet.getInt("total_protein");
          bFoodPoints += bFoodSet.getInt("points");
          breakfastCalories.setText("<HTML><U>Total Calories: </U>" + bFoodCal + "</HTML> ");
          breakfastFatCalories.setText("<HTML><U>Total Fat Calories: </U>" + bFoodFatCal + "</HTML>");
          breakfastCarbs.setText("<HTML><U>Total Carbs: </U>" + bFoodCarb + "</HTML>");
          breakfastProtein.setText("<HTML><U>Total Protein: </U>" + bFoodProtein + "</HTML>");
          updateTotalNutrition();

        } catch (SQLException exc) {
          System.err.println("Didnt work");
        }
      } else if (e.getSource().equals(breakfastSideItems)) {
        try {
          bFoodCal -= bSideSet.getInt("total_calories");
          bFoodFatCal -= bSideSet.getInt("total_fat_cal");
          bFoodCarb -= bSideSet.getInt("total_carbs");
          bFoodProtein -= bSideSet.getInt("total_protein");
          bFoodPoints -= bSideSet.getInt("points");
        } catch (NullPointerException noFoodSet) {
          System.err.println("No bSideSet created");
        } catch (SQLException sqlE) {
          System.err.println("SQL No bSideSet");
        }
        bSideSet = stephensQuery(breakfastRestaurants.getSelectedItem().toString(),
            breakfastSideItems.getSelectedItem().toString(), bSideState, bFoodItemsConnect, bSideSet);
        try {
          bFoodCal += bSideSet.getInt("total_calories");
          bFoodFatCal += bSideSet.getInt("total_fat_cal");
          bFoodCarb += bSideSet.getInt("total_carbs");
          bFoodProtein += bSideSet.getInt("total_protein");
          bFoodPoints += bSideSet.getInt("points");
          breakfastCalories.setText("<HTML><U>Total Calories: </U>" + bFoodCal + "</HTML> ");
          breakfastFatCalories.setText("<HTML><U>Total Fat Calories: </U>" + bFoodFatCal + "</HTML>");
          breakfastCarbs.setText("<HTML><U>Total Carbs: </U>" + bFoodCarb + "</HTML>");
          breakfastProtein.setText("<HTML><U>Total Protein: </U>" + bFoodProtein + "</HTML>");
          updateTotalNutrition();

        } catch (SQLException exc) {
          System.err.println("Didnt work");
        }
      } else if (e.getSource().equals(breakfastDrinkItems)) {
        try {
          bFoodCal -= bDrinkSet.getInt("total_calories");
          bFoodFatCal -= bDrinkSet.getInt("total_fat_cal");
          bFoodCarb -= bDrinkSet.getInt("total_carbs");
          bFoodProtein -= bDrinkSet.getInt("total_protein");
          bFoodPoints -= bDrinkSet.getInt("points");
        } catch (NullPointerException noFoodSet) {
          System.err.println("No bDrinkSet created");
        } catch (SQLException sqlE) {
          System.err.println("SQL No bDrinkSet");
        }
        bDrinkSet = stephensQuery(breakfastRestaurants.getSelectedItem().toString(),
            breakfastDrinkItems.getSelectedItem().toString(), bDrinkState, bFoodItemsConnect, bDrinkSet);
        try {
          bFoodCal += bDrinkSet.getInt("total_calories");
          bFoodFatCal += bDrinkSet.getInt("total_fat_cal");
          bFoodCarb += bDrinkSet.getInt("total_carbs");
          bFoodProtein += bDrinkSet.getInt("total_protein");
          bFoodPoints += bDrinkSet.getInt("points");
          breakfastCalories.setText("<HTML><U>Total Calories: </U>" + bFoodCal + "</HTML> ");
          breakfastFatCalories.setText("<HTML><U>Total Fat Calories: </U>" + bFoodFatCal + "</HTML>");
          breakfastCarbs.setText("<HTML><U>Total Carbs: </U>" + bFoodCarb + "</HTML>");
          breakfastProtein.setText("<HTML><U>Total Protein: </U>" + bFoodProtein + "</HTML>");
          updateTotalNutrition();

        } catch (SQLException exc) {
          System.err.println("Didnt work");
        }
        // --------------------lunch------------------------------------
      } else if (e.getSource().equals(lunchFoodItems)) {
        try {
          lFoodCal -= lFoodSet.getInt("total_calories");
          lFoodFatCal -= lFoodSet.getInt("total_fat_cal");
          lFoodCarb -= lFoodSet.getInt("total_carbs");
          lFoodProtein -= lFoodSet.getInt("total_protein");
          lFoodPoints -= lFoodSet.getInt("points");
        } catch (NullPointerException noFoodSet) {
          System.err.println("No lFoodSet created");
        } catch (SQLException sqlE) {
          System.err.println("SQL No lFoodSet");
        }
        lFoodSet = stephensQuery(lunchRestaurants.getSelectedItem().toString(),
            lunchFoodItems.getSelectedItem().toString(), lFoodState, bFoodItemsConnect, lFoodSet);
        try {
          lFoodCal += lFoodSet.getInt("total_calories");
          lFoodFatCal += lFoodSet.getInt("total_fat_cal");
          lFoodCarb += lFoodSet.getInt("total_carbs");
          lFoodProtein += lFoodSet.getInt("total_protein");
          lFoodPoints += lFoodSet.getInt("points");
          lunchCalories.setText("<HTML><U>Total Calories: </U>" + lFoodCal + "</HTML> ");
          lunchFatCalories.setText("<HTML><U>Total Fat Calories: </U>" + lFoodFatCal + "</HTML>");
          lunchCarbs.setText("<HTML><U>Total Carbs: </U>" + lFoodCarb + "</HTML>");
          lunchProtein.setText("<HTML><U>Total Protein: </U>" + lFoodProtein + "</HTML>");
          updateTotalNutrition();

        } catch (SQLException exc) {
          System.err.println("Didnt work");
        }
      } else if (e.getSource().equals(lunchSideItems)) {
        try {
          lFoodCal -= lSideSet.getInt("total_calories");
          lFoodFatCal -= lSideSet.getInt("total_fat_cal");
          lFoodCarb -= lSideSet.getInt("total_carbs");
          lFoodProtein -= lSideSet.getInt("total_protein");
          lFoodPoints -= lSideSet.getInt("points");
        } catch (NullPointerException noFoodSet) {
          System.err.println("No lSideSet created");
        } catch (SQLException sqlE) {
          System.err.println("SQL No lSideSet");
        }
        lSideSet = stephensQuery(lunchRestaurants.getSelectedItem().toString(),
            lunchSideItems.getSelectedItem().toString(), lSideState, bFoodItemsConnect, lSideSet);
        try {
          lFoodCal += lSideSet.getInt("total_calories");
          lFoodFatCal += lSideSet.getInt("total_fat_cal");
          lFoodCarb += lSideSet.getInt("total_carbs");
          lFoodProtein += lSideSet.getInt("total_protein");
          lFoodPoints += lSideSet.getInt("points");
          lunchCalories.setText("<HTML><U>Total Calories: </U>" + lFoodCal + "</HTML> ");
          lunchFatCalories.setText("<HTML><U>Total Fat Calories: </U>" + lFoodFatCal + "</HTML>");
          lunchCarbs.setText("<HTML><U>Total Carbs: </U>" + lFoodCarb + "</HTML>");
          lunchProtein.setText("<HTML><U>Total Protein: </U>" + lFoodProtein + "</HTML>");
          updateTotalNutrition();

        } catch (SQLException exc) {
          System.err.println("Didnt work");
        }
      } else if (e.getSource().equals(lunchDrinkItems)) {
        try {
          lFoodCal -= lDrinkSet.getInt("total_calories");
          lFoodFatCal -= lDrinkSet.getInt("total_fat_cal");
          lFoodCarb -= lDrinkSet.getInt("total_carbs");
          lFoodProtein -= lDrinkSet.getInt("total_protein");
          lFoodPoints -= lDrinkSet.getInt("points");
        } catch (NullPointerException noFoodSet) {
          System.err.println("No lDrinkSet created");
        } catch (SQLException sqlE) {
          System.err.println("SQL No lDrinkSet");
        }
        lDrinkSet = stephensQuery(lunchRestaurants.getSelectedItem().toString(),
            lunchDrinkItems.getSelectedItem().toString(), lDrinkState, bFoodItemsConnect, lDrinkSet);
        try {
          lFoodCal += lDrinkSet.getInt("total_calories");
          lFoodFatCal += lDrinkSet.getInt("total_fat_cal");
          lFoodCarb += lDrinkSet.getInt("total_carbs");
          lFoodProtein += lDrinkSet.getInt("total_protein");
          lFoodPoints += lDrinkSet.getInt("points");
          lunchCalories.setText("<HTML><U>Total Calories: </U>" + lFoodCal + "</HTML> ");
          lunchFatCalories.setText("<HTML><U>Total Fat Calories: </U>" + lFoodFatCal + "</HTML>");
          lunchCarbs.setText("<HTML><U>Total Carbs: </U>" + lFoodCarb + "</HTML>");
          lunchProtein.setText("<HTML><U>Total Protein: </U>" + lFoodProtein + "</HTML>");
          updateTotalNutrition();

        } catch (SQLException exc) {
          System.err.println("Didnt work");
        }
        //-------------------------------dinner-----------------------------------
      }else if (e.getSource().equals(dinnerFoodItems)) {
        try {
          dFoodCal -= dFoodSet.getInt("total_calories");
          dFoodFatCal -= dFoodSet.getInt("total_fat_cal");
          dFoodCarb -= dFoodSet.getInt("total_carbs");
          dFoodProtein -= dFoodSet.getInt("total_protein");
          dFoodPoints -= dFoodSet.getInt("points");
        } catch (NullPointerException noFoodSet) {
          System.err.println("No dFoodSet created");
        } catch (SQLException sqlE) {
          System.err.println("SQL No dFoodSet");
        }
        dFoodSet = stephensQuery(dinnerRestaurants.getSelectedItem().toString(),
            dinnerFoodItems.getSelectedItem().toString(), dFoodState, bFoodItemsConnect, dFoodSet);
        try {
          dFoodCal += dFoodSet.getInt("total_calories");
          dFoodFatCal += dFoodSet.getInt("total_fat_cal");
          dFoodCarb += dFoodSet.getInt("total_carbs");
          dFoodProtein += dFoodSet.getInt("total_protein");
          dFoodPoints += dFoodSet.getInt("points");
          dinnerCalories.setText("<HTML><U>Total Calories: </U>" + dFoodCal + "</HTML> ");
          dinnerFatCalories.setText("<HTML><U>Total Fat Calories: </U>" + dFoodFatCal + "</HTML>");
          dinnerCarbs.setText("<HTML><U>Total Carbs: </U>" + dFoodCarb + "</HTML>");
          dinnerProtein.setText("<HTML><U>Total Protein: </U>" + dFoodProtein + "</HTML>");
          updateTotalNutrition();

        } catch (SQLException exc) {
          System.err.println("Didnt work");
        }
      } else if (e.getSource().equals(dinnerSideItems)) {
        try {
          dFoodCal -= dSideSet.getInt("total_calories");
          dFoodFatCal -= dSideSet.getInt("total_fat_cal");
          dFoodCarb -= dSideSet.getInt("total_carbs");
          dFoodProtein -= dSideSet.getInt("total_protein");
          dFoodPoints -= dSideSet.getInt("points");
        } catch (NullPointerException noFoodSet) {
          System.err.println("No dSideSet created");
        } catch (SQLException sqlE) {
          System.err.println("SQL No dSideSet");
        }
        dSideSet = stephensQuery(dinnerRestaurants.getSelectedItem().toString(),
            dinnerSideItems.getSelectedItem().toString(), dSideState, bFoodItemsConnect, dSideSet);
        try {
          dFoodCal += dSideSet.getInt("total_calories");
          dFoodFatCal += dSideSet.getInt("total_fat_cal");
          dFoodCarb += dSideSet.getInt("total_carbs");
          dFoodProtein += dSideSet.getInt("total_protein");
          dFoodPoints += dSideSet.getInt("points");
          dinnerCalories.setText("<HTML><U>Total Calories: </U>" + dFoodCal + "</HTML> ");
          dinnerFatCalories.setText("<HTML><U>Total Fat Calories: </U>" + dFoodFatCal + "</HTML>");
          dinnerCarbs.setText("<HTML><U>Total Carbs: </U>" + dFoodCarb + "</HTML>");
          dinnerProtein.setText("<HTML><U>Total Protein: </U>" + dFoodProtein + "</HTML>");
          updateTotalNutrition();

        } catch (SQLException exc) {
          System.err.println("Didnt work");
        }
      } else if (e.getSource().equals(dinnerDrinkItems)) {
        try {
          dFoodCal -= dDrinkSet.getInt("total_calories");
          dFoodFatCal -= dDrinkSet.getInt("total_fat_cal");
          dFoodCarb -= dDrinkSet.getInt("total_carbs");
          dFoodProtein -= dDrinkSet.getInt("total_protein");
          dFoodPoints -= dDrinkSet.getInt("points");
        } catch (NullPointerException noFoodSet) {
          System.err.println("No dDrinkSet created");
        } catch (SQLException sqlE) {
          System.err.println("SQL No dDrinkSet");
        }
        dDrinkSet = stephensQuery(dinnerRestaurants.getSelectedItem().toString(),
            dinnerDrinkItems.getSelectedItem().toString(), dDrinkState, bFoodItemsConnect, dDrinkSet);
        try {
          dFoodCal += dDrinkSet.getInt("total_calories");
          dFoodFatCal += dDrinkSet.getInt("total_fat_cal");
          dFoodCarb += dDrinkSet.getInt("total_carbs");
          dFoodProtein += dDrinkSet.getInt("total_protein");
          dFoodPoints += dDrinkSet.getInt("points");
          dinnerCalories.setText("<HTML><U>Total Calories: </U>" + dFoodCal + "</HTML> ");
          dinnerFatCalories.setText("<HTML><U>Total Fat Calories: </U>" + dFoodFatCal + "</HTML>");
          dinnerCarbs.setText("<HTML><U>Total Carbs: </U>" + dFoodCarb + "</HTML>");
          dinnerProtein.setText("<HTML><U>Total Protein: </U>" + dFoodProtein + "</HTML>");
          updateTotalNutrition();

        } catch (SQLException exc) {
          System.err.println("Didnt work");
        }
        //------------------------------------snack--------------------------------------
      } else if (e.getSource().equals(snackItems)) {
        try {
          sFoodCal -= sFoodSet.getInt("total_calories");
          sFoodFatCal -= sFoodSet.getInt("total_fat_cal");
          sFoodCarb -= sFoodSet.getInt("total_carbs");
          sFoodProtein -= sFoodSet.getInt("total_protein");
          sFoodPoints -= sFoodSet.getInt("points");
        } catch (NullPointerException noFoodSet) {
          System.err.println("No sFoodSet created");
        } catch (SQLException sqlE) {
          System.err.println("SQL No sFoodSet");
        }
        sFoodSet = stephensQuery(snackRestaurants.getSelectedItem().toString(),
            snackItems.getSelectedItem().toString(), sFoodState, bFoodItemsConnect, sFoodSet);
        try {
          sFoodCal += sFoodSet.getInt("total_calories");
          sFoodFatCal += sFoodSet.getInt("total_fat_cal");
          sFoodCarb += sFoodSet.getInt("total_carbs");
          sFoodProtein += sFoodSet.getInt("total_protein");
          sFoodPoints += sFoodSet.getInt("points");
          snackCalories.setText("<HTML><U>Total Calories: </U>" + sFoodCal + "</HTML> ");
          snackFatCalories.setText("<HTML><U>Total Fat Calories: </U>" + sFoodFatCal + "</HTML>");
          snackCarbs.setText("<HTML><U>Total Carbs: </U>" + sFoodCarb + "</HTML>");
          snackProtein.setText("<HTML><U>Total Protein: </U>" + sFoodProtein + "</HTML>");
          updateTotalNutrition();

        } catch (SQLException exc) {
          System.err.println("Didnt work");
        }
      } 
      revalidate();
      repaint();
    }
  }
    
    public void updateTotalNutrition() {
      totalCalories.setText("<HTML><U>Total Calories: </U>" + 0 + "</HTML>");
      totalCalories.setText("<HTML><U>Total Calories: </U>" + (bFoodCal+lFoodCal+dFoodCal+sFoodCal) + "</HTML>");
      totalFatCalories.setText("<HTML><U>Total Fat Calories: </U>" + 0 + "</HTML>");
      totalFatCalories.setText("<HTML><U>Total Fat Calories: </U>" + (bFoodFatCal+lFoodFatCal+dFoodFatCal+sFoodFatCal) + "</HTML>");
      totalCarbs.setText("<HTML><U>Total Carbs: </U>" + 0 + "</HTML>");
      totalCarbs.setText("<HTML><U>Total Carbs: </U>" + (bFoodCarb+lFoodCarb+dFoodCarb+sFoodCarb) + "</HTML>");
      totalProtein.setText("<HTML><U>Total Protein: </U>" + 0 + "</HTML>");
      totalProtein.setText("<HTML><U>Total Protein: </U>" + (bFoodProtein+lFoodProtein+dFoodProtein+sFoodProtein) + "</HTML>");
    }


    public void breakfastItems() {

    /* Breakfast Panel & Items */
        breakfastPanel = new JPanel();
        breakfastPanel.setLayout(new GridLayout(5, 1));
        breakfastPanel.setVisible(true);
        breakfastPanel.setPreferredSize(innerPanelDimension);
        breakfastPanel.setBackground(ToolClass.fgcuGreen);

        breakfastLabel = new JLabel("Breakfast");
        breakfastLabel.setHorizontalAlignment(SwingConstants.CENTER);
        breakfastLabel.setForeground(Color.WHITE);
        breakfastLabel.setFont(ToolClass.largerBoldHeadingFont);
        breakfastLabel.setVisible(true);

        breakfastRestaurants = new JComboBox(restaurants);
        breakfastRestaurants.setVisible(true);
        breakfastRestaurants.setBackground(Color.WHITE);
        breakfastRestaurants.setForeground(ToolClass.fgcuBlue);
        breakfastRestaurants.addActionListener(boxHandler);

        breakfastFoodItems = new JComboBox(ent);
        breakfastFoodItems.setBackground(Color.WHITE);
        breakfastFoodItems.setForeground(ToolClass.fgcuGreen);
        breakfastFoodItems.addActionListener(boxHandler);
//    queryDriver(bFoodState, breakfastRestaurantName, breakfastFoodName, bFoodCal, bFoodFatCal, bFoodProtein, bFoodCarb, bFoodPoints);

        breakfastSideItems = new JComboBox(side);
        breakfastSideItems.setBackground(Color.WHITE);
        breakfastSideItems.setForeground(ToolClass.fgcuGreen);
        breakfastSideItems.addActionListener(boxHandler);
//    queryDriver(bSideStatebreakfastRestaurantName, breakfastSideName, bSideCal, bSideFatCal, bSideProtein, bSideCarb, bSidePoints);

        breakfastDrinkItems = new JComboBox(drink);
        breakfastDrinkItems.setBackground(Color.WHITE);
        breakfastDrinkItems.setForeground(ToolClass.fgcuBlue);
        breakfastDrinkItems.addActionListener(boxHandler);

        breakfastPanel.add(breakfastLabel);
        breakfastPanel.add(breakfastRestaurants);
        breakfastPanel.add(breakfastFoodItems);
        breakfastPanel.add(breakfastSideItems);
        breakfastPanel.add(breakfastDrinkItems);

        breakfastRestaurantName = breakfastRestaurants.getSelectedItem().toString();
        breakfastFoodName = breakfastFoodItems.getSelectedItem().toString();
        breakfastDrinkName = breakfastDrinkItems.getSelectedItem().toString();
        breakfastSideName = breakfastSideItems.getSelectedItem().toString();
    }
    

    
    public ResultSet stephensQuery(String restName, String foodName, Statement FoodState, Connection FoodItemConnect, ResultSet FoodSet) {
      String foodQuery = String.format("SELECT total_calories, total_fat_cal, total_protein, total_carbs," +
          " points FROM food_item WHERE restaurant = '%s' " +
          "AND item_name = '%s'", restName, foodName);
      
      try {
        FoodState = FoodItemConnect.createStatement();
        FoodSet = FoodState.executeQuery(foodQuery);
        return FoodSet;
      } catch (SQLException e) {
        e.printStackTrace();
        return null;
      }
    }
    
//    public void queryAss(String restName, String foodName, Statement FoodState, Connection FoodItemConnect, ResultSet FoodSet, int cal, int fatCals, int protein, int carbs, int points){
//        String bFoodQuery = String.format("SELECT total_calories, total_fat_cal, total_protein, total_carbs," +
//                " points FROM food_item WHERE restaurant = '%s' " +
//                "AND item_name = '%s'", restName, foodName);
//
//        try {
//            FoodState = FoodItemConnect.createStatement();
//            FoodSet = FoodState.executeQuery(bFoodQuery);
//
//            cal = FoodSet.getInt("total_calories");
//            fatCals = FoodSet.getInt("total_fat_cal");
//            protein = FoodSet.getInt("total_protein");
//            carbs = FoodSet.getInt("total_carbs");
//            points = FoodSet.getInt("points");
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        System.out.printf("%d, %d, %d, %d, %d\n", cal, fatCals, protein, carbs, points);
//    }
    
    public void breakfastNutritionItems() {

        breakfastNutrition = new JPanel();
        breakfastNutrition.setLayout(new GridLayout(5,1));
        breakfastNutrition.setVisible(true);
        breakfastNutrition.setBorder(ToolClass.newCompound);
        breakfastNutrition.setBackground(ToolClass.fgcuBlue);


        breakfastCalories = new JLabel("<HTML><U>Total Calories:</U></HTML> ");
        breakfastCalories.setForeground(Color.WHITE);
        breakfastCalories.setFont(ToolClass.nutritionPanelFont);
        breakfastCalories.setHorizontalTextPosition(SwingConstants.LEFT);

        breakfastFatCalories = new JLabel("<HTML><U>Total Fat Calories: </U></HTML>");
        breakfastFatCalories.setForeground(Color.WHITE);
        breakfastFatCalories.setFont(ToolClass.nutritionPanelFont);

        breakfastCarbs = new JLabel("<HTML><U>Total Carbs:</U></HTML>");
        breakfastCarbs = new JLabel("<HTML><U>Total Carbs:</U> " + bFoodCarb + "g </HTML>");
        breakfastCarbs.setForeground(Color.WHITE);
        breakfastCarbs.setFont(ToolClass.nutritionPanelFont);

        breakfastProtein = new JLabel("<HTML><U>Total Protein:</U></HTML>");
        breakfastProtein.setForeground(Color.WHITE);
        breakfastProtein.setFont(ToolClass.nutritionPanelFont);

        breakfastFat = new JLabel("<HTML><U>Total Fat:</U></HTML>");
        breakfastFat.setForeground(Color.WHITE);
        breakfastFat.setFont(ToolClass.nutritionPanelFont);

        breakfastNutrition.add(breakfastCalories);
        breakfastNutrition.add(breakfastFatCalories);
        breakfastNutrition.add(breakfastCarbs);
        breakfastNutrition.add(breakfastProtein);
        breakfastNutrition.add(breakfastFat);

    }

    public void lunchItems() {

    /* Lunch Panel & Items */
        lunchPanel = new JPanel();
        lunchPanel.setLayout(new GridLayout(5, 1));
        lunchPanel.setVisible(true);
        lunchPanel.setPreferredSize(innerPanelDimension);
        lunchPanel.setBackground(ToolClass.fgcuGreen);

        lunchLabel = new JLabel("Lunch");
        lunchLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lunchLabel.setForeground(Color.WHITE);
        lunchLabel.setFont(ToolClass.largerBoldHeadingFont);

        lunchRestaurants = new JComboBox(restaurants);
        lunchRestaurants.addActionListener(boxHandler);
        lunchRestaurants.setVisible(true);
        lunchRestaurants.setBackground(Color.WHITE);
        lunchRestaurants.setForeground(ToolClass.fgcuBlue);

        lunchFoodItems = new JComboBox(ent);
        lunchFoodItems.setBackground(Color.WHITE);
        lunchFoodItems.setForeground(ToolClass.fgcuGreen);
        lunchFoodItems.addActionListener(boxHandler);

        lunchSideItems = new JComboBox(side);
        lunchSideItems.setBackground(Color.WHITE);
        lunchSideItems.setForeground(ToolClass.fgcuGreen);
        lunchSideItems.addActionListener(boxHandler);

        lunchDrinkItems = new JComboBox(drink);
        lunchDrinkItems.setBackground(Color.WHITE);
        lunchDrinkItems.setForeground(ToolClass.fgcuBlue);
        lunchDrinkItems.addActionListener(boxHandler);

        lunchPanel.add(lunchLabel);
        lunchPanel.add(lunchRestaurants);
        lunchPanel.add(lunchFoodItems);
        lunchPanel.add(lunchSideItems);
        lunchPanel.add(lunchDrinkItems);
    }

    public void lunchNutritionItems() {

        String bFoodQuery = String.format("SELECT total_calories, total_fat_cal, total_protein, total_carbs," +
                " points FROM food_item WHERE restaurant = '%s' " +
                "AND item_name = '%s'", breakfastRestaurantName, breakfastFoodName);

//        try {
//            lFoodState = bFoodItemsConnect.createStatement();
//            lFoodSet = bFoodState.executeQuery(bFoodQuery);
//
//            lFoodCal = bFoodSet.getInt("total_calories");
//            lFoodFatCal = bFoodSet.getInt("total_fat_cal");
//            lFoodProtein = bFoodSet.getInt("total_protein");
//            lFoodCarb = bFoodSet.getInt("total_carbs");
//            lFoodPoints = bFoodSet.getInt("points");
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        
        lunchNutrition = new JPanel();
        lunchNutrition.setLayout(new GridLayout(5,1));
        lunchNutrition.setVisible(true);
        lunchNutrition.setBackground(ToolClass.fgcuBlue);
        lunchNutrition.setBorder(ToolClass.newCompound);

        lunchCalories = new JLabel("<HTML><U>Total Calories: </U></HTML> ");
        lunchCalories.setForeground(Color.WHITE);
        lunchCalories.setFont(ToolClass.nutritionPanelFont);
        lunchCalories.setHorizontalTextPosition(SwingConstants.LEFT);

        lunchFatCalories = new JLabel("<HTML><U>Total Fat Calories: </U></HTML>");
        lunchFatCalories.setForeground(Color.WHITE);
        lunchFatCalories.setFont(ToolClass.nutritionPanelFont);

        lunchCarbs = new JLabel("<HTML><U>Total Carbs: </U></HTML>");
        lunchCarbs.setForeground(Color.WHITE);
        lunchCarbs.setFont(ToolClass.nutritionPanelFont);

        lunchProtein = new JLabel("<HTML><U>Total Protein: </U></HTML>");
        lunchProtein.setForeground(Color.WHITE);
        lunchProtein.setFont(ToolClass.nutritionPanelFont);

        lunchFat = new JLabel("<HTML><U>Total Fat: </U></HTML>");
        lunchFat.setForeground(Color.WHITE);
        lunchFat.setFont(ToolClass.nutritionPanelFont);

        lunchNutrition.add(lunchCalories);
        lunchNutrition.add(lunchFatCalories);
        lunchNutrition.add(lunchCarbs);
        lunchNutrition.add(lunchProtein);
        lunchNutrition.add(lunchFat);
    }

    public void dinnerItems() {

    /* Dinner Panel & Items */
        dinnerPanel = new JPanel();
        dinnerPanel.setLayout(new GridLayout(5, 1));
        dinnerPanel.setVisible(true);
        dinnerPanel.setPreferredSize(innerPanelDimension);
        dinnerPanel.setBackground(ToolClass.fgcuGreen);

        dinnerLabel = new JLabel("Dinner");
        dinnerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        dinnerLabel.setForeground(Color.WHITE);
        dinnerLabel.setFont(ToolClass.largerBoldHeadingFont);

        dinnerRestaurants = new JComboBox(restaurants);
        dinnerRestaurants.addActionListener(boxHandler);
        dinnerRestaurants.setVisible(true);
        dinnerRestaurants.setBackground(Color.WHITE);
        dinnerRestaurants.setForeground(ToolClass.fgcuBlue);

        dinnerFoodItems = new JComboBox(ent);
        dinnerFoodItems.setBackground(Color.WHITE);
        dinnerFoodItems.setForeground(ToolClass.fgcuGreen);

        dinnerSideItems = new JComboBox(side);
        dinnerSideItems.setBackground(Color.WHITE);
        dinnerSideItems.setForeground(ToolClass.fgcuGreen);

        dinnerDrinkItems = new JComboBox(drink);
        dinnerDrinkItems.setBackground(Color.WHITE);
        dinnerDrinkItems.setForeground(ToolClass.fgcuBlue);

        dinnerPanel.add(dinnerLabel);
        dinnerPanel.add(dinnerRestaurants);
        dinnerPanel.add(dinnerFoodItems);
        dinnerPanel.add(dinnerSideItems);
        dinnerPanel.add(dinnerDrinkItems);
    }

    private JLabel panelTheme(String txt) {
        JLabel label = new JLabel("<HTML><U>" + txt + "</U></HTML> " + "g");
        label.setForeground(Color.WHITE);
        label.setFont(ToolClass.nutritionPanelFont);
        return label;
    }

    public void dinnerNutritionItems() {

        dinnerNutrition = new JPanel();
        dinnerNutrition.setLayout(new GridLayout(5,1));
        dinnerNutrition.setVisible(true);
        dinnerNutrition.setBackground(ToolClass.fgcuBlue);
        dinnerNutrition.setBorder(ToolClass.newCompound);

        dinnerCalories = panelTheme("Total Calories:");
        dinnerCalories.setHorizontalTextPosition(SwingConstants.LEFT);

        dinnerFatCalories = panelTheme("Total Fat Calories:");
        dinnerCarbs = panelTheme("Total Carbs:");
        dinnerProtein = panelTheme("Total Protein:");
        dinnerFat = panelTheme("Total Fat:");

        dinnerNutrition.add(dinnerCalories);
        dinnerNutrition.add(dinnerFatCalories);
        dinnerNutrition.add(dinnerCarbs);
        dinnerNutrition.add(dinnerProtein);
        dinnerNutrition.add(dinnerFat);

    }

    public void snackItems() {

    /* Snack Panel & Items */
        snackPanel = new JPanel();
        snackPanel.setLayout(new GridLayout(3, 1));
        snackPanel.setVisible(true);
        snackPanel.setSize(innerPanelDimension);
        snackPanel.setBackground(ToolClass.fgcuGreen);

        snackLabel = new JLabel("Snack");
        snackLabel.setSize(50, 20);
        snackLabel.setHorizontalAlignment(SwingConstants.CENTER);
        // snackLabel.setOpaque(true);
        snackLabel.setBackground(Color.CYAN);
        snackLabel.setForeground(Color.WHITE);
        snackLabel.setFont(ToolClass.largerBoldHeadingFont);

        snackRestaurants = new JComboBox(sRestaurants);
        snackRestaurants.addActionListener(boxHandler);
        snackRestaurants.setVisible(true);
        snackRestaurants.setBackground(Color.WHITE);
        snackRestaurants.setForeground(ToolClass.fgcuBlue);

        snackItems = new JComboBox(new String[0]);
        snackItems.setBackground(Color.WHITE);
        snackItems.setForeground(ToolClass.fgcuGreen);

        snackPanel.add(snackLabel);
        snackPanel.add(snackRestaurants);
        snackPanel.add(snackItems);
    }

    public void snackNutritionItems() {

        snackNutrition = new JPanel();
        snackNutrition.setLayout(new GridLayout(5,1));
        snackNutrition.setVisible(true);
        snackNutrition.setBackground(ToolClass.fgcuBlue);
        snackNutrition.setBorder(ToolClass.newCompound);

        snackCalories = new JLabel("<HTML><U>Total Calories: </U></HTML> ");
        snackCalories.setForeground(Color.WHITE);
        snackCalories.setFont(ToolClass.nutritionPanelFont);
        snackCalories.setHorizontalTextPosition(SwingConstants.LEFT);

        snackFatCalories = new JLabel("<HTML><U>Total Fat Calories: </U></HTML>");
        snackFatCalories.setForeground(Color.WHITE);
        snackFatCalories.setFont(ToolClass.nutritionPanelFont);

        snackCarbs = new JLabel("<HTML><U>Total Carbs: </U></HTML>");
        snackCarbs.setForeground(Color.WHITE);
        snackCarbs.setFont(ToolClass.nutritionPanelFont);

        snackProtein = new JLabel("<HTML><U>Total Protein: </U></HTML>");
        snackProtein.setForeground(Color.WHITE);
        snackProtein.setFont(ToolClass.nutritionPanelFont);

        snackFat = new JLabel("<HTML><U>Total Fat: </U></HTML>");
        snackFat.setForeground(Color.WHITE);
        snackFat.setFont(ToolClass.nutritionPanelFont);

        snackNutrition.add(snackCalories);
        snackNutrition.add(snackFatCalories);
        snackNutrition.add(snackCarbs);
        snackNutrition.add(snackProtein);
        snackNutrition.add(snackFat);
    }

    public void totalNutritionItems() {

        totalNutrition = new JPanel();
        totalNutrition.setLayout(new GridLayout(5,1));
        totalNutrition.setVisible(true);
        totalNutrition.setBackground(ToolClass.fgcuBlue);
        totalNutrition.setBorder(ToolClass.whiteLine);

        totalCalories = new JLabel("<HTML><U>Total Calories:</U></HTML> ");
        totalCalories.setForeground(Color.WHITE);
        totalCalories.setFont(ToolClass.nutritionPanelFont);
        totalCalories.setHorizontalTextPosition(SwingConstants.LEFT);

        totalFatCalories = new JLabel("<HTML><U>Total Fat Calories:</U></HTML> " + "g");
        totalFatCalories.setForeground(Color.WHITE);
        totalFatCalories.setFont(ToolClass.nutritionPanelFont);

        totalCarbs = new JLabel("<HTML><U>Total Carbs:</U></HTML> " + "g");
        totalCarbs.setForeground(Color.WHITE);
        totalCarbs.setFont(ToolClass.nutritionPanelFont);

        totalProtein = new JLabel("<HTML><U>Total Protein:</U></HTML> " + "g");
        totalProtein.setForeground(Color.WHITE);
        totalProtein.setFont(ToolClass.nutritionPanelFont);

        totalFat = new JLabel("<HTML><U>Total Fat:</U></HTML> " + "g");
        totalFat.setForeground(Color.WHITE);
        totalFat.setFont(ToolClass.nutritionPanelFont);

        totalNutrition.add(totalCalories);
        totalNutrition.add(totalFatCalories);
        totalNutrition.add(totalCarbs);
        totalNutrition.add(totalProtein);
        totalNutrition.add(totalFat);
    }

    public void submit() {

        submitBtn = new JButton("Plan Meal");
        submitBtn.setVisible(true);
//        submitBtn.setForeground(Color.WHITE);
//        submitBtn.setBackground(Color.BLUE);
//        submitBtn.setOpaque(true);


        ActionListener sumbitButtonHandler = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        };
    }

    public void dBConnect() {

        host = ToolClass.tylerPath;
        try {
            bFoodItemsConnect = DriverManager.getConnection(ToolClass.tylerPath);
//            dbDrive = bFoodItemsConnect.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


////  public void queryDriver(Statement state, String rName, String foodName, int cal, int fatCal, int protein, int carb, int points){
//public void breakfastFoodQuery(){
//
//}
