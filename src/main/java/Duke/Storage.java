package duke;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * This class handles the storage of tasks in a file.
 * It provides methods to create a storage file, load tasks from the file, and store tasks in the file.
 */
public class Storage {
    private final static String dirPath = "./data/";
    private final static String filePath = "./data/taskList.txt";
    private static File file;

    /**
     * Initializes the storage by creating the storage file and loading tasks from it.
     */
    public static void init() {
        try {
            create();
            load();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Creates the storage file if it does not exist.
     *
     * @throws IOException if an I/O error occurs
     */
    private static void create() throws IOException {
        File directory = new File(dirPath);

        if (!directory.exists()) {
            if (!directory.mkdirs()) {
                System.out.println("Failed to create directory.");
                return;
            }
        }

        file = new File(directory, "taskList.txt");
        if (!file.exists()) {
            file.createNewFile();
        }
    }

    /**
     * Stores the current tasks in the storage file.
     */
    public static void store() {
        FileWriter fw = null;
        try {
            // To reset the file
            fw = new FileWriter(filePath);
            fw.write("");
            fw.close();

            fw = new FileWriter(filePath, true);
            for (int i = 1; i <= TaskList.listSize(); i++) {
                Task task = TaskList.getTask(i);
                String encodedTask = task.encode();
                fw.write(encodedTask + "\n");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while storing data: " + e.getMessage());
        } finally {
            try {
                if (fw != null) {
                    fw.close();
                }
            } catch (IOException e) {
                System.out.println("An error occurred while closing the FileWriter: " + e.getMessage());
            }
        }
    }

    /**
     * Loads tasks from the storage file.
     *
     * @throws IOException if an I/O error occurs
     */
    private static void load() throws IOException {
        Scanner s = new Scanner(file);
        while (s.hasNext()) {
            String line = s.nextLine();
            try {
                decode(line);
            } catch (InvalidDateFormatException e) {
                System.out.println("Decoding Error: " + e.getMessage());
            }
        }
        s.close();
    }

    /**
     * Decodes a line from the storage file into a task.
     *
     * @param line the line to decode
     * @throws InvalidDateFormatException if the date format in the line is invalid
     */
    private static void decode(String line) throws InvalidDateFormatException {
        Task task;
        String taskDetails = line.substring(7);

        char taskType = line.charAt(1);
        switch (taskType) {
            case 'T':
                task = ToDo.decode(taskDetails);
                break;
            case 'D':
                task = Deadline.decode(taskDetails);
                break;
            case 'E':
                task = Event.decode(taskDetails);
                break;
            default:
                assert false: "Should not fall into default case of switch block for decode method!";
                task = new ToDo("ERROR");
                System.out.println("Error: Decoding Error, task does not match any of the known types");           
        }
        TaskList.addTaskSilently(task);

        boolean isTaskDone = line.charAt(4) == 'X';
        if (isTaskDone) {
            TaskList.markTaskSilently(TaskList.listSize());
        }
    }
}

