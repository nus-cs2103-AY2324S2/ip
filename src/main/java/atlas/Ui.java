package atlas;

import atlas.task.Task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showGreeting() {
        System.out.println("Hello! I'm Atlas");
        System.out.println("What can I do for you?");
    }

    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showTaskAdded(TaskList tasks) {
        ArrayList<Task> al = tasks.getTasks();
        System.out.println("Got it. I've added this task:\n  " + al.get(al.size() - 1));
        System.out.println("Now you have " + al.size() + " tasks in the list.");
    }

    public void showTaskDeleted(TaskList tasks, int index) {
        ArrayList<Task> al = tasks.getTasks();
        System.out.println("Got it. I've removed this task:\n  " + al.get(index));
        System.out.println("Now you have " + (al.size() - 1) + " tasks in the list.");
    }

    public void showTasksOnDate(TaskList tasks, LocalDate date) {
        ArrayList<Task> al = tasks.getTasksOnDate(date);
        if (al.isEmpty()) {
            System.out.println("No tasks found on " + date);
        } else {
            System.out.println("Tasks on " + date + ":");
            for (Task task : al) {
                System.out.println(task);
            }
        }
    }

    public void showMark(TaskList tasks, int index) {
        ArrayList<Task> al = tasks.getTasks();
        System.out.println("Nice! I've marked this task as done:");
        String str = al.get(index).toString();
        System.out.println(str);
    }

    public void showunMark(TaskList tasks, int index) {
        ArrayList<Task> al = tasks.getTasks();
        System.out.println("OK, I've marked this task as not done yet");
        String str = al.get(index).toString();
        System.out.println(str);
    }

    public void showAllTasks(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("There are no tasks in your list.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + "." + tasks.get(i));
            }
        }
    }

    public void showMatchingTasks(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("No matching tasks found");
        }
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
    }
}
