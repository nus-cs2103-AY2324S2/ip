package fishstock;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import fishstock.task.Task;
import fishstock.task.TaskException;
import fishstock.task.TaskFactory;

/**
 * Encapsulates a Storage object.
 * Handles all storing/loading of save files.
 */
class Storage {
    private final File db;
    private final String filePath;

    /**
     * Initializes a Storage object.
     *
     * @param filePath The path to the save file.
     */
    protected Storage(String filePath) {
        assert filePath.contains("/") : "File path is invalid";

        db = new File(filePath);
        this.filePath = filePath;
    }

    /**
     * Loads the entire save file into an ArrayList.
     *
     * @return The array with all Tasks.
     * @throws FishStockException The exceptions while loading all Tasks.
     */
    protected ArrayList<Task> load() throws FishStockException {
        try {
            ArrayList<Task> list = new ArrayList<>();
            addTasks(list);
            return list;

        } catch (FileNotFoundException e) {
            String pathToDb = filePath.substring(0, filePath.lastIndexOf("/"));
            new File(pathToDb).mkdir();
            throw new FishStockException("File not found... Starting new session...\n");

        } catch (TaskException e) {
            throw new FishStockException("File corrupted!... Starting new session...\n");
        }
    }

    private void addTasks(ArrayList<Task> list) throws FileNotFoundException, TaskException {
        Scanner sc = new Scanner(db);
        while (sc.hasNext()) {
            Task task = TaskFactory.fromStorageString(sc.nextLine());
            list.add(task);
        }
    }

    /**
     * Saves all Tasks in the TaskList into the save file.
     *
     * @throws IOException The exceptions while saving all Tasks.
     */
    protected void close(TaskList list) throws IOException {
        FileWriter fw = new FileWriter(db);
        for (Task task : list.list) {
            fw.write(task.toSaveFormat());
        }
        fw.close();
    }
}
