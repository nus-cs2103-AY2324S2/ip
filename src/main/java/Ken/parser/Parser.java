package ken.parser;

import ken.exception.KenException;
import ken.task.TaskList;
import ken.storage.Storage;

import java.util.Scanner;

/**
 * The Parser Class handles the parsing of user commands in the Ken application.
 * It interprets user input and performs corresponding actions on the TaskList and Storage.
 */
public class Parser {

    private Scanner scanner;
    private TaskList taskList;
    private Storage storage;
    private boolean saidBye;

    /**
     * Constructs a new Parser with the specified TaskList and Storage.
     *
     * @param taskList the list of tasks to be manipulated by the parser
     * @param storage  the storage to save and load tasks
     */
    public Parser(TaskList taskList, Storage storage) {
        this.scanner = new Scanner(System.in);
        this.taskList = taskList;
        this.storage = storage;
        this.saidBye = false;
    }

    /**
     * Parses user commands until the "bye" command is received.
     *
     * @throws KenException if there is an error during command parsing or task manipulation
     */
    public void parseUserCommands() throws KenException {
        String command;

        do {
            command = scanner.nextLine();

            if (command.equals("list")) {
                taskList.listTasks();
            } else if (command.startsWith("mark ")) {
                taskList.markTask(Integer.parseInt(command.substring(5)));
            } else if (command.startsWith("unmark ")) {
                taskList.unmarkTask(Integer.parseInt(command.substring(7)));
            } else if (command.startsWith("delete ")) {
                taskList.deleteTask(Integer.parseInt(command.substring(7)));
            } else if (command.startsWith("todo ")) {
                taskList.addTodoTask(command.substring(5));
            } else if (command.startsWith("deadline ")) {
                taskList.addDeadlineTask(command.substring(9));
            } else if (command.startsWith("event ")) {
                taskList.addEventTask(command.substring(6));
            } else if (command.equalsIgnoreCase("bye")) {
                saidBye = true;
            } else if (command.startsWith("seek ")) {
                taskList.findTasks(command.substring(5));
            } else if (command.startsWith("seek")) {
                System.out.println("OKAY I'LL GO HIDE!\n");
            } else if (command.startsWith("todo")) {
                System.out.println("do what?\n");
            } else if (command.startsWith("deadline")) {
                System.out.println("oh no! which line died?\n");
            } else if (command.startsWith("event")) {
                System.out.println("where you going?\n");
            } else if (!command.equals("bye")) {
                System.out.println("don't know what that is\n");
            } else {
            }

        } while (!command.equalsIgnoreCase("bye"));
        storage.saveTasks(taskList.getTasks());
        scanner.close();
    }

    /**
     * Checks if the user has said "bye" during the last interaction.
     *
     * @return true if the user has said "bye," false otherwise
     */
    public boolean hasSaidBye() {
        return saidBye;
    }
}
