package duke;

class Parser {

    /**
     * Parsed the line input and returns a Task.
     *
     * @param line the String input to be parsed
     * @param splitFormat the separator for the input
     * @return a Task corresponding to the input
     * @throws DukeException if the format of the input does not match
     */
    public static Task parseToTask(String line, String splitFormat) throws DukeException {
        Task task;
        String[] stringComponents = line.split(splitFormat);
        for (int i = 0; i < stringComponents.length; i++) {
            stringComponents[i] = stringComponents[i].trim();
        }
        boolean isDone = Boolean.valueOf(stringComponents[1]);

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
            throw new DukeException("Corrupted Data!");
        }
        return task;
    }

    /**
     * Parsed the line input and returns a Command.
     *
     * @param line the String input to be parsed
     * @return a Command corresponding to the input
     * @throws DukeException if the format of the input does not match
     */
    public static Command parseToCommand(String line) throws DukeException {
        String[] stringComponents = line.split(" ");
        String commandWord = stringComponents[0].trim();
        Command command;
        int index;

        switch (commandWord) {
        case "list":
            command = new ListCommand();
            break;
        case "bye":
            command = new ExitCommand();
            break;
        case "mark":
            // Exception handling and splitting the string
            if (stringComponents.length != 2) {
                throw new DukeException("Invalid input format!");
            }
            index = Integer.parseInt(stringComponents[1]) - 1;
            command = new MarkCommand(index, true);
            break;
        case "unmark":
            // Exception handling and splitting the string
            if (stringComponents.length != 2) {
                throw new DukeException("Invalid input format!");
            }
            index = Integer.parseInt(stringComponents[1]) - 1;
            command = new MarkCommand(index, false);
        case "delete":
            if (stringComponents.length < 2) {
                throw new DukeException("Invalid input format!");
            }
            index = Integer.parseInt(stringComponents[1]) - 1;
            command = new DeleteCommand(index);
        case "find":
            if (stringComponents.length < 2) {
                throw new DukeException("Invalid input format!");
            }
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
            throw new DukeException("I'm sorry, I don't know what that means.\n" +
                    "Please input valid commands (i.e. [command] [description]).\n" +
                    "You can choose from the following available commands:\n" +
                    "   * todo [desc]\n" +
                    "   * event [desc] /from [desc] /to [desc]\n" +
                    "   * deadline [desc] /by [desc]\n" +
                    "   * list\n" +
                    "   * mark [number]\n" +
                    "   * unmark [number]\n" +
                    "   * delete [number]");
        }
        return command;
    }
}
