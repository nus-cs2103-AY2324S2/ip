package tasks;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();

    public String addTask(Task t) {
        this.tasks.add(t);
        return String.format("    Ok! I have added your task:\n      %s\n    You have %d task(s) in the "
                + "list now.\n\n", t.toString(), this.tasks.size());
    }

    public void loadTask(Task t) {
        this.tasks.add(t);
    }

    public String updateTask(int i, Task t) {
        String oldTask = tasks.get(i).toString();
        tasks.set(i, t);
        return String.format("    %s\n    replaced by\n    %s", oldTask, t.toString());
    }


    public String removeTask(int i) {
        Task t = this.tasks.get(i);
        this.tasks.remove(i);
        return String.format("    Ok, I have removed your task:\n    %s\n    You have %d task(s) in the "
                + "list now.\n\n", t.toString(), this.tasks.size());
    }

    public String markTask(int i) {
        this.tasks.get(i).markTask();
        return String.format(
                "    Great job completing your task!\n      %s\n\n", this.tasks.get(i).toString());
    }

    public String unmarkTask(int i) {
        this.tasks.get(i).unmarkTask();
        return String.format(
                "    Don't forget to complete your task soon...\n      %s\n\n", this.tasks.get(i).toString());
    }
    public void writeToFile(File file) throws IOException {
        FileWriter fw = new FileWriter(file);
        for (Task task : this.tasks) {
            fw.write(task.saveFormat());
        }
        fw.close();
    }
    public TaskList filter(String keyword) {
        TaskList res = new TaskList();
        for (Task task : this.tasks) {
            if (task.taskName.toLowerCase().contains(keyword.toLowerCase())) {
                res.loadTask(task);
            }
        }
        return res;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.tasks.size(); i++) {
            sb.append(String.format("    %d.%s\n", i + 1, this.tasks.get(i).toString()));
        }
        return sb.toString();
    }
}
