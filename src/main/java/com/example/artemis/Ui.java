package com.example.artemis;

import java.util.ArrayList;
public class Ui {
    public void showWelcomeMessage() {
        showLine();
        System.out.println("     Hello! I'm Artemis");
        System.out.println("     What can I do for you?");
        showLine();
    }

    public void showGoodbyeMessage() {
        showLine();
        System.out.println("     Bye. Hope to see you again soon!");
        showLine();
    }

    public void showLoadingError() {
        showLine();
        System.out.println("     Error loading tasks from file.");
        showLine();
    }

    public void showError(String message) {
        showLine();
        System.out.println("     " + message);
        showLine();
    }

    public void showTaskList(ArrayList<Task> tasks) {
        showLine();
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); ++i) {
            System.out.println("     " + (i + 1) + "." + tasks.get(i));
        }
        showLine();
    }

    public void showTaskMarkedAsDone(Task task) {
        showLine();
        System.out.println("     Nice! I've marked this task as done:");
        System.out.println("       " + task);
        showLine();
    }

    public void showTaskMarkedAsNotDone(Task task) {
        showLine();
        System.out.println("     OK, I've marked this task as not done yet:");
        System.out.println("       " + task);
        showLine();
    }

    public void showTaskAdded(int size, Task task) {
        showLine();
        System.out.println("     Got it. I've added this task:");
        System.out.println("       " + task);
        System.out.println("     Now you have " + size + " tasks in the list.");
        showLine();
    }

    public void showTaskDelete(Task task, int index) {
        showLine();
        System.out.println("     Noted. I've removed this task:");
        System.out.println("       " + task);
        System.out.println("     Now you have " + index + " tasks in the list.");
        showLine();
    }

    public void showLine() {
        System.out.println("    ____________________________________________________________");
    }
}