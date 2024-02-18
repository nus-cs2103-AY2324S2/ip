package cleo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class UI {
    // Keep the scanner if you still need it for any CLI functionality
    private final Scanner scanner;

    public UI() {
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public String showWelcomeMessage() {
        return "     Hello! I'm Cleo\n"
                + "     What can I do for you?\n";
    }

    public String showGoodbyeMessage() {
        return "     Bye. Hope to see you again soon!\n";
    }

    public String showErrorMessage(String errorMessage) {
        return errorMessage + "\n";
    }

    public String showTaskAdded(Task task, int taskCount) {
        return "     Got it. I've added this task:\n"
                + "       " + task + "\n"
                + "     Now you have " + taskCount + " tasks in the list.";
    }

    public String showTaskList(ArrayList<Task> tasks) {
        StringBuilder message = new StringBuilder("     Here are the tasks in your list:\n");
        if (tasks.isEmpty()) {
            message.append("     No tasks added yet.");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                message.append("     ").append(i + 1).append(".").append(tasks.get(i)).append("\n");
            }
        }
        return message.toString().trim(); // .trim() to remove the last newline character
    }

    public String showTasksOnDate(ArrayList<Task> tasks, LocalDate date) {
        StringBuilder message = new StringBuilder("Tasks on " + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ":\n");
        if (tasks.isEmpty()) {
            message.append("No tasks found on this date.");
        } else {
            for (Task task : tasks) {
                message.append(task).append("\n");
            }
        }
        return message.toString().trim(); // .trim() to remove the last newline character
    }

    public String showMaximumTasksReached() {
        return "     Maximum tasks reached. Cannot add more tasks.";
    }

    public String showTaskRemoved(Task task, int taskCount) {
        return "     Noted. I've removed this task:\n"
                + "       " + task + "\n"
                + "     Now you have " + taskCount + " tasks in the list.";
    }

    public String showTaskMarkedAsDone(Task task) {
        return "     Nice! I've marked this task as done:\n"
                + "       " + task;
    }

    public String showTaskMarkedAsNotDone(Task task) {
        return "     OK, I've marked this task as not done yet:\n"
                + "       " + task;
    }

}
