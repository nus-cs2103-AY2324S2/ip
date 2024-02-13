package earl.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import earl.exceptions.EarlException;
import earl.util.TaskList;
import earl.util.parsers.IntervalParser;

/**
 * Abstract class representing handlers of mass operable commands.
 */
public abstract class MassOperableHandler extends Handler {

    protected final List<String> modifiedItems = new ArrayList<>();

    /** Class constructor. */
    public MassOperableHandler(String args) {
        super(args);
    }

    /**
     * Returns an array of unique valid indices in reverse sorted order.
     *
     * @param tasks           a {@code TaskList} object
     * @param args            user input arguments
     * @return                an array of valid indices
     * @throws EarlException  if the user input is incomprehensible
     */
    protected Integer[] getValidIndices(TaskList tasks,
                                      String args) throws EarlException {
        return IntervalParser.parse(args)
                .filter((idx) -> 0 <= idx && idx < tasks.getSize())
                .toArray(Integer[]::new);
    }

    protected void addDisplayEntry(String entry) {
        modifiedItems.add(entry);
    }

    protected String[] getDisplay() {
        Collections.reverse(modifiedItems);
        String[] result = modifiedItems.toArray(String[]::new);
        modifiedItems.clear();
        return result;
    }
}
