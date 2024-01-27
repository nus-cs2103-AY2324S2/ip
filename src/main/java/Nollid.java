import java.io.IOException;
import java.nio.file.*;
import java.util.Scanner; // For reading user input
import java.util.ArrayList; // For storing to-do tasks
import java.util.Arrays;

public class Nollid {
    private static ArrayList<Task> taskList = new ArrayList<>(100);
    /**
     * Unicode character U+2605 unlikely to be entered by user.
     */
    private static final String DELIMITER = "\u2605";
    private static final Path DATABASE_FILE = Paths.get(".", "data", "nollid.data");

    private enum Command {
        BYE("bye"),
        LIST("list"),
        TODO("todo"),
        DEADLINE("deadline"),
        EVENT("event"),
        MARK("mark"),
        UNMARK("unmark"),
        DELETE("delete"),
        HELP("help");

        private final String commandName;

        Command(String commandName) {
            this.commandName = commandName;
        }

        @Override
        public String toString() {
            return this.commandName;
        }
    }

    public static void main(String[] args) {
        loadDatabaseFile(taskList);
        sendWelcomeMessage();

        Scanner scanner = new Scanner(System.in);
        String userInput;

        while (true) {
            userInput = scanner.nextLine();
            userInput = userInput.strip();

            if (userInput.isEmpty()) {
                botSays("Please enter a command. Type 'help' to view a list of commands.");
                continue;
            }

            // Split user input into individual words
            // e.g. "i am user input" -> ["i", "am", "user", "input"]
            ArrayList<String> userInputAsList = new ArrayList<>(Arrays.asList(userInput.split(" ")));
            String userCommand = userInputAsList.get(0);

            try {
                if (userCommand.equalsIgnoreCase(Command.BYE.toString())) {
                    break;
                } else if (userCommand.equalsIgnoreCase(Command.LIST.toString())) {
                    listCommand();
                } else if (userCommand.equalsIgnoreCase(Command.MARK.toString())) {
                    markCommand(userInputAsList);
                } else if (userCommand.equalsIgnoreCase(Command.UNMARK.toString())) {
                    unmarkCommand(userInputAsList);
                } else if (userCommand.equalsIgnoreCase(Command.TODO.toString())) {
                    addTodoCommand(userInputAsList);
                } else if (userCommand.equalsIgnoreCase(Command.DEADLINE.toString())) {
                    addDeadlineCommand(userInputAsList);
                } else if (userCommand.equalsIgnoreCase(Command.EVENT.toString())) {
                    addEventCommand(userInputAsList);
                } else if (userCommand.equalsIgnoreCase(Command.DELETE.toString())) {
                    deleteTaskCommand(userInputAsList);
                } else if (userCommand.equalsIgnoreCase(Command.HELP.toString())) {
                    helpCommand();
                } else {
                    botSays("Invalid command. Type 'help' to view a list of commands.");
                }
            } catch (DukeException e) {
                botSays(e.getMessage());
            }
        }
        sendGoodbyeMessage();
    }

    /**
     * Sends a welcome message upon starting the bot, with horizontal lines
     * printed for visual separation.
     */
    public static void sendWelcomeMessage() {
        String welcomeMessage = "Hello! I'm Nollid.\n" + "What can I do for you?";
        botSays(welcomeMessage);
    }

    /**
     * Sends a goodbye message upon exiting the bot, with horizontal lines
     * printed for visual separation.
     */
    public static void sendGoodbyeMessage() {
        String goodbyeMessage = "Bye. Hope to see you again soon!";
        botSays(goodbyeMessage);
    }

    /**
     * Prints a horizontal line with unicode character U+2500.
     *
     * @param length Length of line in characters.
     */
    public static void printHorizontalLine(int length) {
        for (int i = 0; i < length; i++) {
            System.out.print("─");
        }
        System.out.println();
    }

    /**
     * Stores a task in the list.
     *
     * @param task Task to store
     */
    public static void addTaskToList(Task task) {
        taskList.add(task);

        String message = "Alright, added:\n" + "\t" + task.toString() + "\n";

        int listSize = taskList.size();

        // "task" for singular, "tasks" for plural
        if (listSize == 1) {
            message += "You now have " + listSize + " task in your list.";
        } else {
            message += "You now have " + listSize + " tasks in your list.";
        }
        botSays(message);

        updateDatabaseFile();
    }

    /**
     * Formats message that the bot will send.
     *
     * @param message The message for the bot to send.
     */
    public static void botSays(String message) {
        // Length of line to be printed.
        int lineLength = 30;

        printHorizontalLine(lineLength);
        System.out.println(message);
        printHorizontalLine(lineLength);
    }

    /**
     * Method to execute when the 'list' command is used.
     */
    public static void listCommand() {
        // List items in to-do list
        StringBuilder response = new StringBuilder("Here are the tasks in your list: \n");
        if (taskList.isEmpty()) {
            response = new StringBuilder("Your list is empty!");
        }

        for (int i = 0; i < taskList.size(); i++) {
            if (i < taskList.size() - 1) {
                response.append(i + 1).append(".").append(taskList.get(i).toString()).append("\n");
            } else {
                response.append(i + 1).append(".").append(taskList.get(i).toString());
            }
        }
        botSays(response.toString());
    }

    /**
     * Method to execute when the 'mark' command is used.
     *
     * @throws DukeException if marking selected task as done is not possible.
     */
    public static void markCommand(ArrayList<String> userInputAsList) throws DukeException {
        // This means that the user has not supplied any number with the command
        if (userInputAsList.size() == 1) {
            throw new DukeException("Please enter the task you wish to mark as done!\n" + "Usage: mark [task number]");
        } else {
            try {
                int taskIndex = Integer.parseInt(userInputAsList.get(1));
                markDone(taskIndex);
            } catch (NumberFormatException e) {
                throw new DukeException("Please enter a number for the mark command.\n" + "Usage: mark [task number]");
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("Are you sure that's a valid task number? (Tip: use 'list' to check the "
                        + "number of your task!)\n" + "Usage: mark [task number]");
            }
        }
    }

    /**
     * Marks the task with the given index as done.
     */
    public static void markDone(int taskIndex) {
        taskList.get(taskIndex - 1).setDone(true);
        String response = "Good job! I've marked this task as done: \n"
                + "\t " + taskList.get(taskIndex - 1).toString();
        botSays(response);

        updateDatabaseFile();
    }

    /**
     * Method to execute when the 'unmark' command is used.
     *
     * @throws DukeException if marking selected task as not done is not possible.
     */
    public static void unmarkCommand(ArrayList<String> inputList) throws DukeException {
        // This means that the user has not supplied any number with the command
        if (inputList.size() == 1) {
            throw new DukeException("Please enter the task you wish to mark as not done!\n"
                    + "Usage: unmark [task number]");
        } else {
            try {
                int taskIndex = Integer.parseInt(inputList.get(1));
                markNotDone(taskIndex);
            } catch (NumberFormatException e) {
                throw new DukeException("Please enter a number for the unmark command.\n"
                        + "Usage: unmark [task number]");
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("Are you sure that's a valid task number? (Tip: use 'list' to check the "
                        + "number of your task!)\n" + "Usage: unmark [task number]");
            }
        }
    }

    /**
     * Marks the task with the given index as not done.
     */
    public static void markNotDone(int taskIndex) {
        taskList.get(taskIndex - 1).setDone(false);
        String response = "Alright, I've marked this task as not done yet: \n"
                + "\t " + taskList.get(taskIndex - 1).toString();
        botSays(response);

        updateDatabaseFile();
    }

    /**
     * Method to execute when the 'todo' command is used.
     *
     * @throws DukeException if adding the task was unsuccessful.
     */
    public static void addTodoCommand(ArrayList<String> userInputAsList) throws DukeException {
        if (userInputAsList.size() == 1) {
            throw new DukeException("Todo description cannot be empty!\n"
                    + "Usage: todo [task description]");
        }

        StringBuilder taskDescription = new StringBuilder();
        for (int i = 1; i < userInputAsList.size(); i++) {
            if (i != userInputAsList.size() - 1) {
                taskDescription.append(userInputAsList.get(i)).append(" ");
            } else {
                taskDescription.append(userInputAsList.get(i));
            }
        }

        ToDo task = new ToDo(taskDescription.toString());
        addTaskToList(task);
    }

    /**
     * Method to execute when the 'deadline' command is used.
     *
     * @throws DukeException if adding the task was unsuccessful.
     */
    public static void addDeadlineCommand(ArrayList<String> inputList) throws DukeException {
        int byIndex = inputList.indexOf("/by");
        if (inputList.size() == 1 || byIndex == 1) {
            throw new DukeException("Deadline description cannot be empty!\n"
                    + "Usage: deadline [task description] /by [deadline]");
        }

        if (byIndex == inputList.size() - 1 || byIndex == -1) {
            throw new DukeException("Please input a deadline!\n"
                    + "Usage: deadline [task description] /by [deadline]");
        }

        StringBuilder taskDescription = new StringBuilder();
        for (int i = 1; i < byIndex; i++) {
            if (i != byIndex - 1) {
                taskDescription.append(inputList.get(i)).append(" ");
            } else {
                taskDescription.append(inputList.get(i));
            }
        }

        StringBuilder deadline = new StringBuilder();
        for (int i = byIndex + 1; i < inputList.size(); i++) {
            if (i != inputList.size() - 1) {
                deadline.append(inputList.get(i)).append(" ");
            } else {
                deadline.append(inputList.get(i));
            }
        }

        Deadline task = new Deadline(taskDescription.toString(), deadline.toString());
        addTaskToList(task);
    }

    /**
     * Method to execute when the 'event' command is used.
     *
     * @throws DukeException if adding the task was unsuccessful.
     */
    public static void addEventCommand(ArrayList<String> userInputAsList) throws DukeException {
        int fromIndex = userInputAsList.indexOf("/from");
        int toIndex = userInputAsList.indexOf("/to");

        if (userInputAsList.size() == 1 || fromIndex == 1 || toIndex == 1) {
            throw new DukeException("Event description cannot be empty!\n"
                    + "Usage: event [task description] /from [start] /to [end]");
        }

        if (fromIndex == -1 || fromIndex == userInputAsList.size() - 1 || fromIndex == toIndex - 1) {
            throw new DukeException("Please enter the start of your event!\n"
                    + "Usage: event [task description] /from [start] /to [end]");
        }

        if (toIndex == -1 || toIndex == userInputAsList.size() - 1 || toIndex == fromIndex - 1) {
            throw new DukeException("Please enter the end of your event!\n"
                    + "Usage: event [task description] /from [start] /to [end]");
        }

        StringBuilder taskDescription = new StringBuilder();
        StringBuilder from = new StringBuilder();
        StringBuilder to = new StringBuilder();

        // Deal with the user sending "/from" before "/to" or vice versa
        if (fromIndex < toIndex) {
            extractEventInfo(userInputAsList, fromIndex, toIndex, taskDescription, from, to);
        } else {
            extractEventInfo(userInputAsList, toIndex, fromIndex, taskDescription, to, from);
        }

        Event task = new Event(taskDescription.toString(), from.toString(), to.toString());
        addTaskToList(task);
    }

    /**
     * Saves the appropriate data in the supplied StringBuilders, given the index of the '/from' and '/to' arguments
     * in the user input.
     */
    private static void extractEventInfo(ArrayList<String> userInputAsList, int fromIndex, int toIndex,
                                         StringBuilder taskDescription, StringBuilder from, StringBuilder to) {
        for (int i = 1; i < fromIndex; i++) {
            if (i != fromIndex - 1) {
                taskDescription.append(userInputAsList.get(i)).append(" ");
            } else {
                taskDescription.append(userInputAsList.get(i));
            }
        }

        for (int i = fromIndex + 1; i < toIndex; i++) {
            if (i != toIndex - 1) {
                from.append(userInputAsList.get(i)).append(" ");
            } else {
                from.append(userInputAsList.get(i));
            }
        }

        for (int i = toIndex + 1; i < userInputAsList.size(); i++) {
            if (i != userInputAsList.size() - 1) {
                to.append(userInputAsList.get(i)).append(" ");
            } else {
                to.append(userInputAsList.get(i));
            }
        }
    }

    /**
     * Method to execute when the 'delete' command is used.
     *
     * @throws DukeException if deleting the task was unsuccessful.
     */
    public static void deleteTaskCommand(ArrayList<String> userInputAsList) throws DukeException {
        // This means that the user has not supplied any number with the command
        if (userInputAsList.size() == 1) {
            throw new DukeException("Please enter the task you wish to delete!\n"
                    + "Usage: delete [task number]");
        } else {
            try {
                int taskIndex = Integer.parseInt(userInputAsList.get(1));

                String message = "Alright, the following task has been removed:\n"
                        + "\t" + taskList.remove(taskIndex - 1).toString() + "\n";

                int listSize = taskList.size();

                // "task" for singular, "tasks" for plural
                if (listSize == 1) {
                    message += "You now have " + listSize + " task in your list.";
                } else {
                    message += "You now have " + listSize + " tasks in your list.";
                }

                botSays(message);

                updateDatabaseFile();
            } catch (NumberFormatException e) {
                throw new DukeException("Please enter a number for the delete command.\n"
                        + "Usage: delete [task number]");
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("Are you sure that's a valid task number? (Tip: use 'list' to check the "
                        + "number of your task!)\n" + "Usage: delete [task number]");
            }
        }
    }

    /**
     * Method to execute when the 'help' command is used.
     */
    public static void helpCommand() {
        String message = "Available commands:\n"
                + "list \t\t- Lists all your tasks\n"
                + "todo \t\t- Create a new todo task.\n"
                + "deadline \t- Create a new task with a deadline.\n"
                + "event \t\t- Create a new task with a starting and ending time.\n"
                + "mark \t\t- Mark a task as completed.\n"
                + "unmark \t\t- Mark a task as not completed.\n"
                + "delete \t\t- Delete a task.";

        botSays(message);
    }

    /**
     * Initializes the list of tasks from a file saved on disk.
     *
     * @param todoList The list of tasks.
     */
    public static void loadDatabaseFile(ArrayList<Task> todoList) {
        int taskCounter = 0;
        try {
            if (Files.notExists(DATABASE_FILE)) {
                if (Files.notExists(DATABASE_FILE.getParent())) {
                    Files.createDirectories(DATABASE_FILE.getParent());
                }
                Files.createFile(DATABASE_FILE);
                System.out.println("database created");
            }

            for (String line : Files.readAllLines(DATABASE_FILE)) {
                String[] lineArray = line.split(DELIMITER);

                Task taskToAdd;
                // If there is a line that doesn't follow the format, skip it and continue.
                try {
                    String taskDescription = lineArray[2];
                    switch (lineArray[0]) {
                    case "T":
                        taskToAdd = createTask(taskDescription);
                        break;
                    case "D":
                        String deadline = lineArray[3];
                        taskToAdd = createTask(taskDescription, deadline);
                        break;
                    case "E":
                        String from = lineArray[3];
                        String to = lineArray[4];
                        taskToAdd = createTask(taskDescription, from, to);
                        break;
                    default:
                        // Unknown first character, go to next line
                        continue;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    continue;
                }

                String doneFlag = lineArray[1];
                if (doneFlag.equals("1")) {
                    taskToAdd.setDone(true);
                }

                todoList.add(taskToAdd);
                taskCounter++;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        if (taskCounter > 0) {
            if (taskCounter == 1) {
                botSays("Loaded " + taskCounter + " task from " + DATABASE_FILE.toAbsolutePath());
            } else {
                botSays("Loaded " + taskCounter + " tasks from " + DATABASE_FILE.toAbsolutePath());
            }
        }
    }

    /**
     * Returns a ToDo task with the specified description.
     */
    public static ToDo createTask(String description) {
        return new ToDo(description);
    }

    /**
     * Returns a Deadline task with the specified description and deadline.
     */
    public static Deadline createTask(String description, String deadline) {
        return new Deadline(description, deadline);
    }

    /**
     * Returns an Event task with the specified description, start, and end time.
     */
    public static Event createTask(String description, String start, String end) {
        return new Event(description, start, end);
    }

    /**
     * Updates the database file on disk based on the current state of the task list.
     */
    public static void updateDatabaseFile() {
        boolean isFirstLine = true;
        if (taskList.isEmpty()) {
            try {
                Files.write(DATABASE_FILE, "".getBytes());
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        for (Task t : taskList) {
            String lineToWrite = "";
            try {
                if (t instanceof ToDo) {
                    lineToWrite = "T" + DELIMITER + t.getStatusNumber() + DELIMITER + t.getDescription() + "\n";
                } else if (t instanceof Deadline) {
                    Deadline deadline = (Deadline) t;
                    lineToWrite = "D" + DELIMITER + deadline.getStatusNumber() + DELIMITER + deadline.getDescription()
                            + DELIMITER + deadline.getDeadline() + "\n";
                } else if (t instanceof Event) {
                    Event event = (Event) t;
                    lineToWrite = "E" + DELIMITER + event.getStatusNumber() + DELIMITER + event.getDescription()
                            + DELIMITER + event.getFrom() + DELIMITER + event.getTo() + "\n";
                }

                if (isFirstLine) {
                    Files.write(DATABASE_FILE, lineToWrite.getBytes());
                    isFirstLine = false;
                } else {
                    Files.write(DATABASE_FILE, lineToWrite.getBytes(), StandardOpenOption.APPEND);
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
