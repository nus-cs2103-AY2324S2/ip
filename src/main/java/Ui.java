import Tasks.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
public class Ui {

    private final String CHATBOTNAME = "Kervyn";
    public Ui() {}

    public void startChatBot(TaskList taskList, Storage storage) throws IOException {
        Scanner scanner = new Scanner(System.in);

        ArrayList<Task> userTasks = taskList.getTaskList();
        System.out.println("\tHello! I'm " + this.CHATBOTNAME);
        System.out.println("\tWhat can I do for you?");
        String userInput;
        Task task;

        do {
            userInput = scanner.nextLine();
            // To check for case when input is mark/unmark
            String[] processedUserInput = userInput.split(" ");
            switch (processedUserInput[0]) {
                case "bye":
                    System.out.println("\tBye. Hope to see you again soon!");
                    break;
                case "list":
                    taskList.listTasks(userTasks);
                    break;
                case "mark":
                    taskList.markTask(userTasks, processedUserInput);
                    break;
                case "unmark":
                    taskList.unMarkTask(userTasks, processedUserInput);
                    break;
                case "delete":
                    taskList.removeTask(userTasks, processedUserInput);
                    break;
                case "todo":
                    ToDo newToDo = getProcessedToDo(userInput);
                    if (newToDo != null) {
                        taskList.addTask(newToDo);
                        toDoTaskTextDisplay(newToDo, userTasks);
                    }
                    break;
                case "deadline":
                    Deadline newDeadline = getProcessedDeadline(userInput);
                    if (newDeadline != null) {
                        taskList.addTask(newDeadline);
                        deadlineTaskTextDisplay(newDeadline, userTasks);
                    }
                    break;
                case "event":
                    Event newEvent = getProcessedEvent(userInput);
                    if (newEvent != null) {
                        taskList.addTask(newEvent);
                        eventTaskTextDisplay(newEvent, userTasks);
                    }
                    break;
                default:
                    System.out.println("\t I'm not sure what that means. Please specify the type of task eg. todo, deadline or event to create a task.");
                    break;
            }
        } while (!Objects.equals(userInput, "bye"));

        for (Task userRequest : userTasks) {
            String content = userRequest.toString();
            storage.writeToFile(content);
        }
    }

    private static ToDo getProcessedToDo(String userInput) {
        try {
            String[] toDoDescriptionArray = userInput.split(" ");

            if (Objects.equals(toDoDescriptionArray[1], "")) {
                System.out.println("\tThe description of a todo cannot be empty. Please try again.");
                return null;
            }

            StringBuilder toDoDescription = new StringBuilder();

            for (int i = 1; i < toDoDescriptionArray.length; i++) {
                toDoDescription.append(" ");
                toDoDescription.append(toDoDescriptionArray[i]);
            }

            taskAdded();
            return new ToDo(toDoDescription.toString(), false);
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("\tPlease provide the ToDo task in the required format.");
            return null;
        }
    }
    private static Deadline getProcessedDeadline(String userInput) {
        // Input Format: deadline return book /by Sunday
        // Output Format: [D][ ] return book (by: Sunday)
        try {
            String[] deadlineProcessedInput = userInput.split("/");
            if (Objects.equals(deadlineProcessedInput[1], "")) {
                System.out.println("\tThe deadline of a Deadline task cannot be empty. Please try again.");
                return null;
            }
            String[] deadlineDescriptionArray = deadlineProcessedInput[0].split(" ");

            StringBuilder deadlineDescription = new StringBuilder();

            for (int i = 1; i < deadlineDescriptionArray.length; i++) {
                deadlineDescription.append(" ");
                deadlineDescription.append(deadlineDescriptionArray[i]);
            }

            String[] deadlineTimeArray = userInput.split("/by");
            // No longer a String
            String convertedDeadline = convertDate(deadlineTimeArray[1].trim());

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            LocalDateTime deadline = LocalDateTime.parse(convertedDeadline,  formatter);

            taskAdded();
            return new Deadline(deadlineDescription.toString(), false, deadline);
        }
        catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
            System.out.println("\tPlease provide the deadline in the required format.");
            return null;
        }
    }

    private static Event getProcessedEvent(String userInput) {
        // Input Format: event project meeting /from Mon 2pm /to 4pm
        // Output Format: [E][ ] project meeting (from: Mon 2pm to: 4pm)
        try {
            String[] eventProcessedInput = userInput.split("/");
            if (Objects.equals(eventProcessedInput[1], "") || Objects.equals(eventProcessedInput[2], "")) {
                System.out.println("\tThe description/startDate/endDate for an event cannot be empty. Please try again.");
                return null;
            }
            String[] eventDescriptionArray = eventProcessedInput[0].split(" ");
            String[] eventDateArray = userInput.split("/from");
            String eventStartDateStr = eventDateArray[1].split("/to")[0].trim();
            String eventEndDateStr = eventDateArray[1].split("/to")[1].trim();
            StringBuilder eventDescription = new StringBuilder();

            for (int i = 1; i < eventDescriptionArray.length; i++) {
                eventDescription.append(" ");
                eventDescription.append(eventDescriptionArray[i]);
            }
            String convertedStartDate = convertDate(eventStartDateStr);
            String convertedEndDate = convertDate(eventEndDateStr);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            // No longer String dates
            LocalDateTime startDate = LocalDateTime.parse(convertedStartDate, formatter);
            LocalDateTime endDate = LocalDateTime.parse(convertedEndDate, formatter);


            taskAdded();
            return new Event(eventDescription.toString(), false, startDate, endDate);
        } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
            System.out.println("\tPlease provide the start date / end date in the required format that looks like dd-MM-yyyy HHmm.");
            return null;
        }
    }

    private static void taskAdded() {
        System.out.println("\tUnderstood. I've added this task:");
    }

    private static void toDoTaskTextDisplay(ToDo toDo, ArrayList<Task> userTasks) {
        System.out.println("\t[" + toDo.getCapitalType() + "]" + "[ ]" + toDo.getDescription());
        System.out.println("\tNow you have " + userTasks.size() + " tasks in the list.");
    }

    private static void deadlineTaskTextDisplay(Deadline deadline, ArrayList<Task> userTasks) {
        System.out.println("\t[" + deadline.getCapitalType() + "]" + "[ ]" + deadline.getDescription() + " (by: " + deadline.getDeadline() + ")");
        System.out.println("\tNow you have " + userTasks.size() + " tasks in the list.");
    }

    private static void eventTaskTextDisplay(Event event, ArrayList<Task> userTasks) {
        System.out.println("\t[" + event.getCapitalType() + "]" + "[ ]" + event.getDescription() + " (from: " + event.getStartDate() + " to: " + event.getEndDate() + ")");
        System.out.println("\tNow you have " + userTasks.size() + " tasks in the list.");
    }

    private static String convertDate(String inputDateTime) {
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
}
