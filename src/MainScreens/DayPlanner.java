package MainScreens;

import MainScreens.MainMenu.CalendarDemo;
import Utility.ToolClass;
import sun.applet.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DayPlanner extends JFrame {

    public static int databaseKey;

    /*
     * private local variables
     */
    JButton submitBtn, deleteBtn;
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
    String[] ent, side, drink = new String[3]; // temporary array to hold menu items

    // items and load into combo-boxes
    Statement bFoodState, bSideState, bDrinkState;
    Statement lFoodState, lSideState, lDrinkState;
    Statement dFoodState, dSideState, dDrinkState;
    Statement sFoodState;


    ResultSet bFoodSet, bSideSet, bDrinkSet;
    ResultSet lFoodSet, lSideSet, lDrinkSet;
    ResultSet dFoodSet, dSideSet, dDrinkSet;
    ResultSet sFoodSet;
    ResultSet foodSet, fNutriSet, sNutriSet, dNutriSet;


    String[] restaurants = {"Einstein Bros. Bagels", "Papa Johns", "Brahma Express", "Chick-Fil-A", "Jamba Juice",
            "Starbucks"};
    String[] sRestaurants = {"Jamba Juice", "Starbucks"};
    String[] einEnt = {"Plain Bagel", "Cheesy Bacon Club", "Santa Fe Wrap"};
    String[] einSide = {"Blueberry Muffin", "Brownie", "Cookie"};
    String[] einDrink = {"Coffee", "Orange Juice", "Chocolate Milk"};
    String[] papaEnt = {"Cheese Pizza", "Pepperoni Pizza", "Hot Wings"};
    String[] papaSide = {"Cookie Slice", "Brownie", "Breadsticks x2"};
    String[] papaDrink = {"Water", "Coca-Cola", "Sprite"};
    String[] brEnt = {"Teriyaki Chicken Bowl", "Dunk City Roll", "Spicy Tuna Roll"};
    String[] brSide = {"Egg Roll", "Miso Soup", "Seaweed Salad"}; 
    String[] brDrink = {"Water", "Coca-Cola", "Sprite"}; 
    String[] chickEnt = {"Chicken Sandwich", "8-Piece Chicken Nuggets", "Chicken Salad"};
    String[] chickSide = {"Waffle Fries", "Cookie", "Fruit Cup"};
    String[] chickDrink = {"Water", "Lemonade", "Iced Tea"};
    String[] jambaDrink = {"Mango Smoothie", "Strawberry Smoothie", "Chocolate Banana Smoothie"};
    String[] starDrink = {"Vanilla Bean Frappuccino", "Coffee", "Raspberry Iced Tea"};

    String breakfastRestaurantName, breakfastDrinkName, breakfastFoodName, breakfastSideName;
    int bFoodCal, bFoodFatCal, bFoodCarb, bFoodProtein, bFoodPoints, bSideCal, bSideFatCal, bSideCarb, bSideProtein, bSidePoints,
            lFoodCal, lFoodFatCal, lFoodCarb, lFoodProtein, lFoodPoints, dFoodCal, dFoodFatCal, dFoodCarb, dFoodProtein, dFoodPoints,
            sFoodCal, sFoodFatCal, sFoodCarb, sFoodProtein, sFoodPoints;


    BoxHandler boxHandler = new BoxHandler();

    public DayPlanner(String dayText) throws SQLException, FileNotFoundException { // dayText is the day number
        super("Meal Plan for " + CalendarDemo.capitalize(CalendarDemo.monthNames[CalendarDemo.month]) + ", " + dayText + " " + CalendarDemo.year);

        databaseKey = Integer.parseInt(CalendarDemo.datePrimaryKey);
        
        System.out.println(databaseKey);

        ent = einEnt;
        side = einSide;
        drink = einDrink;

        dayPlannerPanel = new JPanel();
        setSize(800, 800); // sets the size of the frame
        setLocationRelativeTo(null); // sets the location of the frame on the screen
        dayPlannerPanel.setLayout(null);

        try {
            String foodQuery = String.format("SELECT * FROM '%d' WHERE date = '%d' ", LogInScreen.universityID, Integer.parseInt(CalendarDemo.datePrimaryKey));
            Statement stmt = LogInScreen.studentInfoCon.createStatement();
            foodSet = stmt.executeQuery(foodQuery);            
        } catch (SQLException e) {
            foodSet = null;
            e.printStackTrace();
        }

        breakfastItems(foodSet);
        
        //get nutrition facts for what is loaded into the comboboxes when dayplanner starts
        fNutriSet = stephensQuery(breakfastRestaurants.getSelectedItem().toString(),
            breakfastFoodItems.getSelectedItem().toString(), LogInScreen.studentInfoCon);
        sNutriSet = stephensQuery(breakfastRestaurants.getSelectedItem().toString(),
            breakfastSideItems.getSelectedItem().toString(), LogInScreen.studentInfoCon);
        dNutriSet = stephensQuery(breakfastRestaurants.getSelectedItem().toString(),
            breakfastDrinkItems.getSelectedItem().toString(), LogInScreen.studentInfoCon);
        
        breakfastNutritionItems(fNutriSet, sNutriSet, dNutriSet);
        lunchItems(foodSet);
        
        fNutriSet = stephensQuery(lunchRestaurants.getSelectedItem().toString(),
            lunchFoodItems.getSelectedItem().toString(), LogInScreen.studentInfoCon);
        sNutriSet = stephensQuery(lunchRestaurants.getSelectedItem().toString(),
            lunchSideItems.getSelectedItem().toString(), LogInScreen.studentInfoCon);
        dNutriSet = stephensQuery(lunchRestaurants.getSelectedItem().toString(),
            lunchDrinkItems.getSelectedItem().toString(), LogInScreen.studentInfoCon);
        
        lunchNutritionItems(fNutriSet, sNutriSet, dNutriSet);
        dinnerItems(foodSet);
        
        fNutriSet = stephensQuery(dinnerRestaurants.getSelectedItem().toString(),
            dinnerFoodItems.getSelectedItem().toString(), LogInScreen.studentInfoCon);
        sNutriSet = stephensQuery(dinnerRestaurants.getSelectedItem().toString(),
            dinnerSideItems.getSelectedItem().toString(), LogInScreen.studentInfoCon);
        dNutriSet = stephensQuery(dinnerRestaurants.getSelectedItem().toString(),
            dinnerDrinkItems.getSelectedItem().toString(), LogInScreen.studentInfoCon);
        
        dinnerNutritionItems(fNutriSet, sNutriSet, dNutriSet);
        snackItems(foodSet);
        
        dNutriSet = stephensQuery(snackRestaurants.getSelectedItem().toString(),
            snackItems.getSelectedItem().toString(), LogInScreen.studentInfoCon);
        
        snackNutritionItems(dNutriSet);
        totalNutritionItems(foodSet);
        
        bFoodSet = stephensQuery(breakfastRestaurants.getSelectedItem().toString(),
            breakfastFoodItems.getSelectedItem().toString(), LogInScreen.studentInfoCon);
        bSideSet = stephensQuery(breakfastRestaurants.getSelectedItem().toString(),
            breakfastSideItems.getSelectedItem().toString(), LogInScreen.studentInfoCon);
        bDrinkSet = stephensQuery(breakfastRestaurants.getSelectedItem().toString(),
            breakfastDrinkItems.getSelectedItem().toString(), LogInScreen.studentInfoCon);
        lFoodSet = stephensQuery(lunchRestaurants.getSelectedItem().toString(),
            lunchFoodItems.getSelectedItem().toString(), LogInScreen.studentInfoCon);
        lSideSet = stephensQuery(lunchRestaurants.getSelectedItem().toString(),
            lunchSideItems.getSelectedItem().toString(), LogInScreen.studentInfoCon);
        lDrinkSet = stephensQuery(lunchRestaurants.getSelectedItem().toString(),
            lunchDrinkItems.getSelectedItem().toString(), LogInScreen.studentInfoCon);
        dFoodSet = stephensQuery(dinnerRestaurants.getSelectedItem().toString(),
            dinnerFoodItems.getSelectedItem().toString(), LogInScreen.studentInfoCon);
        dSideSet = stephensQuery(dinnerRestaurants.getSelectedItem().toString(),
            dinnerSideItems.getSelectedItem().toString(), LogInScreen.studentInfoCon);
        dDrinkSet = stephensQuery(dinnerRestaurants.getSelectedItem().toString(),
            dinnerDrinkItems.getSelectedItem().toString(), LogInScreen.studentInfoCon);

        ent = einEnt;
        side = einSide;
        drink = einDrink;

        submit(); //create submit button
        delete(); //create delete button
        
        //staticly set position of all dayplanner items
        submitBtn.setBounds(0, 0, 200, 50);
        deleteBtn.setBounds(210, 0, 200, 50);
        totalNutrition.setBounds(285, 70, 225, 70);
        breakfastPanel.setBounds(20, 120, 225, 225);
        breakfastNutrition.setBounds(20, 345, 225, 70);
        lunchPanel.setBounds(560, 120, 225, 225);
        lunchNutrition.setBounds(560, 345, 225, 70);
        snackPanel.setBounds(310, 120, 185, 170);
        snackNutrition.setBounds(310, 290, 185, 70);
        dinnerPanel.setBounds(300, 400, 225, 225);
        dinnerNutrition.setBounds(300, 625, 225, 70);

        // Addition of contents to dayPlannerPanel
        getContentPane().add(dayPlannerPanel);
        dayPlannerPanel.add(submitBtn);
        dayPlannerPanel.add(deleteBtn);
        dayPlannerPanel.add(totalNutrition);
        dayPlannerPanel.add(breakfastPanel);
        dayPlannerPanel.add(breakfastNutrition);
        dayPlannerPanel.add(lunchPanel);
        dayPlannerPanel.add(lunchNutrition);
        dayPlannerPanel.add(dinnerPanel);
        dayPlannerPanel.add(dinnerNutrition);
        dayPlannerPanel.add(snackPanel);
        dayPlannerPanel.add(snackNutrition);

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
                try {
                  MainMenu menu = new MainMenu();
                } catch (FileNotFoundException e1) {
                  e1.printStackTrace();
                } catch (SQLException e1) {
                  e1.printStackTrace();
                }
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

    
    /*
     * This class is very long, it may not be super efficient, or even the correct way to 
     * implement it, but it works.
     */
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
                  //loads an empty string for food and side comboboxes because you can only get a drink from jamba 
                    ent = new String[0];
                    side = new String[0];
                    drink = jambaDrink;
                } else if (r.equals(restaurants[5])) {
                  //or starbucks
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

                bFoodSet = stephensQuery(breakfastRestaurants.getSelectedItem().toString(),
                    breakfastFoodItems.getSelectedItem().toString(), LogInScreen.studentInfoCon);
                bSideSet = stephensQuery(breakfastRestaurants.getSelectedItem().toString(),
                    breakfastSideItems.getSelectedItem().toString(), LogInScreen.studentInfoCon);
                bDrinkSet = stephensQuery(breakfastRestaurants.getSelectedItem().toString(),
                    breakfastDrinkItems.getSelectedItem().toString(), LogInScreen.studentInfoCon);
                try {
                bFoodCal = bFoodSet.getInt("total_calories") + bSideSet.getInt("total_calories") + bDrinkSet.getInt("total_calories");
                bFoodFatCal = bFoodSet.getInt("total_fat_cal") + bSideSet.getInt("total_fat_cal") + bDrinkSet.getInt("total_fat_cal");
                bFoodCarb = bFoodSet.getInt("total_carbs") + bSideSet.getInt("total_carbs") + bDrinkSet.getInt("total_carbs");
                bFoodProtein = bFoodSet.getInt("total_protein") + bSideSet.getInt("total_protein") + bDrinkSet.getInt("total_protein");
                bFoodPoints = bFoodSet.getInt("points") + bSideSet.getInt("points") + bDrinkSet.getInt("points");
                } catch (SQLException ex) {
                  ex.printStackTrace();
                }
                breakfastCalories.setText("<HTML><U>Total Calories: </U>" + bFoodCal + "</HTML> ");
                breakfastFatCalories.setText("<HTML><U>Total Fat Calories: </U>" + bFoodFatCal + "</HTML>");
                breakfastCarbs.setText("<HTML><U>Total Carbs: </U>" + bFoodCarb + " g</HTML>");
                breakfastProtein.setText("<HTML><U>Total Protein: </U>" + bFoodProtein + " g</HTML>");
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
                  //loads an empty string for food and side comboboxes because you can only get a drink from jamba 
                    ent = new String[0];
                    side = new String[0];
                    drink = jambaDrink;
                } else if (r.equals(restaurants[5])) {
                  //or starbucks
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

                lFoodSet = stephensQuery(lunchRestaurants.getSelectedItem().toString(),
                    lunchFoodItems.getSelectedItem().toString(), LogInScreen.studentInfoCon);
                lSideSet = stephensQuery(lunchRestaurants.getSelectedItem().toString(),
                    lunchSideItems.getSelectedItem().toString(), LogInScreen.studentInfoCon);
                lDrinkSet = stephensQuery(lunchRestaurants.getSelectedItem().toString(),
                    lunchSideItems.getSelectedItem().toString(), LogInScreen.studentInfoCon);
                try {
                lFoodCal = lFoodSet.getInt("total_calories") + lSideSet.getInt("total_calories") + lDrinkSet.getInt("total_calories");
                lFoodFatCal = lFoodSet.getInt("total_fat_cal") + lSideSet.getInt("total_fat_cal") + lDrinkSet.getInt("total_fat_cal");
                lFoodCarb = lFoodSet.getInt("total_carbs") + lSideSet.getInt("total_carbs") + lDrinkSet.getInt("total_carbs");
                lFoodProtein = lFoodSet.getInt("total_protein") + lSideSet.getInt("total_protein") + lDrinkSet.getInt("total_protein");
                lFoodPoints = lFoodSet.getInt("points") + lSideSet.getInt("points") + lDrinkSet.getInt("points");
                } catch (SQLException ex) {
                  ex.printStackTrace();
                }
                lunchCalories.setText("<HTML><U>Total Calories: </U>" + lFoodCal + "</HTML> ");
                lunchFatCalories.setText("<HTML><U>Total Fat Calories: </U>" + lFoodFatCal + "</HTML>");
                lunchCarbs.setText("<HTML><U>Total Carbs: </U>" + lFoodCarb + " g</HTML>");
                lunchProtein.setText("<HTML><U>Total Protein: </U>" + lFoodProtein + " g</HTML>");
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
                  //loads an empty string for food and side comboboxes because you can only get a drink from jamba 
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

                dFoodSet = stephensQuery(dinnerRestaurants.getSelectedItem().toString(),
                    dinnerFoodItems.getSelectedItem().toString(), LogInScreen.studentInfoCon);
                dSideSet = stephensQuery(dinnerRestaurants.getSelectedItem().toString(),
                    dinnerSideItems.getSelectedItem().toString(), LogInScreen.studentInfoCon);
                dDrinkSet = stephensQuery(dinnerRestaurants.getSelectedItem().toString(),
                    dinnerDrinkItems.getSelectedItem().toString(), LogInScreen.studentInfoCon);
                try {
                dFoodCal = dFoodSet.getInt("total_calories") + dSideSet.getInt("total_calories") + dDrinkSet.getInt("total_calories");
                dFoodFatCal = dFoodSet.getInt("total_fat_cal") + dSideSet.getInt("total_fat_cal") + dDrinkSet.getInt("total_fat_cal");
                dFoodCarb = dFoodSet.getInt("total_carbs") + dSideSet.getInt("total_carbs") + dDrinkSet.getInt("total_carbs");
                dFoodProtein = dFoodSet.getInt("total_protein") + dSideSet.getInt("total_protein") + dDrinkSet.getInt("total_protein");
                dFoodPoints = dFoodSet.getInt("points") + dSideSet.getInt("points") + dDrinkSet.getInt("points");
                } catch (SQLException ex) {
                  ex.printStackTrace();
                }
                dinnerCalories.setText("<HTML><U>Total Calories: </U>" + dFoodCal + "</HTML> ");
                dinnerFatCalories.setText("<HTML><U>Total Fat Calories: </U>" + dFoodFatCal + "</HTML>");
                dinnerCarbs.setText("<HTML><U>Total Carbs: </U>" + dFoodCarb + " g</HTML>");
                dinnerProtein.setText("<HTML><U>Total Protein: </U>" + dFoodProtein + " g</HTML>");
                updateTotalNutrition();

            } else if (e.getSource() == snackRestaurants) {
                JComboBox sr = (JComboBox) e.getSource();
                String r = (String) sr.getSelectedItem();
                // update snack combo boxes
                snackPanel.remove(snackItems);

                //snack only has one combobox so only need to update one
                if (r.equals(sRestaurants[0])) {
                    side = jambaDrink;
                } else if (r.equals(sRestaurants[1])) {
                    side = starDrink;
                }
                snackItems = new JComboBox(side);
                snackItems.addActionListener(boxHandler);
                snackPanel.add(snackItems);

                sFoodSet = stephensQuery(snackRestaurants.getSelectedItem().toString(),
                    snackItems.getSelectedItem().toString(), LogInScreen.studentInfoCon);
                try {
                sFoodCal = sFoodSet.getInt("total_calories");
                sFoodFatCal = sFoodSet.getInt("total_fat_cal");
                sFoodCarb = sFoodSet.getInt("total_carbs");
                sFoodProtein = sFoodSet.getInt("total_protein");
                sFoodPoints = sFoodSet.getInt("points");
                } catch (SQLException ex) {
                  ex.printStackTrace();
                }
                snackCalories.setText("<HTML><U>Total Calories: </U>" + sFoodCal + "</HTML> ");
                snackFatCalories.setText("<HTML><U>Total Fat Calories: </U>" + sFoodFatCal + "</HTML>");
                snackCarbs.setText("<HTML><U>Total Carbs: </U>" + sFoodCarb + " g</HTML>");
                snackProtein.setText("<HTML><U>Total Protein: </U>" + sFoodProtein + " g</HTML>");
                updateTotalNutrition();
                //---------------------------here starts food, side, and drink item listeners-------------------------
            } else if (e.getSource().equals(breakfastFoodItems)) {
              //if no food set is created, it creates one
                try {
                  //subtract the nutrition info that was there previously so it will track it dynamically
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
                    breakfastFoodItems.getSelectedItem().toString(), LogInScreen.studentInfoCon);
                try {
                  //add the new nutrition info so nutrition info updates dynamically
                    bFoodCal += bFoodSet.getInt("total_calories");
                    bFoodFatCal += bFoodSet.getInt("total_fat_cal");
                    bFoodCarb += bFoodSet.getInt("total_carbs");
                    bFoodProtein += bFoodSet.getInt("total_protein");
                    bFoodPoints += bFoodSet.getInt("points");
                    breakfastCalories.setText("<HTML><U>Total Calories: </U>" + bFoodCal + "</HTML> ");
                    breakfastFatCalories.setText("<HTML><U>Total Fat Calories: </U>" + bFoodFatCal + "</HTML>");
                    breakfastCarbs.setText("<HTML><U>Total Carbs: </U>" + bFoodCarb + " g</HTML>");
                    breakfastProtein.setText("<HTML><U>Total Protein: </U>" + bFoodProtein + " g</HTML>");
                    updateTotalNutrition();

                } catch (SQLException exc) {
                    System.err.println("Didnt work");
                }
            } else if (e.getSource().equals(breakfastSideItems)) {
                try {
                //subtract the nutrition info that was there previously so it will track it dynamically
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
                    breakfastSideItems.getSelectedItem().toString(), LogInScreen.studentInfoCon);
                try {
                //add the new nutrition info so nutrition info updates dynamically
                    bFoodCal += bSideSet.getInt("total_calories");
                    bFoodFatCal += bSideSet.getInt("total_fat_cal");
                    bFoodCarb += bSideSet.getInt("total_carbs");
                    bFoodProtein += bSideSet.getInt("total_protein");
                    bFoodPoints += bSideSet.getInt("points");
                    breakfastCalories.setText("<HTML><U>Total Calories: </U>" + bFoodCal + "</HTML> ");
                    breakfastFatCalories.setText("<HTML><U>Total Fat Calories: </U>" + bFoodFatCal + "</HTML>");
                    breakfastCarbs.setText("<HTML><U>Total Carbs: </U>" + bFoodCarb + " g</HTML>");
                    breakfastProtein.setText("<HTML><U>Total Protein: </U>" + bFoodProtein + " g</HTML>");
                    updateTotalNutrition();

                } catch (SQLException exc) {
                    System.err.println("Didnt work");
                }
            } else if (e.getSource().equals(breakfastDrinkItems)) {
                try {
                //subtract the nutrition info that was there previously so it will track it dynamically
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
                    breakfastDrinkItems.getSelectedItem().toString(), LogInScreen.studentInfoCon);
                try {
                //add the new nutrition info so nutrition info updates dynamically
                    bFoodCal += bDrinkSet.getInt("total_calories");
                    bFoodFatCal += bDrinkSet.getInt("total_fat_cal");
                    bFoodCarb += bDrinkSet.getInt("total_carbs");
                    bFoodProtein += bDrinkSet.getInt("total_protein");
                    bFoodPoints += bDrinkSet.getInt("points");
                    breakfastCalories.setText("<HTML><U>Total Calories: </U>" + bFoodCal + "</HTML> ");
                    breakfastFatCalories.setText("<HTML><U>Total Fat Calories: </U>" + bFoodFatCal + "</HTML>");
                    breakfastCarbs.setText("<HTML><U>Total Carbs: </U>" + bFoodCarb + " g</HTML>");
                    breakfastProtein.setText("<HTML><U>Total Protein: </U>" + bFoodProtein + " g</HTML>");
                    updateTotalNutrition();

                } catch (SQLException exc) {
                    System.err.println("Didnt work");
                }
                // --------------------lunch------------------------------------
            } else if (e.getSource().equals(lunchFoodItems)) {
                try {
                //subtract the nutrition info that was there previously so it will track it dynamically
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
                        lunchFoodItems.getSelectedItem().toString(), LogInScreen.studentInfoCon);
                try {
                //add the new nutrition info so nutrition info updates dynamically
                    lFoodCal += lFoodSet.getInt("total_calories");
                    lFoodFatCal += lFoodSet.getInt("total_fat_cal");
                    lFoodCarb += lFoodSet.getInt("total_carbs");
                    lFoodProtein += lFoodSet.getInt("total_protein");
                    lFoodPoints += lFoodSet.getInt("points");
                    lunchCalories.setText("<HTML><U>Total Calories: </U>" + lFoodCal + "</HTML> ");
                    lunchFatCalories.setText("<HTML><U>Total Fat Calories: </U>" + lFoodFatCal + "</HTML>");
                    lunchCarbs.setText("<HTML><U>Total Carbs: </U>" + lFoodCarb + " g</HTML>");
                    lunchProtein.setText("<HTML><U>Total Protein: </U>" + lFoodProtein + " g</HTML>");
                    updateTotalNutrition();

                } catch (SQLException exc) {
                    System.err.println("Didnt work");
                }
            } else if (e.getSource().equals(lunchSideItems)) {
                try {
                //subtract the nutrition info that was there previously so it will track it dynamically
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
                        lunchSideItems.getSelectedItem().toString(), LogInScreen.studentInfoCon);
                try {
                //add the new nutrition info so nutrition info updates dynamically
                    lFoodCal += lSideSet.getInt("total_calories");
                    lFoodFatCal += lSideSet.getInt("total_fat_cal");
                    lFoodCarb += lSideSet.getInt("total_carbs");
                    lFoodProtein += lSideSet.getInt("total_protein");
                    lFoodPoints += lSideSet.getInt("points");
                    lunchCalories.setText("<HTML><U>Total Calories: </U>" + lFoodCal + "</HTML> ");
                    lunchFatCalories.setText("<HTML><U>Total Fat Calories: </U>" + lFoodFatCal + "</HTML>");
                    lunchCarbs.setText("<HTML><U>Total Carbs: </U>" + lFoodCarb + " g</HTML>");
                    lunchProtein.setText("<HTML><U>Total Protein: </U>" + lFoodProtein + " g</HTML>");
                    updateTotalNutrition();

                } catch (SQLException exc) {
                    System.err.println("Didnt work");
                }
            } else if (e.getSource().equals(lunchDrinkItems)) {
                try {
                //subtract the nutrition info that was there previously so it will track it dynamically
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
                        lunchDrinkItems.getSelectedItem().toString(), LogInScreen.studentInfoCon);
                try {
                //add the new nutrition info so nutrition info updates dynamically
                    lFoodCal += lDrinkSet.getInt("total_calories");
                    lFoodFatCal += lDrinkSet.getInt("total_fat_cal");
                    lFoodCarb += lDrinkSet.getInt("total_carbs");
                    lFoodProtein += lDrinkSet.getInt("total_protein");
                    lFoodPoints += lDrinkSet.getInt("points");
                    lunchCalories.setText("<HTML><U>Total Calories: </U>" + lFoodCal + "</HTML> ");
                    lunchFatCalories.setText("<HTML><U>Total Fat Calories: </U>" + lFoodFatCal + "</HTML>");
                    lunchCarbs.setText("<HTML><U>Total Carbs: </U>" + lFoodCarb + " g</HTML>");
                    lunchProtein.setText("<HTML><U>Total Protein: </U>" + lFoodProtein + " g</HTML>");
                    updateTotalNutrition();

                } catch (SQLException exc) {
                    System.err.println("Didnt work");
                }
                //-------------------------------dinner-----------------------------------
            } else if (e.getSource().equals(dinnerFoodItems)) {
                try {
                //subtract the nutrition info that was there previously so it will track it dynamically
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
                        dinnerFoodItems.getSelectedItem().toString(), LogInScreen.studentInfoCon);
                try {
                //add the new nutrition info so nutrition info updates dynamically
                    dFoodCal += dFoodSet.getInt("total_calories");
                    dFoodFatCal += dFoodSet.getInt("total_fat_cal");
                    dFoodCarb += dFoodSet.getInt("total_carbs");
                    dFoodProtein += dFoodSet.getInt("total_protein");
                    dFoodPoints += dFoodSet.getInt("points");
                    dinnerCalories.setText("<HTML><U>Total Calories: </U>" + dFoodCal + "</HTML> ");
                    dinnerFatCalories.setText("<HTML><U>Total Fat Calories: </U>" + dFoodFatCal + "</HTML>");
                    dinnerCarbs.setText("<HTML><U>Total Carbs: </U>" + dFoodCarb + " g</HTML>");
                    dinnerProtein.setText("<HTML><U>Total Protein: </U>" + dFoodProtein + " g</HTML>");
                    updateTotalNutrition();

                } catch (SQLException exc) {
                    System.err.println("Didnt work");
                }
            } else if (e.getSource().equals(dinnerSideItems)) {
                try {
                //subtract the nutrition info that was there previously so it will track it dynamically
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
                        dinnerSideItems.getSelectedItem().toString(), LogInScreen.studentInfoCon);
                try {
                //add the new nutrition info so nutrition info updates dynamically
                    dFoodCal += dSideSet.getInt("total_calories");
                    dFoodFatCal += dSideSet.getInt("total_fat_cal");
                    dFoodCarb += dSideSet.getInt("total_carbs");
                    dFoodProtein += dSideSet.getInt("total_protein");
                    dFoodPoints += dSideSet.getInt("points");
                    dinnerCalories.setText("<HTML><U>Total Calories: </U>" + dFoodCal + "</HTML> ");
                    dinnerFatCalories.setText("<HTML><U>Total Fat Calories: </U>" + dFoodFatCal + "</HTML>");
                    dinnerCarbs.setText("<HTML><U>Total Carbs: </U>" + dFoodCarb + " g</HTML>");
                    dinnerProtein.setText("<HTML><U>Total Protein: </U>" + dFoodProtein + " g</HTML>");
                    updateTotalNutrition();

                } catch (SQLException exc) {
                    System.err.println("Didnt work");
                }
            } else if (e.getSource().equals(dinnerDrinkItems)) {
                try {
                //subtract the nutrition info that was there previously so it will track it dynamically
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
                        dinnerDrinkItems.getSelectedItem().toString(), LogInScreen.studentInfoCon);
                try {
                //add the new nutrition info so nutrition info updates dynamically
                    dFoodCal += dDrinkSet.getInt("total_calories");
                    dFoodFatCal += dDrinkSet.getInt("total_fat_cal");
                    dFoodCarb += dDrinkSet.getInt("total_carbs");
                    dFoodProtein += dDrinkSet.getInt("total_protein");
                    dFoodPoints += dDrinkSet.getInt("points");
                    dinnerCalories.setText("<HTML><U>Total Calories: </U>" + dFoodCal + "</HTML> ");
                    dinnerFatCalories.setText("<HTML><U>Total Fat Calories: </U>" + dFoodFatCal + "</HTML>");
                    dinnerCarbs.setText("<HTML><U>Total Carbs: </U>" + dFoodCarb + " g</HTML>");
                    dinnerProtein.setText("<HTML><U>Total Protein: </U>" + dFoodProtein + " g</HTML>");
                    updateTotalNutrition();

                } catch (SQLException exc) {
                    System.err.println("Didnt work");
                }
                //------------------------------------snack--------------------------------------
            } else if (e.getSource().equals(snackItems)) {
                try {
                //subtract the nutrition info that was there previously so it will track it dynamically
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
                        snackItems.getSelectedItem().toString(), LogInScreen.studentInfoCon);
                try {
                //add the new nutrition info so nutrition info updates dynamically
                    sFoodCal += sFoodSet.getInt("total_calories");
                    sFoodFatCal += sFoodSet.getInt("total_fat_cal");
                    sFoodCarb += sFoodSet.getInt("total_carbs");
                    sFoodProtein += sFoodSet.getInt("total_protein");
                    sFoodPoints += sFoodSet.getInt("points");
                    snackCalories.setText("<HTML><U>Total Calories: </U>" + sFoodCal + "</HTML> ");
                    snackFatCalories.setText("<HTML><U>Total Fat Calories: </U>" + sFoodFatCal + "</HTML>");
                    snackCarbs.setText("<HTML><U>Total Carbs: </U>" + sFoodCarb + " g</HTML>");
                    snackProtein.setText("<HTML><U>Total Protein: </U>" + sFoodProtein + " g</HTML>");
                    updateTotalNutrition();

                } catch (SQLException exc) {
                    System.err.println("Didnt work");
                }
            }
            //repaint and revalidate the objects in window whenever something is selected
            revalidate();
            repaint();
        }
    }

    /*
     * This method is called inorder to update the totalNutrition panel
     */
    public void updateTotalNutrition() {

        totalCalories.setText("<HTML><U>Total Calories: </U>" + (bFoodCal + lFoodCal + dFoodCal + sFoodCal) + "</HTML>");
        totalFatCalories.setText("<HTML><U>Total Fat Calories: </U>" + (bFoodFatCal + lFoodFatCal + dFoodFatCal + sFoodFatCal) + "</HTML>");
        totalCarbs.setText("<HTML><U>Total Carbs: </U>" + (bFoodCarb + lFoodCarb + dFoodCarb + sFoodCarb) + " g</HTML>");
        totalProtein.setText("<HTML><U>Total Protein: </U>" + (bFoodProtein + lFoodProtein + dFoodProtein + sFoodProtein) + " g</HTML>");
    }

    /*
     * this method is called to add all breakfast comboBoxes and lables 
     * 
     * Receives a ResultSet inorder to load the correct items in the comboboxes
     * according to what a user had previously selected.
     */
    public void breakfastItems(ResultSet set) {

      
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
        
        /* 
         * as long as there is data in the set, this will load the correct entree,side and drink items
         * into the comboboxes.
         */
        if (set != null) {
            try {
                breakfastRestaurants.setSelectedItem(set.getString("breakfast_restaurant"));
            } catch (SQLException e) {
                System.err.println("No b data found");
            }
        }

        if (breakfastRestaurants.getSelectedItem() == restaurants[0]) {
            //einsteins
            ent = einEnt;
            side = einSide;
            drink = einDrink;
        } else if (breakfastRestaurants.getSelectedItem() == restaurants[1]) {
            //papa
            ent = papaEnt;
            side = papaSide;
            drink = papaDrink;
        } else if (breakfastRestaurants.getSelectedItem() == restaurants[2]) {
            //brahma
            ent = brEnt;
            side = brSide;
            drink = brDrink;
        } else if (breakfastRestaurants.getSelectedItem() == restaurants[3]) {
            //chick
            ent = chickEnt;
            side = chickSide;
            drink = chickDrink;
        } else if (breakfastRestaurants.getSelectedItem() == restaurants[4]) {
            //jamba
            ent = new String[0];
            side = new String[0];
            drink = jambaDrink;
        } else if (breakfastRestaurants.getSelectedItem() == restaurants[0]) {
            //starbucks
            ent = new String[0];
            side = new String[0];
            drink = starDrink;
        }

        //set up the menu option comboboxes
        breakfastFoodItems = new JComboBox(ent);
        breakfastFoodItems.setBackground(Color.WHITE);
        breakfastFoodItems.setForeground(ToolClass.fgcuGreen);

        breakfastSideItems = new JComboBox(side);
        breakfastSideItems.setBackground(Color.WHITE);
        breakfastSideItems.setForeground(ToolClass.fgcuGreen);

        breakfastDrinkItems = new JComboBox(drink);
        breakfastDrinkItems.setBackground(Color.WHITE);
        breakfastDrinkItems.setForeground(ToolClass.fgcuBlue);

        //attempt to query the set to change the selected item in the comboboxes
        try {
            breakfastFoodItems.setSelectedItem(set.getString("breakfast_food"));
            breakfastSideItems.setSelectedItem(set.getString("breakfast_side"));
            breakfastDrinkItems.setSelectedItem(set.getString("breakfast_drink"));
        } catch (SQLException e) {
            System.err.println("No b data found");
        }

        //must add action listeners after you setSelectedItem()
        breakfastRestaurants.addActionListener(boxHandler);
        breakfastFoodItems.addActionListener(boxHandler);
        breakfastSideItems.addActionListener(boxHandler);
        breakfastDrinkItems.addActionListener(boxHandler);

        //add items to the breakfast panel
        breakfastPanel.add(breakfastLabel);
        breakfastPanel.add(breakfastRestaurants);
        breakfastPanel.add(breakfastFoodItems);
        breakfastPanel.add(breakfastSideItems);
        breakfastPanel.add(breakfastDrinkItems);
    }

/*
 * this method is used to query the database to get calorie information from a specific food.
 * Receives: restName - the restaurant name to query for
 *          foodName - the name of the food to query for
 *          connect - the connection to the database
 *          
 * returns: ResultSet set - the ResultSet created from the query
 */
    public ResultSet stephensQuery(String restName, String foodName, Connection connect) {
        String foodQuery = String.format("SELECT total_calories, total_fat_cal, total_protein, total_carbs," +
                " points FROM food_item WHERE restaurant = '%s' " +
                "AND item_name = '%s'", restName, foodName);

        try {
            Statement FoodState = connect.createStatement();
            ResultSet FoodSet = FoodState.executeQuery(foodQuery);
            return FoodSet;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    /*
     * This method creates the breakfast nutrition items and adds them to the breakfastpanel
     * 
     * Receives: ResultSets that contain nutrition info for the selected food, side and drink items
     */
    public void breakfastNutritionItems(ResultSet fSet, ResultSet sSet, ResultSet dSet) {

      
        breakfastNutrition = new JPanel();
        breakfastNutrition.setLayout(new GridLayout(4, 1));
        breakfastNutrition.setVisible(true);
        breakfastNutrition.setBorder(ToolClass.newCompound);
        breakfastNutrition.setBackground(ToolClass.fgcuBlue);
        
        try {
          bFoodCal = fSet.getInt("total_calories") + sSet.getInt("total_calories") + dSet.getInt("total_calories");
          bFoodFatCal = fSet.getInt("total_fat_cal") + sSet.getInt("total_fat_cal") + dSet.getInt("total_fat_cal");
          bFoodCarb = fSet.getInt("total_carbs") + sSet.getInt("total_carbs") + dSet.getInt("total_carbs");
          bFoodProtein = fSet.getInt("total_protein") + sSet.getInt("total_protein") + dSet.getInt("total_protein");
          bFoodPoints = fSet.getInt("points") + sSet.getInt("points") + dSet.getInt("points");
        } catch (SQLException e) {
          e.printStackTrace();
        }

        breakfastCalories = new JLabel("<HTML><U>Total Calories: </U>" + bFoodCal + "</HTML> ");
        breakfastCalories.setForeground(Color.WHITE);
        breakfastCalories.setFont(ToolClass.nutritionPanelFont);
        breakfastCalories.setHorizontalTextPosition(SwingConstants.LEFT);

        breakfastFatCalories = new JLabel("<HTML><U>Total Fat Calories: </U>" + bFoodFatCal + "</HTML>");
        breakfastFatCalories.setForeground(Color.WHITE);
        breakfastFatCalories.setFont(ToolClass.nutritionPanelFont);

        breakfastCarbs = new JLabel("<HTML><U>Total Carbs: </U> " + bFoodCarb + " g</HTML>");
        breakfastCarbs.setForeground(Color.WHITE);
        breakfastCarbs.setFont(ToolClass.nutritionPanelFont);

        breakfastProtein = new JLabel("<HTML><U>Total Protein: </U>" + bFoodProtein + " g</HTML>");
        breakfastProtein.setForeground(Color.WHITE);
        breakfastProtein.setFont(ToolClass.nutritionPanelFont);

//        breakfastFat = new JLabel("<HTML><U>Total Fat:</U></HTML>");
//        breakfastFat.setForeground(Color.WHITE);
//        breakfastFat.setFont(ToolClass.nutritionPanelFont);

        breakfastNutrition.add(breakfastCalories);
        breakfastNutrition.add(breakfastFatCalories);
        breakfastNutrition.add(breakfastCarbs);
        breakfastNutrition.add(breakfastProtein);
//        breakfastNutrition.add(breakfastFat);

    }

    public void lunchItems(ResultSet set) {

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
        lunchRestaurants.setVisible(true);
        lunchRestaurants.setBackground(Color.WHITE);
        lunchRestaurants.setForeground(ToolClass.fgcuBlue);

        /* 
         * as long as there is data in the set, this will load the correct entree,side and drink items
         * into the comboboxes.
         */
        if (set != null) {
            try {
                lunchRestaurants.setSelectedItem(set.getString("lunch_restaurant"));
            } catch (SQLException e) {
                System.err.println("No l data found");
            }
        }

        if (lunchRestaurants.getSelectedItem() == restaurants[0]) {
            //einsteins
            ent = einEnt;
            side = einSide;
            drink = einDrink;
        } else if (lunchRestaurants.getSelectedItem() == restaurants[1]) {
            //papa
            ent = papaEnt;
            side = papaSide;
            drink = papaDrink;
        } else if (lunchRestaurants.getSelectedItem() == restaurants[2]) {
            //brahma
            ent = brEnt;
            side = brSide;
            drink = brDrink;
        } else if (lunchRestaurants.getSelectedItem() == restaurants[3]) {
            //chick
            ent = chickEnt;
            side = chickSide;
            drink = chickDrink;
        } else if (lunchRestaurants.getSelectedItem() == restaurants[4]) {
            //jamba
            ent = new String[0];
            side = new String[0];
            drink = jambaDrink;
        } else if (lunchRestaurants.getSelectedItem() == restaurants[0]) {
            //starbucks
            ent = new String[0];
            side = new String[0];
            drink = starDrink;
        }

        lunchFoodItems = new JComboBox(ent);
        lunchFoodItems.setBackground(Color.WHITE);
        lunchFoodItems.setForeground(ToolClass.fgcuGreen);

        lunchSideItems = new JComboBox(side);
        lunchSideItems.setBackground(Color.WHITE);
        lunchSideItems.setForeground(ToolClass.fgcuGreen);

        lunchDrinkItems = new JComboBox(drink);
        lunchDrinkItems.setBackground(Color.WHITE);
        lunchDrinkItems.setForeground(ToolClass.fgcuBlue);

        //attempt to query the set to change the selected item in the comboboxes
        try {
            lunchFoodItems.setSelectedItem(set.getString("lunch_food"));
            lunchSideItems.setSelectedItem(set.getString("lunch_side"));
            lunchDrinkItems.setSelectedItem(set.getString("lunch_drink"));
        } catch (SQLException e) {
            System.err.println("No l data found");
        }

        //must add action listeners after you setSelectedItem()
        lunchRestaurants.addActionListener(boxHandler);
        lunchFoodItems.addActionListener(boxHandler);
        lunchSideItems.addActionListener(boxHandler);
        lunchDrinkItems.addActionListener(boxHandler);

        lunchPanel.add(lunchLabel);
        lunchPanel.add(lunchRestaurants);
        lunchPanel.add(lunchFoodItems);
        lunchPanel.add(lunchSideItems);
        lunchPanel.add(lunchDrinkItems);
    }

    /*
     * This method creates the lunch nutrition items and adds them to the lunchPanel
     * 
     * Receives: ResultSets that contain nutrition info for the selected food, side and drink items
     */
    public void lunchNutritionItems(ResultSet fSet, ResultSet sSet, ResultSet dSet) {

      try {
        lFoodCal = fSet.getInt("total_calories") + sSet.getInt("total_calories") + dSet.getInt("total_calories");
        lFoodFatCal = fSet.getInt("total_fat_cal") + sSet.getInt("total_fat_cal") + dSet.getInt("total_fat_cal");
        lFoodCarb = fSet.getInt("total_carbs") + sSet.getInt("total_carbs") + dSet.getInt("total_carbs");
        lFoodProtein = fSet.getInt("total_protein") + sSet.getInt("total_protein") + dSet.getInt("total_protein");
        lFoodPoints = fSet.getInt("points") + sSet.getInt("points") + dSet.getInt("points");

      } catch (SQLException e) {
        e.printStackTrace();
      }

        lunchNutrition = new JPanel();
        lunchNutrition.setLayout(new GridLayout(4, 1));
        lunchNutrition.setVisible(true);
        lunchNutrition.setBackground(ToolClass.fgcuBlue);
        lunchNutrition.setBorder(ToolClass.newCompound);

        lunchCalories = new JLabel("<HTML><U>Total Calories: </U>"+lFoodCal+"</HTML> ");
        lunchCalories.setForeground(Color.WHITE);
        lunchCalories.setFont(ToolClass.nutritionPanelFont);
        lunchCalories.setHorizontalTextPosition(SwingConstants.LEFT);

        lunchFatCalories = new JLabel("<HTML><U>Total Fat Calories: </U>"+lFoodFatCal+"</HTML>");
        lunchFatCalories.setForeground(Color.WHITE);
        lunchFatCalories.setFont(ToolClass.nutritionPanelFont);

        lunchCarbs = new JLabel("<HTML><U>Total Carbs: </U>"+lFoodCarb+" g</HTML>");
        lunchCarbs.setForeground(Color.WHITE);
        lunchCarbs.setFont(ToolClass.nutritionPanelFont);

        lunchProtein = new JLabel("<HTML><U>Total Protein: </U>"+lFoodProtein+" g</HTML>");
        lunchProtein.setForeground(Color.WHITE);
        lunchProtein.setFont(ToolClass.nutritionPanelFont);

//        lunchFat = new JLabel("<HTML><U>Total Fat: </U></HTML>");
//        lunchFat.setForeground(Color.WHITE);
//        lunchFat.setFont(ToolClass.nutritionPanelFont);

        lunchNutrition.add(lunchCalories);
        lunchNutrition.add(lunchFatCalories);
        lunchNutrition.add(lunchCarbs);
        lunchNutrition.add(lunchProtein);
//        lunchNutrition.add(lunchFat);
    }

    public void dinnerItems(ResultSet set) {

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
        dinnerRestaurants.setVisible(true);
        dinnerRestaurants.setBackground(Color.WHITE);
        dinnerRestaurants.setForeground(ToolClass.fgcuBlue);

        /* 
         * as long as there is data in the set, this will load the correct entree,side and drink items
         * into the comboboxes.
         */
        if (set != null) {
            try {
                dinnerRestaurants.setSelectedItem(set.getString("dinner_restaurant"));
            } catch (SQLException e) {
                System.err.println("No d data found");
            }
        }

        if (dinnerRestaurants.getSelectedItem() == restaurants[0]) {
            //einsteins
            ent = einEnt;
            side = einSide;
            drink = einDrink;
        } else if (dinnerRestaurants.getSelectedItem() == restaurants[1]) {
            //papa
            ent = papaEnt;
            side = papaSide;
            drink = papaDrink;
        } else if (dinnerRestaurants.getSelectedItem() == restaurants[2]) {
            //brahma
            ent = brEnt;
            side = brSide;
            drink = brDrink;
        } else if (dinnerRestaurants.getSelectedItem() == restaurants[3]) {
            //chick
            ent = chickEnt;
            side = chickSide;
            drink = chickDrink;
        } else if (dinnerRestaurants.getSelectedItem() == restaurants[4]) {
            //jamba
            ent = new String[0];
            side = new String[0];
            drink = jambaDrink;
        } else if (dinnerRestaurants.getSelectedItem() == restaurants[0]) {
            //starbucks
            ent = new String[0];
            side = new String[0];
            drink = starDrink;
        }

        dinnerFoodItems = new JComboBox(ent);
        dinnerFoodItems.setBackground(Color.WHITE);
        dinnerFoodItems.setForeground(ToolClass.fgcuGreen);

        dinnerSideItems = new JComboBox(side);
        dinnerSideItems.setBackground(Color.WHITE);
        dinnerSideItems.setForeground(ToolClass.fgcuGreen);

        dinnerDrinkItems = new JComboBox(drink);
        dinnerDrinkItems.setBackground(Color.WHITE);
        dinnerDrinkItems.setForeground(ToolClass.fgcuBlue);

        //attempt to query the set to change the selected item in the comboboxes
        try {
            dinnerFoodItems.setSelectedItem(set.getString("dinner_food"));
            dinnerSideItems.setSelectedItem(set.getString("dinner_side"));
            dinnerDrinkItems.setSelectedItem(set.getString("dinner_drink"));
        } catch (SQLException e) {
            System.err.println("No d data found");
        }

        //must add action listeners after you setSelectedItem()
        dinnerRestaurants.addActionListener(boxHandler);
        dinnerFoodItems.addActionListener(boxHandler);
        dinnerSideItems.addActionListener(boxHandler);
        dinnerDrinkItems.addActionListener(boxHandler);

        dinnerPanel.add(dinnerLabel);
        dinnerPanel.add(dinnerRestaurants);
        dinnerPanel.add(dinnerFoodItems);
        dinnerPanel.add(dinnerSideItems);
        dinnerPanel.add(dinnerDrinkItems);
    }

    /*
     * experimenting with new ways to set up our nutrition panels, this was one way to make it more 
     * modularized
     */
    private JLabel panelTheme(String txt) {
        JLabel label = new JLabel("<HTML><U>" + txt + "</U></HTML> " + "g");
        label.setForeground(Color.WHITE);
        label.setFont(ToolClass.nutritionPanelFont);
        return label;
    }

    /*
     * This method creates the dinner nutrition items and adds them to the dinnerPanel
     * 
     * Receives: ResultSets that contain nutrition info for the selected food, side and drink items
     */
    public void dinnerNutritionItems(ResultSet fSet, ResultSet sSet, ResultSet dSet) {
      
      try {
        dFoodCal = fSet.getInt("total_calories") + sSet.getInt("total_calories") + dSet.getInt("total_calories");
        dFoodFatCal = fSet.getInt("total_fat_cal") + sSet.getInt("total_fat_cal") + dSet.getInt("total_fat_cal");
        dFoodCarb = fSet.getInt("total_carbs") + sSet.getInt("total_carbs") + dSet.getInt("total_carbs");
        dFoodProtein = fSet.getInt("total_protein") + sSet.getInt("total_protein") + dSet.getInt("total_protein");
        dFoodPoints = fSet.getInt("points") + sSet.getInt("points") + dSet.getInt("points");

      } catch (SQLException e) {
        e.printStackTrace();
      }

        dinnerNutrition = new JPanel();
        dinnerNutrition.setLayout(new GridLayout(4, 1));
        dinnerNutrition.setVisible(true);
        dinnerNutrition.setBackground(ToolClass.fgcuBlue);
        dinnerNutrition.setBorder(ToolClass.newCompound);

        dinnerCalories = panelTheme("Total Calories: " + bFoodCal);
        dinnerCalories.setHorizontalTextPosition(SwingConstants.LEFT);

        dinnerFatCalories = panelTheme("Total Fat Calories: " + bFoodFatCal);
        dinnerCarbs = panelTheme("Total Carbs: " + bFoodCarb + " g");
        dinnerProtein = panelTheme("Total Protein: " + bFoodProtein + " g");
//        dinnerFat = panelTheme("Total Fat:");

        dinnerNutrition.add(dinnerCalories);
        dinnerNutrition.add(dinnerFatCalories);
        dinnerNutrition.add(dinnerCarbs);
        dinnerNutrition.add(dinnerProtein);
//        dinnerNutrition.add(dinnerFat);

    }

    public void snackItems(ResultSet set) {

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
        snackRestaurants.setVisible(true);
        snackRestaurants.setBackground(Color.WHITE);
        snackRestaurants.setForeground(ToolClass.fgcuBlue);

        /* 
         * as long as there is data in the set, this will load the correct entree,side and drink items
         * into the comboboxes.
         */
        try {
            snackRestaurants.setSelectedItem(set.getString("snack_restaurant"));
        } catch (SQLException e) {
            System.err.println("No s data found");
        }

        if (snackRestaurants.getSelectedItem() == sRestaurants[0]) {
            //jamba
            drink = jambaDrink;
        } else if (snackRestaurants.getSelectedItem() == sRestaurants[1]) {
            //starbucks
            drink = starDrink;
        }

        snackItems = new JComboBox(drink);
        snackItems.setBackground(Color.WHITE);
        snackItems.setForeground(ToolClass.fgcuGreen);

        //attempt to query the set to change the selected item in the comboboxes
        try {
            snackItems.setSelectedItem(set.getString("snack"));
        } catch (SQLException e) {
            System.err.println("No s data found");
        }

        //must add action listeners after you setSelectedItem()
        snackRestaurants.addActionListener(boxHandler);
        snackItems.addActionListener(boxHandler);

        snackPanel.add(snackLabel);
        snackPanel.add(snackRestaurants);
        snackPanel.add(snackItems);
    }

    /*
     * This method creates the snack nutrition items and adds them to the snackPanel
     * 
     * Receives: ResultSet that contains nutrition info for the selected drink item
     */
    public void snackNutritionItems(ResultSet dSet) {
      
      try {
        sFoodCal = dSet.getInt("total_calories");
        sFoodFatCal = dSet.getInt("total_fat_cal");
        sFoodCarb = dSet.getInt("total_carbs");
        sFoodProtein = dSet.getInt("total_protein");
        sFoodPoints = dSet.getInt("points");

      } catch (SQLException e) {
        e.printStackTrace();
      }

        snackNutrition = new JPanel();
        snackNutrition.setLayout(new GridLayout(4, 1));
        snackNutrition.setVisible(true);
        snackNutrition.setBackground(ToolClass.fgcuBlue);
        snackNutrition.setBorder(ToolClass.newCompound);

        snackCalories = new JLabel("<HTML><U>Total Calories: </U>"+sFoodCal+"</HTML> ");
        snackCalories.setForeground(Color.WHITE);
        snackCalories.setFont(ToolClass.nutritionPanelFont);
        snackCalories.setHorizontalTextPosition(SwingConstants.LEFT);

        snackFatCalories = new JLabel("<HTML><U>Total Fat Calories: </U>"+sFoodFatCal+"</HTML>");
        snackFatCalories.setForeground(Color.WHITE);
        snackFatCalories.setFont(ToolClass.nutritionPanelFont);

        snackCarbs = new JLabel("<HTML><U>Total Carbs: </U>"+sFoodCarb+" g</HTML>");
        snackCarbs.setForeground(Color.WHITE);
        snackCarbs.setFont(ToolClass.nutritionPanelFont);

        snackProtein = new JLabel("<HTML><U>Total Protein: </U>"+sFoodProtein+" g</HTML>");
        snackProtein.setForeground(Color.WHITE);
        snackProtein.setFont(ToolClass.nutritionPanelFont);

//        snackFat = new JLabel("<HTML><U>Total Fat: </U></HTML>");
//        snackFat.setForeground(Color.WHITE);
//        snackFat.setFont(ToolClass.nutritionPanelFont);

        snackNutrition.add(snackCalories);
        snackNutrition.add(snackFatCalories);
        snackNutrition.add(snackCarbs);
        snackNutrition.add(snackProtein);
//        snackNutrition.add(snackFat);
    }


    /*
     * this method creates the items in the total nutrition panel
     * 
     * Receives: ResultSet set - has the users total nutrition information for that day.
     */
    public void totalNutritionItems(ResultSet set) {

        totalNutrition = new JPanel();
        totalNutrition.setLayout(new GridLayout(4, 1));
        totalNutrition.setVisible(true);
        totalNutrition.setBackground(ToolClass.fgcuBlue);
        totalNutrition.setBorder(ToolClass.whiteLine);

        totalCalories = new JLabel("<HTML><U>Total Calories: </U>"+(bFoodCal+lFoodCal+dFoodCal+sFoodCal)+"</HTML> ");
        totalCalories.setForeground(Color.WHITE);
        totalCalories.setFont(ToolClass.nutritionPanelFont);
        totalCalories.setHorizontalTextPosition(SwingConstants.LEFT);

        totalFatCalories = new JLabel("<HTML><U>Total Fat Calories: </U>"+(bFoodFatCal+lFoodFatCal+dFoodFatCal+sFoodFatCal)+" </HTML>");
        totalFatCalories.setForeground(Color.WHITE);
        totalFatCalories.setFont(ToolClass.nutritionPanelFont);

        totalCarbs = new JLabel("<HTML><U>Total Carbs: </U>"+(bFoodCarb+lFoodCarb+dFoodCarb+sFoodCarb)+" g</HTML>");
        totalCarbs.setForeground(Color.WHITE);
        totalCarbs.setFont(ToolClass.nutritionPanelFont);

        totalProtein = new JLabel("<HTML><U>Total Protein: </U>"+(bFoodProtein+lFoodProtein+dFoodProtein+sFoodProtein)+" g</HTML>");
        totalProtein.setForeground(Color.WHITE);
        totalProtein.setFont(ToolClass.nutritionPanelFont);

        try {
            totalCalories.setText("<HTML><U>Total Calories:</U>" + Integer.parseInt(set.getString("total_calories")) + "</HTML> ");
            totalFatCalories.setText("<HTML><U>Total Fat Calories:</U>" + Integer.parseInt(set.getString("total_fat_cal")) + "</HTML> " + "g");
            totalCarbs.setText("<HTML><U>Total Carbs:</U>" + Integer.parseInt(set.getString("total_carbs")) + "</HTML> " + "g");
            totalProtein.setText("<HTML><U>Total Protein: </U>" + Integer.parseInt(set.getString("total_protein")) + "</HTML> " + "g");
        
        } catch (Exception e) {
            System.err.println("No n data found");
        }

        totalNutrition.add(totalCalories);
        totalNutrition.add(totalFatCalories);
        totalNutrition.add(totalCarbs);
        totalNutrition.add(totalProtein);
    }

    /*
     * method is called inorder to create the delete button and add an action listener
     */
    public void delete() {
      deleteBtn = new JButton("Delete Meal");
      deleteBtn.setVisible(true);
      ActionListener deleteButtonHandler = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          ent = einEnt;
          side = einSide;
          drink = einDrink;
          try {
            String sql = String.format("DELETE FROM '%d' WHERE date= '%s'", 
                LogInScreen.universityID, databaseKey);
            LogInScreen.studentInfoCon.setAutoCommit(false);
            Statement stmt1 = LogInScreen.studentInfoCon.createStatement();
            stmt1.execute(sql);
            stmt1.close();
  
            LogInScreen.studentInfoCon.commit();
  
            // closes window
            dispose();
            System.out.println("Records deleted successfully");

          } catch (SQLException Exc) {
            Exc.printStackTrace();
            System.err.println(Exc.getClass().getName() + ": " + Exc.getMessage());
            dispose();
            System.out.println("Record delete unsuccessful");

  
          }
        }
      };
      deleteBtn.addActionListener(deleteButtonHandler);
    }
    
    /*
     * method is called inorder to create the submit button and add an action listener
     */
    public void submit() {

        submitBtn = new JButton("Plan Meal");
        submitBtn.setVisible(true);
//        submitBtn.setForeground(Color.WHITE);
//        submitBtn.setBackground(Color.BLUE);
//        submitBtn.setOpaque(true);

        String Statement = String.format("INSERT INTO %d ()", LogInScreen.universityID);

        ActionListener sumbitButtonHandler = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ent = einEnt;
                side = einSide;
                drink = einDrink;
                Statement stmt1;
                try {
                    Class.forName("org.sqlite.JDBC");

                    LogInScreen.studentInfoCon.setAutoCommit(false);
                    System.out.println("Opened database successfully");

                    stmt1 = LogInScreen.studentInfoCon.createStatement();


                    String breakfastFood = breakfastFoodItems.getSelectedItem().toString();
                    String breakfastSide = breakfastSideItems.getSelectedItem().toString();
                    String breakfastDrink = breakfastDrinkItems.getSelectedItem().toString();

                    String lunchFood = lunchFoodItems.getSelectedItem().toString();
                    String lunchSide = lunchSideItems.getSelectedItem().toString();
                    String lunchDrink = lunchDrinkItems.getSelectedItem().toString();

                    String dinnerFood = dinnerFoodItems.getSelectedItem().toString();
                    String dinnerSide = dinnerSideItems.getSelectedItem().toString();
                    String dinnerDrink = dinnerDrinkItems.getSelectedItem().toString();

                    String snack = snackItems.getSelectedItem().toString();

                    int total_cal = bFoodCal + lFoodCal + dFoodCal + sFoodCal;
                    int total_fatCal = bFoodFatCal + lFoodFatCal + dFoodFatCal + sFoodFatCal;
                    int total_carb = bFoodCarb + lFoodCarb + dFoodCarb + sFoodCarb;
                    int total_protein = bFoodProtein + lFoodProtein + dFoodProtein + sFoodProtein;

                    int points_used = bFoodPoints + lFoodPoints + dFoodPoints + sFoodPoints;

                    String breakfast_rest = breakfastRestaurants.getSelectedItem().toString();
                    String lunch_rest = lunchRestaurants.getSelectedItem().toString();
                    String dinner_rest = dinnerRestaurants.getSelectedItem().toString();
                    String snack_rest = snackRestaurants.getSelectedItem().toString();


                    String sql = String.format("INSERT INTO '%d' (date,breakfast_restaurant,breakfast_food,breakfast_side," +
                                    "breakfast_drink,lunch_restaurant,lunch_food,lunch_side,lunch_drink,dinner_restaurant," +
                                    "dinner_food,dinner_side,dinner_drink,snack_restaurant,snack,total_calories," +
                                    "total_fat_cal,total_carbs,total_protein, points_used) " +
                                    "VALUES ('%d','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%d','%d','%d','%d','%d');",
                            LogInScreen.universityID, databaseKey, breakfast_rest,
                            breakfastFood, breakfastSide, breakfastDrink, lunch_rest, lunchFood, lunchSide, lunchDrink, dinner_rest, dinnerFood,
                            dinnerSide, dinnerDrink, snack_rest, snack, total_cal, total_fatCal, total_carb, total_protein, points_used);


                    stmt1.executeUpdate(sql);
                    stmt1.close();

                    LogInScreen.studentInfoCon.commit();

                    // closes window
                    dispose();
                    System.out.println("Records created successfully");


                } catch (Exception Exc) {
                    System.err.println(Exc.getClass().getName() + ": " + Exc.getMessage());
                    dispose();
                }
            }
        };

        submitBtn.addActionListener(sumbitButtonHandler);
    }
}
