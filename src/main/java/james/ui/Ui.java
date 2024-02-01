package james.ui;

import james.exception.DukeException;
import james.tasks.Task;
import james.tasklist.TaskList;

import java.util.List;
import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showWelcome() {
        System.out.println("Hello! I'm james.James!\nWhat can I do for you?\n");
    }

    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!\n");
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public void showTask(String message, Task task, int size) {
        System.out.println(message + "\n" + task);
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    public void showTaskList(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.getSize(); i++) {
            try {
                System.out.println((i + 1) + ". " + tasks.getTask(i));
            } catch (DukeException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    public void showTasksFound(List<String> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("No tasks match your search keyword.");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }


    public void showDeletedTask(Task task, int remainingTasks) {
        System.out.println("Noted. I've removed this task: " + task);
        System.out.println("Now you have " + remainingTasks + " tasks in the list.");
    }
    
    public void showMarkedTask(Task task) {
        System.out.println("Nice! I've marked this task as done:\n" + task);
    }

    public void showUnmarkedTask(Task task) {
        System.out.println("OK, I've marked this task as not done yet:\n" + task);
    }
}
