import javax.swing.*;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by TylerHall on 10/17/16.
 */
public class ToolClass {

    static ImageIcon createImageIcon(String path, String description) {
//        java.net.URL imgURL = getClass().getResource(path);
        URL imgURL = null;

        try {
            imgURL = new URL(path);

        } catch (MalformedURLException e) { // catches an invalid url exception
            e.printStackTrace();
        }

        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }

    }
}
