package snoopy;
import service.Parser;
import service.Storage;
import service.TaskList;
import ui.UI;

public class Snoopy {

    private static TaskList taskList;
    private static UI ui;
    private static Storage storage;

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return processCommand(input, taskList, true);
    }

    public enum Command {
        TODO, DEADLINE, EVENT, DELETE, MARK, UNMARK, LIST, BYE, UNKNOWN, FIND;

        public static Command fromString(String maybeCommand) {
            try {
                return Command.valueOf(maybeCommand.toUpperCase());
            } catch (Exception e) {
                return UNKNOWN;
            }
        }
    }

    /**
     * Performs the appropriate action based on the user's input of the task.
     * From adding of tasks which can be todos, deadlines and events, deleting and marking and unmarking them.
     *
     * @param userInput user's commands
     * @param todos the list of tasks that are currently present.
     * @param isVerbose helps to ensure whether we are preloading (includes the need to save the entries)
     * @throws RuntimeException
     */
    public static String processCommand(String userInput, TaskList todos, Boolean isVerbose) throws RuntimeException {
        System.out.println("USERINPUT: " + userInput);
        String maybeCommand;
        String arr[];
        try {
            arr = userInput.split(" ", 2); // String in Array format. Useful: https://www.geeksforgeeks.org/split-string-java-examples/
            assert((arr.length) > 1);
            maybeCommand = arr[0];
        } catch (Exception e) {
            maybeCommand = null;
            return "null";
        }

        Command command = Command.fromString(maybeCommand);

        switch (command) {
        case FIND:
            return TaskManager.processFind(arr, userInput, todos, isVerbose);
        case BYE:
            return (ui.formalities("farewell"));
        case LIST:
            return TaskManager.processList(arr, userInput, todos, isVerbose);
        case MARK:
            return TaskManager.processMark(arr, userInput, todos, isVerbose, storage);
        case UNMARK:
            return TaskManager.processUnmark(arr, userInput, todos, isVerbose, storage);
        case TODO:
            return TaskManager.processTodo(arr, userInput, todos, isVerbose, storage);
        case DEADLINE:
            return TaskManager.processDeadline(arr, userInput, todos, isVerbose, storage);
        case EVENT:
            return TaskManager.processEvent(arr, userInput, todos, isVerbose, storage);
        case DELETE:
            return TaskManager.processDelete(arr, userInput, todos, isVerbose, storage);
        default:
            System.out.println("Uh ah I don't understand ya ");
            return "Uh ah I don't understand ya ";
        }
    }

    /**
     * Constructor of Snoopy
     * @param filePath file storage location to save and retrieve list of tasks
     */
    public Snoopy(String filePath) {
        ui = new UI();
        taskList = new TaskList();
        storage = new Storage(filePath);
    }

    /**
     * Constructor of Snoopy without parameters
     */
    public Snoopy() {
       new Snoopy("./src/main/java/data/snoopy.txt");
    }


    /**
     * With a Snoopy object, .run() is the main entry point of the program, running all its processes.
     *
     */
    public void run() {
        //do something
        ui.formalities("greet");

        //Load existing information
        try {
            storage.loadInfo(taskList);
        } catch (Exception e) {
            e.getMessage();
            taskList = null;
        }
        while (true) {
            //Parsing user input.
           String command = new Parser().parse();
           assert(command != null);
            //Process user command
            try {
                processCommand(command, taskList, true);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Snoopy("./data/duke.txt").run();
    }
}
