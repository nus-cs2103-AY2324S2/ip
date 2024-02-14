package duke.storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

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
            directory.mkdir();
        } else {
            assert filePath.contains(".txt") : "Loading invalid storage format";
            File storage = new File(filePath);
            try {
                if (!storage.createNewFile()) {

                    loadTasksFromFile(new File(filePath), manager);
                }
            } catch (IOException e) {
                throw new DukeException("Stupid thing won't load");
            }
        }
        return manager;
    }

    private void loadTasksFromFile(File file, TaskManager manager) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String next;
            while ((next = br.readLine()) != null) {
                if (!next.isBlank()) {
                    //Read task file
                    Task item = determineTask(next);
                    manager.addItem(item);
                }

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private Task determineTask(String task) {
        String[] data = task.split("\\|");
        String type = data[0];
        Task item;
        String name;
        String by;
        String from;
        switch (type) {
        case "D":
            name = data[2];
            by = data[3];
            String temp = data[4];
            if (!temp.equals("null")) {
                item = new Deadline(name, LocalDateTime.parse(temp.trim()));
            } else {
                item = new Deadline(name, by);
            }
            break;
        case "E":
            name = data[2];
            by = data[3];
            from = data[4];
            String tempBy = data[5];
            String tempFrom = data[6];
            if (!(tempBy.equals("null") || tempFrom.equals("null"))) {
                item = new Event(name, LocalDateTime.parse(tempFrom.trim()), LocalDateTime.parse(tempBy.trim()));
            } else {
                item = new Event(name, by, from);
            }

            break;
        case "T":
            name = data[2];
            item = new Todo(name);
            break;
        default:
            item = new Task(data[2]);
            break;
        }
        String isDone = data[1];
        if (isDone.equals("x")) {
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
