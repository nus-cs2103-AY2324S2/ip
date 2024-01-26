import java.util.Scanner;

public class Duke {

    static ListOfMessages messages;

    public static int loop(Scanner sc) {
        String res = sc.nextLine();
        String output = null;
        try {
            String command = res.split(" ")[0];
            switch (command) {
            case "bye":
                return -1;
            case "list":
                output = messages.list();
                break;
            case "mark":
                output = messages.mark(res);
                break;
            case "unmark":
                output = messages.unmark(res);
                break;
            case "delete":
                output = messages.delete(res);
                break;
            case "todo":
                output = messages.todo(res.substring(4));
                break;
            case "deadline":
                output = messages.deadline(res.substring(8));
                break;
            case "event":
                output = messages.event(res.substring(5));
                break;
            case "":
                output = "";
                break;
            default:
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
