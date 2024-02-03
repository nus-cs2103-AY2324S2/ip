package Duke;
import java.util.Scanner;
import DukeExceptions.DeadlineEmptyException;
import DukeExceptions.EventEmptyException;
import DukeExceptions.InvalidCmd;
import Msg.Msg;
import Msg.Std_msgs;
import Items.Items;
import Task.Deadline;
import Task.Event;
import Task.Todo;
import Task.Task;

/** Sir Duke Chatbot Class */
public class Duke {
    private static Items items = new Items();
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
     * Runs the Duke Chatbot
     *
     * @param args String arguments to be passes by User
     * @throws InvalidCmd If command is not in Commands
     */
    public static void main(String[] args) throws InvalidCmd {
        Scanner sc = new Scanner(System.in);
        // Should I create a profile about the user by having them answer a few questions?
        // welcome_msg
        System.out.println(Std_msgs.LOGO);
        System.out.println(Std_msgs.WELCOME);
        // await input from user
        String userInput = "";
        while (true) {
            try {
                Commands cmd;
                userInput = sc.nextLine();
                try {
                    cmd = Commands.valueOf(userInput.split(" ", 2)[0].toUpperCase());
                } catch (IllegalArgumentException e) {
                    throw new InvalidCmd(userInput);
                }
                switch (cmd) {
                    case BYE:
                        System.out.println(Std_msgs.BYE);
                        return;
                    case LIST:
                        // unsure if i should extend Items as a Msg
                        System.out.println(new Msg(Duke.items.toString()));
                        break;
                    case UNMARK:
                        Duke.items.unmark(Integer.parseInt(userInput.substring(7)));
                        break;
                    case MARK:
                        Duke.items.mark(Integer.parseInt(userInput.substring(5)));
                        break;
                    case TODO:
                        Duke.items.add(new Todo(userInput.substring(5)));
                        break;
                    case EVENT:
                        String[] inputs = userInput.substring(5).split("/", 3); // 0 has description, 1 has from, 2 has to
                        if (inputs.length != 3) {
                            throw new EventEmptyException(userInput, true, true);
                        }
                        Duke.items.add(
                                new Event(inputs[0],
                                        inputs[1].substring(4),
                                        inputs[2].substring(2)));
                        break;
                    case DEADLINE:
                        inputs = userInput.substring(8).split("/by", 2);
                        if (inputs.length != 2) {
                            throw new DeadlineEmptyException(userInput);
                        }
                        Duke.items.add(new Deadline(inputs[0], inputs[1]));
                        break;
                    case ADD:
                        Duke.items.add(new Task(userInput));
                        break;
                    case DELETE:
                        Duke.items.delete(Integer.parseInt(userInput.substring(7)));
                        break;
                }

            } catch (InvalidCmd| DeadlineEmptyException | EventEmptyException err) {
                System.out.println(new Msg(err.toString()));
            }
        }
    }
}
