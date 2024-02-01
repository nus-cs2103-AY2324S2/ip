import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private ArrayList<Task> tasks;
    private Storage storage;

    public int i = 0;

    //constructor
    public TaskList(String filepath) {
        tasks = new ArrayList<>();
        storage = new Storage(filepath);
        tasks.addAll(storage.loadFromFile());
    }

    public void addTask(Task task){
        tasks.add(task);
        storage.saveToFile(i, tasks); //index?
        i++;
    }

    public void deleteTask(int index){
        if(i > 0 && i < tasks.size()) {
            tasks.remove(index);
            storage.saveToFile(i, tasks);
            i--;
        }
    }
    public List<Task> getTasks() {
        return tasks;
    }

    public Task getTask(int index){
        if (index >= 0 && index < tasks.size()) {
            return tasks.get(index);
        } else {
            return null;
        }
    }

    public void setTask(int index, Task elem){
        tasks.set(index, elem);
    }


}
