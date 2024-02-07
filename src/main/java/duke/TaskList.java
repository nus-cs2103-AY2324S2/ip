package duke;

import java.io.IOException;
import java.util.ArrayList;
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void loadTasks(ArrayList<Task> taskList) {
        this.tasks = taskList;
    }

    public void addTask(Task newTask, Storage storage) {
        tasks.add(newTask);
        try {
            storage.saveTasks(tasks);
        } catch(IOException e) {
            System.out.print(e);
        }
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + newTask);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    public void deleteTask(int taskNumber, Storage storage) throws ChatbotException {
        if (taskNumber <= 0 || taskNumber > tasks.size()){
            throw new ChatbotException("Unknown task number. Please try again");
        }
        Task removedTask = tasks.remove(taskNumber - 1);
        try {
            storage.saveTasks(tasks);
        } catch(IOException e) {
            System.out.print(e);
        }
        System.out.println("Noted. I've removed this task: ");
        System.out.println("  " + removedTask.toString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    public void markTask(int taskNumber, boolean isDone, Storage storage) throws ChatbotException {
        if (taskNumber <= 0 || taskNumber > tasks.size()){
            throw new ChatbotException("Unknown task number. Please try again");
        }
        if (isDone) {
            tasks.get(taskNumber-1).markAsDone();
            try {
                storage.saveTasks(tasks);
            } catch(IOException e) {
                System.out.print(e);
            }
            System.out.println("Nice! I've marked this task as done: ");
        }
        else {
            tasks.get(taskNumber-1).unmarkAsDone();
            try {
                storage.saveTasks(tasks);
            } catch(IOException e) {
                System.out.print(e);
            }
            System.out.println("OK, I've marked this task as not done yet:");
        }
        System.out.println("  " + tasks.get(taskNumber-1).toString());
    }

    public void printTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).toString());
        }
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
