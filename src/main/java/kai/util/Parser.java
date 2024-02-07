package kai.util;

import kai.task.Task;
import kai.task.Todo;
import kai.task.Deadline;
import kai.task.TaskList;
import kai.task.Event;

public class Parser {
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
            char pos = inputCommand.charAt(5);
            int index = Integer.parseInt(String.valueOf(pos));
            result = tasklist.markTask(index);
        } else if (inputCommand.startsWith("unmark")) {
            char pos = inputCommand.charAt(7);
            int index = Integer.parseInt(String.valueOf(pos));
            result = tasklist.unmarkTask(index);
        } else if (inputCommand.startsWith("todo")) {
            try {
                String des = inputCommand.substring(5);
                Task todo = new Todo(des);
                result = tasklist.addTask(todo);
            } catch (StringIndexOutOfBoundsException e) {
                DukeException error = new DukeException(e);
                result = error.toString();
            }
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
            char pos = inputCommand.charAt(7);
            int index = Integer.parseInt(String.valueOf(pos));
            result = tasklist.deleteTask(index);
        } else if (inputCommand.startsWith("find")) {
            String keyword = inputCommand.substring(5);
            result = tasklist.findTask(keyword);
        } else {
            result = "OOPS!!! I'm sorry, but I don't know what that means :-(";
        }

        return result;
    }
}
