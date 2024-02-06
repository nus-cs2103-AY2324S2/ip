package handler;

import dukeexecpetions.DeadlineEmptyException;
import dukeexecpetions.EventEmptyException;
import dukeexecpetions.InvalidCmd;
import items.Items;
import msg.Msg;
import msg.StdMsgs;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class represents how Sir Duke handles the input with the appropriate given
 * userInput
 */
public class CommandsHandler {
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

    /**
     * Converts the UserInput to a command. If command does not exist or entries are missing, error is thrown.
     *
     * @param userInput
     * @param items The task list Sir Duke stores
     * @return True or False, True if bye command is passed, false otherwise
     * @throws InvalidCmd Command is not recognised
     * @throws EventEmptyException Field to or from is empty
     * @throws DeadlineEmptyException Field by is empty
     */
    public static boolean handleCommand(String userInput, Items items)
            throws InvalidCmd, EventEmptyException, DeadlineEmptyException, IOException {
        Commands cmd;
        try {
            cmd = Commands.valueOf(userInput.split(" ", 2)[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidCmd(userInput);
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        switch (cmd) {
        case BYE:
            StdMsgs.BYE.print();
            return true;
        case LIST:
            items.toMsg().print();
            break;
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
}
