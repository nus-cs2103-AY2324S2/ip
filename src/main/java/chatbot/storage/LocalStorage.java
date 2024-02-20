package chatbot.storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import chatbot.parse.TaskParser;
import chatbot.print.PrintFormatter;
import chatbot.task.Deadline;
import chatbot.task.TaskList;
import chatbot.task.exception.InvalidTaskStringException;

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
public final class LocalStorage {
    /** The relative path from the project root where the save file is stored. */
    private static final String RELATIVE_PATH = "data/";

    /** The name and format of the save file stored. */
    private static final String SAVE_FILE_NAME = "save.txt";

    /**
     * Tries to save the task list into local storage,
     * overwriting the previous save file if any.
     *
     * @param taskList The task list instance to save into local storage.
     */
    public static void saveTaskList(TaskList taskList) {
        hasCreatedSaveFile();

        File saveFile = new File(RELATIVE_PATH + SAVE_FILE_NAME);
        try {
            // write to save file
            FileWriter fw = new FileWriter(saveFile, false);
            fw.write(taskList.toString());
            fw.close();
        } catch (IOException e) {
            // don't write to save file if saving cannot happen
        }
    }

    /**
     * Creates the save file and folder if it doesn't exist.
     *
     * @return True if the file is created, otherwise false.
     */
    private static boolean hasCreatedSaveFile() {
        File folder = new File(RELATIVE_PATH);
        if (!folder.exists()) {
            if (!folder.mkdirs()) {
                return false;
            }
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
     * @return The {@link TaskList} stored in local storage, or a new instance if none exists.
     */
    public static TaskList loadTaskList() {
        if (hasCreatedSaveFile()) {
            return new TaskList();
        }

        TaskList taskList = readSaveFile();
        SaveState.saveCurrentState(taskList.saveState());
        return taskList;
    }

    /**
     * Reads the save file and converts it to a {@link TaskList}.
     *
     * @return The task list stored in local storage.
     */
    private static TaskList readSaveFile() {
        TaskList taskList = new TaskList();
        Path path = Paths.get(RELATIVE_PATH, SAVE_FILE_NAME);

        try (BufferedReader br = Files.newBufferedReader(path)) {
            String line;
            while ((line = br.readLine()) != null && !line.isBlank()) {
                taskList.add(TaskParser.parseTaskListItem(line));
            }

            PrintFormatter.addLogToMessageQueue(
                    "Found and loaded a previous save file successfully!");
            return taskList;
        } catch (IOException e) {
            PrintFormatter.addLogToMessageQueue(
                    "Cannot read the save file! Invalid file format! Creating new save...");
        } catch (InvalidTaskStringException e) {
            PrintFormatter.addLogToMessageQueue(
                    "Cannot understand the save file! Invalid task format!  Creating new save...");
        }

        return new TaskList();
    }
}
