package naruto;

import java.io.IOException;
import java.util.LinkedList;

import action.Action;
import action.Greet;
import action.HandleError;
import exception.NarutoException;
import util.PrintUtil;
import util.TaskList;
import util.Ui;

/**
 * The Naruto class represents the main class of the Naruto application.
 * It manages the list of actions to be performed and handles exceptions.
 */
public class Naruto {
    private static LinkedList<Action> actions = new LinkedList<>();
    private static TaskList taskList;

    static {
        try {
            taskList = new TaskList();
        } catch (IOException e) {
            PrintUtil.print(NarutoException.createFileCorruptedException().getMessage());
        }
    }

    /**
     * Constructs a Naruto object and initializes it with a Greet action.
     */
    public Naruto() {
        actions.add(new Greet());
    }

    /**
     * Parses user input and adds the corresponding action to the list of actions.
     */
    public static void listen() {
        actions.add(Ui.parseInput(taskList));
    }

    /**
     * Executes the first action in the list of actions.
     * If an IOException occurs, it handles the exception by adding a HandleError action to the list.
     */
    public static void act() {
        try {
            actions.pollFirst().execute();
        } catch (IOException e) {
            handleException(NarutoException.createInvalidActionException());
        }
    }

    /**
     * Checks if there is a next action in the list of actions.
     * @return true if there is a next action, false otherwise.
     */
    public static boolean hasNextAction() {
        return !actions.isEmpty();
    }

    /**
     * Handles the given NarutoException by adding a HandleError action to the list of actions.
     * @param err the NarutoException to handle.
     */
    public static void handleException(NarutoException err) {
        actions.addLast(new HandleError(err));
    }
}
