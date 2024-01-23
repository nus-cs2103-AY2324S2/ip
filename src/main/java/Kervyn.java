import Tasks.Task;
import Tasks.Deadline;
import Tasks.ToDo;
import Tasks.Event;

import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;
public class Kervyn {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> userRequests = new ArrayList<>();
        String chatbotName = "Kervyn";

        System.out.println("\tHello! I'm " + chatbotName);
        System.out.println("\tWhat can I do for you?");
        char check;
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
                    System.out.println("\tHere are the tasks on your list:");
                    listTasks(userRequests);
                    break;
                case "mark":
                    task = userRequests.get(Integer.parseInt(processedUserInput[1]) - 1);
                    markTask(task);
                    break;
                case "unmark":
                    task = userRequests.get(Integer.parseInt(processedUserInput[1]) - 1);
                    unMarkTask(task);
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

    }

    private static void markTask(Task task) {
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

    private static void unMarkTask(Task task) {
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

    private static void listTasks(ArrayList<Task> userRequests) {
        for (int i = 0; i < userRequests.size(); i++) {
            Task task = userRequests.get(i);
            char check = task.getStatus() ? 'X' : ' ';
            char type = task.getCapitalType();
            System.out.println("\t" + (i + 1) + "." + "[" + type + "] " +  "[" + check + "] " + task.getDescription());
        }
    }
    private static ToDo getProcessedToDo(String userInput) {
        try {
            String[] toDoDescriptionArray = userInput.split(" ");

            if (Objects.equals(toDoDescriptionArray[1], "")) {
                System.out.println("The description of a todo cannot be empty. Please try again.");
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
            System.out.println("Please provide the ToDo task in the required format.");
            return null;
        }
    }
    private static Deadline getProcessedDeadline(String userInput) {
        // Input Format: deadline return book /by Sunday
        // Output Format: [D][ ] return book (by: Sunday)
        try {
            String[] deadlineProcessedInput = userInput.split("/");
            if (Objects.equals(deadlineProcessedInput[1], "")) {
                System.out.println("The deadline of a Deadline task cannot be empty. Please try again.");
                return null;
            }
            String[] deadlineDescriptionArray = deadlineProcessedInput[0].split(" ");
            String deadline = deadlineProcessedInput[1].split(" ")[1];
            StringBuilder deadlineDescription = new StringBuilder();

            for (int i = 1; i < deadlineDescriptionArray.length; i++) {
                deadlineDescription.append(" ");
                deadlineDescription.append(deadlineDescriptionArray[i]);
            }
            taskAdded();
            return new Deadline(deadlineDescription.toString(), false, deadline);
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please provide the deadline in the required format.");
            return null;
        }
    }

    private static Event getProcessedEvent(String userInput) {
        // Input Format: event project meeting /from Mon 2pm /to 4pm
        // Output Format: [E][ ] project meeting (from: Mon 2pm to: 4pm)
        try {
            String[] eventProcessedInput = userInput.split("/");
            if (Objects.equals(eventProcessedInput[1], "") || Objects.equals(eventProcessedInput[2], "")) {
                System.out.println("The description/startDate/endDate for an event cannot be empty. Please try again.");
                return null;
            }
            String[] eventDescriptionArray = eventProcessedInput[0].split(" ");
            String[] eventStartDateArray = eventProcessedInput[1].split(" ");
            String[] eventEndDateArray = eventProcessedInput[2].split(" ");
            StringBuilder eventDescription = new StringBuilder();
            StringBuilder eventStartDate = new StringBuilder();
            StringBuilder eventEndDate = new StringBuilder();

            for (int i = 1; i < eventDescriptionArray.length; i++) {
                eventDescription.append(" ");
                eventDescription.append(eventDescriptionArray[i]);
            }

            for (int j = 1; j < eventStartDateArray.length; j++) {
                eventStartDate.append(" ");
                eventStartDate.append(eventStartDateArray[j]);
            }

            for (int k = 1; k < eventEndDateArray.length; k++) {
                eventEndDate.append(" ");
                eventEndDate.append(eventEndDateArray[k]);
            }

            taskAdded();
            return new Event(eventDescription.toString(), false, eventStartDate.toString(), eventEndDate.toString());
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please provide the start date / end date in the required format.");
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
        System.out.println("\t[" + event.getCapitalType() + "]" + "[ ]" + event.getDescription() + " (from:" + event.getStartDate() + " to:" + event.getEndDate() + ")");
        System.out.println("\tNow you have " + userRequests.size() + " tasks in the list.");
    }
}
