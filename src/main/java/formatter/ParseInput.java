package formatter;

import commands.ByeCommand;
import commands.Command;
import commands.DeadlineCommand;
import commands.DeleteCommand;
import commands.EventCommand;
import commands.FindCommand;
import commands.HelpCommand;
import commands.ListCommand;
import commands.MarkCommand;
import commands.TodoCommand;
import commands.UnknownCommand;
import commands.UnmarkCommand;
import tasks.TaskList;

public class ParseInput {
    public ParseInput() {}

    public Command getCommand(String userInput, TaskList tasks) {
        String[] inputCommand = userInput.split(" ");
        switch (inputCommand[0]) {
        case "list":
            return new ListCommand(tasks);
        case "todo":
            return new TodoCommand(userInput, tasks);
        case "event":
            return new EventCommand(userInput, tasks);
        case "deadline":
            return new DeadlineCommand(userInput, tasks);
        case "mark":
            return new MarkCommand(inputCommand, tasks);
        case "unmark":
            return new UnmarkCommand(inputCommand, tasks);
        case "delete":
            return new DeleteCommand(inputCommand, tasks);
        case "find":
            return new FindCommand(userInput, inputCommand, tasks);
        case "help":
            return new HelpCommand();
        case "bye":
            return new ByeCommand();
        default:
            return new UnknownCommand();
        }
    }
}
