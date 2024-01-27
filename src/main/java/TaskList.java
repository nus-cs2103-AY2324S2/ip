import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> toDoList;

    public TaskList() {
        this.toDoList = new ArrayList<>();
    }

    public void printList(Ui ui) {
        String tasklistString = "";
        for (int i = 1; i <= toDoList.size(); i++) {
            tasklistString += i + "." + toDoList.get(i - 1) + "\n";
        }
        ui.botPrint(tasklistString);
    }

    public void add(Task t) {
        this.toDoList.add(t);
    }

    public void remove(Task t) {
        this.toDoList.remove(t);
    }

    public void remove(int i) {
        this.toDoList.remove(i - 1);
    }

    public Task get(int i) {
        return this.toDoList.get(i - 1);
    }

    public int size() {
        return this.toDoList.size();
    }

}
