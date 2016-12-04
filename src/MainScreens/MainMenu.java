package MainScreens;

import Utility.ToolClass;
import acm.gui.TableLayout;
import acm.gui.VPanel;
import acm.program.Program;
import acm.util.JTFTools;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

///////////////////////////////////
/*
 * File: CalendarDemo.java
 * -----------------------
 * This program uses the GUI layout mechanism to create a calendar
 * page.  The program uses the features of Java's Locale class to
 * internationalize the calendar.
 */
///////////////////////////////

public class MainMenu {

    JLabel studentNameLabel, pointsLabel, totalPoints, totalPointsLabel, remainingPointsLabel,
            remainingPoints, smallLogoholderLabel;
    static JFrame menu;
    JButton myMealPlan;
    Calendar thisDay;
    static JPanel mainMenuPanel;
    static String dayOfMonthStr;
    static int dayOfmonth;
    static JPanel dayAtAGlancePanel, dayAtAGlanceDatePanel, mealGlancePanel, breakfastGlancePanel, lunchGlancePanel,
            dinnerGlancePanel, nutritionGlancePanel;
    static JLabel dayAtAGlanceDate, breakFastTitle, breakfastRestaurant, breakfastFoodItem,
            breakfastSideItem, breakfastDrinkItem,
            lunchTitle, lunchRestaurant, lunchFoodItem, lunchSideItem, lunchDrinkItem, dinnerTitle,
            dinnerRestaurant, dinnerFoodItem, dinnerSideItem, dinnerDrinkItem, totalCal, totalFatCal,
            totalCarbs, totalProtein, totalPointsUsed;
    static String breakFastTitleV, breakfastRestaurantV, breakfastFoodItemV, breakfastSideItemV, breakfastDrinkItemV,
            lunchTitleV, lunchRestaurantV, lunchFoodItemV, lunchSideItemV, lunchDrinkItemV, dinnerTitleV, dinnerRestaurantV,
            dinnerFoodItemV, dinnerSideItemV, dinnerDrinkItemV;
    static int totalCalV, totalFatCalV, totalCarbsV, totalProteinV, totalPointsUsedV;
    Font totalPointsLabelFont, remainingPointsLabelFont;

    String logoURL = "http://aandeautos4theneedy.org/wp-content/uploads/2016/12/OrderUpLogo-small.png";
    ImageIcon orderUpLogoSmall;
    JButton calorieCalculator;
    static ResultSet nutriResult;

    public MainMenu() throws FileNotFoundException, SQLException {

        //Initialization of menu JFrame
        menu = new JFrame("Main Menu");
        menu.setSize(975, 700); //sets the size of the frame
        menu.setLocationRelativeTo(null); //sets the location of the frame on the screen

        thisDay = Calendar.getInstance();
        dayOfmonth = thisDay.get(Calendar.DAY_OF_MONTH);
        dayOfMonthStr = String.valueOf(dayOfmonth);

        //Initialization of encasing mainMenuPanel
        //Layout set to null, so that individual object bounds can be set
        mainMenuPanel = new JPanel();
        mainMenuPanel.setLayout(null);
        mainMenuPanel.setBackground(ToolClass.fgcuBlue);

        //Calls createImageIcon from ToolClass to add logo ImageIcon & then adds to orderUpLogoSmall
        orderUpLogoSmall = ToolClass.createImageIcon(logoURL, "Order-Up Logo");
        smallLogoholderLabel = new JLabel(orderUpLogoSmall);

        //Initialization and settings for student Name Label
        studentNameLabel = new JLabel(LogInScreen.firstName + " " + LogInScreen.lastName);
        studentNameLabel.setFont(ToolClass.smallBoldHeadingFont);
        studentNameLabel.setForeground(ToolClass.fgcuBlue);
        studentNameLabel.setBackground(Color.white);
        studentNameLabel.setOpaque(true);
        studentNameLabel.setBorder(ToolClass.fgcuGreenLine);
        studentNameLabel.setHorizontalAlignment(SwingConstants.CENTER);

        //Initialization of calorieCalculator
        calorieCalculator = new JButton("Calorie Calculator");
        calorieMouseHandler handler = new calorieMouseHandler();
        calorieCalculator.addMouseListener(handler);

        // Initialization and settings for points Label
        pointsLabel = new JLabel("Points");
        pointsLabel.setForeground(Color.white);
        pointsLabel.setFont(ToolClass.largerBoldHeadingFont);

        // Initialization and settings for total Points Label
        totalPointsLabel = new JLabel("Total ");
        totalPointsLabel.setFont(ToolClass.smallItalicHeadingFont);
        totalPointsLabel.setForeground(Color.white);

        // Initialization and settings for total Points output
        totalPoints = new JLabel(String.valueOf(LogInScreen.userPointTotal));
        totalPoints.setFont(ToolClass.smallBoldHeadingFont);
        totalPoints.setForeground(ToolClass.fgcuGreen);
        totalPoints.setBackground(Color.white);
        totalPoints.setOpaque(true);
        totalPoints.setBorder(ToolClass.fgcuGreenLine);
        totalPoints.setHorizontalAlignment(SwingConstants.CENTER);

        // Initialization and settings for remaining Points Label
        remainingPointsLabel = new JLabel("Remaining ");
        remainingPointsLabel.setFont(ToolClass.smallItalicHeadingFont);
        remainingPointsLabel.setForeground(Color.white);

        // Initialization and settings for remaining Points output

        int remainingPointsValue = LogInScreen.userPointTotal - (LogInScreen.studentInfoCon.createStatement().executeQuery("SELECT sum(points_used) from '" + LogInScreen.universityID + "'").getInt("sum(points_used)"));

        remainingPoints = new JLabel(String.valueOf(remainingPointsValue)); // <----- Needs to display remaining points from db
        remainingPoints.setFont(ToolClass.smallBoldHeadingFont);
        remainingPoints.setForeground(ToolClass.fgcuGreen);
        remainingPoints.setBackground(Color.yellow);
        remainingPoints.setOpaque(true);
        remainingPoints.setBorder(ToolClass.fgcuGreenLine);
        remainingPoints.setHorizontalAlignment(SwingConstants.CENTER);

        // Initialization and settings for my Meal Plan button
        myMealPlan = new JButton("My Meal Plan");
        myMealPlan.setForeground(ToolClass.fgcuGreen);

        //Initialization of outer panel for Day-At-A-Glance output
        dayAtAGlancePanel = new JPanel();
        dayAtAGlancePanel.setLayout(new GridLayout(3, 1));
        dayAtAGlancePanel.setVisible(true);
        dayAtAGlancePanel.setBackground(Color.WHITE);

        dayAtAGlanceDatePanel = new JPanel();
        dayAtAGlanceDatePanel.setLayout(new FlowLayout());
        dayAtAGlanceDatePanel.setVisible(true);
        dayAtAGlanceDatePanel.setBackground(Color.WHITE);

        /* Initialization of JLabel to hold current Day-At-A-Glance date,
         * Which is updated in the vboxListener MouseListener method */
        dayAtAGlanceDate = new JLabel("");
        dayAtAGlanceDate.setFont(new Font(Font.SANS_SERIF, Font.ITALIC + Font.BOLD, 12));
        dayAtAGlanceDate.setForeground(ToolClass.fgcuGreen);
        dayAtAGlanceDate.setHorizontalTextPosition(SwingConstants.CENTER);

        dayAtAGlanceDatePanel.add(dayAtAGlanceDate, SwingConstants.CENTER);

        //Initialization of breakfastGlancePanel, which holds all breakfast meal items
        breakfastGlancePanel = new JPanel();
        breakfastGlancePanel.setLayout(new GridLayout(5, 1));
        breakfastGlancePanel.setVisible(true);
        breakfastGlancePanel.setBackground(Color.WHITE);

        //Initialization of all breakfast meal items, including fonts
        breakFastTitle = new JLabel("<HTML><U>BREAKFAST</U> </HTML>");
        breakFastTitle.setFont(ToolClass.smallestBoldHeadingFont);
        breakFastTitle.setForeground(ToolClass.fgcuBlue);
        breakfastRestaurant = new JLabel("Restaurant: " + breakfastRestaurantV + "  ");
        breakfastRestaurant.setFont(ToolClass.dayAtAGlanceMealFont);
        breakfastFoodItem = new JLabel("Entree: " + breakfastFoodItemV + "  ");
        breakfastFoodItem.setFont(ToolClass.dayAtAGlanceMealFont);
        breakfastSideItem = new JLabel("Side: " + breakfastSideItemV + "  ");
        breakfastSideItem.setFont(ToolClass.dayAtAGlanceMealFont);
        breakfastDrinkItem = new JLabel("Drink: " + breakfastDrinkItemV + "  ");
        breakfastDrinkItem.setFont(ToolClass.dayAtAGlanceMealFont);

        //Adds all breakfast items to breakfastGlancePanel
        breakfastGlancePanel.add(breakFastTitle);
        breakfastGlancePanel.add(breakfastRestaurant);
        breakfastGlancePanel.add(breakfastFoodItem);
        breakfastGlancePanel.add(breakfastSideItem);
        breakfastGlancePanel.add(breakfastDrinkItem);

        //Initialization of lunchGlancePanel, which holds all lunch meal items
        lunchGlancePanel = new JPanel();
        lunchGlancePanel.setLayout(new GridLayout(5, 1));
        lunchGlancePanel.setVisible(true);
        lunchGlancePanel.setBackground(Color.WHITE);

        //Initialization of all lunch meal items, including fonts
        lunchTitle = new JLabel("<HTML><U>LUNCH</U></HTML>");
        lunchTitle.setFont(ToolClass.smallestBoldHeadingFont);
        lunchTitle.setForeground(ToolClass.fgcuBlue);
        lunchRestaurant = new JLabel("Restaurant: " + lunchRestaurantV + "  ");
        lunchRestaurant.setFont(ToolClass.dayAtAGlanceMealFont);
        lunchFoodItem = new JLabel("Entree: " + lunchFoodItemV + "  ");
        lunchFoodItem.setFont(ToolClass.dayAtAGlanceMealFont);
        lunchSideItem = new JLabel("Side: " + lunchSideItemV + "  ");
        lunchSideItem.setFont(ToolClass.dayAtAGlanceMealFont);
        lunchDrinkItem = new JLabel("Drink: " + lunchDrinkItemV + "  ");
        lunchDrinkItem.setFont(ToolClass.dayAtAGlanceMealFont);

        //Adds all lunch items to lunchGlancePanel
        lunchGlancePanel.add(lunchTitle);
        lunchGlancePanel.add(lunchRestaurant);
        lunchGlancePanel.add(lunchFoodItem);
        lunchGlancePanel.add(lunchSideItem);
        lunchGlancePanel.add(lunchDrinkItem);

        //Initialization of dinnerGlancePanel, which holds all dinner meal items
        dinnerGlancePanel = new JPanel();
        dinnerGlancePanel.setLayout(new GridLayout(5, 1));
        dinnerGlancePanel.setVisible(true);
        dinnerGlancePanel.setBackground(Color.WHITE);

        //Initialization of all dinner meal items, including fonts
        dinnerTitle = new JLabel("<HTML><U>DINNER</U></HTML>");
        dinnerTitle.setFont(ToolClass.smallestBoldHeadingFont);
        dinnerTitle.setForeground(ToolClass.fgcuBlue);
        dinnerRestaurant = new JLabel("Restaurant: " + dinnerRestaurantV);
        dinnerRestaurant.setFont(ToolClass.dayAtAGlanceMealFont);
        dinnerFoodItem = new JLabel("Entree: " + dinnerFoodItemV);
        dinnerFoodItem.setFont(ToolClass.dayAtAGlanceMealFont);
        dinnerSideItem = new JLabel("Side: " + dinnerSideItemV);
        dinnerSideItem.setFont(ToolClass.dayAtAGlanceMealFont);
        dinnerDrinkItem = new JLabel("Drink: " + dinnerDrinkItemV);
        dinnerDrinkItem.setFont(ToolClass.dayAtAGlanceMealFont);

        //Adds all dinner items to dinnerGlancePanel
        dinnerGlancePanel.add(dinnerTitle);
        dinnerGlancePanel.add(dinnerRestaurant);
        dinnerGlancePanel.add(dinnerFoodItem);
        dinnerGlancePanel.add(dinnerSideItem);
        dinnerGlancePanel.add(dinnerDrinkItem);

        //Initialization of mealGlancePanel, which will hold all 3 meal panels
        mealGlancePanel = new JPanel();
        mealGlancePanel.setLayout(new GridLayout(1, 3));
        mealGlancePanel.setVisible(true);
        mealGlancePanel.setBackground(Color.WHITE);

        //Adds all three meal panels to mealGlancePanel
        mealGlancePanel.add(breakfastGlancePanel);
        mealGlancePanel.add(lunchGlancePanel);
        mealGlancePanel.add(dinnerGlancePanel);

        //Initialization of nutritionGlancePanel, which will hold all nutrition items
        nutritionGlancePanel = new JPanel();
        nutritionGlancePanel.setLayout(new GridLayout(2, 3));
        nutritionGlancePanel.setVisible(true);
        nutritionGlancePanel.setBackground(Color.WHITE);

        //Initialization of all nutrition JLabels, including fonts and foreground colors
        totalCal = new JLabel("<HTML><U>Total Calories:</U> " + "  " + totalCalV + " </HTML>" + "  ");
        totalCal.setFont(ToolClass.nutritionPanelFont);
        totalCal.setForeground(ToolClass.fgcuBlue);
        totalFatCal = new JLabel("<HTML><U>Total Fat Calories:</U> " + "  " + totalFatCalV + "</HTML>" + "  ");
        totalFatCal.setFont(ToolClass.nutritionPanelFont);
        totalFatCal.setForeground(ToolClass.fgcuBlue);
        totalCarbs = new JLabel("<HTML><U>Total Carbs:</U> " + "  " + totalCarbsV + " </HTML>" + "  ");
        totalCarbs.setFont(ToolClass.nutritionPanelFont);
        totalCarbs.setForeground(ToolClass.fgcuBlue);
        totalProtein = new JLabel("<HTML><U>Total Protein:</U>" + "  " + totalProteinV + " </HTML>" + "  ");
        totalProtein.setFont(ToolClass.nutritionPanelFont);
        totalProtein.setForeground(ToolClass.fgcuBlue);
        totalPointsUsed = new JLabel("<HTML><U>Total Points Used:</U> " + "  " + totalProteinV + " </HTML>");
        totalPointsUsed.setFont(ToolClass.nutritionPanelFont);
        totalPointsUsed.setForeground(ToolClass.fgcuBlue);

        //Adds all nutrition items to nutritionGlancePanel
        nutritionGlancePanel.add(totalCal);
        nutritionGlancePanel.add(totalFatCal);
        nutritionGlancePanel.add(totalCarbs);
        nutritionGlancePanel.add(totalProtein);
        nutritionGlancePanel.add(totalPointsUsed);

        //Adds all containing panels to dayAtAGlancePanel
        dayAtAGlancePanel.add(dayAtAGlanceDatePanel);
        dayAtAGlancePanel.add(mealGlancePanel);
        dayAtAGlancePanel.add(nutritionGlancePanel);

        //Bounds / placement settings for all objects in mainMenuPanel
        smallLogoholderLabel.setBounds(49, 10, 100, 87);
        studentNameLabel.setBounds(20, 100, 160, 40);
        pointsLabel.setBounds(850, 10, 100, 50);
        totalPoints.setBounds(875, 55, 85, 30);
        totalPointsLabel.setBounds(826, 47, 85, 40);
        remainingPoints.setBounds(875, 84, 85, 30);
        remainingPointsLabel.setBounds(795, 75, 100, 40);
        calorieCalculator.setBounds(807, 135, 150, 30);
        myMealPlan.setBounds(585, 153, 100, 40);
        dayAtAGlancePanel.setBounds(190, 20, 590, 180);

        /* Adds all Container objects to encasing mainMenuPanel,
          aside from dayAtAGlancePanel, which is added
          in vboxListener's mouseEntered() method */
        mainMenuPanel.add(smallLogoholderLabel);
        mainMenuPanel.add(studentNameLabel);
        mainMenuPanel.add(pointsLabel);
        mainMenuPanel.add(totalPoints);
        mainMenuPanel.add(totalPointsLabel);
        mainMenuPanel.add(remainingPoints);
        mainMenuPanel.add(remainingPointsLabel);
        mainMenuPanel.add(calorieCalculator);

        //Adds CalendarDemo object to mainMenuPanel & formats placement
        CalendarDemo cal = new CalendarDemo();
        mainMenuPanel.add(cal);
        cal.setBounds(128, 215, 700, 455);
        cal.setVisible(true);


        /* Adds mainMenuPanel to menu JFrame and sets
           program to close upon Close of window */
        menu.add(mainMenuPanel);
        menu.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        menu.setVisible(true);
    }

    public static class CalendarDemo extends Program implements ItemListener {
        /* Private constants */
        private static final String TITLE_FONT = "Serif-36";
        private static final String LABEL_FONT = "Serif-bold-14";
        private static final String DATE_FONT = "Serif-18";
        private static final Locale[] LOCALES = {new Locale("en", "US", "")};
        public static int month;
        public static int year;
        public static int currentDay;

        public static String datePrimaryKey;

        /* Private instance variables */
        private JLabel localeChooser;
        private String[] countries;
        private Calendar currentCalendar;
        private DateFormatSymbols symbols;
        public static String[] monthNames;
        private String[] weekdayNames;
        private int firstDayOfWeek;


        public CalendarDemo() {
            init();
        }

        /**
         * Initialize the graphical user interface
         */
        public void init() {

            setBackground(Color.WHITE);
            initCountryList();
            localeChooser = new JLabel(countries[0]);
            String country = Locale.getDefault().getDisplayCountry();
            add(new JButton("<-"), NORTH);
            add(localeChooser, NORTH);
            add(new JButton("->"), NORTH);
            currentCalendar = Calendar.getInstance();
            itemStateChanged(null);
            addActionListeners();
        }

        /**
         * Respond to a button action
         */
        public void actionPerformed(ActionEvent e) {
            int delta = (e.getActionCommand().equals("<-")) ? -1 : +1;
            currentCalendar.add(Calendar.MONTH, delta);
            updateCalendarDisplay(currentCalendar);
        }

        /**
         * Respond to a change in the locale selection
         */
        public void itemStateChanged(ItemEvent e) {
            if (e == null || e.getStateChange() == ItemEvent.SELECTED) {
                Date time = currentCalendar.getTime();
                Locale locale = LOCALES[0];
                currentCalendar = Calendar.getInstance(locale);
                currentCalendar.setTime(time);
                symbols = new DateFormatSymbols(locale);
                weekdayNames = symbols.getWeekdays();
                monthNames = symbols.getMonths();
                firstDayOfWeek = currentCalendar.getFirstDayOfWeek();
                updateCalendarDisplay(currentCalendar);
            }
        }


        /* Update the calendar display when a new month is selected */
        private void updateCalendarDisplay(Calendar calendar) {
            removeAll();
            setLayout(new TableLayout(0, 7, -1, -1));
            add(createMonthLabel(calendar), "gridwidth=7 bottom=3");
            for (int i = 0; i < 7; i++) {
                add(createWeekdayLabel(i), "weightx=1 width=1 bottom=2");
            }
            int weekday = getFirstWeekdayIndex(calendar);
            for (int i = 0; i < weekday; i++) {
                add(createDayBox(null), "weighty=1");
            }
            int nDays = getDaysInMonth(calendar);
            for (int day = 1; day <= nDays; day++) {
                add(createDayBox("" + day), "weighty=1");
                weekday = (weekday + 1) % 7;
            }
            while (weekday != 0) {
                add(createDayBox(null), "weighty=1");
                weekday = (weekday + 1) % 7;
            }
            validate();
        }


        /* Generate the header label for a particular month */
        private JLabel createMonthLabel(Calendar calendar) {
            month = calendar.get(Calendar.MONTH);
            year = calendar.get(Calendar.YEAR);
            String monthName = capitalize(monthNames[month]);
            JLabel label = new JLabel(monthName + " " + year);
            label.setFont(JTFTools.decodeFont(TITLE_FONT));
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setForeground(ToolClass.fgcuGreen);
            return label;
        }

        /* Create a label for the weekday header at the specified index */
        private JLabel createWeekdayLabel(int index) {
            int weekday = (firstDayOfWeek + index + 6) % 7 + 1;
            JLabel label = new JLabel(capitalize(weekdayNames[weekday]));
            label.setFont(JTFTools.decodeFont(LABEL_FONT));
            label.setHorizontalAlignment(JLabel.CENTER);
            return label;
        }

        /* Compute the number of days in the current month */
        private int getDaysInMonth(Calendar calendar) {
            calendar = (Calendar) calendar.clone();
            int current = calendar.get(Calendar.DAY_OF_MONTH);
            int next = current;
            while (next >= current) {
                current = next;
                calendar.add(Calendar.DAY_OF_MONTH, 1);
                next = calendar.get(Calendar.DAY_OF_MONTH);
            }
            return current;
        }

        /* Compute the index of the first weekday for the current Locale */
        private int getFirstWeekdayIndex(Calendar calendar) {
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            int weekday = calendar.get(Calendar.DAY_OF_WEEK);
            int weekdayIndex = (weekday + 7 - firstDayOfWeek) % 7;
            return ((5 * 7 + 1) + weekdayIndex - day) % 7;
        }

        /* Create a box for a calendar day containing the specified text */
        private Component createDayBox(String dayText) {

        /*This is the action listener for the buttons on the calendar.
        * It'll make the btn create a new DayPlanner Window when clicked.*/
            ActionListener plannerBtnListener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        new DayPlanner(dayText);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    } catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                    }
//                    MainMenu.menu.dispose();
                    MainMenu.menu.setVisible(false);
                }
            };

            /* This MouseListener is used to run a query that will populate the JLabels
             * in dayAtAGlancePanel with meal and nutrition values from the database*/
            MouseListener vboxListener = new MouseListener() {

                @Override //Mandatory Interface method implementation - never used
                public void mouseClicked(MouseEvent e) {
                }

                @Override //Mandatory Interface method implementation - never used
                public void mousePressed(MouseEvent e) {
                }

                @Override //Mandatory Interface method implementation - never used
                public void mouseReleased(MouseEvent e) {
                }

                @Override
                public void mouseEntered(MouseEvent e) {

                    MainMenu.mainMenuPanel.add(MainMenu.dayAtAGlancePanel);
                    menu.validate();

                    dayAtAGlanceDate.setText(" Day-At-A Glance For " + String.valueOf(month + 1) + "/" + dayText + "/" + String.valueOf(year));
                    datePrimaryKey = String.format("%d%02d%02d", year, month + 1, Integer.parseInt(dayText));

                    String uin = String.valueOf(LogInScreen.universityID);

                    String sql123 = String.format("SELECT breakfast_restaurant, breakfast_food, breakfast_side, " +
                                    "breakfast_drink, lunch_restaurant, lunch_food, lunch_side, lunch_drink, dinner_restaurant,"
                                    + "dinner_food, dinner_side, dinner_drink, total_calories, total_fat_cal, total_carbs, " +
                                    "total_protein, points_used FROM '%s' WHERE date= '%d%02d%02d'", uin, year, (month + 1),
                            Integer.parseInt(dayText));

                    try {
//                        Statement state = LogInScreen.studentInfoCon.createStatement();
                        nutriResult = LogInScreen.studentInfoCon.createStatement().executeQuery(sql123);

                        breakfastRestaurantV = nutriResult.getString("breakfast_restaurant");
//                        System.out.println(breakfastRestaurantV);
                        breakfastRestaurant.setText("Restaurant: " + breakfastRestaurantV + " ");

                        breakfastFoodItemV = nutriResult.getString("breakfast_food");
//                        System.out.println(breakfastFoodItemV);
                        breakfastFoodItem.setText("Entree: " + breakfastFoodItemV + " ");

                        breakfastSideItemV = nutriResult.getString("breakfast_side");
//                        System.out.println(breakfastSideItemV);
                        breakfastSideItem.setText("Side: " + breakfastSideItemV + " ");

                        breakfastDrinkItemV = nutriResult.getString("breakfast_drink");
//                        System.out.println(breakfastDrinkItemV);
                        breakfastDrinkItem.setText("Drink: " + breakfastDrinkItemV + " ");

                        lunchRestaurantV = nutriResult.getString("lunch_restaurant");
//                        System.out.println(lunchRestaurantV);
                        lunchRestaurant.setText("Restaurant: " + lunchRestaurantV + " ");

                        lunchFoodItemV = nutriResult.getString("lunch_food");
//                        System.out.println(lunchFoodItemV);
                        lunchFoodItem.setText("Entree: " + lunchFoodItemV + " ");

                        lunchSideItemV = nutriResult.getString("lunch_side");
//                        System.out.println(lunchSideItemV);
                        lunchSideItem.setText("Side: " + lunchSideItemV + " ");

                        lunchDrinkItemV = nutriResult.getString("lunch_drink");
//                        System.out.println(lunchDrinkItemV);
                        lunchDrinkItem.setText("Drink: " + lunchDrinkItemV + " ");

                        dinnerRestaurantV = nutriResult.getString("dinner_restaurant");
//                        System.out.println(dinnerRestaurantV);
                        dinnerRestaurant.setText("Restaurant: " + dinnerRestaurantV + " ");

                        dinnerFoodItemV = nutriResult.getString("dinner_food");
//                        System.out.println(dinnerFoodItemV);
                        dinnerFoodItem.setText("Entree: " + dinnerFoodItemV + " ");

                        dinnerSideItemV = nutriResult.getString("dinner_side");
//                        System.out.println(dinnerSideItemV);
                        dinnerSideItem.setText("Side: " + dinnerSideItemV + " ");

                        dinnerDrinkItemV = nutriResult.getString("dinner_drink");
//                        System.out.println(dinnerDrinkItemV);
                        dinnerDrinkItem.setText("Drink: " + dinnerDrinkItemV + " ");

                        totalCalV = nutriResult.getInt("total_calories");
//                        System.out.println(totalCalV);
                        totalCal.setText("Total Calories: " + totalCalV + " ");

                        totalFatCalV = nutriResult.getInt("total_fat_cal");
//                        System.out.println(totalFatCalV);
                        totalFatCal.setText("Total Fat Calories: " + totalFatCalV + " ");

                        totalCarbsV = nutriResult.getInt("total_carbs");
//                        System.out.println(totalCarbsV);
                        totalCarbs.setText("Total Carbs: " + totalCarbsV + " ");

                        totalProteinV = nutriResult.getInt("total_protein");
//                        System.out.println(totalProteinV);
                        totalProtein.setText("Total Protein: " + totalProteinV + " ");

                        totalPointsUsedV = nutriResult.getInt("points_used");
//                        System.out.println(totalPointsUsedV);
                        totalPointsUsed.setText("Total Points Used: " + totalPointsUsedV + " ");

                        repaint();
                    } catch (SQLException e1) {
//                        e1.printStackTrace();
                        breakfastRestaurantV = "";
                        breakfastRestaurant.setText("Restaurant: " + breakfastRestaurantV + " ");

                        breakfastFoodItemV = "";
                        breakfastFoodItem.setText("Entree: " + breakfastRestaurantV + " ");

                        breakfastSideItemV = "";
                        breakfastSideItem.setText("Side: " + breakfastSideItemV + " ");

                        breakfastDrinkItemV = "";
                        breakfastDrinkItem.setText("Drink: " + breakfastDrinkItemV + " ");

                        lunchRestaurantV = "";
                        lunchRestaurant.setText("Restaurant: " + lunchRestaurantV + " ");

                        lunchFoodItemV = "";
                        lunchFoodItem.setText("Entree: " + lunchFoodItemV + " ");

                        lunchSideItemV = "";
                        lunchSideItem.setText("Side: " + lunchSideItemV + " ");

                        lunchDrinkItemV = "";
                        lunchDrinkItem.setText("Drink: " + lunchDrinkItemV + " ");

                        dinnerRestaurantV = "";
                        dinnerRestaurant.setText("Restaurant: " + dinnerRestaurantV + " ");

                        dinnerFoodItemV = "";
                        dinnerFoodItem.setText("Entree: " + dinnerFoodItemV + " ");

                        dinnerSideItemV = "";
                        dinnerSideItem.setText("Side: " + dinnerSideItemV + " ");

                        dinnerDrinkItemV = "";
                        dinnerDrinkItem.setText("Drink: " + dinnerDrinkItemV + " ");

                        totalCalV = 0;
                        totalCal.setText("Total Calories: " + totalCalV + " ");

                        totalFatCalV = 0;
                        totalFatCal.setText("Total Fat Calories: " + totalFatCalV + " ");

                        totalCarbsV = 0;
                        totalCarbs.setText("Total Carbs: " + totalCarbsV + " ");

                        totalProteinV = 0;
                        totalProtein.setText("Total Protein: " + totalProteinV + " ");

                        totalPointsUsedV = 0;
                        totalPointsUsed.setText("Total Points Used: " + totalPointsUsedV + " ");
                    }
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    MainMenu.mainMenuPanel.remove(MainMenu.dayAtAGlancePanel);
                    menu.revalidate();
                    menu.repaint();
                }
            };

            VPanel vbox = new VPanel();

            //Adds a layout manager to the VPanel object, so that setSize() will work - TH
            vbox.setLayout(new BorderLayout());

            if (dayText == null) {
                vbox.setBackground(ToolClass.fgcuLightBlue);
            } else {

            /*Added a BorderLayout to plannerBtn, so as to set
            positions for dayDisplay and pointsUsed, which will
            display the day and the points used on that day,
            respectively - TH */

                JButton plannerBtn = new JButton();
                plannerBtn.setLayout(new BorderLayout());

                JLabel dayDisplay = new JLabel(dayText);
                dayDisplay.setFont(ToolClass.smallBoldHeadingFont);

                if (dayText.equals(MainMenu.dayOfMonthStr)) {
                    plannerBtn.setBackground(ToolClass.fgcuLightBlue);
                    plannerBtn.setOpaque(true);
                }

            /*pointsUsed is currently set to a static value, but
            will eventually reflect the number of points used
            on that particular day - TH */

                plannerBtn.add(dayDisplay, BorderLayout.NORTH); //Adds dayDisplay to the plannerBtn
                plannerBtn.setSize(vbox.getWidth(), vbox.getHeight());
                plannerBtn.addActionListener(plannerBtnListener); // Adds an ActionListener to plannerBtn
                vbox.add(plannerBtn); // Adds plannerBtn to the individual day (vbox)

                vbox.setBackground(Color.WHITE);
                vbox.addMouseListener(vboxListener);
                plannerBtn.addMouseListener(vboxListener);
            }
            vbox.setOpaque(true);
            vbox.setBorder(new LineBorder(Color.BLACK));


            return vbox;
        }

        /* Create a list of country names from the list of Locales */
        private void initCountryList() {
            countries = new String[LOCALES.length];
            for (int i = 0; i < LOCALES.length; i++) {
                countries[i] = LOCALES[i].getDisplayCountry();
            }
        }

        /* Capitalize the first letter of a word */
        public static String capitalize(String word) {
            return word.substring(0, 1).toUpperCase() + word.substring(1);
        }
    }

    //handles listeners for calorie calculator, didn't want to touch the
    // behemoth below this
    private class calorieMouseHandler implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {

            CalorieCalculator starter = new CalorieCalculator();
            starter.setVisible(true);
            starter.setSize(420, 420);
            starter.setLocationRelativeTo(null);
            starter.setResizable(false);
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
}
