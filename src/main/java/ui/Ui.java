package ui;

import exception.UncleBobException;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static messages.Messages.MESSAGE_WELCOME;
import static messages.Messages.MESSAGE_BYE;

public class Ui {

    private static final String LINE = "\t――――――――――――――――――――――――――――――――――――――――――――――――――\n";

    private final Scanner in;
    private final PrintStream out;

    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public void showError(String message) {
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
        for (String m : message) {
            out.println(LINE + "\t " + m + "\n" + LINE);
        }
    }
}
