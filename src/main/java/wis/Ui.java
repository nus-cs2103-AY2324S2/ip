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
            return Printer.printActionAttach(Action.LIST, tasks);
        case FIND:
            return find(words, tasks);
//        case BYE:
//            return true;
        case INVALID:
            return WisException.actionExceptionHandler(Action.INVALID);
        default:
            throw new IllegalArgumentException("Illegal action argument provided.\n");
        }
//        return false;
    }

    private String addTodo(TaskList tasks) {
        try {
            Todo todo = new Todo(InputParser.parseTodo(input));
            tasks.add(todo);
            return Printer.printActionAttach(Action.ADD_TODO, todo, tasks);
        } catch (InputMismatchException e) {
            return WisException.actionExceptionHandler(Action.ADD_TODO);
        }
    }

    private String addDeadline(TaskList tasks) {
        try {
            String[] parsedString = InputParser.parseDeadline(input);
            Deadline deadline = new Deadline(parsedString[0],
                    InputParser.parseDateTime(parsedString[1]));
            tasks.add(deadline);
            return Printer.printActionAttach(Action.ADD_DEADLINE, deadline, tasks);
        } catch (InputMismatchException e) {
            return WisException.actionExceptionHandler(Action.ADD_DEADLINE);
        } catch (DateTimeParseException e) {
            return WisException.dateTimeExceptionHandler(e);
        } catch (ArrayIndexOutOfBoundsException e) {
            return WisException.dateTimeExceptionHandler(e);
        }
    }

    private String addEvent(TaskList tasks) {
        try {
            String[] parsedString = InputParser.parseEvent(input);
            Event event = new Event(parsedString[0],
                    InputParser.parseDateTime(parsedString[1]),
                    InputParser.parseDateTime(parsedString[2]));
            tasks.add(event);
            return Printer.printActionAttach(Action.ADD_EVENT, event, tasks);
        } catch (InputMismatchException e) {
            return WisException.actionExceptionHandler(Action.ADD_EVENT);
        } catch (DateTimeParseException e) {
            return WisException.dateTimeExceptionHandler(e);
        } catch (ArrayIndexOutOfBoundsException e) {
            return WisException.dateTimeExceptionHandler(e);
        }
    }

    private String mark(String[] words, TaskList tasks) {
        try {
            Task task = tasks.get(Integer.parseInt(words[1]) - 1);
            task.setDone();
            Storage.saveTasks(tasks);
            return Printer.printActionAttach(Action.MARK, task);
        } catch (IndexOutOfBoundsException e) {
            return WisException.actionExceptionHandler(Action.MARK);
        } catch (NumberFormatException e) {
            return WisException.actionExceptionHandler(Action.MARK);
        }
    }

    private String unmark(String[] words, TaskList tasks) {
        try {
            Task task = tasks.get(Integer.parseInt(words[1]) - 1);
            task.setUndone();
            Storage.saveTasks(tasks);
            return Printer.printActionAttach(Action.UNMARK, task);
        } catch (IndexOutOfBoundsException e) {
            return WisException.actionExceptionHandler(Action.UNMARK);
        } catch (NumberFormatException e) {
            return WisException.actionExceptionHandler(Action.UNMARK);
        }
    }

    private String delete(String[] words, TaskList tasks) {
        try {
            Task task = tasks.remove(Integer.parseInt(words[1]) - 1);
            Storage.saveTasks(tasks);
            return Printer.printActionAttach(Action.DELETE, task, tasks);
        } catch (IndexOutOfBoundsException e) {
            return WisException.actionExceptionHandler(Action.DELETE);
        } catch (NumberFormatException e) {
            return WisException.actionExceptionHandler(Action.DELETE);
        }
    }

    private String find(String[] words, TaskList tasks) {
        try {
            ArrayList<Pair<Integer, Task>> matchingTasks = tasks.find(words[1]);
            return Printer.printActionAttach(Action.FIND, matchingTasks);
        } catch (IndexOutOfBoundsException e) {
            return WisException.actionExceptionHandler(Action.FIND);
        }
    }
}
