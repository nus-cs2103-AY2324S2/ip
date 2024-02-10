package parser;

import command.*;
import dukeexceptions.*;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    public Parser() {}
    public enum Commands {
        BYE,
        LIST,
        UNMARK,
        MARK,
        TODO,
        DEADLINE,
        EVENT,
        ADD,
        DELETE,
        FIND
    }

    /**
     * Takes Command Input and returns a command object with the relevant execute code
     * @param cmdInput
     * @return a command object
     * @throws InvalidCmd For when command is not recognised
     * @throws InvalidDateTimeFormat for when the user gives the date time in an incorrect format
     * @throws EventEmptyException for when any of the fields of the event cmd is empty
     * @throws DeadlineEmptyException for when any of the fields of the deadline cmd is empty
     */
    public Command parseCommand(String cmdInput)
            throws InvalidCmd, InvalidDateTimeFormat, EventEmptyException, DeadlineEmptyException {
        Commands cmd;
        try {
            cmd = Commands.valueOf(cmdInput.split(" ", 2)[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidCmd(cmdInput);
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        switch (cmd) {
            case BYE:
                return new ByeCmd();
            case LIST:
                return new ListCmd();
            case UNMARK:
                return new UnmarkCmd(Integer.parseInt(cmdInput.substring(7)));
            case MARK:
                return new MarkCmd(Integer.parseInt(cmdInput.substring(5)));
            case TODO:
                return new TodoCmd(cmdInput.substring(5));
            case EVENT:
                String[] inputs = parseEvent(cmdInput);
                try {
                    return new EventCmd(inputs[0],
                            LocalDateTime.parse(inputs[1].substring(5), formatter),
                            LocalDateTime.parse(inputs[2].substring(3), formatter));
                } catch (DateTimeParseException e) {
                    throw new InvalidDateTimeFormat(e.getMessage());
                }

            case DEADLINE:
                inputs = parseDeadline(cmdInput);
                try {
                    return new DeadlineCmd(inputs[0], LocalDateTime.parse(inputs[1], formatter));
                } catch (DateTimeParseException e) {
                    throw new InvalidDateTimeFormat(e.getMessage());
                }

            case ADD:
                return new AddCmd(cmdInput);
            case DELETE:
                return new DeleteCmd(Integer.parseInt(cmdInput.substring(7)));
            case FIND:
                return new FindCmd(cmdInput.substring(5));
            default:
                throw new InvalidCmd(cmdInput);
        }
    }

    /**
     * Parses through cmdInput and returns valid parameters an event constructor would accept
     * @param cmdInput
     * @return
     * @throws EventEmptyException
     */
    public String[] parseEvent(String cmdInput) throws EventEmptyException {
        // 0 has description, 1 has from, 2 has to
        String[] inputs = cmdInput.substring(6).split("\\s/", 3);
        // check for missing or invalid fields
        if (!(inputs[1].startsWith("from") && inputs[2].startsWith("to"))) {
            if (!inputs[1].startsWith("from") && !inputs[1].startsWith("to")) {
                throw new EventEmptyException(cmdInput, true, true);
            } else if (!inputs[1].startsWith("from")) {
                throw new EventEmptyException(cmdInput, true, false);
            } else {
                throw new EventEmptyException(cmdInput, false, true);
            }
        }
        return inputs;
    }

    /**
     * Parses through cmdInput and returns valid parameters a deadline constructor would accept
     * @param cmdInput
     * @return
     * @throws DeadlineEmptyException
     */
    public String[] parseDeadline(String cmdInput) throws DeadlineEmptyException {
        String[] inputs = cmdInput.substring(9).split("\\s/by\\s", 2);
        if (inputs.length != 2) {
            throw new DeadlineEmptyException(cmdInput);
        }
        return inputs;
    }

    public enum TaskTag {
        T, // Todo
        D, // Deadline
        E // Event
    }

    /**
     * Takes String of the correct data format and returns task object. Used for loading tasks
     * @param dataFormat
     * @return
     * @throws InvalidDataFormat
     */
    public static Task parseDataFormat(String dataFormat) throws InvalidDataFormat {
        String[] inputsToCmd;
        TaskTag tag;
        Boolean isDone;
        Task result;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");
        tag = TaskTag.valueOf(String.valueOf(dataFormat.charAt(0)).toUpperCase());
        // a lot of exceptions can happen here
        // 1. what if another number other than 0 is put in the isDone category?
        // 2. what if the first character is not a task tag?
        // 3. how do we detect that the file is of incorrect format?
        switch (tag) {
            case D:
                inputsToCmd = dataFormat.split("\\s\\|\\s", 4);
                isDone = (inputsToCmd[1].equals("1"));
                LocalDateTime by = LocalDateTime.parse(inputsToCmd[3], formatter);
                result = new Deadline(inputsToCmd[2], isDone, by);
                return result;
            case E:
                inputsToCmd = dataFormat.split("\\s\\|\\s", 5);
                isDone = (inputsToCmd[1].equals("1"));
                LocalDateTime from = LocalDateTime.parse(inputsToCmd[3], formatter);
                LocalDateTime to = LocalDateTime.parse(inputsToCmd[4], formatter);;
                result = new Event(inputsToCmd[2], isDone, from, to);
                return result;
            case T:
                inputsToCmd = dataFormat.split("\\s\\|\\s", 3);
                isDone = (inputsToCmd[1].equals("1"));
                result = new Todo(inputsToCmd[2], isDone);
                return result;
            default:
                throw new InvalidDataFormat(dataFormat);
        }
    }
}
