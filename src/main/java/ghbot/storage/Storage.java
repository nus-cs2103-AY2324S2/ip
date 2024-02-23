package ghbot.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import ghbot.task.Deadline;
import ghbot.task.Event;
import ghbot.task.Task;
import ghbot.task.Todo;

/**
 * Storage Class.
 * It deals with loading tasks from the file and saving tasks into the file.
 */
public class Storage {
    private static final String TODO = "T";
    private static final String DEADLINE = "D";
    private static final String EVENT = "E";
    private static final String COMPLETED = "1";
    private final String fileName;

    /**
     * Storage Constructor.
     * @param fileName Name of the file.
     */
    public Storage(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Returns the list of task that were saved previously.
     * @return A list of tasks.
     */
    public List<Task> getInputFromFile() {
        List<Task> tasks = new ArrayList<>();
        try {
            File file = new File(this.fileName);
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String input = sc.nextLine();
                String[] inputDetails = input.split("\\| ");
                addTaskToList(tasks, inputDetails);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Chat history are not present!");
        }
        return tasks;
    }

    /**
     * Adds task from file to list.
     * @param tasks List of tasks.
     * @param inputDetails Contain input details from the file.
     */
    public void addTaskToList(List<Task> tasks, String[] inputDetails) {
        if (inputDetails[0].trim().equalsIgnoreCase(TODO)) {
            tasks.add(getTodoTask(inputDetails));
        } else if (inputDetails[0].trim().equalsIgnoreCase(DEADLINE)) {
            tasks.add(getDeadlineTask(inputDetails));
        } else if (inputDetails[0].trim().equalsIgnoreCase(EVENT)) {
            tasks.add(getEventTask(inputDetails));
        }
    }

    /**
     * Returns a todo task.
     * @param inputDetails A string array containing information about the task.
     * @return A todo task.
     */
    public Todo getTodoTask(String[] inputDetails) {
        Todo todo = new Todo(inputDetails[2]);
        if (inputDetails[1].trim().equalsIgnoreCase(COMPLETED)) {
            todo.isCompleted();
        } else {
            todo.isNotCompleted();
        }
        return todo;
    }

    /**
     * Returns a deadline task.
     * @param inputDetails A string array containing information about the task.
     * @return A deadline task.
     */
    public Deadline getDeadlineTask(String[] inputDetails) {
        Deadline deadline = new Deadline(inputDetails[2], inputDetails[3]);
        if (inputDetails[1].trim().equalsIgnoreCase(COMPLETED)) {
            deadline.isCompleted();
        } else {
            deadline.isNotCompleted();
        }
        return deadline;
    }

    /**
     * Returns an event task.
     * @param inputDetails A string array containing information about the task.
     * @return An event task.
     */
    public Event getEventTask(String[] inputDetails) {
        Event event = new Event(inputDetails[2], inputDetails[3], inputDetails[4]);
        if (inputDetails[1].trim().equalsIgnoreCase(COMPLETED)) {
            event.isCompleted();
        } else {
            event.isNotCompleted();
        }
        return event;
    }

    /**
     * Writes updated task to the file.
     * @param list A list of tasks.
     * @throws IOException Throw exception if there is an issue updating the file.
     */
    public void writeDataToFile(List<Task> list) throws IOException {
        Path directory = Paths.get("./data");
        if (!Files.exists(directory)) {
            Files.createDirectories(directory);
        }
        try {
            File file = new File(this.fileName);
            FileWriter fw = new FileWriter(file);
            for (Task task : list) {
                fw.write(task.toFile() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
