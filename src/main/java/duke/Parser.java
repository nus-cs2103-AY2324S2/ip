package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {
    private String input;
    private String commandWord;

    public Parser(String input) {
        this.input = input;
    }

    public String getCommandWord() throws DukeException {
        if (input.trim().isEmpty()) {
            throw new DukeException("Command cannot be empty.");
        }
        this.commandWord = input.split(" ")[0].trim();
        return commandWord;
    }

    public String getDescription() throws DukeException {
        formatCheck("todo");
        return input.split(" ", 2)[1];
    }

    public LocalDate getBy() throws DukeException, DateTimeParseException {
        formatCheck("deadline");
        return Task.getInputDateFormat(input.split("/by ")[1].trim());
    }

    public LocalDate[] getFromTo() throws DukeException, DateTimeParseException {
        formatCheck("event");
        LocalDate from = Task.getInputDateFormat(input.split("/from")[1].split("/to")[0].trim());
        LocalDate to = Task.getInputDateFormat(input.split("/to")[1].trim());
        return new LocalDate[] {from, to};
    }

    private void formatCheck(String taskType) throws DukeException {
        String formatStringInfo = "Please use the following format:" + taskType + " <description>";
        switch (taskType) {
        case "todo":
            if (input.split(" ", 2).length == 1) {
                throw new DukeException("The description of a task cannot be empty. \n\t"
                    + formatStringInfo);
            }
            break;
        case "deadline":
            if (input.split(" ", 2).length == 1 || input.split("/by ").length != 2) {
                throw new DukeException("The deadline and description for a task cannot be empty. \n\t"
                    + "Please use the following format: deadline <description> /by <dd-mm-yyyy>");
            } else if (!input.contains("/by")) {
                throw new DukeException("Invalid command for deadline. \n\t"
                    + formatStringInfo + " /by <dd-mm-yyyy>");
            }
            break;
        case "event":
            if (input.split(" ", 2).length == 1) {
                throw new DukeException("The date and description for an event cannot be empty. \n\t"
                    + formatStringInfo + " /from <dd-mm-yyyy> /to <dd-mm-yyyy>");
            } else if (!input.contains("/from") || !input.contains("/to") || input.split("/from ").length > 2
                || input.split("/to ").length > 2) {
                throw new DukeException("Invalid command for event. \n\t"
                    + formatStringInfo + " /from <dd-mm-yyyy> /to <dd-mm-yyyy>");
            }
            break;
        default:
        }




    }

    public int getIndex() throws DukeException {
        if (input.split(" ").length == 1) {
            throw new DukeException("The index of a task cannot be empty.");
        }
        return Integer.parseInt(input.split(" ")[1]) - 1;
    }


}
