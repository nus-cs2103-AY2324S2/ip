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
        Task[] taskList = new Task[101]; // First element left empty for 1-based indexing

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
                        System.out.println(i + "." + taskList[i].toString());
                    }
                    System.out.println("_________________________\n");
                    break;

                case "blah":
                    System.out.println("This is the blah reply bro.\n");
                    break;

                case "mark":
                    int taskNum = Integer.parseInt(inputSplit[1]);
                    if (taskList[taskNum] == null) {
                        System.out.println("_________________________\n" +
                                "Sorry bro, this task doesn't exist in your list.\n" +
                                "_________________________\n");
                        break;
                    }
                    taskList[taskNum].markAsDone();
                    System.out.println("_________________________\n" +
                            "Got it bro! Task " + taskNum + " is marked as done:\n" +
                            taskList[taskNum].toString() +
                            "\n_________________________\n");
                    break;

                case "unmark":
                    int taskNum2 = Integer.parseInt(inputSplit[1]);
                    if (taskList[taskNum2] == null) {
                        System.out.println("_________________________\n" +
                                "Sorry bro, this task doesn't exist in your list.\n" +
                                "_________________________\n");
                        break;
                    }
                    taskList[taskNum2].markAsUndone();
                    System.out.println("_________________________\n" +
                            "Got it bro! Task " + taskNum2 + " is marked as not done yet:\n" +
                            taskList[taskNum2].toString() +
                            "\n_________________________\n");
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
                            taskList[i] = new Task(input);
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
