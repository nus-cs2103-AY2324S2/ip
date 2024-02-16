package knight;

import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.List;

/**
 * Handles the reading and writing of tasks to and from a file.
 */
public class Storage {
    /**
     * Reads the tasks from a file.
     *
     * @return The tasks read from the file.
     */
    public static TaskList readFromFile() {
        TaskList tasks = new TaskList();
        List<String> lines;

        try {
            lines = java.nio.file.Files.readAllLines(java.nio.file.Paths.get("scrolls_of_tasks.txt"));
        } catch (IOException e) {
            return new TaskList();
        }

        try {
            for (String line : lines) {
                Command command = CommandParser.parseCommand(line);
                if (!command.isTaskInitialisation()) {
                    Ui.speak("Unexpected or invalid content in thy scroll of tasks. Thy scroll is cleared.");
                    return new TaskList();
                }
                tasks.executeCommandSilently(command, line);
            }
        } catch (NonstandardCommandException | DateTimeParseException e) {
            Ui.speak("Unexpected or invalid content in thy scroll of tasks. Thy scroll is cleared.");
            return new TaskList();
        }
        return tasks;
    }

    /**
     * Writes the tasks to a file.
     *
     * @param tasks The tasks to write to the file.
     */
    public static void writeToFile(TaskList tasks) {
        try {
            FileWriter writer = new FileWriter("scrolls_of_tasks.txt");
            writer.write(tasks.getCommands());
            writer.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}
