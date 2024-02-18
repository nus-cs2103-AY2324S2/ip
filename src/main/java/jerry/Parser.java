package jerry;

import jerry.command.*;

/**
 * The Parser class is responsible for interpreting user input into commands that the chatbot can execute.
 * It parses the input string and returns a Command object corresponding to the user's request.
 */
public class Parser {
    private final Ui ui;
    private final TaskList taskList;

    /**
     * Constructs a Parser instance for interpreting user commands.
     * This parser is capable of translating raw text input into actionable commands within the chatbot application.
     */
    public Parser(Ui ui, TaskList taskList) {
        this.ui = ui;
        this.taskList = taskList;
    }

    /**
     * Parses the given input string into a specific Command object.
     * The method determines the type of command based on the input and
     * constructs an instance of the corresponding command class.
     *
     * @param input The raw input string from the user.
     * @return A Command object representing the user's command.
     */
    public Command parse(String input) {
        String[] parts = input.split(" ", 2);
        String command = parts[0].toLowerCase();
        int taskIndex;

        switch (command) {
            case "bye":
                return new ByeCommand(ui);

            case "list":
                return new ListCommand(ui, taskList);

            case "mark":
                taskIndex = Integer.parseInt(parts[1]) - 1;
                return new MarkCommand(ui, taskList, taskIndex);

            case "unmark":
                taskIndex = Integer.parseInt(parts[1]) - 1;
                return new UnmarkCommand(ui, taskList, taskIndex);

            case "delete":
                taskIndex = Integer.parseInt(parts[1]) - 1;
                return new DeleteCommand(ui, taskList, taskIndex);

            case "todo":
                if (parts.length < 2) {
                    // Handle the case where the argument is missing
                    return new AddTodoCommand(ui, taskList, "");
                } else {
                    return new AddTodoCommand(ui, taskList, parts[1]);
                }

            case "deadline":
                if (parts.length < 2) {
                    // Handle the case where the argument is missing
                    return new AddDeadlineCommand(ui, taskList, "");
                } else {
                    return new AddDeadlineCommand(ui, taskList, parts[1]);
                }

            case "event":
                if (parts.length < 2) {
                    // Handle the case where the argument is missing
                    return new AddEventCommand(ui, taskList, "");
                } else {
                    return new AddEventCommand(ui, taskList, parts[1]);
                }

            case "find":
                if (parts.length < 2) {
                    // Handle the case where the argument is missing
                    return new FindCommand(ui, taskList, "");
                } else {
                    return new FindCommand(ui, taskList, parts[1]);
                }

            case "view":
                if (parts.length < 2) {
                    return new ViewCommand(ui, taskList, "");
                } else {
                    return new ViewCommand(ui, taskList, parts[1]);
                }

            default:
                return new InvalidCommand(ui);
        }
    }
}
