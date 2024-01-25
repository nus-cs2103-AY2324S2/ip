import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        //Greets User
        String lineBreak = "____________________________________________________";
        System.out.println(lineBreak + '\n' +
                "Hello! I'm Pororo" + '\n' +
                "What can I do for you?" + '\n' +
                lineBreak);

        Scanner sc = new Scanner(System.in);
        String user_input = sc.nextLine();

        System.out.println(lineBreak + '\n' +
                "Bye. Hope to see you again soon!" + '\n' +
                lineBreak);
    }
}



//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);

