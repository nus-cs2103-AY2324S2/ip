package podz;

import podz.commands.ByeCommand;
import podz.commands.Command;
import podz.exceptions.PodzException;
import podz.parser.Parser;
import podz.storage.Storage;
import podz.task.TaskList;

/**
 * Represents the entry of a task management application.
 */
public class Podz {
    private Storage storage;
    private TaskList tasks;
    private Parser parser;
    private boolean isExit;

    /**
     * Constructs a Podz object.
     */
    public Podz() {
        storage = new Storage("./data/podz.txt");
        tasks = new TaskList(this.storage.loadTasks());
        parser = new Parser();
        this.isExit = false;
    }

    /**
     * Generates a response to user input.
     *
     * @param input the user input.
     * @return the response generated based on the input.
     */
    public String getResponse(String input) {
        try {
            Command command = getCommand(input);
            String responseStr = executeCommand(command);
            checkIfByeCommand(command);
            return responseStr;
        } catch (Exception e) {
            return e.getMessage();
        }
    }


    private String executeCommand(Command command) throws PodzException {
        return command.execute();
    }


    private void checkIfByeCommand(Command command) {
        if (command instanceof ByeCommand) {
            this.isExit = true;
        }
    }


    private Command getCommand(String input) {
        Command command = this.parser.parseCommand(input);
        assert command != null : "command should not be null";
        command.setTasks(this.tasks);
        return command;
    }

    /**
     * Returns a greeting message.
     *
     * @return a greeting message.
     */
    public String getGreeting() {
        return "Hello there! I'm Podz, your friendly robot assistant.\n"
                + "How can I assist you today?";
    }

    /**
     * Checks if the application should exit.
     *
     * @return true if the application should exit, false otherwise.
     */
    public boolean hasExit() {
        return this.isExit;
    }
}