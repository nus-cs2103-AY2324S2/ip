package duke.utility;

import duke.command.Command;
import duke.command.AddTaskCommand;
import duke.command.DeleteTaskCommand;
import duke.command.ExitCommand;
import duke.command.FindTaskCommand;
import duke.command.ListTasksCommand;
import duke.command.MarkTaskCommand;
import duke.command.UnmarkTaskCommand;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

/**
 * Class that represents a parser that is used by the chatbot to parse user input.
 */
public class Parser {
    /**
     * Returns a Command Object that adds a specified Task by parsing the String input.
     *
     * @param description String containing details of the Task to be created.
     * @return Command Object that adds a specified Task.
     * @throws DukeException
     */
    public static Command taskParser(String description) throws DukeException {
        Task newTask;
        validTaskCommand(description);
        if (description.toLowerCase().startsWith("todo")) {
            String[] descriptionArr = description.split(" ");
            StringBuilder descBuilder = new StringBuilder();
            for (int k = 1; k < descriptionArr.length; k++) {
                if (k == descriptionArr.length - 1) {
                    descBuilder.append(descriptionArr[k]);
                } else {
                    descBuilder.append(descriptionArr[k] + " ");
                }
            }
            newTask = new ToDo(descBuilder.toString());
        } else if (description.toLowerCase().startsWith("deadline")) {
            String[] descriptionArr = description.split(" ");
            int byIndex = Arrays.asList(descriptionArr).indexOf("/by");
            StringBuilder descBuilder = new StringBuilder();
            for (int k = 1; k < byIndex; k++) {
                if (k == byIndex - 1) {
                    descBuilder.append(descriptionArr[k]);
                } else {
                    descBuilder.append(descriptionArr[k] + " ");
                }
            }
            StringBuilder byBuilder = new StringBuilder();
            for (int k = byIndex + 1; k < descriptionArr.length; k++) {
                if (k == descriptionArr.length - 1) {
                    byBuilder.append(descriptionArr[k]);
                } else {
                    byBuilder.append(descriptionArr[k] + " ");
                }
            }
            newTask = new Deadline(descBuilder.toString(), checkDates(byBuilder.toString()));
        } else {
            String[] descriptionArr = description.split(" ");
            int fromIndex = Arrays.asList(descriptionArr).indexOf("/from");
            StringBuilder descBuilder = new StringBuilder();
            for (int k = 1; k < fromIndex; k++) {
                if (k == fromIndex - 1) {
                    descBuilder.append(descriptionArr[k]);
                } else {
                    descBuilder.append(descriptionArr[k] + " ");
                }
            }
            int toIndex = Arrays.asList(descriptionArr).indexOf("/to");
            StringBuilder fromBuilder = new StringBuilder();
            for (int k = fromIndex + 1; k < toIndex; k++) {
                if (k == toIndex - 1) {
                    fromBuilder.append(descriptionArr[k]);
                } else {
                    fromBuilder.append(descriptionArr[k] + " ");
                }
            }
            StringBuilder toBuilder = new StringBuilder();
            for (int k = toIndex + 1; k < descriptionArr.length; k++) {
                if (k == descriptionArr.length - 1) {
                    toBuilder.append(descriptionArr[k]);
                } else {
                    toBuilder.append(descriptionArr[k] + " ");
                }
            }
            newTask = new Event(descBuilder.toString(), checkDates(fromBuilder.toString()),
                    checkDates(toBuilder.toString()));
        }
        return new AddTaskCommand(newTask);
    }

    /**
     * Returns a Command Object which alters Tasks in the TaskList by parsing the String input.
     *
     * @param userInput String containing details of the Command to be executed.
     * @return Command Object which alters Tasks in the TaskList
     * @throws DukeException
     */
    public static Command parseInstructions(String userInput) throws DukeException {
        if (userInput.toLowerCase().equals("bye")) {
            return new ExitCommand();
        } else if (userInput.toLowerCase().equals("list")) {
            return new ListTasksCommand();
        } else if (userInput.toLowerCase().startsWith("mark")) {
            String[] inputArr = userInput.split(" ");
            assert inputArr[0].toLowerCase() == "mark";
            try {
                int index = Integer.parseInt(inputArr[1]) - 1;
                return new MarkTaskCommand(index);
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                throw new DukeException("*HONK* Pengu thinks you need a valid task number to mark, "
                        + "consider checking the list command");
            }
        } else if (userInput.toLowerCase().startsWith("unmark")) {
            String[] inputArr = userInput.split(" ");
            assert inputArr[0].toLowerCase() == "unmark";
            try {
                int index = Integer.parseInt(inputArr[1]) - 1;
                return new UnmarkTaskCommand(index);
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                throw new DukeException("*HONK* Pengu thinks you need a valid task number to unmark, "
                        + "consider checking the list command");
            }
        } else if (userInput.toLowerCase().startsWith("delete")) {
            String[] inputArr = userInput.split(" ");
            assert inputArr[0].toLowerCase() == "delete";
            try {
                int index = Integer.parseInt(inputArr[1]) - 1;
                return new DeleteTaskCommand(index);
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                throw new DukeException("*HONK* Pengu thinks you need a valid task number to delete, "
                        + "consider checking the list command");
            }
        } else if (userInput.toLowerCase().startsWith("find")) {
            String[] inputArr = userInput.split(" ");
            assert inputArr[0].toLowerCase() == "find";
            if (inputArr.length > 2) {
                throw new DukeException("Pengu can only look up one-word long keywords");
            }
            return new FindTaskCommand(inputArr[1]);
        }
        String keyword = userInput.split(" ")[0].toLowerCase();
        if (!(keyword.equals("todo") || keyword.equals("deadline") || keyword.equals("event"))) {
            throw new DukeException("*HONK* Pengu has never seen such a command before,"
                    + " some commands Pengu can do are: list, todo, deadline");
        } else {
            return taskParser(userInput);
        }
    }

    /**
     * Returns a Task Object by parsing the String obtained from storage.
     *
     * @param fileLine String obtained from the storage.
     * @return Task Object based on String inputted.
     * @throws DukeException
     */
    public static Task parseFileLine(String fileLine) throws DukeException {
        Task t;
        DateTimeFormatter dTFormatter = DateTimeFormatter.ofPattern("MMM-dd-yyyy HHmm");
        if (fileLine.charAt(1) == 'T') {
            String infoString = fileLine.substring(7);
            t = new ToDo(infoString);
        } else if (fileLine.charAt(1) == 'D') {
            String infoString = fileLine.substring(7);
            String[] strArr = infoString.split(" ");
            int byIndex = Arrays.asList(strArr).indexOf("(by:");
            StringBuilder descBuilder = new StringBuilder();
            for (int k = 0; k < byIndex; k++) {
                if (k == byIndex - 1) {
                    descBuilder.append(strArr[k]);
                } else {
                    descBuilder.append(strArr[k] + " ");
                }
            }
            StringBuilder byBuilder = new StringBuilder();
            for (int k = byIndex + 1; k < strArr.length; k++) {
                if (k == strArr.length - 1) {
                    byBuilder.append(strArr[k]);
                } else {
                    byBuilder.append(strArr[k] + " ");
                }
            }
            if (byBuilder.toString().length() == 0) {
                throw new DukeException("Wrong File Format");
            } else {
                String dateTimeStr = byBuilder.toString();
                dateTimeStr = dateTimeStr.substring(0, dateTimeStr.length() - 1);
                LocalDateTime byTime = LocalDateTime.parse(dateTimeStr, dTFormatter);
                t = new Deadline(descBuilder.toString(), byTime);
            }
        } else if (fileLine.charAt(1) == 'E') {
            String infoString = fileLine.substring(7);
            String[] strArr = infoString.split(" ");
            int fromIndex = Arrays.asList(strArr).indexOf("(from:");
            StringBuilder descBuilder = new StringBuilder();
            for (int k = 0; k < fromIndex; k++) {
                if (k == fromIndex - 1) {
                    descBuilder.append(strArr[k]);
                } else {
                    descBuilder.append(strArr[k] + " ");
                }
            }
            int toIndex = Arrays.asList(strArr).indexOf("to:");
            StringBuilder fromBuilder = new StringBuilder();
            for (int k = fromIndex + 1; k < toIndex; k++) {
                if (k == toIndex - 1) {
                    fromBuilder.append(strArr[k]);
                } else {
                    fromBuilder.append(strArr[k] + " ");
                }
            }
            StringBuilder toBuilder = new StringBuilder();
            for (int k = toIndex + 1; k < strArr.length; k++) {
                if (k == strArr.length - 1) {
                    toBuilder.append(strArr[k]);
                } else {
                    toBuilder.append(strArr[k] + " ");
                }
            }
            if (fromBuilder.toString().length() == 0 || toBuilder.toString().length() == 0) {
                throw new DukeException("Wrong File Format");
            } else {
                String fromDateTimeStr = fromBuilder.toString();
                String toDateTimeStr = toBuilder.toString();
                fromDateTimeStr = fromDateTimeStr.substring(0, fromDateTimeStr.length());
                toDateTimeStr = toDateTimeStr.substring(0, toDateTimeStr.length() - 1);
                LocalDateTime fromTime = LocalDateTime.parse(fromDateTimeStr, dTFormatter);
                LocalDateTime toTime = LocalDateTime.parse(toDateTimeStr, dTFormatter);
                t = new Event(descBuilder.toString(), fromTime, toTime);
            }
        } else {
            throw new DukeException("Wrong File Format");
        }
        return t;
    }

    /**
     * Checks if the Task Command is valid.
     *
     * @param str String containing the user input.
     * @return Boolean Value of whether the command is valid.
     * @throws DukeException
     */
    private static boolean validTaskCommand(String str) throws DukeException {
        List<String> strArr = Arrays.asList(str.split(" "));
        String keyword = str.split(" ")[0].toLowerCase();
        if (!(keyword.equals("todo") || keyword.equals("deadline") || keyword.equals("event"))) {
            throw new DukeException("*HONK* Pengu has never seen such a command before,"
                    + " some commands Pengu can do are: list, todo, deadline");
        } else if (keyword.equals("todo") && !(strArr.size() > 1)) {
            throw new DukeException("*HONK* Pengu needs a Task description to record this down");
        } else if (keyword.equals("deadline") && !(strArr.contains("/by"))) {
            throw new DukeException("*HONK* Pengu needs a /by followed by a end date for your task");
        } else if (keyword.equals("event") && !(strArr.contains("/from") && (strArr.contains("/to")))) {
            throw new DukeException("*HONK* Pengu needs a /from followed by a from date and a "
                    + "/to followed by a end date for your task");
        } else if (keyword.equals("deadline") && strArr.get(1).equals("/by")) {
            throw new DukeException("Honk* Pengu cannot accept a deadline task without a description");
        } else if (keyword.equals("event") && (strArr.get(1).equals("/from") || strArr.get(1).equals("/to"))) {
            throw new DukeException("*Honk* Pengu cannot accept a event without a description");
        } else {
            return true;
        }
    }

    /**
     * Returns a LocalDateTime Object based on the String inputted if in valid format.
     *
     * @param dateString String containing Date and Time details.
     * @return LocalDateTime Object based on the String inputted.
     * @throws DukeException
     */
    public static LocalDateTime checkDates(String dateString) throws DukeException {
        try {
            DateTimeFormatter dTFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            LocalDateTime date = LocalDateTime.parse(dateString, dTFormatter);
            return date;
        } catch (DateTimeParseException e) {
            throw new DukeException("Pengu thinks that you need to put the date in this format: yyyy-MM-dd HHmm");
        }
    }
}
