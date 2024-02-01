package duke.storage;

import duke.parser.Parser;
import duke.parser.Token;

import duke.ui.UI;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

/**
 * The Storage class is responsible for loading tasks from and saving tasks to a file.
 * It interacts with the TaskList, Parser, and UI classes to manage the data persistence of tasks.
 */
public class Storage {

    /**
     * Initializes the storage by loading tasks from a save file into the provided TaskList.
     *
     * @param taskList The TaskList object to be initialized with tasks from the save file.
     */
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

    /**
     * Saves the provided input string to a save file.
     *
     * @param input The input string representing the tasks to be saved.
     */
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
