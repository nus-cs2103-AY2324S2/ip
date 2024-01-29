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
        Store(_string, _type, false);
    }

    // Stores the string as a task 
    public void Store(String _string, StorageType _type, boolean _isMarked) {
        if (tasks == null) tasks = new ArrayList<>();

        Task task;
        try {
            switch (_type) {
                case TODO:
                    task = new ToDo(_string, _isMarked);   
                    break;
                case DEADLINE:
                    task = new Deadline(_string, _isMarked);
                    break;
                case EVENT:
                    task = new Event(_string, _isMarked);
                    break;
                default:
                    task = new Task(_string, _isMarked);   
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

    // Returns the data of the entire storage to be stored into a file
    public String GetFileFormat() {
        String result = "";

        for (Task task : tasks) {
            result += "type=" + task.Type() + 
                    ",marked=" + (task.IsMarked() ? "T" : "F") +
                    ",param=" + task.GetFileFormatParam() +"\n";
        }

        return result;
    }

    // Loads the saved data into the storage
    public void Load(String _savedData) {
        if (!_savedData.equals("")) {
            String strings[] = _savedData.split("\n");

            for (String str : strings) {
                String tokens[] = str.split(",marked=");
                String type = tokens[0].substring(5);
                tokens = tokens[1].split(",param=");
                String isMarked = tokens[0];
                String param = tokens[1];

                Store(param, StringToStorageType(type), (isMarked.equals("T") ? true : false));
            }
        }
    }

    // Helper function for easier conversion
    public StorageType StringToStorageType(String _type) {
        if (_type.equals("Event")) {
            return StorageType.EVENT;
        } else if (_type.equals("Deadline")) {
            return StorageType.DEADLINE;
        } else {
            return StorageType.TODO;
        }
    }
}
