import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void echoText(String text) {
        System.out.println("    ____________________________________________________________");
        System.out.printf("    %s\n", text);
        System.out.println("    ____________________________________________________________");
    }

    public static void addList(String text, ArrayList<String> list) {
        list.add(text);
        System.out.println("    ____________________________________________________________");
        System.out.printf("added: %s\n", text);
        System.out.println("    ____________________________________________________________");
    }

    public static void printList(ArrayList<String> list) {
        int count = 1;
        System.out.println("    ____________________________________________________________");
        if (list.size() == 0) {
            System.out.printf("Nothing added to list yet!");
        }
        for (String command : list) {
            System.out.printf("%d. %s\n", count, command);
            count++;
        }
        System.out.println("    ____________________________________________________________");
    }
    public static void main(String[] args) {
        String name = "Yippee";
        ArrayList<String> list = new ArrayList<>();

        //greeting
        System.out.println("    ____________________________________________________________");
        System.out.printf("    Hello! I'm %s\n", name);
        System.out.println("    What can I do for you?");
        System.out.println("    ____________________________________________________________");

        //scan for input
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();

        //echo until user inputs bye
        while(!command.toLowerCase().equals("bye")) {
            if (command.toLowerCase().equals("list")) {
                printList(list);
            } else {
                addList(command, list);
            }
            command = sc.nextLine();
        }

        //exit
        System.out.println("    ____________________________________________________________");
        System.out.println("    Bye. Hope to see you again soon wooo!");
        System.out.println("    ____________________________________________________________");
    }


}
