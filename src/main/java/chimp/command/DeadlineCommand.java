package chimp.command;

import java.time.LocalDate;

import chimp.core.Storage;
import chimp.core.TaskList;
import chimp.core.Ui;
import chimp.exception.CommandExecuteException;

/**
 * Represents a command to add a deadline task.
 * Inherits from the Command class.
 */
public class DeadlineCommand extends Command {
    String text;
    LocalDate date;

    /**
     * Constructs a DeadlineCommand object with the given text and date.
     * @param text The description of the deadline task.
     * @param date The deadline date of the task.
     */
    public DeadlineCommand(String text, LocalDate date) {
        this.text = text;
        this.date = date;
    }

    /**
     * Checks if the command is an exit command.
     * Overrides the isExit() method in the Command class.
     * @return Always returns false for DeadlineCommand.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the deadline command.
     * Adds the deadline task to the task list.
     * @param tasks The task list.
     * @param ui The user interface.
     * @param storage The storage for saving tasks.
     * @return The response message after executing the command.
     * @throws CommandExecuteException If there is an error executing the command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws CommandExecuteException {
        tasks.add(text, date);
        return ui.say(tasks.get(tasks.size() - 1), tasks);
    }
}
