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

        } catch (IOException e) {
            String message = "An error occurred with the save file at the system level. Try again";
            throw new StartUpException(message, e);

        } catch (ProcessingException e) {
            String message = "An error occurred with parsing the save file. Try again";
            throw new StartUpException(message, e);
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

    public String add(Task task) throws ProcessingException {
        return temp.add(task);
    }
    public String delete(int i) throws ProcessingException {
        return temp.delete(i);
    }
    public String mark(int i) throws ProcessingException {
        return temp.mark(i);
    }
    public String unmark(int i) throws ProcessingException {
        return temp.unmark(i);
    }
    public String displayList() {
        return temp.displayList();
    }

    public String displaySearchList(String query) {
        return temp.displaySearchList(query);
    }

    public void close() {
        local.close();
    }
}
