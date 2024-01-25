import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private static final int MAX_TASKS = 100;
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public static void printHorizontalLine() {
        System.out.println("____________________________________________________________");
    }

    public void addTask(String task) {
        if (this.tasks.size() < MAX_TASKS) {
            this.tasks.add(new Task(task));
            printHorizontalLine();
            System.out.println("Added: " + task);
            printHorizontalLine();
        } else {
            System.out.println("Oops! The task list is full.");
        }
    }

    public void markTask(int index) {
        if (index <= 0 || index > this.tasks.size()) {
            System.out.println("Oops! Task index is out of range.");
            return;
        }
        Task currTask = this.tasks.get(index - 1);
        currTask.markAsDone();
        printHorizontalLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("\t" + currTask.toString());
        printHorizontalLine();
    }

    public void unmarkTask(int index) {
        if (index <= 0 || index > this.tasks.size()) {
            System.out.println("Oops! Invalid task number.");
            return;
        }
        Task currTask = this.tasks.get(index - 1);
        currTask.markAsNotDone();
        printHorizontalLine();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("\t" + currTask.toString());
        printHorizontalLine();
    }

    public void listTasks() {
        if (this.tasks.size() == 0) {
            System.out.println("Oops! The task list is currently empty.");
        } else {
            printHorizontalLine();
            System.out.println("Here are the tasks in your list: ");
            for (int i = 0; i < this.tasks.size(); i++) {
                Task currTask = this.tasks.get(i);
                System.out.println((i + 1) + "." + currTask.toString());
            }
        }
        printHorizontalLine();
    }
}