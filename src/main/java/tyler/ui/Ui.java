package tyler.ui;

import tyler.task.Task;
import tyler.task.TaskList;

import java.util.Scanner;
import java.util.ArrayList;

public class Ui {
    Scanner sc = new Scanner(System.in);
    public void showWelcome() {
        System.out.println("    Hello from Tyler");
        System.out.println("    What can I do for you?");
        System.out.println("    list, todo, deadline, event, mark, unmark, bye");
        System.out.println("    --------------------------------------------------");
    }

    public void showBye() {
        System.out.println("    Bye. Hope to see you again");
    }
    public void showLoadingError() {
        System.out.println("    Can't load file / file does not exist. I'll create a new one for you.");
    }

    public void showTaskAdded(Task task, int num) {
        System.out.println("    Got it! I've added this task:");
        System.out.println("      " + task);
        System.out.println("    Now you have " + num + " tasks in the list");
    }

    public void showTaskDeleted(Task task, int num) {
        System.out.println("    Noted! I've deleted this task:");
        System.out.println("      " + task);
        System.out.println("    Now you have " + num + " tasks in the list");
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void list(TaskList tasks) {
        ArrayList<Task> taskList = tasks.getList();
        for (int i = 1; i < taskList.size() + 1; i++) {
            if (taskList.get(i - 1) == null) {
                break;
            }
            String task = taskList.get(i - 1).toString();
            System.out.println("    " + i + ". " + task);
        }
    }

    public void showError(String message) {
        System.out.println(message);
    }
    public void showLine() {
        System.out.println("    --------------------------------------------------");
    }
}
