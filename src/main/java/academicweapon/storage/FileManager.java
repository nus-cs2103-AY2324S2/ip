package academicweapon.storage;

import academicweapon.exceptions.DukeExceptions;
import academicweapon.task.Deadline;
import academicweapon.task.Event;
import academicweapon.task.Task;
import academicweapon.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Utility class for managing file operations related to the Duke application.
 * The FileManager class provides methods for loading, reading and saving.
 */
public class FileManager {

    /**
     * File route for storing the task list data file.
     */
    private static final String fileRoute = "./src/main/data/acadList.txt";

    /**
     * Private constructor to prevent instantiation of the FileManager class.
     */
    private FileManager() {

    }

    /**
     * Reads the contents of the file and returns a list of tasks.
     *
     * @param readFile The file containing the task list data
     * @return An ArrayList containing tasks read from the file
     */
    public static ArrayList<Task> getList(File readFile) {
        ArrayList<Task> lst = new ArrayList<>();
        try {
            Scanner sc = new Scanner(readFile);
            while (sc.hasNext()) {
                String line = sc.nextLine();
                String[] lineSplit = line.split("\\|");
                String action = lineSplit[0].trim();
                String description = lineSplit[2].trim();
                String isDone = lineSplit[1].trim();
                if (action.equals("T")) {
                    Todo addTask = new Todo(description);
                    checkIfDone(addTask, isDone);
                    lst.add(addTask);
                } else if (action.equals("D")) {
                    Deadline addTask = new Deadline(description, lineSplit[3].trim());
                    checkIfDone(addTask, isDone);
                    lst.add(addTask);
                } else if (action.equals("E")) {
                    String[] splitFromAndTo = lineSplit[3].trim().split("-");
                    Event addTask = new Event(description, splitFromAndTo[0], splitFromAndTo[1]);
                    checkIfDone(addTask, isDone);
                    lst.add(addTask);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            return lst;
        }
    }

    /**
     * Checks if a task is done based on the input value and updates it
     *
     * @param t Task to check and update
     * @param val Value representing the task status (0 for not done, 1 for done)
     */
    public static void checkIfDone(Task t, String val) {
        if (val.equals("0")) {
            t.markAsNotDone();
        } else {
            t.markAsDone();
        }
    }

    /**
     * Loads the task list data file. Creates a new file if it does not exist.
     *
     * @return The loaded or newly created file
     */
    public static File load() {
        File file = new File(fileRoute);
        try {
            if (file.exists()) {
                return file;
            } else {
                file.createNewFile();
                return file;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Reads and displays the contents of the file to the console.
     *
     * @param fileToRead The file to read
     */
    public static void readFile(File fileToRead) {
        try {
            Scanner sc = new Scanner(fileToRead);
            System.out.println("This is retrieved from the file.");
            while(sc.hasNext()) {
                String currentLine = sc.nextLine();
                DukeExceptions.checkCorruptedFile(currentLine);
                System.out.println(currentLine);
            }
            System.out.println("____________________________________________________________");
        } catch (FileNotFoundException e) {
            System.out.println("Sorry, I can't seem to find the file that you want.");
        } catch (DukeExceptions e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Saves the task list data to the specified file.
     *
     * @param fileToSave The file to save the task list data
     * @param lst The list of tasks to be saved
     */
    public static void saveFile(File fileToSave, ArrayList<Task> lst) {
        try {
            FileWriter writer = new FileWriter(fileToSave);
            for (Task task : lst) {
                writer.write(task.fileToString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
