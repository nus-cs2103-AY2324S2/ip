package earl.logic;

import earl.exceptions.EarlException;
import earl.tasks.Deadline;
import earl.util.TaskList;
import earl.util.Ui;

/**
 * Class responsible for the deadline command.
 */
public class DeadlineHandler extends Handler {

    private final String[] COMMAND;

    public DeadlineHandler(String[] command) {
        COMMAND = command;
    }

    public void handle(TaskList tasks, Ui ui) throws EarlException {
        try {
            String[] args = COMMAND[1].split("\\s/by\\s");
            tasks.add(new Deadline(args[0], args[1]));
            ui.makeResponse("Added new deadline.",
                    "\t" + tasks.get(tasks.getSize() - 1),
                    "There are " + tasks.getSize()
                            + " earl.tasks tracked.");
        } catch (IndexOutOfBoundsException e) {
            throw new EarlException(
                    "Error, invalid deadline format.\n"
                            + "\tExample use:\n\t"
                            + "\tdeadline <task_name> /by <end>");
        } catch (Exception e) {
            throw new EarlException(
                    "Error, unknown use of deadline.\n"
                            + e.getMessage());
        }
    }
}
