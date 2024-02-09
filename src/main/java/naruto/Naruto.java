package naruto;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Objects;

import action.Action;
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
     * Constructs a Naruto object.
     */
    public Naruto() {

    }

    /**
     * Parses user input and adds the corresponding action to the list of actions.
     */
    public static void listen(String input) {
        actions.add(Ui.parseInput(taskList, input));
    }

    /**
     * Executes the first action in the list of actions.
     * If an IOException occurs, it handles the exception by adding a HandleError action to the list.
     *
     * @return the result of the executed action
     */
    public static String act() {
        try {
            Action action = Objects.requireNonNull(actions.pollFirst());
            assert action != null : "Action cannot be null";
            return action.execute();
        } catch (IOException e) {
            handleException(NarutoException.createInvalidActionException());
        }
        return null;
    }

    /**
     * Handles the given NarutoException by adding a HandleError action to the list of actions.
     *
     * @param err the NarutoException to handle.
     */
    public static void handleException(NarutoException err) {
        actions.addLast(new HandleError(err));
    }

    /**
     * Generates Naruto's response to user input.
     */
    public String getResponse(String input) {
        Naruto.listen(input);
        return Naruto.act();
    }
}
