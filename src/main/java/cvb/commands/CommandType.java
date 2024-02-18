package cvb.commands;

import java.util.ArrayList;

import cvb.exceptions.ConvoBotException;
import cvb.tasks.Deadline;
import cvb.tasks.Event;
import cvb.tasks.ToDo;
import cvb.utils.DateTime;

/**
 * Represents the types of commands available in the ConvoBot application.
 */
public enum CommandType {

    /**
     * Command type to exit the application.
     */
    BYE {
        @Override
        public Command getCommand(ArrayList<String> args) throws ConvoBotException {
            enforceArgumentCount(args, 1);
            return new Bye();
        }
    },

    /**
     * Command type to list tasks.
     */
    LIST {
        @Override
        public Command getCommand(ArrayList<String> args) throws ConvoBotException {
            enforceArgumentCount(args, 1);
            return new List();
        }
    },

    /**
     * Command type to mark a task as done.
     */
    MARK {
        @Override
        public Command getCommand(ArrayList<String> args) throws ConvoBotException {
            enforceArgumentCount(args, 2);
            return new Mark(getIndex(args));
        }
    },

    /**
     * Command type to unmark a previously marked task.
     */
    UNMARK {
        @Override
        public Command getCommand(ArrayList<String> args) throws ConvoBotException {
            enforceArgumentCount(args, 2);
            return new Unmark(getIndex(args));
        }
    },

    /**
     * Command type to delete a task.
     */
    DELETE {
        @Override
        public Command getCommand(ArrayList<String> args) throws ConvoBotException {
            enforceArgumentCount(args, 2);
            return new Delete(getIndex(args));
        }
    },

    /**
     * Command type to add a simple to-do task.
     */
    TODO {
        @Override
        public Command getCommand(ArrayList<String> args) throws ConvoBotException {
            int n = args.size();
            if (n < 2) {
                throw new ConvoBotException("Invalid input. The description of a todo cannot be empty.");
            }
            String description = String.join(" ", args.subList(1, n));
            return new Add(new ToDo(description));
        }
    },

    /**
     * Command type to add a task with a deadline.
     */
    DEADLINE {
        @Override
        public Command getCommand(ArrayList<String> args) throws ConvoBotException {
            int n = args.size();
            int j = args.indexOf("/by");
            if (j == 1) {
                throw new ConvoBotException("Invalid input. The description of a deadline cannot be empty.");
            }
            if (j == -1 || j == n - 1) {
                throw new ConvoBotException("Invalid input. End date missing.");
            }
            String description = String.join(" ", args.subList(1, j));
            String by = String.join(" ", args.subList(j + 1, n));
            return new Add(new Deadline(description, DateTime.stringToDate(by)));
        }
    },

    /**
     * Command type to add an event task.
     */
    EVENT {
        @Override
        public Command getCommand(ArrayList<String> args) throws ConvoBotException {
            int n = args.size();
            int j = args.indexOf("/from");
            int k = args.indexOf("/to");
            if (j == 1 || k == 1) {
                throw new ConvoBotException("Invalid input. The description of an event cannot be empty.");
            }
            if (j == -1 || j == n - 1 || args.get(j + 1).equals("/to")) {
                throw new ConvoBotException("Invalid input. Start date missing.");
            }
            if (k == -1 || k == n - 1) {
                throw new ConvoBotException("Invalid input. End date missing.");
            }
            String description = String.join(" ", args.subList(1, j));
            String from = String.join(" ", args.subList(j + 1, k));
            String to = String.join(" ", args.subList(k + 1, n));
            return new Add(new Event(description, DateTime.stringToDate(from), DateTime.stringToDate(to)));
        }
    },

    /**
     * Command type to find matching tasks.
     */
    FIND {
        @Override
        public Command getCommand(ArrayList<String> args) throws ConvoBotException {
            int n = args.size();
            if (n < 2) {
                throw new ConvoBotException("Invalid input. Wrong number of arguments.");
            }
            String query = String.join(" ", args.subList(1, n));
            return new Find(query);
        }
    };

    private static void enforceArgumentCount(ArrayList<String> args, int n) throws ConvoBotException {
        if (args.size() != n) {
            throw new ConvoBotException("Invalid input. Wrong number of arguments.");
        }
    }

    private static int getIndex(ArrayList<String> args) throws ConvoBotException {
        try {
            return Integer.parseInt(args.get(1)) - 1;
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new ConvoBotException("Invalid input. Wrong number format or index.");
        }
    }

    /**
     * Parses user input args and returns the corresponding {@code Command} object.
     *
     * @param args the user input ArrayList to be parsed
     * @return the corresponding {@code Command} object
     * @throws ConvoBotException if the input is invalid or cannot be parsed
     */
    abstract Command getCommand(ArrayList<String> args) throws ConvoBotException;
}
