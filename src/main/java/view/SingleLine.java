package view;

import objects.Utils;

import static objects.FilePath.LINE_PATH;

public class SingleLine extends UI {
    public static void display() {
        String character = Utils.getFile(LINE_PATH);
        String line = String.valueOf(character).repeat(60);

        System.out.println(line);
    }
}
