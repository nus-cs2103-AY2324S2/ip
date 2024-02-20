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
        output.append("These are the available commands:\n");

        String[] lines = new String[] {
            "0. Do " + CommandType.LISTCOMMANDS.toString() + " : " + CommandType.LISTCOMMANDS.getCommand(),
            "1. Add " + CommandType.TODO.toString() + " task : " + CommandType.TODO.getCommand(),
            "2. Add " + CommandType.DEADLINE.toString() + " task : " + CommandType.DEADLINE.getCommand(),
            "3. Add " + CommandType.EVENT.toString() + " task : " + CommandType.EVENT.getCommand(),
            "4. To " + CommandType.LIST.toString() + " all tasks : " + CommandType.LIST.getCommand(),
            "5. To " + CommandType.MARK.toString() + " task done : " + CommandType.MARK.getCommand(),
            "6. To " + CommandType.UNMARK.toString() + " task : " + CommandType.UNMARK.getCommand(),
            "7. To " + CommandType.DELETE.toString() + " a task : " + CommandType.DELETE.getCommand(),
            "8. To find tasks related to a date : " + CommandType.CHECKDATE.getCommand(),
            "9. To find tasks related to a matching word : " + CommandType.FIND.getCommand(),
            "10. To undo :" + CommandType.UNDO.getCommand(),
            "11. Close chatbot : " + CommandType.BYE.getCommand()
        };

        for (String line : lines) {
            output.append(line).append("\n");
        }
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
