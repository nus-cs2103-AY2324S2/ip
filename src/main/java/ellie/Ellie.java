package ellie;

import ellie.command.Command;

/**
 * Represents the main class of the Ellie task management application.
 */
public class Ellie {

    private final TaskList taskList;
    private final Storage storage;

    private Command command;

    private Boolean isExit;

    /**
     * Constructs an Ellie object.
     * Initializes the storage, task list, and user interface components.
     */
    public Ellie() {
        storage = new Storage("./data/toDoList.txt", "./data");
        taskList = new TaskList(storage);
        isExit = false;
    }


    /**
     * Returns a response to the user input.
     *
     * @param input The user input.
     * @return The response to the user input.
     */
    public String getResponse(String input) {
        command = Parser.parse(input);

        if (command.isExit()) {
            isExit = true;
            return Ui.showGoodbyeMessage();
        }

        String response = command.runAndReturnResponse(taskList);
        return response;
    }

    /**
     * Returns true if the user has entered the exit command.
     *
     * @return True if the user has entered the exit command.
     */
    public Boolean isExit() {
        return isExit;
    }





}
