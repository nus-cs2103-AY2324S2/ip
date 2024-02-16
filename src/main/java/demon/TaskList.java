package demon;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;

/**
 * Facilitates the loading of tasks from tasks.txt file.
 */
public class TaskList {
    private final List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Loads the saved tasks into the tasks array by verifying the type of tasks to store.
     * The method creates the object, Deadline, Event, To-do, based on the type of tasks identified.
     *
     * @param storageArray The list of strings containing all tasks populated from the tasks.txt file.
     */
    public TaskList(List<String> storageArray) {
        this.tasks = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

        // Read file line by line
        for (int i = 0; i < storageArray.size(); i++) {
            // Process the line
            Task item;
            String isCompleted;
            String line = storageArray.get(i);
            char firstChar = line.charAt(0);
            if (firstChar == 'D') {
                String[] parts = line.split("\\|");
                isCompleted = parts[1].trim();
                String description = parts[2].trim();
                String by = parts[3].trim();
                LocalDateTime dateTime = LocalDateTime.parse(by, formatter);
                item = new Deadline(description, dateTime);

            } else if (firstChar == 'T') {
                String[] parts = line.split("\\|");
                isCompleted = parts[1].trim();
                String description = parts[2].trim();
                item = new Todo(description);

            } else {
                String[] parts = line.split("\\|");
                isCompleted = parts[1].trim();
                String description = parts[2].trim();
                String from = parts[3].trim();
                String to = parts[4].trim();
                LocalDateTime dateTimeFrom = LocalDateTime.parse(from, formatter);
                LocalDateTime dateTimeTo = LocalDateTime.parse(to, formatter);
                item = new Event(description, dateTimeFrom, dateTimeTo);
            }

            this.tasks.add(item);
            if (isCompleted.equals("1")) {
                item.markDone();
            }
        }
    }

    public List<Task> getTaskList() {
        return this.tasks;
    }

    public int getSize() {
        return this.tasks.size();
    }

    public Task getTask(int index) {
        return this.tasks.get(index);
    }
}
