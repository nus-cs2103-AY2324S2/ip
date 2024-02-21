package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import duke.parser.Parser;
import duke.parser.Token;
import duke.responses.Responses;


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
    private static String directory = System.getProperty("user.dir");
    private static String saveFile = "saveFile.txt";

    private static File file = new File(directory, saveFile);

    public static void start(TaskList taskList) {
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                Parser parser = new Parser();
                parser.feed(scanner.nextLine());

                Token output = null;

                try {
                    output = parser.parse();

                    taskList.loadFromSave(output.getTask());
                } catch (Exception e) {
                    Responses.error(e.getMessage());
                }
            }
        } catch (FileNotFoundException e) {
            Responses.error("No save file");
        }
    }

    /**
     * Saves the provided input string to a save file.
     *
     * @param input The input string representing the tasks to be saved.
     */
    public static void save(String input) {
        try {
            FileWriter fw = new FileWriter(file);
            fw.write(input);
            fw.close();
        } catch (IOException e) {
            Responses.error("Missing save file");
        }
    }
}
