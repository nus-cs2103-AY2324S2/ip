package utils;

import java.util.Scanner;

import exceptions.ConvoBotException;
import utils.TaskList;

public class UI {
    private final Scanner scanner;
    private final String LEFT_PADDING = "    ";

    public UI() {
        scanner = new Scanner(System.in);
    }

    public String readUserInput() {
        return scanner.nextLine();
    }

    public void showHorizontalLine(boolean newline) {
        System.out.println(LEFT_PADDING + "____________________________________________________________");
        if (newline) System.out.println();
    }

    public void showWelcomeMsg() {
        showHorizontalLine(false);
        System.out.println(LEFT_PADDING + " Hello! I'm ConvoBot");
        System.out.println(LEFT_PADDING + " What can I do for you?");
        showHorizontalLine(true);
    }

    public void showExitMsg() {
        showHorizontalLine(false);
        System.out.println(LEFT_PADDING + " Bye. Hope to see you again soon!");
        showHorizontalLine(true);
    }

    public void showTaskList(TaskList tasks) {
        System.out.println(LEFT_PADDING + " " + "Here are the tasks in your list:"); 
        for (int i = 0; i < tasks.size(); i++) {
            int index = i+1;
            try {
                System.out.println(LEFT_PADDING + " " + Integer.toString(index)
                + "." + tasks.getTaskString(i));
            } catch (ConvoBotException e) {
            }
        }
    }

    public void showAdded(String taskString, int dbSize) {
        System.out.println(LEFT_PADDING + " " + "Got it. I've added this task:");
        System.out.println(LEFT_PADDING + "   " + taskString);
        System.out.println(LEFT_PADDING + " Now you have " + Integer.toString(dbSize) + " tasks in the list.");
    }

    public void showRemoved(String taskString, int dbSize) {
        System.out.println(LEFT_PADDING + " " + "Noted. I've removed this task:");
        System.out.println(LEFT_PADDING + "   " + taskString);
        System.out.println(LEFT_PADDING + " Now you have " + Integer.toString(dbSize) + " tasks in the list.");
    }

    public void showMarked(String taskString) {
        System.out.println(LEFT_PADDING + " " + "Nice! I've marked this task as done:");
        System.out.println(LEFT_PADDING + " " + taskString);
    }

    public void showUnmarked(String taskString) {
        System.out.println(LEFT_PADDING + " " + "OK, I've marked this task as not done yet:");
        System.out.println(LEFT_PADDING + " " + taskString);
    }

    public void showError(String errorMsg) {
        System.out.println(LEFT_PADDING + " " + errorMsg);
    }
}
