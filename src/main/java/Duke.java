import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

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
            System.out.println(comd);
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
