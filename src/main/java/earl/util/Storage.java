package earl.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import earl.exceptions.EarlException;
import earl.tasks.Task;

/**
 * Class responsible for reading and writing data to disk.
 */
public class Storage {

    private final String filePath;
    private final ArrayList<Task> tasks = new ArrayList<>();

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
     * @throws EarlException if the file could not be created or read
     */
    public ArrayList<Task> load() throws EarlException {
        try {
            File file = new File(filePath);
            boolean isFolderMade = file.getParentFile().mkdirs();
            boolean isFileMade = file.createNewFile();
            if (isFolderMade || isFileMade) {
                // file was surely missing
                assert file.exists() : "File is missing but not made.";
                throw new EarlException("Storage file missing... "
                        + "creating new file.");
            }
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String entry = sc.nextLine();
                tasks.add(Parser.parseStorageEntry(entry));
            }
        } catch (EarlException e) {
            throw new EarlException("Storage file is corrupted... "
                    + "starting with empty list.");
        } catch (Exception e) {
            throw new EarlException("Unknown exception occurred "
                    + "when attempting to create or access "
                    + "storage file: " + e.getMessage());
        }
        return tasks;
    }

    /**
     * Saves given list of {@code Task} onto the disk.
     *
     * @param tasks an {@code ArrayList} of {@code Task} to be saved
     * @throws EarlException if the file could not be written to
     */
    public void save(ArrayList<Task> tasks) throws EarlException {
        try (FileWriter fw = new FileWriter(filePath)) {
            for (Task task : tasks) {
                fw.write(task.toStorageString() + "\n");
            }
        } catch (IOException e) {
            throw new EarlException("Fatal error while saving to storage.");
        }
    }
}
