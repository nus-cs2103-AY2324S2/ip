package ellie;

import ellie.command.Command;
import ellie.command.InvalidCommand;
import ellie.exception.InvalidTaskInputException;
import ellie.exception.UnknownInputException;
import ellie.task.Deadline;
import ellie.task.Event;
import ellie.task.Task;
import ellie.task.Todo;

import java.util.Scanner;

public class Ellie {

    private Ui ui;
    private final TaskList taskList;
    private final Storage storage;

    private Command command;

    public Ellie() {
        storage = new Storage("./data/toDoList.txt");
        taskList = new TaskList(storage);
        ui = new Ui();
    }

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
