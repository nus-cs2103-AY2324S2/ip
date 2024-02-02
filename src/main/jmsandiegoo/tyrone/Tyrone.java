package jmsandiegoo.tyrone;

import jmsandiegoo.tyrone.commands.ByeCommand;
import jmsandiegoo.tyrone.commands.Command;
import jmsandiegoo.tyrone.commands.CommandResult;
import jmsandiegoo.tyrone.exceptions.CommandExecutionException;
import jmsandiegoo.tyrone.exceptions.IncorrectCommandException;
import jmsandiegoo.tyrone.exceptions.StorageHelperException;
import jmsandiegoo.tyrone.parser.Parser;
import jmsandiegoo.tyrone.task.TaskList;
import jmsandiegoo.tyrone.ui.Ui;

/**
 * Represents the entry point of the application, the main class.
 */
public class Tyrone {
    private final Ui UI;
    private final TaskList taskList;

    /**
     * The main entry method of the application.
     *
     * @param args - to pass to the main method.
     */
    public static void main(String[] args) {
        new Tyrone().run();
    }

    public Tyrone() {
        this.UI = new Ui();
        this.taskList = new TaskList();
    }

    public void run() {
        start();
        runUntilExit();
        System.exit(0);
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
            this.UI.outputWelcomeMessage();
            taskList.loadTaskListFromFile();
        } catch (StorageHelperException e) {
            this.UI.outputFailedInitMessage();
            throw new RuntimeException(e);
        }
    }

    /**
     * Queries user input and handles it repeatedly until bye command.
     */
    public void runUntilExit() {
        boolean isActive = true;
        while (isActive) {
            try {
                String rawUserCommand = this.UI.getRawUserCommand();
                Command command = new Parser().parseRawUserCommand(rawUserCommand);
                command.initData(this.taskList);
                CommandResult result = command.execute();
                this.UI.outputResultToUser(result);
                if (command instanceof ByeCommand) {
                    isActive = false;
                }
                taskList.saveTaskListToFile();
            } catch (IncorrectCommandException | CommandExecutionException e) {
                this.UI.outputExceptionToUser(e);
            } catch (StorageHelperException e) {
                this.UI.outputExceptionToUser(e);
                throw new RuntimeException(e);
            }
        }
    }
}
