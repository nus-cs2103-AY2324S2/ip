import java.util.Scanner; // For reading user input
import java.util.ArrayList; // For storing to-do tasks
public class Duke {
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
                    // If user wants to add a task like "mark the exam papers"
                    addToList(userInput);
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
                    // If user wants to add a task like "unmark the exam papers"
                    addToList(userInput);
                }
            }

            else {
                addToList(userInput);
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
     * Stores a message in the list.
     * @param message Text to store
     */
    public static void addToList(String message) {
            todoList.add(new Task(message));
            botSays("added: " + message);
    }

    /**
     * Formats message that the bot will send.
     * @param message The message for the bot to send.
     */
    public static void botSays(String message) {
        // Change message colour to cyan
        // https://www.w3docs.com/snippets/java/how-to-print-color-in-console-using-system-out-println.html
        System.out.print("\u001B[36m");

        // Length of line to be printed.
        int lineLength = 30;

        printHorizontalLine(lineLength);
        System.out.println(message);
        printHorizontalLine(lineLength);

        // Change message colour back to white
        // https://www.w3docs.com/snippets/java/how-to-print-color-in-console-using-system-out-println.html
        System.out.print("\u001B[37m");
    }
}