import java.util.ArrayList;
import java.util.Scanner;

public class Jimmy {
    private static final ArrayList<Task> list = new ArrayList<>();

    private static void greetUser() {
        System.out.println("Hello! I'm Jimmy\nWhat can I do for you?\n");
    }

    private static void createNewTask(String instruction, String details) throws JimmyException {
        Task newTask = null;
        switch (instruction) {
            case "todo":
                newTask = createNewTodo(details);
                break;
            case "deadline":
                newTask = createNewDeadline(details);
                break;
            case "event":
                newTask = createNewEvent(details);
                break;
        }

        list.add(newTask);
        System.out.println("Got it. I've added this task:");
        System.out.println(newTask);
        System.out.println(generateListCounter() + "\n");
    }

    private static Todo createNewTodo(String details) {
        return new Todo(details);
    }

    private static Deadline createNewDeadline(String details) throws JimmyException {
        if (!details.contains(" /by ")) {
            throw new JimmyException("Sorry! " +
                    "Please use the correct format when creating a new deadline.");
        }
        String[] deadlineDetails = details.split(" /by ", 2);
        String deadlineName = deadlineDetails[0], deadline = deadlineDetails[1];
        if (deadlineName.length() == 0 || deadline.length() == 0) {
            throw new JimmyException("Please check that you have entered a deadline name and a deadline.");
        }
        return new Deadline(deadlineName, deadline);
    }

    private static Event createNewEvent(String details) throws JimmyException {
        if (!details.contains(" /from ") || !details.contains(" /to ")) {
            throw new JimmyException("Sorry! " +
                    "Please use the correct format when creating a new event.");
        }
        String[] eventDetails = details.split(" /from | /to ");
        String eventName = eventDetails[0], start = eventDetails[1], end = eventDetails[2];
        if (eventName.length() == 0 || start.length() == 0 || end.length() == 0) {
            throw new JimmyException("Please check that you have entered a event name, a start time and an end time.");
        }
        return new Event(eventName, start, end);
    }

    private static String generateListCounter() {
        if (list.size() == 0) {
            return "You have no tasks, create some now!";
        } else if (list.size() == 1) {
            return "Now you have 1 task in the list.";
        } else {
            return "Now you have " + list.size() + " tasks in the list.";
        }
    }

    private static void deleteTask(String taskIndex) throws JimmyException {
        int deleteTask;
        try {
            deleteTask = Integer.parseInt(taskIndex) - 1;
        } catch (NumberFormatException e) {
            throw new JimmyException("Please only enter an integer.");
        }
        if (deleteTask < 0) {
            throw new JimmyException("Please only enter a positive integer.");

        } else if (deleteTask >= getListSize()) {
            throw new JimmyException("The task you are looking for does not exist.");
        }
        Task deletedTask = list.remove(deleteTask);
        System.out.println("Noted. I've removed this task:");
        System.out.println(deletedTask);
        System.out.println(generateListCounter() + "\n");
    }

    private static void displayTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            if (i == list.size() - 1) {
                System.out.println((i + 1) + "." + list.get(i).toString() + "\n");
            } else {
                System.out.println((i + 1) + "." + list.get(i).toString());
            }
        }
    }

    private static void markTaskComplete(String taskIndex) throws JimmyException {
        int completeTask;
        try {
            completeTask = Integer.parseInt(taskIndex) - 1;
        } catch (NumberFormatException e) {
            throw new JimmyException("Please only enter an integer.");
        }
        if (completeTask < 0) {
            throw new JimmyException("Please only enter a positive integer.");

        } else if (completeTask >= getListSize()) {
            throw new JimmyException("The task you are looking for does not exist.");
        }
        Task curr = list.get(completeTask);
        curr.markAsComplete();
    }

    private static void markTaskIncomplete(String taskIndex) throws JimmyException {
        int incompleteTask;
        try {
            incompleteTask = Integer.parseInt(taskIndex) - 1;
        } catch (NumberFormatException e) {
            throw new JimmyException("Please only enter an integer.");
        }
        if (incompleteTask < 0) {
            throw new JimmyException("Please only enter a positive integer.");

        } else if (incompleteTask >= getListSize()) {
            throw new JimmyException("The task you are looking for does not exist.");
        }
        Task curr = list.get(incompleteTask);
        curr.markAsIncomplete();
    }

    private static int getListSize() {
        return list.size();
    }

    private static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) throws JimmyException{
        Scanner sc = new Scanner(System.in);
        greetUser();

        while (true) {
            String userInput = sc.nextLine();
            String[] inputArray = userInput.split(" ", 2);
            String instruction;
            String details = "";
            if (inputArray.length > 1) {
                instruction = inputArray[0];
                details = inputArray[1];
            } else {
                instruction = inputArray[0];
            }
            switch (instruction) {
                case "bye":
                    exit();
                    return;
                case "list":
                    displayTasks();
                    break;
                case "mark":
                    markTaskComplete(details);
                    break;
                case "unmark":
                    markTaskIncomplete(details);
                    break;
                case "delete":
                    deleteTask(details);
                    break;
                default:
                    createNewTask(instruction, details);
            }
        }
    }
}
