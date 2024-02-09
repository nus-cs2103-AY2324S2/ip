package lia;

import java.util.ArrayList;

public class Ui {
    public void showLoadingError() {
        System.out.println("Hello, I'm Lia! To learn how to use me, type 'help'.");
    }

    public void showWelcomeMessage() {
        System.out.println("What can I do for you?");
    }

    public void showTasks(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("Your task list is currently empty.");
        } else {
            for (int j = 1; j <= tasks.size(); j++) {
                Task task = tasks.get(j - 1);
                System.out.println(j + ". " + task.toString());
            }
        }
    }

    public void showMarkedAsDone(Task task) {
        System.out.println("I've marked this task as done:");
        System.out.println(task.toString());
    }

    public void showMarkedAsNotDone(Task task) {
        System.out.println("I've marked this task as not done:");
        System.out.println(task.toString());
    }

    public void showAddedTask(Task task, TaskList tasks) {
        System.out.println("I've added this task:");
        System.out.println(task.toString());
        System.out.println("You have " + tasks.getSize() + " task(s) in the list.");
    }

    public void showRemovedTask(Task task, TaskList tasks) {
        System.out.println("I've removed this task:");
        System.out.println(task.toString());
        System.out.println("You have " + tasks.getSize() + " task(s) in the list.");
    }

    public void showHelp() {
        System.out.println("list\n" +
                "- Lists out all your tasks\n" +
                "todo <task description>\n" +
                "- Adds a task\n" +
                "deadline <task description> /by <due by>\n" +
                "- Adds a task with a deadline (input date in yyyy-MM-dd format)\n" +
                "event <event description> /from <starts at> to <ends at>\n" +
                "- Adds an event\n" +
                "mark <task number>\n" +
                "- Marks task at specified position as done\n" +
                "unmark <task number>\n" +
                "- Marks task at specified position as not done\n" +
                "delete <task number>\n" +
                " - Deletes task at specified position\n" +
                "exit\n" +
                "- Ends the conversation");
    }

    public void showInvalidCommand() {
        System.out.println("Invalid command. Type help for a list of valid commands and their usages.");
    }

    public void showGoodbye() {
        System.out.println("Goodbye!");
    }
}