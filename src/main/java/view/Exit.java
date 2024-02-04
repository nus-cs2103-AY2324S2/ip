package view;

import static utils.FilePath.EXIT_PATH;
import static utils.FileUtil.getFile;

/**
 * The Exit class provides a method to display an exit message.
 */
public class Exit {

    /**
     * Displays an exit message.
     */
    public static void display() {
        EncaseLines.display(getFile(EXIT_PATH));
    }
}