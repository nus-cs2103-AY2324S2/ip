package earl.util.parsers;

import earl.exceptions.EarlException;

/**
 * Class responsible for interpreting storage entries.
 */
public abstract class StorageParser<T> implements Parser<T> {

    /**
     * Returns a new {@code Task} object based on stored entry.
     *
     * @param entry           a line of text stored in the data file
     * @return                a {@code Task} object of the relevant type
     * @throws EarlException  if stored entry is of unexpected format
     */
    public abstract T parse(String entry) throws EarlException;
}
