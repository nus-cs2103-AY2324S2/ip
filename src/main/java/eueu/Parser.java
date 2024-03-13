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
    static final String BLANK_TASK = "ENTER VALID TASK BABES";

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
            res = list();
        } else if (command.equals("hey") || command.equals("hi")) {
            res = welcome();
        } else if (command.equals("clear list")) {
            res = clearList();
        } else if (command.equals("bye")) {
            System.exit(0);
        } else if (command.startsWith("mark")) {
            res = mark(command);
        } else if (command.startsWith("unmark")) {
            res = unmark(command);
        } else if (command.startsWith("delete")) {
            res = delete(command);
        } else if(command.startsWith("find")) {
            res = find(command);
        } else {
            try {
                if (command.startsWith("todo")) {
                    res = addTodo(command);
                } else if (command.startsWith("deadline")) {
                    res = addDeadline(command);
                } else if (command.startsWith("event")) {
                    res = addEvent(command);
                } else {
                    res = NON_COMMAND_RESPONSE;
                }
            } catch (StringIndexOutOfBoundsException e){
                res = INVALID_TASK_RESPONSE;
            }
        }
        return res;
    }


    /**
     * Adds a new Todo task with the specified description.
     *
     * @param command the command to add a todo task
     * @return a message indicating the result of the add operation
     * @throws IOException if an I/O error occurs while adding the task
     */
    public String addTodo(String command) throws IOException {
        String str = command.substring(5);
        if (str.isEmpty()) {
            return BLANK_TASK;
        } else {
            Todo todo = new Todo(str);
            return tasklist.addTask(todo);
        }
    }

    /**
     * Adds a new Deadline task with the specified description and deadline.
     *
     * @param command the command to add a deadline task
     * @return a message indicating the result of the add operation
     */
    public String addDeadline(String command) {
        try {
            String str = command.substring(9);
            if (str.isEmpty()) {
                return BLANK_TASK;
            } else {
                String[] arr = str.split("/");
                String c = arr[0];
                String ddl = arr[1].substring(3);
                Deadline deadline = new Deadline(ddl, c);
                return tasklist.addTask(deadline);
            }
        } catch (ArrayIndexOutOfBoundsException | IOException e) {
            return INVALID_DEADLINE_DDL;
        }
    }

    /**
     * Adds a new event task with the specified description, start, and end timings.
     *
     * @param command the command to add an event task
     * @return a message indicating the result of the add operation
     */
    public String addEvent(String command) {
        try {
            String str = command.substring(6);
            if (str.isEmpty()) {
                return BLANK_TASK;
            } else {
                String[] arr = str.split("/");
                String c = arr[0];
                String start = arr[1].substring(5);
                String end = arr[2].substring(3);
                Event event = new Event(start, end, c);
                return tasklist.addTask(event);
            }
        } catch(ArrayIndexOutOfBoundsException | IOException e) {
            return INVALID_EVENT_TIMINGS;
        }
    }

    /**
     * Deletes the task with the specified index.
     *
     * @param command the command to delete a task
     * @return a message indicating the result of the delete operation
     */
    public String delete(String command) {
        try {
            String str = command.substring(7);
            int number = Integer.parseInt(str) - 1;
            return tasklist.delete(number);
        } catch (StringIndexOutOfBoundsException | NumberFormatException | IOException e) {
            return INVALID_TASK_DELETE;
        }
    }

    /**
     * Unmarks the task with the specified index.
     *
     * @param command the command to unmark a task
     * @return a message indicating the result of the unmark operation
     */
    public String unmark(String command) {
        try {
            String str = command.substring(7);
            int number = Integer.parseInt(str) - 1;
            return tasklist.unmark(number);
        } catch (StringIndexOutOfBoundsException | NumberFormatException | IOException e) {
            return INVALID_TASK_UNMARK;
        }
    }

    /**
     * Marks the task with the specified index.
     *
     * @param command the command to mark a task
     * @return a message indicating the result of the mark operation
     */
    public String mark(String command) {
        try {
            String str = command.substring(5);
            int number = Integer.parseInt(str) - 1;
            return tasklist.mark(number);
        } catch (StringIndexOutOfBoundsException | NumberFormatException | IOException e) {
            return INVALID_TASK_MARK;
        }
    }

    /**
     * Finds tasks with the specified keyword in their description.
     *
     * @param command the command to find tasks
     * @return a message containing the list of found tasks
     */
    public String find(String command) {
        try {
            String str = command.substring(5);
            return tasklist.find(str);
        } catch (StringIndexOutOfBoundsException e) {
            return INVALID_TASK_FIND;
        }
    }

    /**
     * Clears the task list.
     *
     * @return a message indicating the result of clearing the list
     * @throws IOException if an I/O error occurs while clearing the list
     */
    public String clearList() throws IOException {
        tasklist.clearTasklist();
        return tasklist.clearList();
    }

    /**
     * Generates a welcome message along with the list of tasks.
     *
     * @return a welcome message with the list of tasks
     */
    public String welcome() {
        String res = tasklist.list() + "\n";
        res += WELCOME_MESSAGE;
        return res;
    }

    /**
     * Lists all tasks.
     *
     * @return a string containing the list of tasks
     */
    public String list() {
        return tasklist.list();
    }
}
