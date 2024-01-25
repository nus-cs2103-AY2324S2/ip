import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> AL = new ArrayList<>();

        printGreeting();

        while (true) {
            String input = sc.nextLine();
            String inputs[] = input.split(" ", 2);
            String firstWord = inputs[0];

            if (firstWord.equalsIgnoreCase("bye")) {
                break;
            }
            
            switch (firstWord) {
                case "list":
                    printDiv();
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < AL.size(); i++) {
                        int num = i + 1;
                        System.out.println("\t" + num + ". " + AL.get(i));
                    }
                    printDiv();
                    break;
                case "mark":
                    printDiv();
                    Task taskToMark = AL.get(Integer.parseInt(inputs[1]) - 1);
                    taskToMark.mark();
                    System.out.println("\t" + "Nice! I've marked this task as done: ");
                    System.out.println("\t" + taskToMark);
                    printDiv();
                    break;
                case "unmark":
                    printDiv();
                    Task taskToUnmark = AL.get(Integer.parseInt(inputs[1]) - 1);
                    taskToUnmark.unmark();
                    System.out.println("\t" + "OK, I've marked this task as not done yet: ");
                    System.out.println("\t" + taskToUnmark);
                    printDiv();
                    break;
                case "deadline":
                    // separate to description: deadlineInfo[0]: return book 
                    // and deadline: deadlineInfo[1]: by Sunday
                    String[] deadlineInfo = inputs[1].split("/", 2);
                    printDiv();
                    System.out.println("\tGot it. I've added this task:");
                    Deadline deadline = new Deadline(deadlineInfo[0], deadlineInfo[1].replaceFirst("by", ""));
                    AL.add(deadline);
                    System.out.println("\t" + deadline);
                    printListCounter(AL);
                    printDiv();
                default:
                    break;
            }
        }

        printDiv();
        System.out.println("\tBye. Hope to see you again soon!");
        printDiv();

        sc.close();
    }

    public static void printGreeting() {
        printDiv();
        System.out.println("\tHello! I'm Puffin.");
        System.out.println("\tWhat can I do for you?");
        printDiv();
    }

    public static void printDiv() {
        String DIV = "____________________________________________________________";
        System.out.println("\t" + DIV);
    }

    public static void printListCounter(ArrayList<Task> AL) {
        System.out.println("\tNow you have " + AL.size() + " tasks in the list.");
    }
}
