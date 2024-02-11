package duke.storage;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static duke.constants.Constant.DATE_TIME_FORMATTER;
import static duke.constants.Constant.DATE_TIME_FORMATTER_FOR_PRINT;

public class Storage {
    private String filePath;
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public List<Task> load() {
        Path path = Paths.get(filePath);
        boolean directoryExists = Files.exists(path);
        List<Task> tasks = new ArrayList<>();
        if (directoryExists) {
            try {
                List<String> lines = Files.readAllLines(path);
                for (String line : lines) {
                    try (Scanner scanner = new Scanner(line)) {
                        if (scanner.hasNext()) {
                            String taskType = scanner.next();
                            switch (taskType) {
                            case "T":
                                tasks.add(convertToTodo(line));
                                break;
                            case "D":
                                try {
                                    tasks.add(convertToDeadline(line));
                                } catch (DateTimeParseException e) {
                                    System.err.println("OPPS! The format for the inputted deadline is not " +
                                            "accepted here. Please follow this format: 'yyyy-MM-dd HHmm' " +
                                            "when you are creating the task.");
                                }
                                break;
                            case "E":
                                try {
                                    tasks.add(convertToEvent(line));
                                } catch (DateTimeParseException e) {
                                    System.err.println("OPPS! The format for the inputted start and end time is " +
                                            "not accepted here. Please follow this format: 'yyyy-MM-dd HHmm' " +
                                            "when you are creating the task.");
                                }
                                break;
                            }
                        }
                    }
                }
                System.out.println("The file is loaded");
            } catch (IOException e) {
                System.err.println("There is error in loading file.");
            }
        }
        return tasks;
    }

    public ToDo convertToTodo(String string) {
        String[] parts = string.split("\\|");
        boolean status = parts[1].trim().equals("1");
        String description = parts[2].trim();
        return new ToDo(description, status);
    }

    public Deadline convertToDeadline(String string) throws DateTimeParseException{
        String[] parts = string.split("\\|");
        boolean status = parts[1].trim().equals("1");
        String description = parts[2].trim();
        String deadlineStr = parts[3].trim();
        LocalDateTime deadline = LocalDateTime.parse(deadlineStr, DATE_TIME_FORMATTER);
        return new Deadline(description, status, deadline, DATE_TIME_FORMATTER_FOR_PRINT);
    }

    public Event convertToEvent(String string) throws DateTimeParseException{
        String[] parts = string.split("\\|");
        boolean status = parts[1].trim().equals("1");
        String description = parts[2].trim();
        String startTimeStr = parts[3].trim();
        String endTimeStr = parts[4].trim();
        LocalDateTime startTime = LocalDateTime.parse(startTimeStr, DATE_TIME_FORMATTER);
        LocalDateTime endTime = LocalDateTime.parse(endTimeStr, DATE_TIME_FORMATTER);
        return new Event(description, status, startTime, endTime, DATE_TIME_FORMATTER_FOR_PRINT);
    }
}
