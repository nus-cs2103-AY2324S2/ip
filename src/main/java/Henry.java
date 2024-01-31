import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Henry {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private enum CommandType {
        LIST, UNMARK, MARK, DELETE, TODO, DEADLINE, EVENT, BYE, UNKNOWN
    }
    private void handleCommand(CommandType commandType, String params) throws HenryException {
        switch (commandType) {
        case LIST:
            tasks.printList();
            break;
        case MARK:
            if (params.isBlank()) {
                throw new HenryException("No index provided");
            }
            tasks.markTask(Integer.parseInt(params) - 1);
            storage.save(tasks);
            break;
        case UNMARK:
            if (params.isBlank()) {
                throw new HenryException("No index provided");
            }
            tasks.unmarkTask(Integer.parseInt(params) - 1);
            storage.save(tasks);
            break;
        case TODO:
            if (params.isBlank()) {
                throw new HenryException("No description provided");
            }
            tasks.addTask(new Todo(params));
            storage.save(tasks);
            break;
        case DEADLINE:
            if (params.isBlank()) {
                throw new HenryException("No description and /by provided");
            }
            if (!params.contains("/by")) {
                throw new HenryException("When this has to be done by?");
            }
            String[] deadlineParams = params.split(" /by ");
            tasks.addTask(new Deadline(deadlineParams[0], deadlineParams[1]));
            storage.save(tasks);
            break;
        case EVENT:
            if (params.isBlank()) {
                throw new HenryException("No description, /from and /to provided");
            }
            if (!params.contains("/from") || !params.contains("/to")) {
                throw new HenryException("Please provide /from and /to");
            }
            String[] eventParams = params.split(" /from | /to ");
            tasks.addTask(new Event(eventParams[0], eventParams[1], eventParams[2]));
            storage.save(tasks);
            break;
        case DELETE:
            if (params.isBlank()) {
                throw new HenryException("No index provided");
            }
            tasks.deleteTask(Integer.parseInt(params) - 1);
            storage.save(tasks);
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
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException | HenryException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }
    public void run() {
        ui.greet();
        while (true) {
            String currentLine = ui.readCommand();
            String[] command = currentLine.split(" ", 2);
            CommandType commandType;
            String params = command.length < 2 ? "" : command[1];

            try {
                commandType = CommandType.valueOf(command[0].toUpperCase());
                if (commandType.equals(CommandType.BYE)) {
                    storage.save(tasks);
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
