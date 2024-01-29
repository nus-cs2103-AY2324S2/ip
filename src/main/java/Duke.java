

public class Duke {
    static String horzLine = "____________________________________________________________";
    static String chatbotName = "Destiny";
    static String greetingMessage = horzLine
            + "\nGreetings! I'm " + chatbotName + "\nHow may I serve you?\n"
            + horzLine;
    static String goodbyeMessage = horzLine +
            "\nBye. Hope to see you again soon!\n"
            + horzLine;

    static Task[] taskStorage = new Task[100];
    static int numInStorage = 0;

    public static void echo(String message) {
        Task latestTask = new Task(message);
        taskStorage[numInStorage] = latestTask;
        numInStorage++;
        System.out.println(horzLine);
        System.out.println("added: " + message);
        System.out.println(horzLine);
    }
    public static void list() {
        System.out.println(horzLine);
        for (int i = 0; i < numInStorage; i++) {
            int j = i + 1;
            System.out.println(j + ". " + taskStorage[i].getTaskAsString());
        }
        System.out.println(horzLine);
    }

    public static void markDone(int index) {
        taskStorage[index].markAsDone();
        System.out.println(horzLine);
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("  " + taskStorage[index].getTaskAsString());
        System.out.println(horzLine);
    }

    public static void markNotDone(int index) {
        taskStorage[index].markAsUndone();
        System.out.println(horzLine);
        System.out.println("OK, I've marked this task as not done yet: ");
        System.out.println("  " + taskStorage[index].getTaskAsString());
        System.out.println(horzLine);
    }
    public static void main(String[] args) {
        System.out.println(greetingMessage);

        ChatbotUser user = new ChatbotUser();

        while(!user.getUserInput().equalsIgnoreCase("bye")) {
            user.inputMessage();
            String userMessage = user.getUserInput();
            if (userMessage.equalsIgnoreCase("list")) {
                list();
            } else if (userMessage.toLowerCase().contains("unmark")) {
                String[] arrOfStr = userMessage.split(" ");
                if (arrOfStr.length == 2) {
                    int taskIndex = Integer.valueOf(arrOfStr[1]);
                    markNotDone(taskIndex - 1);
                } else {
                    System.out.println(horzLine);
                    System.out.print("command failed");
                    System.out.println(horzLine);
                }
            } else if (userMessage.toLowerCase().contains("mark")) {
                String[] arrOfStr = userMessage.split(" ");
                if (arrOfStr.length == 2) {
                    int taskIndex = Integer.valueOf(arrOfStr[1]);
                    markDone(taskIndex - 1);
                } else {
                    System.out.println(horzLine);
                    System.out.print("command failed");
                    System.out.println(horzLine);
                }
            } else if (!userMessage.equalsIgnoreCase("bye")) {
                echo(user.getUserInput());
            }
        }
        System.out.println(goodbyeMessage);

    }
}
