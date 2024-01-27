package kaiyap;

import exceptions.AlreadyExistsException;
import exceptions.InvalidInputException;
import exceptions.KaiYapException;
import exceptions.MissingInputException;
import java.util.ArrayList;
import java.util.Scanner;

public class KaiYap {

    private final Ui ui;
    private final Storage storage;
    private final TaskList taskList;
    private final Parser parser;

    public enum TaskType {
        TODO, DEADLINE, EVENT
    }

    public KaiYap() {
        this.ui = new Ui();
        this.taskList = new TaskList(ui);
        this.ui.setTaskList(this.taskList);
        this.storage = new Storage(ui, taskList, System.getProperty("user.home") + "/data/", "kaiyap.txt");
        this.parser = new Parser();
    }

    /**
     * Starts the chat interface for the KaiYap application.
     * This method initiates a loop that continuously reads user input from the console,
     * processes it, and performs actions such as listing tasks, marking tasks as done or undone,
     * deleting tasks, or adding new tasks. The loop terminates when the user inputs "bye".
     */
    public void startChat() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine().strip();
        while (!input.equals("bye")) {
            String action = parser.decideAction(input);
            switch (action) {
            case "listInputs":
                ui.listInputs();
                break;
            case "mark":
                try {
                    this.markAsDone(input);
                } catch (KaiYapException e) {
                    ui.printError(e.getMessage());
                }
                break;
            case "unmark":
                try {
                    this.unmarkDone(input);
                } catch (KaiYapException e) {
                    ui.printError(e.getMessage());
                }
                break;
            case "find":
                try {
                    this.findTasks(input);
                } catch (KaiYapException e) {
                    ui.printError(e.getMessage());
                }
                break;
            case "delete":
                try {
                    this.deleteTask(input);
                } catch (KaiYapException e) {
                    ui.printError(e.getMessage());
                }
                break;
            default:
                Task task = taskList.taskCreator(input);
                if (task != null) {
                    this.taskList.add(task);
                    this.echo(task);
                    storage.saveData();
                }
            }
            input = sc.nextLine().strip();
        }
    }

    /**
     * Deletes a task from the task list based on the user's input.
     * The input should contain a numeric index that corresponds to the task's position in the list.
     *
     * @param input A string containing the command and index of the task to be deleted.
     * @throws KaiYapException If the input is missing, invalid, or if the task does not exist.
     */
    void findTasks(String input) throws KaiYapException {
        if (input.length() <= 5) {
            throw new MissingInputException("\tSorry, it seems like there is some missing input. Please try again! UwU :3");
        }
        String searchPhrase = input.substring(5).toUpperCase();
        ArrayList<Task> tasksFound = new ArrayList<>();
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).getListItem().toUpperCase().contains(searchPhrase)) {
                tasksFound.add(taskList.get(i));
            }
        }
        if (tasksFound.isEmpty()) {
            throw new InvalidInputException("\tSorry, no such tasks exist. Please try again! UwU :3");
        } else {
            ui.printTasksFound(tasksFound);
        }
    }

    /**
     * Deletes a task from the task list based on the user's input.
     * The input should contain a numeric index that corresponds to the task's position in the list.
     *
     * @param input A string containing the command and index of the task to be deleted.
     * @throws KaiYapException If the input is missing, invalid, or if the task does not exist.
     */
    void deleteTask(String input) throws KaiYapException {
        if (input.length() <= 7) {
            throw new MissingInputException("\tSorry, it seems like there is some missing input. Please try again! UwU :3");
        }
        int numericIndex = Integer.parseInt(input.substring(7).strip()) - 1;
        if (numericIndex >= this.taskList.size()) {
            throw new InvalidInputException("\tSorry, this task does not exist. Please try again! UwU :3");
        } else {
            ui.printTaskRemoved(taskList.get(numericIndex), taskList.size() - 1);
            taskList.remove(numericIndex);
        }
    }

    /**
     * Displays the information about a newly added task.
     * It prints a message confirming that the task has been added to the list
     * and shows the total number of tasks currently in the list.
     *
     * @param task The task that has been added to the task list.
     */
    public void echo(Task task) {
        System.out.println("\t____________________________________________________________\n" +
                "\tGot it. I've added this task:\n\t\t" + task.toString() +
                "\n\tYou now have " + this.taskList.size() + (this.taskList.size() == 1 ? " task" : " tasks") + " in the list.\n" +
                "\t____________________________________________________________\n");
    }

    /**
     * Marks a task as done in the task list based on the user's input.
     * The input should contain a numeric index that corresponds to the task's position in the list.
     * If the task is already marked as done, it throws an exception.
     *
     * @param index A string containing the command and index of the task to be marked as done.
     * @throws KaiYapException If the input is missing, invalid, or if the task is already marked as done.
     */
    public void markAsDone(String index) throws KaiYapException {
        if (index.length() <= 5) {
            throw new MissingInputException("\tSorry, it seems like there is some missing input. Please try again! UwU :3");
        }
        int numericIndex = Integer.parseInt(index.substring(5).strip()) - 1;
        if (numericIndex >= this.taskList.size()) {
            throw new InvalidInputException("\tSorry, this task does not exist. Please try again! UwU :3");
        } else if (taskList.get(numericIndex).isTaskDone()) {
            throw new AlreadyExistsException("\tThis task has already been marked as done. Great job!");
        } else {
            taskList.get(numericIndex).setTaskDone(true);
            System.out.println(
                    "\t____________________________________________________________\n" +
                            "\tNice! I've marked this task as done:\n" +
                            "\t\t" + taskList.get(numericIndex).toString() +
                            "\n\t____________________________________________________________"
            );
            storage.saveData();
        }
    }

    /**
     * Marks a task as not done in the task list based on the user's input.
     * The input should contain a numeric index that corresponds to the task's position in the list.
     * If the task is already marked as not done, it throws an exception.
     *
     * @param index A string containing the command and index of the task to be marked as not done.
     * @throws KaiYapException If the input is missing, invalid, or if the task is already marked as not done.
     */
    public void unmarkDone(String index) throws KaiYapException {
        if (index.length() <= 7) {
            throw new MissingInputException("\tSorry, it seems like there is some missing input. Please try again! UwU :3");
        }
        int numericIndex = Integer.parseInt(index.substring(7).strip()) - 1;
        if (numericIndex >= taskList.size()) {
            throw new InvalidInputException("\tSorry, this task does not exist. Please try again! UwU :3");
        } else if (!taskList.get(numericIndex).isTaskDone()) {
            throw new AlreadyExistsException("\tThis task has already been marked as undone. Good luck!");
        } else {
            taskList.get(numericIndex).setTaskDone(false);
            System.out.println(
                    "\t____________________________________________________________\n" +
                            "\tOK, I've marked this task as not done yet:\n" +
                            "\t\t" + taskList.get(numericIndex).toString() +
                            "\n\t____________________________________________________________"
            );
            storage.saveData();
        }
    }

    /**
     * The main method to start the KaiYap application.
     * It creates an instance of KaiYap, loads data, starts the chat interface, and handles the exit process.
     *
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        KaiYap yap = new KaiYap();
        yap.ui.sayHello();
        yap.storage.loadData();
        yap.startChat();
        yap.ui.sayBye();
    }
}
