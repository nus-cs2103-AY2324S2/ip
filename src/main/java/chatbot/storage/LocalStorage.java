package chatbot.storage;

import chatbot.io.ui.Printer;
import chatbot.task.TaskList;
import chatbot.task.Deadline;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * This saves the {@link TaskList} into local storage in the directory {@value RELATIVE_PATH}
 * as a human-readable {@value SAVE_FILE_NAME} file,
 * where each line represents a task,
 * to allow users to view their tasks without running the application.
 * <p>
 *
 * The format for a task is as follows:
 * <pre>{@code <index> [<type>][<status>] <name> <other_arguments>}</pre>
 * <p>
 * The format is specified within each type of task.
 * For example, a {@link Deadline}: {@code 4. [D][ ] read book (by: Sunday)}
 * <p>
 * Regular expressions are used to parse the file back into a {@link TaskList}.
 *
 * @author Titus Chew
 */
public class LocalStorage {
    /**
     * The relative path from the project root where the save file is stored.
     */
    public static String RELATIVE_PATH = "data/";

    /**
     * The name and format of the save file stored.
     */
    public static String SAVE_FILE_NAME = "save.txt";

    /**
     * Tries to save the task list into local storage,
     * overwriting the previous save file if any.
     *
     * @param taskList the task list instance to save into local storage
     */
    public static void saveTaskList(TaskList taskList) {
        createSaveFile();

        File saveFile = new File(RELATIVE_PATH + SAVE_FILE_NAME);
        try {
            // write to save file
            FileWriter fw = new FileWriter(saveFile, false);
            fw.write(taskList.toString());
            fw.close();
        } catch (IOException e) {
            // don't write to save file
        }
    }

    /**
     * Creates the save file and folder if it doesn't exist.
     *
     * @return true if the file is created, otherwise false
     */
    private static boolean createSaveFile() {
        File folder = new File(RELATIVE_PATH);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        try {
            File saveFile = new File(RELATIVE_PATH + SAVE_FILE_NAME);
            return saveFile.createNewFile();
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * Loads the {@link TaskList} into the application.
     *
     * @return the {@link TaskList} stored in local storage, or a new instance if none exists
     */
    public static TaskList loadTaskList() {
        if (createSaveFile()) {
            return new TaskList();
        }

        return readSaveFile();
    }

    /**
     * Reads the save file and converts it to a {@link TaskList}.
     *
     * @return the task list stored in local storage
     */
    private static TaskList readSaveFile() {
        TaskList taskList =  new TaskList();
        Path path = Paths.get(RELATIVE_PATH, SAVE_FILE_NAME);
        try (Stream<String> lines = Files.lines(path)) {
            lines.forEach(line -> taskList.add(TaskList.parseTaskListItem(line)));
            Printer.addToPrintQueue("I have found and loaded a previous save file successfully!");
            return taskList;
        } catch (IOException e) {
            Printer.addToPrintQueue("I cannot read the save file! Invalid file format!");
        } catch (IllegalStateException e) {
            Printer.addToPrintQueue("I cannot understand the save file! Invalid task format!");
        }
        return new TaskList();
    }
}
