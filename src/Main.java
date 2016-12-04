import MainScreens.*;

import java.io.FileNotFoundException;
import java.sql.SQLException;

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
