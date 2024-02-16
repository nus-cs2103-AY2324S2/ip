package bytebuddy.commands;

import bytebuddy.exceptions.ByteBuddyException;
import bytebuddy.storage.Storage;
import bytebuddy.tasks.TaskList;
import bytebuddy.ui.Ui;


/**
 * Interface that represents a Command to be executed by the Bytebuddy chatbot.
 * A command typically performs an action on the task list,
 * interacts with the user interface, and may involve storage operations.
 * Implementing classes must provide an implementation for the {@code execute} method.
 *
 * @see bytebuddy.tasks.TaskList
 * @see bytebuddy.ui.Ui
 * @see bytebuddy.storage.Storage
 */
public interface Command {

    /**
     * Executes the command, affecting the provided task list, user interface, and storage.
     *
     * @param taskList   The task list on which the command operates.
     * @param ui      The user interface for displaying information to the user.
     * @param storage The storage for saving and loading data.
     * @return String result output after executing instruction given
     * @throws ByteBuddyException If an error occurs during the execution of the command.
     */
    String execute(TaskList taskList, Ui ui, Storage storage) throws ByteBuddyException;

    /**
     * Checks if the command is an exit command,
     * indicating whether the chatbot should exit after executing this command.
     *
     * @return {@code true} if the command is an exit command, {@code false} otherwise.
     */
    boolean isExit();
}
