package felix.utils;

import felix.exception.FelixException;
import felix.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class Storage {
    private final File storageFile;

    /**
     * Constructor for Storage class.
     *
     * @param path ArrayList of Strings representing relative path to save file when joined by file separators.
     * @throws IOException If there is error creating the new file.
     */
    public Storage(ArrayList<String> path) throws IOException {
        String filePath = System.getProperty("user.dir") + File.separator + String.join(File.separator, path);
        this.storageFile = new File(filePath);
        if (this.storageFile.getParentFile().mkdirs()) {
            System.out.println("directory created");
        } else {
            System.out.println("directory already exists");
        }
        if (this.storageFile.createNewFile()) {
            System.out.println("new file created");
        } else {
            System.out.println("file already exists");
        }
    }

    /**
     * Generates a list of tasks from the storage file associated with the created Storage instance.
     *
     * @return list of tasks.
     * @throws FelixException If file is not found, there is error reading from the file, or datetime is not in correct format.
     */
    public TaskList getTasksFromFile() throws FelixException {
        try {
            TaskList taskList = new TaskList();
            BufferedReader bufferedReader = new BufferedReader(new FileReader(this.storageFile));
            String line = bufferedReader.readLine();
            while (line != null) {
                taskList.addTask(Task.createTaskFromFileLine(line));
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
            return taskList;
        } catch (FileNotFoundException err) {
            throw new FelixException("File not found");
        } catch (IOException err) {
            throw new FelixException("Cannot read from file");
        } catch (DateTimeParseException err) {
            throw new FelixException("datetime not in the format \"yyyy-MM-dd HHmm\"");
        }
    }

    /**
     * Writes the list of tasks to the storage file associated with the created Storage instance.
     *
     * @param tasks List of tasks.
     * @throws FelixException If error arises when writing to file.
     */
    public void writeToFile(TaskList tasks) throws FelixException {
        try {
            // overwrites existing file if the file exists
            FileWriter writer = new FileWriter(this.storageFile);
            writer.write(tasks.getFileRepresentation());
            writer.close();
        } catch (IOException e) {
            throw new FelixException("Error when writing to file: " + e.getMessage());
        }
    }
}
