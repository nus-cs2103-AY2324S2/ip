import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String divider = "---------------------------------------------------------------";
        String name = "Dwight";
        System.out.printf("%s\nHello! I'm %s\nWhat Can I do for you?\n%s\n"
                , divider, name, divider);
        String input;
        while (scanner.hasNext()) {
            input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.printf("\t%s\n\tBye. Hope to see you again soon!\n\t%s\n", divider,divider);
                scanner.close();
                return;
            } else {
                System.out.printf("\t%s\n\t%s\n\t%s\n", divider, input, divider);
            }
        }

    }
}
