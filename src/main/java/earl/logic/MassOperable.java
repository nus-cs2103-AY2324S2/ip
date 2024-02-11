package earl.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import earl.exceptions.EarlException;
import earl.util.TaskList;
import earl.util.parsers.IntervalParser;

public interface MassOperable {

    List<String> affected = new ArrayList<>();

    default Integer[] getValidIndices(TaskList tasks,
                                      String args) throws EarlException {
        return IntervalParser.parse(args)
                .filter((idx) -> 0 <= idx && idx < tasks.getSize())
                .toArray(Integer[]::new);
    }

    default void addDisplayEntry(String entry) {
        affected.add(entry);
    }

    default String[] getDisplay() {
        Collections.reverse(affected);
        String[] result = affected.toArray(String[]::new);
        affected.clear();
        return result;
    }
}
