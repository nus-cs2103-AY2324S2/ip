package objects;
import view.EncaseLines;

import static objects.Commands.*;
import static objects.FilePath.*;
import static objects.Utils.*;

public class Processor {
    public static void greet() {
        System.out.println(getFile(LOGO_PATH));
        EncaseLines.display(getFile(GREETING_PATH));
    }

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

    public static void exit() {
        EncaseLines.display(getFile(EXIT_PATH));
    }
}
