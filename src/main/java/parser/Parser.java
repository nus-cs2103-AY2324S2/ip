package parser;

import task.Deadline;
import task.Event;
import task.Todo;
import command.AddCommand;
import command.Command;
import command.ManageCommand;
import command.QueryCommand;

import exceptions.LuluException;
import exceptions.InvalidCommandException;
import exceptions.InvalidDateException;
import exceptions.InvalidSlashParameterException;

import java.time.LocalDate;

/**
 * The Parser class is responsible for parsing user input and creating corresponding Command objects.
 * It includes methods for parsing various commands such as adding, managing, and querying tasks.
 */
public class Parser {

    /**
     * Parses the input string and creates the corresponding Command object.
     *
     * @param input The user input string.
     * @return The Command object based on the parsed input.
     * @throws LuluException If an error occurs during parsing.
     */
    public Command parse(String input) throws LuluException {
        String firstWord = input.split(" ")[0];
        if (input.toLowerCase().equals("list")) {
            return manageTasks(Command.Types.LIST, input);
        } else if (firstWord.equals("mark")) {
            return manageTasks(Command.Types.MARK, input);
        } else if (firstWord.equals("unmark")) {
            return manageTasks(Command.Types.UNMARK, input);
        } else if (firstWord.equals("delete")) {
            return manageTasks(Command.Types.DELETE, input);
        } else if (firstWord.equals("todo")) {
            return addTasks(Command.Types.TODO, input);
        } else if (firstWord.equals("deadline")) {
            return addTasks(Command.Types.DEADLINE, input);
        } else if (firstWord.equals("event")) {
            return addTasks(Command.Types.EVENT, input);
        } else if (firstWord.equals("find")) {
            return manageTasks(Command.Types.FIND, input);
        } else {
            throw new InvalidCommandException();
        }
    }

    /**
     * Creates a ManageCommand object based on the specified command type and input.
     *
     * @param command The type of ManageCommand (MARK, UNMARK, DELETE, FIND).
     * @param input   The user input string.
     * @return The ManageCommand object.
     * @throws LuluException If an error occurs during parsing.
     */
    public Command manageTasks(Command.Types command, String input) throws LuluException {
        switch (command) {
            case MARK:
                return mark(input);
            case UNMARK:
                return unmark(input);
            case DELETE:
                return delete(input);
            case FIND:
                return find(input);
            default:
                throw new InvalidCommandException();
        }
    }

    /**
     * Creates an AddCommand object based on the specified command type and input.
     *
     * @param command The type of AddCommand (TODO, DEADLINE, EVENT).
     * @param input   The user input string.
     * @return The AddCommand object.
     * @throws LuluException If an error occurs during parsing.
     */
    public Command addTasks(Command.Types command, String input) throws LuluException {
        switch (command) {
            case TODO:
                return todo(input);
            case DEADLINE:
                return deadline(input);
            case EVENT:
                return event(input);
            default:
                throw new InvalidCommandException();
        }
    }

    /**
     * Parses the input string for a MARK command and creates the corresponding ManageCommand object.
     *
     * @param input The user input string.
     * @return The ManageCommand object for marking a task.
     * @throws LuluException If an error occurs during parsing.
     */
    public Command mark(String input) throws LuluException {
        String[] words = input.split(" ");
        if (words.length <= 1) {
            throw new InvalidCommandException();
        }
        int index = Integer.valueOf(words[1]) - 1;
        return new ManageCommand(Command.Types.MARK, index);
    }

    /**
     * Parses the input string for an UNMARK command and creates the corresponding ManageCommand object.
     *
     * @param input The user input string.
     * @return The ManageCommand object for unmarking a task.
     * @throws LuluException If an error occurs during parsing.
     */
    public Command unmark(String input) throws LuluException {
        String[] words = input.split(" ");
        if (words.length <= 1) {
            throw new InvalidCommandException();
        }
        int index = Integer.valueOf(words[1]) - 1;
        return new ManageCommand(Command.Types.UNMARK, index);
    }

    /**
     * Parses the input string for a DELETE command and creates the corresponding ManageCommand object.
     *
     * @param input The user input string.
     * @return The ManageCommand object for deleting a task.
     * @throws LuluException If an error occurs during parsing.
     */
    public Command delete(String input) throws LuluException {
        String[] words = input.split(" ");
        if (words.length <= 1) {
            throw new InvalidCommandException();
        }
        int index = Integer.valueOf(words[1]) - 1;
        return new ManageCommand(Command.Types.DELETE, index);
    }

    /**
     * Parses the input string for a FIND command and creates the corresponding QueryCommand object.
     *
     * @param input The user input string.
     * @return The QueryCommand object for finding tasks.
     * @throws LuluException If an error occurs during parsing.
     */
    public Command find(String input) throws LuluException {
        String[] words = input.split(" ");
        if (words.length <= 2) {
            throw new InvalidCommandException();
        }

        String taskType = words[1].toLowerCase();
        LocalDate date = LocalDate.parse(words[2]);
        return new QueryCommand(Command.Types.FIND, taskType, date);
    }

    /**
     * Parses the input string for a TODO command and creates the corresponding AddCommand object.
     *
     * @param input The user input string.
     * @return The AddCommand object for adding a Todo task.
     * @throws LuluException If an error occurs during parsing.
     */
    public Command todo(String input) throws LuluException {
        if (input.split(" ").length <= 1) {
            throw new InvalidCommandException();
        }
        String name = input.substring(5).strip();
        Todo todo = new Todo(name);
        String data = String.format("todo,%s,%b", name, todo.getStatus());
        Command command = new AddCommand(todo, data);
        return command;
    }

    /**
     * Parses the input string for a DEADLINE command and creates the corresponding AddCommand object.
     *
     * @param input The user input string.
     * @return The AddCommand object for adding a Deadline task.
     * @throws LuluException If an error occurs during parsing.
     */
    public Command deadline(String input) throws LuluException {
        if (input.split(" ").length <= 1) {
            throw new InvalidCommandException();
        }
        int indexBy = input.indexOf('/');
        if (!input.substring(indexBy + 1).split(" ")[0].equals("by")) {
            throw new InvalidSlashParameterException();
        }
        String name = input.substring(9, indexBy).strip();
        String by = input.substring(indexBy + 3).strip();
        Deadline deadline = new Deadline(name, LocalDate.parse(by));
        String data = String.format("deadline,%s,%s,%b", name, by, deadline.getStatus());
        Command command = new AddCommand(deadline, data);
        return command;
    }

    /**
     * Parses the input string for an EVENT command and creates the corresponding AddCommand object.
     *
     * @param input The user input string.
     * @return The AddCommand object for adding an Event task.
     * @throws LuluException If an error occurs during parsing.
     */
    public Command event(String input) throws LuluException {
        if (input.split(" ").length <= 1) {
            throw new InvalidCommandException();
        }
        int indexFrom = input.indexOf('/');
        int indexTo = input.indexOf('/', indexFrom + 1);
        if (!input.substring(indexFrom + 1).split(" ")[0].equals("from")) {
            throw new InvalidSlashParameterException();
        }
        if (!input.substring(indexTo + 1).split(" ")[0].equals("to")) {
            throw new InvalidSlashParameterException();
        }
        String name = input.substring(6, indexFrom).strip();
        String from = input.substring(indexFrom + 5, indexTo).strip();
        String to = input.substring(indexTo + 3).strip();
        if (LocalDate.parse(to).isBefore(LocalDate.parse(from))) {
            throw new InvalidDateException();
        }
        Event event = new Event(name, LocalDate.parse(from), LocalDate.parse(to));
        String data = String.format("event,%s,%s,%s,%b", name, from, to, event.getStatus());
        Command command = new AddCommand(event, data);
        return command;
    }
}