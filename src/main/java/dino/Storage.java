package dino;

import dino.DinoException.DinoException;
import dino.tasks.TaskList;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * The `Storage` class is responsible for loading and saving a `TaskList` object to a specified file path.
 */
public class Storage {
    private final Path path;


    public Storage(String path) {
        this.path = Path.of(path);
    }

    /**
     * The function loads a TaskList object from a file, and if the file does not exist, it returns a new TaskList
     * object.
     * 
     * @return a TaskList with all the tasks form cache.
     */
    public TaskList load() throws DinoException {
        if (Files.notExists(path)) {
            System.out.println("No cache found.");
            return new TaskList();
        }

        try (FileInputStream fileInputStream = new FileInputStream(path.toString());
             ObjectInputStream objInputStream = new ObjectInputStream(fileInputStream)) {

            TaskList tasks = (TaskList) objInputStream.readObject();
            System.out.println("Tasks loaded from: " + path);
            return tasks;

        } catch (IOException | ClassNotFoundException e) {
            try {
                Files.deleteIfExists(path);
            } catch (IOException ignored) {
            }
            throw new DinoException("Unable to load tasks from the file: " + path);
        }
    }

    /**
     * The function saves a TaskList object to a file using Java's ObjectOutputStream.
     * 
     * @param tasks The `tasks` parameter is an instance of the `TaskList` class. It represents a collection of tasks
     * that need to be saved.
     */
    public void save(TaskList tasks) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(path.toString());
            ObjectOutputStream objOutputStream = new ObjectOutputStream(fileOutputStream);

            objOutputStream.writeObject(tasks);

            objOutputStream.close();
            fileOutputStream.close();
        } catch (IOException ignored) {
        }
    }
}