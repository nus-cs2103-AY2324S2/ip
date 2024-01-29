package view;

import static objects.FilePath.EXIT_PATH;
import static utils.FileUtil.getFile;

public class Exit {
    public static void display() {
        EncaseLines.display(getFile(EXIT_PATH));
    }
}
