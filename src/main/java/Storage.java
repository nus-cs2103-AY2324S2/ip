import java.util.ArrayList;

/* Handles storages */
public class Storage {
    public enum StorageType {
        TODO,
        DEADLINE,
        EVENT
    }

    // ArrayList of all stored tasks
    private ArrayList<Task> tasks;

    // Return the tasks stored as an ArrayList
    public ArrayList<Task> Get() {
        if (tasks == null) tasks = new ArrayList<>();
        return tasks;
    }

    // Stores the string as a task
    public void Store(String _string, StorageType _type) {
        if (tasks == null) tasks = new ArrayList<>();

        Task task;
        try {
            switch (_type) {
                case TODO:
                    task = new ToDo(_string, false);   
                    break;
                case DEADLINE:
                    task = new Deadline(_string, false);
                    break;
                case EVENT:
                    task = new Event(_string, false);
                    break;
                default:
                    task = new Task(_string, false);   
                    break;
            }
            tasks.add(task);
        } catch (Exception e) {
            throw e;
        }
    }

    // Change the mark of a specific task
    // Returns true if the task exists and false otherwise
    public boolean UpdateTask(int _i, boolean _flag) {
        if (tasks == null) return false;

        if (tasks.size() >= _i + 1) {
            tasks.get(_i).UpdateMark(_flag);
            return true;
        } else {
            return false;
        }
    }

    // Deletes a specific task
    // Returns true if the task exists and false otherwise
    public boolean DeleteTask(int _i) {
        if (tasks == null) return false;

        if (tasks.size() >= _i + 1) {
            tasks.remove(_i);
            return true;
        } else {
            return false;
        }
    }
}
