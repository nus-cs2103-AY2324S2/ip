import java.util.ArrayList;
import java.util.Scanner;
public class Henry {
    private static ArrayList<Task> items = new ArrayList<Task>();
    public static void greet() {
        String logo = "  _    _                       \n" +
                " | |  | |                      \n" +
                " | |__| | ___ _ __  _ __ _   _ \n" +
                " |  __  |/ _ \\ '_ \\| '__| | | |\n" +
                " | |  | |  __/ | | | |  | |_| |\n" +
                " |_|  |_|\\___|_| |_|_|   \\__, |\n" +
                "                          __/ |\n" +
                "                         |___/ \n";
        String greetMessage = "Hello! I'm Henry\nWhat can I do for you?";
        System.out.println(logo);
        System.out.println(greetMessage);
        System.out.println();
    }
    public static void bye() {
        System.out.println("See you again bro!");
    }
    public static void addTask(Task task) {
        items.add(task);
        System.out.println("Added this task");
        System.out.println(items.get(items.size() - 1));
        System.out.printf("Now you have %d tasks in the list :(\n", items.size());
        System.out.println();
    }
    public static void markTask(int index) throws HenryException {
        if (index < 0 || index >= items.size()) {
            throw new HenryException("The index is out of bounds!");
        }
        items.get(index).markAsDone();
        System.out.println("This task is marked as done XD");
        System.out.println(items.get(index));
        System.out.println();
    }
    public static void unmarkTask(int index) throws HenryException {
        if (index < 0 || index >= items.size()) {
            throw new HenryException("The index is out of bounds!");
        }
        items.get(index).unmarkAsDone();
        System.out.println("This task is marked as undone :(");
        System.out.println(items.get(index));
        System.out.println();
    }
    public static void deleteTask(int index) throws HenryException {
        if (index < 0 || index >= items.size()) {
            throw new HenryException("The index is out of bounds!");
        }
        System.out.println("This task is deleted :)");
        System.out.println(items.get(index));
        System.out.println();
        items.remove(index);
    }
    public static void handleCommand(String commandType, String params) throws HenryException {
        switch (commandType) {
            case "list":
                System.out.println("Here is a list of tasks:");
                for (int i = 0; i < items.size(); i = i + 1) {
                    System.out.printf("%d. %s\n", i + 1, items.get(i));
                }
                System.out.println();
                break;
            case "mark":
                if (params.isBlank()) {
                    throw new HenryException("No index provided");
                }
                markTask(Integer.parseInt(params) - 1);
                break;
            case "unmark":
                if (params.isBlank()) {
                    throw new HenryException("No index provided");
                }
                unmarkTask(Integer.parseInt(params) - 1);
                break;
            case "todo":
                if (params.isBlank()) {
                    throw new HenryException("No description provided");
                }
                addTask(new Todo(params));
                break;
            case "deadline":
                if (params.isBlank()) {
                    throw new HenryException("No description and /by provided");
                }
                if (!params.contains("/by")) {
                    throw new HenryException("When this has to be done by?");
                }
                String[] deadlineParams = params.split(" /by ");
                addTask(new Deadline(deadlineParams[0], deadlineParams[1]));
                break;
            case "event":
                if (params.isBlank()) {
                    throw new HenryException("No description, /from and /to provided");
                }
                if (!params.contains("/from") || !params.contains("/to")) {
                    throw new HenryException("Please provide /from and /to");
                }
                String[] eventParams = params.split(" /from | /to ");
                addTask(new Event(eventParams[0], eventParams[1], eventParams[2]));
                break;
            case "delete":
                if (params.isBlank()) {
                    throw new HenryException("No index provided");
                }
                deleteTask(Integer.parseInt(params) - 1);
                break;
            default:
                try {
                    throw new HenryException("I don't understand this command...");
                } catch (HenryException e) {
                    System.err.println(e);
                }
                break;
        }
    }
    public static void main(String[] args) {
        greet();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String currentLine = scanner.nextLine();
            String[] command = currentLine.split(" ", 2);
            String commandType = command[0];
            String params = command.length < 2 ? "" : command[1];

            if (commandType.equals("bye")) {
                bye();
                break;
            }

            try {
                handleCommand(commandType, params);
            } catch(HenryException e) {
                System.err.println(e);
            }
        }
    }
}