import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.*;

public class Felix {
    private static final ArrayList<String> FILE_PATH = new ArrayList<>(List.of("data", "taskList.txt"));
    private static final String BOT_NAME = "Felix";
    private final Storage storage;
    private TaskList tasks;


    Felix() throws IOException {
        this.tasks = new TaskList();
        this.storage = new Storage(FILE_PATH);
    }

    private CommandType getCommand(String[] words) {
        switch (words[0]) {
            case "bye":
                return CommandType.EXIT;
            case "list":
                return CommandType.LIST;
            case "mark":
                return CommandType.MARK;
            case "unmark":
                return CommandType.UNMARK;
            case "todo":
                return CommandType.TODO;
            case "deadline":
                return CommandType.DEADLINE;
            case "event":
                return CommandType.EVENT;
            case "delete":
                return CommandType.DELETE;
            default:
                return CommandType.UNDEFINED;
        }
    }

    private void printHorizontalLine(int length) {
        for (int i = 0; i < length; i++) System.out.print('_');
        System.out.println();
    }

    private void printIntroduction() {
        printHorizontalLine(60);
        System.out.printf("Hello! I'm %s\nWhat can I do for you?\n",BOT_NAME);
        printHorizontalLine(60);
    }

    private void exitProgram() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private void listTasks() {
        System.out.println(this.tasks);
    }

    private void markTask(String[] words) throws FelixException {
        try {
            Task task = this.tasks.getTask(Integer.parseInt(words[1]) - 1);
            task.markAsDone();
            System.out.println("Nice! I have marked this task as done:");
            System.out.println(task);
        } catch (IndexOutOfBoundsException err) {
            throw new FelixException(String.format("You have %d tasks, provide a valid index in the range [1,%d]", this.tasks.getCount(), this.tasks.getCount()));
        } catch (NumberFormatException err) {
            throw new FelixException("Enter a number after \"mark\"");
        }
    }

    private void unmarkTask(String[] words) throws FelixException {
        try {
            Task task = this.tasks.getTask(Integer.parseInt(words[1]) - 1);
            task.unmarkDone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(task);
        } catch (IndexOutOfBoundsException err) {
            throw new FelixException(String.format("You have %d tasks, provide a valid index in the range [1,%d]", this.tasks.getCount(), this.tasks.getCount()));
        } catch (NumberFormatException err) {
            throw new FelixException("Enter a number after \"unmark\"");
        }
    }

    private void addToDo(String[] words) throws FelixException {
        try {
            ToDo task = new ToDo(words[1]);
            System.out.println("Got it. I've added this task:");
            System.out.println(task);
            this.tasks.addTask(task);
            System.out.printf("Now you have %d tasks in the list.\n", this.tasks.getCount());
        } catch (IndexOutOfBoundsException e) {
            throw new FelixException("The description of a todo cannot be empty.");
        }
    }

    private void addDeadline(String[] words) throws FelixException {
        try {
            String[] remainder = words[1].split(" /by ");
            Deadline task = new Deadline(remainder[0], remainder[1]);
            System.out.println("Got it. I've added this task:");
            System.out.println(task);
            this.tasks.addTask(task);
            System.out.printf("Now you have %d tasks in the list.\n", this.tasks.getCount());
        } catch (IndexOutOfBoundsException e) {
            throw new FelixException("Separate deadline from description with \"/by\"");
        } catch (DateTimeParseException e) {
            throw new FelixException("datetime for deadline is not in the format \"yyyy-MM-dd HHmm\"");
        }
    }

    private void addEvent(String[] words) throws FelixException {
        try {
            String[] remainder = words[1].split(" /from | /to ");
            Event task = new Event(remainder[0], remainder[1], remainder[2]);
            System.out.println("Got it. I've added this task:");
            System.out.println(task);
            this.tasks.addTask(task);
            System.out.printf("Now you have %d tasks in the list.\n", this.tasks.getCount());
        } catch (IndexOutOfBoundsException e) {
            throw new FelixException("Command does not follow this format: event {description} /from {start_datetime} /to {end_datetime}");
        } catch (DateTimeParseException e) {
            throw new FelixException("datetime not in the format \"yyyy-MM-dd HHmm\"");
        }
    }

    private void deleteTask(String[] words) throws FelixException {
        try {
            int index = Integer.parseInt(words[1]) - 1;
            Task task = this.tasks.getTask(index);
            this.tasks.deleteTask(index);
            System.out.println("Noted. I've removed this task:");
            System.out.println(task);
            System.out.printf("Now you have %d tasks in the list.\n", this.tasks.getCount());
        } catch (IndexOutOfBoundsException err) {
            throw new FelixException(String.format("You have %d tasks, provide a valid index in the range [1,%d]", this.tasks.getCount(), this.tasks.getCount()));
        } catch (NumberFormatException err) {
            throw new FelixException("Enter a number after \"delete\"");
        }
    }

    public static void main(String[] args) throws IOException, FelixException {
        Felix felix = new Felix();
        felix.tasks = felix.storage.getTasksFromFile();
        Scanner scanner = new Scanner(System.in);
        String logo = "___________    .__  .__        \n"
                + "\\_   _____/___ |  | |__|__  ___\n"
                + " |    __)/ __ \\|  | |  \\  \\/  / \n"
                + " |     \\\\  ___/|  |_|  |>    <  \n"
                + " \\___  / \\___  >____/__/__/\\_ \\ \n"
                + "     \\/      \\/              \\/ \n";

        System.out.println("Hello from\n" + logo);
        boolean loop = true;
        felix.printIntroduction();
        while (loop) {
            String line = scanner.nextLine();
            // separate first word from rest of words
            String[] words = line.split(" ", 2);
            CommandType command = felix.getCommand(words);
            felix.printHorizontalLine(60);
            try {
                if (command == CommandType.EXIT) {
                    felix.exitProgram();
                    loop = false;
                } else if (command == CommandType.LIST) {
                    felix.listTasks();
                } else if (command == CommandType.MARK) {
                    felix.markTask(words);
                } else if (command == CommandType.UNMARK) {
                    felix.unmarkTask(words);
                } else if (command == CommandType.TODO) {
                    felix.addToDo(words);
                } else if (command == CommandType.DEADLINE) {
                    felix.addDeadline(words);
                } else if (command == CommandType.EVENT) {
                    felix.addEvent(words);
                } else if (command == CommandType.DELETE) {
                    felix.deleteTask(words);
                }
                else throw new FelixException("Unrecognised command");
            } catch (FelixException felixErr) {
                System.out.println(felixErr.getMessage());
            }
            felix.printHorizontalLine(60);
        }
        felix.storage.writeToFile(felix.tasks);
    }
}
