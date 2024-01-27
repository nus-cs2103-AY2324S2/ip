import java.io.IOException;
import java.util.Scanner;

/**
 * Main chatbot program.
 *
 * @author KohGuanZeh
 */
public class Duke {

    // All possible command types.
    private enum Command {
        UNKNOWN, BYE, LIST, TODO, DEADLINE, EVENT, MARK, UNMARK, DELETE
    }

    public static void main(String[] args) {
        Ui ui = new Ui();
        // Scanner to read user inputs.
        Scanner sc = new Scanner(System.in);
        // Task list to track tasks from users.
        TaskList taskList = new TaskList();

        try {
            taskList.loadTaskList();
        } catch (IOException e) {
            ui.showError("Duke cannot start due to the following error: " + e.getMessage());
            return;
        }

        ui.showGreeting();

        while (true) {
            String input = sc.nextLine().trim();
            Command inputCommand = Duke.getCommand(input);
            ui.showLine();

            try {
                // For commands that expects an index as argument.
                int index = 0;
                switch (inputCommand) {
                case BYE:
                    ui.showFarewell();
                    return;
                case LIST:
                    ui.showMessage(taskList.listTasks());
                    break;
                case TODO:
                    ToDo todo = ToDo.createToDo(input.substring(4));
                    ui.showMessage(taskList.addTask(todo));
                    break;
                case DEADLINE:
                    Deadline deadline = Deadline.createDeadline(input.substring(8));
                    ui.showMessage(taskList.addTask(deadline));
                    break;
                case EVENT:
                    Event event = Event.createEvent(input.substring(5));
                    ui.showMessage(taskList.addTask(event));
                    break;
                case MARK:
                    index = Integer.parseInt(input.substring(5));
                    ui.showMessage(taskList.markTask(index));
                    break;
                case UNMARK:
                    index = Integer.parseInt(input.substring(7));
                    ui.showMessage(taskList.unmarkTask(index));
                    break;
                case DELETE:
                    index = Integer.parseInt(input.substring(7));
                    ui.showMessage(taskList.deleteTask(index));
                    break;
                case UNKNOWN:
                    // Fallthrough
                default:
                    throw new UnknownCommandException();
                }
                taskList.saveTaskList();
            } catch (NumberFormatException e) {
                ui.showError("Error. Command expects a number. Please try again.");
            } catch (TaskException | UnknownCommandException e) {
                ui.showError(e.getMessage());
            } catch (IOException e) {
                ui.showError("Tasks cannot be saved to file. Please restart.");
            } finally {
                ui.showLine();
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
}