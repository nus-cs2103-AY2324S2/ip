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
            if (input.equalsIgnoreCase("bye")) {
                Duke.printMessage("bye! come visit me again!");
                break;
            } else if (input.equalsIgnoreCase("list")) {
                int numOfTasks = taskList.size();
                String listMessage = "";
                for(int i = 0; i < taskList.size(); i++) {
                    int index = i + 1;
                    String taskName = taskList.get(i).printName();
                    if (i == taskList.size() - 1) {
                        listMessage += index + ". " + taskName;
                    } else {
                        listMessage += index + ". " + taskName + "\n";
                    }
                }
                Duke.printMessage(listMessage);
            } else {
                taskList.add(new Task(input));
                Duke.printMessage("added: " + input);
            }
        }
        sc.close();
    }
}

