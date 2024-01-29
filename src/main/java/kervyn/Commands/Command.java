package kervyn.Commands;

import kervyn.Tasks.TaskList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Command {
    private String keyword;
    protected TaskList taskList;

    public Command(String keyword, TaskList taskList) {
        this.keyword = keyword;
        this.taskList = taskList;
    }

    public void taskAdded() {
        System.out.println("\tUnderstood. I've added this task:");
    }

    public String convertDate(String inputDateTime) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

        try {
            LocalDateTime dateTime = LocalDateTime.parse(inputDateTime, inputFormatter);
            return dateTime.format(outputFormatter);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format, please try again with a format that looks like dd-MM-yyyy HHmm");
            return null;
        }
    }

    public void executeCommand() {}
}
