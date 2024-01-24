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
        TaskList taskList = new TaskList();

        while (true) {
            // read user input
            System.out.println("Enter your input: ");
            String input = sc.next();

            // break if user exists the bot
            if (input.equalsIgnoreCase("bye")) {
                System.out.println(byeMsg);
                break;
                // show user's list
            } else if (input.equalsIgnoreCase("list")) {
                System.out.println(line + "Your Task List: ");
                taskList.getTasks();
                System.out.println(line);
                // store user list and display them back
            } else {
                taskList.addTask(input);
                //System.out.println(line + "Task added: " + input + "\n" + line);
            }
        }
        sc.close();
    }
}