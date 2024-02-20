package tofu;

import tofu.task.Task;
import tofu.command.*;
import tofu.task.*;
import tofu.ui.Ui;


class Parser {

    /**
     * Parsed the line input and returns a Task.
     *
     * @param line the String input to be parsed
     * @param splitFormat the separator for the input
     * @return a Task corresponding to the input
     * @throws TofuException if the format of the input does not match
     */
    public static Task parseToTask(String line, String splitFormat) throws TofuException {
        Task task;
        String[] stringComponents = line.split(splitFormat);
        for (int i = 0; i < stringComponents.length; i++) {
            stringComponents[i] = stringComponents[i].trim();
        }
        boolean isDone = Boolean.parseBoolean(stringComponents[1]);

        switch (stringComponents[0]) {
        case "T":
            task = new ToDo(stringComponents[2], isDone);
            break;
        case "D":
            task = new Deadline(stringComponents[2], isDone, stringComponents[3]);
            break;
        case "E":
            task = new Event(stringComponents[2], isDone, stringComponents[3], stringComponents[4]);
            break;
        default:
            throw new TofuException("Corrupted Data!");
        }
        return task;
    }

    /**
     * Parsed the line input and returns a Command.
     *
     * @param line the String input to be parsed
     * @return a Command corresponding to the input
     * @throws TofuException if the format of the input does not match
     */
    public static Command parseToCommand(String line) throws TofuException {
        String[] stringComponents = line.split(" ");
        String commandWord = stringComponents[0].trim();
        Command command;
        int index;

        if (commandWord.equals("list")) {
            command = new ListCommand();
        } else {
            if (stringComponents.length < 2) {
                throw new TofuException(Ui.emptyDescriptionError());
            }
            switch (commandWord) {
            case "mark":
                index = Integer.parseInt(stringComponents[1]) - 1;
                if (index < 0) {
                    throw new TofuException(Ui.indexError());
                }
                command = new MarkCommand(index, true);
                break;
            case "unmark":
                index = Integer.parseInt(stringComponents[1]) - 1;
                if (index < 0) {
                    throw new TofuException(Ui.indexError());
                }
                command = new MarkCommand(index, false);
            case "delete":
                index = Integer.parseInt(stringComponents[1]) - 1;
                if (index < 0) {
                    throw new TofuException(Ui.indexError());
                }
                command = new DeleteCommand(index);
                break;
            case "find":
                String keyword = line.split(commandWord + " ")[1];
                command = new FindCommand(keyword.trim());
                break;
            case "event":
            case "todo":
            case "deadline":
                String description = line.split(commandWord + " ")[1];
                command = new AddCommand(commandWord, description);
                break;
            default:
                throw new TofuException(Ui.unknownCommandError());
            }
        }
        return command;
    }
}
