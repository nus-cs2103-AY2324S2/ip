import java.util.Scanner;

/**
 * This class represents a chat application.
 */
public class Duke {
    private Scanner sc = new Scanner(System.in);
    private Task[] list;
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.startChat();
    }

    /**
     * Constructs a task list of size.
     */
    public Duke() {
        this.list = new Task[100];
    }

    /**
     * Initiates the chat by invoking the sayHi() method.
     * Handles user input to display the list for "list" input, exit the chat for "bye" input,
     * marks or unmarks tasks as done or append to the list for any other input.
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
            else if (userInput.startsWith("mark ")) {
                int num = Integer.parseInt(userInput.replace("mark ", ""));
                markAsDone(num);
            }
            else if (userInput.startsWith("unmark ")) {
                int num = Integer.parseInt(userInput.replace("unmark ", ""));
                unMarkAsDone(num);
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
     * Marks a task as done based on the provided task number.
     *
     * @param num The task number to mark as done.
     */
    public void markAsDone(int num) {
        list[num - 1].markAsDone();
        System.out.println("Nice! I've marked this task as done:\n\t" + list[num - 1] + "\n");
    }

    /**
     * Marks a task as not done based on the provided task number.
     *
     * @param num The task number to mark as not done.
     */
    public void unMarkAsDone(int num) {
        list[num - 1].unMarkAsDone();
        System.out.println("OK, I've marked this task as not done yet:\n\t" + list[num - 1] + "\n");
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
                list[i] = new Task(input);
                System.out.println("\nadded: " + input + "\n");
                return;
            }
        }
    }
}