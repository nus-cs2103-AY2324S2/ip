package talkingjohn;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

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
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = createTaskFromLine(line);
                if (task != null) {
                    taskArr.add(task);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks from file: " + e.getMessage());
        }
        return taskArr;
    }

    private Task createTaskFromLine(String line) {
        String letter = line.substring(1, 2);
        String markCheck = line.substring(4, 5);
        String description = line.substring(7);

        Task task = null;

        if (letter.equals("T")) {
            task = createTodoTask(description, markCheck);
        } else if (letter.equals("D")) {
            task = createDeadlineTask(description, markCheck);
        } else if (letter.equals("E")) {
            task = createEventTask(description, markCheck);
        } else {
            System.out.println("INVALID STORAGE DATA");
        }

        return task;
    }

    private Task createTodoTask(String description, String markCheck) {
        Todo todo = new Todo(description);
        if (markCheck.equals("X")) {
            todo.mark();
        }
        return todo;
    }

    private Task createDeadlineTask(String description, String markCheck) {
        int openingParenthesisIndex = description.indexOf('(');
        int endingParenthesisIndex = description.indexOf(')');

        String content = description.substring(0, openingParenthesisIndex);
        String timing = description.substring(openingParenthesisIndex + 2, endingParenthesisIndex);

        Deadline deadline = new Deadline(content, timing);

        if (markCheck.equals("X")) {
            deadline.mark();
        }

        return deadline;
    }

    private Task createEventTask(String description, String markCheck) {
        int openingParenthesisIndex = description.indexOf('(');
        int endingParenthesisIndex = description.indexOf(')');

        String content = description.substring(0, openingParenthesisIndex);
        String timing = description.substring(openingParenthesisIndex + 2, endingParenthesisIndex);

        String[] timings = timing.split("to:", 2);

        Event event = new Event(content, timings[0], "  " + timings[1]);

        if (markCheck.equals("X")) {
            event.mark();
        }

        return event;
    }

}