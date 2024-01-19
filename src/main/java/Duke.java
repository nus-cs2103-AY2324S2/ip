import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Lucifer\n" + "What can I do for you?");
        System.out.println("____________________________________________________________");
        Scanner sc = new Scanner(System.in);
        String user_word = sc.nextLine();
        List l = new ArrayList();
        while (!user_word.equals("bye")) {
            if (user_word.equals("list")) {
                System.out.println("____________________________________________________________");
                for (int x = 0; x < l.size(); x++) {
                    System.out.println(x + 1 + ". " + l.get(x));
                }
            }
            if(!user_word.equals("list")) {
                System.out.println("____________________________________________________________");
                System.out.println("added: " + user_word);
            }
            l.add(user_word);
            //System.out.println(user_word);
            System.out.println("____________________________________________________________");
            user_word = sc.nextLine();


        }

        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}
