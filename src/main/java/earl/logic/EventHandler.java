package earl.logic;

import java.time.LocalDateTime;

import earl.exceptions.EarlException;
import earl.tasks.TaskType;
import earl.util.TaskList;
import earl.util.Ui;
import earl.util.parsers.DateTimeParser;

/**
 * Class responsible for the event command.
 */
public class EventHandler extends TaskHandler {

    /** Class constructor. */
    public EventHandler(String args) {
        super(args);
    }

    @Override
    public void handle(TaskList tasks, Ui ui) throws EarlException {
        try {
            String[] data = args.split("\\s+/(from|to)\\s+");
            String[] response = addTask(tasks, TaskType.EVENT, data);
            ui.makeResponse(response);
        } catch (EarlException e) {
            throw new EarlException(ui.appendNewline(e.getMessage())
                    + ui.leftPad(ui.appendNewline("Example use:"))
                    + ui.leftPad("event <task name> "
                            + "/from <date time> /to <date time>"));
        }
    }
}
