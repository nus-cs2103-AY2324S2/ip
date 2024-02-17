package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Storage class is responsible for handling file operations including
 * loading tasks from a file and saving tasks to a file.
 */
public class Storage {
    protected static String filePath;
    protected Scanner scanner;
    protected File file;

    /**
     * Creates a Storage object which initializes a Scanner for reading from a file.
     * The file path is specified by the provided string.
     *
     * @param filePath The file path of the storage file.
     * @throws DukeException If the file is not found.
     */
    public Storage(String filePath) throws DukeException {
        try {
            Storage.filePath = filePath;
            this.file = new File(filePath);
            // Attempt to create the file if it does not exist
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists.");
            }
            this.scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new DukeException("File not found: " + filePath);
        } catch (IOException e) {
            throw new DukeException("An error occurred while creating or opening the file: " + filePath);
        }
    }

    /**
     * Writes the given text to a file at the specified file path.
     *
     * @param filePath The file path where the text is to be written.
     * @param textToAdd The text to write to the file.
     * @throws IOException If an I/O error occurs.
     */
    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        File file = new File(filePath);
        FileWriter fw = new FileWriter(file);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Saves the current list of tasks to the file.
     *
     * @param list The task list to be saved.
     * @throws IOException If an I/O error occurs during writing.
     */
    public static void saveCurrentList(TaskList list) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (Task task : list.getList()) {
            sb.append(task.toFileString());
            sb.append("\n");
        }
        writeToFile(filePath, sb.toString());
    }

    /**
     * Loads tasks from the file and returns them as an ArrayList of Task objects.
     *
     * @return An ArrayList of Task objects.
     * @throws DukeException If an error occurs while reading from the file.
     */
    public ArrayList<Task> load() throws DukeException {
        assert scanner != null : "Scanner should not be null";
        assert file != null : "File should not be null";
        ArrayList<Task> list = new ArrayList<>();
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String type = line.substring(0, 1);
            assert type.equals("T") || type.equals("D") || type.equals("E") : "Invalid task type";
            switch (type) {
            case "T":
                list.add(Task.fromFileString(line));
                break;
            case "D":
                list.add(Deadline.fromFileString(line));
                break;
            case "E":
                list.add(Event.fromFileString(line));
                break;
            default:
                throw new DukeException("Error loading file");
            }
        }
        return list;
    }
}
