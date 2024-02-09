package duke.command;

import java.io.IOException;

import duke.storage.Storage;
import duke.task.Priority;
import duke.task.TaskList;
import duke.task.TaskListException;
import duke.utils.FormatException;
import duke.utils.Parser;
import javafx.util.Pair;

/**
 * Class to run Priority Command.
 *
 * @author KohGuanZeh
 */
public class PriorityCommand extends Command {
    private final int indexToModify;

    private final Priority priority;

    /**
     * Creates a Command that runs to modify priority of a task at a specified index.
     *
     * @param input Input of task to modify priority.
     * @throws CommandException Exception when input does not conform to correct format.
     */
    public PriorityCommand(String input) throws CommandException {
        try {
            Pair<Integer, Priority> indexPriorityPair = Parser.parsePriorityInput(input);
            this.indexToModify = indexPriorityPair.getKey();
            this.priority = indexPriorityPair.getValue();
        } catch (NumberFormatException e) {
            throw new CommandException("Error. Priority expects the index of task to be modified.");
        } catch (FormatException e) {
            throw new CommandException("Error. Unknown priority value. Only high/low/none are allowed.");
        }
    }

    @Override
    public String run(TaskList taskList, Storage storage) throws IOException, CommandException, TaskListException {
        String message = taskList.setPriority(this.indexToModify, this.priority);
        storage.save(taskList.toDataString());
        return message;
    }
}
