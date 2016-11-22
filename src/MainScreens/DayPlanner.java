package MainScreens;

//import Calendar.CalendarDemo;
import Utility.Meals;
//import Calendar.CalendarDemo;
import Utility.ToolClass;
//import oracle.jvm.hotspot.jfr.JFR;

import javax.swing.*;

import MainScreens.MainMenu.CalendarDemo;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/*
 * Created by TylerHall on 10/20/16.
 */
public class DayPlanner extends JFrame {

  JButton submitBtn;
  JPanel dayPlannerPanel;
  JPanel breakfastPanel;
  JPanel lunchPanel;
  JPanel dinnerPanel;
  JPanel snackPanel;
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
  String[] restaurants = new String[0];
  String[] einEnt = {"Plain Bagel", "Cheesy Bacon Club", "Santa Fe Wrap"};
  String[] einSide, einDrink, chickEnt,chickSide,chickDrink,
          brEnt,brSide,brDrink,papaEnt,papaSide,papaDrink,JambaDrink,starDrink = new String[3];
  
  String selectedBreakfastRestaurant = "Einstein Bros. Bagels";
  String selectedLunchRestaurant = "Einstein Bros. Bagels";
  String selectedDinnerRestaurant = "Einstein Bros. Bagels";
  String selectedSnackRestaurant = "Einstein Bros. Bagels";
  BoxHandler boxHandler = new BoxHandler();
  // String[] entreeItems = new String[]{"Plain Bagel","Cheesy Bacon Club","Santa Fe Wrap"};
  // String[] sideItems = new String[]{"Blueberry Muffin","Brownie","Cookie"};
  // String[] drinkItems = new String[]{"Coffee", "Orange Juice", "Chocolate Milk"};
  // String selectedRestaurant = "Einstein Bros. Bagels";

  public DayPlanner(String dayText) { // dayText is the day number
    super("Meal Plan for " + "/" + dayText + "/" + CalendarDemo.year);
    
    try {
      Scanner scanner = new Scanner(new File("Meals.txt"));
      scanner.useDelimiter(",");
      while (scanner.hasNext()) {
        mealList.add(new Meals(scanner.next(), scanner.next(), scanner.nextInt(), scanner.next()));
      }
    } catch (FileNotFoundException e) {
      System.out.println("404 Error, file not found.");
    }
    
    

    // build the restaurant hashset for the comboboxes
    for (int i = 0; i < mealList.size(); i++) {
      restaurantHash.add(mealList.get(i).getRestaurant());
//    public DayPlanner(String dayText) { // dayText is the day number
//        super("Meal Plan for " + "/" + dayText + "/" + MainMenu.CalendarDemo.year);

        dayPlannerPanel = new JPanel();
        setSize(800, 800); //sets the size of the frame
        setLocation(500, 500); //sets the location of the frame on the screen
        dayPlannerPanel.setLayout(null);

        /* Breakfast Panel & Items */
        breakfastPanel = new JPanel();
        breakfastPanel.setLayout(new GridLayout(4, 1));
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

        breakfastFoodItems = new JComboBox();
        breakfastFoodItems.setBackground(Color.WHITE);
        breakfastFoodItems.setForeground(ToolClass.fgcuGreen);
        
        breakfastSideItems = new JComboBox();
        breakfastSideItems.setBackground(Color.WHITE);
        breakfastSideItems.setForeground(ToolClass.fgcuGreen);

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
        lunchLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lunchLabel.setForeground(Color.WHITE);
        lunchLabel.setFont(ToolClass.largerBoldHeadingFont);

        lunchRestaurants = new JComboBox(restaurants);
        lunchRestaurants.setVisible(true);
        lunchRestaurants.setBackground(Color.WHITE);
        lunchRestaurants.setForeground(ToolClass.fgcuBlue);

        lunchFoodItems = new JComboBox();
        lunchFoodItems.setBackground(Color.WHITE);
        lunchFoodItems.setForeground(ToolClass.fgcuGreen);
        
        lunchSideItems= new JComboBox();
        lunchSideItems.setBackground(Color.WHITE);
        lunchSideItems.setForeground(ToolClass.fgcuGreen);

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
        dinnerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        dinnerLabel.setForeground(Color.WHITE);
        dinnerLabel.setFont(ToolClass.largerBoldHeadingFont);

        dinnerRestaurants = new JComboBox(restaurants);
        dinnerRestaurants.setVisible(true);
        dinnerRestaurants.setBackground(Color.WHITE);
        dinnerRestaurants.setForeground(ToolClass.fgcuBlue);

        dinnerFoodItems = new JComboBox();
        dinnerFoodItems.setBackground(Color.WHITE);
        dinnerFoodItems.setForeground(ToolClass.fgcuGreen);
        
        dinnerSideItems = new JComboBox();
        dinnerSideItems.setBackground(Color.WHITE);
        dinnerSideItems.setForeground(ToolClass.fgcuGreen);

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
        snackLabel.setSize(50, 20);
        snackLabel.setHorizontalAlignment(SwingConstants.CENTER);
//        snackLabel.setOpaque(true);
        snackLabel.setBackground(Color.CYAN);
        snackLabel.setForeground(Color.WHITE);
        snackLabel.setFont(ToolClass.largerBoldHeadingFont);

        snackRestaurants = new JComboBox(restaurants);
        snackRestaurants.setVisible(true);
        snackRestaurants.setBackground(Color.WHITE);
        snackRestaurants.setForeground(ToolClass.fgcuBlue);

        snackItems = new JComboBox();
        snackItems.setBackground(Color.WHITE);
        snackItems.setForeground(ToolClass.fgcuGreen);

        submitBtn = new JButton("Submit");
        submitBtn.setVisible(true);
        submitBtn.setForeground(Color.WHITE);
        submitBtn.setBackground(Color.BLUE);
        submitBtn.setOpaque(true);

        breakfastPanel.setBounds(20, 120, 225, 225);
        lunchPanel.setBounds(560, 120, 225, 225);
        snackPanel.setBounds(310, 150, 185, 185);
        dinnerPanel.setBounds(300, 400, 225, 225);
        submitBtn.setBounds(350, 400, 60, 60);

        getContentPane().add(dayPlannerPanel);

        dayPlannerPanel.add(breakfastPanel);
        breakfastPanel.add(breakfastLabel);
        breakfastPanel.add(breakfastRestaurants);
        breakfastPanel.add(breakfastFoodItems);
        breakfastPanel.add(breakfastDrinkItems);

        dayPlannerPanel.add(lunchPanel);
        lunchPanel.add(lunchLabel);
        lunchPanel.add(lunchRestaurants);
        lunchPanel.add(lunchFoodItems);
        lunchPanel.add(lunchDrinkItems);

        dayPlannerPanel.add(dinnerPanel);
        dinnerPanel.add(dinnerLabel);
        dinnerPanel.add(dinnerRestaurants);
        dinnerPanel.add(dinnerFoodItems);
        dinnerPanel.add(dinnerDrinkItems);

        dayPlannerPanel.add(snackPanel);
        snackPanel.add(snackLabel);
        snackPanel.add(snackRestaurants);
        snackPanel.add(snackItems);

        dayPlannerPanel.add(submitBtn);

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
                new MainMenu();
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

        addWindowListener(OnCLose);

    }

    // convert the hashset to an array so we can display it in the combobox
    restaurants = restaurantHash.toArray(restaurants);

    dayPlannerPanel = new JPanel();
    setSize(800, 800); // sets the size of the frame
    setLocation(500, 500); // sets the location of the frame on the screen
    dayPlannerPanel.setLayout(null);

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
    breakfastRestaurants.addActionListener(boxHandler);
    breakfastRestaurants.setVisible(true);
    breakfastRestaurants.setBackground(Color.WHITE);
    breakfastRestaurants.setForeground(ToolClass.fgcuBlue);

    breakfastFoodItems = new JComboBox();
    breakfastFoodItems.setBackground(Color.WHITE);
    breakfastFoodItems.setForeground(ToolClass.fgcuGreen);

    breakfastSideItems = new JComboBox();
    breakfastSideItems.setBackground(Color.WHITE);
    breakfastSideItems.setForeground(ToolClass.fgcuGreen);

    breakfastDrinkItems = new JComboBox();
    breakfastDrinkItems.setBackground(Color.WHITE);
    breakfastDrinkItems.setForeground(ToolClass.fgcuBlue);

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

    lunchFoodItems = new JComboBox();
    lunchFoodItems.setBackground(Color.WHITE);
    lunchFoodItems.setForeground(ToolClass.fgcuGreen);

    lunchSideItems = new JComboBox();
    lunchSideItems.setBackground(Color.WHITE);
    lunchSideItems.setForeground(ToolClass.fgcuGreen);

    lunchDrinkItems = new JComboBox();
    lunchDrinkItems.setBackground(Color.WHITE);
    lunchDrinkItems.setForeground(ToolClass.fgcuBlue);

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

    dinnerFoodItems = new JComboBox();
    dinnerFoodItems.setBackground(Color.WHITE);
    dinnerFoodItems.setForeground(ToolClass.fgcuGreen);

    dinnerSideItems = new JComboBox();
    dinnerSideItems.setBackground(Color.WHITE);
    dinnerSideItems.setForeground(ToolClass.fgcuGreen);

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

    submitBtn = new JButton("Submit");
    submitBtn.setVisible(true);
    submitBtn.setForeground(Color.WHITE);
    submitBtn.setBackground(Color.BLUE);
    submitBtn.setOpaque(true);

    breakfastPanel.setBounds(20, 120, 225, 225);
    lunchPanel.setBounds(560, 120, 225, 225);
    snackPanel.setBounds(310, 150, 185, 185);
    dinnerPanel.setBounds(300, 400, 225, 225);
    submitBtn.setBounds(350, 400, 60, 60);

    getContentPane().add(dayPlannerPanel);

    dayPlannerPanel.add(breakfastPanel);
    breakfastPanel.add(breakfastLabel);
    breakfastPanel.add(breakfastRestaurants);
    breakfastPanel.add(breakfastFoodItems);
    breakfastPanel.add(breakfastSideItems);
    breakfastPanel.add(breakfastDrinkItems);

    dayPlannerPanel.add(lunchPanel);
    lunchPanel.add(lunchLabel);
    lunchPanel.add(lunchRestaurants);
    lunchPanel.add(lunchFoodItems);
    lunchPanel.add(lunchSideItems);
    lunchPanel.add(lunchDrinkItems);

    dayPlannerPanel.add(dinnerPanel);
    dinnerPanel.add(dinnerLabel);
    dinnerPanel.add(dinnerRestaurants);
    dinnerPanel.add(dinnerFoodItems);
    dinnerPanel.add(dinnerSideItems);
    dinnerPanel.add(dinnerDrinkItems);

    dayPlannerPanel.add(snackPanel);
    snackPanel.add(snackLabel);
    snackPanel.add(snackRestaurants);
    snackPanel.add(snackItems);

    dayPlannerPanel.add(submitBtn);

    dayPlannerPanel.setBackground(ToolClass.fgcuGreen);
    setVisible(true);

    // changed this to Dispose so it won't close the entire program
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

  }

  private class BoxHandler implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      if (e.getSource() == breakfastRestaurants) {
        JComboBox br = (JComboBox) e.getSource();
        String r = (String) br.getSelectedItem();
        updateRestaurant(br,r);
        //update combo boxes
      } else if (e.getSource() == lunchRestaurants) {
        JComboBox lr = (JComboBox) e.getSource();
        String r = (String) lr.getSelectedItem();
        updateRestaurant(lr,r);
        //update combo boxes
      } else if (e.getSource() == dinnerRestaurants) {
        JComboBox dr = (JComboBox) e.getSource();
        String r = (String) dr.getSelectedItem();
        updateRestaurant(dr,r);
        //update combo boxes
      } else if (e.getSource() == snackRestaurants) {
        JComboBox sr = (JComboBox) e.getSource();
        String r = (String) sr.getSelectedItem();
        updateRestaurant(sr, r);
        //update combo boxes
      }
      System.out.println(selectedBreakfastRestaurant);
      System.out.println(selectedLunchRestaurant);
      System.out.println(selectedDinnerRestaurant);
      System.out.println(selectedSnackRestaurant);
    }
  }
  
  //updates the placeholder for which combo box the user is selecting
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
}
