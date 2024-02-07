package parser;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import ui.Ui;
import storage.Storage;
import task.TaskList;
import task.Task;
import exception.ChronosException;

public class Parser {
    private Parser() {

    }

    /**
     * Adds and prints a todo task.
     *
     * @param description Description of the todo task.
     */
    public static void addToDo(String description, Ui ui, Storage storage, TaskList tasks) {
        task.Todo todo = new task.Todo(description);
        tasks.add(todo);

        ui.showDivider();
        System.out.println("        Got it. I've added this task:");
        System.out.println("          " + todo);
        System.out.println("        Now you have " + tasks.size() + " tasks in the list.");
        ui.showDivider();
    }

    /**
     * Adds and prints a task with a deadline.
     *
     * @param description Description of the task with deadline.
     * @param dueDate Deadline of the task.
     */
    public static void addDeadline(String description, String dueDate, Ui ui, Storage storage, TaskList tasks) {
        task.Deadline deadline = new task.Deadline(description, dueDate);
        tasks.add(deadline);

        ui.showDivider();
        System.out.println("        Got it. I've added this task:");
        System.out.println("          " + deadline);
        System.out.println("        Now you have " + tasks.size() + " tasks in the list.");
        ui.showDivider();
    }

    /**
     * Adds and prints an event.
     *
     * @param description Description of the event.
     * @param from Start date and time of the event.
     * @param to End date and time of the event.
     */
    public static void addEvent(String description, String from, String to, Ui ui, Storage storage, TaskList tasks) {
        task.Event event = new task.Event(description, from, to);
        tasks.add(event);

        ui.showDivider();
        System.out.println("        Got it. I've added this task:");
        System.out.println("          " + event);
        System.out.println("        Now you have " + tasks.size() + " tasks in the list.");
        ui.showDivider();
    }

    /**
     * Marks a task as completed.
     *
     * @param selectedTaskNumberToBeMarked Task number to be marked as completed.
     */
    public static void markTask(int selectedTaskNumberToBeMarked, Ui ui, Storage storage, TaskList tasks) {
        Task selectedTaskToBeMarked = tasks.get(selectedTaskNumberToBeMarked - 1);
        selectedTaskToBeMarked.setMarked();
        tasks.set(selectedTaskNumberToBeMarked - 1, selectedTaskToBeMarked);
        ui.showDivider();
        System.out.println("        Nice! I've marked this task as done:");
        System.out.println("          " + selectedTaskToBeMarked);
        ui.showDivider();
    }

    /**
     * Unmarks a task as incomplete.
     *
     * @param selectedTaskNumberToBeUnmarked Task number to be unmarked as incomplete.
     */
    public static void unMarkTask(int selectedTaskNumberToBeUnmarked, Ui ui, Storage storage, TaskList tasks) {
        Task selectedTaskToBeUnmarked = tasks.get(selectedTaskNumberToBeUnmarked - 1);
        selectedTaskToBeUnmarked.setUnmarked();
        tasks.set(selectedTaskNumberToBeUnmarked - 1, selectedTaskToBeUnmarked);
        ui.showDivider();
        System.out.println("        OK, I've marked this task as not done yet:");
        System.out.println("          " + selectedTaskToBeUnmarked);
        ui.showDivider();
    }

    /**
     * Deletes a task from task list.
     *
     * @param selectedTaskNumberToBeDeleted Task number to be deleted.
     */
    public static void deleteTask(int selectedTaskNumberToBeDeleted, Ui ui, Storage storage, TaskList tasks) {
        Task deletedTask = tasks.get(selectedTaskNumberToBeDeleted - 1);

        ui.showDivider();
        System.out.println("        Noted. I've removed this task:");
        System.out.println("          " + deletedTask);
        System.out.println("        Now you have " + (tasks.size() - 1) + " tasks in the list.");
        ui.showDivider();
        tasks.remove(selectedTaskNumberToBeDeleted - 1);
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

    public static int processCommand(String command, Ui ui, Storage storage, TaskList tasks) throws IOException, ChronosException {
        String[] token = command.split(" ", 2);

        if (token[0].equals("bye")) {
            Ui.bidGoodbye();
            return 0;
        }
        else {
            switch(token[0]) {
                case "list":
                    try {
                        if (token.length != 1) {
                            throw ChronosException.createInvalidListException();
                        } else if (tasks.isEmpty()) {
                            ui.printHelp();
                        } else {
                            ui.printTasks(tasks.size(), tasks);
                        }
                    } catch (exception.InvalidListException e){
                        System.out.println(e.getMessage());
                    }
                    return 1;
//                    break;
                case "mark":
                    try {
                        if (token.length != 2 || token[1].trim().isEmpty()) {
                            throw ChronosException.createMissingTaskNumberException();
                        } else {
                            try {
                                int i = Integer.parseInt(token[1]);
                                Parser.markTask(i, ui, storage, tasks);
                                storage.saveTasks(tasks);
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
                                Parser.unMarkTask(i, ui, storage, tasks);
                                storage.saveTasks(tasks);
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
                            Parser.addToDo(description, ui, storage, tasks);
                            storage.saveTasks(tasks);
                        }
                    } catch (exception.MissingDescriptionException e) {
                        System.out.println(e.getMessage());
                    }
                    return 1;
                case "deadline":
                    try {
                        if (!token[1].contains("/by")) {
                            throw ChronosException.createInvalidDeadlineException();
                        }
                        String[] descriptionAndBy = token[1].split("/by");
                        String description = descriptionAndBy[0].trim();
                        String dueDate = Parser.formatDateTime(descriptionAndBy[1].trim());
                        Parser.addDeadline(description, dueDate, ui, storage, tasks);
                        storage.saveTasks(tasks);
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
                        Parser.addEvent(description, fromDateAndTime, toDateAndTime, ui, storage, tasks);
                        storage.saveTasks(tasks);
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
                                Parser.deleteTask(i, ui, storage, tasks);
                                storage.saveTasks(tasks);
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
