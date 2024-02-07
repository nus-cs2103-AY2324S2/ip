package earl.logic;

import earl.exceptions.EarlException;
import earl.tasks.Deadline;
import earl.util.Parser;
import earl.util.TaskList;
import earl.util.Ui;

/**
 * Class responsible for the deadline command.
 */
public class DeadlineHandler extends Handler {

    private final String[] command;

    /**
     * Class constructor.
     *
     * @param command  the user input that invoked this handler
     */
    public DeadlineHandler(String[] command) {
        this.command = command;
    }

    @Override
    public void handle(TaskList tasks, Ui ui) throws EarlException {
        try {
            String[] args = Parser.parseUserInput(command[1], "\\s/by\\s");
            tasks.add(new Deadline(args[0], args[1]));
            ui.makeResponse("Added new deadline.",
                    "\t" + tasks.get(tasks.getSize() - 1),
                    "There are " + tasks.getSize() + " task(s) tracked.");
        } catch (IndexOutOfBoundsException e) {
            throw new EarlException(
                    "Error, invalid deadline format.\n"
                            + "\tExample use:\n\t"
                            + "\tdeadline <task_name> /by <end>");
        } catch (Exception e) {
            throw new EarlException("Error, unknown use of deadline.\n"
                    + e.getMessage());
        }
    }
}
