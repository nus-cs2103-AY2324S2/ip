package nollid.commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import nollid.Parser;
import nollid.Storage;
import nollid.TaskList;
import nollid.Ui;
import nollid.exceptions.InvalidArgumentException;
import nollid.exceptions.NollidException;
import nollid.tasks.Deadline;

/**
 * DeadlineCommand class represents a command for adding a Deadline task.
 * It extends the Command class and implements the execute method to perform the command logic.
 */
public class DeadlineCommand extends Command {
    /**
     * Constant string providing usage hint for the DeadlineCommand.
     */
    public static final String USAGE_HINT = "Usage: deadline [task description] /by [d/m/yyyy] {hh:mm 24hr format}";
    /**
     * ArrayList containing command arguments.
     */
    private final ArrayList<String> argsList;

    /**
     * Constructor for DeadlineCommand.
     *
     * @param argsList ArrayList containing command arguments.
     */
    public DeadlineCommand(ArrayList<String> argsList) {
        this.argsList = argsList;
    }

    /**
     * Overrides the execute method from the Command class.
     * Executes the command to add a deadline task.
     *
     * @param tasks   The TaskList containing tasks.
     * @param ui      The Ui for user interface interactions.
     * @param storage The Storage for data storage operations.
     * @throws NollidException Thrown if an exception specific to command execution occurs.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws NollidException {
        int byIndex = this.argsList.indexOf("/by");
        if (this.argsList.size() == 1 || byIndex == 1) {
            throw new InvalidArgumentException("Deadline description cannot be empty!\n"
                    + USAGE_HINT);
        }

        if (byIndex == this.argsList.size() - 1 || byIndex == -1) {
            throw new InvalidArgumentException("Please input a deadline!\n"
                    + USAGE_HINT);
        }

        StringBuilder taskDescription = new StringBuilder();
        for (int i = 1; i < byIndex; i++) {
            if (i != byIndex - 1) {
                taskDescription.append(this.argsList.get(i)).append(" ");
            } else {
                taskDescription.append(this.argsList.get(i));
            }
        }

        StringBuilder deadlineString = new StringBuilder();
        for (int i = byIndex + 1; i < this.argsList.size(); i++) {
            if (i != this.argsList.size() - 1) {
                deadlineString.append(this.argsList.get(i)).append(" ");
            } else {
                deadlineString.append(this.argsList.get(i));
            }
        }

        try {
            LocalDateTime deadline = Parser.getLocalDateTimeFromString(deadlineString.toString());
            Deadline task = new Deadline(taskDescription.toString(), deadline);
            tasks.add(task);

            String message = "Alright, added:\n" + "\t" + task + "\n";
            message += tasks.summary();
            storage.update(tasks);

            return message;
        } catch (DateTimeParseException e) {
            throw new InvalidArgumentException("Unrecognized deadline format\n"
                    + USAGE_HINT);
        }
    }
}
