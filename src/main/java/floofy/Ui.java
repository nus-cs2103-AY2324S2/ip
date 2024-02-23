package floofy;

import floofy.task.Task;

import java.util.Scanner;
public class Ui {
    protected Scanner scanner;
    private final String line = "---------------------------";
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String wrapWithlines(String str) {
        return line + "\n" + str + "\n" + line;
    }

    public String showLoadingError() {
        String s = "An error occurred while loading tasks from file.";
        return wrapWithlines(s);
    }
    public String showWelcomeMsg() {
        String s1 = "Hello! I'm floofy.Floofy!";
        String s2 = "What can I do for you?";
        return wrapWithlines(s1 + "\n" + s2);
    }

    public String showMarkedTask(Task task) {
        String msg = "Nice! I've marked this task as done:";
        String markedTask = task.toString();
        return wrapWithlines(msg + "\n" + markedTask);
    }

    public String showUnmarkedTask(Task task) {
        String msg = "OK, I've marked this task as not done yet:";
        String unmarkedTask = task.toString();
        return wrapWithlines(msg + "\n" + unmarkedTask);
    }

    public String showAddedTask(Task task, int len) {
        String startMsg = "Got it. I've added this task:";
        String addedTask = task.toString();
        String endMsg = "Now you have " + len + " tasks in the list.";
        return wrapWithlines(startMsg + "\n" + addedTask + "\n" + endMsg);
    }

    public String showDeletedTask(Task task, int len) {
        String startMsg = "Noted. I've removed this task:";
        String deletedTask = task.toString();
        String endMsg = "Now you have " + len + " tasks in the list.";
        return wrapWithlines(startMsg + "\n" + deletedTask + "\n" + endMsg);
    }

    public String showTaskList(TaskList list) {
        String output = "";
        for (int i = 0; i < list.getSize(); i++) {
            String numberedOutput = String.format("%d. %s", i + 1, list.getTask(i).toString());
            output += numberedOutput + "\n";
        }
        return wrapWithlines(output);
    }

    public String showMatchingTasks(TaskList list) {
        String msg = "Here are the matching tasks in your list:";
        String output = "";
        for (int i = 0; i < list.getSize(); i++) {
            String numberedOutput = String.format("%d. %s", i + 1, list.getTask(i).toString());
            output += numberedOutput + "\n";
        }
        return wrapWithlines(msg + "\n" + output);
    }

    public String showInvalidInput() {
        String msg1 = "You have input an invalid command!";
        String msg2 = "To add a task, please start with any of these commands: 'todo', 'deadline' or 'event'!";
        return wrapWithlines(msg1 + "\n" + msg2);
    }

    public String showGoodbyeMsg() {
        String msg = "Bye. Hope to see you again soon!";
        return wrapWithlines(msg);
    }

}
