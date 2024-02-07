package asher.Ui;

import java.util.ArrayList;

import asher.Tasks.TaskList;
import asher.Tasks.Task;

public class Ui {
    public Ui() {}

    public void showWelcomeMessage() {
        System.out.println("Hello! I'm Asher. What can I do for you?");
    }

    public void showExitMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showAddTaskMessage(Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.println(" " + task);
    }

    public void showRemoveTaskMessage(Task removedTask) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(" " + removedTask);
    }

    public void showNumberOfTaskInListMessage(int totalTasks) {
        System.out.println("Now you have " + totalTasks + " tasks in the list.");
    }

    public void showMarkTaskMessage(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(" "  + task);
    }

    public void showUnmarkTaskMessage(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + task);
    }

    public void showTaskList(TaskList taskList) {
        ArrayList<Task> tasks = taskList.getTasks();
        System.out.println("Here are the tasks in your list:");

        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
    }
}
