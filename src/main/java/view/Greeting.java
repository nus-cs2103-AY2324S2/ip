package view;

import static utils.FilePath.GREETING_PATH;
import static utils.FilePath.LOGO_PATH;
import static utils.FileUtil.getFile;

/**
 * The Greeting class provides a method to display a greeting message with a logo.
 * Extends the UI class.
 */
public class Greeting extends UI {

    /**
     * Displays a greeting message along with a logo.
     */
    public static void display() {
        System.out.println(getFile(LOGO_PATH));
        EncaseLines.display(getFile(GREETING_PATH));
    }
}
