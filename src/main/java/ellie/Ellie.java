package ellie;

import ellie.command.Command;

import java.util.Scanner;

public class Ellie {

    private Ui ui;
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
        ui = new Ui();
    }

    /**
     * Starts the Ellie application.
     * Prompts the user for input and processes commands until an exit command is received.
     */
    public void start() {
        ui.hello();
        Scanner reader = new Scanner(System.in);
        Command command = null;
        String input = reader.nextLine();

        while (true) {
            command = Parser.parse(input);
            if (command.isExit()) {
                break;
            }
            command.run(taskList);
            input = reader.nextLine();
        }

        ui.goodbye();
    }




}
