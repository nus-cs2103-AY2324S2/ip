package kbot.managers;

import java.util.ArrayList;
import java.util.Scanner;

import kbot.actions.ParseTags;
import kbot.tasks.Deadline;
import kbot.tasks.Event;
import kbot.tasks.Task;
import kbot.tasks.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * TaskFileManager class that encapsulates the saving of data onto the local
 * hard drive.
 *
 * @author: CHEN WENLONG
 * @version: CS2103T AY23/24 Semester 2
 */
public class TaskFileManager {

    /** Filepath of where to save to local disk for TASKS */
    private static final String FILE_PATH = "data/storage.txt";

    /** Storage format for date. */
    private static final DateTimeFormatter STORAGE_FORMAT = DateTimeFormatter.ofPattern("d-M-yy");

    /**
     * Constructor for TaskFileManager.
     */
    public TaskFileManager() {
    }

    /**
     * Implementation to save tasks to file
     * 
     * @param tasks Tasks to be saved to the local drive.
     * @throws IOException Thrown when saving to file and file is not found.
     */
    public static void saveTasksToFile(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        for (Task t : tasks) {
            String taskToStore = t.convertToStorageFormat();
            fw.write(taskToStore + "\n");
        }
        fw.close();
    }

    /**
     * Implementation to load tasks from local drive.
     * 
     * @throws FileNotFoundException Thrown when file cannot be found.
     * @throws IOException           Thrown when there is issue loading the file.
     * @return Tasks which are saved in the local drive as an ArrayList<Task>.
     */
    public static ArrayList<Task> loadTasksFromFile() throws FileNotFoundException, IOException {
        File file = new File(FILE_PATH); // create a File for the given file path
        if (!file.exists()) { // Check if the file exists. If not, create a new file.
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        Scanner sc = new Scanner(file); // create a Scanner using the File as the source
        ArrayList<Task> tasks = new ArrayList<>();
        while (sc.hasNext()) { // TYPE | MARK | NAME \ BY \ FROM | TO
            String[] taskSegments = sc.nextLine().split(" \\| ", 2);
            String instruction = taskSegments[0];
            String parameter = taskSegments[1];
            tasks.add(loadTask(instruction, parameter));
        }
        sc.close();
        return tasks;
    }

    /**
     * Load the individual Task in the correct format using the instruction String
     * and the information stored in the instruction. Helper function for
     * loadTasksFromFile().
     * 
     * @param instruction String representing the different instructions.
     * @param parameter   String representing the information stored in the Task.
     * @return Returns the Task that is loaded from the given instruction and info.
     */
    public static Task loadTask(String instruction, String parameter) {
        assert instruction != null && instruction.length() > 0 : "File may be corrupted: Task type is not found!";
        Task t = null;
        switch (instruction) {
            case "T": {
                t = loadToDo(parameter);
                break;
            }
            case "D": {
                try {
                    t = loadDeadline(parameter);
                } catch (DateTimeParseException e) {
                    System.out.println(e.getMessage());
                }
                break;
            }
            case "E": {
                try {
                    t = loadEvent(parameter);
                } catch (DateTimeParseException f) {
                    System.out.println(f.getMessage());
                }
                break;
            }
        }
        return t;
    }

    /**
     * Loads ToDo task.
     * 
     * @param parameter Parameter to enter into ToDo task.
     * @return A new ToDo that stores the task information.
     */
    public static ToDo loadToDo(String parameter) {
        String[] todoInput = parameter.split(" \\| ", 3);
        boolean isCompleted = (!todoInput[0].trim().equals(""));
        String name = todoInput[1];
        String tags = todoInput[2];
        ParseTags pt = new ParseTags(tags);
        return new ToDo(name, isCompleted, pt.tagsStringToArray());
    }

    /**
     * Loads Deadline task.
     * 
     * @param parameter Parameter to enter into Deadline task.
     * @return A new Deadline that stores the task information.
     */
    public static Deadline loadDeadline(String parameter) throws DateTimeParseException {
        String[] deadlineInputs = parameter.split(" \\| ", 4);
        boolean isCompleted = (!deadlineInputs[0].trim().equals(""));
        String name = deadlineInputs[1];
        String date = deadlineInputs[2];
        String tags = deadlineInputs[3];
        ParseTags pt = new ParseTags(tags);
        LocalDate deadline = LocalDate.parse(date, STORAGE_FORMAT);
        return new Deadline(name, deadline, isCompleted, pt.tagsStringToArray());
    }

    /**
     * Loads Event task.
     * 
     * @param parameter Parameter to enter into Event task.
     * @return A new Event that stores the task information.
     */
    public static Event loadEvent(String parameter) throws DateTimeParseException {
        String[] eventInputs = parameter.split(" \\| ", 5);
        boolean isCompleted = (!eventInputs[0].trim().equals(""));
        String name = eventInputs[1];
        String fromDate = eventInputs[2];
        String toDate = eventInputs[3];
        String tags = eventInputs[4];
        ParseTags pt = new ParseTags(tags);
        LocalDate from = LocalDate.parse(fromDate, STORAGE_FORMAT);
        LocalDate to = LocalDate.parse(toDate, STORAGE_FORMAT);
        return new Event(name, from, to, isCompleted, pt.tagsStringToArray());
    }
}
