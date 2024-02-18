package processor;

import tasks.Task;
import tasks.TaskList;
import ui.Ui;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.Arrays;
import java.util.List;

public class AddTaskProcessor extends Processor {


    /**
     * Constructor for Processor element
     * @param taskList
     * @param chatbotUi
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
        assert componentsSplitBySpace.length > 0 : "User input should have at least one word";
        int previousSize = taskList.size();

        if (componentsSplitBySpace.length == 0) {
            System.out.println(chatbotUi.dividerWrapper("Can not type a blank input!"));
            return;
        }

        String command = componentsSplitBySpace[0];

        List<String> validCommands = Arrays.asList("todo", "deadline", "event");

        if (!validCommands.stream().anyMatch(command::equals)) {
            System.out.println(chatbotUi.dividerWrapper("I do not know what type of task that is!"));
            return;
        }

        if (componentsSplitBySpace.length == 1) {
            System.out.println(chatbotUi.dividerWrapper("Description of todo can't be blank!"));
            return;
        }

        
        String todoDescription = componentsSplitBySpace[1].trim();

        
        String[] componentsSplitByTime = userInput.split("//");

        
        String deadlineOrEventDescription = componentsSplitByTime[0].substring(
            componentsSplitByTime[0].indexOf(' ') + 1);

        switch (command) {
        // Case for adding a todo task
        case "todo":
            taskList.addTask(new Task(todoDescription, false));
            System.out.println(chatbotUi.dividerWrapper(
                    "Got it. I've added this task:\n" + taskList.getTaskAtIndex(taskList.size() - 1)
                    + "\nNow you have " + taskList.size() + " tasks in the list."));
            break;
        // Case for adding a deadline task
        case "deadline":
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
            break;

        // Case for adding an event task
        case "event":

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
                taskList.addEventTask(deadlineOrEventDescription, start, end.substring(3)); //substring(3) to remove "to "
            }

            assert taskList.size() > previousSize : "Task list size should have increased";

            System.out.println(chatbotUi.dividerWrapper(
                    "Got it. I've added this task:\n" + taskList.getTaskAtIndex(taskList.size() - 1)
                    + "\nNow you have " + taskList.size() + " tasks in the list."));
            break;
        default:
            System.out.println(chatbotUi.dividerWrapper("Invalid command. Please enter a valid command."));
        }

        try {
            storage.writeToFile();
        } catch (IOException e) {
            System.out.println(chatbotUi.dividerWrapper("Error writing to file in storage."));

        }

    }
}
