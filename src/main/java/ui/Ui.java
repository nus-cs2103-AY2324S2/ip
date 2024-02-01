package ui;

import java.io.PrintStream;
import java.util.Scanner;

public class Ui {
    private static final String LINE = "____________________________________________________________\n";
    private final Scanner sc;
    private final PrintStream printer;

    public Ui() {
        this.sc = new Scanner(System.in);
        this.printer = new PrintStream(System.out);
    }
    public void showWelcome() {
        printer.print(LINE + "Paws what you're doing! I'm Blawg\n" +
                "What can I do for you?\n" + LINE);
    }

    public void showBye() {
        printer.print(" Alright, this kitty's got to go chase some shadows. See you later!\n");
    }

    public void showLine() {
        printer.print(LINE);
    }

    public String getUserInput() {

        return sc.nextLine();
    }

    public void showResult(String result) {

        printer.print(result);
    }

    public void showError(String error) {

        printer.print(error);
    }
}
