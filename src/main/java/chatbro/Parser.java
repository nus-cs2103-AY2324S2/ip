package chatbro;

/**
 * Parser class parses user input and executes commands.
 */
public class Parser {

    /**
     * Parses the user input and executes the appropriate command.
     * @param input User's input.
     * @return The String returned from command to be executed.
     */
    public static String parseCommand(String input) {
        assert input != null;
        String command = input.split(" ")[0].toLowerCase();
        switch (command) {
        case "bye":
            return Command.BYE.execute(input);
        case "list":
            return Command.LIST.execute(input);
        case "mark":
            return Command.MARK.execute(input);
        case "unmark":
            return Command.UNMARK.execute(input);
        case "delete":
            return Command.DELETE.execute(input);
        case "todo":
            return Command.ADD_TODO.execute(input);
        case "deadline":
            return Command.ADD_DEADLINE.execute(input);
        case "event":
            return Command.ADD_EVENT.execute(input);
        case "interval":
            return Command.ADD_INTERVAL_DEADLINE.execute(input);
        case "help":
            return Command.HELP.execute(input);
        case "find":
            return Command.FIND.execute(input);
        case "protein":
            return Command.PROTEIN.execute(input);
        default:
            String invalidCommandMessage = "Sorry bro, I don't understand that command.";
            return invalidCommandMessage;
        }
    }

    /**
     * Parses a String (in storage format) into a Task object.
     * @param taskString String representing the task (in storage format) to be parsed.
     * @return chatbro.Task object.
     */
    public static Task parseTask(String taskString) throws WrongFileFormatException {
        try {
            assert taskString != null;
            String[] splitString = taskString.split(";;");
            for (String s : splitString) {
                if (s.isEmpty()) { // if any information is missing
                    throw new WrongFileFormatException("savedTasks.txt is in the wrong format.\n"
                            + "Please delete the file and restart the program.");
                }
            }
            String type = splitString[0];
            String status = splitString[1];
            String description = splitString[2];
            boolean isDone;
            if (status.equals("X")) {
                isDone = true;
            } else if (status.equals(" ")) {
                isDone = false;
            } else {
                throw new WrongFileFormatException("savedTasks.txt is in the wrong format.\n"
                        + "Please delete the file and restart the program.");
            }
            switch (type) {
            case "T":
                return new ToDo(description, isDone);
            case "D":
                String by = splitString[3];
                return new Deadline(description, by, isDone);
            case "E":
                String start = splitString[3];
                String end = splitString[4];
                return new Event(description, start, end, isDone);
            case "I":
                String startInterval = splitString[3];
                String endInterval = splitString[4];
                return new IntervalDeadline(description, startInterval, endInterval, isDone);
            default:
                throw new WrongFileFormatException("savedTasks.txt is in the wrong format.\n"
                        + "Please delete the file and restart the program.");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new WrongFileFormatException("savedTasks.txt is in the wrong format.\n"
                    + "Please delete the file and restart the program.");
        }
    }
}
