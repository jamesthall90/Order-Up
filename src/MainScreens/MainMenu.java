package MainScreens;

import Utility.ToolClass;
import acm.gui.TableLayout;
import acm.gui.VPanel;
import acm.program.Program;
import acm.util.JTFTools;

import javax.security.auth.login.LoginContext;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
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
    static JFrame menu;

    static JPanel mainMenuPanel;
    JLabel studentNameLabel;
    JLabel pointsLabel;
    JLabel totalPoints;
    JLabel totalPointsLabel;
    JLabel remainingPointsLabel;
    JLabel remainingPoints;
    JButton myMealPlan;
    Calendar thisDay;
    static String dayOfMonthStr;
    static int dayOfmonth;
    static JPanel testPanel;
    static JLabel testLabel;

    Font totalPointsLabelFont, remainingPointsLabelFont;
    //Logo should be added to shorter URL for code convention purposes
    String logoURL = "https://f9149b6c-a-62cb3a1a-s-sites.googlegroups.com/site/outstandingprogramming/documents/OrderUpLogo%20small.png";
    ImageIcon orderUpLogoSmall;
    JLabel smallLogoholderLabel;
    JButton calorieCalculator;


    public MainMenu() {

        menu = new JFrame("Main Menu");

        thisDay = Calendar.getInstance();
        dayOfmonth = thisDay.get(Calendar.DAY_OF_MONTH);
        dayOfMonthStr = String.valueOf(dayOfmonth);

        //Font objects for various labels

        totalPointsLabelFont = new Font(Font.SANS_SERIF, Font.ITALIC, 15);
        remainingPointsLabelFont = new Font(Font.SANS_SERIF, Font.ITALIC, 15);


        mainMenuPanel = new JPanel();

        menu.setSize(750, 640); //sets the size of the frame
        menu.setLocation(500, 280); //sets the location of the frame on the screen

        mainMenuPanel.setLayout(null);
        mainMenuPanel.setBackground(ToolClass.fgcuBlue);


        //Creates a ToolClass object and then calls
        //createImageIcon to add logo ImageIcon to orderUpLogoSmall

        orderUpLogoSmall = ToolClass.createImageIcon(logoURL, "Order-Up Logo");

        smallLogoholderLabel = new JLabel(orderUpLogoSmall);

        // Initialization and settings for student Name Label
        studentNameLabel = new JLabel(LogInScreen.firstName + " " + LogInScreen.lastName);
        studentNameLabel.setFont(ToolClass.smallBoldHeadingFont);
        studentNameLabel.setForeground(ToolClass.fgcuBlue);
        studentNameLabel.setBackground(Color.white);
        studentNameLabel.setOpaque(true);
        studentNameLabel.setBorder(ToolClass.fgcuGreenLine);
        studentNameLabel.setHorizontalAlignment(SwingConstants.CENTER);

        //calorieCalc initilizer
        calorieCalculator = new JButton("Calorie Calculator");
        MouseHandler handler = new MouseHandler();
        calorieCalculator.setBounds(300, 10, 150, 30);
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
        totalPoints = new JLabel("500"); // <----- Needs to display total points (probably an int)
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
        remainingPoints = new JLabel("256"); // <----- Needs to display remaining points (probably an int)
        remainingPoints.setFont(ToolClass.smallBoldHeadingFont);
        remainingPoints.setForeground(ToolClass.fgcuGreen);
        remainingPoints.setBackground(Color.yellow);
        remainingPoints.setOpaque(true);
        remainingPoints.setBorder(ToolClass.fgcuGreenLine);
        remainingPoints.setHorizontalAlignment(SwingConstants.CENTER);

        // Initialization and settings for my Meal Plan button
        myMealPlan = new JButton("My Meal Plan");
        myMealPlan.setForeground(ToolClass.fgcuGreen);

        testPanel = new JPanel();
        testPanel.setLayout(new BorderLayout());
        testPanel.setVisible(true);
        testPanel.setBackground(Color.WHITE);
        testLabel = new JLabel("Hello! This is a test!");
        testPanel.add(testLabel, BorderLayout.NORTH);

        // Bounds / placement settings for all objects in mainMenuPanel
        smallLogoholderLabel.setBounds(49, 10, 100, 87);
        studentNameLabel.setBounds(20, 100, 160, 40);
        pointsLabel.setBounds(650, 10, 100, 50);
        totalPoints.setBounds(685, 55, 40, 30);
        totalPointsLabel.setBounds(636, 47, 65, 40);
        remainingPoints.setBounds(685, 84, 40, 30);
        remainingPointsLabel.setBounds(597, 75, 100, 40);
        myMealPlan.setBounds(585, 153, 100, 40);
        testPanel.setBounds(220, 50, 300, 100);


        mainMenuPanel.add(smallLogoholderLabel);
        mainMenuPanel.add(studentNameLabel);
        mainMenuPanel.add(pointsLabel);
        mainMenuPanel.add(totalPoints);
        mainMenuPanel.add(totalPointsLabel);
        mainMenuPanel.add(remainingPoints);
        mainMenuPanel.add(remainingPointsLabel);
        mainMenuPanel.add(calorieCalculator);
        //mainMenuPanel.add(myMealPlan);


        /*TESTING*/
        CalendarDemo cal = new CalendarDemo();
        mainMenuPanel.add(cal);
        cal.setBounds(78, 200, 600, 400);
        cal.setVisible(true);

        /*END TESTING*/

        menu.add(mainMenuPanel);
        menu.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        menu.setVisible(true);
    }

    public static class CalendarDemo extends Program implements ItemListener {
        /* Private constants */
//    private static final Color EMPTY_BACKGROUND = new Color(0xDDDDDD);
        private static final String TITLE_FONT = "Serif-36";
        private static final String LABEL_FONT = "Serif-bold-14";
        private static final String DATE_FONT = "Serif-18";
        private static final Locale[] LOCALES = {new Locale("en", "US", "")};
//            new Locale("fr", "FR", ""), new Locale("de", "DE", ""),
//            new Locale("es", "MX", ""), new Locale("it", "IT", ""),
//            new Locale("nl", "NL", ""), new Locale("es", "ES", ""),
//            new Locale("en", "GB", ""), new Locale("en", "US", "")
//    };
        public static int month;
        public static int year;
        /* Private instance variables */
//    private JComboBox localeChooser;
        private JLabel localeChooser;
        private String[] countries;
        private Calendar currentCalendar;
        private DateFormatSymbols symbols;
        private String[] monthNames;
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
//        localeChooser = new JComboBox(countries);
            localeChooser = new JLabel(countries[0]);
            String country = Locale.getDefault().getDisplayCountry();
//        localeChooser.setSelectedItem(country);
//        localeChooser.addItemListener(this);
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
                    new DayPlanner(dayText);
                    MainMenu.menu.dispose();
                }
            };



            MouseListener vboxListener = new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {

                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                    MainMenu.mainMenuPanel.add(MainMenu.testPanel);
                    menu.validate();

                    if (dayText.equals("1")) {
                        testLabel.setText("whoo hoo!");
                    } else if (dayText.equals("27")) {
                        testLabel.setText("yee haw!");
                    } else testLabel.setText("Hello! This is a test.");
                }

                @Override
                public void mouseExited(MouseEvent e) {

                    MainMenu.mainMenuPanel.remove(MainMenu.testPanel);
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

            /*I commented out the labels that showed on the days and instead i
            now created btns. The buttons get the text variable that holds
            a string that represents the date number - Yamnel Serra */

//            JLabel label = new JLabel(text);
//            label.setFont(JTFTools.decodeFont(DATE_FONT));
//            vbox.add(label, "anchor=NORTHEAST top=2 right=2");

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

//                JLabel pointsUsed = new JLabel("125");
//                pointsUsed.setSize(20, 20);
//                pointsUsed.setHorizontalAlignment(SwingConstants.CENTER);
//                pointsUsed.setFont(ToolClass.smallItalicHeadingFont);
//                pointsUsed.setForeground(ToolClass.fgcuGreen);

                plannerBtn.add(dayDisplay, BorderLayout.NORTH); //Adds dayDisplay to the plannerBtn
//                plannerBtn.add(pointsUsed, BorderLayout.SOUTH); //Adds pointsUsed to the plannerBtn
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
        private String capitalize(String word) {
            return word.substring(0, 1).toUpperCase() + word.substring(1);
        }
    }

    //handles listeners for calorie calculator, didn't want to touch the
    // behemoth below this
    private class MouseHandler implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            CalorieCalculator starter = new CalorieCalculator();
            starter.setVisible(true);
            starter.setSize(420, 420);
            starter.setLocation(500, 280);
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
