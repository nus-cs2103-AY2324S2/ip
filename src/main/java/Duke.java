import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<>();

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";


        System.out.println("Hello! I'm tars.");
        System.out.println("What can I do for you?");

        while (true) {
            String comd = scanner.nextLine();
            if (comd.equals("bye")) {
                break;
            }
            if (comd.equals("list")) {
                for (int i = 0; i < list.size(); i++) {
                    System.out.println(i+1 + ". " + list.get(i));
                }
            }
            else {
                list.add(comd);
                System.out.println("added: " + comd);
            }


        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
