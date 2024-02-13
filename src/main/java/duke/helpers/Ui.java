package duke.helpers;

import java.util.Scanner;

import duke.command.CommandType;

/**
 * UI class
 */
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
        System.out.println("------------------------------------------------------"
               + "---------------------------------------------------");
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
     * Returns String of available commands.
     */
    public String getCommandList() {
        StringBuilder output = new StringBuilder();
        String line1 = "These are the available commands :\n";
        String line2 = "0. Do " + CommandType.LISTCOMMANDS.toString() + " : "
                + CommandType.LISTCOMMANDS.getCommand() + "\n";
        String line3 = "1. Add " + CommandType.TODO.toString() + " task : "
                + CommandType.TODO.getCommand() + "\n";
        String line4 = "2. Add " + CommandType.DEADLINE.toString() + " task : "
                + CommandType.DEADLINE.getCommand() + "\n";
        String line5 = "3. Add " + CommandType.EVENT.toString() + " task : "
                + CommandType.EVENT.getCommand() + "\n";
        String line6 = "4. To " + CommandType.LIST.toString() + " all tasks : "
                + CommandType.LIST.getCommand() + "\n";
        String line7 = "5. To " + CommandType.MARK.toString() + " task done : "
                + CommandType.MARK.getCommand() + "\n";
        String line8 = "6. To " + CommandType.UNMARK.toString() + " task : "
                + CommandType.UNMARK.getCommand() + "\n";
        String line9 = "7. To " + CommandType.DELETE.toString() + " a task : "
                + CommandType.DELETE.getCommand() + "\n";
        String line10 = "8. To find tasks related to a date : "
                + CommandType.CHECKDATE.getCommand() + "\n";
        String line11 = "9. To find tasks related to a matching word : "
                + CommandType.FIND.getCommand() + "\n";
        String line12 = "10. Close chatbot : " + CommandType.BYE.getCommand() + "\n";
        output.append(line1);
        output.append(line2);
        output.append(line3);
        output.append(line4);
        output.append(line5);
        output.append(line6);
        output.append(line7);
        output.append(line8);
        output.append(line9);
        output.append(line10);
        output.append(line11);
        output.append(line12);
        return output.toString();
    }

    /**
     * Returns string welcome message.
     * @return String welcome message.
     */
    public String getWelcomeMessage() {
        return "Hello! I'm Colin\nWhat can I do for you?";
    }

}
