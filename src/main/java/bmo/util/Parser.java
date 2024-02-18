package bmo.util;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import bmo.ui.Ui;
import bmo.command.Command;
import bmo.command.DefaultCommand;
import bmo.command.DueCommand;
import bmo.command.EventCommand;
import bmo.command.ExitCommand;
import bmo.command.GreetCommand;
import bmo.command.LogCommand;
import bmo.command.RedoCommand;
import bmo.command.DeleteCommand;
import bmo.command.ToDoCommand;
import bmo.command.GuideCommand;
import bmo.command.DoneCommand;
import bmo.command.FindCommand;

/**
 * Parser class to handle parsing of user input.
 */
public class Parser {

    /**
     * Formats the input string into a LocalDateTime object.
     * @param input String containing the date and time.
     * @return LocalDateTime object containing the date and time.
     */
    public static LocalDateTime formatDateTime(String input) {
        Ui ui = new Ui();
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            return LocalDateTime.parse(input, formatter);
        } catch (Exception e) {
            return null;
        }
    }

    //to amend: break down into smaller methods
    /**
     * Parses the user input and returns the corresponding Command object.
     * @param input String containing the user input.
     * @return Command object corresponding to the user input.
     * @throws IOException if unable to parse the input.
     */
    public static Command parse(String input) throws IOException {
            String[] inputArr = input.split(" ");
            String keyword = inputArr[0];
            Command output;
            switch (keyword) {
                case "hi":
                    output = new GreetCommand();
                    break;
                case "bye":
                    output = new ExitCommand();
                    break;
                case "log":
                    output = new LogCommand();
                    break;
                case "done":
                    String doneFormat = "^done\\s+(\\d+)$";
                    Pattern donePattern = Pattern.compile(doneFormat);
                    Matcher doneMatcher = donePattern.matcher(input);

                    if (doneMatcher.matches()) {
                        String index = doneMatcher.group(1);
                        output = new DoneCommand(index);
                        break;
                    }
                    output = new DefaultCommand(0);
                    break;
                case "redo":
                    String redoFormat = "^redo\\s+(\\d+)$";
                    Pattern redoPattern = Pattern.compile(redoFormat);
                    Matcher redoMatcher = redoPattern.matcher(input);

                    if (redoMatcher.matches()) {
                        String index = redoMatcher.group(1);
                        output = new RedoCommand(index);
                        break;
                    }
                    output = new DefaultCommand(0);
                    break;
                case "delete":
                    String deleteFormat = "^delete\\s+(\\d+)$";
                    Pattern deletePattern = Pattern.compile(deleteFormat);
                    Matcher deleteMatcher = deletePattern.matcher(input);

                    if (deleteMatcher.matches()) {
                        String index = deleteMatcher.group(1);
                        output = new DeleteCommand(index);
                        break;
                    }

                    output = new DefaultCommand(0);
                    break;
                // case "find":
                //    output = new FindCommand(inputArr[1]);
                //    break;
                case "todo":
                    String toDoFormat = "^todo\\s+(\\S+(\\s+\\w+)*)$";
                    Pattern toDoPattern = Pattern.compile(toDoFormat);
                    Matcher toDoMatcher = toDoPattern.matcher(input);

                    if (toDoMatcher.matches()) {
                        String desc = toDoMatcher.group(1);
                        output = new ToDoCommand(desc);
                        break;
                    }
                    output = new DefaultCommand(1);
                    break;

                case "due":
                    String dueFormat = "^due\\s+(\\w+(\\s+\\w+)*)\\s+/by\\s+(\\S+(\\s+\\w+|/)*)$";
                    Pattern duePattern = Pattern.compile(dueFormat);
                    Matcher dueMatcher = duePattern.matcher(input);

                    if (dueMatcher.matches()) {
                        String desc = dueMatcher.group(1);
                        String by = dueMatcher.group(3);

                        LocalDateTime byDateTime = formatDateTime(by);
                        if (byDateTime == null) {
                            output = new DefaultCommand(2);
                            break;
                        } else {
                            output = new DueCommand(desc, byDateTime);
                            break;
                        }
                    }
                    output = new DefaultCommand(1);
                    break;

                case "event":
                    String eventFormat = "^event\\s+(\\w+(\\s+\\w+)*)\\s+/from\\s+(\\S+(\\s+\\w+|/)*)\\s+/to\\s+(\\S+(\\s+\\w+|/)*)$";
                    Pattern eventPattern = Pattern.compile(eventFormat);
                    Matcher eventMatcher = eventPattern.matcher(input);

                    if (eventMatcher.matches()) {
                        String desc = eventMatcher.group(1);
                        String start = eventMatcher.group(3);
                        String end = eventMatcher.group(5);

                        LocalDateTime startDateTime = formatDateTime(start);
                        LocalDateTime endDateTime = formatDateTime(end);
                        if (startDateTime == null || endDateTime == null) {
                            output = new DefaultCommand(2);
                            break;
                        } else {
                            output = new EventCommand(desc, startDateTime, endDateTime);
                            break;
                        }
                    }
                    output = new DefaultCommand(1);
                    break;
                case "commands":
                    output = new GuideCommand();
                    break;
                default:
                    output = new DefaultCommand(0);
                    break;
            }
            return output;
    }

}
