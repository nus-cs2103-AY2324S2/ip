package earl.tasks;

import earl.exceptions.EarlException;

public class TaskFactory {

    /**
     * Factory method to create {@code Task} object.
     *
     * @param args            details of task
     * @return                a {@code Task} object
     * @throws EarlException  if arguments are incomprehensible
     */
    public static Task of(String... args) throws EarlException {
        // break unnecessary as all paths return or throw
        switch (args[0]) {
        case "T":
            return new Todo(args[1], args[2]);
        case "D":
            return new Deadline(args[1], args[2], args[3]);
        case "E":
            return new Event(args[1], args[2], args[3], args[4]);
        default:
            throw new EarlException("Unknown arguments for task creation.");
        }
    }
}
