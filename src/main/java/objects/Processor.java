package objects;
import exception.DukeException;
import exception.InvalidCommandException;
import view.EncaseLines;

import static objects.Commands.*;
import static utils.InputUtil.getCommandType;

public class Processor {
    public static void process (String input, TaskList tasks) {
        input = input.trim().toLowerCase();
        String command = getCommandType(input);

        try {
            switch (command) {
                case LIST:
                    listTasks(tasks);
                    break;

                case MARK:
                case UNMARK:
                case DELETE:
                    processTask(tasks, input);
                    break;

                case TODO:
                case DEADLINE:
                case EVENT:
                    createTask(tasks, input);
                    break;

                case HELP:
                    printHelp();
                    break;

                default:
                    throw new InvalidCommandException();
            }

        } catch (DukeException e) {
            EncaseLines.display(e.getMessage());

        }
    }
}
