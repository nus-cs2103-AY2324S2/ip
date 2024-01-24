import java.util.ArrayList;
import java.util.Scanner;

/**
 * A chatbot called Harper.
 *
 * @author gosongying
 * @version CS2103T AY23/24 Sem 2
 */
public class Harper {

    ArrayList<String> list = new ArrayList<>();

    /**
     * Greets the user.
     */
    public void greet() {
        String line = "_________________________________________________________";

        System.out.println(line + "\n"
                + "Hello! I am Harper.\n"
                + "What can I do for you?\n"
                + line);
    }

    /**
     * Exit the chat.
     */
    public void exit() {
        String line = "_________________________________________________________";

        System.out.println(line + "\n"
                + "Hope to see you again soon! Peace out!\n"
                + line);
    }

    /**
     * Starts the chat, reads user's input and respond to user.
     * Saves user's input and displays back when requested.
     */
    public void startChat() {
        this.greet();
        String line = "_________________________________________________________";
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                this.exit();
                scanner.close();
                break;
            } else if (input.equals("list")) {
                System.out.println(line);
                if (this.list.isEmpty()) {
                    System.out.println("Nothing is in your list!");
                    System.out.println(line);
                } else {
                    for (int i = 0; i < this.list.size(); i++) {
                        System.out.println(i + 1 + ". " + this.list.get(i));
                    }
                    System.out.println(line);
                }
            } else {
                this.list.add(input);
                System.out.println(line + "\n" + "added: " + input + "\n" + line);
            }
        }
    }
}
