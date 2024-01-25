import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Asher {
    private static List<Task> tasks = new ArrayList<>();

    private static void greet() {
        System.out.println("Hello! I'm Asher. What can I do for you?");
    }

    private static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void addTask(Task task) throws BotException {
        if (tasks.size() < 100) {
            tasks.add(task);
            System.out.println("Got it. I've added this task:");
            System.out.println(" " + task);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        } else {
            throw new BotException("Task List is full, unable to add more.");
        }
    }

    private static Todo createtoDo(String command) throws BotException {
        if (command.length() >= 5) {
            String description = command.substring(5).trim();
            if (!description.isEmpty()) {
                return new Todo(description);
            } else {
                throw new BotException("Todo cannot be empty.");
            }
        } else {
            throw new BotException("Todo Command is invalid!");
        }
    }

    private static Deadline createDeadline(String command) throws BotException {
        int split = command.indexOf("/by");
        if (split == -1) {
            throw new BotException("Due date not found!");
        }
        if (split + 4 >= command.length()) {
            throw new BotException("No such deadline!");
        }

        String description = command.substring(9, split).trim();
        String deadline = command.substring(split + 4).trim();

        if (description.isEmpty() || deadline.isEmpty()) {
            throw new BotException("Description and deadline cannot be empty!");
        }
        return new Deadline(description, deadline);
    }

    private static Event createEvent(String command) throws BotException {
        int split1 = command.indexOf("/from");
        int split2 = command.indexOf("/to");
        if (split1 == -1 || split2 == -1) {
            throw new BotException("Start and end time not found!");
        }

        if (split2 + 4 >= command.length()) {
            throw new BotException("End time not found!");
        }

        String description = command.substring(6, split1).trim();
        String startDate = command.substring(split1 + 6, split2).trim();
        String deadline = command.substring(split2 + 4).trim();

        if (description.isEmpty() || startDate.isEmpty() || deadline.isEmpty()) {
            throw new BotException("Description, start time and end time not found!");
        }

        return new Event(description, startDate, deadline);
    }

    private static void displayTasks() {
        System.out.println("Here are the tasks in your list:");

        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
    }

    private static int getTaskNumber(String task) {
        String[] word = task.split(" ");
        if (word.length == 2) {
            int taskId = Integer.parseInt(word[1]);
            for (int i = 0; i < tasks.size(); i++) {
                if (tasks.get(i).getId() == taskId) {
                    return i;
                }
            }
        }
        return -1;
    }

    private static void markTaskDone(String task) throws BotException {
        int taskNumber = getTaskNumber(task);
        if (taskNumber != -1) {
            tasks.get(taskNumber).markDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(" "  + tasks.get(taskNumber));
        } else {
            throw new BotException("Invalid Task!");
        }
    }

    private static void markTaskUndone(String task) throws BotException {
        int taskNumber = getTaskNumber(task);
        if (taskNumber != -1) {
            tasks.get(taskNumber).markUndone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("  " + tasks.get(taskNumber));
        } else {
            throw new BotException("Invalid Task!");
        }
    }

    private static int getTaskIndexById(int taskId) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getTaskId() == taskId) {
                return i;
            }
        }
        return -1;
    }

    private static void updateTaskIds() {
        for (int i = 0; i < tasks.size(); i++) {
            tasks.get(i).setId(i + 1);
        }
    }

    private static void deleteTask(int taskId) throws BotException{
        int taskIndex = getTaskIndexById(taskId);
        if (taskIndex != -1) {
            Task removedTask = tasks.remove(taskIndex);
            System.out.println("Noted. I've removed this task:");
            System.out.println(" " + removedTask);
            updateTaskIds();
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        } else {
            throw new BotException("Task not found!");
        }
    }

    public static void processCommand(String command) throws BotException {
        String[] word = command.split(" ");

        if (word[0].equals("bye")) {
            Asher.exit();
        } else if (word[0].equals("list")) {
            displayTasks();
        } else if (word[0].equals("mark")) {
            markTaskDone(command);
        } else if (word[0].equals("unmark")) {
            markTaskUndone(command);
        } else if (word[0].equals("todo")) {
            addTask(createtoDo(command));
        } else if (word[0].equals("deadline")) {
            addTask(createDeadline(command));
        } else if (word[0].equals("event")) {
            addTask(createEvent(command));
        } else if (word[0].equals("delete")) {
            deleteTask(Integer.parseInt(word[1]));
        } else {
            throw new BotException("Invalid Command!");
        }
    }

    public static void main(String[] args) {
        Asher.greet();
        Scanner scanner = new Scanner(System.in);
        String command;

        try {
            do {
                command = scanner.nextLine();
                Asher.processCommand(command);
            } while (!command.equals("bye"));
        } catch (BotException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}