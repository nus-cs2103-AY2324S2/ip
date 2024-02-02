package duke.command;

import duke.task.TaskList;

import duke.util.Ui;
import duke.util.Storage;

import duke.exception.DukeException;

/**
 * The abstract class representing a generic command.
 * */
public abstract class Command {

    /**
     * The generic method to which dispatches actions based on the implementing command.
     *
     * @param taskList The list of tasks for the current instance of the chatbot.
     * @param ui The user interaction object for the current instance of the chatbot.
     * @param storage The storage object for the current instance of the chatbot.
     * */
    public abstract String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

    /**
     * Getter method for isExit property which dictates if the programme should be exited.
     *
     * @return A boolean dictating if the programme should be exited.
     * */
    public abstract boolean isExit();
}
