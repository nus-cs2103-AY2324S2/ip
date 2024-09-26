package plato.storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;

import plato.PlatoException;
import plato.task.Actions;
import plato.task.Deadline;
import plato.task.Event;
import plato.task.Task;
import plato.task.TaskManager;
import plato.task.Todo;

/**
 * Storage to handle saving and loading.
 */
public class Storage {
    private final String filePath;

    /**
     * Creates a storage instead with the given file path.
     *
     * @param filePath A String file path of the save location.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads from the given file path or creates it if it does not exist.
     *
     * @return A TaskManager that contains task from the filepath if any.
     * @throws PlatoException Unable to load file.
     */
    public TaskManager loadFile() throws PlatoException {
        TaskManager manager = new TaskManager();
        File directory = new File("data");
        if (!directory.exists()) {
            boolean created = directory.mkdir();
            if (!created) {
                throw new PlatoException("directoryError");
            }
        }
        if (!filePath.contains("txt") || !filePath.contains("data")) {
            throw new PlatoException("loadError");
        }

        File storage = new File(filePath);
        try {
            if (!storage.createNewFile()) {

                loadTasksFromFile(new File(filePath), manager);
            }
        } catch (IOException e) {
            throw new PlatoException("loadError");
        }

        return manager;
    }

    private void loadTasksFromFile(File file, TaskManager manager) throws IOException, PlatoException {

        assert filePath.contains(".txt") : "Loading invalid storage format";

        BufferedReader br = new BufferedReader(new FileReader(file));
        String next;

        while ((next = br.readLine()) != null) {
            if (!next.isBlank()) {
                Task item = determineTask(next);
                manager.addItem(item);
            }
        }

    }

    private Task determineTask(String task) throws PlatoException {
        String[] data = task.split("\\|");
        String type = data[0];
        String[] information = Arrays.copyOfRange(data, 1, data.length);
        Task item;

        switch (type) {
        case "D":
            item = createLoadedTask(Actions.DEADLINE, information);
            break;
        case "E":
            item = createLoadedTask(Actions.EVENT, information);
            break;
        case "T":
            item = createLoadedTask(Actions.TODO, information);
            break;
        default:
            throw new PlatoException("loadError");
        }

        return item;

    }

    private static Task createLoadedTask(Actions type, String... values) throws PlatoException {

        Task item;
        switch (type) {
        case DEADLINE:
            if (!values[3].equals("null")) {
                item = new Deadline(values[1], LocalDateTime.parse(values[3].trim()));
            } else {
                item = new Deadline(values[1], values[2]);
            }
            break;

        case EVENT:
            boolean emptyDateTime = values[4].equals("null") && values[5].equals("null");
            if (!emptyDateTime) {
                LocalDateTime fromDate = LocalDateTime.parse(values[5].trim());
                LocalDateTime byDate = LocalDateTime.parse(values[4].trim());
                item = new Event(values[1], fromDate, byDate);
            } else {
                item = new Event(values[1], values[2], values[3]);
            }
            break;

        case TODO:
            item = new Todo(values[1]);
            break;
        default:
            throw new PlatoException("loadError");
        }
        if (values[0].equals("x")) {
            item.markAsDone();
        }
        return item;
    }


    /**
     * Writes to the filepath of the items to save.
     *
     * @param manager TaskManager that contains all the items to save to file.
     */
    public void saveFile(TaskManager manager) {
        if (manager.getUpdate()) {
            try (FileWriter fw = new FileWriter(filePath)) {
                fw.write(manager.getTasksSave());
                manager.setUpdate(false);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }


}
