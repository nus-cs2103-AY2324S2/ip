import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.ToDo;

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

    public void startChatBot() throws IOException {
        Scanner scanner = new Scanner(System.in);
        Storage storage = new Storage("data/Kervyn.txt");
        ArrayList<Task> userRequests = Storage.readTasks();

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
                    listTasks(userRequests);
                    break;
                case "mark":
                    markTask(userRequests, processedUserInput);
                    break;
                case "unmark":
                    unMarkTask(userRequests, processedUserInput);
                    break;
                case "delete":
                    removeTask(userRequests, processedUserInput);
                    break;
                case "todo":
                    ToDo newToDo = getProcessedToDo(userInput);
                    if (newToDo != null) {
                        userRequests.add(newToDo);
                        toDoTaskTextDisplay(newToDo, userRequests);
                    }
                    break;
                case "deadline":
                    Deadline newDeadline = getProcessedDeadline(userInput);
                    if (newDeadline != null) {
                        userRequests.add(newDeadline);
                        deadlineTaskTextDisplay(newDeadline, userRequests);
                    }
                    break;
                case "event":
                    Event newEvent = getProcessedEvent(userInput);
                    if (newEvent != null) {
                        userRequests.add(newEvent);
                        eventTaskTextDisplay(newEvent, userRequests);
                    }
                    break;
                default:
                    System.out.println("\t I'm not sure what that means. Please specify the type of task eg. todo, deadline or event to create a task.");
                    break;
            }
        } while (!Objects.equals(userInput, "bye"));

        for (Task userRequest : userRequests) {
            String content = userRequest.toString();
            storage.writeToFile(content);
        }
    }
    private static void markTask(ArrayList<Task> userRequests, String[] processedUserInput) {
        try {
            Task task = userRequests.get(Integer.parseInt(processedUserInput[1]) - 1);
            if (task.getStatus()) {
                taskAlreadyMarked();
            } else {
                System.out.println("\tNice! I've marked this task as done:");
                task.updateStatus();
                char check = task.getStatus() ? 'X' : ' ';
                char type = task.getCapitalType();
                System.out.println("\t[" + type + "] " + "[" + check + "] " + task.getDescription());
            }
        }
        catch (IndexOutOfBoundsException e) {
            // Need to account for trying to mark a task that doesn't exist
            System.out.println("\tTask number provided doesn't exist. Please try again.");
        }
    }

    private static void unMarkTask(ArrayList<Task> userRequests, String[] processedUserInput) {
        try {
            Task task = userRequests.get(Integer.parseInt(processedUserInput[1]) - 1);
            if (!task.getStatus()) {
                taskAlreadyUnMarked();
            } else {
                System.out.println("\tOK, I've marked this task as not done yet:");
                task.updateStatus();
                char check = task.getStatus() ? 'X' : ' ';
                char type = task.getCapitalType();
                System.out.println("\t[" + type + "] " + "[" + check + "] " + task.getDescription());
            }
        }
        catch (IndexOutOfBoundsException e) {
            // Need to account for trying to unmark a task that doesn't exist
            System.out.println("\tTask number provided doesn't exist. Please try again.");
        }
    }

    private static void removeTask(ArrayList<Task> userRequests, String[] processedUserInput) {
        try {
            Task task = userRequests.get(Integer.parseInt(processedUserInput[1]) - 1);
            System.out.println("\tOK, I've removed this task as per your request:");
            char check = task.getStatus() ? 'X' : ' ';
            char type = task.getCapitalType();
            System.out.println("\t[" + type + "] " + "[" + check + "] " + task.getDescription());
            userRequests.remove(task);
        }
        catch (IndexOutOfBoundsException e) {
            // Need to account for trying to delete a task that doesn't exist
            System.out.println("\tTask number provided doesn't exist. Please try again.");
        }
    }

    private static void listTasks(ArrayList<Task> userRequests) {
        System.out.println("\tHere are the tasks on your list:");
        for (int i = 0; i < userRequests.size(); i++) {
            Task task = userRequests.get(i);
            char check = task.getStatus() ? 'X' : ' ';
            char type = task.getCapitalType();
            switch (type) {
                case 'T':
                    System.out.println("\t" + (i + 1) + "." + "[" + type + "] " +  "[" + check + "] " + task.getDescription());
                    break;
                case 'D':
                    Deadline deadlineTask = (Deadline) task;
                    System.out.println("\t" + (i + 1) + "." + "[" + type + "] " + "[" + check + "] " + deadlineTask.getDescription() + " (by: " + deadlineTask.getDeadline() + ")");
                    break;
                case 'E':
                    Event eventTask = (Event) task;
                    System.out.println("\t" + (i + 1) + "." + "[" + type + "] " + "[" + check + "] "  + eventTask.getDescription() + " (from: " + eventTask.getStartDate() + " to: " + eventTask.getEndDate() + ")");
                    break;
            }
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
        }
        catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
            System.out.println("\tPlease provide the start date / end date in the required format that looks like dd-MM-yyyy HHmm.");
            return null;
        }
    }

    private static void taskAlreadyMarked() {
        System.out.println("\tUh oh! It looks like this task is already marked as done, please try again with another task!");
    }

    private static void taskAlreadyUnMarked() {
        System.out.println("\tUh oh! It looks like this task is already marked as not done, please try again with another task!");
    }

    private static void taskAdded() {
        System.out.println("\tUnderstood. I've added this task:");
    }

    private static void toDoTaskTextDisplay(ToDo toDo, ArrayList<Task> userRequests) {
        System.out.println("\t[" + toDo.getCapitalType() + "]" + "[ ]" + toDo.getDescription());
        System.out.println("\tNow you have " + userRequests.size() + " tasks in the list.");
    }

    private static void deadlineTaskTextDisplay(Deadline deadline, ArrayList<Task> userRequests) {
        System.out.println("\t[" + deadline.getCapitalType() + "]" + "[ ]" + deadline.getDescription() + " (by: " + deadline.getDeadline() + ")");
        System.out.println("\tNow you have " + userRequests.size() + " tasks in the list.");
    }

    private static void eventTaskTextDisplay(Event event, ArrayList<Task> userRequests) {
        System.out.println("\t[" + event.getCapitalType() + "]" + "[ ]" + event.getDescription() + " (from: " + event.getStartDate() + " to: " + event.getEndDate() + ")");
        System.out.println("\tNow you have " + userRequests.size() + " tasks in the list.");
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
