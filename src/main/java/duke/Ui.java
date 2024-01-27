package duke;

import duke.exceptions.ChatException;

import java.util.Scanner;

public class Ui {
    private final Scanner sc;
    public Ui() {
        String msg =
                "Hello! I'm Refinement\n"
                        + "What can I do for you?\n\n";
        System.out.println(msg);
        this.sc = new Scanner(System.in);
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void printError(ChatException err) {
        System.out.println("ERR: " + err.getMessage());
    }

    public void respondUser(String msg) {
        System.out.println(msg);
    }

    public void showLine() {
        System.out.println("=========================================");
    }

    public void close() {
        sc.close();
    }
}
