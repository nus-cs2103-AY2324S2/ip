import javax.crypto.spec.PSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;  // Import the Scanner class
import java.util.stream.Collectors;

public class Duke {
    // class variable
    static String CHATBOT_NAME = "ByteBuddy";
    static String solidLineBreak = "____________________________________________________________";
    static String START_MESSAGE = "Hello! I'm " + CHATBOT_NAME + "\n" + "\t What can I do for you?";
    static String BYE_MESSAGE = "Sad to see you leave :(";

    static ArrayList<Task> taskList;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        taskList = new ArrayList<>();

        // start
        printWithSolidLineBreak(START_MESSAGE);

        // repeating user commands
        label:
        while (true) {
            String command = sc.next();
            String info = sc.nextLine().trim();
            switch (command) {
                case "bye":
                    break label;
                case "list":
                    printTaskList(taskList);
                    break;
                case "mark":
                    int markIndex = Integer.parseInt(info.trim()) - 1;
                    String markToPrint = taskList.get(markIndex).markAsDone();
                    printWithSolidLineBreak(markToPrint);
                    break;
                case "unmark":
                    int unmarkIndex = Integer.parseInt(info.trim()) - 1;
                    String unmarkToPrint =taskList.get(unmarkIndex).unmarkAsDone();
                    printWithSolidLineBreak(unmarkToPrint);
                    break;
                case "todo":
                    Task todo = new Todo(info);
                    taskList.add(todo);
                    printTaskAddedWithSolidLineBreak(todo);
                    break;
                case "deadline":
                    List<String> deadlineInfo = Arrays.stream(info.split("/")).map(String::trim).collect(Collectors.toList());
                    Task deadline = new Deadline(deadlineInfo.get(0), deadlineInfo.get(1).substring(3));
                    taskList.add(deadline);
                    printTaskAddedWithSolidLineBreak(deadline);
                    break;
                case "event":
                    List<String> eventInfo = Arrays.stream(info.split("/")).map(String::trim).collect(Collectors.toList());
                    Task event = new Event(eventInfo.get(0), eventInfo.get(1).substring(5), eventInfo.get(2).substring(3));
                    taskList.add(event);
                    printTaskAddedWithSolidLineBreak(event);
                    break;
                default:
                    System.out.println("we are in default!! something is wrong :(");
//                    taskList.add(new Task(command + info));
//                    String toPrint = "added: " + command + info;
//                    printWithSolidLineBreak(toPrint);
//                    break;
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

    public static void printTaskAddedWithSolidLineBreak(Task task) {
        System.out.println("\t" + solidLineBreak);
        System.out.println("\t Got it. I've Added this task:");
        System.out.println("\t\t " + task);
        System.out.println("\t Now you have " + taskList.size() + " tasks in the list.");
        System.out.println("\t" + solidLineBreak);
    }

    public static void printTaskList(ArrayList<Task> taskList) {
        System.out.println("\t" + solidLineBreak);
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println("\t " + (i+1) + "." + taskList.get(i));
        }
        System.out.println("\t" + solidLineBreak);
    }
}
