package chatbot;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import chatbot.exceptions.InvalidArgumentException;
import chatbot.exceptions.PlanaException;

/**
 * Represents an enum of the valid user commands.
 */
public enum Command {
    EXIT("bye", false),
    LIST("list", false),
    MARK("mark", true),
    UNMARK("unmark", true),
    DELETE("delete", true),
    TODO("todo", true),
    DEADLINE("deadline", true),
    EVENT("event", true),
    FIXED("fixed", true),
    FIND("find", true);

    private final String rep;
    private final boolean hasArgs;
    private String args = "";

    /**
     * Constructor that initialises a user command.
     *
     * @param cmd     The string input representing the command.
     * @param hasArgs Whether the command accepts additional arguments.
     */
    Command(String cmd, boolean hasArgs) {
        this.rep = cmd.toLowerCase();
        this.hasArgs = hasArgs;
    }

    public boolean hasArgs() {
        return this.hasArgs;
    }

    /**
     * Populates the command with arguments provided.
     * @param args The arguments.
     */
    public void withArgs(String args) {
        assert this.hasArgs;
        this.args = args;
    }

    @Override
    public String toString() {
        return this.rep;
    }

    /**
     * Executes the command for the provided task list.
     *
     * @param tl The task list object to operate on.
     * @return The messages to display.
     */
    public Response execute(TaskList tl) {
        Response r = null;
        try {
            switch (this) {
            case EXIT:
                r = Response.displayParting();
                break;
            case LIST:
                r = Response.displayList(tl);
                break;
            case TODO:
            case DEADLINE:
            case EVENT:
            case FIXED:
                r = executeAdd(tl);
                break;
            case MARK:
            case UNMARK:
            case DELETE:
                r = executeIndexing(tl);
                break;
            case FIND:
                r = executeFind(tl);
                break;
            default:
            }

        } catch (PlanaException e) {
            r = Response.displayError(e);
        }
        return r;
    }

    /**
     * Executes a command with arguments for the provided task list.
     *
     * @param tl The task list object to operate on.
     * @throws PlanaException If an error occurs with the execution.
     */
    private Response executeAdd(TaskList tl) throws PlanaException {
        Task t = null;
        Pattern pattern;
        Matcher matcher;
        switch (this) {
        case TODO:
            t = new TodoTask(args.strip());
            break;
        case DEADLINE:
            pattern = Pattern.compile("(.+?)\\s+/by\\s+(.+)");
            matcher = pattern.matcher(args);

            if (!matcher.find()) {
                throw new InvalidArgumentException();
            }
            t = new DeadlineTask(matcher.group(1).strip(), matcher.group(2).strip());
            break;
        case FIXED:
            pattern = Pattern.compile("(.+?)\\s+/for\\s+(.+)");
            matcher = pattern.matcher(args);

            if (!matcher.find()) {
                throw new InvalidArgumentException();
            }
            t = new FixedTask(matcher.group(1).strip(), matcher.group(2).strip());
            break;
        case EVENT:
            pattern = Pattern.compile("(.+?)\\s+/from\\s+(.+?)\\s+/to\\s+(.+)");
            matcher = pattern.matcher(args);

            if (!matcher.find()) {
                throw new InvalidArgumentException();
            }
            t = new EventTask(matcher.group(1).strip(), matcher.group(2).strip(), matcher.group(3).strip());
            break;
        default:
            break;
        }
        tl.addTask(t);
        return Response.displayAdd(tl, t);
    }

    /**
     * Executes a command with only index for the provided task list.
     *
     * @param tl The task list object to operate on.
     * @throws PlanaException If an error occurs with the execution.
     */
    private Response executeIndexing(TaskList tl) throws PlanaException {
        int i;
        try {
            i = Integer.parseInt(args);
        } catch (NumberFormatException e) {
            throw new InvalidArgumentException();
        }

        if (i < 1 || i > tl.getSize()) {
            throw new InvalidArgumentException();
        }

        Response r = Response.displayError(new InvalidArgumentException());
        switch (this) {
        case MARK:
            tl.markTask(i - 1);
            r = Response.displayMark(i);
            break;
        case UNMARK:
            tl.unmarkTask(i - 1);
            r = Response.displayUnmark(i);
            break;
        case DELETE:
            Task removed = tl.removeTask(i - 1);
            r = Response.displayDelete(tl, removed);
            break;
        default:
            break;
        }
        return r;
    }

    private Response executeFind(TaskList tl) {
        ArrayList<Task> res = tl.filterByString(args.strip());
        return Response.displayFind(res);
    }
}
