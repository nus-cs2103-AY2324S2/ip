package application;

import commands.Command;
import commands.ErrorCommand;
import exceptions.InvalidInputFormatException;
import utils.Parser;
import utils.Response;
import utils.TaskList;

/**
 * The Chitty class represents a chatbot-style application for managing tasks.
 */
public class Chitty {
    private final TaskList taskList;

    /**
     * Constructs a Chitty object.
     */
    public Chitty() {
        taskList = TaskList.convertFileStringToTaskList();
    }

    /**
     * The main method to start the chatbot.
     * Initializes the application and handles user input.
     */
    public Command getCommand(String input) {
        try {
            String[] inputStrings = input.split(" ", 2);
            Command command = Parser.parse(inputStrings);
            command.execute(taskList);
            return command;
        } catch (InvalidInputFormatException exception) {
            return new ErrorCommand(Response.getInvalidInputResponse(exception.getMessage() + "\n"));
        } catch (IllegalArgumentException exception) {
            return new ErrorCommand(Response.getInvalidCommandResponse());
        } catch (Exception exception) {
            return new ErrorCommand(Response.getInvalidInputResponse());
        }
    }
}
