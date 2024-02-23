package duke;

import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import duke.tasklist.TaskList;
import duke.ui.Ui;


/**
 * Represents the main class for the Duke application.
 * This class initializes the application, loads existing tasks from storage, and processes user commands.
 */

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a new Duke application instance. Initializes the UI, storage, and task list components.
     * Tries to loadFromFile any existing tasks from storage; if unsuccessful, starts with an empty task list.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage();
        try {
            tasks = new TaskList(storage.loadFromFile());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Starts the Duke application. Enters a loop to read and execute commands until the user issues the 'bye' command.
     */
    public void run() {
        System.out.println(ui.showHello());
        boolean isExit = false;
        while (!isExit) {
            try {
                String[] fullCommand = Parser.parse(ui.readCommand());
                String command = fullCommand[0];

                if (command.equals("bye")) {
                    System.out.println(ui.showBye());
                    isExit = true;
                    break;
                } else if (command.equals("list")) {
                    System.out.println(ui.showList(tasks));
                } else if (command.equals("mark") || command.equals("unmark") || command.equals("todo")
                        || command.equals("deadline") || command.equals("event") || command.equals("delete")) {
                    if (fullCommand.length > 1) {
                        if (command.equals("mark")) {
                            int indexToMark = Integer.parseInt(fullCommand[1]) - 1;
                            Task task = tasks.getTasks().get(indexToMark);
                            task.markComplete();
                            System.out.println(ui.showTask(" Nice! I've marked this task as done:", task));
                            storage.saveToFile(tasks.getTasks());
                        } else if (command.equals("unmark")) {
                            int indexToUnmark = Integer.parseInt(fullCommand[1]) - 1;
                            Task task = tasks.getTasks().get(indexToUnmark);
                            task.markIncomplete();
                            System.out.println(ui.showTask(" OK, I've marked this task as not done yet:", task));
                            storage.saveToFile(tasks.getTasks());
                        } else if (command.equals("todo")) {
                            Task task = new ToDo(fullCommand[1]);
                            tasks.addTask(task);
                            System.out.println(ui.showTaskWithNum(" Got it. I've added this task:\n  ", task, tasks));
                            storage.saveToFile(tasks.getTasks()); // Save after adding new task
                        } else if (command.equals("deadline")) {
                            Task task = new Deadline(fullCommand[1]);
                            tasks.addTask(task);
                            System.out.println(ui.showTaskWithNum("Got it. I've added this task:\n", task, tasks));
                            storage.saveToFile(tasks.getTasks()); // Save after adding new task
                        } else if (command.equals("event")) {
                            Task task = new Event(fullCommand[1]);
                            tasks.addTask(task);
                            System.out.println(ui.showTaskWithNum("Got it. I've added this task:\n", task, tasks));
                            storage.saveToFile(tasks.getTasks()); // Save after adding new task
                        } else {
                            int deleteIndex = Integer.parseInt(fullCommand[1]) - 1;
                            Task deletedTask = tasks.deleteTask(deleteIndex);
                            String msg = " Noted. I've removed this task:\n";
                            System.out.println(ui.showTaskWithNum(msg, deletedTask, tasks));

                            storage.saveToFile(tasks.getTasks());
                        }
                    } else {
                        throw new DukeException("____________________________________________________________\n"
                                + " OOPS! Your Only Friend cannot take in an empty "
                                + command + "\n Make sure " + command + "has a description!\n"
                                + "____________________________________________________________\n");
                    }

                } else if (command.equals("find")) {
                    if (fullCommand.length < 2) {
                        throw new DukeException("OOPS! Please make sure you enter a keyword");
                    }

                    String keyword = fullCommand[1];
                    TaskList foundTasks = tasks.findTasks(keyword);
                    System.out.println(ui.showMatchingList(foundTasks));
                } else {
                    throw new DukeException(" OOPS! Turns out Your Only Friend does not know what that is :(\n");
                }
            } catch (DukeException e) {
                System.out.println(ui.showError(e.getMessage()));
            }
        }
    }

    /**
     * Processes the user input and returns a response based on the command given. The method supports
     * various commands such as "bye", "list", "mark", "unmark", "todo", "deadline", "event", "delete", and "find".
     * It handles each command by performing the corresponding actions, such as
     * adding a task, marking a task as complete, listing tasks, or finding tasks based on keywords.
     * For commands that modify the task list, changes are saved to the storage file.
     *
     * @param input The full user input command.
     * @return A string response to be displayed to the user. This response could be a confirmation of the action taken,
     *         a list of tasks, a message indicating an error, or any other feedback related to the command.
     * @throws DukeException If the command is invalid, lacks necessary details, or if any other error occurs during the
     *         processing of the command. The exception contains a message that is suitable for display to the user.
     */
    String getResponse(String input) {
        try {
            String[] fullCommand = Parser.parse(input.trim().toLowerCase());
            String command = fullCommand[0];

            if (command.equals("bye")) {
                return ui.showBye();
            } else if (command.equals("list")) {
                return ui.showList(tasks);
            } else if (command.equals("mark") || command.equals("unmark") || command.equals("todo")
                    || command.equals("deadline") || command.equals("event") || command.equals("delete")) {
                if (fullCommand.length > 1) {
                    if (command.equals("mark")) {
                        int indexToMark = Integer.parseInt(fullCommand[1]) - 1;
                        Task task = tasks.getTasks().get(indexToMark);
                        task.markComplete();
                        storage.saveToFile(tasks.getTasks());
                        return ui.showTask(" Nice! I've marked this task as done:", task);
                    } else if (command.equals("unmark")) {
                        int indexToUnmark = Integer.parseInt(fullCommand[1]) - 1;
                        Task task = tasks.getTasks().get(indexToUnmark);
                        task.markIncomplete();
                        storage.saveToFile(tasks.getTasks());
                        return ui.showTask(" OK, I've marked this task as not done yet:", task);
                    } else if (command.equals("todo")) {
                        Task task = new ToDo(fullCommand[1]);
                        tasks.addTask(task);
                        storage.saveToFile(tasks.getTasks()); // Save after adding new task
                        return ui.showTaskWithNum(" Got it. I've added this task:\n  ", task, tasks);
                    } else if (command.equals("deadline")) {
                        Task task = new Deadline(fullCommand[1]);
                        tasks.addTask(task);
                        storage.saveToFile(tasks.getTasks()); // Save after adding new task
                        return ui.showTaskWithNum("Got it. I've added this task:\n", task, tasks);
                    } else if (command.equals("event")) {
                        Task task = new Event(fullCommand[1]);
                        tasks.addTask(task);
                        storage.saveToFile(tasks.getTasks()); // Save after adding new task
                        return ui.showTaskWithNum("Got it. I've added this task:\n", task, tasks);
                    } else {
                        int deleteIndex = Integer.parseInt(fullCommand[1]) - 1;
                        Task deletedTask = tasks.deleteTask(deleteIndex);
                        storage.saveToFile(tasks.getTasks());
                        return ui.showTaskWithNum(" Noted. I've removed this task:\n", deletedTask, tasks);
                    }
                } else {
                    throw new DukeException("____________________________________________________________\n"
                            + " OOPS! Your Only Friend cannot take in an empty "
                            + command + "\n Make sure " + command + "has a description!\n"
                            + "____________________________________________________________\n");
                }

            } else if (command.equals("find")) {
                if (fullCommand.length < 2) {
                    throw new DukeException("OOPS! Please make sure you enter a keyword");
                }

                String keyword = fullCommand[1];
                TaskList foundTasks = tasks.findTasks(keyword);
                return ui.showMatchingList(foundTasks);
            } else {
                throw new DukeException(" OOPS! Turns out Your Only Friend does not know what that is :(\n");
            }
        } catch (DukeException e) {
            return ui.showError(e.getMessage());
        }
    }

    /**
     * Serves as the entry point of the application.
     * @param args Command line arguments (unused).
     */
    public static void main(String[] args) {
        new Duke().run();
    }
}
