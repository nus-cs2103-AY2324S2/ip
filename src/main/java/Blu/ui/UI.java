package blu.ui;

import static blu.utils.Messages.MESSAGE_DIVIDER;
import static blu.utils.Messages.MESSAGE_EXIT;
import static blu.utils.Messages.MESSAGE_GREET;
import static blu.utils.Messages.MESSAGE_STORAGE_PATH;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import blu.task.Task;
import blu.task.TaskList;

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
        String[] messages = {"Marked task as done:", task.toString()};
        showToUser(messages);
    }

    public void showTaskAlreadyMarked(int taskIdx) {
        showToUser("Task number " + taskIdx + " is already marked as done");
    }

    public void showTaskAlreadyUnmarked(int taskIdx) {
        showToUser("Task number " + taskIdx + " is already unmarked as not done");
    }

    public void showTaskUnmarked(Task task) {
        String[] messages = {"Unmarked task as not done:", task.toString()};
        showToUser(messages);
    }

    public void showTasksFound(List<Task> tasks, String searchString) {
        if (tasks.isEmpty()) {
            showToUser("No tasks found containing " + searchString);
        } else {
            List<String> messagesList = new ArrayList<>();
            messagesList.add("There are " + tasks.size() + " tasks that contains " + searchString);
            int count = 1;
            for (Task task : tasks) {
                messagesList.add(count + ". " + task.toString());
                count++;
            }
            String[] messages = new String[messagesList.size()];
            messages = messagesList.toArray(messages);
            showToUser(messages);
        }
    }

    public void showErrorMessage(String errorMsg) {
        out.println(ANSI_RED + errorMsg + ANSI_RESET);
        out.flush();
    }

    public void showExitMessage() {
        showToUser(MESSAGE_EXIT);
    }

    public void exit() {
        in.close();
        out.close();
    }
    
}
