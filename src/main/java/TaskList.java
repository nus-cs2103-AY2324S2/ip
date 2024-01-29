import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {

        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {

        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {

        return this.tasks;
    }
    public void addTasks(Task t) {

        tasks.add(t);
    }

    public void markTasks(int index, Ui ui) {

        tasks.get(index - 1).setStatus();
        ui.markedMessage(tasks.get(index - 1));
    }
    public void removeTasks(int in) {

        tasks.remove(in - 1);
    }

    public void listTasks(Ui ui) {
        if (tasks.isEmpty()) {
            ui.showMessage("There are no tasks in the list.");
        } else {
            ui.showMessage("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                ui.showMessage((i + 1) + ". " + tasks.get(i).toString());
            }
        }
    }
}