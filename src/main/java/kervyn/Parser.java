package kervyn;

import kervyn.Commands.*;
import kervyn.Tasks.TaskList;

public class Parser {
    private Storage storage;
    public Parser(Storage storage) {
        this.storage = storage;
    }

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
