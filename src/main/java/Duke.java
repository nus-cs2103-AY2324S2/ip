import java.util.Scanner;

/**
 * Main chatbot program.
 *
 * @author KohGuanZeh
 */
public class Duke {
    // Separator decorators for chatbot.
    private static final String BLOCK_SEPARATOR = "=".repeat(80);

    public static void main(String[] args) {
        // Greeting message to be displayed on start.
        final String GREETING = "Hello, I'm Ted. What can I do for you today?";
        // Goodbye message to be displayed on exit.
        final String GOODBYE = "Bye. See you again.";
        // Scanner to read user inputs.
        Scanner sc = new Scanner(System.in);
        // Task list to track tasks from users.
        TaskList taskList = new TaskList();

        Duke.echo(GREETING);

        while (true) {
            String input = sc.nextLine();

            try {
                if (input.equals("bye")) {
                    break;
                } else if (input.equals("list")) {
                    Duke.echo(taskList.listTasks());
                } else if (input.startsWith("todo")) {
                    ToDo todo = ToDo.createToDo(input.substring(4));
                    Duke.echo(taskList.addTask(todo));
                } else if (input.startsWith("deadline")) {
                    Deadline deadline = Deadline.createDeadline(input.substring(8));
                    Duke.echo(taskList.addTask(deadline));
                } else if (input.startsWith("event")) {
                    Event event = Event.createEvent(input.substring(5));
                    Duke.echo(taskList.addTask(event));
                } else if (input.startsWith("mark")) {
                    int index = Integer.parseInt(input.substring(5));
                    Duke.echo(taskList.markTask(index));
                } else if (input.startsWith("unmark")) {
                    int index = Integer.parseInt(input.substring(7));
                    Duke.echo(taskList.unmarkTask(index));
                } else {
                    throw new UnknownCommandException();
                }
            } catch (NumberFormatException e) {
                Duke.echo("Error. Command expects a number. Please try again.");
            } catch (TaskException | UnknownCommandException e) {
                Duke.echo(e.getMessage());
            }
        }

        Duke.echo(GOODBYE);
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