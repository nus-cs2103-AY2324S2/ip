package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {
    private String input;
    private String commandWord;
    public Parser(String input) {
        this.input = input;
    }

    public String getCommandWord() {
        this.commandWord = input.split(" ")[0];
        return commandWord;
    }

    public String getDescription() throws DukeException {
        formatCheck("description");
        return input.split(" ", 2)[1];
    }

    public LocalDate getBy() throws DukeException, DateTimeParseException {
        formatCheck("by");
        return Task.getInputDateFormat(input.split("/by ")[1].trim());
    }

    public LocalDate[] getFromTo() throws DukeException, DateTimeParseException {
        formatCheck("fromTo");
        LocalDate from = Task.getInputDateFormat(input.split("/from")[1].split("/to")[0].trim());
        LocalDate to = Task.getInputDateFormat(input.split("/to")[1].trim());
        return new LocalDate[] {from, to};
    }

    private void formatCheck(String taskType) throws DukeException {
        String formatStringInfo = "Please use the following format:" + taskType + " <description>";
        switch (taskType) {
        case "description":
            if (input.split(" ").length == 1) {
                throw new DukeException("The description of a task cannot be empty. \n\t" +
                    formatStringInfo);
            }
            break;
        case "by":
            if (input.split(" ").length == 1) {
                throw new DukeException("The deadline and description for a task cannot be empty. \n\t" +
                    "Please use the following format: deadline <description> /by <dd-mm-yyyy>");
            } else if (!input.contains("/by")) {
                throw new DukeException("Invalid command for deadline. \n\t" +
                    formatStringInfo + " /by <dd-mm-yyyy>");
            }
            break;
        case "fromTo":
            if (input.split(" ").length == 1) {
                throw new DukeException("The date and description for an event cannot be empty. \n\t" +
                    formatStringInfo + " /from <dd-mm-yyyy> /to <dd-mm-yyyy>");
            } else if (!input.contains("/from") || !input.contains("/to")) {
                throw new DukeException("Invalid command for event. \n\t" +
                    formatStringInfo + " /from <dd-mm-yyyy> /to <dd-mm-yyyy>");
            }
            break;
        }



    }



    public int getIndex() {
        return Integer.parseInt(input.split(" ")[1]) - 1;
    }


}
