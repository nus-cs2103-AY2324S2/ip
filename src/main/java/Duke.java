import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String Greeting = "____________________________________________________________\n" +
                " Hello! I'm VICTOR\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n";
        String Ending =
                "____________________________________________________________\n"+
                        " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________";
        String barrier = "____________________________________________________________";
        Boolean exit = true;
        System.out.println(Greeting);
        while (exit) {
            Scanner myObj = new Scanner(System.in);
            String userInput = myObj.nextLine();
            if (userInput.equals("bye")) {
                exit = false;
            } else {
                System.out.println(barrier);
                System.out.println(userInput);
                System.out.println(barrier);
            }
        }
        System.out.println(Ending);

    }
}
