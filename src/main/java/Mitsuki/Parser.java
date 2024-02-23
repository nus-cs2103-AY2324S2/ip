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
        this.commands.add("find");
    }

    /**
     * Processes user input and calls the relevant method based
     * on what user command is.
     *
     * @param userInput the user's input.
     */
    public String parse(String userInput) {
        String command = userInput.indexOf(' ') < 0
                ? userInput
                : userInput.substring(0, userInput.indexOf(' '));

        // Making sure user is giving a valid command.
        try {
            MitsukiException.validate(command, commands);
        } catch (MitsukiException ex) {
            return ui.invalidCommandMessage();
        }

        String description;

        // Calling the method based on what the user input is.
        switch (command) {
        case "help":
            return ui.printHelpList(commands);

        case "deadline":
            description = userInput.substring(userInput.indexOf(' '));
            return TaskList.deadline(description);

        case "todo":
            description = userInput.substring(userInput.indexOf(' '));
            return TaskList.todo(description);

        case "event":
            description = userInput.substring(userInput.indexOf(' '));
            return TaskList.event(description);

        case "list":
            return TaskList.list();

        case "mark":
            description = userInput.substring(userInput.indexOf(' ') + 1);
            int index = Integer.parseInt(description);
            return TaskList.mark(index);

        case "unmark":
            description = userInput.substring(userInput.indexOf(' ') + 1);
            int index1 = Integer.parseInt(description);
            return TaskList.unmark(index1);

        case "delete":
            description = userInput.substring(userInput.indexOf(' ') + 1);
            int index2 = Integer.parseInt(description);
            return TaskList.delete(index2);

        case "bye":
            try {
                Storage.save("list.txt");
            } catch (IOException ex) {
                return ui.printErrorMessage(ex);
            }
            return ui.printByeMessage();

        case "find":
            description = userInput.substring(userInput.indexOf(' '));
            return TaskList.find(description);

        default:
            return " ";
        }
    }
}

