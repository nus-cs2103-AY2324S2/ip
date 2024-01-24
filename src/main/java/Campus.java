import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

/**
 * Contains the logic for the ChatBot named 'Campus'
 */
public class Campus {
    static List<Task> tasks = new ArrayList<>();

    /**
     * Main Driver Logic of the Campus Class which handles user inputs and sorts them into cases
     */
    public static void main(String[] args) {
        Campus.greet();

        Scanner scanner = new Scanner(System.in);
        String userInput = "";
        int response = 0;

        do {
            try {
                userInput = scanner.nextLine();
                response = processCommand(userInput);
            } catch (CampusException e) {
                String message = "------------------------------\n"
                        + String.format("%s\n", e.getMessage())
                        + "------------------------------\n";
                System.out.println(message);
            }
        } while (response != 1);

        scanner.close();
        Campus.exit();
    }

    /**
     * Main Logic to keep the Chat Bot running until a user decides to quit talking by sending 'bye'
     *
     * @param userInput A String i/o input
     * @return Returns int value 0 except for the "bye" case in which the value is 1
     * @throws CampusException Throws a CampusException, may arise due to improper syntax or unknown commands
     */
    public static int processCommand(String userInput) throws CampusException {
        String[] arr = userInput.split(" ", 2);
        String firstWord;
        String remaining;

        if (arr.length > 1) {
            firstWord = arr[0].trim();
            remaining = arr[1].trim();
        } else {
            firstWord = arr[0].trim();
            remaining = "";
        }

        switch(firstWord) {
            case "list":
                Campus.display();
                break;
            case "mark":
                // Getting the ith Task in the List<Task> field
                // Again Looks Clunky - Need to refactor
                String listNumber = userInput.substring(userInput.length() - 1);
                int index = Integer.parseInt(listNumber) - 1;
                Task task = Campus.tasks.get(index);
                Campus.markDone(task);
                break;
            case "unmark":
                // Getting the ith Task in the List<Task> field
                // Again Looks Clunky - Need to refactor
                String listNumber1 = userInput.substring(userInput.length() - 1);
                int index1 = Integer.parseInt(listNumber1) - 1;
                Task task1 = Campus.tasks.get(index1);
                Campus.markUndone(task1);
                break;
            case "todo":
                if (remaining.isEmpty()) {
                    throw new CampusException("Error! A todo task must have a name, please follow the following syntax: todo <task name>\n");
                } else {
                    ToDos todo = new ToDos(remaining);
                    Campus.add(todo);
                }
                break;
            case "deadline":
                String[] temp = remaining.split("/by", 2);
                if (temp.length == 1) {
                    throw new CampusException("Error! A deadline task must have parameters, please follow the following syntax: deadline <deadline name> /by <endDateTime>\n");
                }
                String deadlineName = temp[0].trim();
                String endDateTime = temp[1].trim();

                if (deadlineName.isEmpty()) {
                    throw new CampusException("Error! A deadline task must have a name, please follow the following syntax: deadline <deadline name> /by <endDateTime>\n");
                } else if (endDateTime.isEmpty()) {
                    throw new CampusException("Error! A deadline task must have an end datetime, please follow the following syntax: deadline <deadline name> /by <endDateTime>\n");
                } else {
                    Deadline deadline = new Deadline(deadlineName, endDateTime);
                    Campus.add(deadline);
                }
                break;
            case "event":
                // seems a little clunky but it will do for now
                String[] temp1 = remaining.split("/from", 2);

                if (temp1.length == 1) {
                    throw new CampusException("Error! An event task must have parameters, please follow the following syntax: event <event name> /from <startDateTime> /to <endDateTime>\n");
                }

                String eventName = temp1[0].trim();
                String remaining1 = temp1[1].trim();
                String[] temp2 = remaining1.split("/to", 2);

                if (temp2.length == 1) {
                    throw new CampusException("Error! An event task must have parameters, please follow the following syntax: event <event name> /from <startDateTime> /to <endDateTime>\n");
                }

                String from = temp2[0].trim();
                String to = temp2[1].trim();

                if (eventName.isEmpty()) {
                    throw new CampusException("Error! An event task must have a name, please follow the following syntax: event <event name> /from <startDateTime> /to <endDateTime>\n");
                } else if (from.isEmpty()) {
                    throw new CampusException("Error! An event task must have a start datetime, please follow the following syntax: event <event name> /from <startDateTime> /to <endDateTime>\n");
                } else if (to.isEmpty()) {
                    throw new CampusException("Error! An event task must have an end datetime, please follow the following syntax: event <event name> /from <startDateTime> /to <endDateTime>\n");
                } else {
                    Event event = new Event(eventName, from, to);
                    Campus.add(event);
                }
                break;
            case "bye":
                return 1;
            case "":
                break;
            default:
                throw new CampusException("Sorry, I don't understand that command, please check for potential spelling errors");
        }
        return 0;
    }

    public static void markDone(Task task) {
        task.markComplete();

        String message = "------------------------------\n"
                + "Nice! I've completed this task successfully:\n"
                + String.format("%s\n", task)
                + "------------------------------\n";

        System.out.println(message);
    }

    public static void markUndone(Task task) {
        task.markIncomplete();

        String message = "------------------------------\n"
                + "Ok, this task is still not done yet:\n"
                + String.format("%s\n", task)
                + "------------------------------\n";

        System.out.println(message);
    }

    /**
     * Displays all the tasks currently stored in the Campus Class by iterating through the list of type Task
     * and adding it to a final string
     */
    public static void display() {
        int numOfTasks = Campus.tasks.size();
        StringBuilder listOfTasks = new StringBuilder();
        for (int i = 0; i < numOfTasks; i++) {
            int listNum = i + 1;
            Task task = Campus.tasks.get(i);
            listOfTasks.append(String.format("%s. %s\n", listNum, task.toString()));
        }

        String message = "------------------------------\n"
                + String.format("%s\n", listOfTasks)
                + "------------------------------\n";

        System.out.println(message);
    }

    /**
     * Adds a Task instance to the list of tasks in the Campus Class
     * @param task an instance of the Task Class
     */
    public static void add(Task task) {
        Campus.tasks.add(task);

        int numOfTasks = Campus.tasks.size();

        String message = "------------------------------\n"
                + "Got it. I've added this to our list of tasks:\n"
                + String.format("added: %s\n", task.toString())
                + String.format("Now you have %s task(s) in the list.\n", numOfTasks)
                + "------------------------------\n";

        System.out.println(message);
    }

    public static void greet() {
        String message = "------------------------------\n"
                + "Hello! I'm Campus\n"
                + "What can I do for you?\n"
                + "\n"
                + "------------------------------\n";

        System.out.println(message);
    }

    public static void exit() {
        String message = "------------------------------\n"
                + "Bye. Hope to see you again soon!\n"
                + "\n"
                + "------------------------------\n";
        System.out.println(message);
    }
}
