package kervyn;

import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import kervyn.Commands.*;
import kervyn.FXControls.DialogBox;
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
     * @return
     */
    public String deduceCommand(String userInput, TaskList taskList, Image kervynImage, VBox dialogContainer) {
        String[] processedUserInput = userInput.split(" ");
        assert(!processedUserInput.equals(""));
        switch (processedUserInput[0]) {
            case "bye":
                new ByeCommand(taskList, this.storage, kervynImage, dialogContainer).executeCommand();
                break;
            case "list":
                new ListCommand(taskList, kervynImage, dialogContainer).executeCommand();
                break;
            case "mark":
                new MarkCommand(taskList, processedUserInput, kervynImage, dialogContainer).executeCommand();
                break;
            case "unmark":
                new UnMarkCommand(taskList, processedUserInput, kervynImage, dialogContainer).executeCommand();
                break;
            case "delete":
                new DeleteCommand(taskList, processedUserInput, kervynImage, dialogContainer).executeCommand();
                break;
            case "todo":
                new ToDoCommand(taskList, userInput, kervynImage, dialogContainer).executeCommand();
                break;
            case "deadline":
                new DeadlineCommand(taskList, userInput, kervynImage, dialogContainer).executeCommand();
                break;
            case "event":
                new EventCommand(taskList, userInput, kervynImage, dialogContainer).executeCommand();
                break;
            case "find":
                new FindCommand(taskList, userInput, kervynImage, dialogContainer).executeCommand();
                break;
            default:
                dialogContainer.getChildren().add(
                        DialogBox.getKervynDialog("\t I'm not sure what that means. Please specify the type of task eg. todo, deadline or event to create a task.", kervynImage)
                );
                break;
        }
        return userInput;
    }
}
