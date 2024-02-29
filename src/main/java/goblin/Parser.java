package goblin;

import goblin.command.*;
import goblin.exception.OrkException;

public class Parser {
    public static Command parse(String input) throws OrkException {
        if (input.startsWith("mark")) {
            String[] details = input.split(" ");
            String index = details[1];
            int indexInt = Integer.parseInt(index) - 1;
            return new MarkCommand(indexInt);
        } else if (input.startsWith("unmark")) {
            String[] details = input.split(" ");
            String index = details[1];
            int indexInt = Integer.parseInt(index) - 1;
            return new UnmarkCommand(indexInt);
        } else if (input.equals("list")) {
            return new ListCommand();
        } else if (input.startsWith("todo")) {
            String description = input.replaceFirst("todo", "");
            return new AddTodoCommand(description);
        } else if (input.startsWith("deadline")) {
            String details = input.replaceFirst("deadline", "");
            return new AddDeadlineCommand(details);
        } else if (input.startsWith("event")) {
            String details = input.replaceFirst("event", "");
            return new AddEventCommand(details);
        } else if (input.startsWith("delete")) {
            String[] details = input.split(" ");
            if (!(details.length == 2)) {
                throw new OrkException("Delete what?");
            }
            String indexString = details[1];
            int index = Integer.parseInt(indexString);
            return new DeleteCommand(index);
        } else if (input.equals("bye")) {
            return new ByeCommand();
        } else if (input.startsWith("find")) {
            String description = input.replaceFirst("find", "");
            return new FindCommand(description.trim());
        } else {
            throw new OrkException("You think you are smart? You fresh meat!");
        }
    }
}
