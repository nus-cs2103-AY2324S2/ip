package main.java;

import main.java.task.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Paimon {
    /**
     * Default function for sending messages, includes text formatting
     */
    private static void sendTaskMessage(String mainMessage, String subMessage, String closingMessage) {
        System.out.println(mainMessage);
        System.out.println("-------------------->");
        System.out.println(subMessage);
        System.out.println("-------------------->");
        System.out.println(closingMessage);
    }

    private static void sendTaskMessage(String mainMessage, String subMessage) {
        System.out.println(mainMessage);
        System.out.println("-------------------->");
        System.out.println(subMessage);
        System.out.println("-------------------->");
    }

    private static void printTaskList(TaskList list) {
        if (list.getSize() == 0) {
            System.out.println("Your list is currently empty, add some tasks!");
        } else {
            System.out.println("Here is your list so far!");
            System.out.println("-------------------->");
            System.out.println(list);
            System.out.println("-------------------->");
        }

    }

    private static void greeting() {
        System.out.println("-------------------->" + "\nGreetings Traveller!\nI'm Paimon, your friendly guide!\nType help to see what I can help you with!" + "\n-------------------->");
    }

    private static void goodbye() {
        System.out.println("-------------------->" + "\nSee you later Traveller!" + "\n-------------------->");
    }


    public static void main(String[] args) {
        greeting();
        Scanner scanner = new Scanner(System.in);
        TaskList taskList = FileHandler.loadTaskList();
        boolean isActive = true;
        while (isActive) {
            String userInput = scanner.nextLine();
            CommandParser parser = new CommandParser(userInput);

            switch (parser.getType()) {
                case "bye":
                    isActive = false;
                    break;
                case "list":
                    printTaskList(taskList);
                    break;
                case "help":
                    System.out.println("You can perform the following actions! Make sure to follow the syntax\n----->");
                    System.out.println("list : Lists all your Tasks");
                    System.out.println("todo <task> : Adds a Task without any deadline");
                    System.out.println("deadline <task> /by <time> : Adds a task done before a time");
                    System.out.println("event<Task> /from <time> /to <time> : Adds a task with a time window");
                    System.out.println("mark/unmark <index>: Mark a Task as done or not done. Index must be a number!");
                    System.out.println("bye : Exits the program");
                    System.out.println("----->");
                    break;
                case "mark":
                    try {
                        String markIndexString = parser.parseInput()[0];
                        int markIndex = Integer.parseInt(markIndexString);
                        if (markIndex >= 1 && markIndex <= taskList.getSize()) {
                            Task markTask = taskList.getTask(markIndex - 1);
                            if (taskList.markTask(markIndex - 1, true)) {
                                sendTaskMessage("Okay Traveller, I've marked this task as done!", taskList.getTask(markIndex - 1).getTask());
                            } else {
                                System.out.println("Traveller, this task is already marked as done!");
                            }
                        } else {
                            System.out.println("Sorry Traveller, that task does not exist");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Sorry Traveller, your input is invalid");
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Sorry Traveller, your index is out of bounds");
                    } catch (ChatException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case "unmark":
                    try {
                        String unmarkIndexString = parser.parseInput()[0];
                        int unmarkIndex = Integer.parseInt(unmarkIndexString);
                        if (unmarkIndex >= 1 && unmarkIndex <= taskList.getSize()) {
                            Task unmarkTask = taskList.getTask(unmarkIndex - 1);
                            if (taskList.markTask(unmarkIndex - 1, false)) {
                                sendTaskMessage("Okay Traveller, I've unmarked this task!", taskList.getTask(unmarkIndex - 1).getTask());
                            } else {
                                System.out.print("Traveller, this task is already unmarked!");
                            }
                        } else {
                            System.out.println("Sorry Traveller, that task does not exist");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Sorry Traveller, your input is invalid");
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Sorry Traveller, your index is out of bounds");
                    } catch (ChatException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case "todo":
                    try {
                        String todoDescription = parser.parseInput()[0];
                        Task todoTask = new TodoTask(todoDescription);
                        taskList.addTask(todoTask);
                        sendTaskMessage("Okay Traveller, I've added the following task!", todoTask.getTask(), "You now have " + taskList.getSize() + " tasks.");
                    } catch (ChatException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "deadline":
                    try {
                        String deadlineDescription = parser.parseInput()[0];
                        String deadlineEndString = parser.parseInput()[1];
                        LocalDateTime deadlineEndDate = DateParser.parseDate(deadlineEndString);
                        Task deadlineTask = new DeadlineTask(deadlineDescription, deadlineEndDate);
                        taskList.addTask(deadlineTask);
                        sendTaskMessage("Okay Traveller, I've added the following task!", deadlineTask.getTask(), "You now have " + taskList.getSize() + " tasks.");
                    } catch (ChatException e) {
                        System.out.println(e.getMessage());
                    } catch (DateTimeParseException e) {
                        System.out.println("The date should be in the format: yyyy/MM/dd, yyyy/MM/dd HH:mm, yyyy-MM-DD, dd/MM/yyyy HH:mm");
                    }
                    break;
                case "event":
                    try {
                        String eventDescription = parser.parseInput()[0];
                        String eventStartString = parser.parseInput()[1];
                        String eventEndString = parser.parseInput()[2];
                        LocalDateTime eventStartDate = DateParser.parseDate(eventStartString);
                        LocalDateTime eventEndDate = DateParser.parseDate(eventEndString);
                        Task eventTask = new EventTask(eventDescription, eventStartDate, eventEndDate);
                        taskList.addTask(eventTask);
                        sendTaskMessage("Okay Traveller, I've added the following task!", eventTask.getTask(), "You now have " + taskList.getSize() + " tasks.");

                    } catch (ChatException e) {
                        System.out.println(e.getMessage());
                    } catch (DateTimeParseException e) {
                        System.out.println("The date should be in the format: yyyy/MM/dd, yyyy/MM/dd HH:mm, yyyy-MM-DD, dd/MM/yyyy HH:mm");
                    }
                    break;
                case "delete":
                    try {
                        String deleteIndexString = parser.parseInput()[0];
                        int deleteIndex = Integer.parseInt(deleteIndexString);
                        if (deleteIndex >= 1 && deleteIndex <= taskList.getSize()) {
                            taskList.deleteTask(deleteIndex - 1);
                            sendTaskMessage("Okay Traveller, I've deleted the task. ", taskList.toString(), "You now have " + taskList.getSize() + " tasks.");

                        } else {
                            System.out.println("Sorry Traveller, that task does not exist");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Sorry Traveller, your input is invalid");
                    } catch (ChatException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                default:
                    System.out.println("Sorry Traveller, your input doesn't match any of our commands!");
                    break;

            }
        }
        goodbye();
    }

}

