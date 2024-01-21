import java.io.*;

public class Duke {
    static String horizontalLine = "\t――――――――――――――――――――――――――――――――――――――――\n";
    static String chatBotName = "Bob";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        greet();
        while (true) {
            String input = br.readLine();
            if (input.equals("bye")) {
                break;
            } else {
                System.out.println(horizontalLine + "\t" + input + "\n" + horizontalLine);
            }
        }
        exit();
    }

    // greet user
    public static void greet() {
        System.out.println(horizontalLine + "\t" + "Hello! I'm " + chatBotName + "\n\t"
                + "What can I do for you?\n" + horizontalLine);
    }

    // exit statement
    public static void exit() {
        System.out.println(horizontalLine + "\t" + "Bye! Hope to see you again soon!\n" + horizontalLine);
    }

}
