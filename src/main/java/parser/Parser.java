package parser;

import command.Command;
import dukeexceptions.DeadlineEmptyException;
import dukeexceptions.DukeException;
import dukeexceptions.EventEmptyException;
import handler.DataHandler;
import msg.StdMsgs;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {
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
    }
    public Command parseCommand(String cmdInput) throws DukeException {
        Commands cmd;
        cmd = Commands.valueOf(cmdInput.split(" ", 2)[0].toUpperCase());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        switch (cmd) {
            case BYE:
                StdMsgs.BYE.print();
                return new byeCmd();
            case LIST:

            case UNMARK:
                items.unmark(Integer.parseInt(userInput.substring(7)));
                break;
            case MARK:
                items.mark(Integer.parseInt(userInput.substring(5)));
                break;
            case TODO:
                items.add(new Todo(userInput.substring(5)));
                break;
            case EVENT:
                // 0 has description, 1 has from, 2 has to
                String[] inputs = userInput.substring(6).split("\\s/", 3);
                if (inputs.length != 3) {
                    throw new EventEmptyException(userInput, true, true);
                }
                // exception handling here is incomplete. How to see if only from or to fields are empty?
                items.add(
                        new Event(inputs[0],
                                LocalDateTime.parse(inputs[1].substring(5), formatter),
                                LocalDateTime.parse(inputs[2].substring(3), formatter)));
                break;
            case DEADLINE:
                inputs = userInput.substring(9).split("\\s/by\\s", 2);
                if (inputs.length != 2) {
                    throw new DeadlineEmptyException(userInput);
                }
                items.add(new Deadline(inputs[0], LocalDateTime.parse(inputs[1], formatter)));
                break;
            case ADD:
                items.add(new Task(userInput));
                break;
            case DELETE:

                items.delete(Integer.parseInt(userInput.substring(7)));
                break;
        }
        return false;
    }

    public enum TaskTag {
        T, // Todo
        D, // Deadline
        E // Event
    }
    public static Task parseDataFormat(String dataFormat) {
        String[] inputsToCmd;
        DataHandler.TaskTag tag;
        Boolean isDone;
        Task result;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");
        try {
            tag = DataHandler.TaskTag.valueOf(String.valueOf(dataFormat.charAt(0)));
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
            }
        } catch (IllegalArgumentException e) {
            // throw invalid format exception
        }
        return new Task("you are not supposed to be here");
    }
}
