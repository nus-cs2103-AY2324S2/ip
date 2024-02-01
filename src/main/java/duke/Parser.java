package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

    public static ParsedCommand parse(String input) {
        if (input.replaceAll("\\s", "").equals("")) {
            System.out.println("\tTask should not be empty!");
            return new ParsedCommand(CommandType.INVALID, -1);
        }

        String[] parts = input.split(" ", 2);
        CommandType commandType = CommandType.fromString(parts[0]);
        if (commandType == CommandType.LIST || commandType == CommandType.BYE) {
            return new ParsedCommand(commandType, -1);
        }
        if (parts.length < 2 || commandType == CommandType.INVALID) {
            return new ParsedCommand(CommandType.INVALID, -1); // Indicate invalid task number
        }
        if (commandType == CommandType.MARK || commandType == CommandType.UNMARK || commandType == CommandType.DELETE) {
            try {
                int taskNumber = Integer.parseInt(parts[1]);
                if (taskNumber > TaskList.storageFill || taskNumber <= 0) {
                    System.out.println("Task does not exist!");
                    return new ParsedCommand(CommandType.INVALID, -1); // Indicate invalid task number
                }
                return new ParsedCommand(commandType, taskNumber); // success
            } catch (NumberFormatException e) {
                System.out.println("Invalid task number!");
                return new ParsedCommand(CommandType.INVALID, -1); // Indicate invalid task number
            }
        } else {
            return new ParsedCommand(commandType, input); // success
        }
    }

    public static class ParsedCommand {
        private final CommandType commandType;
        private final String input;
        private final int taskNumber;

        // Constructor for commands with details
        public ParsedCommand(CommandType commandType, String input) {
            this.commandType = commandType;
            this.input = input;
            this.taskNumber = -1;
        }

        // Constructor for commands with a task number
        public ParsedCommand(CommandType commandType, int taskNumber) {
            this.commandType = commandType;
            this.taskNumber = taskNumber;
            this.input = null;
        }

        // Getters
        public CommandType getCommandType() {
            return commandType;
        }

        public String getinput() {
            return input;
        }

        public int getTaskNumber() {
            return taskNumber;
        }
    }

    public static Task createTask(CommandType command, String input) {
        String[] parts = input.split(" ", 2);
        Task newTask = null;
        switch (command) {
            case TODO:
                if (parts[1].replaceAll("\\s", "").equals("")) {
                    System.out.println("\tTask should not be empty!");
                } else {
                    newTask = new Todo(parts[1], input);
                }
                break;
            case DEADLINE:
                String[] deadlineParts = parts[1].split(" /by ", 2);
                if (deadlineParts.length < 2) {
                    System.out.println("\tSpecify /by xxx!");
                } else if (deadlineParts[0].replaceAll("\\s", "").equals("")) {
                    System.out.println("\tTask should not be empty!");
                } else if (deadlineParts[1].replaceAll("\\s", "").equals("")) {
                    System.out.println("\tDue date should not be empty!");
                } else {
                    LocalDate dueDate = parseDate(deadlineParts[1]);
                    if (dueDate != null) {
                        newTask = new Deadline(deadlineParts[0], dueDate, input);
                    }
                }
                break;
            case EVENT:
                String[] eventParts = parts[1].split(" /from ", 2);
                if (eventParts[0].replaceAll("\\s", "").equals("")) {
                    System.out.println("\tTask should not be empty!");
                } else if (eventParts.length < 2) {
                    // not enough parts for an event
                    System.out.println("\tSpecify /from xxx and /to xxx!");
                } else {
                    String[] timeParts = eventParts[1].split(" /to ", 2);
                    if (timeParts[0].replaceAll("\\s", "").equals("")) {
                        System.out.println("\tStart time should not be empty!");
                    } else if (timeParts.length < 2) {
                        System.out.println("\tSpecify xxx /to xxx!");
                    } else if (timeParts[1].replaceAll("\\s", "").equals("")) {
                        System.out.println("\tEnd time should not be empty!");
                    } else {
                        // Construct the event string
                        String eventTime = timeParts[0] + " to: " + timeParts[1];
                        LocalDate start = parseDate(timeParts[0]);
                        LocalDate end = parseDate(timeParts[1]);
                        if (start != null && end != null) {
                            newTask = new Event(eventParts[0], eventTime, input, start, end);
                        }
                    }
                }
                break;
            default:
                System.out.println("Default case");
                break;
        }
        return newTask;
    }

    private static LocalDate parseDate(String dateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            // Parse the date string into a LocalDate object
            return LocalDate.parse(dateStr, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("\tUnable to parse the date. Please use the format: yyyy-MM-dd");
            return null;
        }
    }

    protected static Task createTaskFromFile(String line) {
        String[] parts = line.split(" \\| ");
        CommandType commandType = CommandType.fromString(parts[0]);
        boolean isDone = parts[1].trim().equals("1");
        String input = parts[2];
        Task task = createTask(commandType, input);
        if (isDone) {
            task.markWithoutPrint();
        }
        return task;
    }
}
