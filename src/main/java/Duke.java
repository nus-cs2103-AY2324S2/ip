import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Jojo");
        System.out.println("What can I do for you?");
        Scanner sc = new Scanner(System.in);
        String userTxt = sc.nextLine();
        System.out.println("added: " + userTxt);
        System.out.println("Bye. Hope to see you again soon!");

//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
    }
}
