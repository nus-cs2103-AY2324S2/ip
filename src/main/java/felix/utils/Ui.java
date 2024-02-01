package felix.utils;

import java.util.Scanner;

public class Ui {
    private static final String BOT_NAME = "Felix";
    private static final String LOGO = "___________    .__  .__        \n"
            + "\\_   _____/___ |  | |__|__  ___\n"
            + " |    __)/ __ \\|  | |  \\  \\/  / \n"
            + " |     \\\\  ___/|  |_|  |>    <  \n"
            + " \\___  / \\___  >____/__/__/\\_ \\ \n"
            + "     \\/      \\/              \\/ \n";
    private static final String LINE_SEPARATOR = "__________________________"
            + "__________________________________";

    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void println(Object obj) {
        System.out.println(obj);
    }
    public void printHorizontalLine() {
        System.out.println(LINE_SEPARATOR);
    }

    public void printException(Exception e) {
        System.out.println(e.getMessage());
    }

    public void printLoadingError(Exception e) {
        System.out.println("Error occurred when loading file:");
        this.printException(e);
        this.printHorizontalLine();
    }

    public void printLogo() {
        System.out.println("Hello from\n" + LOGO);
        this.printHorizontalLine();
    }


    public void printIntroduction() {
        this.printHorizontalLine();
        System.out.printf("Hello! I'm %s\nWhat can I do for you?\n",BOT_NAME);
        this.printHorizontalLine();
    }

    public void exitProgram() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public String getNextLine() {
        return this.scanner.nextLine();
    }
}
