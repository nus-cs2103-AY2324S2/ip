package tsundere.task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.Scanner;

import tsundere.exception.GeneralException;

public class Storage {

    private static final String filepath = "./data/data.txt";

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

        try (PrintWriter pw = new PrintWriter(new FileWriter(filepath), true)) {

            for (Task task : TaskList.taskList) {
                pw.println(task.toSaveString());
            }
            System.out.println("Your session has been saved.");

        } catch (FileNotFoundException e) {

            System.out.println("hello there");
            File file = new File(filepath);

            if (file.createNewFile()) {
                saveTasksToFile();
                System.out.println("Your session has been saved.");
            } else {
                throw new IOException("Unable to create the file: " + filepath);
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
        File f = new File(Storage.filepath);

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
    private static Task parseTaskFromSaveString(String saveString) {

        String[] parsedData = saveString.split(",");
        String type = parsedData[0];
        Task task = null;

        switch (type) {
        case ("T"):
            task = new ToDo(parsedData[2]);
            if (parsedData[1].equals("1")) task.markAsDone();
            break;
        case ("E"):
            task = new Event(parsedData[2], parsedData[3], parsedData[4]);
            if (parsedData[1].equals("1")) task.markAsDone();
            break;
        case ("D"):
            task = new Deadline(parsedData[2], parsedData[3]);
            if (parsedData[1].equals("1")) task.markAsDone();
            break;
        default:
            assert task == null : "task should not be null";
            break;
        }
        return task;
    }
}