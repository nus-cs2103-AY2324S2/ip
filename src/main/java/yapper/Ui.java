package yapper;

import yapper.tasks.Task;

import java.util.List;
public class Ui {
    public void showWelcomeMessage() {
        System.out.println("Hello! I'm Yapper.");
    }

    public void showInstructions() {
        System.out.println("What would you like to yap about today? :-)");
    }

    public void showUserPrompt() {
        System.out.println("User: ");
    }

    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    public void showLoadingError() {
        System.out.println("Error loading tasks from file. Creating a new task list.");
    }

    public void showTaskList(List<Task> tasks) {
        System.out.println("Here are the tasks in your yapping list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
    }

    public void showMarkedDoneMessage(Task task) {
        System.out.println("Nice yap! I've marked this task as done:");
        System.out.println(" " + task);
    }

    public void showMarkedNotDoneMessage(Task task) {
        System.out.println("Ok bro, I've marked this task as not done yet:");
        System.out.println(" " + task);
    }

    public void showAddedTaskMessage(Task task, int taskCount) {
        System.out.println("Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    public void showRemovedTaskMessage(Task task, int taskCount) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("   " + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    public void showGoodbyeMessage() {
        System.out.println("Bye. Hope to yap with you again soon!");
    }
}