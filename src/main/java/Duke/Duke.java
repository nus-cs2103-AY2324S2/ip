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
                try {
                    userInput = sc.nextLine();
                    Commands cmd = Commands.valueOf(userInput);
                } catch (IllegalArgumentException e) {
                    throw new InvalidCmd(userInput);
                }
                if (userInput.startsWith("bye")) {
                    // bye
                    System.out.println(Std_msgs.BYE);
                    break;
                } else if (userInput.startsWith("list")) {
                    // unsure if i should extend Items as a Msg
                    System.out.println(new Msg(Duke.items.toString()));
                    // add new Task
                } else if (userInput.startsWith("unmark")) {
                    Duke.items.unmark(Integer.parseInt(userInput.substring(7)));
                } else if (userInput.startsWith("mark")) {
                    Duke.items.mark(Integer.parseInt(userInput.substring(5)));
                } else if (userInput.startsWith("todo")) {
                    Duke.items.add(new Todo(userInput.substring(5)));
                } else if (userInput.startsWith("deadline")) {
                    String[] inputs = userInput.substring(8).split("/by", 2);
                    if (inputs.length != 2) {
                        throw new DeadlineEmptyException(userInput);
                    }
                    Duke.items.add(new Deadline(inputs[0], inputs[1]));
                } else if (userInput.startsWith("event")) {
                    String[] inputs = userInput.substring(5).split("/", 3); // 0 has description, 1 has from, 2 has to
                    if (inputs.length != 3) {
                        throw new EventEmptyException(userInput, true, true);
                    }
                    Duke.items.add(
                            new Event(inputs[0],
                                    inputs[1].substring(4),
                                    inputs[2].substring(2)));
                } else if (userInput.startsWith("add")) {
                    Duke.items.add(new Task(userInput));
                } else if (userInput.startsWith("delete")) {
                    Duke.items.delete(Integer.parseInt(userInput.substring(7)));
                }
            } catch (InvalidCmd| DeadlineEmptyException | EventEmptyException inv) {
                System.out.println(new Msg(inv.toString()));
            }
        }
    }
}
