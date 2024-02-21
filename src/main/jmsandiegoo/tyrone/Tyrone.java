package jmsandiegoo.tyrone;

import jmsandiegoo.tyrone.commands.*;
import jmsandiegoo.tyrone.common.Messages;
import jmsandiegoo.tyrone.exceptions.CommandExecutionException;
import jmsandiegoo.tyrone.exceptions.IncorrectCommandException;
import jmsandiegoo.tyrone.exceptions.StorageHelperException;
import jmsandiegoo.tyrone.parser.Parser;
import jmsandiegoo.tyrone.state.StateManager;

/**
 * Represents the entry point of the application, the main class.
 */
public class Tyrone {
    private final StateManager stateManager;
    private boolean isExit;

    public Tyrone() {
        this.stateManager = new StateManager();
        this.isExit = false;
    }

    /**
     * Initializes the application such as loading the tasklist from storage
     * and displaying the welcome message.
     * If file io operations fails it throws RuntimeException(e).
     *
     * @throws RuntimeException - runtime exception.
     * */
    public void start() {
        try {
            this.stateManager.getTaskList().loadTaskListFromFile();
        } catch (StorageHelperException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Queries user input and handles it repeatedly until bye command.
     * Set's class instance isExit property to true if bye command is processed.
     *
     * @param rawUserCommand - the raw user input from the gui.
     * @return String.
     */
    public String processUserCommand(String rawUserCommand) {
        try {
            Command command = new Parser().parseRawUserCommand(rawUserCommand);
            command.initData(this.stateManager.getTaskList());
            CommandResult result = command.execute();
            assert result != null : "result should not be null";

            if (command instanceof UndoCommand) {
                result = result.chain(this.stateManager.popCommandFromStack());
            }

            if (command instanceof UndoableCommand) {
                this.stateManager.addCommandToStack((UndoableCommand) command);
            }

            if (command instanceof ByeCommand) {
                this.isExit = true;
            }

            this.stateManager.getTaskList().saveTaskListToFile();
            return this.replyCommandResult(result);
        } catch (IncorrectCommandException | CommandExecutionException e) {
            return this.replyErrorOccurred(e);
        } catch (StorageHelperException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns if tyrone should terminate / exit the program. So to exit the application.
     *
     * @return boolean.
     */
    public boolean checkIfExit() {
        return this.isExit;
    }

    /**
     * Returns greeting message to the user.
     *
     * @return String.
     */
    public String greetUser() {
        return this.generateMessageToUser(Messages.MESSAGE_GREET);
    }

    /**
     * Returns fail init message to the user.
     *
     * @return String.
     */
    public String notifyFailToInit() {
        return this.generateMessageToUser(Messages.MESSAGE_INITIALIZE_FAIL);
    }

    /**
     * Returns exception message to the user.
     *
     * @param e - the exception that contains the message.
     * @return String.
     */
    public String replyErrorOccurred(Exception e) {
        return this.generateMessageToUser(
                String.format(Messages.MESSAGE_ERROR, e.getMessage()));
    }

    /**
     * Returns reply message to the user after successfully
     * executing user command.
     *
     * @param commandResult - the result returned of the command execution.
     * @return String.
     */
    public String replyCommandResult(CommandResult commandResult) {
        return this.generateMessageToUser(commandResult.toString());
    }

    /**
     * Returns concatenated list of message strings.
     *
     * @param messages - list of messages strings that would be concatenated.
     * @return String.
     */
    public String generateMessageToUser(String... messages) {
        StringBuilder outputStr = new StringBuilder();
        for (String m : messages) {
            outputStr.append(m);
        }

        return outputStr.toString();
    }
}
