package duke.ui;

import duke.DukeException;
import duke.task.Task;
import duke.tasklist.TaskList;

import java.util.Scanner;

public class Ui {
    public void showWelcome() {
        System.out.println("Hello! I'm Fluffy, \nWhat can I do for you?");
        showLine();
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showError(String errorMessage) {
        System.out.println("OOPS!!! " + errorMessage);
    }

    public void showLoadingError() {
        System.out.println("Error loading file. Creating new file...");
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void showTaskList(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.getSize(); i++) {
            try {
                System.out.println((i + 1) + ". " + tasks.getTask(i));
            } catch (DukeException e) {
                showError(e.getMessage());
            }
        }
    }

    public void showTaskAdded(Task task, int newSize) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + newSize + " tasks in the list.");
    }

    public void showTaskDeleted(Task task, int newSize) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + newSize + " tasks in the list.");
    }

    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
