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

/**
 * This class represents how Sir Duke handles the input with the appropriate given
 * userInput
 */
public class handleCommands {
    public enum Commands {
        BYE,
        LIST,
        UNMARK,
        MARK,
        TODO,
        DEADLINE,
        EVENT,
        ADD,
        DELETE
    }

    /**
     * Converts the UserInput to a command. If command does not exist or entries are missing, error is thrown.
     *
     * @param userInput
     * @param items The task list Sir Duke stores
     * @return True or False, True is bye command is passed, false otherwise
     * @throws InvalidCmd Command is not recognised
     * @throws EventEmptyException Field to or from is empty
     * @throws DeadlineEmptyException Field by is empty
     */
    public static boolean handleCommand(String userInput, Items items) throws InvalidCmd, EventEmptyException, DeadlineEmptyException {
        Commands cmd;
        try {
            cmd = Commands.valueOf(userInput.split(" ", 2)[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidCmd(userInput);
        }
        switch (cmd) {
            case BYE:
                System.out.println(StdMsgs.BYE);
                return true;
            case LIST:
                System.out.println(new Msg(items.toString()));
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
                String[] inputs = userInput.substring(5).split("/", 3); // 0 has description, 1 has from, 2 has to
                if (inputs.length != 3) {
                    throw new EventEmptyException(userInput, true, true);
                }
                items.add(
                        new Event(inputs[0],
                                inputs[1].substring(4),
                                inputs[2].substring(2)));
                break;
            case DEADLINE:
                inputs = userInput.substring(8).split("/by", 2);
                if (inputs.length != 2) {
                    throw new DeadlineEmptyException(userInput);
                }
                items.add(new Deadline(inputs[0], inputs[1]));
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
