import java.util.Scanner;

public class Duke {

    static ListOfMessages messages;

    public static int loop(Scanner sc) {
        String res = sc.nextLine();
        String output = null;
        if (res.equals("bye")) {
            return -1;
        } else if (res.equals("list")) {
            output = messages.list();
        } else if (res.startsWith("mark")) {
            int idx = Integer.parseInt(res.split(" ")[1]);
            output = messages.mark(idx-1);
        } else if (res.startsWith("unmark")) {
            int idx = Integer.parseInt(res.split(" ")[1]);
            output = messages.unmark(idx-1);
        } else {
            output = messages.add(res);
        }
        System.out.println(output);
        return 0;
    }
    public static void main(String[] args) {
        String msg =
                "Hello! I'm Refinement\n"
                + "What can I do for you?\n\n";
        System.out.println(msg);

        Scanner sc = new Scanner(System.in);
        messages = new ListOfMessages();
        while (loop(sc) == 0);
        sc.close();
        System.out.println("Bye. Hope to see you again soon!");
    }
}
