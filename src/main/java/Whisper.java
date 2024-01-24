import java.util.Scanner;

public class Whisper {
    static String line = "-------------------------------------------------\n";
    static String name = "Whisper";
    static String welcomeMsg = "Hello! I'm " + name + " , your personal chatbot!\n" +
            "What can I do for you?\n";
    static String byeMsg = line + "Bye. Hope to see you soon!\n" + line;

    // Main method
    public static void main(String[] args) {
        System.out.println(line + welcomeMsg + line);
        startChat();
    }

    // method for chats
    public static void startChat() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            // read user input
            System.out.println("Enter your input: ");
            String input = sc.nextLine();

            // break if user exists the bot
            if (input.equalsIgnoreCase("bye")) {
                System.out.println(byeMsg);
                break;
            } else {
                System.out.println(line + input + "\n" + line);
            }
        }
        sc.close();
    }
}