package duke.helpers;

import duke.command.CommandType;

import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    /**
     * Constructor of Ui.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prints out a line on the screen.
     */
    public void drawLine() {
        System.out.println("------------------------------------------------------" +
                "---------------------------------------------------");
    }

    /**
     * Displays a message with two draw lines.
     *
     * @param message message to be displayed.
     */
    public void displayToScreen(String message) {
        drawLine();
        System.out.println(message);
        drawLine();
    }

    /**
     * Prints out available commands.
     */
    public void printCommandList() {
        drawLine();
        System.out.println("These are the available commands :");
        System.out.println("0. Do " + CommandType.LISTCOMMANDS.toString() + " : "
                + CommandType.LISTCOMMANDS.getCommand());
        System.out.println("1. Add " + CommandType.TODO.toString() + " task : "
                + CommandType.TODO.getCommand());
        System.out.println("2. Add " + CommandType.DEADLINE.toString() + " task : "
                + CommandType.DEADLINE.getCommand());
        System.out.println("3. Add " + CommandType.EVENT.toString() + " task : "
                + CommandType.EVENT.getCommand());
        System.out.println("4. To " + CommandType.LIST.toString() +" all tasks : "
                + CommandType.LIST.getCommand());
        System.out.println("5. To " + CommandType.MARK.toString() + " task done : "
                + CommandType.MARK.getCommand());
        System.out.println("6. To " + CommandType.UNMARK.toString() + " task : "
                + CommandType.UNMARK.getCommand());
        System.out.println("7. To " + CommandType.DELETE.toString() + " a task : "
                + CommandType.DELETE.getCommand());
        System.out.println("8. To tasks related to a date : "
                + CommandType.CHECKDATE.getCommand());
        System.out.println("9. Close chatbot : " + CommandType.BYE.getCommand());
        drawLine();
    }

    /**
     * Prints out welcome message.
     */
    public void welcomeMessage() {
        drawLine();
        System.out.println("Hello! I'm Colin");
        System.out.println("What can I do for you?");
        drawLine();
    }

    /**
     * Returns the command that being input by User.
     *
     * @return String command input by user.
     */
    public String readCommand() {
        String command = scanner.nextLine();
        return command;
    }

}
