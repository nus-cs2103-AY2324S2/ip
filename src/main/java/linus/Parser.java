package linus;

import java.util.ArrayList;
import java.util.Arrays;

import linus.commands.Command;
import linus.commands.CommandBye;
import linus.commands.CommandDeadline;
import linus.commands.CommandDelete;
import linus.commands.CommandEvent;
import linus.commands.CommandFind;
import linus.commands.CommandList;
import linus.commands.CommandMark;
import linus.commands.CommandSort;
import linus.commands.CommandToDo;
import linus.commands.CommandUnmark;
import linus.exceptions.UnknownCommandException;
import linus.tasks.TaskList;

/**
 * Class that reads user input
 */
public class Parser {

    private Ui ui;
    private TaskList taskList;
    private Storage storage;
    
    /**
     * Constructor for Parser
     * @param taskList TaskList List for storing Tasks
     * @param ui UI for printing to system
     */
    public Parser(TaskList taskList, Ui ui, Storage storage) {
        this.taskList = taskList;
        this.ui = ui;
        this.storage = storage;
    }

    /**
     * Splits the user command and description
     */
    public void readUserInput(String userInput) {
        String[] userInputSplit = userInput.split(" ", 2);
        ArrayList<String> userInputList = new ArrayList<>(Arrays.asList(userInputSplit));

        //This makes sure that the inputs can always be read
        userInputList.add("");

        assert userInputList.size() >= 2: "There should be a user input here";

        String userCommand = userInputList.get(0).toUpperCase();
        String description = userInputList.get(1);
        parseUserInput(userCommand, description);
    }

    /**
     * Reads and understand user input then create a command and executes it
     * @param userCommand String user command
     * @param description String user description
     */
    private void parseUserInput(String userCommand, String description) {
        try {
            Command command;
            switch (userCommand) {
                case "BYE":
                    storage.saveData(taskList);
                    command = new CommandBye(taskList, ui);
                    break;
                case "LIST":
                    command = new CommandList(taskList, ui);
                    break;
                case "MARK": 
                    command = new CommandMark(taskList, ui);
                    break;
                case "UNMARK": 
                    command = new CommandUnmark(taskList, ui);
                    break;
                case "DELETE":
                    command = new CommandDelete(taskList, ui);
                    break;
                case "TODO": 
                    command = new CommandToDo(taskList, ui);
                    break;
                case "DEADLINE":
                    command = new CommandDeadline(taskList, ui);
                    break;
                case "EVENT":
                    command = new CommandEvent(taskList, ui);
                    break;
                case "FIND":
                    command = new CommandFind(taskList, ui);
                    break;
                case "SORT":
                    command = new CommandSort(taskList, ui);
                    break;
                default:
                    throw new UnknownCommandException("Sorry I don't recognize that command :/");
            }
            command.execute(description);
        } catch (Exception e) {
            ui.add(e.getMessage());
        } 
    }
}
