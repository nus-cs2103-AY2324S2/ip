import java.util.Scanner;
import java.util.ArrayList;
public class Fredricksen {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList list = new ArrayList();
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Fredricksen");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
        while (true) {
            System.out.println("");
            String s = in.nextLine();
            switch (s.toLowerCase()) {
                case "bye":
                    System.out.println("____________________________________________________________");
                    System.out.println("Bye. Hope to see you again soon!");
                    System.out.println("____________________________________________________________");
                    break;
                case "":
                    System.out.println("____________________________________________________________");
                    System.out.println("Please specify a task.");
                    System.out.println("____________________________________________________________");
                    break;
                case "list":
                    System.out.println("____________________________________________________________");
                    if (list.size() > 0) {
                        for (int i = 1; i <= list.size(); i++) {
                            System.out.println(" " + i + ". " + list.get(i-1));
                        }
                    }
                    System.out.println("____________________________________________________________");
                    break;
                default:
                    System.out.println("____________________________________________________________");
                    System.out.println("added: " + s);
                    list.add(s);
                    System.out.println("____________________________________________________________");
                    break;
            }
        }
    }
}