package wis.task;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

import wis.Storage;
import wis.util.Printer;

public class TaskList {
    private ArrayList<Task> list;
    private int taskCount;

    public TaskList() {
        this.list = new ArrayList<>();
        this.taskCount = 0;
    }

    public TaskList(ArrayList<Task> list) {
        this.list = list;
        this.taskCount = list.size();
    }

    public void add(Task task) {
        this.list.add(task);
        this.taskCount++;
        Storage.saveTasks(this);
    }

    public Task get(int i) {
        return this.list.get(i);
    }

    public Task remove(int i) {
        this.taskCount--;
        Storage.saveTasks(this);
        return this.list.remove(i);
    }

    public void print() {
        for (int i = 0; i < taskCount; i++) {
            Printer.println((i + 1) + "." + list.get(i).toString());
        }
    }

    public void printTaskCount() {
        Printer.println("Now you have " + taskCount + " tasks in the list.");
    }

    public void save(BufferedWriter bufferedWriter) throws IOException {
        for (Task task : list) {
            bufferedWriter.write(task.toSavedString());
        }
    }
}
