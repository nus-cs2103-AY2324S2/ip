import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static final String NAME = "Fatnom";
    private static final int lineLength = 60;

    public static void printLine() {
        for (int i = 0; i < lineLength; i++) {
            System.out.print("_");
        }
        System.out.print("\n");
    }

    public static void printMessage(String message) {
        Duke.printLine();
        System.out.println(message);
        Duke.printLine();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> taskList= new ArrayList<>();

        String welcomeMessage = "hello! i'm " + NAME + "!!!\n" +
                                "i see you've adopted me! yay!\n" +
                                "what can i do for you?";
        Duke.printMessage(welcomeMessage);

        while (true) {
            String input = sc.nextLine();
            String[] inputTokens = input.split(" ");
            String command = inputTokens[0];

            if (command.equalsIgnoreCase("bye")) {
                Duke.printMessage("bye! come visit me again!");
                break;
            } else if (command.equalsIgnoreCase("list")) {
                int numOfTasks = taskList.size();
                String listMessage = "alright! here is your task list:\n";
                for(int i = 0; i < taskList.size(); i++) {
                    int index = i + 1;
                    String taskMessage = taskList.get(i).printTask();
                    if (i == taskList.size() - 1) {
                        listMessage += index + ". " + taskMessage;
                    } else {
                        listMessage += index + ". " + taskMessage + "\n";
                    }
                }
                Duke.printMessage(listMessage);
            } else if (command.equalsIgnoreCase("mark")) {
                int taskNum = Integer.parseInt(inputTokens[1]);
                taskList.get(taskNum - 1).complete();
                String markedMessage = "good job!!! i've marked this task as done:\n" +
                                        "   " + taskList.get(taskNum - 1).printTask();
                Duke.printMessage(markedMessage);
            } else if (command.equalsIgnoreCase("unmark")) {
                int taskNum = Integer.parseInt(inputTokens[1]);
                taskList.get(taskNum - 1).unmark();
                String unmarkedMessage = "okay! i've unmarked this task:\n" +
                        "   " + taskList.get(taskNum - 1).printTask();
                Duke.printMessage(unmarkedMessage);
            } else {
                int len = inputTokens.length;
                String taskName = "";
                for(int i = 0; i < len; i++) {
                    taskName += " " + inputTokens[i];
                }
                taskList.add(new Task(taskName));
                Duke.printMessage("added: " + taskName);
            }
        }
        sc.close();
    }
}

