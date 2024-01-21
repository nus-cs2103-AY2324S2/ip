import java.util.Scanner;

public class Duke {

    static ListOfMessages messages;

    public static int loop(Scanner sc) {
        String res = sc.nextLine();
        String output = null;
        try {
            if (res.equals("bye")) {
                return -1;
            } else if (res.equals("list")) {
                output = messages.list();
            } else if (res.startsWith("mark")) {
                output = messages.mark(res);
            } else if (res.startsWith("unmark")) {
                output = messages.unmark(res);
            } else if (res.startsWith("delete")) {
                output = messages.delete(res);
            } else if (res.startsWith("todo")) {
                output = messages.todo(res.substring(4));
            } else if (res.startsWith("deadline")) {
                output = messages.deadline(res.substring(8));
            } else if (res.startsWith("event")) {
                output = messages.event(res.substring(5));
            } else if (res.isEmpty()) {
                output = "";
            } else {
                throw new ChatException("I'm sorry, but I don't know what that means :-(");
            }
        } catch (ChatException e) {
            output = e.getMessage();
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
