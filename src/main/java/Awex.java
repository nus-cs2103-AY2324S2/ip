import java.util.*;

public class Awex {
    public static void main(String[] args) {
        /* String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n"; */
        System.out.println("Hello! I'm AWEX!\nWhat can I do for you?");
        ArrayList<String> list = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        String next = sc.nextLine();
        while (!next.equals("bye")) {
            if (next.equals("list")) {
                int len = list.size();
                for (int i = 1; i <= len; i++) {
                    System.out.println(i + ". " + list.get(i - 1));
                }
            } else {
                list.add(next);
                System.out.println("added: " + next);
            }
            next = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
