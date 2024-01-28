import java.util.Scanner;
import java.util.ArrayList;

public class Liv {
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static void displayBar() {
        System.out.println("____________________________________________________________");
    }

    private static void handleGreetCommand() {
        System.out.println("Liv, under your instructions!");
        System.out.println("What is your command?\n");
    }

    private static void handleByeCommand() {
        System.out.println("Farewell, see you next time!");
    }

    private static int parseNumberInCommand(String command) throws LivException {
        // Expect input.txt in this form: "<command> <number>"
        int spaceIndex = command.indexOf(' ');
        if (spaceIndex == -1) {
            throw new LivException("Please state the mission number!");
        }
        String numberString = command.substring(spaceIndex + 1);
        if (!numberString.matches("\\d+")) {
            throw new LivException("Please enter a number as the mission number!");
        }
        return Integer.parseInt(numberString);
    }

    private static void handleListCommand() {
        // display task
        System.out.println("Here are the missions you added:");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            int displayedIndex = i + 1;
            System.out.println(displayedIndex + ". " + task.getDisplayedString());
            //System.out.printf("%d. %s %s\n", displayedIndex, task.getStatusIcon(), task.getDescription());
        }
        System.out.println("Total: " + tasks.size() + " mission(s)");
    }

    private static void handleMarkOrUnmarkCommand(String command, boolean state) throws LivException {
        int index = parseNumberInCommand(command);
        int trueIndex = index - 1;
        if ((trueIndex < 0) || (trueIndex >= tasks.size())) {
            throw new LivException("That mission number does not exist in the list!");
        }
        Task task = tasks.get(trueIndex);
        boolean currentState = task.getStatus();
        if (currentState == state) {
            if (currentState) {
                throw new LivException("Mission already marked!");
            } else {
                throw new LivException("Mission already unmarked!");
            }
        }
        if (state) {
            task.markAsDone();
        } else {
            task.markAsNotDone();
        }
    }

    private static void handleDeleteCommand(String command) throws LivException {
        int index = parseNumberInCommand(command);
        int trueIndex = index - 1;
        if ((trueIndex < 0) || (trueIndex >= tasks.size())) {
            throw new LivException("That mission number does not exist in the list!");
        }
        Task removed = tasks.remove(trueIndex);
        System.out.println("Mission deleted from list:");
        System.out.println(removed.getDisplayedString());
        System.out.println("You have " + tasks.size() + " mission(s) on the list");
    }

    private static void handleTodoTask(String command) throws LivException {
        int spaceIndex = command.indexOf(' ');
        if (spaceIndex == -1) {
            throw new LivException("Description cannot be empty!");
        }
        String description = command.substring(spaceIndex + 1);
        TodoTask newTodoTask = new TodoTask(description);
        tasks.add(newTodoTask);
        System.out.println("Task added:");
        System.out.println(newTodoTask.getDisplayedString());
        System.out.println("You have " + tasks.size() + " mission(s) on the list");
    }

    private static void handleDeadlineTask(String command) throws LivException {
        // deadline <description> /by <time>
        int spaceIndex = command.indexOf(' ');
        if (spaceIndex == -1) {
            throw new LivException("Description cannot be empty!");
        }
        int timeIndex = command.indexOf('/');
        if (timeIndex == -1) {
            throw new LivException("Time cannot be empty!");
        }
        String description = command.substring(spaceIndex + 1, timeIndex - 1);
        String time = command.substring(timeIndex + 4);
        Deadline newDeadline = new Deadline(description, time);
        tasks.add(newDeadline);
        System.out.println("Deadline added:");
        System.out.println(newDeadline.getDisplayedString());
        System.out.println("You have " + tasks.size() + " mission(s) on the list");
    }

    private static void handleEventTask(String command) throws LivException {
        int spaceIndex = command.indexOf(' ');
        if (spaceIndex == -1) {
            throw new LivException("Description cannot be empty!");
        }
        int timeIntervalIndex = command.indexOf('/');
        if (timeIntervalIndex == -1) {
            throw new LivException("Time cannot be empty!");
        }
        String description = command.substring(spaceIndex + 1, timeIntervalIndex - 1);
        String timeInterval = command.substring(timeIntervalIndex + 6);
        int splitterIndex = timeInterval.indexOf('/');
        if (splitterIndex == -1) {
            throw new LivException("Please enter the correct format for time!");
        }
        String from = timeInterval.substring(0, splitterIndex - 1);
        String to = timeInterval.substring(splitterIndex + 4);
        Event newEvent = new Event(description, from, to);
        tasks.add(newEvent);
        System.out.println("Event added:");
        System.out.println(newEvent.getDisplayedString());
        System.out.println("You have " + tasks.size() + " mission(s) on the list");
    }
    private static void handleNewTask (String command) throws LivException {
        if (command.startsWith("todo")) {
            handleTodoTask(command);
        } else if (command.startsWith("deadline")) {
            handleDeadlineTask(command);
        } else if (command.startsWith("event")) {
            handleEventTask(command);
        } else {
            throw new LivException("Invalid command");
        }
    }

    public static void main(String[] args) {
        handleGreetCommand();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            displayBar();
            try {
                if (input.equalsIgnoreCase("bye")) {
                    handleByeCommand();
                    displayBar();
                    break;
                } else if (input.equalsIgnoreCase("list")) {
                    handleListCommand();
                } else if (input.startsWith("mark")) {
                    handleMarkOrUnmarkCommand(input, true);
                } else if (input.startsWith("unmark")) {
                    handleMarkOrUnmarkCommand(input, false);
                } else if (input.startsWith("delete")) {
                    handleDeleteCommand(input);
                } else {
                    handleNewTask(input);
                }
            } catch (LivException e) {
                //e.printStackTrace();
                System.out.println(e.getMessage());
            }
            displayBar();
        }
    }
}


