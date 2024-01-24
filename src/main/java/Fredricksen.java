import java.util.Scanner;
import java.util.ArrayList;
public class Fredricksen {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<String> list = new ArrayList();
        ArrayList<Boolean> boolList = new ArrayList();
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Fredricksen");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
        while (true) {
            System.out.println("");
            String s = in.nextLine();
            String[] split = s.split(" ");
            switch (split[0].toLowerCase()) {
                case "mark":
                    System.out.println("____________________________________________________________");
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("    [X] " + list.get(Integer.parseInt(split[1]) - 1));
                    boolList.set(Integer.parseInt(split[1]) - 1, true);
                    System.out.println("____________________________________________________________");
                    break;
                case "unmark":
                    System.out.println("____________________________________________________________");
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("    [] " + list.get(Integer.parseInt(split[1]) - 1));
                    boolList.set(Integer.parseInt(split[1]) - 1, false);
                    System.out.println("____________________________________________________________");
                    break;
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
                    System.out.println("Here are the tasks in your list:");
                    if (list.size() > 0) {
                        for (int i = 1; i <= list.size(); i++) {
                            String t = boolList.get(i - 1) ? "X" : "";
                            System.out.println(" " + i + ". " + "[" + t + "] " + list.get(i - 1));
                        }
                    }
                    System.out.println("____________________________________________________________");
                    break;
                default:
                    System.out.println("____________________________________________________________");
                    System.out.println("added: " + s);
                    list.add(s);
                    boolList.add(false);
                    System.out.println("____________________________________________________________");
                    break;
            }
        }
    }
}
