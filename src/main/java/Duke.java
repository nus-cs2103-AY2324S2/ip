import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);

        String hello = "Hello!\n" + "What can I do for you?\n";
        System.out.println(hello);

        String bye = "See you soon!";


        while (true) {
            Scanner input = new Scanner(System.in);
            String str = input.nextLine();
            if (str.equals("bye") || str.equals("Bye")) {
                System.out.println(bye);
                break;
            } else {
                System.out.println(str);
            }
        }
    }
}
