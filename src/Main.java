import MainScreens.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Scanner;

/* Added Main Class and src Directory */
public class Main {
    public static  String HOST = "";
    

    public static void main(String[] args) throws SQLException, FileNotFoundException {

        LogInScreen login = new LogInScreen();

        /* FOR TESTING */

//        MainMenu menu = new MainMenu();
//        DayPlanner day = new DayPlanner("monday");
      
        /* END TESTING */
    }

}
