package ui;

import task.TaskList;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Ui {

    private static final String LINE = "――――――――――――――――――――――――――――――――――――――――――――――――――";
    private static final String MESSAGE_WELCOME = "Hello! I'm Uncle Bob \n\t What can uncle do for you?";
    private static final String MESSAGE_BYE = "Bye! Hope to see you again soon!";

    private final Scanner in;
    private final PrintStream out;

    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public void showList (TaskList tasks) {
        out.println("\t" + LINE);
        for (int i = 0; i < tasks.numTasks(); i++){
            out.println("\t " + (i+1) + ". " + tasks.get(i));
        }
        out.println("\t" + LINE);
    }

    public void showErrorMessage(String message) {
        showToUser(message);
    }

    public String getUserCommand() {
        out.print("Enter command: ");
        return in.nextLine();
    }

    public void showGreetingMessage() {
        showToUser(MESSAGE_WELCOME);
    }

    public void showExitMessage() {
        showToUser(MESSAGE_BYE);
    }

    public void showToUser(String... message) {
        out.println("\t" + LINE);
        for (String m : message) {
            out.print("\t " + m + "\n");
        }
        out.println("\t" + LINE);
    }
}
