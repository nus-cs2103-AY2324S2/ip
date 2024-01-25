import java.util.ArrayList;

/* Handles storages */
public class Storage {
    // ArrayList of all stored tasks
    private ArrayList<Task> tasks;

    // Return the tasks stored as an ArrayList
    public ArrayList<Task> Get() {
        if (tasks == null) tasks = new ArrayList<>();
        return tasks;
    }

    // Stores the string as a task
    public void Store(String _string) {
        if (tasks == null) tasks = new ArrayList<>();
        tasks.add(new Task(_string, false));
    }

    // Change the mark of a specific task
    // Returns true if the task exists and false otherwise
    public boolean UpdateTask(int _i, boolean _flag) {
        if (tasks.size() >= _i + 1) {
            tasks.get(_i).UpdateMark(_flag);
            return true;
        } else {
            return false;
        }
    }
}
