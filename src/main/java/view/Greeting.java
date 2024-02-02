package view;

import static utils.FilePath.GREETING_PATH;
import static utils.FilePath.LOGO_PATH;
import static utils.FileUtil.getFile;

public class Greeting extends UI {
    public static void display() {
        System.out.println(getFile(LOGO_PATH));
        EncaseLines.display(getFile(GREETING_PATH));
    }
}
