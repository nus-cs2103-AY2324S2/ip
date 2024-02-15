package duke.storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;

import duke.core.ChatbotException;
import duke.parser.DateParser;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;

/**
 * Represents a storage for all tasks saved within a dhatbot.
 */
public class Storage {
    private static final String FILE_PATH = "./data/duke.txt";

    /**
     * Converts a task into appropriate string format, to be saved in duke.txt
     *
     * @param task Task to be converted.
     * @return Task in string format.
     */
    public static String taskToFileString(Task task) {
        StringBuilder sb = new StringBuilder();

        // Append the type of the task
        if (task instanceof ToDo) {
            sb.append("T");
        } else if (task instanceof Deadline) {
            sb.append("D");
        } else if (task instanceof Event) {
            sb.append("E");
        }

        // Append the done status
        sb.append(" | ").append(task.getStatus() ? "1 | " : "0 | ").append(task.getInitialDesc());

        // Append the deadline or event time if applicable
        if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            sb.append(" | ").append(deadline.getStart()); // Append the 'by' time for deadlines
        } else if (task instanceof Event) {
            Event event = (Event) task;

            // Append the 'from - to' times for events
            sb.append(" | ").append(event.getStart()).append(" - ").append(event.getTo());
        }

        return sb.toString();
    }

    /**
     * Converts a string in duke.txt to Task data type
     *
     * @param fileString String to be converted.
     * @return Task object.
     */
    public static Task fileStringToTask(String fileString) {
        String[] parts = fileString.split(" \\| ");
        assert parts.length > 0 : "Incorrect file format";
        if (parts.length < 3) {
            ChatbotException.getError(ChatbotException.ErrorType.TASK_CORRUPT);
        }

        String type = parts[0].trim();
        boolean isDone = parts[1].trim().equals("1");
        String description = parts[2];

        Task task = new Task(description);

        switch (type) {
        case "T":
            task = createTask("todo", description);
            break;
        case "D":
            if (parts.length < 4) {
                ChatbotException.getError(ChatbotException.ErrorType.TODO_CORRUPT);
            }
            description += " /by " + parts[3];
            task = createTask("deadline", description);
            break;
        case "E":
            String timeInfo = parts[3].trim();
            String[] timeParts = timeInfo.split(" - ");
            if (timeParts.length < 2) {
                ChatbotException.getError(ChatbotException.ErrorType.EVENT_CORRUPT);
            }
            description += " /from " + timeParts[0].trim() + " /to " + timeParts[1].trim();
            task = createTask("event", description);
            break;
        default:
            ChatbotException.getError(ChatbotException.ErrorType.UNKNOWN_TASK);
        }

        task.mark(isDone);
        return task;
    }

    /**
     * Returns appropriate Task type given user command
     *
     * @param command     User commands.
     * @param description Task description to be added.
     * @return Task object.
     */
    public static Task createTask(String command, String description) {
        assert command.startsWith("todo") || command.startsWith("deadline")
                || command.startsWith("event") : "Incorrect command entered";

        if (command.startsWith("todo")) {
            return new ToDo(description);

        } else if (command.startsWith("deadline")) {
            String[] parts = description.split("/by", 2);
            LocalDateTime by = DateParser.parseDateTime(parts[1].trim());
            return new Deadline(parts[0], by);

        } else {
            String[] parts = description.split("\\s+/from\\s+|\\s+/to\\s+");
            LocalDateTime from = DateParser.parseDateTime(parts[1].trim());
            LocalDateTime to = DateParser.parseDateTime(parts[2].trim());
            return new Event(parts[0], from, to);

        }
    }

    /**
     * Checks if there are any saved tasks from the previous run
     */
    public static void ensureFileExists() {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                file.getParentFile().mkdirs(); // Ensure the directory exists
                file.createNewFile(); // Create the file if it doesn't exist
            }
        } catch (IOException e) {
            System.out.println("An error occurred while ensuring the task file exists.");
        }
    }

    /**
     * Saves all tasks in the list to duke.txt
     *
     * @param tasks Tasklist to be saved.
     */
    public static void saveTasks(TaskList tasks) {
        try (PrintWriter writer = new PrintWriter(new File(FILE_PATH))) {
            for (Task task : tasks.getTasks()) {
                writer.println(Storage.taskToFileString(task));
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while saving tasks.");
        }
    }

    /**
     * Loads previously saved tasks into chatbot
     *
     * @param tasks Tasklist from the previous run.
     */
    public static void loadTasks(ArrayList<Task> tasks) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                tasks.add(Storage.fileStringToTask(line));
            }
            TaskList.tasksCount = tasks.size();
        } catch (FileNotFoundException e) {
            System.out.println("duke.task.Task file not found. A new file will be created.");
        } catch (IOException e) {
            System.out.println("An error occurred while loading tasks.");
        } catch (Exception e) {
            System.out.println("duke.task.Task file is corrupted.");
        }
    }

}
