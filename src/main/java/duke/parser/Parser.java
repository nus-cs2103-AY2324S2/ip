package duke.parser;

import duke.exception.EmptyTaskNameException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * Parses user input.
 */
public class Parser {
    private String userInput;
    private String command;

    /**
     * Constructs a Parser object.
     *
     * @param userInput User input text.
     * @param command Command keyword.
     */
    public Parser(String userInput, String command) {
        this.userInput = userInput;
        this.command = command;
    }

    public Parser(String userInput) {
        Scanner sc = new Scanner(userInput);
        this.command = sc.next().trim();
        if (sc.hasNext()) {
            this.userInput = sc.nextLine().trim();
        } else {
            this.userInput = "";
        }
    }

    public String getCommand() {
        return this.command;
    }

    /**
     * Retrieves raw user input.
     *
     * @return User input text.
     */
    public String getUserInput() {
        return this.userInput.trim();
    }

    /**
     * Checks if the task name is empty.
     *
     * @throws EmptyTaskNameException If task name is empty.
     */
    public void checkEmptyTask() throws EmptyTaskNameException {
        if (this.getTaskName().isEmpty()) {
            throw new EmptyTaskNameException();
        }
    }

    /**
     * Returns task name from the given user input.
     *
     * @return Task name.
     */
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

    /**
     * Returns task's due date as string.
     * Only valid for tasks of type deadline.
     *
     * @return Due date in string format.
     */
    public String getDue() {
        return userInput.split(" /by ")[1].trim();
    }

    /**
     * Returns task's due date and time as a LocalDateTime object.
     * Only valid for tasks of type deadline.
     *
     * @return Due date as LocalDateTime object.
     */
    public LocalDateTime parseDueTime(){
        return LocalDateTime.parse(getDue(), DateTimeFormatter.ofPattern("yyyy-MM-dd kk:mm"));
    }

    /**
     * Returns task's due date as a LocalDate object.
     * Only valid for tasks of type deadline.
     *
     * @return Due date as LocalDate object.
     */
    public LocalDate parseDueDate() {
        return LocalDate.parse(getDue(), DateTimeFormatter.ISO_LOCAL_DATE);
    }

    /**
     * Returns a string of event's start time.
     * Only valid for tasks of type event.
     *
     * @return Start of event.
     */
    public String getEventStart() {
        return userInput.split(" /from ")[1].split(" /to ")[0].trim();
    }

    /**
     * Returns a string of event's end time.
     * Only valid for tasks of type event.
     *
     * @return End of event.
     */
    public String getEventEnd() {
        return userInput.split(" /to ")[1].trim();
    }

    /**
     * Parses user input as integer.
     * Only valid for commands 'delete', 'unmark', 'mark'.
     *
     * @return User input as integer.
     */
    public int parseIndex() {
        return Integer.parseInt(userInput.trim());
    }
}
