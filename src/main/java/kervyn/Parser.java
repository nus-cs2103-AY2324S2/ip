package kervyn;

import kervyn.Commands.*;
import kervyn.Tasks.TaskList;

/**
 * The Parser class is responsible for parsing user input and executing commands.
 */
public class Parser {
    private Storage storage;

    /**
     * Constructs a Parser instance using the specified Storage object.
     *
     * @param storage The Storage object that is used to read and write tasks.
     */
    public Parser(Storage storage) {
        this.storage = storage;
    }

    /**
     * Takes the user's input, deduces the command to execute, and triggers the command execution.
     * Commands include bye, list, mark, unmark, delete, todo, deadline, and event.
     * If the command is not recognized, an error message is displayed.
     *
     * @param userInput The raw input string from the user.
     * @param taskList  The current list of tasks which may be modified or used by the commands.
     */
    public void deduceCommand(String userInput, TaskList taskList) {
        String[] processedUserInput = userInput.split(" ");
        switch (processedUserInput[0]) {
            case "bye":
                new ByeCommand(taskList, this.storage).executeCommand();
                break;
            case "list":
                new ListCommand(taskList).executeCommand();
                break;
            case "mark":
                new MarkCommand(taskList, processedUserInput).executeCommand();
                break;
            case "unmark":
                new UnMarkCommand(taskList, processedUserInput).executeCommand();
                break;
            case "delete":
                new DeleteCommand(taskList, processedUserInput).executeCommand();
                break;
            case "todo":
                new ToDoCommand(taskList, userInput).executeCommand();
                break;
            case "deadline":
                new DeadlineCommand(taskList, userInput).executeCommand();
                break;
            case "event":
                new EventCommand(taskList, userInput).executeCommand();
                break;
            default:
                System.out.println("\t I'm not sure what that means. Please specify the type of task eg. todo, deadline or event to create a task.");
                break;
        }
    }
}
