import java.util.*;

public class Duke {
    private static final String BOT_NAME = "Felix";
    private TaskList tasks;

    Duke() {this.tasks = new TaskList();}

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

    private void markTask(String[] words) throws DukeException {
        try {
            Task task = this.tasks.getTask(Integer.parseInt(words[1]) - 1);
            task.markAsDone();
            System.out.println("Nice! I have marked this task as done:");
            System.out.println(task);
        } catch (IndexOutOfBoundsException err) {
            int index = Integer.parseInt(words[1]) - 1;
            throw new DukeException(String.format("You have %d tasks, provide a valid index in the range [1,%d]", this.tasks.getCount(), this.tasks.getCount()));
        } catch (NumberFormatException err) {
            throw new DukeException("Enter a number after \"mark\"");
        }
    }

    private void unmarkTask(String[] words) throws DukeException {
        try {
            Task task = this.tasks.getTask(Integer.parseInt(words[1]) - 1);
            task.unmarkDone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(task);
        } catch (IndexOutOfBoundsException err) {
            int index = Integer.parseInt(words[1]) - 1;
            throw new DukeException(String.format("You have %d tasks, provide a valid index in the range [1,%d]", this.tasks.getCount(), this.tasks.getCount()));
        } catch (NumberFormatException err) {
            throw new DukeException("Enter a number after \"unmark\"");
        }
    }

    private void addToDo(String[] words) throws DukeException {
        try {
            ToDo task = new ToDo(words[1]);
            System.out.println("Got it. I've added this task:");
            System.out.println(task);
            this.tasks.addTask(task);
            System.out.printf("Now you have %d tasks in the list.\n", this.tasks.getCount());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
    }

    private void addDeadline(String[] words) throws DukeException {
        try {
            String[] remainder = words[1].split(" /by ");
            Deadline task = new Deadline(remainder[0], remainder[1]);
            System.out.println("Got it. I've added this task:");
            System.out.println(task);
            this.tasks.addTask(task);
            System.out.printf("Now you have %d tasks in the list.\n", this.tasks.getCount());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Separate deadline from description with \"/by\"");
        }
    }

    private void addEvent(String[] words) throws DukeException {
        try {
            String[] remainder = words[1].split(" /from | /to ");
            Event task = new Event(remainder[0], remainder[1], remainder[2]);
            System.out.println("Got it. I've added this task:");
            System.out.println(task);
            this.tasks.addTask(task);
            System.out.printf("Now you have %d tasks in the list.\n", this.tasks.getCount());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Command does not follow this format: event {description} /from {start_datetime} /to {end_datetime}");
        }
    }

    private void deleteTask(String[] words) throws DukeException {
        try {
            int index = Integer.parseInt(words[1]) - 1;
            Task task = this.tasks.getTask(index);
            this.tasks.deleteTask(index);
            System.out.println("Noted. I've removed this task:");
            System.out.println(task);
            System.out.printf("Now you have %d tasks in the list.\n", this.tasks.getCount());
        } catch (IndexOutOfBoundsException err) {
            int index = Integer.parseInt(words[1]) - 1;
            throw new DukeException(String.format("You have %d tasks, provide a valid index in the range [1,%d]", this.tasks.getCount(), this.tasks.getCount()));
        } catch (NumberFormatException err) {
            throw new DukeException("Enter a number after \"delete\"");
        }
    }
    public static void main(String[] args) {
        Duke duke = new Duke();
        Scanner scanner = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        boolean loop = true;
        duke.printIntroduction();
        while (loop) {
            String line = scanner.nextLine();
            // separate first word from rest of words
            String[] words = line.split(" ", 2);
            CommandType command = duke.getCommand(words);
            duke.printHorizontalLine(60);
            try {
                if (command == CommandType.EXIT) {
                    duke.exitProgram();
                    loop = false;
                } else if (command == CommandType.LIST) {
                    duke.listTasks();
                } else if (command == CommandType.MARK) {
                    duke.markTask(words);
                } else if (command == CommandType.UNMARK) {
                    duke.unmarkTask(words);
                } else if (command == CommandType.TODO) {
                    duke.addToDo(words);
                } else if (command == CommandType.DEADLINE) {
                    duke.addDeadline(words);
                } else if (command == CommandType.EVENT) {
                    duke.addEvent(words);
                } else if (command == CommandType.DELETE) {
                    duke.deleteTask(words);
                }
                else throw new DukeException("Unrecognised command");
            } catch (DukeException dukeErr) {
                System.out.println(dukeErr.getMessage());
            }
            duke.printHorizontalLine(60);
        }
    }
}
