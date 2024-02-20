package Mitsuki;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * Deals with making sense of the user command.
 *
 * @author Tee Chu Jie
 */
public class Parser {
    /**
     * Initiating the scanner for user input.
     */
    private final Scanner scan = new Scanner(System.in);

    /**
     * A list of commands that can be given to Mitsuki.
     */
    protected ArrayList<String> commands = new ArrayList<String>();
    private Ui ui = new Ui();

    /**
     * A constructor for a Parser object.
     * Adds all currently available commands to the list of commands
     * that will be used by the Parser object.
     */
    public Parser() {
        // Adding the commands to the list of commands Mitsuki can execute.
        this.commands.add("help");
        this.commands.add("list");
        this.commands.add("todo");
        this.commands.add("deadline");
        this.commands.add("event");
        this.commands.add("mark");
        this.commands.add("unmark");
        this.commands.add("delete");
        this.commands.add("bye");
    }

    /**
     * Processes user input and calls the relevant method based
     * on what user comand is.
     *
     * @param command the command to be executed.
     */
    public void parse(String command) {
        // The loop that takes in user input and determines what to do.
        while (command != null) {
            // Taking in next user input.
            command = scan.next();

            // Making sure user is giving a valid command.
            try {
                MitsukiException.validate(command, commands);
            } catch (MitsukiException ex) {
                ui.invalidCommandMessage();
            }

            // Calling the method based on what the user input is.
            switch (command) {
            case "help":
                ui.printHelpList(commands);
                break;

            case "deadline":
                TaskList.deadline(scan);
                break;

            case "todo":
                TaskList.todo(scan);
                break;

            case "event":
                TaskList.event(scan);
                break;

            case "list":
                TaskList.list();
                break;

            case "mark":
                TaskList.mark(scan);
                break;

            case "unmark":
                TaskList.unmark(scan);
                break;

            case "delete":
                TaskList.delete(scan);
                break;

            case "bye":
                try {
                    Storage.save("list.txt");
                } catch (IOException ex) {
                    ui.printErrorMessage(ex);
                }
                ui.printByeMessage(scan);

            default:
                break;
            }
        }
    }
}
