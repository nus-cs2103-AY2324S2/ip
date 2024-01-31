
package jivox;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jivox.exception.DataHandlerException;
import jivox.exception.JivoxException;
import jivox.task.Deadline;
import jivox.task.Event;
import jivox.task.Task;
import jivox.task.TaskList;
import jivox.task.Todo;


public class Jivox {

    private final DatabaseHandler dbHandler;
    private final Ui ui;
    private final TaskList tasks;
    private final Parser parser;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HH:mm");

    public Jivox(String filePath) {
        this.dbHandler = new DatabaseHandler(filePath);
        this.tasks = new TaskList(dbHandler.load());
        this.ui = new Ui();
        this.parser = new Parser();
    }

    private void mark(int i) throws JivoxException {
        if (i > this.tasks.getLength() || i < 0) {
            throw new JivoxException("Oops! There are only " + this.tasks.getLength() + " Tasks!");
        }
        Task t = this.tasks.getTask(i - 1);
        t.mark();
        dbHandler.save(this.tasks);
        this.ui.showMark(t);

    }

    private void unmark(int i) throws JivoxException {
        if (i > this.tasks.getLength() || i < 0) {
            throw new JivoxException("Oops! There are only " + this.tasks.getLength() + " Tasks!");
        }
        Task t = this.tasks.getTask(i - 1);
        t.unmark();
        dbHandler.save(this.tasks);
        this.ui.showUnmark(t);
    }

    private void addEvent(String content) throws JivoxException {
        String[] first = this.parser.split(content, " /from ");
        //    content.split(" /from ");
        if (first.length == 1) {
            throw new JivoxException("No time interval (from) received  for the event , Please try again!");
        }
        String[] second = this.parser.split(first[1], " /to ", 2);
        //first[1].split(" /to ",2);
        if (second.length == 1) {
            throw new JivoxException("No time interval received (to) for the event , Please try again!");
        }
        LocalDateTime from = LocalDateTime.parse(second[0], formatter);
        LocalDateTime to = LocalDateTime.parse(second[1], formatter);
        if (to.isBefore(from)) {
            throw new JivoxException("Invalid event ! To is before From");
        }
        this.tasks.add(new Event(first[0].trim(), from, to));
        dbHandler.save(this.tasks);
    }

    private void addTodo(String content) throws DataHandlerException {
        this.tasks.add(new Todo(content));
        dbHandler.save(this.tasks);
    }

    private void addDeadline(String content) throws JivoxException {
        String[] in = this.parser.split(content, " /by ", 2);
        // content.split(" /by ",2);
        if (in.length == 1) {
            throw new JivoxException("Oooops! Please provide a deadline");
        }
        LocalDateTime deadline = LocalDateTime.parse(in[1], formatter);
        this.tasks.add(new Deadline(in[0].trim(), deadline));
        dbHandler.save(this.tasks);
    }

    public void add(String type, String description) throws JivoxException {
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
            throw new JivoxException("Can't identify the Task type!");
        }
        this.ui.showAdd(this.tasks.getTask(this.tasks.getLength() - 1), this.tasks.getLength());
    }


    public void delete(int i) throws JivoxException {
        if (i > this.tasks.getLength() || i < 0) {
            throw new JivoxException("Oops! There are only " + this.tasks.getLength() + " Tasks!");
        }
        Task t = this.tasks.getTask(i - 1);
        this.tasks.delete(i - 1);
        this.dbHandler.save(this.tasks);
        this.ui.showDelete(t, this.tasks.getLength());
    }

    public void show(String input) {
        String[] split = this.parser.split(input, "/on ");
        // input.split("/on ");
        LocalDate time = LocalDate.parse(split[1].replaceFirst(" ", ""), DateTimeFormatter.ofPattern("d/MM/yyyy"));
        this.ui.showDeadline(this.tasks, time);
    }

    public void run() {
        this.ui.greet();
        boolean isRunning = true;
        do {
            String rawInput = this.ui.input();
            Commands type;
            String[] input = this.parser.parseInput(rawInput);
            try {
                type = parser.parseCommand(rawInput);
            } catch (JivoxException e) {
                this.ui.showException(e);
                continue;
            }
            switch (type) {
            case BYE:
                isRunning = false;
                this.ui.close();
                this.ui.exit();
                break;
            case DEADLINE:
                try {
                    this.add("deadline", input[1]);
                } catch (JivoxException e) {
                    this.ui.showException(e);
                }
                break;
            case EVENT:
                try {
                    if (input.length == 1) {
                        throw new JivoxException("Ooops! Please provide a description!");
                    }
                    this.add("event", input[1]);
                } catch (JivoxException e) {
                    this.ui.showException(e);
                }
                break;
            case TODO:
                try {
                    if (input.length == 1) {
                        throw new JivoxException("Ooops! Please provide a description!");
                    }
                    this.add("todo", input[1]);
                } catch (JivoxException e) {
                    this.ui.showException(e);
                }
                break;
            case MARK:
                try {
                    if (input.length == 1) {
                        throw new JivoxException("Please, provide a task number to mark");
                    }
                    this.mark(Integer.parseInt(input[1]));
                } catch (JivoxException e) {
                    this.ui.showException(e);
                }
                break;
            case UNMARK:
                try {
                    if (input.length == 1) {
                        throw new JivoxException("Please, provide a task number to mark");
                    }
                    this.unmark(Integer.parseInt(input[1]));
                } catch (JivoxException e) {
                    this.ui.showException(e);
                }
                break;
            case DELETE:
                try {
                    if (input.length == 1) {
                        throw new JivoxException("Please, provide a task number to delete");
                    }
                    this.delete(Integer.parseInt(input[1]));
                } catch (JivoxException e) {
                    this.ui.showException(e);
                }
                break;
            case LIST:
                this.ui.showTasks(this.tasks);
                break;
            case SHOW:
                this.show(input[1]);
                break;
            default:
                System.out.println("Invalid Input");
            }
        } while (isRunning);
    }

}
