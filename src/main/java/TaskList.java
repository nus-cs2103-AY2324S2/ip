import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> listOfTasks;

    TaskList() {
        this.listOfTasks = new ArrayList<>();
    }

    TaskList(ArrayList<Task> taskList) {
        this.listOfTasks = taskList;
    }

    public void addTask(Task newTask) {
        listOfTasks.add(newTask);
    }

    public Task getTask(int index) {
        return listOfTasks.get(index);
    }

    public int getSize() {
        return listOfTasks.size();
    }

    public void removeTask(int index) {
        listOfTasks.remove(index);
    }

    public ArrayList<Task> getList() {
        return listOfTasks;
    }
}
