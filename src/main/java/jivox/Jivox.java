
package jivox;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import jivox.exception.*;


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

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HH:mm");

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
        LocalDateTime from;
        LocalDateTime to;
        try {
            from = LocalDateTime.parse(second[0], formatter);
            to = LocalDateTime.parse(second[1], formatter);
        } catch (DateTimeParseException e) {
            throw new JivoxInvalidDateException();
        }
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
        LocalDateTime deadline;
        try {
            deadline = LocalDateTime.parse(in[1], formatter);
        } catch (DateTimeParseException e) {
            throw new JivoxInvalidDateException();
        }
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
     * @throws JivoxUnknownCommandException, JivoxDuplicateTaskException,
     * JivoxMissingArgumentException If unable to add the task.
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
    public String show(String input) {
        String[] split = this.parser.split(input, "/on ");
        LocalDate time = LocalDate.parse(split[1].replaceFirst(" ", ""), DateTimeFormatter.ofPattern("d/MM/yyyy"));
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
    public String tag(String input) {
        String[] in = parser.parseInput(input);
        int taskNum = Integer.parseInt(in[0]) - 1;
        Task t = this.tasks.getTask(taskNum);
        t.setTag(new Tag(in[1]));
        return this.ui.showTag(t, in[1]);
    }


    public String getResponse(String rawInput) {
        Commands type;
        String[] input;
        try {
            type = parser.parseCommand(rawInput);
            input = parser.parseInput(rawInput);
            switch (type) {
            case BYE:
                this.isRunning = false;
                this.ui.close();
                this.dbHandler.save(this.tasks);
                return this.ui.exit();
            case DEADLINE:
                if (input.length == 1) {
                    throw new JivoxMissingArgumentException();
                }
                return this.add("deadline", input[1]);
            case EVENT:
                if (input.length == 1) {
                    throw new JivoxMissingArgumentException();
                }
                return this.add("event", input[1]);
            case TODO:
                if (input.length == 1) {
                    throw new JivoxMissingArgumentException();
                }
                return this.add("todo", input[1]);
            case MARK:
                if (input.length == 1) {
                    throw new JivoxMissingArgumentException();
                }
                return this.mark(Integer.parseInt(input[1]));
            case UNMARK:
                if (input.length == 1) {
                    throw new JivoxMissingArgumentException();
                }
                return this.unmark(Integer.parseInt(input[1]));
            case DELETE:
                if (input.length == 1) {
                    throw new JivoxMissingArgumentException();
                }
                return this.delete(Integer.parseInt(input[1]));
            case LIST:
                return this.ui.showTasks(this.tasks);
            case SHOW:
                if (input.length == 1) {
                    throw new JivoxMissingArgumentException();
                }
                return this.show(input[1]);
            case FIND:
                if (input.length == 1) {
                    throw new JivoxMissingArgumentException();
                }
                return this.find(input[1]);
            case TAG:
                if (input.length == 0) {
                    throw new JivoxMissingArgumentException();
                }
                return this.tag(input[1]);
            default:
                throw new JivoxMissingArgumentException();
            }
        } catch (JivoxException e) {
            return this.ui.showException(e.toString());
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
