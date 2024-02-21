
package jivox;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jivox.exception.JivoxDuplicateTaskException;
import jivox.exception.JivoxException;
import jivox.exception.JivoxInvalidDateException;
import jivox.exception.JivoxInvalidDateRangeException;
import jivox.exception.JivoxMissingArgumentException;
import jivox.exception.JivoxNoTaskFoundException;
import jivox.exception.JivoxUnknownCommandException;
import jivox.task.Deadline;
import jivox.task.Event;
import jivox.task.Tag;
import jivox.task.Task;
import jivox.task.TaskList;
import jivox.task.Todo;


/**
 * Jivox handles the core functionality of the to-do list application.
 */
public class Jivox {

    private final DatabaseHandler dbHandler;
    private final Ui ui;
    private final TaskList tasks;
    private final Parser parser;
    private boolean isRunning = true;



    /**
     * Creates a new Jivox instance with the given file path.
     *
     * @param filePath The path to the database file.
     */
    public Jivox(String filePath) {
        this.dbHandler = new DatabaseHandler(filePath);
        this.tasks = new TaskList(dbHandler.load());
        this.ui = new Ui();
        this.parser = new Parser();
    }

    /**
     * Marks the task at the given index as completed.
     *
     * @param i The index of the task to mark.
     * @throws JivoxNoTaskFoundException If index is invalid.
     */
    private String mark(int i) throws JivoxNoTaskFoundException {
        if (i > this.tasks.getLength() || i < 0) {
            throw new JivoxNoTaskFoundException(i);
        }
        assert i > 0;
        Task t = this.tasks.getTask(i - 1);
        t.mark();
        return this.ui.showMark(t);
    }

    /**
     * Unmarks the task at the given index as completed.
     *
     * @param i The index of the task to unmark.
     * @throws JivoxNoTaskFoundException If index is invalid.
     */
    private String unmark(int i) throws JivoxNoTaskFoundException {
        if (i > this.tasks.getLength() || i < 0) {
            throw new JivoxNoTaskFoundException(i);
        }
        assert i > 0;
        Task t = this.tasks.getTask(i - 1);
        t.unmark();
        return this.ui.showUnmark(t);
    }

    private void addEvent(String content) throws JivoxMissingArgumentException,
            JivoxInvalidDateRangeException, JivoxDuplicateTaskException, JivoxInvalidDateException {
        String[] first = this.parser.split(content, " /from ");
        if (first.length == 1) {
            throw new JivoxMissingArgumentException();
        }
        assert first.length > 1;
        String[] second = this.parser.split(first[1], " /to ", 2);
        if (second.length == 1) {
            throw new JivoxMissingArgumentException();
        }
        LocalDateTime from = parser.parseDateTime(second[0]);
        LocalDateTime to = parser.parseDateTime(second[1]);

        if (to.isBefore(from)) {
            throw new JivoxInvalidDateRangeException();
        }
        Event task = new Event(first[0].trim(), from, to);

        if (this.checkDuplicateTask(task)) {
            throw new JivoxDuplicateTaskException();
        } else {
            this.tasks.add(task);
        }
    }

    private void addTodo(String content) throws JivoxDuplicateTaskException {
        Todo task = new Todo(content);
        if (this.checkDuplicateTask(task)) {
            throw new JivoxDuplicateTaskException();
        } else {
            this.tasks.add(task);
        }
    }

    /**
     * Saves the tasks into the Local Database
     */
    public void saveDb() {
        try {
            this.dbHandler.save(this.tasks);
        } catch (JivoxException e) {
            this.ui.showException(e.toString());
        }
    }

    private void addDeadline(String content) throws JivoxMissingArgumentException,
            JivoxDuplicateTaskException, JivoxInvalidDateException {
        String[] in = this.parser.split(content, " /by ", 2);
        if (in.length == 1) {
            throw new JivoxMissingArgumentException();
        }
        LocalDateTime deadline = parser.parseDateTime(in[1]);
        Deadline task = new Deadline(in[0].trim(), deadline);
        if (this.checkDuplicateTask(task)) {
            throw new JivoxDuplicateTaskException();
        } else {
            this.tasks.add(task);
        }
    }


    private boolean checkDuplicateTask(Task t) {
        for (int i = 0; i < this.tasks.getLength(); i++) {
            if (this.tasks.getTask(i).equals(t)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds a new task of the given type and description.
     *
     * @param type        The type of task (todo, deadline, event).
     * @param description The task description.
     * @throws JivoxUnknownCommandException Unable to recognise the Command.
     * @throws JivoxMissingArgumentException If missing any arguement
     * @throws JivoxInvalidDateException If the date entered is not correct format
     * @throws JivoxInvalidDateRangeException If the range of date is not correct
     */
    public String add(String type, String description) throws JivoxUnknownCommandException,
            JivoxDuplicateTaskException, JivoxMissingArgumentException,
            JivoxInvalidDateRangeException, JivoxInvalidDateException {
        switch (type) {
        case "todo":
            addTodo(description);
            break;
        case "deadline":
            addDeadline(description);
            break;
        case "event":
            addEvent(description);
            break;
        default:
            throw new JivoxUnknownCommandException(type);
        }
        return this.ui.showAdd(this.tasks.getTask(this.tasks.getLength() - 1), this.tasks.getLength());
    }

    /**
     * Deletes the task at the given index.
     *
     * @param i The index of the task to delete.
     * @throws JivoxNoTaskFoundException If index is invalid.
     */
    public String delete(int i) throws JivoxNoTaskFoundException {
        if (i > this.tasks.getLength() || i < 0) {
            throw new JivoxNoTaskFoundException(i);
        }
        Task t = this.tasks.getTask(i - 1);
        this.tasks.delete(i - 1);
        return this.ui.showDelete(t, this.tasks.getLength());
    }

    /**
     * Shows tasks due on the given date.
     *
     * @param input The date to show tasks for.
     */
    public String handleShow(String input) throws JivoxInvalidDateException {
        String[] split = this.parser.split(input, "/on ");
        LocalDate time = parser.parseDate(split[1].replaceFirst(" ", ""));
        return this.ui.showDeadline(this.tasks, time);
    }

    public String find(String input) {
        return this.ui.showFind(this.tasks, input);
    }


    /**
     * Sets the tag for a given task with a keyword
     *
     * @param input The date to show tasks for.
     */
    public String tag(String input) throws JivoxNoTaskFoundException {
        String[] in = parser.parseInput(input);
        int taskNum = Integer.parseInt(in[0]) - 1;
        if(taskNum >= this.tasks.getLength() || taskNum < 0){
            throw new JivoxNoTaskFoundException(taskNum + 1);
        }
        Task t = this.tasks.getTask(taskNum);
        t.setTag(new Tag(in[1]));
        return this.ui.showTag(t, in[1]);
    }

    /**
     * Given input from the user generates the response
     *
     * @param rawInput the input from user
     */
    public String getResponse(String rawInput) {
        try {
            Commands type = parser.parseCommand(rawInput);
            String[] input = parser.parseInput(rawInput);
            return handleCommand(type, input);
        } catch (JivoxException e) {
            return this.ui.showException(e.toString());
        }
    }

    private void validateInput(Commands type, String[] input) throws JivoxMissingArgumentException {
        if(type != Commands.LIST && type != Commands.BYE && input.length == 1) {
            throw new JivoxMissingArgumentException();
        }
    }

    private String handleCommand(Commands type, String[] input) throws JivoxException {
        validateInput(type,input);
        switch (type) {
        case BYE:
            isRunning = false;
            this.ui.close();
            dbHandler.save(tasks);
            return ui.exit();
        case DEADLINE:
            return this.add("deadline", input[1]);
        case EVENT:
            return this.add("event", input[1]);
        case TODO:
            return this.add("todo", input[1]);
        case MARK:
            return this.mark(Integer.parseInt(input[1]));
        case UNMARK:
            return this.unmark(Integer.parseInt(input[1]));
        case DELETE:
            return this.delete(Integer.parseInt(input[1]));
        case LIST:
            return this.ui.showTasks(tasks);
        case SHOW:
            return handleShow(input[1]);
        case FIND:
            return find(input[1]);
        case TAG:
            return tag(input[1]);
        default:
            throw new JivoxUnknownCommandException(type.toString());
        }
    }


    /**
     * Starts the application.
     */
    public void run() {
        this.ui.greet();
        while (isRunning) {
            String rawInput = this.ui.input();
            System.out.println(this.getResponse(rawInput));
        }
    }

    public static void main(String[] args) {
        Jivox jivox = new Jivox("./data/jivox.txt");
        jivox.run();
    }

}
