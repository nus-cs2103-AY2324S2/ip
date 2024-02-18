package nihao.handler;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import nihao.action.task.Task;
import nihao.exception.IndexOutOfBoundsException;
import nihao.util.TaskTypeAdapter;



/**
 * Manages all actions related to accessing runtime data and local data storage.
 */
public class DataHandler {
    protected static ArrayList<Task> tasks = new ArrayList<>();
    private static final String DIRECTORY_PATH = "data/";
    private static final String FILE_PATH = "data/PersistentData.json";
    private DataHandler() {};

    private static void readFromJson() throws IOException {
        FileReader fileReader = new FileReader(FILE_PATH);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeHierarchyAdapter(Task.class, new TaskTypeAdapter());
        Gson gson = gsonBuilder.create();
        // Alternative: Type objectType = new TypeToken<ArrayList<Task>>() {}.getType();
        Type objectType = TypeToken.getParameterized(ArrayList.class, Task.class).getType();
        tasks = gson.fromJson(fileReader, objectType);
        fileReader.close();
    }

    private static void saveToJson() throws IOException {
        FileWriter fileWriter = new FileWriter(FILE_PATH);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeHierarchyAdapter(Task.class, new TaskTypeAdapter());
        Gson gson = gsonBuilder.create();
        gson.toJson(tasks, fileWriter);
        fileWriter.flush();
        fileWriter.close();
    }

    /**
     * Reads from the local storage file specified by FILE_PATH and invokes
     * readFromJson() to load the stored data into the tasks ArrayList.
     * Creates the storage file and/or the directory if not found.
     */
    public static void read() {
        try {
            readFromJson();
        } catch (IOException e) {
            Path newFilePath = Paths.get(FILE_PATH);
            try {
                Files.createFile(newFilePath);
            } catch (IOException ex) {
                try {
                    Files.createDirectory(Paths.get(DIRECTORY_PATH));
                } catch (IOException exc) {
                    PrintHandler.printException(exc);
                }
            }
        }
    }

    /**
     * Saves the content of tasks to the local storage file.
     */
    public static void save() {
        try {
            saveToJson();
        } catch (IOException e) {
            PrintHandler.printException(e);
        }
    }

    /**
     * Adds a new Task to tasks.
     *
     * @param task Task to be added to tasks.
     */
    public static void addTask(Task task) {
        tasks.add(task);
        // Todo: Exception handling
    }

    /**
     * Mark the Task at the given index as completed.
     *
     * @param index Index of the task to be marked as completed.
     * @throws IndexOutOfBoundsException When the index provided is more than the length of tasks.
     */
    public static void markTask(int index) throws IndexOutOfBoundsException {
        if (index > size() || index <= 0) {
            throw new IndexOutOfBoundsException(index, size());
        }
        tasks.get(index - 1).mark();
    }

    /**
     * Mark the Task at the given index as uncompleted.
     *
     * @param index Index of the task to be marked as uncompleted.
     * @throws IndexOutOfBoundsException When the index provided is more than the length of tasks.
     */
    public static void unmarkTask(int index) throws IndexOutOfBoundsException {
        if (index > size() || index <= 0) {
            throw new IndexOutOfBoundsException(index, size());
        }
        tasks.get(index - 1).unmark();
    }

    /**
     * Delete the Task at the given index.
     *
     * @param index Index of the task to be deleted.
     * @throws IndexOutOfBoundsException When the index provided is more than the length of tasks.
     */
    public static void deleteTask(int index) throws IndexOutOfBoundsException {
        if (index > size() || index <= 0) {
            throw new IndexOutOfBoundsException(index, size());
        }
        tasks.remove(index - 1);
    }

    /**
     * Delete all Tasks in tasks.
     */
    public static void deleteAll() {
        tasks = new ArrayList<>();
    }

    /**
     * Returns the number of elements of tasks.
     *
     * @return Number of elements of tasks.
     */
    public static int size() {
        return tasks.size();
    }

    /**
     * Returns tasks.
     *
     * @return The tasks ArrayList.
     */
    public static ArrayList<Task> getData() {
        return tasks;
    }

    /**
     * Returns the Task object at the given index.
     *
     * @param index Index of the Task object requested.
     * @return The Task object.
     * @throws IndexOutOfBoundsException When the index provided is more than the length of tasks.
     */
    public static Task getTask(int index) throws IndexOutOfBoundsException {
        if (index > size() || index <= 0) {
            throw new IndexOutOfBoundsException(index, size());
        }
        return tasks.get(index - 1);
    }

    /**
     * Returns an ArrayList of Tasks that contain the given keyword in their task names.
     */
    public static ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> returnList = new ArrayList<>();
        tasks.stream().forEach(e -> {
            if (e.contains(keyword)) {
                returnList.add(e);
            }
        });
        return returnList;
    }
}
