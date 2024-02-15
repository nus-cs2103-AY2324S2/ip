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
                String description = line.substring(7);
                String letter = line.substring(1, 2);
                String markCheck = line.substring(4, 5);

                Task task = null;

                if (letter.equals("T")) {
                    task = new Todo(description);

                    if (markCheck.equals("X")) {
                        task.mark();
                    }
                } else if (letter.equals("D")) {
                    int openingParenthesisIndex = description.indexOf('(');
                    int endingParenthesisIndex = description.indexOf(')');

                    String content = description.substring(0, openingParenthesisIndex);
                    String timing = description.substring(openingParenthesisIndex + 2, endingParenthesisIndex);

                    task = new Deadline(content, timing);

                    if (markCheck.equals("X")) {
                        task.mark();
                    }
                } else if (letter.equals("E")) {
                    int openingParenthesisIndex = description.indexOf('(');
                    int endingParenthesisIndex = description.indexOf(')');

                    String content = description.substring(0, openingParenthesisIndex);
                    String timing = description.substring(openingParenthesisIndex + 2, endingParenthesisIndex);

                    String[] timings = timing.split("to:", 2);

                    task = new Event(content, timings[0], "  " + timings[1]);

                    if (markCheck.equals("X")) {
                        task.mark();
                    }
                } else {
                    System.out.println("INVALID STORAGE DATA");
                }
                taskArr.add(task);
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks from file: " + e.getMessage());
        }
        return taskArr;
    }
}