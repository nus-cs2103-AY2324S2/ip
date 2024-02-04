package badgpt.util;

import badgpt.tasks.Task;

public class TasksUi extends Ui {
    public void printAddTask(Task task, int numTasks) {
        printLine();
        System.out.println("Added task: " + task);
        System.out.println("Now you have " + numTasks + " task(s) in the list.");
        printLine();
    }

    public void printTask(Task task, int index, int total) {
        if (index == 1) {
            printLine();
            System.out.println("Here are the tasks in your list:");
        }

        System.out.println(index + ". " + task);

        if (index == total) {
            printLine();
        }
    }

    public void printMarkUnmarkOutcome(Task task, int type) {
        printLine();
        System.out.println((type == 0 ? "Nice! I've marked this task as done:\n" : "wyd bro why undo\n") + task);
        printLine();
    }

    public void printDelete(Task task, int numTasks) {
        printLine();
        System.out.println("This task has been removed: " + task);
        System.out.println("Now you have " + numTasks + " task(s) in the list.");
        System.out.println("No, what are you waiting for? Do it! Just do it!");
        printLine();
    }
}
