package nollid.commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import nollid.Parser;
import nollid.Storage;
import nollid.TaskList;
import nollid.exceptions.EmptyDescriptionException;
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
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws NollidException {
        checkDescriptionNotEmpty();
        checkDeadlineNotEmpty();

        String taskDescription = getTaskDescription();
        String deadlineString = getDeadlineString();

        try {
            LocalDateTime deadline = Parser.getLocalDateTimeFromString(deadlineString);
            Deadline task = new Deadline(taskDescription, deadline);
            tasks.add(task);

            String returnMessage = tasks.getAddSuccessMessage(task);
            storage.update(tasks);

            return returnMessage;
        } catch (DateTimeParseException e) {
            throw new InvalidArgumentException("Unrecognized deadline format\n" + USAGE_HINT);
        }
    }

    /**
     * Checks if a deadline description is provided.
     *
     * @throws InvalidArgumentException If the deadline description is empty.
     */
    private void checkDescriptionNotEmpty() throws EmptyDescriptionException {
        int byIndex = this.argsList.indexOf("/by");
        if (this.argsList.size() == 1 || byIndex == 1) {
            throw new EmptyDescriptionException("Deadline description cannot be empty!\n" + USAGE_HINT);
        }
    }

    /**
     * Checks if a deadline is provided after the "/by" argument.
     *
     * @throws InvalidArgumentException If the deadline is not provided.
     */
    private void checkDeadlineNotEmpty() throws InvalidArgumentException {
        int byIndex = this.argsList.indexOf("/by");
        if (byIndex == this.argsList.size() - 1 || byIndex == -1) {
            throw new InvalidArgumentException("Please input a deadline!\n" + USAGE_HINT);
        }
    }

    /**
     * Extracts the task description from the command-line arguments.
     *
     * @return The task description, consisting of the words before the "/by" argument.
     */
    private String getTaskDescription() {
        int byIndex = this.argsList.indexOf("/by");
        StringBuilder taskDescription = new StringBuilder();
        for (int i = 1; i < byIndex; i++) {
            if (i != byIndex - 1) {
                taskDescription.append(this.argsList.get(i)).append(" ");
            } else {
                taskDescription.append(this.argsList.get(i));
            }
        }

        return taskDescription.toString();
    }

    /**
     * Retrieves the deadline string from the input arguments.
     *
     * @return The deadline string.
     */
    private String getDeadlineString() {
        int byIndex = this.argsList.indexOf("/by");

        StringBuilder deadlineString = new StringBuilder();
        for (int i = byIndex + 1; i < this.argsList.size(); i++) {
            if (i != this.argsList.size() - 1) {
                deadlineString.append(this.argsList.get(i)).append(" ");
            } else {
                deadlineString.append(this.argsList.get(i));
            }
        }

        return deadlineString.toString();
    }
}

