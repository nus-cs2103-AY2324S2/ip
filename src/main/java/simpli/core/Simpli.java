package simpli.core;

import java.io.IOException;

import simpli.configs.SimpliConfiguration;
import simpli.exceptions.ActionException;
import simpli.exceptions.TaskException;
import simpli.interpreter.Interpreter;
import simpli.parser.Parser;
import simpli.storage.Storage;
import simpli.tasks.TaskList;

/**
 * Main chatbot.
 */
public class Simpli {
    private TaskList taskList;
    private Parser parser;
    private Interpreter intrpr;
    private Storage storage;

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
            storage.loadTasksFromFile(SimpliConfiguration.DATAPATH);
        } catch (TaskException e) {
            System.out.println("Error in task.");
        } catch (ActionException e) {
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
            storage.saveTasksToFile(SimpliConfiguration.DATAPATH);
        } catch (IOException e) {
            System.out.println("Error in opening file.");
        }
    }

    /**
     * Returns a greeting message.
     *
     * @return goodbye message.
     */
    public String greet() {
        return "Hello! I'm SIMP-LI\n"
            + "How can I simp-lify your life?";
    }

    /**
     * Returns a goodbye message.
     *
     * @return goodbye message.
     */
    public String bye() {
        return "Bye. Hope to simp for you again soon!";
    }

    /**
     * Processes the user input string from start to end.
     *
     * @param input User input String.
     * @return resposne String.
     */
    public String processInput(String input) {
        try {
            String[] tokens = parser.parseCommand(input);
            return intrpr.interpret(tokens);
        } catch (TaskException e) {
            return "Invalid task parameters, cannot simp :(";
        } catch (IndexOutOfBoundsException e) {
            return "Please enter a valid task number for me to simp :(";
        } catch (ActionException e) {
            return "No such action to simp for :(";
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
