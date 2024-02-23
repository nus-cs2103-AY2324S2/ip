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
        List<Task> lst = new ArrayList<>();
        try {
            File file = new File(this.fileName);
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String str = sc.nextLine();
                String[] subStr = str.split("\\| ");
                if (subStr[0].trim().equalsIgnoreCase(TODO)) {
                    Todo todo = getTodoTask(subStr);
                    lst.add(todo);
                } else if (subStr[0].trim().equalsIgnoreCase(DEADLINE)) {
                    Deadline deadline = getDeadlineTask(subStr);
                    lst.add(deadline);
                } else if (subStr[0].trim().equalsIgnoreCase(EVENT)) {
                    Event event = getEventTask(subStr);
                    lst.add(event);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Chat history are not present!");
        }
        return lst;
    }

    /**
     * Returns a todo task.
     * @param subStr A string array containing information about the task.
     * @return A todo task.
     */
    public Todo getTodoTask(String[] subStr) {
        Todo todo = new Todo(subStr[2]);
        if (subStr[1].trim().equalsIgnoreCase(COMPLETED)) {
            todo.isCompleted();
        } else {
            todo.isNotCompleted();
        }
        return todo;
    }

    /**
     * Returns a deadline task.
     * @param subStr A string array containing information about the task.
     * @return A deadline task.
     */
    public Deadline getDeadlineTask(String[] subStr) {
        Deadline deadline = new Deadline(subStr[2], subStr[3]);
        if (subStr[1].trim().equalsIgnoreCase(COMPLETED)) {
            deadline.isCompleted();
        } else {
            deadline.isNotCompleted();
        }
        return deadline;
    }

    /**
     * Returns an event task.
     * @param subStr A string array containing information about the task.
     * @return An event task.
     */
    public Event getEventTask(String[] subStr) {
        Event event = new Event(subStr[2], subStr[3], subStr[4]);
        if (subStr[1].trim().equalsIgnoreCase(COMPLETED)) {
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
