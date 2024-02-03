import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    private Ui ui;
    public TaskList() {
        this.tasks = new ArrayList<Task>();
        this.ui = new Ui();
    }

    public void addStoredTask(Task newTask) {
        this.tasks.add(newTask);
    }

    public void addNewTask(Task task) {
        this.tasks.add(task);
        ui.addTaskRespond(task, this.tasks.size());
    }
}
