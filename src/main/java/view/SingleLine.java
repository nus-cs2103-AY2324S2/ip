package view;

import static objects.FilePath.LINE_PATH;
import static utils.FileUtil.getFile;

public class SingleLine extends UI {
    public static void display() {
        String character = getFile(LINE_PATH);
        String line = String.valueOf(character).repeat(100);

        System.out.println(line);
    }
}
