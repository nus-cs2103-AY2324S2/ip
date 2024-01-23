import java.util.Scanner;

/**
 * This class represents a chat application.
 */
public class Duke {
    private Scanner sc = new Scanner(System.in);
    private String[] list;
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.startChat();
    }

    /**
     * Constructs a list of size.
     */
    public Duke() {
        this.list = new String[100];
    }

    /**
     * Initiates the chat by invoking the sayHi() method.
     * Handles user input to display the list for "list" input, exit the chat for "bye" input
     * or append to the list for any other input.
     */
    public void startChat() {
        sayHi();

        boolean exited = false;

        while (!exited) {
            String userInput = sc.nextLine();
            if (userInput.equals("bye")) {
                exited = true;
            }
            else if (userInput.equals("list")) {
                displayList();
            }
            else {
                appendList(userInput);
            }
        }
        sayBye();
    }

    /**
     * Displays a starting message to greet the user.
     */
    public void sayHi() {
        System.out.println("Hello! I'm myChats\n" + "What can I do for you?\n");
    }

    /**
     *  Displays an exit message.
     */
    public void sayBye() {
        System.out.println("\nBye. Hope to see you again soon!");
    }

    /**
     * Echoes the user's input back to the console.
     *
     * @param input The user's input to be echoed.
     */
    public void echoMessage(String input) {
        System.out.println(input + "\n");
    }

    /**
     * Displays the current list of items with their respective indices.
     * Skips null or uninitialized elements in the list.
     */
    public void displayList() {
        System.out.println();
        for (int i = 0; i < list.length; i++) {
            if (list[i] != null) {
                System.out.printf("%d. %s\n", i + 1, list[i]);
            }
        }
        System.out.println();
    }

    /**
     * Appends the given input to the list at the first available slot.
     *
     * @param input The input to be added to the list.
     */
    public void appendList(String input) {
        for (int i = 0; i < list.length; i++) {
            if (list[i] == null) {
                list[i] = input;
                System.out.println("\nadded: " + input + "\n");
                return;
            }
        }
    }
}