package lucky.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import lucky.common.Utils;
import lucky.tasks.Deadline;
import lucky.tasks.Event;
import lucky.tasks.Task;
import lucky.tasks.ToDo;

/**
 * The `Storage` class provides methods for reading and writing tasks to a file.
 */
public class Storage {

    private static final String FILE_PATH = "src/main/java/lucky/resources/data.txt";
    private static final String DELIMITER = "~";

    /**
     * Empty constructor for Storage.
     */
    public Storage() {

    }

    /**
     * Initialises an ArrayList of Task objects by either reading from a storage file or creating a new
     * ArrayList if the file does not exist.
     *
     * @return The method is returning an ArrayList of task. This contains tasks from previous
     *         application session if there's any.
     * @throws FileNotFoundException if the FILE_PATH does not exist.
     */
    public static ArrayList<Task> init() throws FileNotFoundException {
        if (new File(FILE_PATH).exists()) {
            return Storage.readFromStorage();
        } else {
            File file = new File(FILE_PATH);
            file.getParentFile().mkdirs();

            assert file.getParentFile().exists() : "Parent folder of data.txt, resources,"
                    + " should always exist after calling init()";

            return new ArrayList<>();
        }
    }

    /**
     * Reads tasks from a file and returns them as an ArrayList.
     *
     * @return The method is returning an ArrayList of Task objects.
     * @throws FileNotFoundException if the FILE_PATH does not exist.
     */
    public static ArrayList<Task> readFromStorage() throws FileNotFoundException {
        try {
            File file = new File(FILE_PATH);
            Scanner sc = new Scanner(file);
            ArrayList<Task> tasks = new ArrayList<>();

            while (sc.hasNext()) {
                String[] input = sc.nextLine().split(DELIMITER);
                Task task = parseInput(input);
                if (task != null) {
                    tasks.add(task);
                }
            }
            sc.close();
            return tasks;
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Unable to find the file path...");
        }
    }

    /**
     * Takes an array of strings as input and returns a `Task` object based on the command and data
     * provided in the input.
     *
     * @param input An array of strings representing the input data for a task. The first element of the
     *              array is the command, and the subsequent elements contain the necessary information for
     *              creating the task object.
     * @return The method `parseInput` returns a `Task` object.
     */
    private static Task parseInput(String[] input) {
        String command = input[0];
        switch (command) {
        case "event":
            return getEventFromFile(input);
        case "todo":
            return getTodoFromFile(input);
        case "deadline":
            return getDeadlineFromFile(input);
        default:
            System.out.println("Error in loading task: " + input[0]);
            return null;
        }
    }

    private static boolean isValidFileLineInput(int numOfDelimiter, String[] input) {
        return input.length > numOfDelimiter && (input[1].equals("0") || input[1].equals("1"));
    }

    private static ToDo getTodoFromFile(String[] input) {
        // format: todo~status~description
        if (!isValidFileLineInput(2, input)) {
            // corrupted data
            System.out.println("Error in loading a todo task...");
            return null;
        }

        ToDo todo = new ToDo(input[2]);
        todo.setMarked(input[1].equals("1"));

        if (input.length > 3) {
            todo.addTags(Arrays.copyOfRange(input, 3, input.length));
        }

        return todo;
    }

    private static Event getEventFromFile(String[] input) {
        // format: event~status~description~<date>~<date>
        if (!isValidFileLineInput(4, input) || !Utils.isValidDateTimeFormat(input[3], input[4])) {
            // corrupted data
            System.out.println("Error in loading an event task...");
            return null;
        }

        Event event = new Event(input[2], Utils.parseDateTime(input[3]),
                Utils.parseDateTime(input[4]));
        event.setMarked(input[1].equals("1"));

        if (input.length > 5) {
            event.addTags(Arrays.copyOfRange(input, 5, input.length));
        }

        return event;
    }

    private static Deadline getDeadlineFromFile(String[] input) {
        // format: deadline~status~description~<date>
        if (!isValidFileLineInput(3, input) || !Utils.isValidDateTimeFormat(input[3])) {
            // corrupted data
            System.out.println("Error in loading a deadline task...");
            return null;
        }

        Deadline deadline = new Deadline(input[2], Utils.parseDateTime(input[3]));
        deadline.setMarked(input[1].equals("1"));

        if (input.length > 4) {
            deadline.addTags(Arrays.copyOfRange(input, 4, input.length));
        }

        return deadline;
    }

    /**
     * Writes a list of tasks to a storage file.
     *
     * @param tasks An ArrayList of Task objects that need to be written to storage.
     */
    public static void writeToStorage(ArrayList<Task> tasks) throws IOException {
        try {
            FileWriter fw = new FileWriter(FILE_PATH);

            for (Task task : tasks) {
                fw.write(task.toStorageString() + "\n");
            }

            fw.close();
        } catch (IOException e) {
            throw new IOException();
        }
    }

    /**
     * Writes a task object to a file in storage.
     *
     * @param task The parameter `task` is of type `Task`, which is an object representing a task.
     */
    public static void writeToStorage(Task task) throws IOException {
        assert task != null : "Task being written to storage should never be null";

        try {
            FileWriter fw = new FileWriter(FILE_PATH, true);

            fw.write(task.toStorageString() + "\n");

            fw.close();
        } catch (IOException e) {
            throw new IOException("file not found");
        }
    }
}
