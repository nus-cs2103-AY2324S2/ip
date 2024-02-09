package duke.exceptions;

/**
 * An exception that is thrown when the order of dates are wrong.
 * e.g. Date D1 that is supposed to be after D2 is actually before D2 instead.
 */
public class DukeWrongDateOrderException extends DukeException {
    public DukeWrongDateOrderException() {}
}
