package tofu;

import tofu.task.Task;
import tofu.command.*;
import tofu.task.*;
import tofu.ui.Ui;


class Parser {

    /**
     * Parses the input string and returns a corresponding Task.
     *
     * @param line the input string input to be parsed
     * @param splitFormat the separator string used to split the input string.
     * @return A Task object corresponding to the input string.
     * @throws TofuException If the format of the input string does not match the expected format
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
     * Parses the input string and returns a corresponding Command for Tofu to execute.
     *
     * @param line the input string to be parsed.
     * @return A Command object corresponding to the input string.
     * @throws TofuException If the format of the input string does not match the expected format
     * for the identified command word.
     */
    public static Command parseToCommand(String line) throws TofuException {
        String[] stringComponents = line.split(" ");
        String commandWord = stringComponents[0].trim();
        Command command;
        int index;

        if (commandWord.equals("list")) {
            command = new ListCommand();
        } else {
            switch (commandWord) {
            case "mark":
                if (stringComponents.length < 2) {
                    throw new TofuException(Ui.emptyDescriptionError());
                }
                index = Integer.parseInt(stringComponents[1]) - 1;
                if (index < 0) {
                    throw new TofuException(Ui.indexError());
                }
                command = new MarkCommand(index, true);
                break;
            case "unmark":
                if (stringComponents.length < 2) {
                    throw new TofuException(Ui.emptyDescriptionError());
                }
                index = Integer.parseInt(stringComponents[1]) - 1;
                if (index < 0) {
                    throw new TofuException(Ui.indexError());
                }
                command = new MarkCommand(index, false);
            case "delete":
                if (stringComponents.length < 2) {
                    throw new TofuException(Ui.emptyDescriptionError());
                }
                index = Integer.parseInt(stringComponents[1]) - 1;
                if (index < 0) {
                    throw new TofuException(Ui.indexError());
                }
                command = new DeleteCommand(index);
                break;
            case "find":
                if (stringComponents.length < 2) {
                    throw new TofuException(Ui.emptyDescriptionError());
                }
                String keyword = line.split(commandWord + " ")[1];
                command = new FindCommand(keyword.trim());
                break;
            case "event":
            case "todo":
            case "deadline":
                if (stringComponents.length < 2) {
                    throw new TofuException(Ui.emptyDescriptionError());
                }
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
