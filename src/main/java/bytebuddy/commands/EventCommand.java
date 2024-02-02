package bytebuddy.commands;

import bytebuddy.exceptions.ByteBuddyException;
import bytebuddy.storage.Storage;
import bytebuddy.tasks.TaskList;
import bytebuddy.ui.Ui;

/**
 * Command to add an event task to the task list based on the provided information.
 */
public class EventCommand implements Command {
    private String info;

    /**
     * Constructs an EventCommand with the provided information.
     *
     * @param info The information containing details of the event task to be added.
     */
    public EventCommand(String info) {
        this.info = info;
    }

    /**
     * Executes the EventCommand, adding an event task to the task list.
     *
     * @param taskList The task list to which the event task will be added.
     * @param ui       The user interface.
     * @param storage  The storage for saving and loading data.
     * @throws ByteBuddyException If there is an error executing the EventCommand.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws ByteBuddyException {
        return taskList.event(info);
    }

    /**
     * Checks if the EventCommand is an exit command, indicating that the chatbot should not exit.
     *
     * @return {@code false} as EventCommand is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
