import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Represents the storage unit for the application. Deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    private final FileWriter fileWriter;
    private final Scanner fileScanner;

    /**
     * Initialises Storage to load data from and save data to a file. Creates
     * the required directories and files if necessary.
     *
     * @param dataDir directory to store the data file
     * @param dataFileName name of data file (.txt format)
     * @throws IOException - if an I/O operation occurred using File
     */
    public Storage(String dataDir, String dataFileName) throws IOException {
        // Creates the directories if they do not exist yet. No effect if it exists.
        File dir = new File(dataDir);
        dir.mkdirs();

        // Creates the file if it does not exist yet. No effect if it exists.
        File dataFile = new File(dir, dataFileName);
        dataFile.createNewFile();

        this.fileWriter = new FileWriter(dataFile);
        this.fileScanner = new Scanner(dataFile);
    }

    /**
     * Saves the  task list to the designated text file for storing data for the program.
     * Creates one if it does not exist yet.
     */
    void saveToFile(TaskList tasks) {
        try {
            // Loops through taskList, writes them to the file with the specified format
            for (Task task: tasks.getTasks()) {
                String parsedTask = task.parsedFormatToSave();
                this.fileWriter.write(parsedTask + '\n');
            }
            this.fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Loads the taskList from the data file for this session
     */
    void loadFromFile(TaskList tasks) {
        try {
            // Data file exist in folder, load it into our taskList
            while (fileScanner.hasNextLine()) {
                String parsedString = fileScanner.nextLine();
                Task task = Task.createFromParsedString(parsedString);
                tasks.add(task);
            }
        } catch (CreateTaskException e) {
            System.out.println("Error loading tasks from save file. Might be corrupted...");
            throw new RuntimeException(e.getMessage());
        }
    }
}