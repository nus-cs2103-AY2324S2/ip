package duke;

import duke.exceptions.DukeException;

import duke.exceptions.StorageException;
import duke.task.*;

import java.io.File;
import java.util.Scanner;
import java.io.FileWriter;
import java.time.LocalDate;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.time.format.DateTimeParseException;

/** Helper class to manage all storage related methods of duke. */
public class Storage {
    private File file;
    private String filePath;

    /**
     * Constructs a duke.Storage object with the data file
     *
     * Verifies if this file exists, and will create a new file/directory if needed
     */
    public Storage(String filePath) {
        this.file = new File(filePath);
        this.filePath = filePath;
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
                System.out.println("Data not found, created new file at: " + filePath);
            } else {
                System.out.println("Data found at: " + filePath);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error creating file: " + e.getMessage(), e);
        }
    }

    /**
     * Saves contents of taskList to memory
     *
     * @param taskList duke.command.task.TaskList instance to save
     * @see TaskList
     */
    public void save(TaskList taskList) {
        try {
            FileWriter fw = new FileWriter(this.filePath);
            for (Task task : taskList) {
                fw.write(task.getTokens() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Loads taskList from datafile and returns a duke.command.task.TaskList object
     *
     * @return duke.command.task.TaskList object
     * @throws StorageException
     * @see TaskList
     */
    public TaskList load() throws StorageException{
        TaskList taskList = new TaskList();

        try {
            Scanner s = new Scanner(this.file);
            while (s.hasNext()) {
                Task next_task = parseLineFromStorage(s.nextLine());
                taskList.add(next_task);
            }
        } catch (FileNotFoundException e) {
            throw new StorageException("File / Directory does not exist.");
        }

        return taskList;
    }

    private Task parseLineFromStorage(String tokens) throws StorageException {
        try {
            String[] data = tokens.split(",");
            switch (data[0]) {
            case "TODO":
                return Task.createTask(TaskType.TODO, data[1], Boolean.parseBoolean(data[2]));
            case "EVENT":
                return Task.createTask(TaskType.EVENT, data[1], Boolean.parseBoolean(data[2]),
                        LocalDate.parse(data[3], Task.getDateFormat()),
                        LocalDate.parse(data[4], Task.getDateFormat()));
//                    return new Event(data[1], Boolean.parseBoolean(data[2]),
//                            LocalDate.parse(data[3], Task.getDateFormat()),
//                            LocalDate.parse(data[4], Task.getDateFormat()));
            case "DEADLINE":
                return Task.createTask(TaskType.DEADLINE, data[1], Boolean.parseBoolean(data[2]),
                        LocalDate.parse(data[3], Task.getDateFormat()));
//                    return new Deadline(data[1], Boolean.parseBoolean(data[2]), LocalDate.parse(data[3], Task.getDateFormat()));
            default:
                throw new StorageException("Data file is corrupted, task type does not exist");
            }
        } catch (ArrayIndexOutOfBoundsException | DateTimeParseException | IllegalArgumentException e) {
            throw new StorageException("Data file is corrupted, error parsing data: " + e.getMessage());
        }
    }
}
