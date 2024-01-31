import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("____________________________________________________________\n" +
                " Hello! I'm Your Only Friend\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n");

        Scanner whatToDo = new Scanner(System.in);
        String whatToDoCall = whatToDo.nextLine().trim();

        while (!whatToDoCall.equals("bye")) {
            System.out.println("____________________________________________________________\n");
            System.out.println(whatToDoCall);
            System.out.println("____________________________________________________________\n");
            whatToDoCall = whatToDo.nextLine().trim();
        }

        System.out.println("____________________________________________________________\n"
                + " Bye. Hope to see you again soon!\n -Your Only Friend\n"
                + "____________________________________________________________\n");

    }
}