import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Chatbot {
    public enum CommandType {
        TODO, DEADLINE, EVENT, MARK, UNMARK, DELETE, BYE, LIST, INVALID;

        public static CommandType fromString(String command) {
            try {
                return CommandType.valueOf(command.toUpperCase());
            } catch (IllegalArgumentException e) {
                return INVALID;
            }
        }
    }

    private static final String LINE = "\t____________________________________________________________\n";
    private String hello = Chatbot.LINE
            + "\tHello! I'm %s\n" + "\tWhat can I do for you?\n"
            + Chatbot.LINE;

    private String goodbye = "\tBye. Hope to see you again soon!\n"
            + Chatbot.LINE;
    private static final String DATA_DIRECTORY = "..\\src\\main\\java\\data";
    private static final String DATA_FILE = DATA_DIRECTORY + "/tasks.txt";

    private String name;
    private ArrayList<Task> tasks;
    private int storageFill;
    private boolean isLoading;

    Chatbot(String name) {
        this.name = name;
        this.tasks = new ArrayList<>();
        this.storageFill = 0;
        this.hello = String.format(this.hello, name);
    }

    // Level 0
    public void greet() {
        String message = this.hello + this.goodbye;
        System.out.print(message);
    }

    // Level 1
    public void echo() {
        System.out.println(this.hello);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();
            System.out.println(Chatbot.LINE);

            if (input.equals("bye")) {
                System.out.println(this.goodbye);
                break;
            } else {
                System.out.println("\t" + input);
                System.out.println(Chatbot.LINE);
            }
        }

        scanner.close();
    }

    public void list() {
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < this.storageFill; i++) {
            String formattedOutput = String.format("\t%d. %s", (i + 1), this.tasks.get(i));
            System.out.println(formattedOutput);
        }
    }

    // Level 2 onwards
    public void store() {
        System.out.println(this.hello);
        this.loadTasks();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();
            System.out.print(Chatbot.LINE);
            String[] parts = input.split(" "); // split input
            if (input.replaceAll("\\s", "").equals("")) { // check empty input
                System.out.println("\tEnter a non-empty command!");
            } else {
                String firstWord = parts[0]; // check for mark/unmark
                CommandType command = CommandType.fromString(firstWord);
                switch (command) {
                    case BYE:
                        System.out.print(this.goodbye);
                        scanner.close();
                        this.saveTasks();
                        return;
                    case LIST:
                        this.list();
                        break;
                    case MARK:
                    case UNMARK:
                    case DELETE:
                        if (parts.length == 2) {
                            try {
                                int taskNumber = Integer.parseInt(parts[1]); // convert 2nd part to an int
                            } catch (NumberFormatException e) {
                                System.out.println("\tPlease enter a valid number after command");
                                continue;
                            }
                            int taskNumber = Integer.parseInt(parts[1]);
                            if (taskNumber > this.storageFill || taskNumber <= 0) { // check if int within range
                                System.out.println("\tOut of bounds!");
                            } else { // valid operation
                                if (parts[0].equals("mark")) {
                                    this.tasks.get(taskNumber - 1).mark();
                                }
                                if (parts[0].equals("unmark")) {
                                    this.tasks.get(taskNumber - 1).unmark();
                                }
                                if (parts[0].equals("delete")) {
                                    Task task = this.tasks.get(taskNumber - 1);
                                    this.tasks.remove(taskNumber - 1);
                                    System.out.println("\tNoted. I've removed this task:\n\t" + task);
                                    System.out.println("\tNow you have " + --this.storageFill + " tasks in the list.");
                                }
                            }
                        } else {
                            System.out.println("\tOOPS!!! That is not a valid command! "
                                    + "Try the following: \n"
                                    + "\ttodo xxx\n"
                                    + "\tdeadline xxx /by xxx\n"
                                    + "\tevent xxx /from xxx /to xxx");
                        }
                        break;

                    default:
                        this.addTask(input, command);
                        break;
                }
            }
            System.out.println(Chatbot.LINE);
        }
    }

    private void addTask(String input, CommandType command) {
        String[] parts = input.split(" ", 2);
        Task newTask = null;
        switch (command) {
            case TODO:
                if (parts[1].replaceAll("\\s", "").equals("")) {
                    System.out.println("\tTask should not be empty!");
                } else {
                    newTask = new Todo(parts[1], input);
                }
                break;
            case DEADLINE:
                String[] deadlineParts = parts[1].split(" /by ", 2);
                if (deadlineParts.length < 2) {
                    System.out.println("\tSpecify /by xxx!");
                } else if (deadlineParts[0].replaceAll("\\s", "").equals("")) {
                    System.out.println("\tTask should not be empty!");
                } else if (deadlineParts[1].replaceAll("\\s", "").equals("")) {
                    System.out.println("\tDue date should not be empty!");
                } else {
                    newTask = new Deadline(deadlineParts[0], deadlineParts[1], input);
                }
                break;
            case EVENT:
                String[] eventParts = parts[1].split(" /from ", 2);
                if (eventParts[0].replaceAll("\\s", "").equals("")) {
                    System.out.println("\tTask should not be empty!");
                } else if (eventParts.length < 2) {
                    // not enough parts for an event
                    System.out.println("\tSpecify /from xxx and /to xxx!");
                } else {
                    String[] timeParts = eventParts[1].split(" /to ", 2);
                    if (timeParts[0].replaceAll("\\s", "").equals("")) {
                        System.out.println("\tStart time should not be empty!");
                    } else if (timeParts.length < 2) {
                        System.out.println("\tSpecify xxx /to xxx!");
                    } else if (timeParts[1].replaceAll("\\s", "").equals("")) {
                        System.out.println("\tEnd time should not be empty!");
                    } else {
                        // Construct the event string
                        String eventTime = timeParts[0] + " to: " + timeParts[1];
                        newTask = new Event(eventParts[0], eventTime, input);
                    }
                }
                break;
            default:
                System.out.println("\tOOPS!!! That is not a valid command! "
                        + "Try the following: \n"
                        + "\ttodo xxx\n"
                        + "\tdeadline xxx /by xxx\n"
                        + "\tevent xxx /from xxx /to xxx");
                break;
        }

        if (newTask != null) {
            this.tasks.add(newTask);
            this.storageFill++;
        }

        if (!isLoading) {
            System.out.println("\tGot it. I've added this task:\n\t" + newTask);
            System.out.println("\tNow you have " + this.storageFill + " tasks in the list.");
        }
    }

    private void addTaskFromFile(String line) {
        String[] parts = line.split(" \\| ");
        CommandType commandType = CommandType.fromString(parts[0]);
        boolean isDone = parts[1].trim().equals("1");
        String input = parts[2];

        switch (commandType) {
            case EVENT:
                this.addTask(input, CommandType.EVENT);
                if (isDone) {
                    this.tasks.get(storageFill - 1).setComplete();
                }
                break;
            case DEADLINE:
                this.addTask(input, CommandType.DEADLINE);
                if (isDone) {
                    this.tasks.get(storageFill - 1).setComplete();
                }
                break;
            case TODO:
                this.addTask(input, CommandType.TODO);
                if (isDone) {
                    this.tasks.get(storageFill - 1).setComplete();
                }
                break;
            default:
                // Handle unknown task type
                break;
        }
    }

    private void loadTasks() {
        File file = new File(DATA_FILE);
        this.isLoading = true;

        if (!file.exists()) {
            ErrorHandler.handleFileNotFoundException(DATA_FILE);
            return;
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                addTaskFromFile(line);
            }
        } catch (FileNotFoundException e) {
            this.isLoading = false;
            ErrorHandler.handleFileNotFoundException(DATA_FILE);
        }
        this.isLoading = false;
    }

    private void saveTasks() {
        try (PrintWriter writer = new PrintWriter(DATA_FILE)) {
            for (Task task : this.tasks) {
                writer.println(task.toFileFormat());
            }
        } catch (IOException e) {
            ErrorHandler.handleIOException();
        }
    }
}