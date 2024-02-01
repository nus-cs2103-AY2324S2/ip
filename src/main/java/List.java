import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;

public class List {
    private ArrayList<Task> tasks;

    public List(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task, Storage storage) throws IOException {
        tasks.add(task);
        String task_s = tasks.size() == 1 ? " task " : " tasks ";
        System.out.println("__________________________________________________________\n"
                + "Got it. I've added this task:\n"
                + task.toString()
                + "\nNow you have " + tasks.size() + task_s + "in the list.\n"
                + "__________________________________________________________\n");
        storage.saveTasks(this);
    }

    public void displayTasks() {
        System.out.println("__________________________________________________________\n"
                + "Here are the tasks in your list:"
        );
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i).toString());
        }
        System.out.println("__________________________________________________________\n");
    }

    public void deleteTask(int taskNum, Storage storage) throws IOException {
        String task_s = tasks.size() - 1 == 1 ? " task " : " tasks ";
        System.out.println("__________________________________________________________\n"
                + "Noted. I've removed this task:\n"
                + tasks.get(taskNum).toString()
                + "\nNow you have " + (tasks.size() - 1) + task_s + "in the list.\n"
                + "__________________________________________________________\n");
        storage.saveTasks(this);
    }

    public boolean validTaskNum(int taskNum) {
        return taskNum > 0 && taskNum <= tasks.size();
    }

    public void markTask(int taskNum, Storage storage) throws IOException {
        tasks.get(taskNum).mark();
        storage.saveTasks(this);
    }

    public void unmarkTask(int taskNum, Storage storage) throws IOException {
        tasks.get(taskNum).unmark();
        storage.saveTasks(this);
    }

    public int getListSize() {
        return tasks.size();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            result.append(i + 1).append(".").append(tasks.get(i).toString());
            if (i != tasks.size() - 1) {
                result.append("\n");
            }
        }
        return String.valueOf(result);
    }
}
