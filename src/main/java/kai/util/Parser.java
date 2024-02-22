package kai.util;

import kai.task.Task;
import kai.task.Todo;
import kai.task.Deadline;
import kai.task.TaskList;
import kai.task.Event;
import kai.ui.Ui;

public class Parser {
    private static final String INDEX_ERROR = "OOPS!!! I'm sorry, but that task does not exist :-(";
    private static final String UNKNOWN_ERROR = "OOPS!!! I'm sorry, but I don't know what that means :-(";
    private static final String MISSING_DATE_ERROR = "OOPS!!! I'm sorry, but the date cannot be missing for this tasks :-(";
    private static final String DESCRIPTION_ERROR = "OOPS!!! I'm sorry, but description cannot be empty :-(";
    private static final String UNKNOWN_INPUT_MESSAGE = "OOPS!!! I'm sorry, but I don't know what that means :-(";

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
    public static String parse(String inputCommand, TaskList tasklist) throws DukeException {
        String result= "";
        try {
            if (inputCommand.equals("list")) {
                result = tasklist.listDownTask();
            } else if (inputCommand.startsWith("mark") || inputCommand.startsWith("unmark")) {
                result = changeTaskDoneHelper(inputCommand, tasklist, result);
            } else if (inputCommand.startsWith("todo") || inputCommand.startsWith("deadline") || inputCommand.startsWith("event")) {
                result = addTaskCommandHelper(inputCommand, tasklist, result);
            } else if (inputCommand.startsWith("delete")) {
                String pos = inputCommand.substring(DELETE_COMMAND_INDEX);
                int index = Integer.parseInt(String.valueOf(pos));
                result = tasklist.deleteTask(index);
            } else if (inputCommand.startsWith("find")) {
                String keyword = inputCommand.substring(FIND_COMMAND_INDEX);
                result = tasklist.findTask(keyword);
            } else if (inputCommand.startsWith("help")) {
                result = Ui.showHelp();
            } else {
                result = UNKNOWN_INPUT_MESSAGE;
            }
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException(DESCRIPTION_ERROR);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException(MISSING_DATE_ERROR);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(INDEX_ERROR);
        } catch (NumberFormatException e) {
            throw new DukeException(UNKNOWN_ERROR);
        }
        return result;
    }

    /**
     * Parses input when a task of type todo, deadline, event is added to the tasklist
     *
     * @param inputCommand User input from terminal
     * @param tasklist Current TaskList
     * @param result String that will be returned from the method
     * @return String output when the command executes
     */
    private static String addTaskCommandHelper(String inputCommand, TaskList tasklist, String result) {
        if (inputCommand.startsWith("todo")) {
            String des = inputCommand.substring(TODO_COMMAND_INDEX);
            Task todo = new Todo(des);
            result = tasklist.addTask(todo);
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
        }
        return result;
    }

    /**
     * Helps to update the isDone status of the specific task in the input
     *
     * @param inputCommand User input from terminal
     * @param tasklist Current TaskList
     * @param result String that will be returned from the method
     * @return String output when the command executes
     */
    private static String changeTaskDoneHelper(String inputCommand, TaskList tasklist, String result) {
        if (inputCommand.startsWith("mark")) {
            String pos = inputCommand.substring(MARK_COMMAND_INDEX);
            int index = Integer.parseInt(String.valueOf(pos));
            result = tasklist.markTask(index);
        } else if (inputCommand.startsWith("unmark")) {
            String pos = inputCommand.substring(UNMARK_COMMAND_INDEX);
            int index = Integer.parseInt(String.valueOf(pos));
            result = tasklist.unmarkTask(index);
        }
        return result;
    }
}
