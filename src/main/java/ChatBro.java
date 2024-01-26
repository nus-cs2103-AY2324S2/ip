import java.util.Scanner;
import java.util.HashMap;

public class ChatBro {
    public static void main(String[] args) {
        System.out.println("_________________________\n" +
                " __  __       __\n" +
                " \\ \\/ /__    / /\n" +
                "  \\  / _ \\  /_/ \n" +
                "  /_/\\___/ (_)\n\n" +
                "I'm ChatBro!\n" +
                "What can I do for you bro?\n" +
                "Use the available commands: list, blah, bye, mark, unmark\n" +
                "or type anything else to store it in your list bro.\n" +
                "_________________________\n");

        Scanner sc = new Scanner(System.in);
        boolean isQuit = false;
        String[] taskList = new String[101]; // First element left empty for 1-based indexing
        HashMap<Integer, Boolean> taskDone = new HashMap<>();

        while (!isQuit) {
            String input = sc.nextLine();
            String[] inputSplit = input.split(" ");
            String command = inputSplit[0];
            switch (command) {
                case "list":
                    System.out.println("Here are the tasks in your list bro:\n" +
                            "_________________________");
                    for (int i = 1; i <= 100; i++) {
                        if (taskList[i] == null) {
                            break;
                        }
                        System.out.println(i + ".[" + (taskDone.get(i) ? "X" : " ") + "] " + taskList[i]);
                    }
                    System.out.println("_________________________\n");
                    break;

                case "blah":
                    System.out.println("This is the blah reply bro.\n");
                    break;

                case "mark":
                    int taskNum = Integer.parseInt(inputSplit[1]);
                    taskDone.put(taskNum, true);
                    System.out.println("_________________________\n" +
                            "Got it bro! Task " + taskNum + " is marked as done:\n" +
                            "[" + (taskDone.get(taskNum) ? "X" : " ") + "] " + taskList[taskNum] + "\n" +
                            "_________________________\n");
                    break;

                case "unmark":
                    int taskNum2 = Integer.parseInt(inputSplit[1]);
                    taskDone.put(taskNum2, false);
                    System.out.println("_________________________\n" +
                            "Got it bro! Task " + taskNum2 + " is marked as not done yet:\n" +
                            "[" + (taskDone.get(taskNum2) ? "X" : " ") + "] " + taskList[taskNum2] + "\n" +
                            "_________________________\n");
                    break;

                case "bye":
                    isQuit = true;
                    System.out.println("_________________________\n" +
                            "Bye bro! Hope to see you again soon!\n" +
                            "_________________________\n");
                    break;

                default: // Other input will be stored into taskList
                    for (int i = 1; i <= 100; i++) {
                        if (taskList[i] == null) {
                            taskList[i] = input;
                            taskDone.put(i, false);
                            break;
                        }
                    }
                    System.out.println("_________________________\n" +
                            "Ok bro, I've added: " + input + "\n" +
                            "_________________________\n");
                    break;
            }
        }
        sc.close();
    }
}
