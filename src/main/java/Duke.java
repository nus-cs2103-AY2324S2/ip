import java.util.Scanner;

public class Duke {
    public static int echo(Scanner sc) {
        String res = sc.nextLine();
        if (res.equals("bye")) {
            return -1;
        }
        System.out.println(res + "\n");
        return 0;
    }
    public static void main(String[] args) {
        String msg =
                "Hello! I'm Refinement\n"
                + "What can I do for you?\n\n";
        System.out.println(msg);

        Scanner sc = new Scanner(System.in);
        while (echo(sc) == 0);
        System.out.println("Bye. Hope to see you again soon!");
    }
}
