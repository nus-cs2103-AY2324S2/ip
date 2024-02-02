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

    /**
     * Prints to the console a welcome message
     * everytime a user starts up the chatbot.
     */
    public void showWelcome() {
        System.out.println(line);
        System.out.println("Hello! I'm Fredricksen");
        System.out.println("What can I do for you?");
        System.out.println(line);
    }

    public void showLine() {
        System.out.println(line);
    }

    /**
     * Returns the content String passed as argument
     * in between 2 lines given by the line variable.
     *
     * @param content The String in between the 2 lines.
     * @return A String line separated consisting of line, content, line.
     */
    public String output(String content) {
        return line + "\n" + content + "\n" + line + "\n";
    }

    /**
     * Prints to the console the error message of the Exception.
     */
    public void showError(Exception e) {
        System.out.println(e.getMessage());
    }

    /**
     * Returns the next line that the user inputs into the CLI.
     * @return A String that the user inputs into the CLI.
     */
    public String readCommand() {
        return this.in.nextLine();
    }

    /**
     * Prints to the console a list of valid commands
     * and how to use them to get valid outputs.
     */
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