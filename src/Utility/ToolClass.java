package Utility;

import javax.swing.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.*;
import java.awt.Font;

/**
 * *Created by TylerHall on 10/17/16.
 */
public class ToolClass {

    //Custom FGCU color objects to be used by any class that requires them
    public static final Color fgcuBlue = new Color(0, 40, 122);  // #00287A (Official FGCU Blue)
    public static Color fgcuLightBlue = new Color(0, 40, 122, 150); // FGCU Blue lowered to 150 opacity
    public static final Color fgcuGreen = new Color(0, 136, 90); // #00885A (Official FGCU Green)
    public static Color fgcuLightGreen = new Color(0, 136, 90, 150); // FGCU Green lowered to 150 opacity
    public static final Color fgcuGold = new Color(185, 151, 91,0); // #ba9e66 or #b9975b FGCU Gold (Official FGCU Gold)

    public static Font smallBoldHeadingFont = new Font(Font.SANS_SERIF, Font.BOLD, 15);
    public static Font largerBoldHeadingFont = new Font(Font.SANS_SERIF, Font.BOLD, 20);
    public static Font smallItalicHeadingFont = new Font(Font.SANS_SERIF, Font.ITALIC, 12);


    public static ImageIcon createImageIcon(String path, String description) {

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
