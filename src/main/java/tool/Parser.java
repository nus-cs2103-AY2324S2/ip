package tool;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

import exception.ChronosException;
import task.Task;
import tool.Storage;
import tool.TaskList;
import tool.Ui;

/**
 * Represents the tool to process user commands.
 */
public class Parser {
    /**
     * Constructs a Parser object.
     */
    private Parser() {
    }

    /**
     * Changes the format the date specified for a deadline or an event.
     *
     * @param dateTime Date and time of a deadline or an event in the format yyyy-mm-dd HH:MM.
     * @return Formatted date and time.
     */
    public static String formatDateTime(String dateTime) {
        String[] dateTimeArray = dateTime.split(" ");
        String date = dateTimeArray[0];
        String time = dateTimeArray[1];
        String combinedDateTime = date + "T" + time;
        return LocalDateTime.parse(combinedDateTime).format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm"));
    }

    /**
     * Processes the command provided by the user.
     *
     * @param command Command provided by the user.
     * @param ui Ui object that deals with the interactions with the user.
     * @param storage Storage object that deals with loading tasks from file and saving tasks to file.
     * @param tasks TaskList object that contains the task list.
     * @return Integer to indicate whether program should terminate.
     * @throws IOException If there is an exception when processing input/output.
     * @throws ChronosException If there are invalid commands provided.
     */
    public static int processCommand(String command, Ui ui, Storage storage, TaskList tasks) throws IOException, ChronosException {
        String[] token = command.split(" ", 2);

        if (token[0].equals("bye")) {
            Ui.bidGoodbye();
            return 0;
        } else {
            switch(token[0]) {
            case "help":
                try {
                    if (token.length != 1) {
                        throw ChronosException.createInvalidHelpException();
                    } else {
                        ui.printHelp();
                    }
                } catch (exception.InvalidHelpException e){
                    System.out.println(e.getMessage());
                }
                return 1;
            case "list":
                try {
                    if (token.length != 1) {
                        throw ChronosException.createInvalidListException();
                    } else if (tasks.isEmpty()) {
                        ui.printNoOutstandingTasks();
                    } else {
                        ui.printTasks(tasks);
                    }
                } catch (exception.InvalidListException e){
                    System.out.println(e.getMessage());
                }
                return 1;
            case "mark":
                try {
                    if (token.length != 2 || token[1].trim().isEmpty()) {
                        throw ChronosException.createMissingTaskNumberException();
                    } else {
                        try {
                            int i = Integer.parseInt(token[1]);
                            tasks.markTask(i, ui);
                            storage.saveTasksToFile(tasks);
                        } catch (NumberFormatException e) {
                            ui.printNumberFormatException();
                        } catch (IndexOutOfBoundsException e) {
                            ui.printIndexOutOfBoundsException();
                        }
                    }
                } catch (exception.MissingTaskNumberException e) {
                    System.out.println(e.getMessage());
                }
                return 1;
            case "unmark":
                try {
                    if (token.length != 2 || token[1].trim().isEmpty()) {
                        throw ChronosException.createMissingTaskNumberException();
                    } else {
                        try {
                            int i = Integer.parseInt(token[1]);
                            tasks.unMarkTask(i, ui);
                            storage.saveTasksToFile(tasks);
                        } catch (NumberFormatException e) {
                            ui.printNumberFormatException();
                        } catch (IndexOutOfBoundsException e) {
                            ui.printIndexOutOfBoundsException();
                        }
                    }
                } catch (exception.MissingTaskNumberException e) {
                    System.out.println(e.getMessage());
                }
                return 1;
            case "todo":
                try {
                    if (token.length != 2 || token[1].trim().isEmpty()) {
                        throw ChronosException.createMissingDescriptionException();
                    } else {
                        String description = token[1];
                        tasks.addToDo(description, ui);
                        storage.saveTasksToFile(tasks);
                        return 1;
                    }
                } catch (exception.MissingDescriptionException e) {
                    System.out.println(e.getMessage());
                }
            case "deadline":
                try {
                    if (!token[1].contains("/by")) {
                        throw ChronosException.createInvalidDeadlineException();
                    }
                    String[] descriptionAndBy = token[1].split("/by");
                    String description = descriptionAndBy[0].trim();
                    String dueDate = Parser.formatDateTime(descriptionAndBy[1].trim());
                    tasks.addDeadline(description, dueDate, ui);
                    storage.saveTasksToFile(tasks);
                } catch (exception.InvalidDeadlineException e) {
                    System.out.println(e.getMessage());
                } catch (Exception e) {
                    ui.showDivider();
                    System.out.println("        Invalid command. Please include a task name and a valid due date following the syntax of the example below:");
                    System.out.println("        e.g. deadline return library book /by 2024-09-22 15:00");
                    ui.showDivider();
                }
                return 1;
            case "event":
                try {
                    if (!token[1].contains("/from") && !token[1].contains("/to")) {
                        throw ChronosException.createInvalidEventException();
                    }
                    String[] descriptionAndDate = token[1].split("/from");
                    String description = descriptionAndDate[0].trim();
                    String[] fromAndTo = descriptionAndDate[1].split("/to");
                    String fromDateAndTime = Parser.formatDateTime(fromAndTo[0].trim());
                    String toDateAndTime = Parser.formatDateTime(fromAndTo[1].trim());
                    tasks.addEvent(description, fromDateAndTime, toDateAndTime, ui);
                    storage.saveTasksToFile(tasks);
                } catch (exception.InvalidEventException e) {
                    System.out.println(e.getMessage());
                } catch (Exception e) {
                    ui.showDivider();
                    System.out.println("        Invalid command. Please include a task name and a valid due date following the syntax of the example below:");
                    System.out.println("        e.g. event concert /from 2024-02-16 18:00 /to 2024-02-16 20:00");
                    ui.showDivider();
                }
                return 1;
            case "delete":
                try {
                    if (token.length != 2 || token[1].trim().isEmpty()) {
                        throw ChronosException.createMissingTaskNumberException();
                    } else {
                        try {
                            int i = Integer.parseInt(token[1]);
                            tasks.deleteTask(i, ui);
                            storage.saveTasksToFile(tasks);
                        } catch (NumberFormatException e) {
                            ui.printNumberFormatException();
                        } catch (IndexOutOfBoundsException e) {
                            ui.printIndexOutOfBoundsException();
                        }
                    }
                } catch (exception.MissingTaskNumberException e) {
                    System.out.println(e.getMessage());
                }

                return 1;
            default:
                try {
                    throw ChronosException.createInvalidCommandException();
                } catch (exception.InvalidCommandException e) {
                    System.out.println(e.getMessage());
                }
                return 1;
            }
        }
    }
}
