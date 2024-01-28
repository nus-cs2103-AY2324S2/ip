package dino.command;

import dino.task.Deadline;
import dino.task.Event;
import dino.task.Task;
import dino.task.ToDo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.Scanner;

public class Parser {

    private TaskList tasks;
    private Ui ui;
    private Dino.TaskType taskType;
    private Scanner sc;

    public Parser(TaskList tasks, Ui ui, Scanner sc) {
        this.tasks = tasks;
        this.ui = ui;
        this.sc = sc;
    }

    public void parseCommand(String command) {

        while (true) {
            switch (command) {
                case "list":
                    tasks.listTask();
                    break;

                case "bye":
                    break;

                case "delete":
                    try {
                        int taskNum = sc.nextInt();
                        tasks.deleteTask(taskNum);
                    } catch (DinoException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case "todo":
                    taskType = Dino.TaskType.TODO;
                    handleTaskCreation(sc, taskType);
                    break;

                case "deadline":
                    taskType = Dino.TaskType.DEADLINE;
                    handleTaskCreation(sc, taskType);
                    break;

                case "event":
                    taskType = Dino.TaskType.EVENT;
                    handleTaskCreation(sc, taskType);
                    break;

                case "filter":
                    printTasksForDate(sc);
                    break;

                case "mark":
                    int taskNum = sc.nextInt();
                    if (taskNum > tasks.size()) {
                        System.out.println("Uh oh, we do not have a task assigned to that number.");
                    } else {
                        System.out.println("Good job on completing the task! I have checked it off the list.");
                        Task completed = tasks.get(taskNum - 1);
                        completed.markAsDone();
                    }
                    break;

                case "unmark":
                    int taskNumber = sc.nextInt();
                    if (taskNumber > tasks.size()) {
                        System.out.println("Uh oh, we do not have a task assigned to that number.");
                    } else {
                        System.out.println("Ah, I will mark it as undone. Remember to do it asap!");
                        Task missing = tasks.get(taskNumber - 1);
                        missing.markAsUndone();
                    }
                    break;

                default:
                    try {
                        throw new DinoException("I don't understand ;;");
                    } catch (DinoException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
            }
        }
    }

    private void handleTaskCreation(Scanner sc, Dino.TaskType taskType) {
        try {
            String taskDetails = sc.nextLine().trim();
            if (taskDetails.isEmpty()) {
                throw new DinoException("Description cannot be empty.");
            }

            tasks.addTask(createTaskFromInput(taskType, taskDetails));

            System.out.println("Okay.");
            System.out.println("  " + tasks.get(tasks.size() - 1));
            System.out.println("Now you have " + tasks.size() + " in the list.");
        } catch (DinoException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public Task createTaskFromInput(Dino.TaskType taskType, String taskDetails) throws DinoException {
        switch (taskType) {
            case TODO:
                return new ToDo(taskDetails);

            case DEADLINE:
                String[] deadlineParts = taskDetails.split("/by");
                if (deadlineParts.length != 2) {
                    throw new DinoException("Invalid input format for deadline. Please use: deadline <deadline name> /by <time>");
                }
                String deadlineName = deadlineParts[0].trim();
                String deadlineTimeString = deadlineParts[1].trim();
                if (deadlineName.isEmpty() || deadlineTimeString.isEmpty()) {
                    throw new DinoException("Deadline name and time cannot be empty.");
                }

                try {
                    return new Deadline(deadlineName, parseStringToTime(deadlineTimeString));
                } catch (DateTimeParseException e) {
                    throw new DinoException("Error parsing deadline date and time: " + e.getMessage());
                }

            case EVENT:
                String[] eventParts = taskDetails.split("/from|/to");
                if (eventParts.length != 3) {
                    throw new DinoException("Invalid input format for event. Please use: event <event name> /from <time> /to <time>");
                }
                String eventName = eventParts[0].trim();
                String startTimeString = eventParts[1].trim();
                String endTimeString = eventParts[2].trim();
                if (eventName.isEmpty() || startTimeString.isEmpty() || endTimeString.isEmpty()) {
                    throw new DinoException("Event name, start time, and end time cannot be empty.");
                }

                try {
                    LocalDateTime startTime = parseStringToTime(startTimeString);
                    LocalDateTime endTime = parseStringToTime(endTimeString);
                    return new Event(eventName, startTime, endTime);
                } catch (DateTimeParseException e) {
                    throw new DinoException("Error parsing event date and time: " + e.getMessage());
                }

            default:
                throw new DinoException("Unknown task type: " + taskType);
        }
    }


    public LocalDateTime parseStringToTime(String time) {
        DateTimeFormatter dateOnlyFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm", Locale.ENGLISH);

        LocalDateTime deadlineTime;
        if (time.contains(" ")) {
            deadlineTime = LocalDateTime.parse(time, dateTimeFormatter);
        } else {
            deadlineTime = LocalDateTime.of(LocalDate.parse(time, dateOnlyFormatter), LocalTime.MIDNIGHT);
        }
        return deadlineTime;
    }

    public String parseStringToNum(String time) {
        time = time.trim();
        DateTimeFormatter dateOnlyFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy", Locale.ENGLISH);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm", Locale.ENGLISH);

        LocalDateTime deadlineTime;
        if (time.contains(" ")) {
            deadlineTime = LocalDateTime.parse(time, dateTimeFormatter);
        } else {
            deadlineTime = LocalDateTime.of(LocalDate.parse(time, dateOnlyFormatter), LocalTime.MIDNIGHT);
        }

        DateTimeFormatter resultFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
        String formattedDate = deadlineTime.format(resultFormatter);

        if (deadlineTime.toLocalTime() != LocalTime.MIDNIGHT) {
            formattedDate += " " + deadlineTime.toLocalTime().format(DateTimeFormatter.ofPattern("HHmm"));
        }

        return formattedDate;
    }

    void printTasksForDate(Scanner sc) {
        try {
            String dateString = sc.next();
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
            LocalDate date = LocalDate.parse(dateString, dateFormatter);

            System.out.println("Tasks for " + date + ":");

            tasks.getTaskList().stream()
                    .filter(task -> task instanceof Deadline)
                    .map(task -> (Deadline) task)
                    .filter(deadline -> deadline.getDateTime().toLocalDate().equals(date))
                    .forEach(System.out::println);

            tasks.getTaskList().stream()
                    .filter(task -> task instanceof Event)
                    .map(task -> (Event) task)
                    .filter(event -> event.getStartTime().toLocalDate().equals(date) || event.getEndTime().toLocalDate().equals(date))
                    .forEach(System.out::println);

        } catch (DateTimeParseException e) {
            System.out.println("Error parsing date: " + e.getMessage());
        }
    }
}

