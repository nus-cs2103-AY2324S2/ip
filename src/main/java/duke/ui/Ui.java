package duke.ui;

import duke.dukeexception.DukeException;

import java.io.PrintStream;
import java.util.Scanner;

public class Ui {
    private final Scanner in;
    private final PrintStream out;

    public Ui() {
        in = new Scanner(System.in);
        out = System.out;
    }

    public void greet() {
        out.println("Hello! I'm " + "NotDuke");
        out.println("What can I do for you?");
    }

    public String getUserCommand() {
        String fullInputLine = in.nextLine();
        return fullInputLine;
    }

    public void sendReply(String outputMessage) {
        out.print(outputMessage);
    }

    public void showError(DukeException e) {
        out.println(e);
    }
}
