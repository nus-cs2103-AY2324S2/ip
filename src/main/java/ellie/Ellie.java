package ellie;

import ellie.command.Command;

import java.util.Scanner;

public class Ellie {

    private final TaskList taskList;
    private final Storage storage;

    private Command command;

    /**
     * Constructs an Ellie object.
     * Initializes the storage, task list, and user interface components.
     */
    public Ellie() {
        storage = new Storage("./data/toDoList.txt", "./data");
        taskList = new TaskList(storage);
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
            return Ui.goodbye();
        }

        String response = command.runAndReturnResponse(taskList);
        return response;
    }





}
