import java.util.Scanner;
import java.util.ArrayList;
import java.lang.StringBuilder;

public class Duke {
    static ArrayList<String> listOfMessages = new ArrayList<>();

    public static String list() {
        StringBuilder res = new StringBuilder();
        int idx = 1;
        for (String msg : listOfMessages) {
            res.append(String.format("%d. %s\n", idx, msg));
            idx++;
        }
        return res.toString();
    }
    public static int loop(Scanner sc) {
        String res = sc.nextLine();
        String output = null;
        if (res.equals("bye")) {
            return -1;
        } else if (res.equals("list")) {
            output = list();
        } else {
            listOfMessages.add(res);
            output = "added: " + res + "\n";
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
        while (loop(sc) == 0);
        System.out.println("Bye. Hope to see you again soon!");
    }
}
