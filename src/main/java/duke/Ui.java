package duke;

import java.util.ArrayList;
import java.util.List;

/**
* The Ui class manages the user interface for the Duke chatbot application.
*
* Provides methods for interacting with the user via stdin and stdout, displaying
* messages, and processing user commands.
*
* @see TaskList
*/
public class Ui {

    private static final String MESSAGE_DELINEATOR =
        "______________________________________________________";
    private static final String GREET_FORMAT =
        "Hello! I'm %s! What can I do for you?";
    private static final String BYE_MESSAGE = "Bye. Hope to see you again soon!";
    private static final String MARK_MESSAGE =
        "Nice! I've marked this task as done:";
    private static final String UNMARK_MESSAGE =
        "OK, I've marked this task as not done yet:";
    private static final String LIST_MESSAGE = "Here are the tasks in your list:";
    private static final String TODO_MESSAGE = "Got it. I've added this task:";
    private static final String DELETE_MESSAGE = "Noted. I've removed this task:";
    private static final String FIND_MESSAGE =
        "Here are the matching tasks in your list:";
    private static final String TASKS_SUMMARY_MESSAGE =
        "Now you have %s tasks in the list.";

    private static final String NAME = ">uwu<";

    private List<String> buffer;

    /**
    * Constructs a Ui object, initializing the scanner for user input.
    */
    public Ui() {
        buffer = new ArrayList<String>();
    }

    void greet() {
        reply(String.format(GREET_FORMAT, NAME));
    }

    void error(String msg) {
        reply(String.format("OOPS!! %s", msg));
    }

    /**
    * Handles user commands and performs actions on the task list.
    *
    * @param tasks     The task list on which the command actions are performed.
    * @param command   The command string indicating the action to be performed.
    * @param arguments An array of strings representing the arguments for the command.
    * @return {@code true} if the chatbot should continue processing commands,
    *         {@code false} if it should exit.
    * @throws DukeException
    */
    boolean handleCommand(TaskList tasks, String command, String[] arguments)
            throws DukeException {
        switch (command) {
        case "end":
            bye();
            return false;
        case "list":
            reply(LIST_MESSAGE);
            for (int idx = 0; idx < tasks.numberOfTask(); idx++) {
                reply(String.format("  %d.%s", idx + 1, tasks.peekTask(idx)));
            }
            return true;
        case "mark":
        case "unmark": {
            boolean isMark = command.equals("mark");
            String ferr2 = "%s command: no such task numbered %s.";
            String idxString = arguments[0];
            int idx = Integer.parseInt(idxString) - 1;
            if (!tasks.checkTaskIdx(idx)) {
                throw new DukeException(
                    String.format(ferr2, command, idxString)
                );
            }
            tasks.setDone(idx, isMark);
            reply(isMark ? MARK_MESSAGE : UNMARK_MESSAGE);
            reply(String.format("  %s", tasks.peekTask(idx)));
            return true;
        }
        case "todo": {
            String taskStr = arguments[0];
            Task task = TaskList.createTodo(taskStr, false);
            tasks.addTask(task);
            reply(TODO_MESSAGE);
            reply(String.format("  %s", task));
            reply(String.format(TASKS_SUMMARY_MESSAGE, tasks.numberOfTask()));
            return true;
        }
        case "deadline": {
            String taskStr = arguments[0];
            String deadline = arguments[1];
            Task task = TaskList.createDeadline(taskStr, deadline, false);
            tasks.addTask(task);
            reply(TODO_MESSAGE);
            reply(String.format("  %s", task));
            reply(String.format(TASKS_SUMMARY_MESSAGE, tasks.numberOfTask()));
            return true;
        }
        case "event": {
            String taskStr = arguments[0];
            String fromStr = arguments[1];
            String toStr = arguments[2];
            Task task = TaskList.createEvent(taskStr, fromStr, toStr, false);
            tasks.addTask(task);
            reply(TODO_MESSAGE);
            reply(String.format("  %s", task));
            reply(String.format(TASKS_SUMMARY_MESSAGE, tasks.numberOfTask()));
            return true;
        }
        case "delete": {
            String ferr2 = "%s command: no such task numbered %s.";
            String idxString = arguments[0];
            int idx = Integer.parseInt(idxString) - 1;
            if (!tasks.checkTaskIdx(idx)) {
                throw new DukeException(
                    String.format(ferr2, command, idxString)
                );
            }
            Task t = tasks.popTask(idx);
            reply(DELETE_MESSAGE);
            reply(String.format("  %s", t));
            reply(String.format(TASKS_SUMMARY_MESSAGE, tasks.numberOfTask()));
            return true;
        }
        case "find": {
            String query = arguments[0];
            reply(FIND_MESSAGE);
            int[] count = { 1 };
            tasks
                .getStoredTasks()
                .stream()
                .filter(t -> t.toString().contains(query))
                .forEach(t -> reply(String.format("  %d.%s", count[0]++, t)));
            return true;
        }
        default:
            throw new DukeException(
                String.format("Unhandled command: %s", command)
            );
        }
    }

    private void reply(String s) {
        buffer.add(s);
    }

    /**
     * Returns all buffered replies and clear buffer
     * @return
     */
    public String flushBuffer() {
        String ret = String.join("\n", buffer);
        buffer.removeIf(t -> true);
        return ret;
    }

    private void bye() {
        reply(BYE_MESSAGE);
    }
}
