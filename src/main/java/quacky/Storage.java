package quacky;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.Scanner;
import java.io.FileNotFoundException;

/**
 * Represents the file used to store task data
 * adapted from:https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/storage/StorageFile.java
 */
public class Storage {
    /**
     * Default file path used if the user doesn't provide the file name.
     */
    public static final String DEFAULT_STORAGE_FILEPATH = "./data.txt";
    public final File file;

    public final Path path;

    /**
     * @throws InvalidStorageFilePathException if the default path is invalid
     */
    public Storage() throws InvalidStorageFilePathException {
        this(DEFAULT_STORAGE_FILEPATH);
    }

    /**
     * @throws InvalidStorageFilePathException if the given file path is invalid
     */
    public Storage(String filePath) throws InvalidStorageFilePathException {
        path = Paths.get(filePath);
        if (!isValidPath(path)) {
            throw new InvalidStorageFilePathException("Quacky.Quacky.Storage file should end with '.txt'");
        }
        this.file = new File(filePath);
    }

    /**
     * Saves the {@code addressBook} data to the storage file.
     *
     * @throws StorageOperationException if there were errors converting and/or storing data to file.
     */
    public void save(TaskList tasks) throws StorageOperationException {
        try {
            FileWriter fw = new FileWriter(this.file, false);
            String textToAdd = tasks.printSimplified();
            fw.write(textToAdd);
            fw.close();
        } catch (IOException ioe) {
            throw new StorageOperationException("Error writing to file: " + path);
        }
    }

    /**
     * Loads the {@code Quacky.Quacky.TaskList} data fromDate this storage file, and then returns it.
     * Returns an empty {@code Quacky.Quacky.TaskList} if the file does not exist, or is not a regular file.
     *
     * @throws StorageOperationException if there were errors reading and/or converting data fromDate file.
     */
    public TaskList load() throws StorageOperationException {
        TaskList tasks = new TaskList();
        if (!Files.exists(path) || !Files.isRegularFile(path)) {
            return tasks;
        }
        try {
            Scanner fileReader = new Scanner(file);
            while (fileReader.hasNext()) {
                String line = fileReader.nextLine();
                String[] parts = line.split(" \\| ");
                Task task = null;

                switch (parts[0]) {
                case "E":
                    task = new Event(parts[2], LocalDate.parse(parts[3]), LocalDate.parse(parts[4]));
                    break;
                case "D":
                    task = new Deadline(parts[2], LocalDate.parse(parts[3]));
                    break;
                case "T":
                    task = new Todo(parts[2]);
                    break;
                }
                if (task != null) {
                    if (parts[1].equals("0")) {
                        task.markDone();
                    }
                    try {
                        tasks.addTask(task);
                    } catch (QuackyException e) {
                        //This should not happen as each time a task is saved, there will be a check for clashes
                    }
                }
            }
            fileReader.close();
            return tasks;
        } catch (FileNotFoundException fnfe) {
            return tasks;
        }

    }

    /**
     * Returns true if the given path is acceptable as a storage file.
     * The file path is considered acceptable if it ends with '.txt'
     */
    private static boolean isValidPath(Path filePath) {
        return filePath.toString().endsWith(".txt");
    }


    /**
     * Signals that the given file path does not fulfill the storage filepath constraints.
     */
    public static class InvalidStorageFilePathException extends IllegalValueException {
        public InvalidStorageFilePathException(String message) {
            super(message);
        }
    }

    /**
     * Signals that some error has occured while trying to convert and read/write data between the application
     * and the storage file.
     */
    public static class StorageOperationException extends Exception {
        public StorageOperationException(String message) {
            super(message);
        }
    }
}
