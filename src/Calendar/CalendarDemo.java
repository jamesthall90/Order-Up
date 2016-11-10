package Calendar;

import MainScreens.DayPlanner;
import Utility.ToolClass;
/*
 * File: CalendarDemo.java
 * -----------------------
 * This program uses the GUI layout mechanism to create a calendar
 * page.  The program uses the features of Java's Locale class to
 * internationalize the calendar.
 */

import acm.gui.*;
import acm.program.*;
import acm.util.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CalendarDemo extends Program implements ItemListener {

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

    /* Private instance variables */
//    private JComboBox localeChooser;
    private JLabel localeChooser;
    private String[] countries;
    private Calendar currentCalendar;
    private DateFormatSymbols symbols;
    private String[] monthNames;
    private String[] weekdayNames;
    private int firstDayOfWeek;



    public CalendarDemo(){
        init();

    }

    /** Initialize the graphical user interface */
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

    /** Respond to a button action */
    public void actionPerformed(ActionEvent e) {
        int delta = (e.getActionCommand().equals("<-")) ? -1 : +1;
        currentCalendar.add(Calendar.MONTH, delta);
        updateCalendarDisplay(currentCalendar);
    }

    /** Respond to a change in the locale selection */
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
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
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
        ActionListener plannerBtnListener = e -> new DayPlanner(dayText);

        VPanel vbox = new VPanel();

        if (dayText== null) {
            vbox.setBackground(ToolClass.fgcuLightBlue);//EMPTY_BACKGROUND);
        } else {

            /*I commented out the labes thet shoed on the days and instead i
            t now created btns. The buttons get the text variable that holds
            a string that represents the date number*/

//            JLabel label = new JLabel(text);
//            label.setFont(JTFTools.decodeFont(DATE_FONT));
//            vbox.add(label, "anchor=NORTHEAST top=2 right=2");

            JButton plannerBtn = new JButton(dayText);

            // this should make the btns the same size as the box
            // but it's not working... not sure why yet. Will keep working on it on the future
            plannerBtn.setSize(vbox.getWidth(), vbox.getHeight());
            plannerBtn.addActionListener(plannerBtnListener); // adding the listener
            vbox.add(plannerBtn); // ADDS A BTN to DayPlaner

            vbox.setBackground(Color.WHITE);
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