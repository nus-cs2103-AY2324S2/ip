import java.util.Scanner;

public class Numerator {
    public static void main(String[] args) {
        String logo =
                "____________________________________________________________\n" +
                        " Hello! I'm Numerator\n" +
                        " What can I do for you?\n" +
                        "____________________________________________________________\n";
        String bye = "    ____________________________________________________________\n" +
                "     Bye. Hope to see you again soon!\n" +
                "    ____________________________________________________________";

        Scanner scanner = new Scanner(System.in);
        System.out.println(logo);

        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println(bye);
                break;
            }

            System.out.println(input);


        }
    }
}
