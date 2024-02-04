package earl.logic;

import earl.exceptions.EarlException;
import earl.tasks.Event;
import earl.util.Parser;
import earl.util.TaskList;
import earl.util.Ui;

/**
 * Class responsible for the event command.
 */
public class EventHandler extends Handler {

    private final String[] COMMAND;

    public EventHandler(String[] command) {
        COMMAND = command;
    }

    public void handle(TaskList tasks, Ui ui) throws EarlException {
        try {
            String[] args = COMMAND[1].split("\\s/(from|to)\\s");
            tasks.add(new Event(args[0],
                    Parser.parseDateTime(args[1]),
                    Parser.parseDateTime(args[2])));
            ui.makeResponse("Added new event.",
                    "\t" + tasks.get(tasks.getSize() - 1),
                    "There are " + tasks.getSize()
                            + " earl.tasks tracked.");
        } catch (IndexOutOfBoundsException e) {
            throw new EarlException("Error, invalid event format.\n"
                    + "\tExample use:\n\t"
                    + "\tevent <task_name> /from <start> /to <end>");
        } catch (Exception e) {
            throw new EarlException("Error, unknown use of event.\n"
                    + e.getMessage());
        }
    }
}
