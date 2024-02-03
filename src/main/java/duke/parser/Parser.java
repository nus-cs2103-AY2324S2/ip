package duke.parser;

import duke.exception.EmptyTaskNameException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {
    private String userInput;
    private String command;

    public Parser() { }

    public Parser(String userInput, String command) {
        this.userInput = userInput;
        this.command = command;
    }

    public void checkEmptyTask() throws EmptyTaskNameException {
        if (this.getTaskName().isEmpty()) {
            throw new EmptyTaskNameException();
        }
    }

    public String getTaskName() {
        String taskName = "";
        switch (this.command) {
        case "todo" :
            taskName = this.userInput.trim();
            break;
        case "deadline" :
            taskName = this.userInput.split(" /by ")[0].trim();
            break;
        case "event" :
            taskName = this.userInput.split(" /from ")[0].trim();
            break;
        default:
            break;
        }
        return taskName;
    }

    public String getDue() {
        return userInput.split(" /by ")[1].trim();
    }
    public LocalDateTime parseDueTime() {
        return LocalDateTime.parse(getDue(), DateTimeFormatter.ofPattern("yyyy-MM-dd kk:mm"));
    }

    public LocalDate parseDueDate() {
        return LocalDate.parse(getDue(), DateTimeFormatter.ISO_LOCAL_DATE);
    }

    public String getEventStart() {
        return userInput.split(" /from ")[1].split(" /to ")[0].trim();
    }

    public String getEventEnd() {
        return userInput.split(" /to ")[1].trim();
    }

    public int parseIndex() {
        return Integer.parseInt(userInput.trim());
    }
}
