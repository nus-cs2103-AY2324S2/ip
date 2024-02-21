package wis.util;

import java.util.ArrayList;

import wis.Action;
import wis.task.Task;
import wis.task.TaskList;

/**
 * Contains static methods dealing with printing output. To fit with the GUI, methods
 * in this class will return the message to be printed, instead of printing messages
 * directly.
 */
public class Printer {
    public static final String DECORATOR = "    ____________________________________________________________\n";

    public static String getLine(String string) {
        return ("     " + string + "\n");
    }

    public static String getLineFurtherIndent(String string) {
        return ("       " + string + "\n");
    }

    /**
     * Prints message displayed to users after performing an action.
     */
    public static String getMessageActionAttach(Action action, Task task, TaskList tasks) {
        StringBuilder sb = new StringBuilder();
        switch (action) {
        case ADD_TODO:
        case ADD_DEADLINE:
        case ADD_EVENT:
            sb.append(DECORATOR);
            sb.append(Printer.getLine("Got it. I've added this task:\n"));
            sb.append(Printer.getLineFurtherIndent(task.toString()));
            sb.append(tasks.printTaskCount());
            sb.append(DECORATOR);
            return sb.toString();
        case DELETE:
            sb.append(DECORATOR);
            sb.append(Printer.getLine("Noted. I've removed this task:"));
            sb.append(Printer.getLineFurtherIndent(task.toString()));
            sb.append(tasks.printTaskCount());
            sb.append(DECORATOR);
            return sb.toString();
        default:
            throw new IllegalArgumentException("Illegal action argument provided.\n");
        }
    }

    /**
     * Prints message displayed to users after performing an action.
     */
    public static String getMessageActionAttach(Action action, Task task) {
        StringBuilder sb = new StringBuilder();
        switch (action) {
        case MARK:
            sb.append(DECORATOR);
            sb.append(Printer.getLine("Nice! I've marked this task as done:"));
            sb.append(Printer.getLineFurtherIndent(task.toString()));
            sb.append(DECORATOR);
            return sb.toString();
        case UNMARK:
            sb.append(DECORATOR);
            sb.append(Printer.getLine("OK, I've marked this task as not done yet:"));
            sb.append(Printer.getLineFurtherIndent(task.toString()));
            sb.append(DECORATOR);
            return sb.toString();
        default:
            throw new IllegalArgumentException("Illegal action argument provided.\n");
        }
    }

    /**
     * Prints message displayed to users after performing an action.
     */
    public static String getMessageActionAttach(Action action, TaskList tasks) {
        StringBuilder sb = new StringBuilder();
        if (action != Action.LIST) {
            throw new IllegalArgumentException("Illegal action argument provided.\n");
        }
        sb.append(DECORATOR);
        sb.append(Printer.getLine("Here are the tasks in your list:"));
        sb.append(tasks.print());
        sb.append(DECORATOR);
        return sb.toString();
    }

    /**
     * Prints message displayed to users after performing an action.
     */
    public static String getMessageActionAttach(Action action) {
        StringBuilder sb = new StringBuilder();
        if (action != Action.GREET) {
            throw new IllegalArgumentException("Illegal action argument provided.\n");
        }
        sb.append(DECORATOR);
        sb.append(Printer.getLine("Hello! I'm Wis."));
        sb.append(Printer.getLine("What can I do for you?"));
        sb.append(DECORATOR);
        return sb.toString();
    }

    /**
     * Prints message displayed to users after performing an action.
     */
    public static String getMessageActionAttach(Action action, ArrayList<Pair<Integer, Task>> matchingTasks) {
        StringBuilder sb = new StringBuilder();
        if (action != Action.FIND) {
            throw new IllegalArgumentException("Illegal action argument provided.\n");
        }
        sb.append(DECORATOR);
        sb.append(Printer.getLine("Here are the matching tasks in your list:"));
        for (Pair<Integer, Task> pair : matchingTasks) {
            sb.append(Printer.getLineFurtherIndent(pair.first + ". " + pair.second.toString()));
        }
        sb.append(DECORATOR);
        return sb.toString();
    }

    /**
     * Prints message displayed to user when there is nothing to undo.
     */
    public static String printNothingToUndo() {
        StringBuilder sb = new StringBuilder();
        sb.append(DECORATOR);
        sb.append(Printer.getLine("There is nothing to undo."));
        sb.append(DECORATOR);
        return sb.toString();
    }

    /**
     * Prints message displayed to users when an add command is successfully undone.
     */
    public static String printUndoAdd(Task task) {
        StringBuilder sb = new StringBuilder();
        sb.append(DECORATOR);
        sb.append(Printer.getLine("Undo successful. The following task has been removed:"));
        sb.append(Printer.getLineFurtherIndent(task.toString()));
        sb.append(DECORATOR);
        return sb.toString();
    }

    /**
     * Prints message displayed to users when a mark command is successfully undone.
     */
    public static String printUndoMark() {
        StringBuilder sb = new StringBuilder();
        sb.append(DECORATOR);
        sb.append(Printer.getLine("Undo mark successful."));
        sb.append(DECORATOR);
        return sb.toString();
    }

    /**
     * Prints message displayed to users when an unmark command is successfully undone.
     */
    public static String printUndoUnmark() {
        StringBuilder sb = new StringBuilder();
        sb.append(DECORATOR);
        sb.append(Printer.getLine("Undo unmark successful."));
        sb.append(DECORATOR);
        return sb.toString();
    }

    /**
     * Prints message displayed to users when a delete command is successfully undone.
     */
    public static String printUndoDelete(Task task) {
        StringBuilder sb = new StringBuilder();
        sb.append(DECORATOR);
        sb.append(Printer.getLine("Undo delete successful. The following task is put back:"));
        sb.append(Printer.getLineFurtherIndent(task.toString()));
        sb.append(DECORATOR);
        return sb.toString();
    }
}


