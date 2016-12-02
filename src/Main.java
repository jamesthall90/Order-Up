import MainScreens.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Scanner;

/* Added Main Class and src Directory */
public class Main {
    public static  String HOST = "";
    public static Scanner fs;
    
    public static void main(String[] args) throws SQLException, FileNotFoundException {
      fs = new Scanner(new File("database_path.txt"));
      HOST = fs.nextLine();
      fs.close();
        LogInScreen login = new LogInScreen(HOST);

        /* FOR TESTING */

//        MainMenu menu = new MainMenu();
//        DayPlanner day = new DayPlanner("monday");
      
        /* END TESTING */
    }

}
