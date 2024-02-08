package luke.ui;

import luke.task.Task;
import luke.task.TaskList;

import java.util.Scanner;

public class Ui {
    Scanner sc;
    public Ui() {
        sc = new Scanner(System.in);

    }
    public void welcome() {
        System.out.println("________________________________________________________________________");
        System.out.println("Hello! I'm Luke.Luke");
        System.out.println("What can I do for you?");
        System.out.println("________________________________________________________________________");

    }

    public void goodbye() {
        System.out.println("________________________________________________________________________");
        System.out.println("Bye! Hope to see you again soon!");
        System.out.println("________________________________________________________________________");
    }

    public void getErrorMessage(String message) {
        System.out.println("________________________________________________________________________");
        System.out.println(message);
        System.out.println("________________________________________________________________________");
    }

    public void printList(TaskList tasks) {
        System.out.println("________________________________________________________________________");
        System.out.println("Here are the tasks in your list:");

        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i) != null) {
                System.out.println(i + 1 + "." + tasks.get(i));

            }
        }
        System.out.println("________________________________________________________________________");
    }

    public void printTaskMarked(Task task) {
        String status = "";
        if (task.getIsDone()) {
            status = "done:";
        } else {
            status = "not done yet:";
        }
        System.out.println("________________________________________________________________________");
        System.out.println("Nice! I've marked this task as " + status);
        System.out.println(task);
        System.out.println("________________________________________________________________________");
    }

    public void printTaskAdded(Task task, int size) {
        String taskStringType = "";
        if (size > 1) {
            taskStringType = "tasks";
        } else {
            taskStringType = "task";
        }

        System.out.println("________________________________________________________________________");
        System.out.println("Got it! I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + size + " " + taskStringType + " in the list.");
        System.out.println("________________________________________________________________________");
    }

    public void printTaskDeleted(Task task, int size) {
        String taskStringType = "";
        if (size > 1) {
            taskStringType = "tasks";
        } else {
            taskStringType = "task";
        }

        System.out.println("________________________________________________________________________");
        System.out.println("Noted! I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + size + " " + taskStringType + " in the list.");
        System.out.println("________________________________________________________________________");
    }

    public String readCommand() {
        return sc.nextLine().trim();

    }

    public void printTaskFound(TaskList tasks) {
        System.out.println("________________________________________________________________________");
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i) != null) {
                System.out.println(i + 1 + "." + tasks.get(i));
            }
        }
        System.out.println("________________________________________________________________________");
    }
}
