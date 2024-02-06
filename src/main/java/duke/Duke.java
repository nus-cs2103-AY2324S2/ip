package duke;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * A chatbot programme named Homie that helps you keep track
 * of to-do tasks, deadlines and events. Date and time can be specified for deadlines and events.
 * Other functions include adding tasks, finding tasks, marking or un-marking tasks as done,
 * deleting tasks and listing tasks.
 */
public class Duke {
    private static final String CURRENT_DIR = System.getProperty("user.dir");
    public static final Path FILE_PATH = Paths.get(CURRENT_DIR, "src", "main", "java", "duke", "data", "data.txt");
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    /**
     * Constructor for Duke class
     * @param filePath File path of storage text file
     */
    public Duke(Path filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        tasks = new TaskList(storage.loadTasksFromFile());
        ui.showLoadingError();
        tasks = new TaskList();
    }
    /**
     * Run the chatbot application.
     */
    public void run() {
        this.ui.showWelcome();
        boolean isExit = false;

        Scanner scanner = new Scanner(System.in); // Create scanner
        String command = scanner.nextLine();

        String[] tempArr;
        Task currTask;

        while (!command.equals("bye")) {

            try {
                Duke.checkCommand(command);
            } catch (DukeException e) {
                System.err.println(e.getMessage());
                command = scanner.nextLine(); // Read next command
                continue;
            }

            if (command.equals("list")) {
                this.ui.showDivider();
                this.ui.showListMessage();
                this.tasks.showTasks();
                this.ui.showDivider();
                command = scanner.nextLine(); // Read next command
                continue;
            }

            if (command.startsWith("delete")) {
                tempArr = command.split(" ");
                currTask = this.tasks.getTask(Integer.parseInt(tempArr[1]));
                this.tasks.deleteTask(Integer.parseInt(tempArr[1]) - 1);
                this.ui.showDivider();
                this.ui.showDeleteMessage(currTask, tasks);
                this.ui.showDivider();
                this.storage.updateStorageFile(this.tasks);
                command = scanner.nextLine(); // Read next command
                continue;
            }

            if (command.startsWith("find")) {
                tempArr = command.split(" ");
                this.ui.showDivider();
                this.ui.showFindMessage();
                this.tasks.findTask(tempArr[1]);
                this.ui.showDivider();
            }

            if (command.contains("todo") || command.contains("deadline") || command.contains("event")) {
                tempArr = command.split(" ");
                switch (tempArr[0]) {
                case ("todo"):
                    try {
                        this.ui.showDivider();
                        Todo currTodo = new Todo(command.substring(5));
                        this.tasks.addTask(currTodo);
                        this.ui.showToDoMessage(currTodo, this.tasks);
                        this.ui.showDivider();
                        this.storage.updateStorageFile(this.tasks);
                    } catch (TodoException e) {
                        System.err.println(e.getMessage());
                    }
                    break;

                case ("deadline"):
                    this.ui.showDivider();
                    tempArr = (command.substring(9)).split("/");
                    Deadline currDeadline = new Deadline(tempArr[0], tempArr[1].substring(3));
                    this.tasks.addTask(currDeadline);
                    this.ui.showDeadlineMessage(currDeadline, this.tasks);
                    this.ui.showDivider();
                    this.storage.updateStorageFile(this.tasks);
                    break;

                case ("event"):
                    this.ui.showDivider();
                    tempArr = (command.substring(6)).split("/");
                    Event currEvent = new Event(tempArr[0], tempArr[1].substring(5), tempArr[2].substring(3));
                    this.tasks.addTask(currEvent);
                    this.ui.showEventMessage(currEvent, this.tasks);
                    this.ui.showDivider();
                    this.storage.updateStorageFile(this.tasks);
                    break;
                default:
                    break;
                }
                command = scanner.nextLine(); // Read next command
                continue;
            }

            if (command.contains("mark")) {
                tempArr = command.split(" ");
                currTask = this.tasks.getTask(Integer.parseInt(tempArr[1]) - 1);
                switch (tempArr[0]) {
                case ("mark"):
                    this.ui.showDivider();
                    this.tasks.markTask(Integer.parseInt(tempArr[1]) - 1);
                    this.ui.showMarkMessage(currTask);
                    this.ui.showDivider();
                    this.storage.updateStorageFile(this.tasks);
                    break;

                case ("unmark"):
                    this.ui.showDivider();
                    this.tasks.unMarkTask(Integer.parseInt(tempArr[1]) - 1);
                    this.ui.showUnmarkMessage(currTask);
                    this.ui.showDivider();
                    this.storage.updateStorageFile(this.tasks);
                    break;

                default:
                    this.ui.showWrongCommand();
                }
                command = scanner.nextLine(); // Read next command
                continue;
            }

            command = scanner.nextLine(); // Read next command
        }

    }

    public static void main(String[] args) {
        new Duke(FILE_PATH).run();
    }

    /**
     * Check if command is valid or not
     * Throws a Duke Exception if command is invalid
     * @param command String representation of input command
     * @throws DukeException If command is not recognised
     */
    public static void checkCommand(String command) throws DukeException {
        String line = "____________________________________________________________";
        if (!(command.startsWith("todo") || command.startsWith("deadline") || command.startsWith("event")
                || command.startsWith("list") || command.startsWith("bye") || command.startsWith("delete")
                || command.contains("mark") || command.contains("find"))) {
            throw new DukeException("\n" + line + "\nOPPS!!! I'm sorry, but I don't know what that means :-(\n" + line);
        }
    }
}
