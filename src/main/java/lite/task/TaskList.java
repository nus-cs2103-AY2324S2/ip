package lite.task;

import lite.Storage;
import lite.util.Printer;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public int size() {
        return tasks.size();
    }

    public Task get(int i) {
        return tasks.get(i);
    }

    public void add (Task task) {
        this.tasks.add(task);
    }

    public Task remove(int i) {
        return this.tasks.remove(i);
    }

    public void outputTasks() {
        Printer.printHorizontalLine();
        System.out.println("Here are the tasks in your taskList:");
        for (int i = 0; i < this.tasks.size(); i++) {
            System.out.println((i+1) + ". " + this.tasks.get(i));
        }
        Printer.printHorizontalLine();
    }

    public void saveFile() {
        Storage.save(this.tasks);
    }

}
