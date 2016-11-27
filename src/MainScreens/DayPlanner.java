package MainScreens;

//import Calendar.CalendarDemo;

import MainScreens.MainMenu.CalendarDemo;
import Utility.Meals;
import Utility.ToolClass;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

//import Calendar.CalendarDemo;
//import oracle.jvm.hotspot.jfr.JFR;

public class DayPlanner extends JFrame {

  JButton submitBtn;
  JPanel dayPlannerPanel;
  JPanel breakfastPanel;
  JPanel lunchPanel;
  JPanel dinnerPanel;
  JPanel snackPanel;
  JPanel breakfastNutrition;
  JLabel breakfastCalories;
  JLabel breakfastFatCalories;
  JLabel breakfastProtein;
  JLabel breakfastCarbs;
  JLabel breakfastFat;
  JPanel lunchNutrition;
  JLabel lunchCalories;
  JLabel lunchFatCalories;
  JLabel lunchProtein;
  JLabel lunchCarbs;
  JLabel lunchFat;
  JPanel dinnerNutrition;
  JLabel dinnerCalories;
  JLabel dinnerFatCalories;
  JLabel dinnerProtein;
  JLabel dinnerCarbs;
  JLabel dinnerFat;
  JPanel snackNutrition;
  JLabel snackCalories;
  JLabel snackFatCalories;
  JLabel snackProtein;
  JLabel snackCarbs;
  JLabel snackFat;
  JPanel totalNutrition;
  JLabel totalCalories;
  JLabel totalFatCalories;
  JLabel totalProtein;
  JLabel totalCarbs;
  JLabel totalFat;
  JLabel breakfastLabel;
  JComboBox breakfastRestaurants;
  JComboBox breakfastFoodItems;
  JComboBox breakfastSideItems;
  JComboBox breakfastDrinkItems;
  JLabel lunchLabel;
  JComboBox lunchRestaurants;
  JComboBox lunchFoodItems;
  JComboBox lunchSideItems;
  JComboBox lunchDrinkItems;
  JLabel dinnerLabel;
  JComboBox dinnerRestaurants;
  JComboBox dinnerFoodItems;
  JComboBox dinnerSideItems;
  JComboBox dinnerDrinkItems;
  JLabel snackLabel;
  JComboBox snackRestaurants;
  JComboBox snackItems;
  Dimension innerPanelDimension = new Dimension(200, 200);
  HashSet<String> restaurantHash = new HashSet<String>();
  ArrayList<String> restaurantList = new ArrayList<String>();
  ArrayList<Meals> mealList = new ArrayList<Meals>();
  String[] ent, side, drink = new String[3];
  String[] restaurants = { "Einstein Bros. Bagels", "Papa Johns", "Brahma Express", "Chick-Fil-A", "Jamba Juice",
      "Starbucks" };
  String[] einEnt = { "Plain Bagel", "Cheesy Bacon Club", "Santa Fe Wrap" };
  String[] chickEnt = { "Chicken Sandwich", "8 Piece Chicken Nuggets", "Chicken Salad" };
  String[] einSide, einDrink, chickSide, chickDrink, brEnt, brSide, brDrink, papaEnt, papaSide, papaDrink, JambaDrink,
      starDrink = new String[3];

  String selectedBreakfastRestaurant = "Einstein Bros. Bagels";
  String selectedLunchRestaurant = "Einstein Bros. Bagels";
  String selectedDinnerRestaurant = "Einstein Bros. Bagels";
  String selectedSnackRestaurant = "Einstein Bros. Bagels";
  BoxHandler boxHandler = new BoxHandler();

  public DayPlanner(String dayText) { // dayText is the day number
    super("Meal Plan for " + "/" + dayText + "/" + CalendarDemo.year);
    ent = einEnt;
    // try {
    // Scanner scanner = new Scanner(new File("Meals.txt"));
    // scanner.useDelimiter(",");
    // while (scanner.hasNext()) {
    // mealList.add(new Meals(scanner.next(), scanner.next(),
    // scanner.nextInt(), scanner.next()));
    // }
    // } catch (FileNotFoundException e) {
    // System.out.println("404 Error, file not found.");
    // }
    //
    // // build the restaurant hashset for the comboboxes
    // for (int i = 0; i < mealList.size(); i++) {
    // restaurantHash.add(mealList.get(i).getRestaurant());
    // }

    dayPlannerPanel = new JPanel();
    setSize(800, 800); // sets the size of the frame
    setLocation(500, 500); // sets the location of the frame on the screen
    dayPlannerPanel.setLayout(null);

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
        updateRestaurant(br, r);
        // update combo boxes
        if (r.equals(restaurants[0])){
          System.out.println("Einsteins");
          ent = einEnt;
        } else if (r.equals(restaurants[1])) {
          System.out.println("papa johns");
        } else if (r.equals(restaurants[2])) {
          System.out.println("Brahma");
        } else if (r.equals(restaurants[3])) {
          System.out.println("Chick-Fil-A");
          ent = chickEnt;
        }
        
      } else if (e.getSource() == lunchRestaurants) {
        JComboBox lr = (JComboBox) e.getSource();
        String r = (String) lr.getSelectedItem();
        updateRestaurant(lr, r);
        // update combo boxes
      } else if (e.getSource() == dinnerRestaurants) {
        JComboBox dr = (JComboBox) e.getSource();
        String r = (String) dr.getSelectedItem();
        updateRestaurant(dr, r);
        // update combo boxes
      } else if (e.getSource() == snackRestaurants) {
        JComboBox sr = (JComboBox) e.getSource();
        String r = (String) sr.getSelectedItem();
        updateRestaurant(sr, r);
        // update combo boxes
      }
      repaint();
      revalidate();
    }
  }

  // updates the placeholder for which combo box the user is selecting
  public void updateRestaurant(JComboBox box, String r) {
    if (box.equals(breakfastRestaurants)) {
      selectedBreakfastRestaurant = r;
    } else if (box.equals(lunchRestaurants)) {
      selectedLunchRestaurant = r;
    } else if (box.equals(dinnerRestaurants)) {
      selectedDinnerRestaurant = r;
    } else if (box.equals(snackRestaurants)) {
      selectedSnackRestaurant = r;
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

    breakfastSideItems = new JComboBox();
    breakfastSideItems.setBackground(Color.WHITE);
    breakfastSideItems.setForeground(ToolClass.fgcuGreen);

    breakfastDrinkItems = new JComboBox();
    breakfastDrinkItems.setBackground(Color.WHITE);
    breakfastDrinkItems.setForeground(ToolClass.fgcuBlue);

    breakfastPanel.add(breakfastLabel);
    breakfastPanel.add(breakfastRestaurants);
    breakfastPanel.add(breakfastFoodItems);
    breakfastPanel.add(breakfastSideItems);
    breakfastPanel.add(breakfastDrinkItems);
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

    lunchSideItems = new JComboBox();
    lunchSideItems.setBackground(Color.WHITE);
    lunchSideItems.setForeground(ToolClass.fgcuGreen);

    lunchDrinkItems = new JComboBox();
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

    dinnerSideItems = new JComboBox();
    dinnerSideItems.setBackground(Color.WHITE);
    dinnerSideItems.setForeground(ToolClass.fgcuGreen);

    dinnerDrinkItems = new JComboBox();
    dinnerDrinkItems.setBackground(Color.WHITE);
    dinnerDrinkItems.setForeground(ToolClass.fgcuBlue);

    dinnerPanel.add(dinnerLabel);
    dinnerPanel.add(dinnerRestaurants);
    dinnerPanel.add(dinnerFoodItems);
    dinnerPanel.add(dinnerSideItems);
    dinnerPanel.add(dinnerDrinkItems);
  }

  public void dinnerNutritionItems() {

    dinnerNutrition = new JPanel();
    dinnerNutrition.setLayout(new FlowLayout());
    dinnerNutrition.setVisible(true);
    dinnerNutrition.setBackground(ToolClass.fgcuBlue);
    dinnerNutrition.setBorder(ToolClass.newCompound);

    dinnerCalories = new JLabel("<HTML><U>Total Calories:</U></HTML> ");
    dinnerCalories.setForeground(Color.WHITE);
    dinnerCalories.setFont(ToolClass.nutritionPanelFont);
    dinnerCalories.setHorizontalTextPosition(SwingConstants.LEFT);

    dinnerFatCalories = new JLabel("<HTML><U>Total Fat Calories:</U></HTML> " + "g");
    dinnerFatCalories.setForeground(Color.WHITE);
    dinnerFatCalories.setFont(ToolClass.nutritionPanelFont);

    dinnerCarbs = new JLabel("<HTML><U>Total Carbs:</U></HTML> " + "g");
    dinnerCarbs.setForeground(Color.WHITE);
    dinnerCarbs.setFont(ToolClass.nutritionPanelFont);

    dinnerProtein = new JLabel("<HTML><U>Total Protein:</U></HTML> " + "g");
    dinnerProtein.setForeground(Color.WHITE);
    dinnerProtein.setFont(ToolClass.nutritionPanelFont);

    dinnerFat = new JLabel("<HTML><U>Total Fat:</U></HTML> " + "g");
    dinnerFat.setForeground(Color.WHITE);
    dinnerFat.setFont(ToolClass.nutritionPanelFont);

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

    snackItems = new JComboBox();
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

  }
}
