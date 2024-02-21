package jav.manager;

import java.util.ArrayList;

import jav.exception.InvalidParamException;
import jav.task.Deadline;
import jav.task.Event;
import jav.task.Task;
import jav.task.ToDo;

/**
* StorageManager manages and stores tasks into a storage.
*/
public class StorageManager {
    /** The currently stored tasks. */
    private ArrayList<Task> tasks;

    // Singleton pattern but lazy loaded from wiki https://en.wikipedia.org/wiki/Initialization-on-demand_holder_idiom
    // Wanted a singleton pattern and this seemed the best.
    private StorageManager() {}
    private static class LazyHolder {
        static final StorageManager INSTANCE = new StorageManager();
    }
    public static StorageManager getInstance() {
        return LazyHolder.INSTANCE;
    }

    /** All types of tasks that is handled by Storage. */
    public enum StorageType {
        TODO,
        DEADLINE,
        EVENT,
        TASK
    }

    /**
     * Returns the currently stored tasks as a string.
     *
     * @return a string representation of the currently stored tasks.
     */
    public String printStoredTasks() {
        if (tasks == null) {
            tasks = new ArrayList<>();
            return "";
        } else {
            return printTasks(tasks);
        }
    }

    /**
     * Returns the given tasks as a string.
     *
     * @param tasks the given tasks.
     * @return a string representation of the given tasks.
     */
    public String printTasks(ArrayList<Task> tasks) {
        int i = 1;
        String s = "";
        for (Task t : tasks) {
            String taskString = String.format("%d. %s\n", i, t.toString());
            s += taskString;
            i++;
        }

        return s;
    }

    /**
     * Stores an unmarked task in the back of the storage.
     *
     * @param param the information about the task.
     * @param type the type of task.
     * @throws InvalidParamException if the task has issues being stored.
     */
    public void store(String param, StorageType type) throws InvalidParamException {
        store(param, type, false);
    }


    /**
     * Stores a task in the storage at a specified index.
     *
     * @param param the information about the task.
     * @param type the type of task.
     * @param shouldMark whether the task should be marked.
     * @param index the index to store the task in.
     * @throws InvalidParamException if the task has issues being stored.
     */
    public void store(String param, StorageType type, boolean shouldMark, int index) throws InvalidParamException {
        if (tasks == null) {
            tasks = new ArrayList<>();
        }

        Task task;
        try {
            switch (type) {
            case TODO:
                task = new ToDo(param, shouldMark);
                break;
            case DEADLINE:
                task = new Deadline(param, shouldMark);
                break;
            case EVENT:
                task = new Event(param, shouldMark);
                break;
            default:
                assert false : "Unexpected task type " + type;
                task = new Task(param, shouldMark);
                break;
            }
            tasks.add(index, task);
        } catch (InvalidParamException e) {
            throw e;
        }
    }

    /**
     * Stores a task in the back of the storage.
     *
     * @param param the information about the task.
     * @param type the type of task.
     * @param shouldMark whether the task should be marked.
     */
    public void store(String param, StorageType type, boolean shouldMark) throws InvalidParamException {
        if (tasks == null) {
            tasks = new ArrayList<>();
        }

        Task task;
        try {
            switch (type) {
            case TODO:
                task = new ToDo(param, shouldMark);
                break;
            case DEADLINE:
                task = new Deadline(param, shouldMark);
                break;
            case EVENT:
                task = new Event(param, shouldMark);
                break;
            default:
                assert false : "Unexpected task type " + type;
                task = new Task(param, shouldMark);
                break;
            }
            tasks.add(task);
        } catch (InvalidParamException e) {
            throw e;
        }
    }

    /**
     * Updates whether a specific task is marked or not.
     *
     * @param index the index of the task.
     * @param shouldMark whether the task should be marked.
     * @return whether the specified task exists or not.
     */
    public boolean updateTask(int index, boolean shouldMark) {
        if (tasks == null) {
            return false;
        }

        if (tasks.size() < index + 1) {
            return false;
        }

        if (tasks.get(index).isMarked() == shouldMark) {
            return false;
        }

        tasks.get(index).updateMark(shouldMark);
        return true;
    }

    /**
     * Deletes a specific task.
     *
     * @param index the index of the task.
     *
     * @return whether the specified task exists or not.
     */
    public boolean deleteTask(int index) {
        if (tasks == null) {
            return false;
        }

        if (tasks.size() >= index + 1) {
            tasks.remove(index);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Deletes the latest added task.
     *
     * @return whether the specified task exists or not.
     */
    public boolean deleteLatestTask() {
        if (tasks == null) {
            return false;
        }

        if (tasks.size() >= 1) {
            tasks.remove(tasks.size() - 1);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Gets the task at the specified index.
     *
     * @return the desired task.
     */
    public Task getTask(int index) {
        if (tasks == null) {
            return null;
        }

        if (tasks.size() >= index + 1) {
            return tasks.get(index);
        } else {
            return null;
        }
    }

    /**
     * Returns the data of the entire storage in a file ready format.
     *
     * @return a string containing the data of the entire storage in a file ready format.
     */
    public String getFileFormat() {
        String result = "";

        for (Task task : tasks) {
            result += "type=" + task.getType()
                   + ",marked=" + (task.isMarked() ? "T" : "F")
                   + ",param=" + task.getFileFormatParam() + "\n";
        }

        return result;
    }

    /**
     * Loads the saved data into the storage.
     * 
     * @param savedData the data to load into the storage.
     */
    public void load(String savedData) {
        if (savedData.equals("")) {
            return;
        }

        // Tokenize the data and store it into the storage
        String[] strings = savedData.split("\n");
        for (String str : strings) {
            String[] tokens = str.split(",marked=");
            String type = tokens[0].substring(5);
            tokens = tokens[1].split(",param=");
            String isMarked = tokens[0];
            String param = (tokens.length == 1) ? "" : tokens[1];
            store(param, stringToStorageType(type), (isMarked.equals("T") ? true : false));
        }
    }

    /**
     * A helper function to convert a string to a storage type.
     *
     * @param typeStr string to convert.
     * @return the converted storage type.
     */
    private StorageType stringToStorageType(String typeStr) {
        if (typeStr.equals(StorageType.EVENT.toString())) {
            return StorageType.EVENT;
        } else if (typeStr.equals(StorageType.DEADLINE.toString())) {
            return StorageType.DEADLINE;
        } else if (typeStr.equals(StorageType.TODO.toString())) {
            return StorageType.TODO;
        } else {
            assert false : "Unexpected task type " + typeStr;
            return StorageType.TASK;
        }
    }

    /**
     * Finds tasks that match the given keyword.
     *
     * @param param the keyword to search for.
     * @return a string representation of the found tasks.
     */
    public String findTask(String param) {
        ArrayList<Task> foundTasks = new ArrayList<Task>();
        for (Task task : tasks) {
            if (task.getDescription().contains(param)) {
                foundTasks.add(task);
            }
        }

        return printTasks(foundTasks);
    }
}
