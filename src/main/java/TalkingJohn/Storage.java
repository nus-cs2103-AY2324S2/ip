package talkingjohn;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

/**
 * Handles the saving and loading of tasks to and from a file.
 */
public class Storage {
    private final String filePath;
    private final List<Task> taskArr;

    /**
     * Constructs a Storage object with the specified file path and task list.
     *
     * @param filePath The file path where the tasks are stored.
     * @param taskArr  The list containing the tasks.
     */
    public Storage(String filePath, List<Task> taskArr) {
        assert filePath != null : "filePath cannot be null";
        assert taskArr != null : "taskArr cannot be null";
        this.filePath = filePath;
        this.taskArr = taskArr;
    }

    /**
     * Saves the tasks to the file.
     */
    public void saveTasksToFile() {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (Task task : taskArr) {
                writer.write(task.toString() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }

    /**
     * Loads the tasks from the file.
     *
     * @return The list containing the loaded tasks.
     */
    public List<Task> loadTasksFromFile() {
        try {
            Path path = Paths.get(filePath);
            if (Files.exists(path)) {
                BufferedReader reader = new BufferedReader(new FileReader(filePath));
                String line;
                while ((line = reader.readLine()) != null) {
                    Task task = createTaskFromLine(line);
                    if (task != null) {
                        taskArr.add(task);
                    }
                }
            } else {
                Files.createDirectories(path.getParent());
                Files.createFile(path);
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks from file: " + e.getMessage());
        }


        return taskArr;
    }


    /**
     * Creates a Task object from the given line of text representing a task.
     *
     * @param line The line of text representing the task, including task type, completion status,
     *             description, and optionally notes.
     * @return The Task object created from the line of text, or null if the line is invalid.
     */
    private Task createTaskFromLine(String line) {
        String letter = line.substring(1, 2);
        String markCheck = line.substring(4, 5);
        String description = line.substring(7);
        String notes = "";

        if (description.contains("[") && description.contains("]")) {
          String[] descriptionAndNotes = description.split("\\[", 2);
          description = descriptionAndNotes[0];
          notes = descriptionAndNotes[1];
        }
        Task task = null;

        if (letter.equals("T")) {
            task = createTodoTask(description, notes, markCheck);
        } else if (letter.equals("D")) {
            task = createDeadlineTask(description, notes, markCheck);
        } else if (letter.equals("E")) {
            task = createEventTask(description, notes, markCheck);
        } else {
            System.out.println("INVALID STORAGE DATA");
        }
        return task;
    }

    /**
     * Creates a Todo task with the given description, notes, and completion status.
     *
     * @param description The description of the Todo task.
     * @param notes       Additional notes for the Todo task (optional).
     * @param markCheck   The completion status of the Todo task ('X' for completed, '' for not completed).
     * @return The created Todo task.
     */
    private Task createTodoTask(String description, String notes, String markCheck) {
        Todo todo;
        if (Objects.equals(notes, "")) {
            todo = new Todo(description);
        } else {
            String noteWithoutLastCharacter = notes.substring(0, notes.length() - 1);
            todo = new Todo(description, noteWithoutLastCharacter);
        }
        if (markCheck.equals("X")) {
            todo.mark();
        }
        return todo;
    }

    /**
     * Creates a Deadline task with the given description, note and timing, and completion status.
     *
     * @param description    The description of the Deadline task.
     * @param noteAndTiming  The note and timing information for the Deadline task (optional).
     * @param markCheck      The completion status of the Deadline task ('X' for completed, '' for not completed).
     * @return The created Deadline task.
     */
    private Task createDeadlineTask(String description, String noteAndTiming, String markCheck) {
        Deadline deadline;

        if (Objects.equals(noteAndTiming, "")) {
            int openingParenthesisIndex = description.indexOf('(');
            int endingParenthesisIndex = description.indexOf(')');
            String content = description.substring(0, openingParenthesisIndex);
            String timing = description.substring(openingParenthesisIndex + 2, endingParenthesisIndex);

            deadline = new Deadline(content, timing);
        } else {
            int openingParenthesisIndex = noteAndTiming.indexOf('(');
            int endingParenthesisIndex = noteAndTiming.indexOf(')');
            String notes = noteAndTiming.substring(0, openingParenthesisIndex - 1);
            String timing = noteAndTiming.substring(openingParenthesisIndex + 2, endingParenthesisIndex);

            deadline = new Deadline(description, notes, timing);
        }

        if (markCheck.equals("X")) {
            deadline.mark();
        }

        return deadline;
    }

    /**
     * Creates an Event task with the given description, note and timing, and completion status.
     *
     * @param description    The description of the Event task.
     * @param noteAndTiming  The note and timing information for the Event task (optional).
     * @param markCheck      The completion status of the Event task ('X' for completed, '' for not completed).
     * @return The created Event task.
     */
    private Task createEventTask(String description, String noteAndTiming, String markCheck) {
        Event event;
        if (Objects.equals(noteAndTiming, "")) {
            int openingParenthesisIndex = description.indexOf('(');
            int endingParenthesisIndex = description.indexOf(')');

            String content = description.substring(0, openingParenthesisIndex);
            String timing = description.substring(openingParenthesisIndex + 2, endingParenthesisIndex);

            String[] timings = timing.split("to:", 2);

            event = new Event(content, timings[0], "  " + timings[1]);
        } else {
            int openingParenthesisIndex = noteAndTiming.indexOf('(');
            int endingParenthesisIndex = noteAndTiming.indexOf(')');

            String notes = noteAndTiming.substring(0, openingParenthesisIndex - 1);
            String timing = noteAndTiming.substring(openingParenthesisIndex + 2, endingParenthesisIndex);

            String[] timings = timing.split("to:", 2);

            event = new Event(description, notes, timings[0], "  " + timings[1]);
        }
        if (markCheck.equals("X")) {
            event.mark();
        }
        return event;
    }
}