import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import task.Task;
import task.Todo;
import task.Deadline;
import task.Event;

import exception.ChronosException;

/**
 * Represents the main class of the Chronos Task Management System.
 */
public class Chronos {
    private static final String DIVIDER = "        ------------------------------------------------------------";
    private static final String POSSIBLE_COMMANDS = "        TODO     --- todo [task name]\n" +
                                                    "        DEADLINE --- deadline [task name] /by [yyyy-mm-dd HH:MM]\n" +
                                                    "        EVENT    --- event [task name] /from [yyyy-mm-dd HH:MM] /to [yyyy-mm-dd HH:MM]" ;

    private static String filePath = "./data/chronos.txt";
    private static ArrayList<Task> tasks = new ArrayList<Task>();
    private static int noOfTasks = 0;

    /**
     * Prints greetings to user.
     */
    public static void greetUser() {
        System.out.println(DIVIDER);
        System.out.println("        Hello! I'm Chronos.");
        System.out.println("        What can I do for you?");
        System.out.println(DIVIDER);
    }

    /**
     * Prints goodbye to user.
     */
    public static void bidGoodbye() {
        System.out.println(DIVIDER);
        System.out.println("        Bye. Hope to see you again soon!");
        System.out.println(DIVIDER);
    }

    /**
     * Prints task list.
     */
    public static void printTasks() {
        System.out.println(DIVIDER);
        System.out.println("        Here are the tasks in your list:");
        for (int i = 1; i < noOfTasks + 1; i++) {
            Task currentTask = tasks.get(i - 1);
            System.out.println("        " + i + ". " + currentTask.toString());
        }
        System.out.println(DIVIDER);
    }

    /**
     * Prints help list.
     */
    public static void printHelp() {
        System.out.println(DIVIDER);
        System.out.println("        There are no outstanding tasks in your list.\n");
        System.out.println("        You may add various tasks with the commands below:\n" + POSSIBLE_COMMANDS);
        System.out.println(DIVIDER);
    }

    /**
     * Saves task list to text file upon each change.
     *
     * @param fw Filewriter object.
     */
    public static void saveTasks(FileWriter fw) throws IOException {
        fw = new FileWriter(filePath);
        fw.write(DIVIDER + "\n");
        for (int j = 1; j < noOfTasks + 1; j++) {
            Task currentTask = tasks.get(j - 1);
            fw.write("        " + j + ". " + currentTask.toString() + "\n");
        }
        fw.write(DIVIDER + "\n");
        fw.close();
    }

    /**
     * Adds amd prints a todo task.
     *
     * @param description Description of the todo task.
     */
    public static void addToDo(String description) {
        Todo todo = new Todo(description);
        tasks.add(todo);
        noOfTasks++;

        System.out.println(DIVIDER);
        System.out.println("        Got it. I've added this task:");
        System.out.println("          " + todo);
        System.out.println("        Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(DIVIDER);
    }

    /**
     * Adds and prints a task with a deadline.
     *
     * @param description Description of the task with deadline.
     * @param dueDate Deadline of the task.
     */
    public static void addDeadline(String description, String dueDate) {
        Deadline deadline = new Deadline(description, dueDate);
        tasks.add(deadline);
        noOfTasks++;

        System.out.println(DIVIDER);
        System.out.println("        Got it. I've added this task:");
        System.out.println("          " + deadline);
        System.out.println("        Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(DIVIDER);
    }

    /**
     * Adds and prints an event.
     *
     * @param description Description of the event.
     * @param from Start date and time of the event.
     * @param to End date and time of the event.
     */
    public static void addEvent(String description, String from, String to) {
        Event event = new Event(description, from, to);
        tasks.add(event);
        noOfTasks++;

        System.out.println(DIVIDER);
        System.out.println("        Got it. I've added this task:");
        System.out.println("          " + event);
        System.out.println("        Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(DIVIDER);
    }

    /**
     * Marks a task as completed.
     *
     * @param selectedTaskNumberToBeMarked Task number to be marked as completed.
     */
    public static void markTask(int selectedTaskNumberToBeMarked) {
        Task selectedTaskToBeMarked = tasks.get(selectedTaskNumberToBeMarked - 1);
        selectedTaskToBeMarked.setMarked();
        tasks.set(selectedTaskNumberToBeMarked - 1, selectedTaskToBeMarked);
        System.out.println(DIVIDER);
        System.out.println("        Nice! I've marked this task as done:");
        System.out.println("          " + selectedTaskToBeMarked);
        System.out.println(DIVIDER);
    }

    /**
     * Unmarks a task as incomplete.
     *
     * @param selectedTaskNumberToBeUnmarked Task number to be unmarked as incomplete.
     */
    public static void unMarkTask(int selectedTaskNumberToBeUnmarked) {
        Task selectedTaskToBeUnmarked = tasks.get(selectedTaskNumberToBeUnmarked - 1);
        selectedTaskToBeUnmarked.setUnmarked();
        tasks.set(selectedTaskNumberToBeUnmarked - 1, selectedTaskToBeUnmarked);
        System.out.println(DIVIDER);
        System.out.println("        OK, I've marked this task as not done yet:");
        System.out.println("          " + selectedTaskToBeUnmarked);
        System.out.println(DIVIDER);
    }

    /**
     * Deletes a task from task list.
     *
     * @param selectedTaskNumberToBeDeleted Task number to be deleted.
     */
    public static void deleteTask(int selectedTaskNumberToBeDeleted) {
        Task deletedTask = tasks.get(selectedTaskNumberToBeDeleted - 1);
        noOfTasks--;

        System.out.println(DIVIDER);
        System.out.println("        Noted. I've removed this task:");
        System.out.println("          " + deletedTask);
        System.out.println("        Now you have " + noOfTasks + " tasks in the list.");
        System.out.println(DIVIDER);
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

    public static void printNumberFormatException() {
        System.out.println(DIVIDER);
        System.out.println("        Task number is not an integer. Please include a valid task number.");
        System.out.println(DIVIDER);
    }

    public static void printIndexOutOfBoundsException() {
        System.out.println(DIVIDER);
        System.out.println("        Task number out of range. Please include a valid task number.");
        System.out.println(DIVIDER);
    }

    /**
     * Initialises text file to store task list and processes user commands.
     *
     * @throws @IOException If directory or file is not found.
     */
    public static void main(String[] args) throws IOException, ChronosException {
        String dirName = "data";
        Path dirPath = Paths.get(dirName);
        if (!Files.exists(dirPath)) {
            Files.createDirectory(dirPath);
        }

        FileWriter fw = new FileWriter(filePath);
        fw.write("There are no outstanding tasks in your list.");
        fw.close();

        Chronos.greetUser();
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String command = sc.nextLine();
            String[] token = command.split(" ", 2);

            if (token[0].equals("bye")) {
                Chronos.bidGoodbye();
                break;
            } else {
                switch(token[0]) {
                    case "list":
                        try {
                            if (token.length != 1) {
                                throw ChronosException.createInvalidListException();
                            } else if (tasks.isEmpty()) {
                                Chronos.printHelp();
                            } else {
                                Chronos.printTasks();
                            }
                        } catch (exception.InvalidListException e){
                            System.out.println(e.getMessage());
                        }
                        break;
                    case "mark":
                        try {
                            if (token.length != 2 || token[1].trim().isEmpty()) {
                                throw ChronosException.createMissingTaskNumberException();
                            } else {
                                try {
                                    int i = Integer.parseInt(token[1]);
                                    Chronos.markTask(i);
                                    Chronos.saveTasks(fw);
                                } catch (NumberFormatException e) {
                                    Chronos.printNumberFormatException();
                                } catch (IndexOutOfBoundsException e) {
                                    Chronos.printIndexOutOfBoundsException();
                                }
                            }
                        } catch (exception.MissingTaskNumberException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case "unmark":
                        try {
                            if (token.length != 2 || token[1].trim().isEmpty()) {
                                throw ChronosException.createMissingTaskNumberException();
                            } else {
                                try {
                                    int i = Integer.parseInt(token[1]);
                                    Chronos.unMarkTask(i);
                                    Chronos.saveTasks(fw);
                                } catch (NumberFormatException e) {
                                    Chronos.printNumberFormatException();
                                } catch (IndexOutOfBoundsException e) {
                                    Chronos.printIndexOutOfBoundsException();
                                }
                            }
                        } catch (exception.MissingTaskNumberException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case "todo":
                        try {
                            if (token.length != 2 || token[1].trim().isEmpty()) {
                                throw ChronosException.createMissingDescriptionException();
                            } else {
                                String description = token[1];
                                Chronos.addToDo(description);
                                Chronos.saveTasks(fw);
                            }
                        } catch (exception.MissingDescriptionException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case "deadline":
                        try {
                            if (!token[1].contains("/by")) {
                                throw ChronosException.createInvalidDeadlineException();
                            }
                            String[] descriptionAndBy = token[1].split("/by");
                            String description = descriptionAndBy[0].trim();
                            String dueDate = Chronos.formatDateTime(descriptionAndBy[1].trim());
                            Chronos.addDeadline(description, dueDate);
                            Chronos.saveTasks(fw);
                        } catch (exception.InvalidDeadlineException e) {
                            System.out.println(e.getMessage());
                        } catch (Exception e) {
                            System.out.println(DIVIDER);
                            System.out.println("        Invalid command. Please include a task name and a valid due date following the syntax of the example below:");
                            System.out.println("        e.g. deadline return library book /by 2024-09-22 15:00");
                            System.out.println(DIVIDER);
                        }
                        break;
                    case "event":
                        try {
                            if (!token[1].contains("/from") && !token[1].contains("/to")) {
                                throw ChronosException.createInvalidEventException();
                            }
                            String[] descriptionAndDate = token[1].split("/from");
                            String description = descriptionAndDate[0].trim();
                            String[] fromAndTo = descriptionAndDate[1].split("/to");
                            String fromDateAndTime = Chronos.formatDateTime(fromAndTo[0].trim());
                            String toDateAndTime = Chronos.formatDateTime(fromAndTo[1].trim());
                            Chronos.addEvent(description, fromDateAndTime, toDateAndTime);
                            Chronos.saveTasks(fw);
                        } catch (exception.InvalidEventException e) {
                            System.out.println(e.getMessage());
                        } catch (Exception e) {
                            System.out.println(DIVIDER);
                            System.out.println("        Invalid command. Please include a task name and a valid due date following the syntax of the example below:");
                            System.out.println("        e.g. event concert /from 2024-02-16 18:00 /to 2024-02-16 20:00");
                            System.out.println(DIVIDER);
                        }
                        break;
                    case "delete":
                        try {
                            if (token.length != 2 || token[1].trim().isEmpty()) {
                                throw ChronosException.createMissingTaskNumberException();
                            } else {
                                try {
                                    int i = Integer.parseInt(token[1]);
                                    Chronos.deleteTask(i);
                                    Chronos.saveTasks(fw);
                                } catch (NumberFormatException e) {
                                    Chronos.printNumberFormatException();
                                } catch (IndexOutOfBoundsException e) {
                                    Chronos.printIndexOutOfBoundsException();
                                }
                            }
                        } catch (exception.MissingTaskNumberException e) {
                            System.out.println(e.getMessage());
                        }

                        break;
                    default:
                        try {
                            throw ChronosException.createInvalidCommandException();
                        } catch (exception.InvalidCommandException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                }
            }
        }

        sc.close();
    }
}