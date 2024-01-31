package storage;

import dukeException.ListOutofBoundsException;
import parser.Parser;
import parser.Command;
import parser.Token;

import ui.UI;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {

    public static void start(TaskList taskList) {
        try {
            File file = new File("./src/main/java/Storage/savefile.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                Parser parser = new Parser();
                parser.feed(scanner.nextLine());
                Token output = null;
                try {
                    output = parser.parse();
                    taskList.loadFromSave(output.getTask());
                } catch (Exception e) {
                    UI.error(e.getMessage());
                }
            }
        } catch (FileNotFoundException e) {
            UI.error("No save file");
        }
    }

    public static void save(String input) {
        try {
            FileWriter fw = new FileWriter("./src/main/java/Storage/savefile.txt");
            fw.write(input);
            fw.close();
        } catch (IOException e) {
            UI.error("Missing save file");
        }
    }
}
