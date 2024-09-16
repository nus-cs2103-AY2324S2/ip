package luke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TaskMaker {

    static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    static final String todoString = TaskType.TODO.toString();
    static final String deadlineString = TaskType.DEADLINE.toString();
    static final String eventString = TaskType.EVENT.toString();

    /**
     * Returns a Task given a string input by the user.
     *
     * @param input String input given by user.
     * @return The task if the input is valid, else null.
     * @throws MakeTaskException
     */
    public static Task makeTask(String input) throws MakeTaskException {
        String trimmedLowercase = input.trim().toLowerCase();
        String taskType = trimmedLowercase.split(" ")[0];
        String errorMessage;

        if (taskType.equals(todoString)) {
            return makeTodoTask(input, trimmedLowercase);
        } else if (taskType.equals(deadlineString)) {
            return makeDeadlineTask(input, trimmedLowercase);
        } else if (taskType.equals(eventString)) {
            return makeEventTask(input, trimmedLowercase);
        } else {
            errorMessage = UI.getCommandNotFoundMessage();
            throw new MakeTaskException(errorMessage);
        }
    }

    /**
     * Makes a Todo task.
     *
     * @param input Original user input.
     * @param processedInput Neatly processed user input.
     * @return Todo task.
     * @throws MakeTaskException
     */
    private static Task makeTodoTask(String input, String processedInput) throws MakeTaskException {
        Task task = new Todo(input.substring(todoString.length()).trim());
        if (processedInput.split(" ").length < 2) {
            String errorMessage = "You have eyes for a reason, don't you?\n" +
                    "[Missing todo description]\n";
            throw new MakeTaskException(errorMessage);
        }
        return task;
    }

    /**
     * Makes a Deadline task.
     *
     * @param input Original user input.
     * @param processedInput Neatly processed user input.
     * @return Deadline task.
     * @throws MakeTaskException
     */
    private static Task makeDeadlineTask(String input, String processedInput) throws MakeTaskException {
        Task task;
        String errorMessage;
        try {
            String taskName = input.split("/")[0].trim().substring(deadlineString.length()).trim();
            LocalDate by = LocalDate.parse(processedInput.split("/")[1].replace("by", "").trim(), formatter);
            task = new Deadline(taskName, by);
        } catch (ArrayIndexOutOfBoundsException e) {
            errorMessage = UI.getMissingParameterMessage(deadlineString);
            throw new MakeTaskException(errorMessage);
        } catch (DateTimeParseException e) {
            errorMessage = UI.getWrongDateFormatMessage();
            throw new MakeTaskException(errorMessage);
        }
        return task;
    }

    /**
     * Makes an Event task.
     *
     * @param input Original user input.
     * @param processedInput Neatly processed user input.
     * @return An event task.
     * @throws MakeTaskException
     */
    private static Task makeEventTask(String input, String processedInput) throws MakeTaskException {
        Task task;
        String errorMessage;
        try {
            String taskName = input.split("/")[0].trim().substring(eventString.length()).trim();
            LocalDate start = LocalDate.parse(processedInput.split("/")[1].replace("from", "").trim(), formatter);
            LocalDate end = LocalDate.parse(processedInput.split("/")[2].replace("to", "").trim(), formatter);
            task = new Event(taskName, start, end);
        } catch (IndexOutOfBoundsException e) {
            errorMessage = "/// I don't know when you are free... ///\n"
                    + "[Missing event parameter(s)]\n";
            throw new MakeTaskException(errorMessage);
        } catch (DateTimeParseException e) {
            errorMessage = UI.getWrongDateFormatMessage();
            throw new MakeTaskException(errorMessage);
        }
        return task;
    }
}
