import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> AL = new ArrayList<>();

        printGreeting();

        while (true) {
            String input = sc.nextLine();

            if (input.equalsIgnoreCase("bye")) {
                break;
            }
            
            switch (input) {
                case "list":
                    printDiv();
                    for (int i = 0; i < AL.size(); i++) {
                        int num = i + 1;
                        System.out.println("\t" + num + ". " + AL.get(i));
                    }
                    printDiv();
                    break;
                default:
                    printEcho("added: " + input);
                    AL.add(input);
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

    public static void printEcho(String msg) {
        printDiv();
        System.out.println("\t" + msg);
        printDiv();
    }
}
