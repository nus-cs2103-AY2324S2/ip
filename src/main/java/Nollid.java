import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner; // For reading user input
import java.util.ArrayList; // For storing to-do tasks
import java.util.Arrays;

public class Nollid {
    private TaskList tasks;
    private Storage storage;
    /**
     * Unicode character U+2605 unlikely to be entered by user.
     */

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

    public Nollid(Path filePath) {
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(this.storage.load());
    }
    public static void main(String[] args) {
        new Nollid(Storage.DEFAULT_FILEPATH).run();
    }

    public void run() {
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
    public void sendWelcomeMessage() {
        String welcomeMessage = "Hello! I'm Nollid.\n" + "What can I do for you?";
        botSays(welcomeMessage);
    }

    /**
     * Sends a goodbye message upon exiting the bot, with horizontal lines
     * printed for visual separation.
     */
    public void sendGoodbyeMessage() {
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
    public void addTaskToList(Task task) {
        tasks.add(task);

        String message = "Alright, added:\n" + "\t" + task.toString() + "\n";

        int listSize = tasks.size();

        // "task" for singular, "tasks" for plural
        if (listSize == 1) {
            message += "You now have " + listSize + " task in your list.";
        } else {
            message += "You now have " + listSize + " tasks in your list.";
        }
        botSays(message);

        this.storage.update(this.tasks);
    }

    /**
     * Formats message that the bot will send.
     *
     * @param message The message for the bot to send.
     */
    public void botSays(String message) {
        // Length of line to be printed.
        int lineLength = 30;

        printHorizontalLine(lineLength);
        System.out.println(message);
        printHorizontalLine(lineLength);
    }

    /**
     * Method to execute when the 'list' command is used.
     */
    public void listCommand() {
        // List items in to-do list
        StringBuilder response = new StringBuilder("Here are the tasks in your list: \n");
        if (tasks.isEmpty()) {
            response = new StringBuilder("Your list is empty!");
        }

        for (int i = 0; i < tasks.size(); i++) {
            if (i < tasks.size() - 1) {
                response.append(i + 1).append(".").append(tasks.get(i).toString()).append("\n");
            } else {
                response.append(i + 1).append(".").append(tasks.get(i).toString());
            }
        }
        botSays(response.toString());
    }

    /**
     * Method to execute when the 'mark' command is used.
     *
     * @throws DukeException if marking selected task as done is not possible.
     */
    public void markCommand(ArrayList<String> userInputAsList) throws DukeException {
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
    public void markDone(int taskIndex) {
        tasks.get(taskIndex - 1).setDone(true);
        String response = "Good job! I've marked this task as done: \n"
                + "\t " + tasks.get(taskIndex - 1).toString();
        botSays(response);

        this.storage.update(this.tasks);
    }

    /**
     * Method to execute when the 'unmark' command is used.
     *
     * @throws DukeException if marking selected task as not done is not possible.
     */
    public void unmarkCommand(ArrayList<String> inputList) throws DukeException {
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
    public void markNotDone(int taskIndex) {
        tasks.get(taskIndex - 1).setDone(false);
        String response = "Alright, I've marked this task as not done yet: \n"
                + "\t " + tasks.get(taskIndex - 1).toString();
        botSays(response);

        this.storage.update(this.tasks);
    }

    /**
     * Method to execute when the 'todo' command is used.
     *
     * @throws DukeException if adding the task was unsuccessful.
     */
    public void addTodoCommand(ArrayList<String> userInputAsList) throws DukeException {
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
    public void addDeadlineCommand(ArrayList<String> inputList) throws DukeException {
        int byIndex = inputList.indexOf("/by");
        if (inputList.size() == 1 || byIndex == 1) {
            throw new DukeException("Deadline description cannot be empty!\n"
                    + Deadline.USAGE_HINT);
        }

        if (byIndex == inputList.size() - 1 || byIndex == -1) {
            throw new DukeException("Please input a deadline!\n"
                    + Deadline.USAGE_HINT);
        }

        StringBuilder taskDescription = new StringBuilder();
        for (int i = 1; i < byIndex; i++) {
            if (i != byIndex - 1) {
                taskDescription.append(inputList.get(i)).append(" ");
            } else {
                taskDescription.append(inputList.get(i));
            }
        }

        StringBuilder deadlineString = new StringBuilder();
        for (int i = byIndex + 1; i < inputList.size(); i++) {
            if (i != inputList.size() - 1) {
                deadlineString.append(inputList.get(i)).append(" ");
            } else {
                deadlineString.append(inputList.get(i));
            }
        }

        try {
            LocalDateTime deadline = Task.getLocalDateTimeFromString(deadlineString.toString());
            Deadline task = new Deadline(taskDescription.toString(), deadline);
            addTaskToList(task);
        } catch (DateTimeParseException e) {
            botSays("Unrecognized deadline format\n"
                    + Deadline.USAGE_HINT);
        }
    }

    /**
     * Method to execute when the 'event' command is used.
     *
     * @throws DukeException if adding the task was unsuccessful.
     */
    public void addEventCommand(ArrayList<String> userInputAsList) throws DukeException {
        int fromIndex = userInputAsList.indexOf("/from");
        int toIndex = userInputAsList.indexOf("/to");

        if (userInputAsList.size() == 1 || fromIndex == 1 || toIndex == 1) {
            throw new DukeException("Event description cannot be empty!\n"
                    + Event.USAGE_HINT);
        }

        if (fromIndex == -1 || fromIndex == userInputAsList.size() - 1 || fromIndex == toIndex - 1) {
            throw new DukeException("Please enter the start of your event!\n"
                    + Event.USAGE_HINT);
        }

        if (toIndex == -1 || toIndex == userInputAsList.size() - 1 || toIndex == fromIndex - 1) {
            throw new DukeException("Please enter the end of your event!\n"
                    + Event.USAGE_HINT);
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

        try {
            LocalDateTime fromDateTime = Task.getLocalDateTimeFromString(from.toString());
            LocalDateTime toDateTime = Task.getLocalDateTimeFromString(to.toString());
            Event task = new Event(taskDescription.toString(), fromDateTime, toDateTime);
            addTaskToList(task);
        } catch (DateTimeParseException e) {
            botSays("Unrecognized start/end format\n"
                    + Event.USAGE_HINT);
        } catch (DukeException e) {
            botSays(e.getMessage());
        }
    }

    /**
     * Saves the appropriate data in the supplied StringBuilders, given the index of the '/from' and '/to' arguments
     * in the user input.
     */
    private void extractEventInfo(ArrayList<String> userInputAsList, int fromIndex, int toIndex,
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
    public void deleteTaskCommand(ArrayList<String> userInputAsList) throws DukeException {
        // This means that the user has not supplied any number with the command
        if (userInputAsList.size() == 1) {
            throw new DukeException("Please enter the task you wish to delete!\n"
                    + "Usage: delete [task number]");
        } else {
            try {
                int taskIndex = Integer.parseInt(userInputAsList.get(1));

                String message = "Alright, the following task has been removed:\n"
                        + "\t" + tasks.remove(taskIndex - 1).toString() + "\n";

                int listSize = tasks.size();

                // "task" for singular, "tasks" for plural
                if (listSize == 1) {
                    message += "You now have " + listSize + " task in your list.";
                } else {
                    message += "You now have " + listSize + " tasks in your list.";
                }

                botSays(message);

                this.storage.update(this.tasks);
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
    public void helpCommand() {
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
}