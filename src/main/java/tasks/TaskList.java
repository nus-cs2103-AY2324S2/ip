package tasks;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Returns a String after a Task has been added successfully into the TaskList.
     *
     * @param t Task
     * @return String response
     */
    public String addTask(Task t) {
        this.tasks.add(t);
        return String.format("    Ok! I have added your task:\n      %s\n    You have %d task(s) in the "
                + "list now.\n\n", t.toString(), this.tasks.size());
    }

    /**
     * Adds a Task into the TaskList
     * @param t Task
     */
    public void loadTask(Task t) {
        this.tasks.add(t);
    }

    /**
     * Returns a String after a Task in the TaskList has been updated successfully.
     *
     * @param i index of Task in TaskList
     * @param t Task
     * @return String response
     */
    public String updateTask(int i, Task t) {
        String oldTask = tasks.get(i).toString();
        tasks.set(i, t);
        return String.format("    %s\n    replaced by\n    %s", oldTask, t.toString());
    }

    /**
     * Returns a String after a Task in the TaskList has been removed successfully.
     *
     * @param i index of Task in TaskList
     * @return String response
     */
    public String removeTask(int i) {
        Task t = this.tasks.get(i);
        this.tasks.remove(i);
        return String.format("    Ok, I have removed your task:\n    %s\n    You have %d task(s) in the "
                + "list now.\n\n", t.toString(), this.tasks.size());
    }

    /**
     * Returns a String after a Task in the TaskList has been marked as done successfully.
     *
     * @param i index of Task in TaskList
     * @return String response
     */
    public String markTask(int i) {
        this.tasks.get(i).markTask();
        return String.format(
                "    Great job completing your task!\n      %s\n\n", this.tasks.get(i).toString());
    }

    /**
     * Returns a String after a Task in the TaskList has been marked as not done successfully.
     *
     * @param i index of Task in TaskList
     * @return String response
     */
    public String unmarkTask(int i) {
        this.tasks.get(i).unmarkTask();
        return String.format(
                "    Don't forget to complete your task soon...\n      %s\n\n", this.tasks.get(i).toString());
    }

    /**
     * Saves the tasks in the TaskList to a file.
     *
     * @param file File to save tasks.
     * @throws IOException
     */
    public void writeToFile(File file) throws IOException {
        FileWriter fw = new FileWriter(file);
        for (Task task : this.tasks) {
            fw.write(task.saveFormat());
        }
        fw.close();
    }

    /**
     * Returns a new TaskList which contain only Tasks with a keyword.
     * @param keyword
     * @return TaskList
     */
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
