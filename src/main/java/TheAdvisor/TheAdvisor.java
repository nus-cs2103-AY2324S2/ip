package theadvisor;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * TheAdvisor class represents the main application that manages tasks.
 * It interacts with the user interface, task list, and storage to handle user commands.
 */
public class TheAdvisor implements Serializable {
    private static final String FILE_PATH = "list.bin";

    private Ui ui;

    private Storage storage;

    private TaskList taskList;

    /**
     * Constructs a new TheAdvisor instance with default settings.
     */
    public TheAdvisor() {
        this.ui = new Ui();
        this.storage = new Storage(FILE_PATH);
        this.taskList = new TaskList();
    }

    /**
     * Runs the main loop of the application, handling user commands and interacting with the task list.
     */
    public void run() {
        try {
            taskList = storage.loadList();
            if (taskList == null) {
                taskList = new TaskList();
            }
        } catch (IOException err) {
            System.out.println("No list found, creating empty task list");
            taskList = new TaskList();
        } catch (ClassNotFoundException err) {
            System.out.println("Class mismatch. Check and try again");
            taskList = new TaskList();
        }

        ui.intro();

        while (true) {
            try {
                String str = ui.getUserInput();
                String[] strings = str.split(" ");
                Parser.Prompts prompt = Parser.parsePrompt(strings[0]);
                switch (prompt) {
                case BYE:
                    ui.goodbye();
                    break;
                case LIST:
                    try {
                        ui.printList(taskList);
                    } catch (NullPointerException e) {
                        throw new TheAdvisorException("The task list is empty! Input in some tasks first!");
                    }
                    break;
                case MARK:
                    checkArrayLength(strings, 2, "Invalid format. Make sure that the format is: "
                            + "mark + (number) to mark something on the list as completed.");
                    int markNumber = Integer.parseInt(strings[1]);
                    taskList.markTask(markNumber - 1);
                    storage.saveTasks(taskList);
                    break;
                case UNMARK:
                    checkArrayLength(strings, 2, "Invalid format. Make sure that the format is: "
                            + "unmark + (number) to unmark something on the list.");
                    int unmarkNumber = Integer.parseInt(strings[1]);
                    taskList.unmarkTask(unmarkNumber - 1);
                    storage.saveTasks(taskList);
                    break;
                case DELETE:
                    checkArrayLength(strings, 2, "Invalid format. Make sure that the format is: "
                            + "delete + (number) to delete something from the list.");
                    int deleteNumber = Integer.parseInt(strings[1]);
                    taskList.deleteFromList(deleteNumber - 1);
                    storage.saveTasks(taskList);
                    break;
                case TODO:
                    String todo = str.substring(4);
                    checkEmptyDescription(todo, "The description for todo cannot be empty. " +
                            "The input should be <todo> + description");
                    ToDos toDos = new ToDos(todo);
                    taskList.addToList(toDos);
                    storage.saveTasks(taskList);
                    break;
                case DEADLINE:
                    String due = str.substring(8);
                    checkEmptyDescription(due, "The description for deadline cannot be empty. " +
                            "The input should be <deadline> + description");
                    String[] arrTask = due.split(" /by ");
                    checkArrayLength(arrTask, 2, "Invalid deadline format " +
                            "Please use the correct format: deadline + description + /by + <YYYY-MM-DD HHmm>");
                    try {
                        Deadline deadline = new Deadline(arrTask[0], LocalDateTime.parse(arrTask[1], Task.INPUT_FORMAT));
                        taskList.addToList(deadline);
                        storage.saveTasks(taskList);
                    } catch (DateTimeException e) {
                        throw new TheAdvisorException("Incorrect format of your timestamp! " +
                                "Please input YYYY-MM-DD HHmm");
                    }
                    break;
                case EVENT:
                    String event = str.substring(5);
                    checkEmptyDescription(event, "The description for event cannot be empty. The " +
                            "input should be <event> + description + /from <YYYY-MM-DD HHmm> + /to <YYYY-MM-DD HHmm>");
                    String[] eventArr = event.split(" /from ");
                    checkArrayLength(eventArr, 2, "Invalid event format. " +
                            "The input should be <event> + description + /from <YYYY-MM-DD HHmm> + /to <YYYY-MM-DD HHmm>");
                    String[] timings = eventArr[1].split(" /to");
                    checkArrayLength(timings, 2, "Invalid event format" +
                            "The input should be <event> + description + /from <YYYY-MM-DD HHmm> + /to <YYYY-MM-DD HHmm>");
                    String startStr = timings[0].trim();
                    String endStr = timings[1].trim();
                    try {
                        LocalDateTime start = LocalDateTime.parse(startStr, Task.INPUT_FORMAT);
                        LocalDateTime end = LocalDateTime.parse(endStr, Task.INPUT_FORMAT);
                        Events events = new Events(eventArr[0], start, end);
                        taskList.addToList(events);
                        storage.saveTasks(taskList);
                    } catch (DateTimeException e) {
                        throw new TheAdvisorException("Incorrect format of your timestamp! " +
                                "Please input YYYY-MM-DD HHmm");
                    }
                    break;
                case FIND:
                    String keyword = str.substring(5);
                    checkEmptyDescription(keyword, "Please key in the keyword of what you wish " +
                            "to find please!");
                    taskList.findItem(keyword);
                    break;
                default:
                    throw new TheAdvisorException("Incorrect input, please try again with the correct input of either: "
                            + "todo, event, mark...etc");
                }
            } catch (TheAdvisorException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (IOException e) {
                System.out.println("Please input something >_<*");
            }
        }

    }

    /**
     * The main method to start the application.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        TheAdvisor advisor = new TheAdvisor();
        advisor.run();
    }

    private static void checkEmptyDescription(String description, String errorMessage) throws TheAdvisorException {
        if (description.isEmpty()) {
            throw new TheAdvisorException(errorMessage);
        }
    }

    private static void checkArrayLength(String[] array, int expectedLength, String errorMessage) throws TheAdvisorException {
        if (array.length != expectedLength) {
            throw new TheAdvisorException(errorMessage);
        }
    }
}
