import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
        //Chatbot greets user
        System.out.println("Hello! I'm Chatteroo\n" + "What can I do for you?\n");

        //Chatbot echos user commands
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            System.out.println(input + "\n");
            input = sc.nextLine();
        }
        //Chatbot exits
        System.out.println("Bye. Hope to see you again soon!\n");
    }
}
