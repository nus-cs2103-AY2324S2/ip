package SlayBot;

import entity.Task;

import java.util.ArrayList;
import java.util.List;
public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public List<Task> getTasks() {
        return this.tasks;
    }

    public int getSize() {
        return this.tasks.size();
    }

    public void removeTask(int index) {
        this.tasks.remove(index);
    }

    public void addTask(Task t) {
        this.tasks.add(t);
    }

    public void iterate() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i + 1 + ". " + tasks.get(i));
        }
    }

    public void markTask(int index) {
        Task taskToMark = tasks.get(index);
        taskToMark.setMarked(true);
        System.out.println("____________________________________________________________"
                + "\nNice! I've marked this task as done:\n" + taskToMark.toString()
                + "\n" + "____________________________________________________________");
    }

    public void unmarkTask(int index) {
        Task taskToUnmark = tasks.get(index);
        taskToUnmark.setMarked(false);
        System.out.println("____________________________________________________________"
                + "\nOK, I've marked this task as not done yet:\n" + taskToUnmark.toString()
                + "\n" + "____________________________________________________________");
    }

}
