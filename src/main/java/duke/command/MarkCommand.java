package duke.command;

import java.io.IOException;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.task.TaskListException;
import duke.utils.Parser;

/**
 * Class to run the Mark Command.
 *
 * @author KohGuanZeh
 */
public class MarkCommand extends Command {
    private final int indexToMark;

    /**
     * Creates a Command that runs to mark a task as done at a specified index.
     *
     * @param input Input of task to mark.
     * @throws CommandException Exception when input is not given in integer format.
     */
    public MarkCommand(String input) throws CommandException {
        try {
            this.indexToMark = Parser.parseInteger(input);
        } catch (NumberFormatException e) {
            throw new CommandException("Error. Mark expects the index of task to be marked.");
        }
    }

    @Override
    public String run(TaskList taskList, Storage storage) throws IOException, CommandException, TaskListException {
        String message = taskList.markTask(this.indexToMark);
        storage.save(taskList.toDataString());
        return message;
    }
}
