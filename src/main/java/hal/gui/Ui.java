package hal.gui;

import hal.command.*;
import hal.task.TaskList;

public class Ui {
    TaskList taskList;

    public Ui(TaskList taskList) {
        this.taskList = taskList;
    }
    public Command getCommand(String userInput) {

        String[] userInputArray = userInput.split(" ");
        String command = userInputArray[0].toLowerCase();

        switch (command) {
        case "bye":
            return new ByeCommand();
        case "list":
            return new ListCommand();
        case "mark":
            return new MarkCommand(Integer.parseInt(userInputArray[1]) - 1);
        case "unmark":
            return new UnmarkCommand(Integer.parseInt(userInputArray[1]) - 1);
        case "todo":
        case "deadline":
        case "event":
            return new AddCommand(userInput);
        case "delete":
            return new DeleteCommand(Integer.parseInt(userInputArray[1]) - 1);
        case "find":
            return new FindCommand(userInputArray[1]);
        }

        return new UnknownCommand();
    }
}
