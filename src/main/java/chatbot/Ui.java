package chatbot;

import java.util.Scanner;

public class Ui {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_CYAN = "\u001B[36m";
    private static final String LOGO =
            "    ____  __                 \n" +
                    "   / __ \\/ /___ _____  ____ _\n" +
                    "  / /_/ / / __ `/ __ \\/ __ `/\n" +
                    " / ____/ / /_/ / / / / /_/ / \n" +
                    "/_/   /_/\\__,_/_/ /_/\\__,_/  \n";

    private static final String NAME = "Plana";

    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void greet() {
        System.out.println("Hello from\n" + LOGO);
        System.out.println("Hello! I'm " + NAME + "!");
        System.out.println("What can I do for you?");
        System.out.println("======================");
    }

    public String getInput() {
        System.out.print(ANSI_GREEN + "> ");
        return this.scanner.nextLine();
    }

    private void display(String msg) {
        System.out.println(ANSI_CYAN + msg + ANSI_RESET);
    }

    public void displayList(TaskList tl) {
        if (tl.toString().isEmpty()) {
            this.display("You have no tasks right now, add some!");
        } else {
            this.display("You've added the following tasks so far:\n" + tl);
        }
    }

    public void displayAdd(TaskList tl, Task t) {
        this.display("Got it. I've added this task:\n" +
                ">> " + t + "\n" +
                "You now have " + tl.getSize() + " tasks in the list.\n");
    }

    public void displayMark(int i) {
        this.display("Task " + i + "marked as done");
    }

    public void displayUnmark(int i) {
        this.display("Task " + i + "marked as undone");
    }

    public void displayDelete(TaskList tl, Task t) {
        this.display("Got it. I've removed this task:\n" +
                ">> " + t + "\n" +
                "You now have " + tl.getSize() + " tasks in the list.\n");
    }

    public void displayError(Exception e) {
        System.out.println(ANSI_RED + e.getMessage() + ANSI_RESET);
    }

    public void bye() {
        this.scanner.close();
        System.out.println(ANSI_RESET + "==================");
        System.out.println("See you next time!");
    }
}
