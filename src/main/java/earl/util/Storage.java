package earl.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

import earl.exceptions.EarlException;
import earl.tasks.Task;
import earl.util.parsers.StorageParser;

/**
 * Class responsible for reading and writing data to disk.
 */
public class Storage {

    private final String filePath;
    private final List<Task> tasks = new ArrayList<>();
    private boolean wasLoadSuccessful = false;

    /**
     * Class constructor.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns an {@code ArrayList} of {@code Task} read from the disk.
     * <p>
     * Attempts to find the storage file at the given file path.
     * Starts with an empty file if no existing file is found.
     *
     * @return an {@code ArrayList} of {@code Task} read
     */
    public List<Task> load() {
        try {
            File file = new File(filePath);
            boolean isFolderMade = file.getParentFile().mkdirs();
            boolean isFileMade = file.createNewFile();
            assert file.exists();
            if (isFolderMade || isFileMade) {
                return tasks; // file is surely missing
            }
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String entry = sc.nextLine();
                Task task = StorageParser.parse(entry);
                tasks.add(task);
            }
            wasLoadSuccessful = true;
            return tasks;
        } catch (Exception e) {
            return new ArrayList<>(); // start with empty list
        }
    }

    /** Returns if loading from storage occurred without error. */
    public boolean wasLoadSuccessful() {
        return wasLoadSuccessful;
    }

    /**
     * Saves given list of {@code Task} onto the disk.
     *
     * @param tasks a {@code List} of {@code Task} to be saved
     * @throws EarlException if the file could not be written to
     */
    public void save(Stream<Task> tasks) throws EarlException {
        try (FileWriter fw = new FileWriter(filePath)) {
            String[] data = tasks.map(Task::toStorageString)
                    .map((str) -> str + "\n")
                    .toArray(String[]::new);
            for (String line : data) {
                fw.write(line);
            }
        } catch (IOException e) {
            throw new EarlException("Fatal error while saving to storage.");
        }
    }
}
