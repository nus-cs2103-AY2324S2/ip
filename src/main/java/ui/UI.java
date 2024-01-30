package ui;

import static utils.Messages.MESSAGE_EXIT;
import static utils.Messages.MESSAGE_GREET;
import static utils.Messages.MESSAGE_STORAGE_PATH;
import static utils.Messages.MESSAGE_DIVIDER;

import java.io.PrintWriter;
import java.util.Scanner;

import task.Task;
import task.TaskList;

public class UI {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String PROMPT = "> ";
    private final Scanner in;
    private final PrintWriter out;

    public UI() {
        this.in = new Scanner(System.in);
        this.out = new PrintWriter(System.out);
    }

    private void showToUser(String message) {
        out.println(MESSAGE_DIVIDER);
        out.println(message);
        out.println(MESSAGE_DIVIDER);
        out.flush();
    }

    private void showToUser(String[] messages) {
        out.println(MESSAGE_DIVIDER);
        for (String message : messages) {
            out.println(message);
        }
        out.println(MESSAGE_DIVIDER);
        out.flush();
    }

    public void showWelcomeMessage(String storageFilePath) {
        String storagePathString = String.format(MESSAGE_STORAGE_PATH, storageFilePath);
        String[] messages = {storagePathString, MESSAGE_GREET};
        showToUser(messages);
    }

    public String getUserInput() {
        out.print(PROMPT);
        out.flush();
        String userInput = in.nextLine();
        return userInput;
    }

    public void showTaskList(TaskList taskList) {
        showToUser(taskList.toString());
    }

    public void showTaskAdded(Task task, TaskList taskList) {
         String[] messages = {"Added task to the list:", task.toString(), "You have " + taskList.getNumberOfTasks() +" tasks currently."};
         showToUser(messages);
    }

    public void showTaskDeleted(Task task, TaskList taskList) {
        String[] messages = {"Deleted task from list:", task.toString(), "You have " + taskList.getNumberOfTasks() + " tasks currently"};
        showToUser(messages);
    }

    public void showTaskMarked(Task task, int taskIdx) {
        if (task.getIsMarked()) {
            showToUser("Task number " + taskIdx + " is already marked as done");
        } else {
            task.setMarked();
            String[] messages = {"Marked task as done:", task.toString()};
            showToUser(messages);
        }
    }

    public void showTaskUnmarked(Task task, int taskIdx) {
        if (!task.getIsMarked()) {
            showToUser("Task number " + taskIdx + " is already unmarked as not done");
        } else {
            task.setMarked();
            String[] messages = {"Unmarked task as not done:", task.toString()};
            showToUser(messages);
        }
    }

    public void showErrorMessage(String errorMsg) {
        out.println(ANSI_RED + errorMsg + ANSI_RESET);
    }

    public void showExitMessage() {
        showToUser(MESSAGE_EXIT);
    }

    public void exit() {
        in.close();
        out.close();
    }
    
}
