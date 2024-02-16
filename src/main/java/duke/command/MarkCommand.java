package duke.command;

import duke.task.TaskList;
import duke.task.Task;

import duke.util.Ui;
import duke.util.Storage;

import duke.exception.MarkInvalidException;
import duke.exception.SaveStorageException;


import java.util.Objects;

/**
 * The abstract class representing a mark or unmark command.
 * */
public class MarkCommand extends Command {
    /* The separated list of constituent words in the user-entered command. */
    private final String[] commandList;

    /* Specifies the command type (mark or unmark). */
    private final String type;
    /* The chatbot notification to the user for marking a task as done. */
    public static final String MARK_RESPONSE = "Nice! I've marked this task as done:\n  ";
    /* The chatbot notification to the user for marking a task as done. */
    public static final String UNMARK_RESPONSE = "Nice! I've marked this task as done:\n  ";

    public MarkCommand(String[] commandList, String type) {
        this.commandList = commandList;
        this.type = type;
    }
    public String execute(TaskList taskList, Ui ui, Storage storage) throws MarkInvalidException {
        String markMessage;

        if (Objects.equals(this.type, "mark")) {
            markMessage = MARK_RESPONSE;
        } else {
            markMessage = UNMARK_RESPONSE;
        }

        try {
            if (this.commandList.length <= 1) {
                throw new MarkInvalidException(this.type);
            }

            int index = Integer.parseInt(commandList[1].replaceAll("\\s", ""));
            boolean isZeroIndex = index < 1;
            boolean isIndexOutOfBounds = index > taskList.size();
            if (isZeroIndex || isIndexOutOfBounds) {
                throw new MarkInvalidException(this.type);
            }

            Task currentTask = taskList.get(index - 1);
            currentTask.changeDone();

            markMessage += currentTask;

            storage.save(taskList);
        } catch (NumberFormatException e) {
            throw new MarkInvalidException(this.type);
        } catch (SaveStorageException e) {
            markMessage = ui.showError(e.getMessage());
        }
        return markMessage;
    }

    public boolean isExit() {
        return false;
    }
}
