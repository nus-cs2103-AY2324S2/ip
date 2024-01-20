import java.util.ArrayList;
import java.util.Scanner;  // Import the Scanner class
public class Duke {
    // class variable
    static String CHATBOT_NAME = "ByteBuddy";
    static String solidLineBreak = "____________________________________________________________";
    static String START_MESSAGE = "Hello! I'm " + CHATBOT_NAME + "\n" + "\t What can I do for you?";
    static String BYE_MESSAGE = "Sad to see you leave :(";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<>();

        // start
        printWithSolidLineBreak(START_MESSAGE);

        // repeating user commands
        while (true) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                break;
            } else if (command.equals("list")){
                printTaskList(taskList);
            } else {
                taskList.add(new Task(command));
                String toPrint = "added: " + command;
                printWithSolidLineBreak(toPrint);
            }
        }

        // bye
        printWithSolidLineBreak(BYE_MESSAGE);
    }

    public static void printWithSolidLineBreak(String s) {
        System.out.println("\t" + solidLineBreak);
        System.out.println("\t " + s);
        System.out.println("\t" + solidLineBreak);
    }

    public static void printTaskList(ArrayList<Task> taskList) {
        System.out.println("\t" + solidLineBreak);
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println("\t " + (i+1) + ". " + taskList.get(i));
        }
        System.out.println("\t" + solidLineBreak);
    }
}
