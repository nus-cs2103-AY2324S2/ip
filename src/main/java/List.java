import java.util.*;
public class List {
    private ArrayList<Task> tasks;

    public List(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void displayTasks() {
        System.out.println("__________________________________________________________\n" +
                "Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ".[" + tasks.get(i).getStatusIcon()
                    + "] " + tasks.get(i).toString());
        }
        System.out.println("__________________________________________________________\n");
    }

    public boolean validTaskNum(int taskNum) {
        return taskNum > 0 && taskNum <= tasks.size();
    }

    public void markTask(int taskNum) {
        this.tasks.get(taskNum).mark();
    }

    public void unmarkTask(int taskNum) {
        this.tasks.get(taskNum).unmark();
    }
}
