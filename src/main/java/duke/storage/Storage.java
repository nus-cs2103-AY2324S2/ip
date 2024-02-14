package duke.storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.Arrays;

import duke.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskManager;
import duke.task.Todo;

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
     * @throws DukeException Unable to load file.
     */
    public TaskManager loadFile() throws DukeException {
        TaskManager manager = new TaskManager();
        File directory = new File("data");
        if (!directory.exists()) {
            boolean created = directory.mkdir();
            if (!created) {
                throw new DukeException("directoryError");
            }
        }
        File storage = new File(filePath);
        try {
            if (!storage.createNewFile()) {

                loadTasksFromFile(new File(filePath), manager);
            }
        } catch (IOException e) {
            throw new DukeException("loadError");
        }

        return manager;
    }

    private void loadTasksFromFile(File file, TaskManager manager) throws IOException, DukeException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        String next;

        while ((next = br.readLine()) != null) {
            if (!next.isBlank()) {
                Task item = determineTask(next);
                manager.addItem(item);
            }
        }

    }

    private Task determineTask(String task) throws DukeException {
        String[] data = task.split("\\|");
        String type = data[0];
        String[] information = Arrays.copyOfRange(data, 1, data.length);
        Task item;

        switch (type) {
        case "D":
            item = createLoadedTask(SaveType.DEADLINE, information);
            break;
        case "E":
            item = createLoadedTask(SaveType.EVENT, information);
            break;
        case "T":
            item = createLoadedTask(SaveType.TODO, information);
            break;
        default:
            throw new DukeException("loadError");
        }

        return item;

    }

    private static Task createLoadedTask(SaveType type, String... values) throws DukeException {

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
            throw new DukeException("loadError");
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
