import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("    Hello! I'm Hanxiao. \n  What can I do for you?");

        while (true) {
            String inputs = sc.nextLine();
            Command cmd;
            if (inputs.equals("bye")) {
                cmd = new Bye();
            } else if (inputs.equals("List")) {
                cmd = new List();
            } else {
                cmd = new Add(inputs);
            }
            cmd.reply();
        }
    }
}
