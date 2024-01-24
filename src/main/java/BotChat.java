import java.util.Scanner;

public class BotChat {
    static boolean terminate = false;
    static String[] dataStore = new String[100];
    static int lastIdx = 0;

    public static String response(String s) {
        if (s.equals("bye")) {
            terminate = true;
            return "Bye. Hope to see you again soon!";
        } else if (s.equals("list")) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 1; i <= lastIdx; i++) {
                stringBuilder.append(i);
                stringBuilder.append(". ");
                stringBuilder.append(dataStore[i-1]);
                stringBuilder.append("\n ");;
            }
            return stringBuilder.toString();
        } else {
            dataStore[lastIdx] = s;
            lastIdx++;
            return "added: " + s;
        }
    }

    public static void main(String[] args) {
        String greeting = "Hello! I'm BotChat.\n What can I do for you?\n Bye. Hope to see you again soon!";
        System.out.println(greeting);

        Scanner userInput = new Scanner(System.in);

        while (!terminate) {
            String command = userInput.nextLine();
            System.out.println(response(command));
        }
    }
}
