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
    public TaskList(List<String> storageArray) throws NoSuchTaskException {
        this.tasks = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

        for (String line : storageArray) {
            Task item;
            char firstChar = line.charAt(0);
            String[] parts = line.split("\\|");

            switch (firstChar) {
            case 'D':
                item = createDeadline(parts, formatter);
                break;
            case 'T':
                item = createTodo(parts);
                break;
            case 'E':
                item = createEvent(parts, formatter);
                break;
            default:
                throw new NoSuchTaskException(firstChar);
            }

            this.tasks.add(item);
        }
    }

    private Task createDeadline(String[] parts, DateTimeFormatter formatter) {
        String isCompleted = parts[1].trim();
        String description = parts[2].trim();
        String by = parts[3].trim();
        LocalDateTime dateTime = LocalDateTime.parse(by, formatter);
        Deadline deadline = new Deadline(description, dateTime);
        if (isCompleted.equals("1")) {
            deadline.markDone();
        }
        return deadline;
    }

    private Task createTodo(String[] parts) {
        String isCompleted = parts[1].trim();
        String description = parts[2].trim();
        Todo todo = new Todo(description);
        if (isCompleted.equals("1")) {
            todo.markDone();
        }
        return todo;
    }

    private Task createEvent(String[] parts, DateTimeFormatter formatter) {
        String isCompleted = parts[1].trim();
        String description = parts[2].trim();
        String from = parts[3].trim();
        String to = parts[4].trim();
        LocalDateTime dateTimeFrom = LocalDateTime.parse(from, formatter);
        LocalDateTime dateTimeTo = LocalDateTime.parse(to, formatter);
        Event event = new Event(description, dateTimeFrom, dateTimeTo);
        if (isCompleted.equals("1")) {
            event.markDone();
        }
        return event;
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
