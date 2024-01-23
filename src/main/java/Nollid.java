import java.util.Arrays;
import java.util.Scanner; // For reading user input
import java.util.ArrayList; // For storing to-do tasks

public class Nollid {
    private static ArrayList<Task> todoList = new ArrayList<>(100);

    public static void main(String[] args) {
        sendWelcomeMessage();

        Scanner scanner = new Scanner(System.in);
        String userInput;

        while (true) {
            userInput = scanner.nextLine();
            userInput = userInput.strip();

            if (userInput.isEmpty()) {
                botSays("Please enter a command.");
                continue;
            }

            // Split user input into individual words
            // e.g. "i am user input" -> ["i", "am", "user", "input"]
            ArrayList<String> inputList = new ArrayList<>(Arrays.asList(userInput.split(" ")));
            String command = inputList.get(0);
            try {
                if (command.equalsIgnoreCase("bye")) {
                    break;
                } else if (command.equalsIgnoreCase("list")) {
                    listCommand();
                } else if (command.equalsIgnoreCase("mark")) {
                    markCommand(inputList);
                } else if (command.equalsIgnoreCase("unmark")) {
                    unmarkCommand(inputList);
                } else if (command.equalsIgnoreCase("todo")) {
                    addTodoCommand(inputList);
                } else if (command.equalsIgnoreCase("deadline")) {
                    addDeadlineCommand(inputList);
                } else if (command.equalsIgnoreCase("event")) {
                    addEventCommand(inputList);
                } else if (command.equalsIgnoreCase("delete")) {
                    deleteTaskCommand(inputList);
                } else if (command.equalsIgnoreCase("help")) {
                    helpCommand();
                }
                else {
                    botSays("Invalid command. Use 'help' to view a list of commands.");
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
    public static void addToList(Task task) {
        todoList.add(task);

        String message = "Alright, added:\n" + "\t" + task.toString() + "\n";

        int listSize = todoList.size();

        // "task" for singular, "tasks" for plural
        if (listSize == 1) {
            message += "You now have " + listSize + " task in your list.";
        } else {
            message += "You now have " + listSize + " tasks in your list.";
        }
        botSays(message);
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

    public static void listCommand() {
        // List items in to-do list
        StringBuilder response = new StringBuilder("Here are the tasks in your list: \n");
        if (todoList.isEmpty()) {
            response = new StringBuilder("Your list is empty!");
        }

        for (int i = 0; i < todoList.size(); i++) {
            if (i < todoList.size() - 1) {
                response.append(i + 1).append(".").append(todoList.get(i).toString()).append("\n");
            } else {
                response.append(i + 1).append(".").append(todoList.get(i).toString());
            }
        }
        botSays(response.toString());
    }

    public static void markCommand(ArrayList<String> inputList) throws DukeException {
        // This means that the user has not supplied any number with the command
        if (inputList.size() == 1) {
            throw new DukeException("Please enter the task you wish to mark as done!\n" +
                    "Usage: mark [task number]");
        } else {
            try {
                int taskIndex = Integer.parseInt(inputList.get(1));
                markDone(taskIndex);
            } catch (NumberFormatException e) {
                throw new DukeException("Please enter a number for the mark command.\n" +
                        "Usage: mark [task number]");
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("Are you sure that's a valid task number? (Tip: use 'list' to check the number of your task!)\n" +
                        "Usage: mark [task number]");
            }
        }
    }

    public static void markDone(int taskIndex) {
        todoList.get(taskIndex - 1).markDone();
        String response = "Good job! I've marked this task as done: \n" + "\t " + todoList.get(taskIndex - 1).toString();
        botSays(response);
    }

    public static void unmarkCommand(ArrayList<String> inputList) throws DukeException {
        // This means that the user has not supplied any number with the command
        if (inputList.size() == 1) {
            throw new DukeException("Please enter the task you wish to mark as not done!\n" +
                    "Usage: unmark [task number]");
        } else {
            try {
                int taskIndex = Integer.parseInt(inputList.get(1));
                markNotDone(taskIndex);
            } catch (NumberFormatException e) {
                throw new DukeException("Please enter a number for the unmark command.\n" +
                        "Usage: unmark [task number]");
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("Are you sure that's a valid task number? (Tip: use 'list' to check the number of your task!)\n" +
                        "Usage: unmark [task number]");
            }
        }
    }

    public static void markNotDone(int taskIndex) {
        todoList.get(taskIndex - 1).markNotDone();
        String response = "Alright, I've marked this task as not done yet: \n" + "\t " + todoList.get(taskIndex - 1).toString();
        botSays(response);
    }

    public static void addTodoCommand(ArrayList<String> inputList) throws DukeException {
        if (inputList.size() == 1) {
            throw new DukeException("Todo description cannot be empty!\n" +
                    "Usage: todo [task description]");
        }

        StringBuilder taskDescription = new StringBuilder();
        for (int i = 1; i < inputList.size(); i++) {
            if (i != inputList.size() - 1) {
                taskDescription.append(inputList.get(i)).append(" ");
            } else {
                taskDescription.append(inputList.get(i));
            }
        }

        ToDo task = new ToDo(taskDescription.toString());
        addToList(task);
    }

    public static void addDeadlineCommand(ArrayList<String> inputList) throws DukeException {
        int byIndex = inputList.indexOf("/by");
        if (inputList.size() == 1 || byIndex == 1) {
            throw new DukeException("Deadline description cannot be empty!\n" +
                    "Usage: deadline [task description] /by [deadline]");
        }

        if (byIndex == inputList.size() - 1 || byIndex == -1) {
            throw new DukeException("Please input a deadline!\n" +
                    "Usage: deadline [task description] /by [deadline]");
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
        addToList(task);
    }

    public static void addEventCommand(ArrayList<String> inputList) throws DukeException {
        int fromIndex = inputList.indexOf("/from");
        int toIndex = inputList.indexOf("/to");

        if (inputList.size() == 1 || fromIndex == 1 || toIndex == 1) {
            throw new DukeException("Event description cannot be empty!\n" +
                    "Usage: event [task description] /from [start] /to [end]");
        }

        if (fromIndex == -1 || fromIndex == inputList.size() - 1 || fromIndex == toIndex - 1) {
            throw new DukeException("Please enter the start of your event!\n" +
                    "Usage: event [task description] /from [start] /to [end]");
        }

        if (toIndex == -1 || toIndex == inputList.size() - 1 || toIndex == fromIndex - 1) {
            throw new DukeException("Please enter the end of your event!\n" +
                    "Usage: event [task description] /from [start] /to [end]");
        }

        StringBuilder taskDescription = new StringBuilder();
        StringBuilder from = new StringBuilder();
        StringBuilder to = new StringBuilder();

        // Deal with the user sending "/from" before "/to" or vice versa
        if (fromIndex < toIndex) {
            extractEventInfo(inputList, fromIndex, toIndex, taskDescription, from, to);
        } else {
            extractEventInfo(inputList, toIndex, fromIndex, taskDescription, to, from);
        }

        Event task = new Event(taskDescription.toString(), from.toString(), to.toString());
        addToList(task);
    }

    private static void extractEventInfo(ArrayList<String> inputList, int fromIndex, int toIndex, StringBuilder taskDescription, StringBuilder from, StringBuilder to) {
        for (int i = 1; i < fromIndex; i++) {
            if (i != fromIndex - 1) {
                taskDescription.append(inputList.get(i)).append(" ");
            } else {
                taskDescription.append(inputList.get(i));
            }
        }

        for (int i = fromIndex + 1; i < toIndex; i++) {
            if (i != toIndex - 1) {
                from.append(inputList.get(i)).append(" ");
            } else {
                from.append(inputList.get(i));
            }
        }

        for (int i = toIndex + 1; i < inputList.size(); i++) {
            if (i != inputList.size() - 1) {
                to.append(inputList.get(i)).append(" ");
            } else {
                to.append(inputList.get(i));
            }
        }
    }

    public static void deleteTaskCommand(ArrayList<String> inputList) throws DukeException {
        // This means that the user has not supplied any number with the command
        if (inputList.size() == 1) {
            throw new DukeException("Please enter the task you wish to delete!\n" +
                    "Usage: delete [task number]");
        } else {
            try {
                int taskIndex = Integer.parseInt(inputList.get(1));

                String message = "Alright, the following task has been removed:\n" +
                        "\t" + todoList.remove(taskIndex - 1).toString() + "\n";

                int listSize = todoList.size();

                // "task" for singular, "tasks" for plural
                if (listSize == 1) {
                    message += "You now have " + listSize + " task in your list.";
                } else {
                    message += "You now have " + listSize + " tasks in your list.";
                }

                botSays(message);
            } catch (NumberFormatException e) {
                throw new DukeException("Please enter a number for the delete command.\n" +
                        "Usage: delete [task number]");
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("Are you sure that's a valid task number? (Tip: use 'list' to check the number of your task!)\n" +
                        "Usage: delete [task number]");
            }
        }
    }

    public static void helpCommand() {
        String message = "Available commands:\n" +
                "list \t\t- Lists all your tasks\n" +
                "todo \t\t- Create a new todo task.\n" +
                "deadline \t- Create a new task with a deadline.\n" +
                "event \t\t- Create a new task with a starting and ending time.\n" +
                "mark \t\t- Mark a task as completed.\n" +
                "unmark \t\t- Mark a task as not completed.\n" +
                "delete \t\t- Delete a task.";

        botSays(message);
    }
}