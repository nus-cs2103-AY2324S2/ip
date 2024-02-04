package duke;

import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;




/**
 * Storage class handles the loading and saving of tasks to a file.
 */
public class Storage {
    private final String File_Path;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param file The file path to save and load tasks.
     */
    public Storage(String file) {
        this.File_Path = file;
    }

    /**
     * Saves a list of tasks to the file specified in the constructor.
     *
     * @param store The list of tasks to be saved.
     */
    public void saveTasks(ArrayList<Task> store) {
        try {
            File file = new File(File_Path);
            if (!file.exists()) {
                file.getParentFile().mkdirs(); // Create parent directories if they don't exist
                file.createNewFile(); // Create the file if it doesn't exist
            }

            FileWriter fileWriter = new FileWriter(File_Path);
            for (Task task : store) {
                fileWriter.write(task.toFileString() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }

    /**
     * Loads tasks from the file specified in the constructor using the Parser class.
     *
     * @return An ArrayList of Task objects loaded from the file.
     * @throws DukeException If an error occurs during the loading of tasks.
     */
    public ArrayList<Task> load() throws DukeException {
        try {
            File file = new File(File_Path);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            //System.out.println("b");
            Scanner fileScanner = new Scanner(file);
            ArrayList<Task> tasks = new ArrayList<>();

            while (fileScanner.hasNext()) {
                String line = fileScanner.nextLine();
                //System.out.println(line);
                Task task = Parser.parseTaskFromLine(line);
                tasks.add(task);
                //System.out.println("line");
            }

            fileScanner.close();
            return tasks;
        } catch (IOException e) {
            throw new DukeException("Error loading tasks from file: " + e.getMessage());
        }
    }
}
