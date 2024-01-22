import java.util.Scanner;
public class KaiYap {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("\t____________________________________________________________\n" +
                "\tHello! I'm KaiYap.\n" +
                "\tWhat can I do for you?\n" +
                "\t____________________________________________________________\n"
        );
        String input = "";
        while (true) {
            input = sc.nextLine();
            if (input.equals("bye")) {
                break;
            }
            System.out.println("\t____________________________________________________________\n" +
                    "\t" + input + "\n" +
                    "\t____________________________________________________________\n");
        }
        System.out.println("\t____________________________________________________________\n" +
                "\tBye. Hope to see you again soon!\n" +
                "\t____________________________________________________________\n");
    }
}
