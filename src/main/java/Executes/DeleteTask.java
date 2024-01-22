package Executes;

import Actions.Action;
import Exceptions.DukeException;

import java.util.List;

public class DeleteTask {
    /**
     * No constructor needed
     */
    private DeleteTask() {
        throw new AssertionError("Constructor is not allowed");
    }

    /**
     * Execute Deleting tasks
     * @param input : User input
     * @param actionList
     * @throws DukeException
     */
    public static void exec(String input, List<Action> actionList) throws DukeException {
        try {
            String[] parts = input.split(" ", 2);
            int pos = Integer.parseInt(parts[1]);

            if (pos > actionList.size() || pos <= 0) {
                throw new DukeException("Invalid task number");
            }

            System.out.println("Noted. I've removed this tasks:");
            System.out.println(actionList.get(pos - 1));
            actionList.remove(pos - 1);
            System.out.println("Now you have " + actionList.size() + " tasks in the list.");

        } catch (ArrayIndexOutOfBoundsException err) {
            throw new DukeException("Please include index of task to be removed");
        }
    }
}
