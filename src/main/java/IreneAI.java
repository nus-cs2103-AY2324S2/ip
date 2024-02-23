/**
 * The IreneAI program implements a simple chat application that
 * provides simple feedbacks to the user's inputs
 *
 * @author Chai Ming How
 * @since 2024-02-01
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.IOException;

public class IreneAI {
	private static final String FILE_PATH = "./data/duke.txt";
	private static FileManager fileManager;
    private static List<Task> tasks;
    private static final String LINE = "____________________________________________________________";
    public static void main(String[] args) throws DukeException {
		fileManager = new FileManager(FILE_PATH);
		try {
			tasks = fileManager.loadTasks();
		} catch (IOException e) {
			System.out.println("Error loading tasks from file.");
			tasks = new ArrayList<>();
		}
        Scanner scanner = new Scanner(System.in);

        String chatbotName = "IreneAI";
        dividingLine(LINE);
        System.out.println(" Hello! I'm " + chatbotName);
        System.out.println(" What can I do for you?");
        dividingLine(LINE);

        while (true) {
            System.out.println("You: ");
            String userInput = scanner.nextLine();
            String[] splitInputs = userInput.split(" ");
            Command command = Command.getCommand(splitInputs[0]);
            String argument = splitInputs.length > 1 ? splitInputs[1] : "";
            dividingLine(LINE);

            try {
                switch (command) {
                    case BYE:
                        fileManager.saveTasks(tasks);
                        System.out.println(" Bye. Hope to see you again soon!");
                        dividingLine(LINE);
                        return;
                    case TODO:
                        if (argument.isEmpty()) {
                            throw new DukeException("The description of a todo cannot be empty.");
                        }
                        handleTodo(argument);
                        break;
                    case DEADLINE:
                        // Checks command contains "/by"
                        boolean hasBy = userInput.split(" /by ").length > 1;
                        // Makes sure arguments are at least 3 (e.g. command, subject, deadline)
                        boolean isValid = hasBy && splitInputs.length > 2;
                        if (!isValid) {
                            throw new DukeException("The deadline command requires both description and time inputs, separated by ' /by '");
                        }
                        handleDeadline(userInput);
                        break;
                    case EVENT:
                        // Checks command contains '/from'
                        boolean hasFrom = userInput.split(" /from ").length > 1;
                        // Checks command contains '/to'
                        boolean hasTo = userInput.split(" /to ").length > 1;
                        isValid = hasTo && hasFrom && splitInputs.length > 3;
                        if (!isValid) {
                            throw new DukeException("The event command requires a description, from when, and to when"
                                    + ".\n" + "For example, event project meeting /from Mon 2pm /to 4pm");
                        }
                        handleEvent(userInput);
                        break;
                    case LIST:
                        listTasks();
                        break;
                    case UNMARK:
                        if (argument.isEmpty()) {
                            throw new DukeException("You must specific which task to unmark (in number).");
                        }
                        handleUnmark(userInput);
                        break;
                    case MARK:
                        // future works: index out of bound cases
                        if (argument.isEmpty()) {
                            throw new DukeException("You must specific which task to mark (in number).");
                        }
                        handleMark(userInput);
                        break;
                    case DELETE:
                        if (argument.isEmpty()) {
                            throw new DukeException("You must specific which task to mark (in number).");
                        }
                        handleDelete(userInput);
                        break;
                    case INVALID:
                        //Default error handling
                    default:
                        throw new DukeException("I don't get what you mean.. (╥﹏╥)");
                }
            } catch (DukeException e) {
                dividingLine(LINE);
                System.out.println("OOPS!! " + e.getMessage());
                dividingLine(LINE);
            }
        }
    }

    private static void handleTodo(String userInput) {
        tasks.add(new ToDo(userInput));
        dividingLine(LINE);
        System.out.println("Okie. I've added this task: ");
        System.out.println("  " + tasks.get(tasks.size() - 1));
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    private static void handleDeadline(String userInput) {
        String[] parts = userInput.split(" /by ");
        String description = parts[0].substring(9);
        String deadline = parts[1];
        tasks.add(new Deadline(description, deadline));
        dividingLine(LINE);
        System.out.println("Okie. I've added this task: ");
        System.out.println("  " + tasks.get(tasks.size() - 1));
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    private static void handleEvent(String userInput) {
        String[] parts = userInput.split(" /from ");
        String description = parts[0].substring(6);
        String[] from_to = parts[1].split(" /to ");
        String from = from_to[0];
        String to = from_to[1];
        tasks.add(new Event(description, from, to));
        dividingLine(LINE);
        System.out.println("Okie. I've added this task: ");
        System.out.println("  " + tasks.get(tasks.size() - 1));
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    private static void listTasks() {
        dividingLine(LINE);
        System.out.println("Documented tasks: ");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ": " + tasks.get(i));
        }
        dividingLine(LINE);
    }

    private static void handleUnmark(String userInput) {
        int index = Integer.parseInt(userInput.substring(7)) - 1;
        Task task = tasks.get(index);
        task.markAsNotDone();
        dividingLine(LINE);
        System.out.println("Meow ~ I've marked " + task.getDescription() + " as not done : (");
        System.out.println("  " + task);
        dividingLine(LINE);
    }

    private static void handleMark(String userInput) {
        // Index in an ArrayList starts from 0, therefore deducts 1 from actual counting
        int index = Integer.parseInt(userInput.substring(5)) - 1;
        Task task = tasks.get(index);
        task.markAsDone();
        dividingLine(LINE);
        System.out.println("Brilliant! I've marked " + task.getDescription() + " as done : )");
        System.out.println("  " + task);
        dividingLine(LINE);
    }

    private static void handleDelete(String userInput) {
        try {
            int index = Integer.parseInt(userInput.substring((7))) - 1;
            Task removedTask = tasks.remove(index);
            System.out.println("Success! I have removed this task: " + removedTask + ".");
            System.out.println("Now you still have " + tasks.size() + " tasks remaining.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("OOPS!! I cannot delete the task! It does not exist.");
        }
    }

    private static void dividingLine(String line){
        System.out.println(line);
    }
}
