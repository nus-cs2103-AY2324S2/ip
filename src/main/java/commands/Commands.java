package commands;

import duke.*;
import exceptions.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

/**
 * Contains methods to handle different commands for the Duke application.
 */
public class Commands {

    /**
     * Marks a task as done.
     *
     * @param input The input command string.
     * @param storage The storage object to interact with tasks.
     * @return A string representation of the task that was marked as done.
     * @throws OutOfIntexException If the index provided is out of range.
     */
    public static String markCommand(String input, Storage storage) throws OutOfIntexException {
        List<String> inputParts = Arrays.asList(input.split(" "));
        if (Integer.parseInt(inputParts.get(1)) > storage.size()) {
            throw new OutOfIntexException();
        }
        Task t = storage.get(Integer.parseInt(inputParts.get(1)) - 1);
        t.markAsDone();
        return t.toString();
    }

    /**
     * Marks a task as undone.
     *
     * @param input The input command string.
     * @param storage The storage object to interact with tasks.
     * @return A string representation of the task that was marked as undone.
     * @throws OutOfIntexException If the index provided is out of range.
     */
    public static String unmarkCommand (String input, Storage storage) throws OutOfIntexException {
        List<String> inputParts = Arrays.asList(input.split(" "));
        if (Integer.parseInt(inputParts.get(1)) > storage.size()) {
            throw new OutOfIntexException();
        }
        Task t = storage.get(Integer.parseInt(inputParts.get(1)) - 1);
        t.markAsUndone();
        return t.toString();
    }

    /**
     * Deletes a task.
     *
     * @param input The input command string.
     * @param storage The storage object to interact with tasks.
     * @return A string representation of the task that was deleted.
     * @throws OutOfIntexException If the index provided is out of range.
     */
    public static String deleteCommand (String input, Storage storage) throws OutOfIntexException {
        List<String> inputParts = Arrays.asList(input.split(" "));
        if (Integer.parseInt(inputParts.get(1)) > storage.size()) {
            throw new OutOfIntexException();
        }
        Task t = storage.pop(Integer.parseInt(inputParts.get(1))-1);
        return t.toString();
    }

    /**
     * Finds tasks based on a given keyword.
     *
     * @param input The input command string.
     * @param storage The storage object to interact with tasks.
     * @return A string representation of tasks matching the keyword.
     * @throws EmptyStringException If the keyword is empty.
     */
    public static String findCommand (String input, Storage storage) throws EmptyStringException {
        List<String> inputParts = Arrays.asList(input.split(" "));
        String identifier = "";
        for (int j=1; j<inputParts.size(); j++) {
            identifier += inputParts.get(j) + " ";
        }
        identifier = identifier.trim();
        if (identifier.isEmpty()) {
            throw new EmptyStringException();
        }
        String output = storage.find(identifier);
        return output;
    }

    /**
     * Creates a new ToDos task.
     *
     * @param input The input command string.
     * @param storage The storage object to interact with tasks.
     * @return The newly created ToDos task.
     * @throws EmptyTaskException If the task description is empty.
     */
    public static ToDo todosCommand (String input, Storage storage) throws EmptyTaskException {
        List<String> inputParts = Arrays.asList(input.split(" "));
        String descriptor = "";
        for (int i=1; i<inputParts.size(); i++) {
            descriptor += inputParts.get(i) + " ";
        }
        descriptor = descriptor.trim();
        if (descriptor.isEmpty()) {
            throw new EmptyTaskException();
        }
        ToDo t = new ToDo(descriptor);
        storage.add(t);
        return t;
    }

    /**
     * Creates a new Deadlines task.
     *
     * @param input The input command string.
     * @param storage The storage object to interact with tasks.
     * @return The newly created Deadlines task.
     * @throws EmptyTaskException If the task description is empty.
     * @throws WrongFormatException If the input format is incorrect.
     */
    public static Deadline deadlinesCommand (String input, Storage storage) throws EmptyTaskException, WrongFormatException {
        List<String> inputParts = Arrays.asList(input.split(" "));
        int index = inputParts.indexOf("/by");
        if (index == -1) {
            throw new WrongFormatException();
        }
        String descriptor = "";
        String deadline = "";
        for (int i=1; i<index; i++) {
            descriptor += inputParts.get(i)+ " ";
        }
        for (int i=index+1; i<inputParts.size(); i++) {
            deadline += inputParts.get(i)+ " ";
        }
        deadline = deadline.trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy HHmm");
        LocalDateTime localDateTime = LocalDateTime.parse(deadline, formatter);

        descriptor = descriptor.trim();
        if (descriptor.isEmpty()) {
            throw new EmptyTaskException();
        }
        Deadline d = new Deadline(descriptor, localDateTime);
        storage.add(d);
        return d;
    }

    /**
     * Creates a new Events task.
     *
     * @param input The input command string.
     * @param storage The storage object to interact with tasks.
     * @return The newly created Events task.
     * @throws DukeExceptions If any Duke-specific exception occurs.
     * @throws WrongFormatException If the input format is incorrect.
     */
    public static Event eventsCommand (String input, Storage storage) throws DukeExceptions, WrongFormatException {
        List<String> inputParts = Arrays.asList(input.split(" "));
        int index1 = inputParts.indexOf("/from");
        int index2 = inputParts.indexOf("/to");
        if (index1 == -1 || index2 == -1) {
            throw new WrongFormatException();
        }
        String descriptor = "";
        String from = "";
        String to = "";
        for (int i=index1 + 1; i<index2; i++) {
            from += inputParts.get(i)+ " ";
        }
        for (int i=index2 + 1; i<inputParts.size(); i++) {
            to += inputParts.get(i)+ " ";
        }
        for (int i=1; i<index1; i++) {
            descriptor += inputParts.get(i)+ " ";
        }
        descriptor = descriptor.trim();
        if (descriptor.isEmpty()) {
            throw new EmptyTaskException();
        }
        from = from.trim();
        to = to.trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy HHmm");
        LocalDateTime localDateTimeFrom = LocalDateTime.parse(from, formatter);
        LocalDateTime localDateTimeTo = LocalDateTime.parse(to, formatter);
        Event e =  new Event(descriptor, localDateTimeFrom, localDateTimeTo);
        storage.add(e);
        return e;
    }


}
