package simpli.core;

import java.io.IOException;

import simpli.commands.base.CommandResult;
import simpli.commands.base.CommandWord;
import simpli.configs.Config;
import simpli.exceptions.CommandException;
import simpli.exceptions.TaskException;
import simpli.interpreter.Interpreter;
import simpli.parser.Parser;
import simpli.storage.Storage;
import simpli.tasks.TaskList;

/**
 * Main chatbot.
 */
public class Simpli {
    private final TaskList taskList;
    private final Parser parser;
    private final Interpreter intrpr;
    private final Storage storage;

    /**
     * Initializes the chatbot and its components.
     */
    public Simpli() {
        this.taskList = new TaskList();
        this.parser = new Parser();
        this.intrpr = new Interpreter(taskList);
        this.storage = new Storage(parser, intrpr, taskList);
    }

    /**
     * Starts running the chatbot.
     */
    public void start() {
        try {
            storage.loadTasksFromFile(Config.DATAPATH);
        } catch (TaskException e) {
            System.out.println("Error in task.");
        } catch (CommandException e) {
            System.out.println("Error in actions");
        } catch (IOException e) {
            System.out.println("Error in opening file.");
        }
    }

    /**
     * Stops the chatbot and perform saving of tasks before quitting.
     */
    public void stop() {
        try {
            storage.saveTasksToFile(Config.DATAPATH);
        } catch (IOException e) {
            System.out.println("Error in opening file.");
        }
    }

    /**
     * Returns a goodbye message.
     *
     * @return goodbye message.
     */
    public String bye() {
        return "Bye. Hope to simp for you again soon!";
    }

    public TaskList getTaskList() {
        return taskList;
    }

    public Storage getStorage() {
        return storage;
    }

    /**
     * Processes the user input string from start to end.
     *
     * @param input User input String.
     * @return resposne String.
     */
    public CommandResult processInput(String input) {
        try {
            String[] tokens = parser.parseCommand(input);
            return intrpr.interpret(tokens);
        } catch (TaskException e) {
            return new CommandResult(
                    CommandWord.INVALID,
                    "Invalid task parameters, cannot simp :("
            );
        } catch (IndexOutOfBoundsException e) {
            return new CommandResult(
                    CommandWord.INVALID,
                    "Please enter a valid task number for me to simp :("
            );
        } catch (CommandException e) {
            return new CommandResult(
                    CommandWord.INVALID,
                    "No such action to simp for :("
            );
        }
    }

    /**
     * Returns the chatbot String representation.
     *
     * @return String representation of the chatbot.
     */
    @Override
    public String toString() {
        return "SIMP-LI";
    }
}
