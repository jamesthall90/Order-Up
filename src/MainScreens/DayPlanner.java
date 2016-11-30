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
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.Scanner;

//import Calendar.CalendarDemo;
//import oracle.jvm.hotspot.jfr.JFR;

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
                                             // items and load into comboboxes

  String host;
  Connection food_itemsConnect;
  Statement dbDrive;

  String[] restaurants = { "Einstein Bros. Bagels", "Papa Johns", "Brahma Express", "Chick-Fil-A", "Jamba Juice",
      "Starbucks" };
  String[] einEnt = {"Plain Bagel", "Cheesy Bacon Club", "Santa Fe Wrap"}; //Entered into DB
  String[] einSide = {"Blueberry Muffin", "Brownie", "Cookie"}; //Entered into DB
  String[] einDrink = {"Coffee", "Orange Juice", "Chocolate Milk"}; //Entered into DB
  String[] papaEnt = {"Cheese Pizza", "Pepperoni Pizza", "Hot Wings"}; //Entered into DB
  String[] papaSide = {"Cookie Slice", "Brownie", "Breadsticks x2"}; //Entered into DB
  String[] papaDrink = {"Water", "Coke", "Sprite"}; //Entered into DB
  String[] brEnt = {"Teriyaki Chicken Bowl", "Dunk City Roll", "Spicy Tuna Roll"}; //Entered into DB
  String[] brSide = {"Egg Roll", "Miso Soup", "Seaweed Salad"}; //Entered into DB
  String[] brDrink = {"Water", "Coke", "Sprite"}; //Entered into DB
  String[] chickEnt = {"Chicken Sandwich", "8 Piece Chicken Nuggets", "Chicken Salad"};  //Entered into DB
  String[] chickSide = {"Waffle Fries", "Cookie", "Fruit Cup"}; //Entered into DB
  String[] chickDrink = {"Water", "Lemonade", "Iced Tea"}; //Entered into DB
  String[] jambaDrink = {"Mango Smoothie", "Strawberry Smoothie", "Chocolate Banana Smoothie"}; //Entered into DB
  String[] starDrink = {"Vanilla Bean Frappuccino", "Coffee", "Raspberry Iced Tea"}; //Entered into DB

  String breakfastRestaurantName;

  BoxHandler boxHandler = new BoxHandler();

  public DayPlanner(String dayText) throws SQLException { // dayText is the day number
    super("Meal Plan for " + "/" + dayText + "/" + CalendarDemo.year);
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

    totalNutrition.setBounds(285, 70, 225, 50);
    breakfastPanel.setBounds(20, 120, 225, 225);
    breakfastNutrition.setBounds(20, 345, 225, 50);
    lunchPanel.setBounds(560, 120, 225, 225);
    lunchNutrition.setBounds(560, 345, 225, 50);
    snackPanel.setBounds(310, 120, 185, 170);
    snackNutrition.setBounds(310, 290, 185, 70);
    dinnerPanel.setBounds(300, 400, 225, 225);
    dinnerNutrition.setBounds(300, 625, 225, 50);
    // submitBtn.setBounds(350, 400, 60, 60);

    // Addition of contents to dayPlannerPanel
    getContentPane().add(dayPlannerPanel);
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
          System.out.println("Einsteins");
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
          System.out.println("Chick-Fil-A");
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
        breakfastSideItems = new JComboBox(side);
        breakfastDrinkItems = new JComboBox(drink);
        breakfastPanel.add(breakfastFoodItems);
        breakfastPanel.add(breakfastSideItems);
        breakfastPanel.add(breakfastDrinkItems);

      } else if (e.getSource() == lunchRestaurants) {
        JComboBox lr = (JComboBox) e.getSource();
        String r = (String) lr.getSelectedItem();
        // update lunch combo boxes
        lunchPanel.remove(lunchFoodItems);
        lunchPanel.remove(lunchSideItems);
        lunchPanel.remove(lunchDrinkItems);

        if (r.equals(restaurants[0])) {
          System.out.println("Einsteins");
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
          System.out.println("Chick-Fil-A");
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
        lunchSideItems = new JComboBox(side);
        lunchDrinkItems = new JComboBox(drink);
        lunchPanel.add(lunchFoodItems);
        lunchPanel.add(lunchSideItems);
        lunchPanel.add(lunchDrinkItems);
      } else if (e.getSource() == dinnerRestaurants) {
        JComboBox dr = (JComboBox) e.getSource();
        String r = (String) dr.getSelectedItem();
        // update dinner combo boxes
        dinnerPanel.remove(dinnerFoodItems);
        dinnerPanel.remove(dinnerSideItems);
        dinnerPanel.remove(dinnerDrinkItems);

        if (r.equals(restaurants[0])) {
          System.out.println("Einsteins");
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
          System.out.println("Chick-Fil-A");
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
        dinnerSideItems = new JComboBox(side);
        dinnerDrinkItems = new JComboBox(drink);
        dinnerPanel.add(dinnerFoodItems);
        dinnerPanel.add(dinnerSideItems);
        dinnerPanel.add(dinnerDrinkItems);
      } else if (e.getSource() == snackRestaurants) {
        JComboBox sr = (JComboBox) e.getSource();
        String r = (String) sr.getSelectedItem();
        // update snack combo boxes
        snackPanel.remove(snackItems);

        if (r.equals(restaurants[0])) {
          System.out.println("Einsteins");
          side = new String[0];
        } else if (r.equals(restaurants[1])) {
          side = new String[0];
        } else if (r.equals(restaurants[2])) {
          side = new String[0];
        } else if (r.equals(restaurants[3])) {
          System.out.println("Chick-Fil-A");
          side = new String[0];
        } else if (r.equals(restaurants[4])) {
          side = jambaDrink;
        } else if (r.equals(restaurants[5])) {
          side = starDrink;
        }
        snackItems = new JComboBox(side);
        snackPanel.add(snackItems);
      }

      revalidate();
      repaint();
    }
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

    breakfastSideItems = new JComboBox(side);
    breakfastSideItems.setBackground(Color.WHITE);
    breakfastSideItems.setForeground(ToolClass.fgcuGreen);

    breakfastDrinkItems = new JComboBox(drink);
    breakfastDrinkItems.setBackground(Color.WHITE);
    breakfastDrinkItems.setForeground(ToolClass.fgcuBlue);

    breakfastPanel.add(breakfastLabel);
    breakfastPanel.add(breakfastRestaurants);
    breakfastPanel.add(breakfastFoodItems);
    breakfastPanel.add(breakfastSideItems);
    breakfastPanel.add(breakfastDrinkItems);

    breakfastRestaurantName = breakfastRestaurants.getSelectedItem().toString();
  }

  public void breakfastNutritionItems() {

    breakfastNutrition = new JPanel();
    breakfastNutrition.setLayout(new FlowLayout());
    breakfastNutrition.setVisible(true);
    breakfastNutrition.setBorder(ToolClass.newCompound);
    breakfastNutrition.setBackground(ToolClass.fgcuBlue);

    breakfastCalories = new JLabel("<HTML><U>Total Calories:</U></HTML> ");
    breakfastCalories.setForeground(Color.WHITE);
    breakfastCalories.setFont(ToolClass.nutritionPanelFont);
    breakfastCalories.setHorizontalTextPosition(SwingConstants.LEFT);

    breakfastFatCalories = new JLabel("<HTML><U>Total Fat Calories:</U></HTML> " + "g");
    breakfastFatCalories.setForeground(Color.WHITE);
    breakfastFatCalories.setFont(ToolClass.nutritionPanelFont);

    breakfastCarbs = new JLabel("<HTML><U>Total Carbs:</U></HTML> " + "g");
    breakfastCarbs.setForeground(Color.WHITE);
    breakfastCarbs.setFont(ToolClass.nutritionPanelFont);

    breakfastProtein = new JLabel("<HTML><U>Total Protein:</U></HTML> " + "g");
    breakfastProtein.setForeground(Color.WHITE);
    breakfastProtein.setFont(ToolClass.nutritionPanelFont);

    breakfastFat = new JLabel("<HTML><U>Total Fat:</U></HTML> " + "g");
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

    lunchSideItems = new JComboBox(side);
    lunchSideItems.setBackground(Color.WHITE);
    lunchSideItems.setForeground(ToolClass.fgcuGreen);

    lunchDrinkItems = new JComboBox(drink);
    lunchDrinkItems.setBackground(Color.WHITE);
    lunchDrinkItems.setForeground(ToolClass.fgcuBlue);

    lunchPanel.add(lunchLabel);
    lunchPanel.add(lunchRestaurants);
    lunchPanel.add(lunchFoodItems);
    lunchPanel.add(lunchSideItems);
    lunchPanel.add(lunchDrinkItems);
  }

  public void lunchNutritionItems() {

    lunchNutrition = new JPanel();
    lunchNutrition.setLayout(new FlowLayout());
    lunchNutrition.setVisible(true);
    lunchNutrition.setBackground(ToolClass.fgcuBlue);
    lunchNutrition.setBorder(ToolClass.newCompound);

    lunchCalories = new JLabel("<HTML><U>Total Calories:</U></HTML> ");
    lunchCalories.setForeground(Color.WHITE);
    lunchCalories.setFont(ToolClass.nutritionPanelFont);
    lunchCalories.setHorizontalTextPosition(SwingConstants.LEFT);

    lunchFatCalories = new JLabel("<HTML><U>Total Fat Calories:</U></HTML> " + "g");
    lunchFatCalories.setForeground(Color.WHITE);
    lunchFatCalories.setFont(ToolClass.nutritionPanelFont);

    lunchCarbs = new JLabel("<HTML><U>Total Carbs:</U></HTML> " + "g");
    lunchCarbs.setForeground(Color.WHITE);
    lunchCarbs.setFont(ToolClass.nutritionPanelFont);

    lunchProtein = new JLabel("<HTML><U>Total Protein:</U></HTML> " + "g");
    lunchProtein.setForeground(Color.WHITE);
    lunchProtein.setFont(ToolClass.nutritionPanelFont);

    lunchFat = new JLabel("<HTML><U>Total Fat:</U></HTML> " + "g");
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
    dinnerNutrition.setLayout(new FlowLayout());
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

    snackRestaurants = new JComboBox(restaurants);
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
    snackNutrition.setLayout(new FlowLayout());
    snackNutrition.setVisible(true);
    snackNutrition.setBackground(ToolClass.fgcuBlue);
    snackNutrition.setBorder(ToolClass.newCompound);

    snackCalories = new JLabel("<HTML><U>Total Calories: </U></HTML> ");
    snackCalories.setForeground(Color.WHITE);
    snackCalories.setFont(ToolClass.nutritionPanelFont);
    snackCalories.setHorizontalTextPosition(SwingConstants.LEFT);

    snackFatCalories = new JLabel("<HTML><U>Total Fat Calories: </U></HTML> " + "g");
    snackFatCalories.setForeground(Color.WHITE);
    snackFatCalories.setFont(ToolClass.nutritionPanelFont);

    snackCarbs = new JLabel("<HTML><U>Total Carbs: </U></HTML> " + "g");
    snackCarbs.setForeground(Color.WHITE);
    snackCarbs.setFont(ToolClass.nutritionPanelFont);

    snackProtein = new JLabel("<HTML><U>Total Protein: </U></HTML> " + "g");
    snackProtein.setForeground(Color.WHITE);
    snackProtein.setFont(ToolClass.nutritionPanelFont);

    snackFat = new JLabel("<HTML><U>Total Fat: </U></HTML> " + "g");
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
    totalNutrition.setLayout(new FlowLayout());
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

    submitBtn = new JButton("Submit");
    submitBtn.setVisible(true);
    submitBtn.setForeground(Color.WHITE);
    submitBtn.setBackground(Color.BLUE);
    submitBtn.setOpaque(true);

    ActionListener sumbitButtonHandler = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {

      }
    };
  }

  public void dBConnect() {

    host = "jdbc:sqlite:/Users/iceman371/git/Order-Up/data/studentinfo.db";
    try {
      food_itemsConnect = DriverManager.getConnection(host);
      dbDrive = food_itemsConnect.createStatement();
    } catch (SQLException e) {
      e.printStackTrace();
    }



  }

  public void setEinsteinItems() {

    String einsteinEnt = String.format("SELECT item_name FROM food_item WHERE [item_type = '%s'] AND [", "entree");


    //Reads Einstein's Entree items from db & sets int einEnt String array
    for (int i = 0; i < 3; i++) {


    }
  }
}
