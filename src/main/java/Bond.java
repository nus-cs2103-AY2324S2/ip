import java.util.Scanner;

public class Bond {
    public static void main(String[] args) {

        String line = "____________________________________________________________";

        String logo = "Bond";
        System.out.println(String.format("Hello! I'm %s \nWhat can I do for you? \n%s\n", logo, line));

        Scanner sc = new Scanner(System.in);

        while (true) {

            String userInput = sc.nextLine();

            if (userInput.equals("bye")) {
                System.out.println(String.format("Bye. Hope to see you again soon! \n%s", line));
                System.exit(0);
            } else {
                System.out.println(String.format("\n    %s \n%s", userInput, line));
            }
        }
    }
}
