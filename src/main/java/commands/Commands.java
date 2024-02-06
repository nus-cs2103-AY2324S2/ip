package commands;

import duke.*;
import exceptions.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class Commands {

    public static String markCommand(String input, Storage storage) throws OutOfIntexException {
        List<String> inputParts = Arrays.asList(input.split(" "));
        if (Integer.parseInt(inputParts.get(1)) > storage.size()) {
            throw new OutOfIntexException();
        }
        Task t = storage.get(Integer.parseInt(inputParts.get(1)) - 1);
        t.markAsDone();
        return t.toString();
    }

    public static String unmarkCommand (String input, Storage storage) throws OutOfIntexException {
        List<String> inputParts = Arrays.asList(input.split(" "));
        if (Integer.parseInt(inputParts.get(1)) > storage.size()) {
            throw new OutOfIntexException();
        }
        Task t = storage.get(Integer.parseInt(inputParts.get(1)) - 1);
        t.markAsUndone();
        return t.toString();
    }

    public static String deleteCommand (String input, Storage storage) throws OutOfIntexException {
        List<String> inputParts = Arrays.asList(input.split(" "));
        if (Integer.parseInt(inputParts.get(1)) > storage.size()) {
            throw new OutOfIntexException();
        }
        Task t = storage.pop(Integer.parseInt(inputParts.get(1))-1);
        return t.toString();
    }

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

    public static ToDos todosCommand (String input, Storage storage) throws EmptyTaskException {
        List<String> inputParts = Arrays.asList(input.split(" "));
        String descriptor = "";
        for (int i=1; i<inputParts.size(); i++) {
            descriptor += inputParts.get(i) + " ";
        }
        descriptor = descriptor.trim();
        if (descriptor.isEmpty()) {
            throw new EmptyTaskException();
        }
        ToDos t = new ToDos(descriptor);
        storage.add(t);
        return t;
    }

    public static Deadlines deadlinesCommand (String input, Storage storage) throws EmptyTaskException, WrongFormatException {
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
        Deadlines d = new Deadlines(descriptor, localDateTime);
        storage.add(d);
        return d;
    }

    public static Events eventsCommand (String input, Storage storage) throws DukeExceptions, WrongFormatException {
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
        Events e =  new Events(descriptor, localDateTimeFrom, localDateTimeTo);
        storage.add(e);
        return e;
    }


}
