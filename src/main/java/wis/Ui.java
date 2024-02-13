package wis;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;

import wis.task.Task;
import wis.task.Todo;
import wis.task.Deadline;
import wis.task.Event;
import wis.task.TaskList;
import wis.util.InputParser;
import wis.util.WisException;
import wis.util.Pair;
import wis.util.Printer;

/**
 * Part of the chat box which deals with user interation.
 */
public class Ui {
    private String input;

    public Ui(String input) {
        this.input = input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    /**
     * Parses user input for action, and performs the action.
     *
     * @param tasks Task list storing all tasks.
     * @return True if and only if user input signals exit.
     */
    public boolean parseInput(TaskList tasks) {
        String[] words = input.split(" ");
        Action action = InputParser.parseAction(input, words);
        switch (action) {
        case NONE:
            break;
        case ADD_TODO:
            addTodo(tasks);
            break;
        case ADD_DEADLINE:
            addDeadline(tasks);
            break;
        case ADD_EVENT:
            addEvent(tasks);
            break;
        case MARK:
            mark(words, tasks);
            break;
        case UNMARK:
            unmark(words, tasks);
            break;
        case DELETE:
            delete(words, tasks);
            break;
        case LIST:
            Printer.printActionAttach(Action.LIST, tasks);
            break;
        case FIND:
            find(words, tasks);
            break;
        case BYE:
            return true;
        case INVALID:
            WisException.actionExceptionHandler(Action.INVALID);
            break;
        default:
            throw new IllegalArgumentException("Illegal action argument provided.\n");
        }
        return false;
    }

    private void addTodo(TaskList tasks) {
        try {
            Todo todo = new Todo(InputParser.parseTodo(input));
            tasks.add(todo);
            Printer.printActionAttach(Action.ADD_TODO, todo, tasks);
        } catch (InputMismatchException e) {
            WisException.actionExceptionHandler(Action.ADD_TODO);
        }
    }

    private void addDeadline(TaskList tasks) {
        try {
            String[] parsedString = InputParser.parseDeadline(input);
            Deadline deadline = new Deadline(parsedString[0],
                    InputParser.parseDateTime(parsedString[1]));
            tasks.add(deadline);
            Printer.printActionAttach(Action.ADD_DEADLINE, deadline, tasks);
        } catch (InputMismatchException e) {
            WisException.actionExceptionHandler(Action.ADD_DEADLINE);
        } catch (DateTimeParseException e) {
            WisException.dateTimeExceptionHandler(e);
        } catch (ArrayIndexOutOfBoundsException e) {
            WisException.dateTimeExceptionHandler(e);
        }
    }

    private void addEvent(TaskList tasks) {
        try {
            String[] parsedString = InputParser.parseEvent(input);
            Event event = new Event(parsedString[0],
                    InputParser.parseDateTime(parsedString[1]),
                    InputParser.parseDateTime(parsedString[2]));
            tasks.add(event);
            Printer.printActionAttach(Action.ADD_EVENT, event, tasks);
        } catch (InputMismatchException e) {
            WisException.actionExceptionHandler(Action.ADD_EVENT);
        } catch (DateTimeParseException e) {
            WisException.dateTimeExceptionHandler(e);
        } catch (ArrayIndexOutOfBoundsException e) {
            WisException.dateTimeExceptionHandler(e);
        }
    }

    private void mark(String[] words, TaskList tasks) {
        try {
            Task task = tasks.get(Integer.parseInt(words[1]) - 1);
            task.setDone();
            Storage.saveTasks(tasks);
            Printer.printActionAttach(Action.MARK, task);
        } catch (IndexOutOfBoundsException e) {
            WisException.actionExceptionHandler(Action.MARK);
        } catch (NumberFormatException e) {
            WisException.actionExceptionHandler(Action.MARK);
        }
    }

    private void unmark(String[] words, TaskList tasks) {
        try {
            Task task = tasks.get(Integer.parseInt(words[1]) - 1);
            task.setUndone();
            Storage.saveTasks(tasks);
            Printer.printActionAttach(Action.UNMARK, task);
        } catch (IndexOutOfBoundsException e) {
            WisException.actionExceptionHandler(Action.UNMARK);
        } catch (NumberFormatException e) {
            WisException.actionExceptionHandler(Action.UNMARK);
        }
    }

    private void delete(String[] words, TaskList tasks) {
        try {
            Task task = tasks.remove(Integer.parseInt(words[1]) - 1);
            Storage.saveTasks(tasks);
            Printer.printActionAttach(Action.DELETE, task, tasks);
        } catch (IndexOutOfBoundsException e) {
            WisException.actionExceptionHandler(Action.DELETE);
        } catch (NumberFormatException e) {
            WisException.actionExceptionHandler(Action.DELETE);
        }
    }

    private void find(String[] words, TaskList tasks) {
        try {
            ArrayList<Pair<Integer, Task>> matchingTasks = tasks.find(words[1]);
            Printer.printActionAttach(Action.FIND, matchingTasks);
        } catch (IndexOutOfBoundsException e) {
            WisException.actionExceptionHandler(Action.FIND);
        }
    }
}
