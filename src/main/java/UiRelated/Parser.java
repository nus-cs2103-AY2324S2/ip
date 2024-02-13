package UiRelated;

import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Commands.AddToListCommand;
import Commands.ClearListCommand;
import Commands.Command;
import Commands.FindCommand;
import Commands.MarkCommand;
import Commands.RemoveCommand;
import Commands.ShowListCommand;
import Commands.UnMarkCommand;
import Tasks.DeadLineTask;
import Tasks.EventTask;
import Tasks.ToDoTask;


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
     */
    public static Command parseInput(String input) throws IllegalArgumentException {
        input = input.trim();
        if (input.isEmpty()) {
            throw new IllegalArgumentException("Task cannot be empty. Please enter a valid task.");
        }
        String[] parts = input.trim().split("\\s+", 2);
        String command = parts[0].toLowerCase();
        int l = parts.length;
        assert l >= 1 : "empty string handled";
        if (l == 1) {
            return commandWithNoIndex(command);
        }
        String possibleIndexOrTask = parts[1].toLowerCase();
        if (isFindCommand(command)) {
            return findCommand(possibleIndexOrTask);
        }
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
     * @param command The input string to be checked.
     * @return True if the input is an exit request, false otherwise.
     */

    private static Command findCommand(String command) {
        return new FindCommand(command);
    }

    private static Command commandWithNoIndex(String command) {
        switch (command) {
        case "list":
            return new ShowListCommand();
        case "clearl":
            return new ClearListCommand();
        default:
            throw new IllegalArgumentException("Please input valid commands. Type --commands for list of commands");
        }
    }
    private static boolean isFindCommand(String input) {
        return input.equals(("find"));
    }

    private static Command indexRelatedCommand(String command, int index) {
        switch (command) {
        case "unmark":
            return new UnMarkCommand(index);
        case "remove":
            return new RemoveCommand(index);
        case "mark":
            return new MarkCommand(index);
        default:
            throw new IllegalArgumentException("Please input valid commands. Type --commands for list of commands");
        }
    }

    private static boolean isFollowedByInteger(String s) {
        return s.matches("\\d+");
    }

    private static Command addCommand(
            String command, String possibleTask) throws IllegalArgumentException {
        switch (command) {
        case "todo":
            return new AddToListCommand(new ToDoTask(possibleTask));
        case "deadline":
            int index = possibleTask.lastIndexOf("by");
            String pattern1 = "\\d{2}-\\d{2}";
            String pattern2 = "(?i)\\d{2}-\\d{2} \\d{2}:\\d{2} [ap]m";

            if (index == -1) {
                throw new IllegalArgumentException("Invalid time format for deadline command\n"
                                                    + "type --commands to see valid format");
            }
            String deadlineInfo = possibleTask.substring(index + 3);
            String taskName = possibleTask.substring(0, index);
            if (!deadlineInfo.trim().matches(pattern1) && !deadlineInfo.trim().matches(pattern2)) {
                throw new IllegalArgumentException("Invalid time format for deadline command\n"
                                                    + " type --commands to see valid format");
            }
            Command c;
            try {
                c = new AddToListCommand(new DeadLineTask(deadlineInfo, taskName));
            } catch (DateTimeParseException e) {
                throw new IllegalArgumentException("Please input a valid time format in am or pm.");
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
                throw new IllegalArgumentException("Invalid time format for event command\n"
                                                    + " type --commands to see valid format");
            }
            String[] timingDetails = eventDetail.split("to");
            Command c2;
            try {
                c2 = new AddToListCommand(new EventTask(timingDetails[0], timingDetails[1], taskN));
            } catch (DateTimeParseException e) {
                throw new IllegalArgumentException("Please input a valid time format in am or pm.");
            }
            return c2;
        default:
            throw new IllegalArgumentException("Please input valid commands. Type --commands for list of commands");
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
