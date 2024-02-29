package jiayou.function;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

import jiayou.task.Deadline;
import jiayou.task.Event;
import jiayou.task.Task;
import jiayou.task.TaskList;
import jiayou.task.ToDo;

/**
 * Represents the storage to link the text file with the task list of the chatbot.
 * @author Liu Jiayao
 */
public class Storage {
    private TaskList taskList;
    private Path filePath;

    /**
     * Returns a new Storage instance linked with the text file and the task list.
     *
     * @param filePath the path of the text file to store the content of the task list.
     * @param taskList the task list of the chatbot.
     */
    public Storage(String filePath, TaskList taskList) {
        this.filePath = Paths.get(filePath);
        this.taskList = taskList;
    }

    /**
     * Loads the content of the text file into the task list of the chatbot.
     */
    public void loadFromFile() {
        try {
            if (!Files.exists(filePath.getParent())) {
                Files.createDirectories(filePath.getParent());
            }

            if (Files.exists(filePath)) {
                List<String> lines = Files.readAllLines(filePath);
                for (String line : lines) {
                    this.taskList.addTask(parseFromFile(line));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes the content of the task list of the chatbot back to the linked text file.
     */
    public void saveToFile() {
        try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
            for (Task task : this.taskList.getList()) {
                writer.write(task.toStringForStore());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads and parses the text in the file into a task instance.
     *
     * @param string the string to be parsed.
     * @return a task instance corresponding to the text content.
     */
    public static Task parseFromFile(String string) {
        String[] parts = string.split(" \\| ", 3);
        String command = parts[0];
        String isDone = parts[1];
        String content = parts[2];
        Task newTask = null;

        switch (command) {
        case "T":
            newTask = new ToDo(content);
            break;
        case "E":
            newTask = createEvent(content);
            break;
        case "D":
            newTask = createDeadline(content);
            break;
        default:
            break;
        }

        newTask.setStatus((isDone.equals("1")));
        return newTask;
    }

    /**
     * Creates a new Event object using the input string.
     * @param input the string to be parsed.
     * @return a new Event object.
     */
    private static Event createEvent(String input) {
        String[] eventParts = input.split(" \\| ", 2);
        String description = eventParts[0];
        String datePart = eventParts[1];
        String[] dates = datePart.split(" to ");
        LocalDate startDate = LocalDate.parse(dates[0].substring(dates[0].indexOf("from") + 5).trim());
        LocalDate endDate = LocalDate.parse(dates[1].trim());
        Event newEvent = new Event(description, startDate, endDate);
        return newEvent;
    }

    /**
     * Creates a new Deadline object using the input string.
     * @param input the string to be parsed.
     * @return a new Deadline object.
     */
    private static Deadline createDeadline(String input) {
        String[] deadlineParts = input.split(" \\| by");
        LocalDate by = LocalDate.parse(deadlineParts[1]);
        Deadline newDeadline = new Deadline(deadlineParts[0], by);
        return newDeadline;
    }
}

