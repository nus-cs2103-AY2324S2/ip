package earl.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import earl.exceptions.EarlException;
import earl.util.TaskList;
import earl.util.parsers.IntervalParser;

/**
 * Interface representing a user command that can act on multiple entries.
 */
public interface MassOperable {

    List<String> MODIFIED_ITEMS = new ArrayList<>();

    /**
     * Returns an array of unique valid indices in reverse sorted order.
     *
     * @param tasks           a {@code TaskList} object
     * @param args            user input arguments
     * @return                an array of valid indices
     * @throws EarlException  if the user input is incomprehensible
     */
    default Integer[] getValidIndices(TaskList tasks,
                                      String args) throws EarlException {
        return IntervalParser.parse(args)
                .filter((idx) -> 0 <= idx && idx < tasks.getSize())
                .toArray(Integer[]::new);
    }

    default void addDisplayEntry(String entry) {
        MODIFIED_ITEMS.add(entry);
    }

    default String[] getDisplay() {
        Collections.reverse(MODIFIED_ITEMS);
        String[] result = MODIFIED_ITEMS.toArray(String[]::new);
        MODIFIED_ITEMS.clear();
        return result;
    }
}
