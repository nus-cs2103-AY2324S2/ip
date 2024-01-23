import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String greet = "Hello! I'm DOUMMI\n" +
                "What can I do for you?";

        System.out.println(greet);

        Scanner cmd = new Scanner(System.in);

        while (!cmd.hasNext("bye")) {
            String userCmd = cmd.nextLine();
            System.out.println(userCmd);
        }


        String bye = "Hope to see you again soon!";

        System.out.println(bye);
    }
}
