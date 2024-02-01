package duke.storage;

import duke.parser.Parser;
import duke.parser.Token;

import duke.ui.UI;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {

    public static void start(TaskList taskList) {
        try {
            File file = new File("./src/main/java/duke/storage/savefile.txt");
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
            FileWriter fw = new FileWriter("./src/main/java/duke/storage/savefile.txt");
            fw.write(input);
            fw.close();
        } catch (IOException e) {
            UI.error("Missing save file");
        }
    }
}
