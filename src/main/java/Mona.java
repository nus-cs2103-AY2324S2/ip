import java.util.Scanner;

public class Mona {
    public static void main(String[] args) {
        String introduction = "  ____________________________________________________________\n"
                + "   Hello! I'm Mona\n"
                + "   What can I do for you?\n"
                + "  ____________________________________________________________\n";
        String farewell = "  ____________________________________________________________\n"
                + "   Bye. Hope to see you again soon!\n"
                + "  ____________________________________________________________";
        Scanner sc = new Scanner(System.in);
        System.out.println(introduction);
        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println(farewell);
                break;
            }
            String response = "  ____________________________________________________________\n"
                    + "  " + input + "\n"
                    + "  ____________________________________________________________\n";
            System.out.print(response);
        }

    }
}
