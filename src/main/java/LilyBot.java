import java.util.Scanner;

public class LilyBot {
    public static void main(String[] args) {
        Scanner myCom = new Scanner(System.in);
        String sayHi = "Hello! I'm LilyBot\nWhat can I do for you?\n";
        String divider = "_____________________________________________________";
        System.out.println(sayHi + divider );
        String command = myCom.nextLine();

        while (command.equals("list")
                || command.equals("blah")) {
            System.out.println("  " + command + "\n" + divider);
            command = myCom.nextLine();
        }
        String sayBye = "Bye. Hope to see you again soon!\n";
        System.out.println(sayBye);
    }
}
