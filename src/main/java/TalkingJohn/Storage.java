package talkingjohn;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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