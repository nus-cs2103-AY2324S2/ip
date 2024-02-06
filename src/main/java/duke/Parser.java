package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.FileNotFoundException;

/**
 * Represents a parser that parses user's String input
 * to a command for TaskList to execute.
 */

public class Parser {
private TaskList tasklist;

    public Parser(TaskList tasklist) {
        this.tasklist = tasklist;
    }

    /**
     * Parses user's String input to commands that TaskList executes
     * (i.e. mark, unmark, delete, find, todo, deadline, event)
     *
     * @param command String input from user
     * @throws StringIndexOutOfBoundsException When user does not specify the task to do after a command (e.g. todo)
     * @throws NumberFormatException When user does not leave a space between command and number (e.g. mark1)
     * @throws ArrayIndexOutOfBoundsException When user does not specify dates of deadline/event
     * @throws FileNotFoundException When File f does not exist.
     */

    public String parsing(String command) throws StringIndexOutOfBoundsException, NumberFormatException,
            ArrayIndexOutOfBoundsException, FileNotFoundException {
        String res = "";
        if (command.equals("list")) {
            res = tasklist.list();
        } else if (command.equals("bye")) {
            res = "byeee love uu ttyl ok!";
        } else if (command.startsWith("mark")) {
            try {
                String str = command.substring(5);
                int number = Integer.parseInt(str) - 1;
                res =  tasklist.mark(number);
            } catch (StringIndexOutOfBoundsException e) {
                res = "Enter task to mark done: e.g. mark 1";
            }
            catch (NumberFormatException e) {
                res = "Enter task to mark done: e.g. mark 1";
            }
        } else if (command.startsWith("unmark")) {
            try {
                String str = command.substring(7);
                int number = Integer.parseInt(str) - 1;
                res = tasklist.unmark(number);
            } catch (StringIndexOutOfBoundsException e) {
                res = "Enter task to unmark: e.g. unmark 1";
            } catch (NumberFormatException e) {
                res = "Enter task to unmark: e.g. unmark 1";
            }
        } else if (command.startsWith("delete")) {
            try {
                String str = command.substring(7);
                int number = Integer.parseInt(str) - 1;
                res = tasklist.delete(number);
            } catch (StringIndexOutOfBoundsException e) {
                res = "Enter task to delete: e.g. delete 1";
            } catch (NumberFormatException e) {
                res = "Enter task to delete: e.g. delete 1";
            }
        } else if(command.startsWith("find")) {
            try {
                String str = command.substring(5);
                res = tasklist.find(str);
            } catch (StringIndexOutOfBoundsException e) {
                res = "What are you finding babe?";
            }
        } else {
            try {
                if (command.startsWith("todo")) {
                    String str = command.substring(5);
                    Todo todo = new Todo(str);
                    res = tasklist.addTask(todo);
                } else if (command.startsWith("deadline")) {
                    try {
                        String str = command.substring(9);
                        String[] arr = str.split("/");
                        String c = arr[0];
                        String ddl = arr[1].substring(3);
                        Deadline deadline = new Deadline(ddl, c);
                        res = tasklist.addTask(deadline);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        res = "Let me know your deadlines babe: e.g. deadline <deadline> /by <ddl>";
                    }
                } else if (command.startsWith("event")) {
                    try {
                        String str = command.substring(6);
                        String[] arr = str.split("/");
                        String c = arr[0];
                        String start = arr[1].substring(5);
                        String end = arr[2].substring(3);
                        Event event = new Event(start, end, c);
                        res = tasklist.addTask(event);
                    } catch(ArrayIndexOutOfBoundsException e) {
                        res = "Let me know when this event is bb: " +
                                "e.g. event <event> /from <when>/to <when>";
                    }
                } else {
                    res = "Baby, what are you saying? Tell me what your TODOs, DEADLINEs and EVENTs are!";
                }
            } catch (StringIndexOutOfBoundsException e){
                res = "ENTER INSTRUCTION";
            }
        }
        return res;
    }
}
