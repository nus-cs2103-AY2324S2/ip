package Commands;

import Exceptions.DudeException;

public class HelpCommand extends Command {

    private static final String COMMAND_FORMAT = "help <?command>";

    private final String[] supportedCommands = new String[]{
            "list: Lists all the tasks in the task list.",
            "todo: Adds a todo task to the task list.",
            "deadline: Adds a deadline task to the task list.",
            "event: Adds an event task to the task list.",
            "mark: Marks a task as done.",
            "unmark: Marks a task as undone.",
            "delete: Deletes a task from the task list.",
            "bye: Exits the program.",
            "help: Provides help on the commands I support."
    };

    private final String input;

    public HelpCommand(String input) {
        super("help <?command>", "help");
        this.input = input.trim();
    }

    public String execute() throws DudeException {
        if (input.equals("help")) {
            return this.generateHelpMessage();
        } else {
            return this.generateCommandHelpMessage();
        }
    }

    private String generateHelpMessage() {
        String helpMessage = "Here are the commands I support:";
        for (String command : supportedCommands) {
            helpMessage += "\n\t" + command;
        }
        helpMessage += "\nFor more information on a specific command, type 'help <command>'";
        return helpMessage;
    }


    private String generateCommandHelpMessage() {
        String[] parts = input.split(" ", 2);

        //the length must be two, as the string is not equals to "help".
        String command = parts[1];

        switch (command) {
        case "list":
            return "list: Lists all the tasks in the task list."
                    + "\n\tFormat: list"
                    + "\n\tExample: list";
        case "todo":
            return "todo: Adds a todo task to the task list."
                    + "\n\tFormat: todo <description>"
                    + "\n\tExample: todo read book"
                    + "\n\tNote: The description cannot be empty.";
        case "deadline":
            return "deadline: Adds a deadline task to the task list."
                    + "\n\tFormat: deadline <description> /by <date>"
                    + "\n\tExample: deadline return book /by 09/09/2021 18:00"
                    + "\n\tExample (2): deadline get book /by 09/09/2021"
                    + "\n\t*Note: The description and date cannot be empty."
                    + "\n\t*Note: The date must be in the format d/M/yyyy H:m or d/M/yyyy";
        case "event":
            return "event: Adds an event task to the task list."
                    + "\n\tFormat: event <description> /from <date> /to <date>"
                    + "\n\tExample: event project meeting /from 09/09/2021 18:00 /to 09/09/2021 20:00"
                    + "\n\tExample (2): event project meeting /from 09/09/2021 /to 09/09/2021"
                    + "\n\t*Note: The description and date cannot be empty."
                    + "\n\t*Note: The date must be in the format d/M/yyyy H:m or d/M/yyyy";
        case "mark":
            return "mark: Marks a task as done."
                    + "\n\tFormat: mark <id>"
                    + "\n\tExample: mark 1"
                    + "\n\t*Note: The id must be a valid integer task id.";
        case "unmark":
            return "unmark: Marks a task as undone."
                    + "\n\tFormat: unmark <id>"
                    + "\n\tExample: unmark 1"
                    + "\n\t*Note: The id must be a valid integer task id.";
        case "delete":
            return "delete: Deletes a task from the task list."
                    + "\n\tFormat: delete <id>"
                    + "\n\tExample: delete 1"
                    + "\n\t*Note: The id must be a valid integer task id.";
        case "bye":
            return "bye: Exits the program."
                    + "\n\tFormat: bye"
                    + "\n\tExample: bye";
        }

        return "I'm sorry, I don't know what that command is. Please type 'help' to see the list of commands I support.";
    }

    @Override
    public CommandTypes getCommandType() {
        return CommandTypes.HELP;
    }
}
