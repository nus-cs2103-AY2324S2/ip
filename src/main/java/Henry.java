import java.util.ArrayList;
import java.util.Scanner;

public class Henry {
    private Storage storage;
    private Ui ui;
    private enum CommandType {
        LIST, UNMARK, MARK, DELETE, TODO, DEADLINE, EVENT, BYE, UNKNOWN
    }
    private ArrayList<Task> items = new ArrayList<Task>();
    public void addTask(Task task) {
        items.add(task);
        System.out.println("Added this task");
        System.out.println(items.get(items.size() - 1));
        System.out.printf("Now you have %d tasks in the list :(\n", items.size());
        System.out.println();
    }
    public void markTask(int index) throws HenryException {
        if (index < 0 || index >= items.size()) {
            throw new HenryException("The index is out of bounds!");
        }
        items.get(index).markAsDone();
        System.out.println("This task is marked as done XD");
        System.out.println(items.get(index));
        System.out.println();
    }
    public void unmarkTask(int index) throws HenryException {
        if (index < 0 || index >= items.size()) {
            throw new HenryException("The index is out of bounds!");
        }
        items.get(index).unmarkAsDone();
        System.out.println("This task is marked as undone :(");
        System.out.println(items.get(index));
        System.out.println();
    }
    public void deleteTask(int index) throws HenryException {
        if (index < 0 || index >= items.size()) {
            throw new HenryException("The index is out of bounds!");
        }
        System.out.println("This task is deleted :)");
        System.out.println(items.get(index));
        System.out.println();
        items.remove(index);
    }
    private void handleCommand(CommandType commandType, String params) throws HenryException {
        switch (commandType) {
        case LIST:
            System.out.println("Here is a list of tasks:");
            for (int i = 0; i < items.size(); i = i + 1) {
                System.out.printf("%d. %s\n", i + 1, items.get(i));
            }
            System.out.println();
            break;
        case MARK:
            if (params.isBlank()) {
                throw new HenryException("No index provided");
            }
            markTask(Integer.parseInt(params) - 1);
            storage.save(items);
            break;
        case UNMARK:
            if (params.isBlank()) {
                throw new HenryException("No index provided");
            }
            unmarkTask(Integer.parseInt(params) - 1);
            storage.save(items);
            break;
        case TODO:
            if (params.isBlank()) {
                throw new HenryException("No description provided");
            }
            addTask(new Todo(params));
            storage.save(items);
            break;
        case DEADLINE:
            if (params.isBlank()) {
                throw new HenryException("No description and /by provided");
            }
            if (!params.contains("/by")) {
                throw new HenryException("When this has to be done by?");
            }
            String[] deadlineParams = params.split(" /by ");
            addTask(new Deadline(deadlineParams[0], deadlineParams[1]));
            storage.save(items);
            break;
        case EVENT:
            if (params.isBlank()) {
                throw new HenryException("No description, /from and /to provided");
            }
            if (!params.contains("/from") || !params.contains("/to")) {
                throw new HenryException("Please provide /from and /to");
            }
            String[] eventParams = params.split(" /from | /to ");
            addTask(new Event(eventParams[0], eventParams[1], eventParams[2]));
            storage.save(items);
            break;
        case DELETE:
            if (params.isBlank()) {
                throw new HenryException("No index provided");
            }
            deleteTask(Integer.parseInt(params) - 1);
            storage.save(items);
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
    public Henry(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        this.items = new ArrayList<>(storage.load());
    }
    public void run() {
        ui.greet();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String currentLine = scanner.nextLine();
            String[] command = currentLine.split(" ", 2);
            CommandType commandType;
            String params = command.length < 2 ? "" : command[1];

            try {
                commandType = CommandType.valueOf(command[0].toUpperCase());
                if (commandType.equals(CommandType.BYE)) {
                    storage.save(items);
                    ui.bye();
                    break;
                }
            } catch (IllegalArgumentException e) {
                commandType = CommandType.UNKNOWN;
            }

            try {
                handleCommand(commandType, params);
            } catch(HenryException e) {
                System.err.println(e);
            }
        }
    }
    public static void main(String[] args) {
        new Henry("data/tasks.txt").run();
    }
}
