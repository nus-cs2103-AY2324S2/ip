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

            ArrayList<String> inputList = new ArrayList<>(Arrays.asList(userInput.split(" ")));
            String command = inputList.get(0);

            if (command.equalsIgnoreCase("bye")) {
                break;
            } else if (command.equalsIgnoreCase("list")) {
                listCommand();
            } else if (command.equalsIgnoreCase("mark")) {
                markCommand(inputList);
            } else if (command.equalsIgnoreCase("unmark")) {
                unmarkCommand(inputList);
            } else if (command.equalsIgnoreCase("todo")) {
                addTodoTask(inputList);
            } else if (command.equalsIgnoreCase("deadline")) {
                addDeadlineTask(inputList);
            } else if (command.equalsIgnoreCase("event")) {
                addEventTask(inputList);
            } else {
                botSays("Sorry, I couldn't quite understand that command.");
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
    public static void markCommand(ArrayList<String> inputList) {
        // This means that the user has not supplied any number with the command
        if (inputList.size() == 1) {
            botSays("Please enter the task you wish to mark as done! Usage: mark [task number]");
        } else {
            try {
                int taskIndex = Integer.parseInt(inputList.get(1));
                markDone(taskIndex);
            } catch (NumberFormatException e) {
                botSays("Please enter a task number for the mark command.");
            } catch (IndexOutOfBoundsException e) {
                botSays("Are you sure that's a valid task number? (Tip: use 'list' to check the number of your task!)");
            }
        }
    }

    public static void unmarkCommand(ArrayList<String> inputList) {
        // This means that the user has not supplied any number with the command
        if (inputList.size() == 1) {
            botSays("Please enter the task you wish to mark as not done! Usage: unmark [task number]");
        } else {
            try {
                int taskIndex = Integer.parseInt(inputList.get(1));
                markNotDone(taskIndex);
            } catch (NumberFormatException e) {
                botSays("Please enter a task number for the unmark command.");
            } catch (IndexOutOfBoundsException e) {
                botSays("Are you sure that's a valid task number? (Tip: use 'list' to check the number of your task!)");
            }
        }
    }

    public static void markDone(int taskIndex) {
        todoList.get(taskIndex - 1).markDone();
        String response = "Good job! I've marked this task as done: \n" + "\t " + todoList.get(taskIndex - 1).toString();
        botSays(response);
    }

    public static void markNotDone(int taskIndex) {
        todoList.get(taskIndex - 1).markNotDone();
        String response = "Alright, I've marked this task as not done yet: \n" + "\t " + todoList.get(taskIndex - 1).toString();
        botSays(response);
    }

    public static void addTodoTask(ArrayList<String> inputList) {
        if (inputList.size() == 1) {
            botSays("Todo description cannot be empty! Usage: todo [task description]");
            return;
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

    public static void addDeadlineTask(ArrayList<String> inputList) {
        int byIndex = inputList.indexOf("/by");
        if (inputList.size() == 1 || byIndex == 1) {
            botSays("Deadline description cannot be empty! Usage: deadline [task description] /by [deadline]");
            return;
        }

        if (byIndex == inputList.size() - 1 || byIndex == -1) {
            botSays("Please input a deadline! Usage: deadline [task description] /by [deadline]");
            return;
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

    public static void addEventTask(ArrayList<String> inputList) {
        int fromIndex = inputList.indexOf("/from");
        int toIndex = inputList.indexOf("/to");

        if (inputList.size() == 1 || fromIndex == 1 || toIndex == 1) {
            botSays("Event description cannot be empty! Usage: event [task description] /from [start] /to [end]");
            return;
        }

        if (fromIndex == -1 || fromIndex == inputList.size() - 1 || fromIndex == toIndex - 1) {
            botSays("Please enter the start of your event! Usage: event [task description] /from [start] /to [end]");
            return;
        }

        if (toIndex == -1 || toIndex == inputList.size() - 1 || toIndex == fromIndex - 1) {
            botSays("Please enter the end of your event! Usage: event [task description] /from [start] /to [end]");
            return;
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
}