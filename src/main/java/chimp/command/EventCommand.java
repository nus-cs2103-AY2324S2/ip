package chimp.command;

import java.time.LocalDate;

import chimp.core.Storage;
import chimp.core.TaskList;
import chimp.core.Ui;
import chimp.exception.CommandExecuteException;

/**
 * Represents a command to add an event task to the task list.
 * Inherits from the Command class.
 */
public class EventCommand extends Command {
    String text;
    LocalDate fromDate;
    LocalDate toDate;

    /**
     * Constructs an EventCommand object with the specified text, from date, and to date.
     *
     * @param text     The description of the event task.
     * @param fromDate The starting date of the event.
     * @param toDate   The ending date of the event.
     */
    public EventCommand(String text, LocalDate fromDate, LocalDate toDate) {
        this.text = text;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    /**
     * Checks if the command is an exit command.
     * 
     * @return true if the command is an exit command, false otherwise.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the event command, adding a new event task to the task list.
     * 
     * @param tasks   The task list to add the event task to.
     * @param ui      The user interface to display messages.
     * @param storage The storage to save the updated task list.
     * @return The message to be displayed to the user.
     * @throws CommandExecuteException If there is an error executing the command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws CommandExecuteException {
        tasks.add(text, fromDate, toDate);
        return ui.say(tasks.get(tasks.size() - 1), tasks);
    }

}
