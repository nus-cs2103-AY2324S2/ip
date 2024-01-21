import java.io.*;
import java.util.*;

public class Duke {
    // constants
    static final String LINE = "\t――――――――――――――――――――――――――――――――――――――――\n";
    static final String CHAT_BOT_NAME = "Bob";
    static final String EXIT_COMMAND = "bye";
    static final String LIST = "list";
    static ArrayList<String> store = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        greet();
        boolean isExiting = false;
        while (!isExiting) {
            String input = br.readLine();
            switch (input) {
                case EXIT_COMMAND:
                    isExiting = true;
                    break;
                case LIST:
                    System.out.println(LINE);
                    if (store.isEmpty()) {
                        System.out.println("\tStore is empty\n" + LINE);
                    }
                    for (int i = 0; i < store.size(); i++){
                        System.out.println("\t" + (i+1) + ". " + store.get(i) + "\n");
                    }
                    System.out.println(LINE);
                    break;
                default:
                    echo(input);
                    store.add(input);
            }
        }
        exit();
    }

    // greet user
    public static void greet() {
        System.out.println(LINE + "\t" + "Hello! I'm " + CHAT_BOT_NAME + "\n\t"
                + "What can I do for you?\n" + LINE);
    }

    // exit statement
    public static void exit() {
        System.out.println(LINE + "\t" + "Bye! Hope to see you again soon!\n" + LINE);
    }

    public static void echo(String input) {
        System.out.println(LINE + "\tadded: " + input + "\n" + LINE);
    }
}
