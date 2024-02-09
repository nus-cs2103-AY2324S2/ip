package duke;

class Parser {

    public static Task parseToTask(String line, String splitFormat) throws DukeException {
        Task task;
        String[] stringSplit = line.split(splitFormat);
        for (int i = 0; i < stringSplit.length; i++) {
            stringSplit[i] = stringSplit[i].trim();
        }
        boolean isDone = Boolean.valueOf(stringSplit[1]);

        switch (stringSplit[0]) {
        case "T":
            task = new ToDo(stringSplit[2], isDone);
            break;
        case "D":
            task = new Deadline(stringSplit[2], isDone, stringSplit[3]);
            break;
        case "E":
            task = new Event(stringSplit[2], isDone, stringSplit[3], stringSplit[4]);
            break;
        default:
            throw new DukeException("Corrupted Data!");
        }
        return task;
    }

    public static Command parseToCommand(String line) throws DukeException {
        String[] stringSplit = line.split(" ");
        String commandWord = stringSplit[0].trim();
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
            if (stringSplit.length != 2) {
                throw new DukeException("Invalid input format!");
            }
            index = Integer.parseInt(stringSplit[1]) - 1;
            command = new MarkCommand(index, true);
            break;
        case "unmark":
            // Exception handling and splitting the string
            if (stringSplit.length != 2) {
                throw new DukeException("Invalid input format!");
            }
            index = Integer.parseInt(stringSplit[1]) - 1;
            command = new MarkCommand(index, false);
        case "delete":
            if (stringSplit.length < 2) {
                throw new DukeException("Invalid input format!");
            }
            index = Integer.parseInt(stringSplit[1]) - 1;
            command = new DeleteCommand(index);
        case "find":
            if (stringSplit.length < 2) {
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
