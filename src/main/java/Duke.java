import java.util.*;
public class Duke {
    public static void main(String[] args) {

        String line = "_____________________________________________";
        String greeting = "Hello! I'm Donald.\nWhat can I do for you?\n";
        System.out.println(line);
        System.out.println(greeting + line + "\n");

        Scanner sc = new Scanner(System.in);
        String input;
        List<String> list = new ArrayList<String>();

        while (sc.hasNextLine()) {
            input = sc.nextLine();
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                System.out.println(line);
                int index = 1;
                for (String s: list) {
                    System.out.println(index + ". " + s);
                    index++;
                }
                System.out.println(line + "\n");
            } else {
                list.add(input);
                System.out.println(line);
                System.out.println("added: " + input + "\n" + line + "\n");
            }
        }

        String closing = "Bye. Hope to see you again soon!\n";
        System.out.println(line);
        System.out.println(closing + line);
    }
}
