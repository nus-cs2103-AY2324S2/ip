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
    /** Contains all the tasks added by the user. */
    private final TaskList taskList;

    /** Break down command string into tokens. */
    private final Parser parser;

    /** Interprets the meaning of the tokens parsed from the parser. */
    private final Interpreter intrpr;

    /** Stores the tasks into a file */
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

    public TaskList getTaskList() {
        return taskList;
    }

    /**
     * Processes the user input string from start to end.
     *
     * @param input user input string.
     * @return resposne string.
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

    @Override
    public String toString() {
        return "SIMP-LI";
    }
}
