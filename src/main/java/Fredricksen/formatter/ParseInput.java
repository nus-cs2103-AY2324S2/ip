package Fredricksen.formatter;

import Fredricksen.commands.ByeCommand;
import Fredricksen.commands.Command;
import Fredricksen.commands.DeadlineCommand;
import Fredricksen.commands.DeleteCommand;
import Fredricksen.commands.EventCommand;
import Fredricksen.commands.FindCommand;
import Fredricksen.commands.HelpCommand;
import Fredricksen.commands.ListCommand;
import Fredricksen.commands.MarkCommand;
import Fredricksen.commands.TodoCommand;
import Fredricksen.commands.UnknownCommand;
import Fredricksen.commands.UnmarkCommand;
import Fredricksen.tasks.TaskList;

public class ParseInput {
    public ParseInput() {}

    public Command getCommand(String userInput, TaskList tasks) {
        String[] inputCommand = userInput.split(" ");

        // assert method 1
        assert inputCommand[0] != "" : "Processing command";

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
