import java.util.Scanner;

public class Awex {
    public static void main(String[] args) {
        /* String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n"; */
        System.out.println("Hello! I'm AWEX!\nWhat can I do for you?");
        Scanner sc = new Scanner(System.in);
        String next = sc.nextLine();
        while (!next.equals("bye")) {
            System.out.println(next);
            next = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
