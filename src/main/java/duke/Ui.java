package duke;

import java.util.Scanner;

public class Ui {
    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        System.out.println("User:");
        return scanner.nextLine();
    }

    public void closeScanner() {
        scanner.close();
    }
    private void botHeader() {
        System.out.print("DevGPT:\n\t");
    }

    private void botHeader(String s) {
        System.out.print("DevGPT " + s + ":\n\t");
    }

    public void showWelcome() {
        botHeader();
        System.out.println("Hello! I'm DevGPT\n\tWhat can I do for you?");
    }

    public void showTaskList(TaskList tasks) throws DukeException {
        if (tasks.getSize() == 0) {
            throw new DukeException("No tasks found.");
        }
        botHeader();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.getSize(); i++) {
            System.out.println(doubleTab() + (i + 1) + "." + tasks.getTask(i));
        }
    }

    public void showDone(Task markTask) {
        botHeader();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(doubleTab() + markTask);
    }

    public void showUnmark(Task unmarkTask) {
        botHeader();
        System.out.println("Got it! I've marked this task as not done yet");
        System.out.println(doubleTab() + unmarkTask);
    }

    public void showDelete(Task deleteTask, int size) {
        botHeader();
        System.out.println("Poof! I've removed this task:");
        System.out.println(doubleTab() + deleteTask);
        System.out.println(doubleTab() + "Now you have " + size + " tasks in the list.");
    }

    public void showAddTask(Task task, int size) {
        botHeader();
        System.out.println("Got it. I've added this task:");
        System.out.println(doubleTab() + task);
        System.out.println(doubleTab() + "Now you have " + size + " tasks in the list.");
    }

    private String doubleTab() {
        return "\t\t";
    }

    public void showError(String s) {
        botHeader("Error");
        System.out.println(s);
    }

    public void commandNotUnderstood() {
        showError("Your message is not understood. Please use following:\n\t"
                + "todo <description>\n\t"
                + "deadline <description> /by <dd-mm-yyyy>\n\t"
                + "event <description> /from <dd-mm-yyyy> /to <dd-mm-yyyy>\n\t"
                + "list\n\t"
                + "done <index>\n\t"
                + "delete <index>\n\t"
                + "find <keyword>\n\t"
                + "bye");
    }


    public void showBye() {
        botHeader();
        System.out.println("Bye. Hope to see you again soon!");
    }
}
