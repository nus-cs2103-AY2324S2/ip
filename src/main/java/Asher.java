import java.util.Scanner;

public class Asher {
    private static Task[] tasks = new Task[100];
    private static int count = 0;

    private static void greet() {
        System.out.println("Hello! I'm Asher. What can I do for you?");
    }

    private static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void addTask(Task task) throws BotException {
        if (count < tasks.length) {
            tasks[count] = task;
            count++;
            System.out.println("Got it. I've added this task:");
            System.out.println(" " + task);
            System.out.println("Now you have " + count + " tasks in the list.");
        } else {
            throw new BotException("Task List is full, unable to add more.");
        }
    }

    private static void displayTasks() {
        System.out.println("Here are the tasks in your list:");

        for (int i = 0; i < count; i++) {
            System.out.println((i + 1) + "." + tasks[i]);
        }
    }

    private static int getTaskNumber(String task) {
        String[] word = task.split(" ");
        if (word.length == 2) {
            int number = Integer.parseInt(word[1]) - 1;
            if (number >= 0 && number < count) {
                return number;
            }
        }
        return -1;
    }

    private static void markTaskDone(String task) throws BotException {
        int taskNumber = getTaskNumber(task);
        if (taskNumber != -1) {
            tasks[taskNumber].markDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(" "  + tasks[taskNumber]);
        } else {
            throw new BotException("Invalid Task!");
        }
    }

    private static void markTaskUndone(String task) throws BotException {
        int taskNumber = getTaskNumber(task);
        if (taskNumber != -1) {
            tasks[taskNumber].markUndone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("  " + tasks[taskNumber]);
        } else {
            throw new BotException("Invalid Task!");
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