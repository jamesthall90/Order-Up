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
    public static Color fgcuBlue = new Color(9, 40, 105);
    public static Color lightBlue = new Color(136, 179, 214, 75);
    public static Color fgcuGreen = new Color(1, 121, 76);
    public static Color fgcuTransparentGreen = new Color(1, 121, 76, 75);

    public static Font smallBoldHeadingFont = new Font(Font.SANS_SERIF, Font.BOLD, 15);
    public static Font largerBoldHeadingFont = new Font(Font.SANS_SERIF, Font.BOLD, 20);
    public static Font smallItalicHeadingFont = new Font(Font.SANS_SERIF, Font.ITALIC, 15);


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
