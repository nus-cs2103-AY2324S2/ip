package bytebuddy.storage;

import static bytebuddy.constants.FilePaths.RELATIVE_DATA_DIRECTORY_PATH;
import static bytebuddy.constants.FilePaths.RELATIVE_OUTPUT_TXT_FILE_PATH;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import bytebuddy.exceptions.ByteBuddyException;
import bytebuddy.tasks.Deadline;
import bytebuddy.tasks.Event;
import bytebuddy.tasks.TaskList;
import bytebuddy.tasks.Todo;

/**
 * The Storage class manages the reading and writing of tasks to a file.
 */
public class Storage {

    /**
     * Creates a new Storage instance, initializing the data directory and output file.
     *
     * @throws ByteBuddyException If there is an issue with creating the directory or file.
     */
    public Storage() throws ByteBuddyException {
        init();
    }

    /**
     * Initializes the data directory and output file.
     *
     * @throws ByteBuddyException If there is an issue with creating the directory or file.
     */
    public void init() throws ByteBuddyException {
        File dataDir = new File(RELATIVE_DATA_DIRECTORY_PATH);
        File outputTxt = new File(RELATIVE_OUTPUT_TXT_FILE_PATH);
        createOutputDirectoryAndFile(dataDir, outputTxt);
    }

    /**
     * Creates the output directory and file.
     *
     * @param dataDir   The data directory.
     * @param outputTxt The output file.
     * @throws ByteBuddyException If there is an issue with creating the directory or file.
     */
    public static void createOutputDirectoryAndFile(File dataDir, File outputTxt) throws ByteBuddyException {
        try {
            dataDir.mkdirs();
            outputTxt.createNewFile();
        } catch (SecurityException | IOException e) {
            throw new ByteBuddyException(e.toString());
        }
    }


    /**
     * Loads tasks from the output file and returns a TaskList.
     *
     * @return The TaskList loaded from the output file.
     * @throws ByteBuddyException If there is an issue with reading from the file.
     */
    public TaskList load() throws ByteBuddyException {
        return initTaskList(RELATIVE_OUTPUT_TXT_FILE_PATH);
    }

    /**
     * Writes the given text to the specified file path.
     *
     * @param filePath  The path to the file.
     * @param textToAdd The text to write to the file.
     * @throws IOException If there is an issue with writing to the file.
     */
    public static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Initializes a TaskList by reading tasks from the specified file path.
     *
     * @param filePath The path to the file.
     * @return The initialized TaskList.
     * @throws ByteBuddyException If there is an issue with reading from the file.
     */
    public static TaskList initTaskList(String filePath) throws ByteBuddyException {
        TaskList list = new TaskList();

        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNext()) {
                String[] parts = scanner.nextLine().split(" \\| ");
                assert parts.length >= 3 : "Invalid task format";

                switch (parts[0]) {
                case "T":
                    assert parts.length == 3 : "Invalid Todo task format";
                    list.add(new Todo(parts[1], parts[2]));
                    break;
                case "D":
                    assert parts.length == 4 : "Invalid Deadline task format";
                    list.add(new Deadline(parts[1], parts[2], parts[3]));
                    break;
                case "E":
                    assert parts.length == 5 : "Invalid Event task format";
                    list.add(new Event(parts[1], parts[2], parts[3], parts[4]));
                    break;
                default:
                    assert false : "Unknown task type";
                    break;
                }
            }
        } catch (IOException e) {
            throw new ByteBuddyException("Error reading from file: " + e);
        }

        return list;
    }
}
