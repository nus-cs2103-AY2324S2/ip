package kai.util;

import kai.task.Task;
import kai.task.Todo;
import kai.task.Deadline;
import kai.task.TaskList;
import kai.task.Event;

public class Parser {
    private static final int MARK_COMMAND_INDEX = 5;
    private static final int UNMARK_COMMAND_INDEX = 7;
    private static final int TODO_COMMAND_INDEX = 5;
    private static final int DEADLINE_COMMAND_INDEX = 9;
    private static final int EVENT_COMMAND_INDEX = 6;
    private static final int DELETE_COMMAND_INDEX = 7;
    private static final int FIND_COMMAND_INDEX = 5;
    private static final int DEADLINE_EVENT_DESCRIPTION = 0;
    private static final int DEADLINE_EVENT_ISDONE = 1;
    /**
     * Reads user input of tasks and parse it into the current TaskList.
     *
     * @param inputCommand User input from terminal.
     * @param tasklist Current TaskList.
     */
    public static String parse(String inputCommand, TaskList tasklist) {
        String result= "";
        if (inputCommand.equals("list")) {
            result = tasklist.listDownTask();
        } else if (inputCommand.startsWith("mark")) {
            String pos = inputCommand.substring(MARK_COMMAND_INDEX);
            int index = Integer.parseInt(String.valueOf(pos));
            result = tasklist.markTask(index);
        } else if (inputCommand.startsWith("unmark")) {
            String pos = inputCommand.substring(UNMARK_COMMAND_INDEX);
            int index = Integer.parseInt(String.valueOf(pos));
            result = tasklist.unmarkTask(index);
        } else if (inputCommand.startsWith("todo")) {
            try {
                String des = inputCommand.substring(TODO_COMMAND_INDEX);
                Task todo = new Todo(des);
                result = tasklist.addTask(todo);
            } catch (StringIndexOutOfBoundsException e) {
                DukeException error = new DukeException(e);
                result = error.toString();
            }
        } else if (inputCommand.startsWith("deadline")) {
            String[] separate = inputCommand.substring(DEADLINE_COMMAND_INDEX).split("\\|");
            for (int i = 0; i < separate.length; i++) {
                separate[i] = separate[i].trim();
            }
            Task deadline = new Deadline(separate[DEADLINE_EVENT_DESCRIPTION], separate[DEADLINE_EVENT_ISDONE]);
            result = tasklist.addTask(deadline);
        } else if (inputCommand.startsWith("event")) {
            String[] separate = inputCommand.substring(EVENT_COMMAND_INDEX).split("\\|");
            for (int i = 0; i < separate.length; i++) {
                separate[i] = separate[i].trim();
            }
            Task event = new Event(separate[DEADLINE_EVENT_DESCRIPTION], separate[DEADLINE_EVENT_ISDONE]);
            result = tasklist.addTask(event);
        } else if (inputCommand.startsWith("delete")) {
            String pos = inputCommand.substring(DELETE_COMMAND_INDEX);
            int index = Integer.parseInt(String.valueOf(pos));
            result = tasklist.deleteTask(index);
        } else if (inputCommand.startsWith("find")) {
            String keyword = inputCommand.substring(FIND_COMMAND_INDEX);
            result = tasklist.findTask(keyword);
        } else {
            result = "OOPS!!! I'm sorry, but I don't know what that means :-(";
        }

        return result;
    }
}
