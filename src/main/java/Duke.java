import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm GHBot");
        System.out.println("What can I do for you?");
        List<Task> lst = new ArrayList<>();

        while (true) {
            Scanner sc = new Scanner(System.in);
            String str = sc.nextLine();
            String[] subStr = str.split(" ");
            String instr = subStr[0];

            if (instr.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (instr.equalsIgnoreCase("list")) {
                for (int i = 0; i < lst.size(); i++) {
                    System.out.println(i + 1 + "." + lst.get(i));
                }
            } else if (instr.equalsIgnoreCase("mark")) {
                int lstNo = Integer.parseInt(subStr[1]);
                for (int i = 0; i < lst.size(); i++) {
                    if (i + 1 == lstNo) {
                        lst.get(i).isCompleted();
                        System.out.println("Nice! I've marked this task as done:\n" + lst.get(i));
                    }
                }
            } else if (instr.equalsIgnoreCase("unmark")) {
                int lstNo = Integer.parseInt(subStr[1]);
                for (int i = 0; i < lst.size(); i++) {
                    if (i + 1 == lstNo) {
                        lst.get(i).isNotCompleted();
                        System.out.println("OK, I've marked this task as not done yet:\n" + lst.get(i));
                    }
                }
            } else {
                lst.add(new Task(str));
                System.out.println("added: " + str);
            }
        }
    }
}
