import java.util.Scanner;

/**
 * This class represents a chat application.
 */
public class Duke {
    private Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.startChat();
    }

    /**
     * Initiates the chat by invoking the sayHi() and sayBye() methods.
     */
    public void startChat() {
        sayHi();

        boolean exited = false;

        while (!exited) {
            String toEcho = sc.nextLine();
            if (toEcho.equals("bye")) {
                exited = true;
            }
            else {
                echoMessage(toEcho);
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
        System.out.println("Bye. Hope to see you again soon!\n");
    }

    /**
     * Echoes the user's input back to the console.
     *
     * @param input The user's input to be echoed.
     */
    public void echoMessage(String input) {
        System.out.println(input + "\n");
    }
}