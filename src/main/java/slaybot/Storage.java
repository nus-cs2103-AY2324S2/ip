package slaybot;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entity.Deadline;
import entity.Event;
import entity.Task;
import entity.ToDo;

/**
 * The Storage class deals with loading tasks from the file and saving tasks in the file
 */
public class Storage {
    private static final String PATH = "./logs/slaybot.txt";

    /**
     * Constructor for Storage class
     */
    public Storage() {
        try {
            Path filePath = Paths.get(PATH);

            Files.createDirectories(filePath.getParent());

            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }
        } catch (IOException e) {
            System.out.println("Error" + e.getMessage());
        }

    }

    /**
     * Saves the tasks from the provided TaskList to a file.
     * <p>
     * This method takes a TaskList object, retrieves the list of tasks from it, and
     * writes each Task to a file in the PATH
     * The tasks are saved in a format suitable for later retrieval.
     *
     * @param tasks The TaskList object which contains the Task objects to be saved
     *
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public static void saveTasks(TaskList tasks) {
        List<Task> list = tasks.getTasks();

        try {
            FileWriter fw = new FileWriter(PATH);
            for (Task t : list) {
                fw.write(t.save() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Loads tasks from the file defined by {@link #PATH}.
     * This method reads the file line by line, parses each line to recreate the task objects,
     * and adds them to a list of tasks which is then returned.
     *
     * @return A list of tasks loaded from the file.
     */
    public List<Task> loadData() {
        List<Task> result = new ArrayList<>();
        try {
            File f = new File(PATH);
            Scanner sc = new Scanner(f);

            while (sc.hasNextLine()) {
                String task = sc.nextLine();
                String[] taskDetails = task.split("\\|");
                Task currentTask;

                String taskType = taskDetails[0];
                switch (taskType) {
                case "T":
                    currentTask = loadToDo(taskDetails);
                    break;
                case "D":
                    currentTask = loadDeadline(taskDetails);
                    break;
                case "E":
                    currentTask = loadEvent(taskDetails);
                    break;
                default:
                    currentTask = null;
                    break;
                }
                if (currentTask != null) {
                    result.add(currentTask);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    /**
     * Parses the details of a ToDo task from a string array and creates a ToDo object.
     *
     * @param taskDetails A string array containing the details of the ToDo task.
     * @return A new ToDo object.
     */
    public ToDo loadToDo(String[] taskDetails) {
        if (taskDetails[1].trim().equals("Not Done")) {
            return new ToDo(taskDetails[2].trim(), false);
        } else {
            return new ToDo(taskDetails[2].trim(), true);
        }
    }

    /**
     * Parses the details of a Deadline task from a string array and creates a Deadline object.
     *
     * @param taskDetails A string array containing the details of the Deadline task.
     * @return A new Deadline object with the specified description, completion status, and deadline.
     */
    public Deadline loadDeadline(String[] taskDetails) {
        LocalDateTime dateTime = LocalDateTime.parse(taskDetails[3]);
        if (taskDetails[1].trim().equals("Not Done")) {
            return new Deadline(taskDetails[2].trim(), false, dateTime);
        } else {
            return new Deadline(taskDetails[2].trim(), true, dateTime);
        }
    }

    /**
     * Parses the details of an Event task from a string array and creates an Event object.
     *
     * @param taskDetails A string array containing the details of the Event task.
     * @return A new Event object with the specified description, completion status, start time, and end time.
     */
    public Event loadEvent(String[] taskDetails) {
        LocalDateTime startTime = LocalDateTime.parse(taskDetails[3]);
        LocalDateTime endTime = LocalDateTime.parse(taskDetails[4]);
        if (taskDetails[1].trim().equals("Not Done")) {
            return new Event(taskDetails[2].trim(), false, startTime, endTime);
        } else {
            return new Event(taskDetails[2].trim(), true, startTime, endTime);
        }
    }

}
