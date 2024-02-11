package tsundere.task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import tsundere.exception.GeneralException;

/**
 * Encapsulates a Storage object that manages loading and saving of data.
 */
public class Storage {

    private static final String FILEPATH = "./data/data.txt";

    /**
     * Initializes Storage.
     */
    public Storage() {

        try {
            new File("./data").mkdirs();
            TaskList.taskList = loadTasksFromFile();
        } catch (IOException | GeneralException e) {
            System.out.println("Something went wrong with loading your previous session data!");
        }

    }

    /**
     * Saves current data in TaskList to provided path.
     * Creates new directory and file if needed.
     *
     * @throws IOException If file cannot be found at provided path and new file cannot be created.
     */
    public void saveTasksToFile() throws IOException {

        try (PrintWriter pw = new PrintWriter(new FileWriter(FILEPATH), true)) {

            for (Task task : TaskList.taskList) {
                pw.println(task.toSaveString());
            }
            System.out.println("Your session has been saved.");

        } catch (FileNotFoundException e) {

            File file = new File(FILEPATH);

            if (file.createNewFile()) {
                saveTasksToFile();
                System.out.println("Your session has been saved.");
            } else {
                throw new IOException("Unable to create the file: " + FILEPATH);
            }

        }

    }

    /**
     * Loads data from provided path.
     * Creates new directory and file if needed.
     *
     * @return TaskList containing data from provided path.
     * @throws IOException If Scanner object fails to read from file.
     * @throws GeneralException If parsing from stored data fails.
     */
    private ArrayList<Task> loadTasksFromFile() throws IOException, GeneralException {

        ArrayList<Task> tasks = new ArrayList<>();
        File f = new File(Storage.FILEPATH);

        if (f.exists()) {
            Scanner scanner = new Scanner(f);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Task task = parseTaskFromSaveString(line);
                tasks.add(task);
            }
            System.out.println("Your previous session has been restored. Enjoy your chat.");
        }
        return tasks;

    }

    /**
     * Parses stored data to be used for reconstructing TaskList.
     *
     * @param saveString Stored data.
     * @return Task object to be created from saved data.
     */
    private static Task parseTaskFromSaveString(String saveString) throws GeneralException {

        String[] parsedData = saveString.split(",");
        String type = parsedData[0];
        Task task = null;

        switch (type) {
        case ("T"):
            task = new ToDo(parsedData[3]);
            if (parsedData[2].equals("1")) {
                task.markTaskAsDone();
            }

            if (parsedData[1].equals("1")) {
                String[] x = parsedData[4].split(" ");
                for (String tag : x) {
                    task.tagTask(tag);
                }
            }

            break;
        case ("E"):

            task = new Event(parsedData[3], parsedData[4], parsedData[5]);
            if (parsedData[2].equals("1")) {
                task.markTaskAsDone();
            }

            if (parsedData[1].equals("1")) {
                String[] y = parsedData[6].split(" ");
                for (String tag : y) {
                    task.tagTask(tag);
                }
            }

            break;
        case ("D"):

            task = new Deadline(parsedData[3], parsedData[4]);
            if (parsedData[2].equals("1")) {
                task.markTaskAsDone();
            }
            if (parsedData[1].equals("1")) {
                String[] z = parsedData[5].split(" ");
                for (String tag : z) {
                    task.tagTask(tag);
                }
            }

            break;
        default:
            assert task == null : "task should not be null";
            break;
        }
        return task;
    }
}
