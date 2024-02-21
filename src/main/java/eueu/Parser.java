package eueu;

import eueu.task.Deadline;
import eueu.task.Event;
import eueu.task.Todo;

import java.io.IOException;

/**
 * Represents a parser that parses user's String input
 * to a command for TaskList to execute.
 */

public class Parser {
private TaskList tasklist;

    public Parser(TaskList tasklist) {
        this.tasklist = tasklist;
    }

    public Parser(){}

    static final String WELCOME_MESSAGE = "Hi babyyy! It's your EUEU!! \n"
                                            + "What are you doing today??";
    static final String EXIT_MESSAGE = "byeee love uu ttyl ok!";
    static final String NON_COMMAND_RESPONSE = "Baby, what are you saying? " +
                                                "Tell me what your TODOs, DEADLINEs and EVENTs are!";
    static final String INVALID_TASK_RESPONSE = "ENTER TASK";
    static final String INVALID_TASK_MARK = "Enter task to mark done: e.g. mark 1";
    static final String INVALID_TASK_UNMARK = "Enter task to unmark: e.g. unmark 1";
    static final String INVALID_TASK_DELETE = "Enter task to delete: e.g. delete 1";
    static final String INVALID_TASK_FIND = "What are you finding babe?";
    static final String INVALID_DEADLINE_DDL = "Let me know your deadlines babe: e.g. deadline <deadline> /by <ddl>";
    static final String INVALID_EVENT_TIMINGS = "Let me know when this event is bb: " +
                                                "e.g. event <event> /from <when>/to <when>";

    /**
     * Parses user's String input to commands that TaskList executes.
     * (i.e. mark, unmark, delete, find, clear list, list, todo, deadline, event).
     *
     * @param command String input from user.
     * @throws StringIndexOutOfBoundsException When user does not specify the task to do after a command (e.g. todo).
     * @throws NumberFormatException When user does not leave a space between command and number (e.g. mark1).
     * @throws ArrayIndexOutOfBoundsException When user does not specify dates of deadline/event.
     * @throws IOException When file cannot be found.
     * @return ChatBot's reply to user input.
     */

    public String parsing(String command) throws StringIndexOutOfBoundsException, NumberFormatException,
            ArrayIndexOutOfBoundsException, IOException {
        assert command != null : "Command should not be null";
        assert tasklist != null : "TaskList should not be null";
        String res = "";
        if (command.equals("list")) {
            res = tasklist.list();
        } else if (command.equals("hey") || command.equals("hi")) {
            res = tasklist.list() + "\n";
            res += WELCOME_MESSAGE;
        } else if (command.equals("clear list")) {
            tasklist.clearTasklist();
            res = tasklist.clearList();
        } else if (command.equals("bye")) {
            res = EXIT_MESSAGE;
        } else if (command.startsWith("mark")) {
            try {
                String str = command.substring(5);
                int number = Integer.parseInt(str) - 1;
                res =  tasklist.mark(number);
            } catch (StringIndexOutOfBoundsException | NumberFormatException e) {
                res = INVALID_TASK_MARK;
            }
        } else if (command.startsWith("unmark")) {
            try {
                String str = command.substring(7);
                int number = Integer.parseInt(str) - 1;
                res = tasklist.unmark(number);
            } catch (StringIndexOutOfBoundsException | NumberFormatException e) {
                res = INVALID_TASK_UNMARK;
            }
        } else if (command.startsWith("delete")) {
            try {
                String str = command.substring(7);
                int number = Integer.parseInt(str) - 1;
                res = tasklist.delete(number);
            } catch (StringIndexOutOfBoundsException | NumberFormatException e) {
                res = INVALID_TASK_DELETE;
            }
        } else if(command.startsWith("find")) {
            try {
                String str = command.substring(5);
                res = tasklist.find(str);
            } catch (StringIndexOutOfBoundsException e) {
                res = INVALID_TASK_FIND;
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
                        res = INVALID_DEADLINE_DDL;
                    }
                } else if (command.startsWith("event")) {
                    try {
                        String str = command.substring(8);
                        String[] arr = str.split("/");
                        String c = arr[0];
                        String start = arr[1].substring(5);
                        String end = arr[2].substring(3);
                        Event event = new Event(start, end, c);
                        res = tasklist.addTask(event);
                    } catch(ArrayIndexOutOfBoundsException e) {
                        res = INVALID_EVENT_TIMINGS;
                    }
                } else {
                    res = NON_COMMAND_RESPONSE;
                }
            } catch (StringIndexOutOfBoundsException e){
                res = INVALID_TASK_RESPONSE;
            }
        }
        return res;
    }
}
