import java.util.Objects;
import java.util.Scanner;

public class Wei {
    public static void main(String[] args) {
        String greet = "Hello! I'm Wei.\n" + "What can I do for you?";
        String exit = "Bye. Hope to see you again soon!";
        String split = "______________________________";
        // greet
        System.out.println(greet);
        System.out.println(split);

        String[] text = new String[100];
        int count = 0;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if(Objects.equals(input, "bye")) {
                break;
            } else if (Objects.equals(input, "list")) {
                if(text[0] == null) {
                    System.out.println(split);
                    continue;
                }
                // list
                for (int i = 0; i < count; i++) {
                    System.out.println(String.valueOf(i + 1) + ". " + text[i]);
                }
                System.out.println(split);
            } else {
                // add
                text[count] = input;
                count++;
                // echo
                System.out.println("added: " + input);
                System.out.println(split);
            }
        }

        // exit
        System.out.println(exit);
        System.out.println(split);
    }
}
