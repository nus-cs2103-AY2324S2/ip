import java.util.Scanner;
public class Tes {
    public static void main(String[] args) {
        String line = "    _______________________________________________________________\n";
        Scanner scanner = new Scanner(System.in);
        System.out.println(line +
                "    Hello! I'm Tes\n" +
                "    What can I do for you?\n" +
                line);

        while (true) {
            String command = scanner.nextLine();

            if (command.equals("bye")) {
                System.out.println(line
                        + "    Bye. Hope to see you again soon!\n"
                        + line);
                break;
            }

            System.out.println(line
                    + "    " + command + "\n"
                    + line);
        }
    }
}
