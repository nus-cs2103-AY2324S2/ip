package simpli.core;

import simpli.actions.Action;
import simpli.configs.SimpliConfiguration;
import simpli.exceptions.ActionException;
import simpli.exceptions.TaskException;
import simpli.interpreter.Interpreter;
import simpli.parser.Parser;
import simpli.storage.Storage;
import simpli.tasks.TaskList;

import java.io.IOException;

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

        } catch (ActionException e) {

        } catch (IOException e) {

        }
    }

    public void stop() {
        try {
            storage.saveTasksToFile(SimpliConfiguration.DATAPATH);
        } catch (IOException e) {

        }
    }

    /**
     * Returns a greeting message.
     *
     * @return goodbye message.
     */
    public String greet() {
        return "Hello! I'm SIMP-LI\n" +
                "How can I simp-lify your life?";
    }

    /**
     * Returns a goodbye message.
     *
     * @return goodbye message.
     */
    public String bye() {
        return "Bye. Hope to simp for you again soon!";
    }

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
