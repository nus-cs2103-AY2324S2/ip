import java.util.Scanner;
import java.util.ArrayList;
import java.util.ListIterator;

public class Bond {
    public static void main(String[] args) {

        String line = "____________________________________________________________";

        String logo = "Bond";
        System.out.println(String.format("Hello! I'm %s \nWhat can I do for you? \n%s\n", logo, line));

        Scanner sc = new Scanner(System.in);

        ArrayList<String> List = new ArrayList<String>();

        while (true) {

            String userInput = sc.nextLine();

            if (userInput.equals("bye")) {
                System.out.println(String.format("\nBye. Hope to see you again soon! \n%s", line));
                System.exit(0);
            } else {

                if (userInput.equals("list")) {
                    ListIterator toPrint = List.listIterator();

                    while (toPrint.hasNext()) {
                        System.out.println(String.format("\n    %d. %s", toPrint.nextIndex() + 1, toPrint.next()));
                    }

                    System.out.println(line);
                } else {
                    List.add(userInput);
                    System.out.println(String.format("\n    Added: %s \n%s", userInput, line));
                }
            }
        }
    }
}
