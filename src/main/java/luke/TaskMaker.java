package luke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TaskMaker {

    /**
     * Returns a Task given a string input by the user.
     *
     * @param input String input given by user
     * @return The task if the input is valid, else null.
     * @throws MakeTaskException
     */
    public static Task makeTask(String input) throws MakeTaskException {
        Task task;
        String trimmedLowercase = input.trim().toLowerCase();
        String taskType = trimmedLowercase.split(" ")[0];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        String todoString = TaskType.TODO.toString();
        String deadlineString = TaskType.DEADLINE.toString();
        String eventString = TaskType.EVENT.toString();
        String errorMessage;

        if (taskType.equals(todoString)) {
            task = new Todo(trimmedLowercase.substring(todoString.length()).trim());
            if (input.split(" ").length < 2) {
                errorMessage = "You have eyes for a reason, don't you?\n" +
                        "[Missing todo description]\n";
                throw new MakeTaskException(errorMessage);
            }
        } else if (taskType.equals(deadlineString)) {
            try {
                String taskName = input.split("/")[0].trim().substring(deadlineString.length()).trim();
                LocalDate by = LocalDate.parse(input.split("/")[1].replace("by", "").trim(), formatter);
                task = new Deadline(taskName, by);
            } catch (ArrayIndexOutOfBoundsException e) {
                errorMessage = UI.getMissingParameterMessage(deadlineString);
                throw new MakeTaskException(errorMessage);
            } catch (DateTimeParseException e) {
                errorMessage = UI.getWrongDateFormatMessage();
                throw new MakeTaskException(errorMessage);
            }
        } else if (taskType.equals(eventString)) {
            try {
                String taskName = input.split("/")[0].trim().substring(eventString.length()).trim();
                LocalDate start = LocalDate.parse(input.split("/")[1].replace("from", "").trim(), formatter);
                LocalDate end = LocalDate.parse(input.split("/")[2].replace("to", "").trim(), formatter);
                task = new Event(taskName, start, end);
            } catch (IndexOutOfBoundsException e) {
                errorMessage = "/// I don't know when you are free... ///\n"
                        + "[Missing event parameter(s)]\n";
                throw new MakeTaskException(errorMessage);
            } catch (DateTimeParseException e) {
                errorMessage = UI.getWrongDateFormatMessage();
                throw new MakeTaskException(errorMessage);
            }
        } else {
            errorMessage = UI.getCommandNotFoundMessage();
            throw new MakeTaskException(errorMessage);
        }
        return task;
    }
}
