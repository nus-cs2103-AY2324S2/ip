package lelu;

import commands.AddDeadlineCommand;
import commands.AddEventCommand;
import commands.AddToDoCommand;
import commands.ByeCommand;
import commands.Command;
import commands.DeleteCommand;
import commands.FindCommand;
import commands.ListCommand;
import commands.MarkCommand;
import commands.UnmarkCommand;
import exceptions.InvalidInputException;
import exceptions.LeluException;
import ui.Ui;

public class Parser {
    public static Command parse(String message, Ui ui) throws LeluException {
        if (message.equals("bye")) {
            return new ByeCommand();
        } else if (message.equals("list")) {
            return new ListCommand();
        }
        return switch (message.split(" ")[0]) {
            case "mark" -> new MarkCommand();
            case "unmark" -> new UnmarkCommand();
            case "delete" -> new DeleteCommand();
            case "todo" -> new AddToDoCommand();
            case "deadline" -> new AddDeadlineCommand();
            case "event" -> new AddEventCommand();
            case "find" -> new FindCommand();
            default -> throw new InvalidInputException(ui.showInstructions());
        };
    }
}
