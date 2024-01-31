package Ui;

import java.util.Scanner;

public class Ui {
    private Scanner in;
    private final String line = "____________________________________________________________";

    public Ui() {
        this.in = new Scanner(System.in);
    }

    public void closeScanner() {
        this.in.close();
    }

    public void showWelcome() {
        System.out.println(line);
        System.out.println("Hello! I'm Fredricksen");
        System.out.println("What can I do for you?");
        System.out.println(line);
    }

    public void showLine() {
        System.out.println(line);
    }

    public void output(String content) {
        System.out.println(line);
        System.out.println(content);
        System.out.println(line);
    }

    public void showError(Exception e) {
        System.out.println(e.getMessage());
    }

    public String readCommand() {
        return this.in.nextLine();
    }

    public void listOfCommands() {
        System.out.println("Below are the available commands and formats to follow!");
        System.out.println("1. To view all your current task: list");
        System.out.println("2. To add new task:");
        System.out.println("    a. todos: todo <task>");
        System.out.println("    b. deadlines: deadline <task> /by <deadline>");
        System.out.println("    c. event: Event <event> /from <startdate, starttiming> /to <enddate, endtiming>");
        System.out.println("3. To delete a task: delete <task number>");
        System.out.println("4. To mark task as completed: mark <task number>");
        System.out.println("5. To unmark completed task: unmark <task number>");
        System.out.println("6. To exit program: bye");
    }
}