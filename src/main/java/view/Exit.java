package view;

import static objects.FilePath.EXIT_PATH;
import static objects.Utils.getFile;

public class Exit {
    public static void display() {
        EncaseLines.display(getFile(EXIT_PATH));
    }
}
