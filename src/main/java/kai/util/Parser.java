package kai.util;

import kai.task.Task;
import kai.task.Todo;
import kai.task.Deadline;
import kai.task.TaskList;
import kai.task.Event;

public class Parser {
    private static final String INDEX_ERROR = "OOPS!!! I'm sorry, but that task does not exist :-(";
    private static final String UNKNOWN_ERROR = "OOPS!!! I'm sorry, but I don't know what that means :-(";
    private static final String FORMAT_ERROR = "OOPS!!! I'm sorry, but that is the wrong date format. Try yyyy-MM-dd!";
    private static final String MISSING_DATE_ERROR = "OOPS!!! I'm sorry, but the date cannot be missing for this tasks :-(";
    private static final String DESCRIPTION_ERROR = "OOPS!!! I'm sorry, but description cannot be empty :-(";

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
            } else if (inputCommand.startsWith("mark")) {
                String pos = inputCommand.substring(5);
                int index = Integer.parseInt(String.valueOf(pos));
                result = tasklist.markTask(index);
            } else if (inputCommand.startsWith("unmark")) {
                String pos = inputCommand.substring(7);
                int index = Integer.parseInt(String.valueOf(pos));
                result = tasklist.unmarkTask(index);
            } else if (inputCommand.startsWith("todo")) {
                String des = inputCommand.substring(5);
                Task todo = new Todo(des);
                result = tasklist.addTask(todo);
            } else if (inputCommand.startsWith("deadline")) {
                String[] separate = inputCommand.substring(9).split("\\|");
                for (int i = 0; i < separate.length; i++) {
                    separate[i] = separate[i].trim();
                }
                Task deadline = new Deadline(separate[0], separate[1]);
                result = tasklist.addTask(deadline);
            } else if (inputCommand.startsWith("event")) {
                String[] separate = inputCommand.substring(6).split("\\|");
                for (int i = 0; i < separate.length; i++) {
                    separate[i] = separate[i].trim();
                }
                Task event = new Event(separate[0], separate[1]);
                result = tasklist.addTask(event);
            } else if (inputCommand.startsWith("delete")) {
                String pos = inputCommand.substring(7);
                int index = Integer.parseInt(String.valueOf(pos));
                //assert index > ZERO_INDEX : "There should be no zero index";
                result = tasklist.deleteTask(index);
            } else if (inputCommand.startsWith("find")) {
                String keyword = inputCommand.substring(5);
                result = tasklist.findTask(keyword);
            } else {
                result = "OOPS!!! I'm sorry, but I don't know what that means :-(";
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
}
