package UiRelated;


import Commands.Command;
import Commands.MarkCommand;
import Commands.UnMarkCommand;
import Commands.ShowListCommand;
import Commands.RemoveCommand;
import Commands.AddToListCommand;
import Tasks.DeadLineTask;
import Tasks.EventTask;
import Tasks.ToDoTask;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The Parser class is responsible for parsing user input and converting it into corresponding commands or tasks.
 */
public class Parser {

    /**
     * Parses the input string and returns the corresponding command.
     *
     * @param input The user input string to be parsed.
     * @return A Command object representing the parsed input.
     * @throws IllegalArgumentException If the input is empty or does not follow the specific pattern.
     * @throws DateTimeParseException  If the input has invalid time format.
     */
    public static Command parseInput(String input) throws IllegalArgumentException, DateTimeParseException {
        input = input.trim();
        if (input.isEmpty()) {
            throw new IllegalArgumentException("Task cannot be empty. Please enter a valid task.");
        }
        String[] parts = input.trim().split("\\s+", 2);
        String command = parts[0];
        int l = parts.length;
        if (l == 1) {
            return CommandWithNoIndex(command);
        }
        String possibleIndexOrTask = parts[1];
        boolean followedByInteger = isFollowedByInteger(possibleIndexOrTask);
        if (followedByInteger && !command.equals("todo")) {
            int index = Integer.parseInt(possibleIndexOrTask);
            return indexRelatedCommand(command, index);
        } else {
            return addCommand(command, possibleIndexOrTask);
        }
    }
    /**
     * Checks if the input is a display command.
     *
     * @param input The input string to be checked.
     * @return True if the input is a display command request, false otherwise.
     */
    public static boolean isDisplayCommand(String input) {
        return input.equals("--commands");
    }

    /**
     * Checks if the input is an exit command.
     *
     * @param s The input string to be checked.
     * @return True if the input is an exit request, false otherwise.
     */
    public static boolean isExitCommand(String s) {
        return s.trim().toLowerCase().equals("bye");
    }

    private static Command CommandWithNoIndex(String command) {
        switch (command) {
            case "list":
                return new ShowListCommand();
            default:
                throw new IllegalArgumentException("Please input valid commands: ");
        }
    }

    private static Command indexRelatedCommand(String command, int index) {
        switch (command) {
            case "unmark":
                return new UnMarkCommand(index);
            case "remove":
                return new RemoveCommand(index);
            case "mark":
                return new MarkCommand(index);
        }
        throw new IllegalArgumentException("Please input valid commands");
    }

    private static boolean isFollowedByInteger(String s) {
        return s.matches("\\d+");
    }

    private static Command addCommand(String command, String possibleTask) throws IllegalArgumentException, DateTimeParseException {
        switch (command) {
            case "todo":
                return new AddToListCommand(new ToDoTask(possibleTask));
            case "deadline":
                int index = possibleTask.lastIndexOf("by");
                String pattern1 = "\\d{2}-\\d{2}";
                String pattern2 = "(?i)\\d{2}-\\d{2} \\d{2}:\\d{2} [ap]m";

                if (index == -1) {
                    throw new IllegalArgumentException("Invalid deadline format. Please specify the task following by its deadline using 'by mm-dd or mm-dd hh:mm am/pm' eg. task1 by 01-14 01:00 pm  or just task1 by 01-14");
                }
                String deadlineInfo = possibleTask.substring(index + 3);
                String taskName = possibleTask.substring(0, index);
                if (!deadlineInfo.trim().matches(pattern1) && !deadlineInfo.trim().matches(pattern2)) {
                    throw new IllegalArgumentException("Invalid deadline format. Please specify the deadline using  these formats: 'by mm-dd or mm-dd hh:mm am/pm' eg. task1 by 01-14 01:00 pm  or task1 by 01-14");
                }
                Command c;
                try {
                    c = new AddToListCommand(new DeadLineTask(deadlineInfo, taskName));
                } catch (DateTimeParseException e) {
                    throw new DateTimeParseException("Please input a valid time format in am or pm. the given input is invalid: ", e.getParsedString(), e.getErrorIndex());
                }
                return c;
            case "event":
                String eventPattern1 = "(?i)\\d{2}:\\d{2} [ap]m to \\d{2}:\\d{2} [ap]m";
                String eventPattern2 = "(?i)\\d{2}-\\d{2} \\d{2}:\\d{2} [ap]m to \\d{2}:\\d{2} [ap]m";
                int posPattern1 = setEventTimingPos(possibleTask, eventPattern1);
                int posPattern2 = setEventTimingPos(possibleTask, eventPattern2);
                String taskN;
                String eventDetail;
                if (posPattern2 != -1) {
                    taskN = possibleTask.substring(0, posPattern2);
                    eventDetail = possibleTask.substring(posPattern2);
                } else if (posPattern1 != -1) {
                    taskN = possibleTask.substring(0, posPattern1);
                    eventDetail = possibleTask.substring(posPattern1);
                } else {
                    throw new IllegalArgumentException("Invalid event format. Please specify the event using event followed by task name and its time in the following format <hh:mm am/pm to hh:mm am/pm or MM-dd hh:mm am/pm to hh:mm am/pm> eg1. event task1 10:00 am to 12:00 am  eg.2 event task1 01-01 10:00 am to 12:00 pm");
                }
                String[] timingDetails = eventDetail.split("to");
                Command c2;
                try {
                    c2 = new AddToListCommand(new EventTask(timingDetails[0], timingDetails[1], taskN));
                } catch (DateTimeParseException e) {
                    throw new DateTimeParseException("Please input a valid time format in am or pm.", e.getParsedString(), e.getErrorIndex());
                }
                return c2;
            default:
                throw new IllegalArgumentException("Please input valid commands");
        }
    }

    private static int setEventTimingPos(String input, String regex) {
        int match;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            match = matcher.start();
        } else {
            match = -1;
        }
        return match;
    }


}
