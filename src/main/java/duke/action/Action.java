package duke.action;

import duke.exception.DuplicateIndexException;

import java.util.HashSet;
import java.util.Set;

/**
 * An interface representing an action in the Duke application.
 */
public interface Action {

    /**
     * Gets the response associated with the action.
     *
     * @return A string representing the response of the action.
     */
    String response();

    static int[] parseIndices(String[] indicesString) {
        int[] indices = new int[indicesString.length];
        for (int i = 0; i < indicesString.length; i++) {
            indices[i] = Integer.parseInt(indicesString[i]) - 1;
        }
        return indices;
    }

    static void checkForDuplicateIndices(int[] indices) throws DuplicateIndexException {
        Set<Integer> indexSet = new HashSet<>();
        for (int index : indices) {
            if (indexSet.contains(index)) {
                throw new DuplicateIndexException();
            }
            indexSet.add(index);
        }
    }

    /**
     * Checks if the action represents an exit command.
     *
     * @return {@code true} if the action is an exit command, {@code false} otherwise.
     */
    default boolean isExit() {
        return false;
    }
}
