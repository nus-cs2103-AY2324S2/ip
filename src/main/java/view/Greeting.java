package view;

import static objects.FilePath.GREETING_PATH;
import static objects.FilePath.LOGO_PATH;
import static utils.FileUtil.getFile;

public class Greeting extends UI {
    public static void display() {
        System.out.println(getFile(LOGO_PATH));
        EncaseLines.display(getFile(GREETING_PATH));
    }
}
