package dobby;

import java.util.List;
import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public String printLine() {
        return "______________________________________";
    }

    public String welcome() {
        return printLine() + "\n" +
               "Hello! I'm Dobby\n" + 
               "What can I do for you?\n" +
               printLine();
    }

    public String bye() {
        return printLine() + "\n" +
               "Bye. Hope to see you again soon!\n" +
               printLine();
    }

    public String list(List<Task> tasks) {
        StringBuilder result = new StringBuilder();
        result.append(printLine()).append("\n")
              .append("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            Task addTask = tasks.get(i);
            result.append((i + 1)).append(".").append(addTask.toString()).append("\n");
        }
        result.append(printLine());
        return result.toString();
    }

    public String findList(List<Task> tasks) {
        StringBuilder result = new StringBuilder();
        result.append(printLine()).append("\n")
              .append("Here are the matching tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            Task addTask = tasks.get(i);
            result.append((i + 1)).append(".").append(addTask.toString()).append("\n");
        }
        result.append(printLine());
        return result.toString();
    }

    public String unmark(Task t) {
        return printLine() + "\n" +
               "OK, I've marked this task as not done yet:\n" +
               t.toString() + "\n" +
               printLine();
    }

    public String mark(Task t) {
        return printLine() + "\n" +
               "Nice! I've marked this task as done:\n" +
               t.toString() + "\n" +
               printLine();
    }

    public String showError(DukeException e) {
        return printLine() + "\n" +
               "OOPS!!! " + e.toString() + "\n" +
               printLine();
    }

    public String dontUnderstand() {
        return printLine() + "\n" +
               "OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
               printLine();
    }

    public String showLoadingError() {
        return printLine() + "\n" +
               "loading error\n" +
               printLine();
    }

    public String addedMessage(Task task) {
        return printLine() + "\n" +
               "Got it. I've added this task:\n" +
               task.toString();
    }

    public String deletedMessage(Task task) {
        return printLine() + "\n" +
               "Noted. I've removed this task:\n" +
               task.toString();
    }

    public String totalTask(int count) {
        return "Now you have " + count + " tasks in the list.\n" +
               printLine();
    }
}
