package Jerry;

import Jerry.command.*;

public class Parser {
    Ui ui;
    TaskList taskList;

    public Parser(Ui ui, TaskList taskList) {
        this.ui = ui;
        this.taskList = taskList;
    }

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
            default:
                return new InvalidCommand(ui);
        }
    }
}
