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
    private String lastInput;
    private Action lastAction;
    private Task lastDeletedTask;

    public Ui(String input) {
        this.input = input;
        this.lastInput = "";
        this.lastAction = Action.NONE;
        this.lastDeletedTask = null;
    }

    public void setInput(String input) {
        this.input = input;
    }

    /**
     * Parses user input for action, and performs the action.
     *
     * @param tasks Task list storing all tasks.
     */
    public String parseInput(TaskList tasks) {
        String[] words = input.split(" ");
        Action action = InputParser.parseAction(input, words);
        switch (action) {
        case NONE:
            return "";
        case ADD_TODO:
            return addTodo(tasks);
        case ADD_DEADLINE:
            return addDeadline(tasks);
        case ADD_EVENT:
            return addEvent(tasks);
        case MARK:
            return mark(words, tasks);
        case UNMARK:
            return unmark(words, tasks);
        case DELETE:
            return delete(words, tasks);
        case LIST:
            return Printer.getMessageActionAttach(Action.LIST, tasks);
        case FIND:
            return find(words, tasks);
        case UNDO:
            return undo(tasks);
        case INVALID:
            return WisException.handleActionException(Action.INVALID);
        default:
            throw new IllegalArgumentException("Illegal action argument provided.\n");
        }
    }

    private void cacheInput(String input, Action action) {
        this.lastInput = input;
        this.lastAction = action;
    }

    private void clearCache() {
        this.lastInput = "";
        this.lastAction = Action.NONE;
        this.lastDeletedTask = null;
    }

    private String addTodo(TaskList tasks) {
        try {
            Todo todo = new Todo(InputParser.parseTodo(input));
            tasks.add(todo);
            cacheInput(input, Action.ADD_TODO);
            return Printer.getMessageActionAttach(Action.ADD_TODO, todo, tasks);
        } catch (InputMismatchException e) {
            return WisException.handleActionException(Action.ADD_TODO);
        }
    }

    private String addDeadline(TaskList tasks) {
        try {
            String[] parsedString = InputParser.parseDeadline(input);
            Deadline deadline = new Deadline(parsedString[0],
                    InputParser.parseDateTime(parsedString[1]));
            tasks.add(deadline);
            cacheInput(input, Action.ADD_DEADLINE);
            return Printer.getMessageActionAttach(Action.ADD_DEADLINE, deadline, tasks);
        } catch (InputMismatchException e) {
            return WisException.handleActionException(Action.ADD_DEADLINE);
        } catch (DateTimeParseException | ArrayIndexOutOfBoundsException e) {
            return WisException.handleDateTimeException(e);
        }
    }

    private String addEvent(TaskList tasks) {
        try {
            String[] parsedString = InputParser.parseEvent(input);
            Event event = new Event(parsedString[0],
                    InputParser.parseDateTime(parsedString[1]),
                    InputParser.parseDateTime(parsedString[2]));
            tasks.add(event);
            cacheInput(input, Action.ADD_EVENT);
            return Printer.getMessageActionAttach(Action.ADD_EVENT, event, tasks);
        } catch (InputMismatchException e) {
            return WisException.handleActionException(Action.ADD_EVENT);
        } catch (DateTimeParseException | ArrayIndexOutOfBoundsException e) {
            return WisException.handleDateTimeException(e);
        }
    }

    private String mark(String[] words, TaskList tasks) {
        try {
            Task task = tasks.get(Integer.parseInt(words[1]) - 1);
            task.setDone();
            cacheInput(input, Action.MARK);
            return Printer.getMessageActionAttach(Action.MARK, task);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            return WisException.handleActionException(Action.MARK);
        }
    }

    private String unmark(String[] words, TaskList tasks) {
        try {
            Task task = tasks.get(Integer.parseInt(words[1]) - 1);
            task.setUndone();
            cacheInput(input, Action.UNMARK);
            return Printer.getMessageActionAttach(Action.UNMARK, task);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            return WisException.handleActionException(Action.UNMARK);
        }
    }

    private String delete(String[] words, TaskList tasks) {
        try {
            this.lastDeletedTask = tasks.remove(Integer.parseInt(words[1]) - 1);
            cacheInput(input, Action.DELETE);
            return Printer.getMessageActionAttach(Action.DELETE, lastDeletedTask, tasks);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            return WisException.handleActionException(Action.DELETE);
        }
    }

    private String find(String[] words, TaskList tasks) {
        try {
            ArrayList<Pair<Integer, Task>> matchingTasks = tasks.find(words[1]);
            return Printer.getMessageActionAttach(Action.FIND, matchingTasks);
        } catch (IndexOutOfBoundsException e) {
            return WisException.handleActionException(Action.FIND);
        }
    }

    private String undo(TaskList tasks) {
        String[] words = lastInput.split(" ");
        switch (lastAction) {
        case NONE:
            return Printer.printNothingToUndo();
        case MARK:
            unmark(words, tasks);
            clearCache();
            return Printer.printUndoMark();
        case UNMARK:
            mark(words, tasks);
            clearCache();
            return Printer.printUndoUnmark();
        case ADD_EVENT:
        case ADD_TODO:
        case ADD_DEADLINE:
            Task taskRemoved = tasks.removeLast();
            clearCache();
            return Printer.printUndoAdd(taskRemoved);
        case DELETE:
            return undoDelete(tasks);
        default:
            throw new InputMismatchException();
        }
    }

    private String undoDelete(TaskList tasks) {
        if (lastDeletedTask == null) {
            throw new InputMismatchException();
        }
        tasks.add(lastDeletedTask);
        Task task = lastDeletedTask;
        clearCache();
        return Printer.printUndoDelete(task);
    }
}
