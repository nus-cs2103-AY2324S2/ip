import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {

        // templates
        String line = "\t____________________________________________________________\n";
        String intro = line +
                "\t Hello! I'm Megatron\n" +
                "\t What can I do for you?\n" +
                line;
        String outro = line +
                "\t Bye. Hope to see you again soon!\n" +
                line;

        Scanner sc = new Scanner(System.in);
        String cmd = "";

        System.out.println(intro);

        while (!cmd.equals("bye")) {
            cmd = sc.nextLine();
            if (cmd.equals("bye")) break;

            System.out.println(
                    line + "\t" + cmd + "\n" + line
            );
        }

        System.out.println(outro);
    }
}
