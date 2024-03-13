package duke.command;

import duke.task.TaskList;
import duke.task.Event;

import duke.util.Ui;
import duke.util.Storage;

import duke.exception.MissingEventException;
import duke.exception.SaveStorageException;

/**
 * The class representing the creation of deadline task command.
 * */
public class EventCommand extends Command {
    /* The separated list of constituent words in the user-entered command. */
    private final String[] commandList;
    /* The first line of response to the user. */
    public static final String RESPONSE_ONE = "Got it. I've added this task:\n ";
    /* The second line of response to the user. */
    public static final String RESPONSE_TWO = "\nNow you have ";
    /* The third line of response to the user. */
    public static final String RESPONSE_THREE = " tasks in the list.";

    public EventCommand(String[] commandList) {
        this.commandList = commandList;
    }

    public String execute(TaskList taskList, Ui ui, Storage storage) throws MissingEventException {
        if (this.commandList.length <= 1) {
            throw new MissingEventException();
        }

        Event currentEvent = new Event(commandList[1], commandList[2], commandList[3]);
        taskList.add(currentEvent);

        String response = RESPONSE_ONE
                + currentEvent
                + RESPONSE_TWO
                + taskList.size()
                + RESPONSE_THREE;

        try {
            storage.save(taskList);
        } catch (SaveStorageException e) {
            response = ui.showError(e.getMessage());
        }

        return response;
    }

    public boolean isExit() {
        return false;
    }
}
