package objects;
import commands.*;
import exception.*;
import view.EncaseLines;

import static objects.Commands.*;
import static utils.InputUtil.getCommandType;
import static utils.InputUtil.parseIndex;

public class Processor {
    public static void process (String input, TaskList tasks) {
        input = input.trim().toLowerCase();
        String commandType = getCommandType(input);
        Command command = null;

        try {
            switch (commandType) {
                case LIST:
                    command = new ListTasks(tasks);
                    break;

                case MARK:
                    command = new MarkTask(tasks, parseIndex(input));
                    break;

                case UNMARK:
                    command = new UnmarkTask(tasks, parseIndex(input));
                    break;

                case DELETE:
                    command = new DeleteTask(tasks, parseIndex(input));
                    break;


                case TODO:
                case DEADLINE:
                case EVENT:
                    createTask(tasks, input);
                    break;

                case HELP:
                    command = new Help();
                    break;

                default:
                    throw new InvalidCommandException();
            }

        } catch (DukeException e) {
            EncaseLines.display(e.getMessage());

        }

        if (command != null) {
            try {
                command.execute();

            } catch (DukeException e) {
                EncaseLines.display(e.getMessage());
            }
        }
    }
}
