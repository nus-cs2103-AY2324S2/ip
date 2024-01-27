import java.io.IOException;
import java.util.Scanner;

/**
 * Main chatbot program.
 *
 * @author KohGuanZeh
 */
public class Duke {
    // Separator decorators for chatbot.
    private static final String BLOCK_SEPARATOR = "=".repeat(80);

    // All possible command types.
    private enum Command {
        UNKNOWN, BYE, LIST, TODO, DEADLINE, EVENT, MARK, UNMARK, DELETE
    }

    public static void main(String[] args) {
        // Greeting message to be displayed on start.
        final String GREETING = "Hello, I'm Ted. What can I do for you today?";
        // Goodbye message to be displayed on exit.
        final String GOODBYE = "Bye. See you again.";
        // Scanner to read user inputs.
        Scanner sc = new Scanner(System.in);
        // Task list to track tasks from users.
        TaskList taskList = new TaskList();

        try {
            taskList.loadTaskList();
        } catch (IOException e) {
            Duke.echo("Duke cannot start due to the following error: " + e.getMessage());
            return;
        }

        Duke.echo(GREETING);

        while (true) {
            String input = sc.nextLine().trim();
            Command inputCommand = Duke.getCommand(input);

            try {
                // For commands that expects an index as argument.
                int index = 0;
                switch (inputCommand) {
                case BYE:
                    Duke.echo(GOODBYE);
                    return;
                case LIST:
                    Duke.echo(taskList.listTasks());
                    break;
                case TODO:
                    ToDo todo = ToDo.createToDo(input.substring(4));
                    Duke.echo(taskList.addTask(todo));
                    break;
                case DEADLINE:
                    Deadline deadline = Deadline.createDeadline(input.substring(8));
                    Duke.echo(taskList.addTask(deadline));
                    break;
                case EVENT:
                    Event event = Event.createEvent(input.substring(5));
                    Duke.echo(taskList.addTask(event));
                    break;
                case MARK:
                    index = Integer.parseInt(input.substring(5));
                    Duke.echo(taskList.markTask(index));
                    break;
                case UNMARK:
                    index = Integer.parseInt(input.substring(7));
                    Duke.echo(taskList.unmarkTask(index));
                    break;
                case DELETE:
                    index = Integer.parseInt(input.substring(7));
                    Duke.echo(taskList.deleteTask(index));
                    break;
                case UNKNOWN:
                    // Fallthrough
                default:
                    throw new UnknownCommandException();
                }
                taskList.saveTaskList();
            } catch (NumberFormatException e) {
                Duke.echo("Error. Command expects a number. Please try again.");
            } catch (TaskException | UnknownCommandException e) {
                Duke.echo(e.getMessage());
            } catch (IOException e) {
                Duke.echo("Tasks cannot be saved to file. Please restart.");
            }
        }
    }

    private static Command getCommand(String input) {
        if (input.equals("bye")) {
            return Command.BYE;
        } else if (input.equals("list")) {
            return Command.LIST;
        } else if (input.startsWith("todo")) {
            return Command.TODO;
        } else if (input.startsWith("deadline")) {
            return Command.DEADLINE;
        } else if (input.startsWith("event")) {
            return Command.EVENT;
        } else if (input.startsWith("mark")) {
            return Command.MARK;
        } else if (input.startsWith("unmark")) {
            return Command.UNMARK;
        } else if (input.startsWith("delete")) {
            return Command.DELETE;
        }

        return Command.UNKNOWN;
    }

    /**
     * Prints the specified message to the output.
     *
     * @param message Message to be printed on output.
     */
    private static void echo(String message) {
        System.out.println(BLOCK_SEPARATOR);
        System.out.println(message);
        System.out.println(BLOCK_SEPARATOR);
    }
}