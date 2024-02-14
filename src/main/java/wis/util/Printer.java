package wis.util;

import java.util.ArrayList;

import wis.Action;
import wis.task.Task;
import wis.task.TaskList;

/**
 * Contains static methods dealing with printing output.
 */
public class Printer {
    public static final String DECORATOR = "    ____________________________________________________________\n";

    public static String println(String string) {
        return ("     " + string + "\n");
    }

    public static String printlnFurtherIndent(String string) {
        return ("       " + string + "\n");
    }

    /**
     * Prints message displayed to users after performing an action.
     */
    public static String printActionAttach(Action action, Task task, TaskList tasks) {
        StringBuilder sb = new StringBuilder();
        switch (action) {
        case ADD_TODO:
        case ADD_DEADLINE:
        case ADD_EVENT:
            sb.append(DECORATOR);
            sb.append(Printer.println("Got it. I've added this task:\n"));
            sb.append(Printer.printlnFurtherIndent(task.toString()));
            sb.append(tasks.printTaskCount());
            sb.append(DECORATOR);
            return sb.toString();
        case DELETE:
            sb.append(DECORATOR);
            sb.append(Printer.println("Noted. I've removed this task:"));
            sb.append(Printer.printlnFurtherIndent(task.toString()));
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
    public static String printActionAttach(Action action, Task task) {
        StringBuilder sb = new StringBuilder();
        switch (action) {
        case MARK:
            sb.append(DECORATOR);
            sb.append(Printer.println("Nice! I've marked this task as done:"));
            sb.append(Printer.printlnFurtherIndent(task.toString()));
            sb.append(DECORATOR);
            return sb.toString();
        case UNMARK:
            sb.append(DECORATOR);
            sb.append(Printer.println("OK, I've marked this task as not done yet:"));
            sb.append(Printer.printlnFurtherIndent(task.toString()));
            sb.append(DECORATOR);
            return sb.toString();
        default:
            throw new IllegalArgumentException("Illegal action argument provided.\n");
        }
    }

    /**
     * Prints message displayed to users after performing an action.
     */
    public static String printActionAttach(Action action, TaskList tasks) {
        StringBuilder sb = new StringBuilder();
        if (action == Action.LIST) {
            sb.append(DECORATOR);
            sb.append(Printer.println("Here are the tasks in your list:"));
            sb.append(tasks.print());
            sb.append(DECORATOR);
            return sb.toString();
        } else {
            throw new IllegalArgumentException("Illegal action argument provided.\n");
        }
    }

    /**
     * Prints message displayed to users after performing an action.
     */
    public static String printActionAttach(Action action) {
        StringBuilder sb = new StringBuilder();
        switch (action) {
        case BYE:
            sb.append(DECORATOR);
            sb.append(Printer.println("Bye. Hope to see you again soon!"));
            sb.append(DECORATOR);
            return sb.toString();
        case GREET:
            sb.append(DECORATOR);
            sb.append(Printer.println("Hello! I'm Wis."));
            sb.append(Printer.println("What can I do for you?"));
            sb.append(DECORATOR);
            return sb.toString();
        default:
            throw new IllegalArgumentException("Illegal action argument provided.\n");
        }
    }


    public static String printActionAttach(Action action, ArrayList<Pair<Integer, Task>> matchingTasks) {
        StringBuilder sb = new StringBuilder();
        if (action == Action.FIND) {
            sb.append(DECORATOR);
            sb.append(Printer.println("Here are the matching tasks in your list:"));
            for (Pair<Integer, Task> pair : matchingTasks) {
                sb.append(Printer.printlnFurtherIndent(pair.first + ". " + pair.second.toString()));
            }
            sb.append(DECORATOR);
            return sb.toString();
        } else {
            throw new IllegalArgumentException("Illegal action argument provided.\n");
        }
    }
}


