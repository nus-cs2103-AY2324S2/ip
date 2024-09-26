package maltese.action;

import java.util.HashSet;
import java.util.Set;

import maltese.exception.DuplicateIndexException;

/**
 * An interface representing an action in the maltese application.
 */
public interface Action {

    /**
     * Gets the response associated with the action.
     *
     * @return A string representing the response of the action.
     */
    String getResponse();

    /**
     * Parses an array of strings representing indices into an array of integers.
     *
     * @param indicesString An array of strings representing indices.
     * @return An array of integers parsed from the indices strings.
     */
    static int[] parseIndices(String[] indicesString) {
        int[] indices = new int[indicesString.length];
        for (int i = 0; i < indicesString.length; i++) {
            indices[i] = Integer.parseInt(indicesString[i]) - 1;
        }
        return indices;
    }

    /**
     * Checks for duplicate indices in the provided array.
     *
     * @param indices An array of integers representing indices.
     * @throws DuplicateIndexException If duplicate indices are found.
     */
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
