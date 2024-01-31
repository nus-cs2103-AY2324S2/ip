import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.io.IOException;
import java.time.format.DateTimeParseException;

import static java.lang.Boolean.parseBoolean;

/** Helper class to manage all storage related methods of duke. */
public class Storage {
    private File file;
    private static final String DEFAULT_PATH = "./data/duke.txt";

    /**
     * Constructs a Storage object with the data file
     *
     * Verifies if this file exists, and will create a new file/directory if needed
     */
    public Storage() {
        this.file = new File(DEFAULT_PATH);
        try {
            // Check if the parent directory exists; if not, create it
            File parentDir = file.getParentFile();
            if (!parentDir.exists()) {
                if (parentDir.mkdirs()) {
                    System.out.println("Created parent directory at: " + parentDir.getAbsolutePath());
                } else {
                    throw new RuntimeException("Error creating parent directory at: " + parentDir.getAbsolutePath());
                }
            }

            // Check if file exist, if not create
            if (file.createNewFile()) {
                System.out.println("Data not found, created new file at: " + DEFAULT_PATH);
            } else {
                System.out.println("Data found at: " + DEFAULT_PATH);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error creating file: " + e.getMessage(), e);
        }
    }

    /**
     * Saves contents of taskList to memory
     *
     * @param taskList TaskList instance to save
     * @see TaskList
     */
    public void save(TaskList taskList) {
        try {
            FileWriter fw = new FileWriter(DEFAULT_PATH);
            for (Task task : taskList) {
                fw.write(task.getTokens() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Loads taskList from datafile and returns a TaskList object
     *
     * @return TaskList object
     * @see TaskList
     */
    public TaskList load() {
        TaskList taskList = new TaskList();

        try {
            Scanner s = new Scanner(this.file);
            while (s.hasNext()) {
                Task next_task = parseLineFromStorage(s.nextLine());
                taskList.add(next_task);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (DukeException.CorruptedDataException e) {
            System.out.println(e.getMessage() + "Please check the datafile and delete if not recoverable.");
            throw new RuntimeException(e);
        }

        return taskList;
    }

    private Task parseLineFromStorage(String tokens) throws DukeException.CorruptedDataException {
        try {
            String[] data = tokens.split(",");
            switch (data[0]) {
                case "T":
                    return new Task.ToDos(data[1], parseBoolean(data[2]));
                case "E":
                    return new Task.Events(data[1], parseBoolean(data[2]), data[3], data[4]);
                case "D":
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
                    return new Task.Deadlines(data[1], parseBoolean(data[2]), LocalDate.parse(data[3], formatter));
                default:
                    throw new DukeException.CorruptedDataException("Data file is corrupted, task type does not exist");
            }
        } catch (ArrayIndexOutOfBoundsException | DateTimeParseException | IllegalArgumentException e) {
            throw new DukeException.CorruptedDataException("Data file is corrupted, error parsing data: " + e.getMessage());
        }
    }
}
