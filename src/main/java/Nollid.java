import java.util.Scanner; // For reading user input
import java.util.ArrayList; // For storing to-do tasks
public class Nollid {
    private static ArrayList<Task> todoList = new ArrayList<>(100);
    public static void main(String[] args) {
        sendWelcomeMessage();

        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();

        while (true) {
            userInput = userInput.strip();

            if (userInput.equalsIgnoreCase("bye")) {
                break;
            }

            else if (userInput.equalsIgnoreCase("list")) {
                // List items in to-do list
                String response = "Here are the tasks in your list: \n";
                if (todoList.isEmpty())
                {
                    response = "Your list is empty!";
                }

                for (int i = 0; i < todoList.size(); i++) {
                    if (i < todoList.size() - 1) {
                        response += i + 1 + "." + todoList.get(i).toString() + "\n";
                    } else {
                        response += i + 1 + "." + todoList.get(i).toString();
                    }
                }
                botSays(response);
            }

            else if (userInput.length() > 4 && userInput.substring(0,4).equalsIgnoreCase("mark")) {
                int index;

                try {
                    index = Integer.parseInt(userInput.substring(5));
                    todoList.get(index - 1).markDone();
                    String response = "Good job! I've marked this task as done: \n"
                            + "\t " + todoList.get(index - 1).toString();
                    botSays(response);
                } catch (NumberFormatException nfe) {
                    botSays("Sorry, I couldn't quite understand that.");
                }
            }

            else if (userInput.length() > 6 && userInput.substring(0,6).equalsIgnoreCase("unmark")) {
                int index;

                try {
                    index = Integer.parseInt(userInput.substring(7));
                    todoList.get(index - 1).markNotDone();
                    String response = "Alright, I've marked this task as not done yet: \n"
                            + "\t " + todoList.get(index - 1).toString();
                    botSays(response);
                } catch (NumberFormatException nfe) {
                    botSays("Sorry, I couldn't quite understand that.");

                }
            }

            else if (userInput.length() > 4 && userInput.substring(0,4).equalsIgnoreCase("todo")) {
                String taskDescription = userInput.substring(5);
                ToDo task = new ToDo(taskDescription);
                addToList(task);
            }
            else if (userInput.length() > 8 && userInput.substring(0,8).equalsIgnoreCase("deadline")) {
                int byIndex = userInput.indexOf("/by");
                String taskDescription = userInput.substring(9, byIndex - 1);
                String deadline = userInput.substring(byIndex + 4);

                Deadline task = new Deadline(taskDescription, deadline);
                addToList(task);
            }
            else if (userInput.length() > 5 && userInput.substring(0,5).equalsIgnoreCase("event")) {
                int fromIndex = userInput.indexOf("/from");
                int toIndex = userInput.indexOf("/to");

                String taskDescription;
                String from;
                String to;

                // Deal with the user sending "/from" before "/to" or vice versa
                if (fromIndex < toIndex) {
                    taskDescription = userInput.substring(6, fromIndex - 1);
                    from = userInput.substring(fromIndex + 6, toIndex - 1);
                    to = userInput.substring(toIndex + 4);
                }
                else {
                    taskDescription = userInput.substring(6, toIndex - 1);
                    from = userInput.substring(fromIndex + 6);
                    to = userInput.substring(toIndex + 4, fromIndex - 1);
                }

                Event task = new Event(taskDescription, from, to);
                addToList(task);
            }
            else {
                botSays("Sorry, I couldn't quite understand that.");
            }
            userInput = scanner.nextLine();
        }
        sendGoodbyeMessage();
    }

    /**
     * Sends a welcome message upon starting the bot, with horizontal lines
     * printed for visual separation.
     */
    public static void sendWelcomeMessage() {
        String welcomeMessage = "Hello! I'm Nollid.\n"
                + "What can I do for you?";
        botSays (welcomeMessage);
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
     * @param task Task to store
     */
    public static void addToList(Task task) {
            todoList.add(task);

            String message = "Alright, added:\n"
                    + "\t" + task.toString()+ "\n";

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
     * @param message The message for the bot to send.
     */
    public static void botSays(String message) {
        // Length of line to be printed.
        int lineLength = 30;

        printHorizontalLine(lineLength);
        System.out.println(message);
        printHorizontalLine(lineLength);
    }
}