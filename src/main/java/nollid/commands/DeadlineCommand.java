package nollid.commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import nollid.Parser;
import nollid.Storage;
import nollid.TaskList;
import nollid.exceptions.EmptyDescriptionException;
import nollid.exceptions.InvalidArgumentException;
import nollid.exceptions.MissingTagsException;
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
    public static final String USAGE_HINT =
            "Usage: deadline task_description /by d/m/yyyy [hh:mm] [/tags tag1,tag2,...";
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
        String taskDescription;
        try {
            taskDescription = Parser.getDescription(argsList);
        } catch (EmptyDescriptionException e) {
            throw new EmptyDescriptionException(e.getMessage() + "\n" + USAGE_HINT);
        }

        ArrayList<String> tags;
        try {
            tags = Parser.getTags(argsList);
        } catch (MissingTagsException e) {
            throw new MissingTagsException(e.getMessage() + "\n" + USAGE_HINT);
        }

        String deadlineString = Parser.getDeadlineString(argsList);
        LocalDateTime deadline;
        try {
            deadline = Parser.getLocalDateTimeFromString(deadlineString);
        } catch (DateTimeParseException e) {
            throw new InvalidArgumentException("Unrecognized deadline format.\n" + USAGE_HINT);
        }

        Deadline task = new Deadline(taskDescription, deadline, tags);
        tasks.add(task);
        storage.update(tasks);
        return tasks.getAddSuccessMessage(task);
    }
}

