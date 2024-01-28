package duke.storage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Stream;

import duke.exceptions.ProcessingException;
import duke.exceptions.StartUpException;
import duke.tasks.Task;

/**
 * The `Storage` class manages the interaction between the temporary storage (`TempStorage`)
 * and local storage (`LocalStorage`) for saving, loading, updating, and performing operations
 * on tasks in the Duke application.
 */
public class Storage {

    private final LocalStorage local;
    private final TempStorage temp;

    /**
     * Constructs a new `Storage` object with the specified file location.
     *
     * @param fileLocation The file location where tasks are stored.
     * @throws StartUpException If an error occurs during the startup process, such as file access issues.
     */
    public Storage(String fileLocation) throws StartUpException {
        temp = new TempStorage();

        try {
            local = new LocalStorage(fileLocation);
            if (local.createdNewFile()) {
                saveFileNotFound();
            } else {
                saveFileFound();
                ArrayList<String> loadList = local.load();
                temp.load(loadList); //Load from local into temp
            }

        } catch (IOException | ProcessingException e) {
            String message = "An error occurred with the save file. Try again";
            e.printStackTrace();
            throw new StartUpException(message);
        }

    }
    private void saveFileNotFound() {
        System.out.println("Save file not found! Created an new one!");
    }
    private void saveFileFound() {
        System.out.println("Save file found! Loading old save...");
    }

    /**
     * Updates the local storage with the data from the temporary storage.
     *
     * @throws ProcessingException If an error occurs while updating the save file.
     */
    public void update() throws ProcessingException {
        try {
            Stream<String> stringStream = temp.save();
            local.save(stringStream);

        } catch (IOException e) {
            String message = "An error occurred while trying to update the save file. Try again";
            e.printStackTrace();
            throw new ProcessingException(message);
        }
    }

    public void add(Task task) throws ProcessingException {
        temp.add(task);
    }
    public void delete(int i) throws ProcessingException {
        temp.delete(i);
    }
    public void mark(int i) throws ProcessingException {
        temp.mark(i);
    }
    public void unmark(int i) throws ProcessingException {
        temp.unmark(i);
    }
    public void displayList() {
        temp.displayList();
    }

}
