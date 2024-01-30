package chatbot;

import chatbot.exceptions.DukeException;
import chatbot.exceptions.InvalidArgumentException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Command {
    EXIT("bye"),
    LIST("list"),
    MARK("mark"),
    UNMARK("unmark"),
    DELETE("delete"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event");

    private final String rep;
    private String args = "";
    private boolean isBreaking;


    Command(String cmd) {
        this.rep = cmd.toLowerCase();
        this.isBreaking = this.rep.equals("bye");
    }

    public void withArgs(String args) {
        this.args = args;
    }

    @Override
    public String toString() {
        return this.rep + this.args;
    }

    public boolean shouldExit() {
        return this.isBreaking;
    }

    public void execute(Ui view, TaskList tl) throws DukeException {
        switch (this) {
            case LIST:
                view.displayList(tl);
                break;
            case TODO:
            case DEADLINE:
            case EVENT:
                executeFlagged(view, tl);
                break;
            case MARK:
            case UNMARK:
            case DELETE:
                executeIndexing(view, tl);
                break;
        }
    }

    private void executeFlagged(Ui view, TaskList tl) throws DukeException {
        Task t = new Task("");
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
            case EVENT:
                pattern = Pattern.compile("(.+?)\\s+/from\\s+(.+?)\\s+/to\\s+(.+)");
                matcher = pattern.matcher(args);

                if (!matcher.find()) {
                    throw new InvalidArgumentException();
                }
                t = new EventTask(matcher.group(1).strip(), matcher.group(2).strip(), matcher.group(3).strip());
                break;
        }
        tl.addTask(t);
        view.displayAdd(tl, t);
    }

    private void executeIndexing(Ui view, TaskList tl) throws DukeException {
        int i;
        try {
            i = Integer.parseInt(args);
        } catch (NumberFormatException e) {
            throw new InvalidArgumentException();
        }

        if (i < 1 || i > tl.getSize()) throw new InvalidArgumentException();

        switch (this) {
            case MARK:
                tl.markTask(i - 1);
                view.displayMark(i);
                break;
            case UNMARK:
                tl.unmarkTask(i - 1);
                view.displayUnmark(i);
                break;
            case DELETE:
                Task removed = tl.removeTask(i - 1);
                view.displayDelete(tl, removed);
                break;
        }
    }
}
