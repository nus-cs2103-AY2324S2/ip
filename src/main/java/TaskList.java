import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;


    TaskList() {
        this.taskList = new ArrayList<Task>();
    }
    TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    int size() {
        return this.taskList.size();
    }

    Task get(int index) {
        return this.taskList.get(index);
    }

    void addTask(Task task) {
        taskList.add(task);
    }

    void deleteTask(int index) {
        taskList.remove(index);
    }

    void markTask(int index) {
        this.taskList.get(index).markAsDone();
    }

    void unMarkTask(int index) {
        this.taskList.get(index).markBackNotDone();
    }
}
