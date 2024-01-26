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

    private static int parseNumberInCommand(String command) {
        // Expect input.txt in this form: "<command> <number>"
        int spaceIndex = command.indexOf(' ');
        String numberString = command.substring(spaceIndex + 1);
        return Integer.parseInt(numberString);
    }

    private static void handleListCommand() {
        // display task
        System.out.println("Task list:");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            int displayedIndex = i + 1;
            System.out.println(displayedIndex + ". " + task.getDisplayedString());
            //System.out.printf("%d. %s %s\n", displayedIndex, task.getStatusIcon(), task.getDescription());
        }
    }

    private static void handleMarkOrUnmarkCommand(String command, boolean isDone) throws LivException {
        int index = parseNumberInCommand(command);
        int trueIndex = index - 1;
        if ((trueIndex < 0) || (trueIndex >= tasks.size())) {
            throw new LivException("This command number does not exist in your list!");
        }
        Task task = tasks.get(trueIndex);
        if (isDone) {
            task.markAsDone();
        } else {
            task.markAsNotDone();
        }
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
        String from = timeInterval.substring(0, splitterIndex - 1);
        String to = timeInterval.substring(splitterIndex + 4);
        Event newEvent = new Event(description, from, to);
        tasks.add(newEvent);
        System.out.println("Event added:");
        System.out.println(newEvent.getDisplayedString());
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


