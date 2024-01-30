package Utilities;

import Task.TaskList;
import Task.Task;

import java.util.Scanner;

public class Ui {
    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public void showWelcome() {
        System.out.println("Hello! I'm TodoPal!");
        System.out.println("What can I do for you?");
    }

    public void showList(TaskList taskList) {
        if (taskList.isEmpty()) {
            System.out.println("Your task list is empty.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskList.length(); i++) {
                System.out.println((i + 1) + "." + taskList.getTask(i).toString());
            }
        }
    }

    public void showTaskListLength(TaskList taskList) {
        System.out.println("Now you have " + taskList.length() + " task(s) in the list.");
    }

    public void showDelete(Task removedTask) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(removedTask.toString());
    }

    public void showMarkTask(Task task, boolean isMarked) {
        System.out.println("Nice! I've marked this task as " + (isMarked ? "done:" : "not done yet:"));
        System.out.println(task.toString());
    }

    public void showError(String message) {
        System.out.println(message);
    }
}
