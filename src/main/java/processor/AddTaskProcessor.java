package processor;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

import tasks.Task;
import tasks.TaskList;
import ui.Ui;


/**
 * The AddTaskProcessor class represents a processor that processes the user command to add a task to the TaskList.
 */
public class AddTaskProcessor extends Processor {
    /**
     * Constructor for Processor element
     * @param taskList task list.
     * @param chatbotUi chatbot ui.
     */
    public AddTaskProcessor(TaskList taskList, Ui chatbotUi) {
        super(taskList, chatbotUi);
    }

    /**
     * Processes the given user command by delegating it to the appropriate method in the Processor class.
     * Specifically, this method processes the user command to add a task to the TaskList.
     * @param userInput the user command to be processed
     * @throws IOException if an I/O error occurs while processing the command
     */
    public void processCommand(String userInput) throws IOException {
        String[] componentsSplitBySpace = userInput.split(" ", 2);
        assert componentsSplitBySpace.length > 0 : "User input should have at least one word ya!";
        int previousSize = taskList.size();

        if (componentsSplitBySpace[0].isEmpty()) {
            System.out.println(chatbotUi.dividerWrapper("Cannot have blank!"));
            return;
        }

        String command = componentsSplitBySpace[0];

        List<String> validCommands = Arrays.asList("todo", "deadline", "event");

        if (!validCommands.stream().anyMatch(command::equals)) {
            System.out.println(chatbotUi.dividerWrapper(
                    "Apology, what does that mean, human?"));
            return;
        }

        if (componentsSplitBySpace.length == 1) {
            System.out.println(chatbotUi.dividerWrapper(
                    "The task description cannot be blank."));
            return;
        }
        String todoDescription = componentsSplitBySpace[1].trim();
        String[] componentsSplitByTime = userInput.split("//");
        String deadlineOrEventDescription = componentsSplitByTime[0].substring(
                componentsSplitByTime[0].indexOf(' ') + 1);

        switch (command) {
            case "todo":
                addTodoTask(todoDescription);
                break;
            case "deadline":
                addDeadlineTask(componentsSplitByTime, deadlineOrEventDescription);
                break;
            case "event":
                addEventTask(componentsSplitByTime, deadlineOrEventDescription, previousSize);
                break;
            default:
                System.out.println(chatbotUi.dividerWrapper("Invalid command. Please enter a valid command."));
        }

        try {
            storage.writeToFile(taskList);
        } catch (IOException e) {
            System.out.println(chatbotUi.dividerWrapper("Error writing to file in storage."));

        }

    }

    /**
     * Adds a to-do task to the task list.
     * @param todoDescription the description of the todo task
     */
    private void addTodoTask(String todoDescription) {
        taskList.addTask(new Task(todoDescription, false));
        System.out.println(chatbotUi.dividerWrapper(
                "Got it. I've added this task:\n" + taskList.getTaskAtIndex(taskList.size() - 1)
                        + "\nNow you have " + taskList.size() + " tasks in the list."));
    }

    /**
     * Adds a deadline task to the task list.
     * @param componentsSplitByTime the components split by "//"
     * @param deadlineOrEventDescription the description of the deadline task
     */
    private void addDeadlineTask(String[] componentsSplitByTime, String deadlineOrEventDescription) {
        if (componentsSplitByTime.length < 2) {
            System.out.println(chatbotUi.dividerWrapper("Wrong syntax! Must be `deadline <task> //by <deadline>`"));
            return;
        }

        String deadline = componentsSplitByTime[1].trim();

        if (!deadline.startsWith("by ")) {
            System.out.println(chatbotUi.dividerWrapper("You must start the statement with the word `//by`."));
            return;
        }

        try {
            LocalDateTime deadlineDateTime = LocalDateTime.parse(
                    deadline.substring(3), DateTimeFormatter.ofPattern("M/d/yyyy HHmm"));

            String formattedDeadline = deadlineDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy, ha"));
            taskList.addDeadlineTask(deadlineOrEventDescription, formattedDeadline);

        } catch (DateTimeParseException e) {
            System.out.println(chatbotUi.recommenderWrapper(
                    "Added the task, but recommend using"
                            + " the date/time format `M/d/yyyy HHmm` for better experience."));
            taskList.addDeadlineTask(deadlineOrEventDescription, deadline);
        }

        System.out.println(chatbotUi.dividerWrapper(
                "Got it. I've added this task:\n" + taskList.getTaskAtIndex(taskList.size() - 1)
                        + "\nNow you have " + taskList.size() + " tasks in the list."));
    }

    /**
     * Adds an event task to the task list.
     * @param componentsSplitByTime the components split by "//"
     * @param deadlineOrEventDescription the description of the event task
     */
    private void addEventTask(String[] componentsSplitByTime, String deadlineOrEventDescription, int previousSize) {
        if (componentsSplitByTime.length < 3) {
            System.out.println(chatbotUi.dividerWrapper(
                    "Wrong syntax! Must be `event <task> //from <start date> //to <end date>`"));
            return;
        }
        String start = componentsSplitByTime[1].trim();
        String end = componentsSplitByTime[2].trim();

        if (!start.startsWith("from ") || !end.startsWith("to ")) {
            System.out.println(chatbotUi.dividerWrapper(
                    "You must start the statements with the words `//from` and `//to`."));
            return;
        }

        try {
            LocalDateTime startDateTime = LocalDateTime.parse(
                    start.substring(5), DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
            LocalDateTime endDateTime = LocalDateTime.parse(
                    end.substring(3), DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
            String formattedStart = startDateTime.format(
                    DateTimeFormatter.ofPattern("MMM dd yyyy, ha"));
            String formattedEnd = endDateTime.format(
                    DateTimeFormatter.ofPattern("MMM dd yyyy, ha"));
            taskList.addEventTask(deadlineOrEventDescription, formattedStart, formattedEnd);
        } catch (DateTimeParseException e) {
            System.out.println(chatbotUi.recommenderWrapper(
                    "Added the task, but recommend using"
                            + " the date/time format `M/d/yyyy HHmm` "
                            + "on both start and end dates for better experience."));
            taskList.addEventTask(
                    deadlineOrEventDescription, start, end.substring(3)); //substring(3) to remove "to "
        }

        assert taskList.size() > previousSize : "Task list size should have increased";

        System.out.println(chatbotUi.dividerWrapper(
                "Got it. I've added this task:\n" + taskList.getTaskAtIndex(taskList.size() - 1)
                        + "\nNow you have " + taskList.size() + " tasks in the list."));
    }
}